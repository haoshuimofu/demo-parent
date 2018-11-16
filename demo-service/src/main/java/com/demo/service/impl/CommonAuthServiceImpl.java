package com.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.dao.ResourceDao;
import com.demo.dao.RoleDao;
import com.demo.domain.model.Resource;
import com.demo.domain.model.Role;
import com.demo.service.ICommonAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * sdfsdf
 *
 * @Author dell
 * @Create 2017-07-03 16:42
 */
@Service
public class CommonAuthServiceImpl implements ICommonAuthService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Role> getUserRoleList(Integer userId) {
        return roleDao.selectRoleListByUserId(userId);
    }

    @Override
    public List<Resource> getUserPermissionList(Integer userId) {
        List<Role> userRoles = this.getUserRoleList(userId);
        List<Integer> roleIdList = userRoles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        return resourceDao.selectResourceListByRoleIds(roleIdList);

    }

    @Override
    public JSONArray initMenuTree(Integer userId) {
        List<Role> userRoles = this.getUserRoleList(userId);
        List<Integer> roleIdList = userRoles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        List<Resource> userMenuResourceList = resourceDao.selectResourceListByRoleIdsAndType(roleIdList, 1);
        return this.menuResourceListToTree(userMenuResourceList, 0);
    }

    private JSONArray menuResourceListToTree(List<Resource> resources, Integer level) {
        JSONArray array = new JSONArray();
        List<Resource> topLevelList = resources.stream().filter(resource -> level.compareTo(resource.getParentId()) == 0).collect(Collectors.toList());
        for (Resource menu : topLevelList) {
            JSONObject json = new JSONObject();
            json.put("id", menu.getId());
            json.put("name", menu.getResName());
            json.put("url", menu.getResPath());
            if (menu.getIsParent() == 1) {
                json.put("children", this.menuResourceListToTree(resources, level + 1));
            }
            array.add(json);
        }
        return array;
    }
}
