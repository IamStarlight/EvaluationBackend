package com.lantu;



import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.security.DomainLoadStoreParameter;
import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {

        String username = "gxc";
        String password = "qwe123123";
        String moduleName = "domain";
        String mapperLocation = "src\\main\\resources\\mapper\\"+moduleName;
//        GlobalConfig globalConfig = new com.baomidou.mybatisplus.core.config.GlobalConfig();


//        String tables = ;

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/vueblog2?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8", username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author("gxccc") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride()
                            .outputDir("src\\main\\java\\1"); // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.lantu") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("article","article_tags","comments","collect_art","roles","roles_user","tags") ;// 设置需要生成的表名
//                            .addTablePrefix("x_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
