package cc.qianlang.aiclassroom.common;

import cc.qianlang.web.common.core.exception.IErr;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Getter
public enum Err implements IErr {

	UNKNOWN(500, "未知错误"),

	账户_不存在(1001, "账户不存在"),

	会话_不存在(2001, "会话不存在"),
	会话_失败(2002, "会话生成失败."),
	会话_谱图匹配记录_不存在(2003, "谱图匹配记录不存在."),

	文件_类型_不支持(3001, "文件类型不支持."),
	文件_不存在(3002, "文件不存在."),
	文件_无效(3003, "无效的文件."),
	文件_解析_失败(3004, "文件解析失败."),
	数据_异常(3005, "数据异常."),

	知识库_文档_不存在(4001, "文档不存在."),
	知识库_文档_已存在(4002, "文档已存在"),

	谱图_解析_失败(5001, "谱图解析失败"),
	谱图_参数_无效(5002, "参数无效."),

	模型_不存在(6001, "模型不存在."),
	模型_模型名_重复(6002, "模型名重复."),
	模型_API_KEY_不存在(6003, "模型 API KEY 不存在.");

	private final int code;
	private final String message;

	Err(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
