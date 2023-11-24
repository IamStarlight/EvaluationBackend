#!/usr/bin/env python
# coding: utf-8

# In[46]:


import numpy as np
import pandas as pd
import matplotlib as mpl
import matplotlib.pyplot as plt
import csv
import collections
import time

datapath = 'C:/Users/84579/Desktop/shixun/BJTU-23Winter-MutualEvaluationSys/EvaluationBackend/evaluation/source/new.csv'
data = pd.read_csv(datapath)
t = data.iloc[:, -4:]
# print(t)

class KMEANS:
    '''使用python语言实现聚类算法'''
    
    def __init__(self, k, times):
        '''初始化方法
        
        Parameters
        -----
        k : int
            聚类的个数
            
        times : int
            聚类迭代的次数
        '''
        
        self.k = k
        self.times = times
        
    def fit(self, X):
        '''根据提供的训练数据，对模型进行训练。
        
        Parameters
        ------
        X : 类数组类型，形状为：[样本数量，特征数量]
            待训练的样本特征属性。
        '''
        
        X = np.asarray(X)
        # 设置随机种子，以便于可以产生相同的随机序列。（随机的结果可以重现。）
        np.random.seed(0)
        # 从随机数组中随机选择k个点作为初始聚类中心。
        self.cluster_centers_ = X[np.random.randint(0, len(X), self.k)]
        # 用来存放每个点所处的组或簇
        self.labels_ = np.zeros(len(X))
        
        for i in range(self.times):
            for index, x in enumerate(X):
                # 计算每个样本与聚类中心的距离，采用欧式距离
                dis = np.sqrt(np.sum((x - self.cluster_centers_) ** 2, axis=1))
                # 将最小的索引赋值给标签数组。索引的值是当前点所属的簇。范围为[0, k - 1]
                self.labels_[index] = dis.argmin()
            # 循环遍历每一个簇
            for i in range(self.k):
                # 计算每个簇内所有点的均值，更新聚类中心
                self.cluster_centers_[i] = np.mean(X[self.labels_ == i], axis=0)
            
    def predict(self, X):
        '''根据参数传递的样本，对样本数据进行预测。（预测样本属于哪一个簇中）
        
        Parameters
        -----
        X : 类数组类型。形状为：[样本数量，特征数量]
            待预测的特征属性。
            
        Returns
        -----
        result : 数组类型
            预测的结果。每一个X所属的簇。
        '''
        
        X = np.asarray(X)
        result = np.zeros(len(X))
        for index, x in enumerate(X):
            # 计算样本到每个聚类中心的距离
            dis = np.sqrt(np.sum((x - self.cluster_centers_) ** 2, axis=1))
            # 找到距离最近的聚类中心，划分类别。
            result[index] = dis.argmin()
        return result

