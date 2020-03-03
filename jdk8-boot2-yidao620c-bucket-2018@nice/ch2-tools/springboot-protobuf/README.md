# 起步例子

- Protobuf 结合 Java 快速入门
    - https://www.jianshu.com/p/bb3ac7e5834e

```
protoc -I=src/main/resources/proto --java_out=src/main/java gps_data.proto
```

```
===== 构建一个GPS模型开始 =====
id: 100
dataTime: "2017-12-17 16:21:44"
lon: 120.112
lat: 39.123
altitude: 1
gpsStatus: 1
direction: 30.2

===== 构建GPS模型结束 =====
===== gps Byte 开始=====
810026195048495545495045495532495458504958525233-707312243794644157-76-56118-66-113676456172185-102-103-1565
bytes长度50
===== gps Byte 结束 =====
===== 使用gps 反序列化生成对象开始 =====
id: 100
dataTime: "2017-12-17 16:21:44"
lon: 120.112
lat: 39.123
altitude: 1
gpsStatus: 1
direction: 30.2
===== 使用gps 反序列化生成对象结束 =====
```
