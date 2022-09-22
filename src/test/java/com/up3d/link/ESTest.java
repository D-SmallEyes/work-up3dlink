package com.up3d.link;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-21  16:28
 * @Description: elasticsearch使用测试
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Test
    public void insertData(){

    }

    @Test
    public void createIndex(){
        boolean b = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("dr_saas_pinyin_search")).create();
        System.out.println(b);
    }

    @Test
    public void indexIsExist(){
        boolean b = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("dr_saas_pinyin_search")).exists();
        System.out.println(b);
    }

}
