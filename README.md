# spring-boot-template

# Getting Started

## aliyun docker

```sh
sudo docker login --username=786678257@qq.com registry.cn-hangzhou.aliyuncs.com
```

## Security

https://www.bezkoder.com/spring-boot-security-jwt/

https://juejin.cn/post/7037014454067789860

https://github.com/joey2217/Spring-Boot-Security-JWT-SPA

https://segmentfault.com/a/1190000041947192

https://blog.csdn.net/qq_44709990/article/details/123082560

https://juejin.cn/post/6846687598442708999

### JWT

https://github.com/jwtk/jjwt#jws-create-key

## JPA

https://knife.blog.csdn.net/article/details/120687270

## Cache

http://blog.yuqiyu.com/redis-springboot2-starter.html

https://spring.io/guides/gs/caching/

### Cache 注解

https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations

https://zhuanlan.zhihu.com/p/77615122

## 参数校验

https://blog.csdn.net/qq_43437874/article/details/116988733

## Docker

https://spring.io/guides/topicals/spring-boot-docker/

## @Value读取配置

- ${ property : default_value }
- #{ obj.property? :default_value }
- https://www.cnblogs.com/liuqijia/p/11428789.html

```java
public class jwt{
    @Value("${jwt.expirationInMs:43200000}")
    private int expirationInMs;
}
```

## openapi

https://springdoc.org/

https://github.com/springdoc/springdoc-openapi-demos

## redis

```shell
sudo service redis-server start
```
