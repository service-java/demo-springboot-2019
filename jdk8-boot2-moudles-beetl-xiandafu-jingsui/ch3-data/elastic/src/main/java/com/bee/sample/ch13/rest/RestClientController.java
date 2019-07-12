package com.bee.sample.ch13.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bee.sample.ch13.entity.Book;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestClientController {

    @RequestMapping("/restclient/book/{id}")
    public String getLogById(@PathVariable String id) throws Exception {
        Book book = null;
        RestTemplate template = new RestTemplate();
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("id", id);
        String str = template.getForObject("http://127.0.0.1:9200/product/book/{id}", String.class, paras);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(str);
        JsonNode root = mapper.readTree(parser);
        JsonNode sourceNode = root.get("_source");
        book = mapper.convertValue(sourceNode, Book.class);
        return book.getMessage();

    }

//	@RequestMapping("/restclient/search/{key}")
//	public String search(@PathVariable String key) throws Exception {
//		Book book = null;
//		RestTemplate template = new RestTemplate();
//		Map<String,Object> paras = new HashMap<>();
//		paras.put("key", key);
//		String str = template.getForObject("http://172.16.86.56:9200/product/book/_search?q=message:{key}", String.class,paras);
//		ObjectMapper mapper = new ObjectMapper();
//		JsonFactory factory = mapper.getFactory();
//		JsonParser parser = factory.createParser(str);
//		JsonNode root = mapper.readTree(parser);
//		JsonNode hitsNode = root.get("hits");
//		int result = hitsNode.get("total").asInt();
//		JsonNode  array = hitsNode.get("hits");
//		
//		for(JsonNode node:  array.){
//			
//		}
//		book  = mapper.convertValue(sourceNode, Book.class);
//		return book.getMessage();
//
//	}
}
