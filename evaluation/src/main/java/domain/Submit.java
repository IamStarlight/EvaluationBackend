package domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stu_homework")
public class Submit implements Serializable {
    @TableId(value = "sid")
    private String Sid;

    @TableId(value = "wid")
    private String Wid;

    @TableId(value = "cid")
    private String Cid;

    @TableField
    @NotBlank(message = "url不能为空")
    private String URL;

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "提交时间不能为空")
    private Date SubmitTime;

    @TableField
    @NotBlank(message = "是否晚交不能为空")
    private String isLate;

    @TableField
    @NotBlank(message = "总成绩不能为空")
    private String TotalGrade;

    @TableField
    @NotBlank(message = "教师评分不能为空")
    private String TeacherGrade;

    @TableField
    @NotBlank(message = "教师评论不能为空")
    private String TeacherComments;

}
