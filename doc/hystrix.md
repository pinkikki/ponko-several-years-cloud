# hystrix memo

## @HystrixCommand

`@HystrixCommand`の`fallbackMethod`で指定するメソッドの戻り値は、`@HystrixCommand`を付与したメソッドの戻り値の型と同一でなければならない。

こうゆうやつ
```java
public String error() {
    return "error";
}

@HystrixCommand(fallbackMethod = "error")
@GetMapping(path = "monoohagiandmozuku")
public Mono<String> withMonoOfOhagiAndMozuku() {
    return Mono.just("error");
}
```