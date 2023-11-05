package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.evaluation.enums.PerEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user")
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "permission", discriminatorType = DiscriminatorType.INTEGER)
public class User implements Serializable {

    private static final long serialVersionUID = -7674269980281525370L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotBlank(message = "管理员账号不能为空")
    protected Integer id;

    @Column
//    @NotBlank(message = "用户名不能为空")
    protected String name;

    @Column
//    @NotBlank(message = "密码不能为空")
    protected String password;

    @Column
//    @NotBlank(message = "权限不能为空")
    protected String permission;

    @Column
//    @Email(message = "邮箱格式不正确")
//    @NotBlank(message = "权限不能为空")
    protected String email;

}
