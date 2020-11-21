package com.xkcoding.elasticsearch.service;

import com.xkcoding.elasticsearch.model.FreeCitizen;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * PersonService
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/9/15 23:07
 */
public interface PersonService {

    /**
     * create Index
     *
     * @author fxbin
     * @param index elasticsearch index name
     */
    void createIndex(String index);

    /**
     * delete Index
     *
     * @author fxbin
     * @param index elasticsearch index name
     */
    void deleteIndex(String index);

    /**
     * insert document source
     *
     * @author fxbin
     * @param index elasticsearch index name
     * @param list data source
     */
    void insert(String index, List<FreeCitizen> list);

    /**
     * update document source
     *
     * @author fxbin
     * @param index elasticsearch index name
     * @param list data source
     */
    void update(String index, List<FreeCitizen> list);

    /**
     * delete document source
     *
     * @author fxbin
     * @param person delete data source and allow null object
     */
    void delete(String index, @Nullable FreeCitizen person);

    /**
     * search all doc records
     *
     * @author fxbin
     * @param index elasticsearch index name
     * @return person list
     */
    List<FreeCitizen> searchList(String index);

}
