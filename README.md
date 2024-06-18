## 第三方接口：微信平台
本项目为微信平台接口定义了openapi文档，使用该文档可生成客户端代码

### 对spring的特殊处理
1. 微信平台返回的Content-Type问题
为spring生成的客户端为本地依赖com.github.rainmanhhh:weixin-api（见./weixin/spring目录），
但若直接使用其中的feign client会报错，原因是：
微信平台返回的部分接口，Content-Type为text/plain，但实际报文体格式为json

2. 解决办法
这里自定义了类继承AbstractJackson2HttpMessageConverter并覆写supports函数，
让spring可以正常反序列化这些接口的返回报文

例子：
```kotlin
class GetMpSessionKeyResHttpMessageConverter(
  objectMapper: ObjectMapper
): AbstractJackson2HttpMessageConverter(
  objectMapper, MediaType.asMediaType(MimeType("text", "plain"))
) {
  override fun supports(clazz: Class<*>): Boolean = clazz == GetMpSessionKeyRes::class.java
}
```
注意继承时给父类传的两个参数不可缺少

3. 正确的依赖引用方式
实际使用时，应依赖com.github.rainmanhhh:weixin-api-spring-boot-starter（即定义在本项目pom.xml中的artifact），
而不是直接依赖根据openapi文档生成出来的com.github.rainmanhhh:weixin-api