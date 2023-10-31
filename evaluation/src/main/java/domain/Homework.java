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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("homework")
public class Homework implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId(type = IdType.AUTO)
    private String Wid;

    @TableField
    @NotBlank(message = "用户名不能为空")
    private String Tiltle;

    //todo: time format
    @TableField
    @DateTimeFormat()
    @NotBlank(message = "开始时间不能为空")
    private String StartTime;

    @TableField
    @NotBlank(message = "截止时间不能为空")
    private String EndTime;

    @TableField
    @NotBlank(message = "编辑状态不能为空")
    private int ReleaseStatus;

    @TableField
    @NotBlank(message = "互评状态不能为空")
    private String EvaluateStatus;

    //todo: url
}
