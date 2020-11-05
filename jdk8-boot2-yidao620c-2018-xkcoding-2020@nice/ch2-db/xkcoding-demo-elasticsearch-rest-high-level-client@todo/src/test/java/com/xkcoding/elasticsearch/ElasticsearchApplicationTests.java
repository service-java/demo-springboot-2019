package com.xkcoding.elasticsearch;

import com.xkcoding.elasticsearch.contants.ElasticsearchConstant;
import com.xkcoding.elasticsearch.model.FreeCitizen;
import com.xkcoding.elasticsearch.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private PersonService personService;

    /**
     * 测试删除索引
     */
    @Test
    public void deleteIndexTest() {
        personService.deleteIndex(ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 测试创建索引
     */
    @Test
    public void createIndexTest() {
        personService.createIndex(ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 测试新增
     */
    @Test
    public void insertTest() {
        List<FreeCitizen> list = new ArrayList<>();
        list.add(FreeCitizen.builder().age(11).birthday(new Date()).country("CN").id(1L).name("哈哈").remark("test1").build());
        list.add(FreeCitizen.builder().age(22).birthday(new Date()).country("US").id(2L).name("hiahia").remark("test2").build());
        list.add(FreeCitizen.builder().age(33).birthday(new Date()).country("ID").id(3L).name("呵呵").remark("test3").build());

        personService.insert(ElasticsearchConstant.INDEX_NAME, list);
    }

    /**
     * 测试更新
     */
    @Test
    public void updateTest() {
        FreeCitizen person = FreeCitizen.builder().age(33).birthday(new Date()).country("ID_update").id(3L).name("呵呵update").remark("test3_update").build();
        List<FreeCitizen> list = new ArrayList<>();
        list.add(person);
        personService.update(ElasticsearchConstant.INDEX_NAME, list);
    }

    /**
     * 测试删除
     */
    @Test
    public void deleteTest() {
        personService.delete(ElasticsearchConstant.INDEX_NAME, FreeCitizen.builder().id(1L).build());
    }

    /**
     * 测试查询
     */
    @Test
    public void searchListTest() {
        List<FreeCitizen> personList = personService.searchList(ElasticsearchConstant.INDEX_NAME);
        System.out.println(personList);
    }

}