class Hungarian():
    """
    """
    def __init__(self, input_matrix=None, is_profit_matrix=False):
        """
        输入为一个二维嵌套列表
        is_profit_matrix=False代表输入是消费矩阵（需要使消费最小化），反之则为利益矩阵（需要使利益最大化）
        """
        if input_matrix is not None:
            # 保存输入
            my_matrix = np.array(input_matrix)
            self._input_matrix = np.array(input_matrix)
            self._maxColumn = my_matrix.shape[1]
            self._maxRow = my_matrix.shape[0]

            # 本算法必须作用于方阵，如果不为方阵则填充0变为方阵
            matrix_size = max(self._maxColumn, self._maxRow)
            pad_columns = matrix_size - self._maxRow
            pad_rows = matrix_size - self._maxColumn
            my_matrix = np.pad(my_matrix, ((0,pad_columns),(0,pad_rows)), 'constant', constant_values=(0))

            # 如果需要，则转化为消费矩阵
            if is_profit_matrix:
                my_matrix = self.make_cost_matrix(my_matrix)

            self._cost_matrix = my_matrix
            self._size = len(my_matrix)
            self._shape = my_matrix.shape

            # 存放算法结果
            self._results = []
            self._totalPotential = 0
        else:
            self._cost_matrix = None
    def make_cost_matrix(self,profit_matrix):
        '''利益矩阵转化为消费矩阵，输出为numpy矩阵'''
        # 消费矩阵 = 利益矩阵最大值组成的矩阵 - 利益矩阵
        matrix_shape = profit_matrix.shape
        offset_matrix = np.ones(matrix_shape, dtype=int) * profit_matrix.max()
        cost_matrix = offset_matrix - profit_matrix
        return cost_matrix
    def get_results(self):
        """获取算法结果"""
        return self._results
    def calculate(self):
        """
        实施匈牙利算法的函数
        """
        result_matrix = self._cost_matrix.copy()

        # 步骤 1: 矩阵每一行减去本行的最小值
        for index, row in enumerate(result_matrix):
            result_matrix[index] -= row.min()

        # 步骤 2: 矩阵每一列减去本行的最小值
        for index, column in enumerate(result_matrix.T):
            result_matrix[:, index] -= column.min()
        #print('步骤2结果 ',result_matrix)
        # 步骤 3： 使用最少数量的划线覆盖矩阵中所有的0元素
        # 如果划线总数不等于矩阵的维度需要进行矩阵调整并重复循环此步骤
        total_covered = 0
        while total_covered < self._size:
            time.sleep(1)
            #print("---------------------------------------")
            #print('total_covered: ',total_covered)
            #print('result_matrix:',result_matrix)
            # 使用最少数量的划线覆盖矩阵中所有的0元素同时记录划线数量
            cover_zeros = CoverZeros(result_matrix)
            single_zero_pos_list = cover_zeros.calculate()
            covered_rows = cover_zeros.get_covered_rows()
            covered_columns = cover_zeros.get_covered_columns()
            total_covered = len(covered_rows) + len(covered_columns)

            # 如果划线总数不等于矩阵的维度需要进行矩阵调整（需要使用未覆盖处的最小元素）
            if total_covered < self._size:
                result_matrix = self._adjust_matrix_by_min_uncovered_num(result_matrix, covered_rows, covered_columns)
        #元组形式结果对存放到列表
        self._results = single_zero_pos_list
        # 计算总期望结果
        value = 0
        for row, column in single_zero_pos_list:
            value += self._input_matrix[row, column]
        self._totalPotential = value

    def get_total_potential(self):
        return self._totalPotential

    def _adjust_matrix_by_min_uncovered_num(self, result_matrix, covered_rows, covered_columns):
        """计算未被覆盖元素中的最小值（m）,未被覆盖元素减去最小值m,行列划线交叉处加上最小值m"""
        adjusted_matrix = result_matrix
        # 计算未被覆盖元素中的最小值（m）
        elements = []
        for row_index, row in enumerate(result_matrix):
            if row_index not in covered_rows:
                for index, element in enumerate(row):
                    if index not in covered_columns:
                        elements.append(element)
        min_uncovered_num = min(elements)
        #print('min_uncovered_num:',min_uncovered_num)
        #未被覆盖元素减去最小值m
        for row_index, row in enumerate(result_matrix):
            if row_index not in covered_rows:
                for index, element in enumerate(row):
                    if index not in covered_columns:
                        adjusted_matrix[row_index,index] -= min_uncovered_num
        #print('未被覆盖元素减去最小值m',adjusted_matrix)
    
        #行列划线交叉处加上最小值m
        for row_ in covered_rows:
            for col_ in covered_columns:
                #print((row_,col_))
                adjusted_matrix[row_,col_] += min_uncovered_num
        #print('行列划线交叉处加上最小值m',adjusted_matrix)

        return adjusted_matrix



class CoverZeros():
    """
    使用最少数量的划线覆盖矩阵中的所有零
    输入为numpy方阵
    """
    def __init__(self, matrix):
        # 找到矩阵中零的位置（输出为同维度二值矩阵，0位置为true，非0位置为false）
        self._zero_locations = (matrix == 0)
        self._zero_locations_copy = self._zero_locations.copy()
        self._shape = matrix.shape

        # 存储划线盖住的行和列
        self._covered_rows = []
        self._covered_columns = []

    def get_covered_rows(self):
        """返回覆盖行索引列表"""
        return self._covered_rows

    def get_covered_columns(self):
        """返回覆盖列索引列表"""
        return self._covered_columns

    def row_scan(self,marked_zeros):
        '''扫描矩阵每一行，找到含0元素最少的行，对任意0元素标记（独立零元素），划去标记0元素（独立零元素）所在行和列存在的0元素'''
        min_row_zero_nums = [9999999,-1]
        for index, row in enumerate(self._zero_locations_copy):#index为行号
            row_zero_nums = collections.Counter(row)[True]
            if row_zero_nums < min_row_zero_nums[0] and row_zero_nums!=0:
                #找最少0元素的行
                min_row_zero_nums = [row_zero_nums,index]
        #最少0元素的行
        row_min = self._zero_locations_copy[min_row_zero_nums[1],:]
        #找到此行中任意一个0元素的索引位置即可
        row_indices, = np.where(row_min)
        #标记该0元素
        #print('row_min',row_min)
        marked_zeros.append((min_row_zero_nums[1],row_indices[0]))
        #划去该0元素所在行和列存在的0元素
        #因为被覆盖，所以把二值矩阵_zero_locations中相应的行列全部置为false
        self._zero_locations_copy[:,row_indices[0]] = np.array([False for _ in range(self._shape[0])])
        self._zero_locations_copy[min_row_zero_nums[1],:] = np.array([False for _ in range(self._shape[0])])

    def calculate(self):
        '''进行计算'''
        #储存勾选的行和列
        ticked_row   = []
        ticked_col   = []
        marked_zeros = []
        #1、试指派并标记独立零元素
        while True:
            #print('_zero_locations_copy',self._zero_locations_copy)
            #循环直到所有零元素被处理（_zero_locations中没有true）
            if True not in self._zero_locations_copy:
                break
            self.row_scan(marked_zeros)
            
        #2、无被标记0（独立零元素）的行打勾
        independent_zero_row_list = [pos[0] for pos in marked_zeros]
        ticked_row = list(set(range(self._shape[0])) - set(independent_zero_row_list))
        #重复3,4直到不能再打勾
        TICK_FLAG = True
        while TICK_FLAG:
            #print('ticked_row:',ticked_row,'   ticked_col:',ticked_col)
            TICK_FLAG = False
            #3、对打勾的行中所含0元素的列打勾
            for row in ticked_row:
                #找到此行
                row_array = self._zero_locations[row,:]
                #找到此行中0元素的索引位置
                for i in range(len(row_array)):
                    if row_array[i] == True and i not in ticked_col:
                        ticked_col.append(i)
                        TICK_FLAG = True
            
            #4、对打勾的列中所含独立0元素的行打勾
            for row,col in marked_zeros:
                if col in ticked_col and row not in ticked_row:
                    ticked_row.append(row)
                    FLAG = True
        #对打勾的列和没有打勾的行画画线
        self._covered_rows    = list(set(range(self._shape[0])) - set(ticked_row))
        self._covered_columns = ticked_col
            
        return marked_zeros



