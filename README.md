# ponko-several-years-cloud

1. eurekaサーバを起動（クラスタ構成）

```bash
mvn spring-boot:run -Dspring.profiles.active=server1
```

```bash
mvn spring-boot:run -Dspring.profiles.active=server2
```

2. 各サービスを起動

```bash
mvn spring-boot:run
```

3. eurekaサーバに登録されているか確認

`http://localhost:[9090/9091]`