# spring-boot-demo-cache-redis

> 此 demo 主要演示了 Spring Boot 如何整合 redis，操作redis中的数据，并使用redis缓存数据。连接池使用  Lettuce。


## application.yml

```yaml
spring:
  redis:
    host: localhost
    # 连接超时时间（记得添加单位，Duration）
    timeout: 10000ms
    # Redis默认情况下有16个分片，这里配置具体使用的分片
    # database: 0
    lettuce:
      pool:
        # 连接池最大连接数（使用负值表示没有限制） 默认 8
        max-active: 8
        # 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1
        max-wait: -1ms
        # 连接池中的最大空闲连接 默认 8
        max-idle: 8
        # 连接池中的最小空闲连接 默认 0
        min-idle: 0
  cache:
    # 一般来说是不用配置的，Spring Cache 会根据依赖的包自行装配
    type: redis
logging:
  level:
    com.xkcoding: debug
```

## 参考

- spring-data-redis 官方文档：https://docs.spring.io/spring-data/redis/docs/2.0.1.RELEASE/reference/html/
- redis 文档：https://redis.io/documentation
- redis 中文文档：http://www.redis.cn/commands.html
