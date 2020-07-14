# README

- 有父级pom起来方便, 但是作为demo搬运起来不方便...所以在原仓库基础上都改了些...

- 在 SpringBootBucket 的基础上修改
    - https://github.com/yidao620c/SpringBootBucket

- springboot-demo是基础模板

```js
// 使用依赖
jetty
thymeleaf
junit
apache common
hutool + guava
mysql + mybatis-plus
lombok     
```

    
# 常见问题 @faq

- mysql连接失败
    - 版本降至 5.1.38

---

# 模块访问网址

- aop

```
http://localhost:8092/first
http://localhost:8092/second
http://localhost:8092/doError
```

- async

```jsx
运行测试 testAsync

// 输出
主线程执行finished
asyncInvokeReturnFuture, parementer=5
返回值为void的异步调用开始AsyncThread-1
返回值为void的异步调用结束AsyncThread-1
success:5
```

- cxf

- RabbitMQ

# 备注

```xml
<groupId>com.xkcoding</groupId>
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.4.RELEASE</version>
</parent>

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <spring.boot.version>2.1.0.RELEASE</spring.boot.version>
    <mysql.version>8.0.12</mysql.version>
    <hutool.version>4.6.6</hutool.version>
    <guava.version>28.1-jre</guava.version>
    <user.agent.version>1.20</user.agent.version>
</properties>
```

---

# 参考
