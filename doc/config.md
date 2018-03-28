# config memo

## client

`bootstrap.yml`に以下Config Serverを設定する

```yaml
spring:
  application:
    name: sushi-app
  cloud:
    config:
      uri: http://localhost:9099
      label: master
```

`application.yml`に設定しても意味がない

なお、取得するファイル名は以下以下の規則に従う。

http://[uri]/[アプリケーション名]/[profile名]

### configの値を変更した場合

http://[host]/actuator/refreshにPostすると`@RefreshScope`が付与されたクラスのインスタンスが再生成される