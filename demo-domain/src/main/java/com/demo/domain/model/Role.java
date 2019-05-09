package com.demo.domain.model;

import java.util.Date;

/**
 * @Description Automatically generated by MBG.
 * @Author ddmc
 * @Datetime @mbg.generated 2019-05-09 14:45:18
 * @Table t_role
*/
public class Role {
    /**
     * ID: id
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private Integer id;

    /**
     * 角色名称: role_name
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private String roleName;

    /**
     * 角色描述: role_desc
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private String roleDesc;

    /**
     * 创建时间: created
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private Date created;

    /**
     * 创建人: created_by
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private Integer createdBy;

    /**
     * 修改时间: modified
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private Date modified;

    /**
     * 修改人: modified_by
     * 
     * @mbg.generated 2019-05-09 14:45:18
      */
    private Integer modifiedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}