package com.demo.components.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * Solr中Product Bean
 *
 * @Author wude
 * @Create 2017-08-24 14:03
 */
public class SolrProductBean {

    @Field("id")
    private String id;
    @Field(value = "code")
    private String code;
    @Field(value = "name")
    private String name;
    @Field(value = "skus")
    private List<String> skus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkus() {
        return skus;
    }

    public void setSkus(List<String> skus) {
        this.skus = skus;
    }
}
