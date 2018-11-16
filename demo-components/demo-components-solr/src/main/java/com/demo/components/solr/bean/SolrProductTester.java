package com.demo.components.solr.bean;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.util.*;

/**
 * Solr Product 测试
 *
 * @Author dell
 * @Create 2017-08-24 14:13
 */
public class SolrProductTester {

    public static void main(String[] args) {

        HttpSolrServer solrProductServer = new HttpSolrServer("http://10.0.0.44:8983/solr/sbt_product_sku_info");

        SolrProductBean solrProduct1 = new SolrProductBean();
        solrProduct1.setId(1 + "");
        solrProduct1.setCode("code-001");
        solrProduct1.setName("code-name-001");
        solrProduct1.setSkus(Arrays.asList("code-sku-001-1", "code-sku-001-2", "code-sku-001-3"));

        SolrProductBean solrProduct2 = new SolrProductBean();
        solrProduct2.setId(2 + "");
        solrProduct2.setCode("code-002");
        solrProduct2.setName("code-name-002");
        solrProduct2.setSkus(Arrays.asList("code-sku-002-1", "code-sku-002-2", "code-sku-002-3"));

        List<SolrProductBean> solrProductBeans = new ArrayList<>();
        solrProductBeans.add(solrProduct1);
        solrProductBeans.add(solrProduct2);

        Sku sku1 = new Sku();
        sku1.setId("001");
        sku1.setSku("001001");

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "001");
        Map<String, Object> skuSet = new HashMap<>();
        skuSet.put("set", "001001");
        doc.addField("sku", skuSet);

        try {
            UpdateResponse response = solrProductServer.add(doc);
            solrProductServer.commit();
            System.out.println(response);
            // 添加Doc
//            UpdateResponse updateResponse = solrProductServer.deleteByQuery("*:*");
//            System.out.println(String.format("delete all: %s", ReflectionToStringBuilder.toString(updateResponse, ToStringStyle.MULTI_LINE_STYLE)));
//            updateResponse = solrProductServer.addBeans(solrProductBeans);
//            System.out.println(String.format("addBeans: %s", ReflectionToStringBuilder.toString(updateResponse, ToStringStyle.MULTI_LINE_STYLE)));
//            // 如果DB的事务commit, 必须commit才能确保Doc添加至索引文件中
//            updateResponse = solrProductServer.commit();
//            System.out.println(String.format("commit: %s", ReflectionToStringBuilder.toString(updateResponse, ToStringStyle.MULTI_LINE_STYLE)));

            // 搜索Doc
            SolrQuery solrQuery = new SolrQuery();
            // 简单查询
//            solrQuery.setQuery("*:*");
//            solrQuery.setQuery("code:code*");
//            solrQuery.addFilterQuery("name:001");
//            solrQuery.addFilterQuery("id:2");
//            solrQuery.addFilterQuery("id:[1 TO 2}");
            // in 查询
//            solrQuery.setQuery("name:code-name-001");


            //设置高亮
//            solrQuery.setHighlight(true); // 开启高亮组件
//            solrQuery.addHighlightField("code");// 高亮字段
//            solrQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
//            solrQuery.setHighlightSimplePost("</font>");//后缀
//            solrQuery.setHighlightSnippets(1);//结果分片数，默认为1
//            solrQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100


            //分片信息
            /*--------------P31_pStatus---------------
            OnSale: 26292
            WaitingPublish: 5707
            InStock: 3896
            --------------P31_pStatus---------------*/
            solrQuery.setQuery("*:*");                        // 查询条件
//            solrQuery.setFilterQueries("productChannel:001"); // 查询结果过滤条件
//            solrQuery.setFacet(true);                         // 开启FacetQuery
//            solrQuery.setFacetMissing(false);                 // 不统计null的值，FacetField为null(不存在)的记录不统计
//            solrQuery.setFacetMinCount(1);                    // 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
//            solrQuery.addFacetField(new String[]{"P5_pStatus", "P6_pStatus", "P7_pStatus", "P8_pStatus", "P9_pStatus", "P10_pStatus", "P11_pStatus", "P12_pStatus"});


//            solrQuery.setFacet(true).setFacetMissing(false)
//                    .setQuery("productChannel:928")
//                    .setFacetMinCount(1) // 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
//                    .setFacetLimit(2)
//                    .addFacetField("P31_pStatus");


            QueryResponse queryResponse = solrProductServer.query(solrQuery);
//            System.out.println(String.format("SolrQuery: %s", ReflectionToStringBuilder.toString(queryResponse, ToStringStyle.MULTI_LINE_STYLE)));
//            List<SolrProductBean> productBeans = queryResponse.getBeans(SolrProductBean.class);
//            productBeans.forEach(solrProductBean -> {
//                System.out.println(ReflectionToStringBuilder.toString(solrProductBean, ToStringStyle.MULTI_LINE_STYLE));
//            });

            //输出分片信息
//            List<FacetField> facets = queryResponse.getFacetFields();
//            for (FacetField facet : facets) {
//                System.out.println("--------------" + facet.getName() + "---------------");
//                List<FacetField.Count> facetCounts = facet.getValues();
//                for (FacetField.Count count : facetCounts) {
//                    System.out.println(count.getName() + ": " + count.getCount());
//                }
//                System.out.println("--------------" + facet.getName() + "---------------");
//
//                System.out.println();
//                System.out.println();
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static class Sku {
        @Field("id")
        private String id;
        @Field("sku")
        private String sku;
        @Field("active")
        private Boolean active;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
    }
}
