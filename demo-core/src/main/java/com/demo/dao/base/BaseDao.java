package com.demo.dao.base;

import java.util.List;

/**
 * Mapper接口父接口, 系统统一将XxMapper重命名为XxDao
 *
 * @Author wude
 * @Create 2017-06-12 13:40
 */
public interface BaseDao<T, ID> {

    int insert(T t);

    int insertList(List<T> ts);

    <ID> int deleteById(ID id);

    <ID> T selectById(ID id);

    T selectOne(T t);

    List<T> selectList(T t);

    List<T> selectAll();

    int update(T t);

}
