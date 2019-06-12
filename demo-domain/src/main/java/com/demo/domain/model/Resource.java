package com.demo.domain.model;

import java.util.Date;

/**
 * @Description Automatically generated by MBG.
 * @Author ddmc
 * @Datetime @mbg.generated 2019-06-12 13:20:08
 * @Table t_resource
*/
public class Resource {
    /**
     * ID: id
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Integer id;

    /**
     * 资源名称: res_name
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private String resName;

    /**
     * 资源描述: res_desc
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private String resDesc;

    /**
     * 资源类型: 1、Menu; 2、Page; 3、Function: type
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Integer type;

    /**
     * 父资源ID: parent_id
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Integer parentId;

    /**
     * 是否是父资源: 1、是; 0、不是: is_parent
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Integer isParent;

    /**
     * 资源请求路径: res_path
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private String resPath;

    /**
     * 创建时间: created
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Date created;

    /**
     * 创建人: created_by
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Integer createdBy;

    /**
     * 修改时间: modified
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Date modified;

    /**
     * 修改人: modified_by
     * 
     * @mbg.generated 2019-06-12 13:20:08
     */
    private Integer modifiedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
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