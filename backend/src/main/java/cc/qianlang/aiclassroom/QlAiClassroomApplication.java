package cc.qianlang.aiclassroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * OpenMAIC Backend Application
 * <p>
 * AI-driven Interactive Classroom Platform
 *
 * @author ql-team
 * @version 0.1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"cc.qianlang.foundation.service.biz", "cc.qianlang.aiclassroom"}) // 如果是 JPA
@EntityScan(basePackages = {"cc.qianlang.foundation.service.biz", "cc.qianlang.aiclassroom"})
@ComponentScan(basePackages = {"cc.qianlang.web.common", "cc.qianlang.foundation.service.biz", "cc.qianlang.aiclassroom"})
@EnableFeignClients(basePackages = "cc.qianlang.aiclassroom")
public class QlAiClassroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(QlAiClassroomApplication.class, args);
	}
}
