package domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;

    @TableId(type = IdType.AUTO)
    private String Id;

    @TableField
    @NotBlank(message = "用户名不能为空")
    private String Name;

    @TableField
    @NotBlank(message = "密码不能为空")
    private String Password;

    @TableField
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String Email;

    @TableField
    @NotBlank(message = "权限不能为空")
    private String Permission;

}
