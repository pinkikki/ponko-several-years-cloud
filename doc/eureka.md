## ereka memo

## eureka server

単純に、server.portを変更するだけでは例外が発生する。
以下のような記載にする
```yaml
server:
  port: 9091

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false # eurekaサーバがシングル構成の場合は、falseにしておく
    fetchRegistry: false # eurekaサーバがシングル構成の場合は、falseにしておく
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

## eureka client

```yaml
server:
  port: 9094
spring:
  application:
    name: man-app
eureka:
  client:
    service-url:
      defaultZone: http://localhost:9090/eureka,http://localhost:9091/eureka
  instance:
    leaseRenewalIntervalInSeconds: 1 # 初回登録するまでの時間
    leaseExpirationDurationInSeconds: 2 # インスタンスの生存チェック時間
```