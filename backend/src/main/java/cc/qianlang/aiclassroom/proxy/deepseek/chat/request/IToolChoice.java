package cc.qianlang.aiclassroom.proxy.deepseek.chat.request;

/**
 * DeepSeek API {@code tool_choice} 字段的类型接口。
 * <p>
 * 该字段在 API 中可以是字符串或对象两种形式，通过两个实现类分别建模：
 * <ul>
 *   <li>{@link ToolChoice} — 字符串形式，如 {@code "none"}、{@code "auto"}、{@code "required"}</li>
 *   <li>{@link NamedToolChoice} — 对象形式，强制模型调用指定工具函数</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public sealed interface IToolChoice permits ToolChoice, NamedToolChoice {
}
