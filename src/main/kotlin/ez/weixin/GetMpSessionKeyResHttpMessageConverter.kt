package ez.weixin

import com.fasterxml.jackson.databind.ObjectMapper
import ez.weixin.models.GetMpSessionKeyRes
import org.springframework.http.MediaType
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.util.MimeType

class GetMpSessionKeyResHttpMessageConverter(
  objectMapper: ObjectMapper
): AbstractJackson2HttpMessageConverter(
  objectMapper, MediaType.asMediaType(MimeType("text", "plain"))
) {
  override fun supports(clazz: Class<*>): Boolean = clazz == GetMpSessionKeyRes::class.java
}