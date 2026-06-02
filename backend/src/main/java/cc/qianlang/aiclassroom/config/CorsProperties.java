package cc.qianlang.aiclassroom.config;

import lombok.Data;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NullMarked
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

	@Nullable
	private List<String> allowedOrigins;

	public List<String> getAllowedOrigins() {
		if (this.allowedOrigins == null) this.allowedOrigins = new ArrayList<>();
		return this.allowedOrigins;
	}

}
