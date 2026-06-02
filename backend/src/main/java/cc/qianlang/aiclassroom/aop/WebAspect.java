package cc.qianlang.aiclassroom.aop;

import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import cc.qianlang.web.common.core.web.request.RequestUtils;
import cc.qianlang.web.common.core.web.response.Response;
import cc.qianlang.web.common.time.TimeParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 本切面用于监控 API 接口的执行.
 * <p>
 * 当 API 接口执行失败时, 会抛出异.
 * 本切面会将所抛出的异常封装为错误响应, 作为接口的返回值.
 * <p>
 * 当 API 方法执行结束, 将从会话上下文中清理本次会话的信息.
 *
 * @author sunjian20
 * @version 1.0
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class WebAspect {

	@Around("execution(* cc.qianlang.aiclassroom..*Ctrl.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {

		// 1. 记录请求时间
		LocalDateTime calledAt = LocalDateTime.now();

		// 2.1. 获取参数和返回值
		String methodName = null;
		Class<?>[] paramClasses = new Class<?>[]{};
		String[] paramNames = new String[]{};
		Object[] paramValues = pjp.getArgs();
		Class<?> returnType = null;
		switch (pjp.getSignature()) {
			case MethodSignature ms -> {
				methodName = ms.getName();
				paramClasses = ms.getParameterTypes();
				paramNames = ms.getParameterNames();
				returnType = ms.getReturnType();
			}
			case null, default -> {
			}
		}

		// 2.2. 获取账户 IP
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String ip = attributes != null ? RequestUtils.clientIp(attributes.getRequest()) : null;

//		// 2.3. 设置 MDC 参数
//		MDC.put(MDC_KEY_IP, ip);

		// 3. 执行接口, 以其返回值作为响应体
		Object response = pjp.proceed();

		// 4. 记录执行结束时间
		LocalDateTime endedAt = LocalDateTime.now();

//		// 5. 清理会话上下文
//		MDC.clear();

		// 6.1. 组合日志输出内容
		Map<String, String> pv = new LinkedHashMap<>();
		pv.put("method", methodName);
		for (int i = 0; i < paramClasses.length; i++) {
			Class<?> paramClass = paramClasses[i];
			Object paramValue = paramValues[i];
			String paramName = paramNames[i];
			if (paramValue instanceof MultipartFile file) pv.put(paramName, file.getOriginalFilename());
			else pv.put(paramName, ObjectMapperFactory.jsonify(paramValue));
		}

		if (returnType == Response.class) {
			Response<?> resp = (Response<?>) response;
			pv.put("响应", ObjectMapperFactory.jsonify(resp));
			pv.put("响应状态", resp.getStatus() + "");
		}

		pv.put("IP", ip);
		pv.put("执行开始时间", TimeParser.format(calledAt));
		pv.put("执行时长", Duration.between(calledAt, endedAt).toMillis() + "");

		// 6.2. 输出日志
		String info = pv.entrySet().stream()
				.map(e -> e.getKey() + ": " + e.getValue())
				.collect(Collectors.joining(", "));
		log.info("{}", info);

		return response;

	}

}
