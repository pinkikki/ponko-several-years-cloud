# actuator memo

デフォルトでwebで閲覧可能なのは、だいぶ減っている。
webで閲覧できないものは、JMXで取得可能

＜参考文献＞
https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/htmlsingle/#production-ready-endpoints-exposing-endpoints

なお、各Endpointを有効化するのは、以下のような設定を追加する
```yml
management:
  endpoints:
    web:
      exposure:
        include: refresh,env
```
