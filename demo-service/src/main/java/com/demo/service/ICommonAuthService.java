package com.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.demo.domain.model.Resource;
import com.demo.domain.model.Role;

import java.util.List;

/**
 * 22222
 *
 * @Author dell
 * @Create 2017-07-03 16:41
 */
public interface ICommonAuthService {

    List<Role> getUserRoleList(Integer userId);

    List<Resource> getUserPermissionList(Integer userId);

    JSONArray initMenuTree(Integer userId);

}
