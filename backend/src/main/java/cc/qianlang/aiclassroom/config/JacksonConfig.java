package cc.qianlang.aiclassroom.config;

import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Configuration
public class JacksonConfig {

	@Bean
	@Primary // 标记为全局唯一首选
	public ObjectMapper objectMapper() {
		return ObjectMapperFactory.normal();
	}
}
