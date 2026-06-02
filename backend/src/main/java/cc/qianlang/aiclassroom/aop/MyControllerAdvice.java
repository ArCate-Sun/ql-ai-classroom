package cc.qianlang.aiclassroom.aop;

import cc.qianlang.web.common.core.exception.CoreErr;
import cc.qianlang.web.common.core.web.response.Response;
import cc.qianlang.web.common.core.web.response.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyControllerAdvice {
	@ExceptionHandler(AuthorizationDeniedException.class)
	public Response<?> ofException(AuthorizationDeniedException e) {
		return ResponseUtils.err(CoreErr.SERVICE_DENY);
	}
}
