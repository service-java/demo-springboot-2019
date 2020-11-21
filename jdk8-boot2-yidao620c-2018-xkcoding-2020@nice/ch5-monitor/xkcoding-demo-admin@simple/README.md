# spring-boot-demo-admin

> 本 demo 主要演示了 Spring Boot 如何集成 Admin 管控台，监控管理 Spring Boot 应用，分别为 admin 服务端和 admin 客户端，两个模块。

- spring-boot-admin-server和spring-boot-admin-client服务启动失败
    - https://github.com/xkcoding/spring-boot-demo/pull/28

## 运行步骤

1. 进入 `spring-boot-demo-admin-server` 服务端，启动管控台服务端程序
2. 进入 `spring-boot-demo-admin-client` 客户端，启动客户端程序，注册到服务端
3. 观察服务端里，客户端程序的运行状态等信息
    - http://localhost:8000/#/applications