kmeans = KMEANS(5, 50)
kmeans.fit(t)


# 1. 创建文件对象
f = open('C:/Users/84579/Desktop/shixun/BJTU-23Winter-MutualEvaluationSys/EvaluationBackend/evaluation/result/new.csv','w',newline='',encoding='utf-8')

# 2. 基于文件对象构建 csv写入对象
csv_writer = csv.writer(f)

# 3. 构建列表头
csv_writer.writerow(["eva_sid","be_eva_sid"])

for i in range(5):
    list1 = t[kmeans.labels_ == i]

    score_max = list1.iloc[:].score.max()
    score_min = list1.iloc[:].score.min()
    for i in range(0,(len(list1.iloc[:]))):
        a = ((list1.iloc[i].score) - score_min) / (score_max - score_min)
        list1.iloc[i].score = int(round(a*100))

    willingness_max = list1.willingness.max()
    willingness_min = list1.willingness.min()
    for i in range(0,(len(list1))):  
        a = ((list1.iloc[i].willingness) - willingness_min) / (willingness_max - willingness_min)
        list1.iloc[i].willingness = int(round(a*100))
        
    ability_max = list1.ability.max()
    ability_min = list1.ability.min()
    for i in range(0,(len(list1))):  
        a = ((list1.iloc[i].ability) - ability_min) / (ability_max - ability_min)
        list1.iloc[i].ability = int(round(a*100))

    n = len(list1)
    matrix = np.zeros((n, n))
    list2 = []

    for i in range(n):
        a = list1.iloc[i].score * 0.2 + list1.iloc[i].willingness * 0.35 + 0.2 + list1.iloc[i].ability * 0.35 + 0.2 + list1.iloc[i].evaed * 0.1
        list2.append(a) 

    for i in range(n):
        for j in range(n):
            if i==j:
                matrix[i][j] = 1000
            else:
                if ((list2[i-1].item() - list2[j-1].item()) > 0) :
                    matrix[i][j] = list2[i-1].item() - list2[j-1].item()
                else:
                    matrix[i][j] = list2[j-1].item() - list2[i-1].item()
                matrix[j][i] = matrix[i][j]

    matrix2 = np.zeros((n+1, n+1))

    for i in range(n):
        matrix2[i+1][0] = list1.index[i]
    for i in range(n):
        matrix2[0][i+1] = list1.index[i]


    number = 3
    for i in range(number):
        cost_matrix = matrix
        hungarian = Hungarian(cost_matrix)
        hungarian.calculate()
        print("Calculated value:\t", hungarian.get_total_potential())  
        print("Results:\n\t", hungarian.get_results())
        list3 = hungarian.get_results()
        for i in range(len(list3)):
            matrix2[list3[i][0] + 1][list3[i][1] + 1] = 1    
        print(matrix2)
        for i in range(len(list3)):
            matrix[list3[i][0]][list3[i][1]] = 1000 
    
    for i in range(1,n + 1):
        for j in range(1,n + 1):
            if matrix2[i][j]==1:
                # 4. 写入csv文件内容
                csv_writer.writerow([int(matrix2[i][0]),int(matrix2[0][j])]) 
    
    # csv_writer.writerow(["l",'18','男'])
    # csv_writer.writerow(["c",'20','男'])
    # csv_writer.writerow(["w",'22','女'])
            
# 5. 关闭文件
f.close()


