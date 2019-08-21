package com.demo.dao.codegen;

import com.demo.components.mybatis.codegen.MybatisGenerator;

/**
 * @Author ddmc
 * @Create 2019-05-09 11:39
 */
public class DaoGenerator {
    static final String KANBAN = "classpath:codegen/generatorConfig.xml";

    public static void main(String[] args) {
        try {
            MybatisGenerator.runFromClassPath(KANBAN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}