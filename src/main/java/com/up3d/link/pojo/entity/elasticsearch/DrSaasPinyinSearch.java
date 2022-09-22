package com.up3d.link.pojo.entity.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-21  14:11
 * @Description: es拼音搜索索引对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "student")
public class DrSaasPinyinSearch {
    @Id
    @Field(store = true,type = FieldType.Keyword)
    private String id;

    @Field(store = true,type = FieldType.Text,analyzer = "ik_max_word")
    private String keyword;

    @Field(store = true,type = FieldType.Integer)
    private Long searchId;
}

