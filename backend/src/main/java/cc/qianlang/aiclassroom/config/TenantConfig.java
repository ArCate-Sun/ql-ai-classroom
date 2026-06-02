package cc.qianlang.aiclassroom.config;

import cc.qianlang.foundation.service.biz.account.entity.Tenant;
import cc.qianlang.foundation.service.biz.account.srv.TenantSrv;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantConfig {

	@Value("${tenant-secret-key}")
	private String TENANT_SECRET_KEY;

	@Resource
	private TenantSrv tenantSrv;

	@Bean
	public Tenant getTenant() {
		return this.tenantSrv.getActiveBySecretKeyOrThrow(this.TENANT_SECRET_KEY);
	}
}
