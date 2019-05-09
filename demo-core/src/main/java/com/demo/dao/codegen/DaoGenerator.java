package com.demo.dao.codegen;

import com.demo.components.mybatis.codegen.DemoMybatisGenerator;

/**
 * @Author ddmc
 * @Create 2019-05-09 11:39
 */
public class DaoGenerator {

    public static void main(String[] args) {
        try {
            DemoMybatisGenerator.runFromClassPath("classpath:codegen/generatorConfig.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}