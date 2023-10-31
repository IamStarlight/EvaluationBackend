package domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;

    @TableId(type = IdType.AUTO)
    private String Cid;

    @TableField
    @NotBlank(message = "课程名不能为空")
    private String Cname;

    @TableField
    @NotBlank(message = "授课教师不能为空")
    private String Tid;
}
