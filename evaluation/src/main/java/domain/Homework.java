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
@TableName("homework")
public class Homework implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId(value = "wid",type = IdType.AUTO)
    private String Wid;

    @TableId(value = "cid")
    @NotBlank(message = "课程id不能为空")
    private String cid;

    @TableField
    @NotBlank(message = "用户名不能为空")
    private String Title;

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "开始时间不能为空")
    private Date StartTime;

    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotBlank(message = "截止时间不能为空")
    private Date EndTime;

    @TableField
    @NotBlank(message = "编辑状态不能为空")
    private int EditStatus;

    @TableField
    @NotBlank(message = "互评状态不能为空")
    private String EvaluateStatus;

    @TableField
    @NotBlank(message = "URL不能为空")
    private String URL;
}
