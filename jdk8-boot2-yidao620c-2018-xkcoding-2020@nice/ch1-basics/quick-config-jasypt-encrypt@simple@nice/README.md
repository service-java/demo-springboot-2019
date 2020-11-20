# 配置文件加解密
使用见测试用例

- https://juejin.cn/entry/6844904137423847438
- jasypt会使用这个自定义加密密钥，对配置文件里的重要项进行加密
- jasypt-spring-boot组件会自动将`ENC()`语法包裹的配置项加密字段自动解密，数据得以还原
- 为了更加安全可以命令行参数带入???

```java
java -jar yourproject.jar --jasypt.encryptor.password=CodeSheep
```

其他内容，参见https://github.com/ulisesbocchio/jasypt-spring-boot
