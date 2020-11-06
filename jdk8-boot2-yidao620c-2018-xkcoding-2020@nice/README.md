# README

- 项目代码改造来源
    - https://github.com/wuyouzhuguli/SpringAll
    - https://github.com/xkcoding/spring-boot-demo
    - https://github.com/vector4wang/spring-boot-quick
    - https://github.com/yidao620c/SpringBootBucket

- 工程里带@todo就是没搞好的, 不要看
- 有父级pom维护起来方便, 但为了demo的搬运运行方便直接去掉了

- 基础模板
    - springboot-demo --> 基础
    - springboot-modules-demo --> 多模块
    - springboot-dubbo-demo --> 远程通信
    
- 公共依赖

```js
// jetty
// thymeleaf
junit
apache common
hutool + guava
mysql + mybatis-plus
lombok     
```

# TODO

- 将相关url尽量改成example.com, 然后用switchhost映射
    
# 常见问题 @faq

- mysql连接失败
    - 版本降至 5.1.38

---

# 备注 @ignore

```xml
<version>1.0.0-SNAPSHOT</version>

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
