package cc.qianlang.aiclassroom.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 跨域配置类
 * 解决跨域访问问题
 *
 * @author ACat
 */
@Slf4j
@Configuration
public class CorsConfig {

	@Resource
	private CorsProperties properties;

	private CorsConfiguration corsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 是否允许请求带有验证信息
		corsConfiguration.setAllowCredentials(true);
		// 设置允许跨域请求的域名
		// 当 allow-credentials 为 true 时, allowed-origin-pattern 不可使用 *
		for (String pattern : properties.getAllowedOrigins()) {
			corsConfiguration.addAllowedOriginPattern(pattern);
			log.info("允许域名: {}", pattern);
		}
		// 设置允许的头属性
		corsConfiguration.addAllowedHeader("*");
		// 设置允许的请求方式
		corsConfiguration.addAllowedMethod("*");
		// 跨域允许时间
		corsConfiguration.setMaxAge(3600L);
		return corsConfiguration;
	}

	//配置 CorsFilter
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", this.corsConfig());
		return source;
	}

}