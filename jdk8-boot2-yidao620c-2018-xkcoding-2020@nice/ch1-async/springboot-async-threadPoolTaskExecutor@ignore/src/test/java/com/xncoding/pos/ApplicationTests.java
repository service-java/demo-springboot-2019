package com.xncoding.pos;

import com.xncoding.pos.task.AsyncDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Console;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 测试异步任务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    private AsyncDemo asyncDemo;

    @Test
    public void contextLoads() throws InterruptedException, ExecutionException {
        asyncDemo.asyncInvokeSimplest(); // asyncSimplest
        asyncDemo.asyncInvokeWithParameter("test"); // asyncInvokeWithParameter, parementer=test
        Future<String> future = asyncDemo.asyncInvokeReturnFuture(100); // asyncInvokeWithParameter, parementer=test
        System.out.println(future.get()); // success:100
        System.out.println("==============");

    }
}
