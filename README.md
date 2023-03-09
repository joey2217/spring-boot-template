# spring-boot-template

> http://localhost:8080/swagger-ui/index.html#/

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

> https://connect2id.com/products/nimbus-jose-jwt

https://mp.weixin.qq.com/s/Jo3PZoa7nL99c8UCxPiTTA

## JPA

https://knife.blog.csdn.net/article/details/120687270

## Cache

http://blog.yuqiyu.com/redis-springboot2-starter.html

https://spring.io/guides/gs/caching/

### Cache 注解

> 保存对象需要 implements Serializable

https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations

https://zhuanlan.zhihu.com/p/77615122

```xml
<!--redis 不需要-->
<!-- <dependency>-->
<!--    <groupId>org.springframework.boot</groupId>-->
<!--    <artifactId>spring-boot-starter-cache</artifactId>-->
<!--</dependency>-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

```java
@EnableCaching
@Cacheable(cacheNames = "user",key = "#id")
```

## 参数校验

https://blog.csdn.net/qq_43437874/article/details/116988733

## Docker

https://spring.io/guides/topicals/spring-boot-docker/

https://zhuanlan.zhihu.com/p/342179720

### spring-boot-maven-plugin build-image

```shell
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=myorg/myapp
```

### Dockerfile

```shell
mkdir target/dependency
(cd target/dependency; jar -xf ../*.jar)
docker build -t myorg/myapp .
```

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","template.Application"]
```

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
