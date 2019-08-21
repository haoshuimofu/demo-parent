package com.demo.domain.model;

import java.util.Date;

/**
 * @Description Automatically generated by MBG.
 * @Author ddmc
 * @Datetime @mbg.generated 2019-08-20 13:54:39
 * @Table t_user
 */
public class User {
    /**
     * ID: id
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private Integer id;

    /**
     * 用户名: username
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private String username;

    /**
     * 密码: password
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private String password;

    /**
     * gender
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private Integer gender;

    /**
     * 手机号码: cellphone
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private String cellphone;

    /**
     * 邮箱: email
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private String email;

    /**
     * 创建时间: created
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private Date created;

    /**
     * 创建人: created_by
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private Integer createdBy;

    /**
     * 修改时间: modified
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private Date modified;

    /**
     * 修改人: modified_by
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private Integer modifiedBy;

    /**
     * 介绍: description
     * 
     * @mbg.generated 2019-08-20 13:54:39
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}