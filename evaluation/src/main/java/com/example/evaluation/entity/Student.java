package com.example.evaluation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 9115429216382631425L;

}
