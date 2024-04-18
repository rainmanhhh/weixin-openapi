## 第三方接口：微信平台
### 对spring的特殊说明
如果调用方为spring，则应依赖主目录下的weixin-api-spring-boot-starter，
而不是直接依赖weixin-openapi生成出来的weixin-api
原因是：
微信平台返回的部分接口是text/plain（但实际报文体为json），
需要自定义类继承AbstractJackson2HttpMessageConverter并覆写supports函数，
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
