# ql-ai-classroom Backend

> OpenMAIC (Open Multi-Agent Interactive Classroom) 后端服务 - 基于 Spring Boot 的企业级 AI 课程平台

## 项目简介

本项目是 ql-ai-classroom (OpenMAIC) 的后端服务重构版本，将原有基于 Next.js API Routes 的后端迁移至标准的 Spring Boot + Spring JPA + MariaDB 架构，以支持更好的性能、可扩展性和企业级功能。

### 核心功能

- **AI 驱动课程生成**: 基于主题或文档自动生成完整课程内容
- **多代理协作**: 支持多个 AI 代理协同教学和讨论
- **流式聊天**: 基于 SSE 的实时流式对话
- **场景渲染**: 支持幻灯片、测验、交互式场景、PBL 等多种场景类型
- **媒体生成**: 集成图片、视频、TTS 语音合成
- **异步任务**: 支持长时间运行的生成任务异步处理

## 技术栈

### 核心框架
- **Java**: 21
- **Spring Boot**: 4.0.6
- **Spring Data JPA**: 数据持久化
- **Spring Security**: 认证授权
- **Spring WebFlux**: SSE 流式响应

### 数据存储
- **MariaDB**: 11.x (主数据库)
- **Flyway**: 数据库版本管理
- **Caffeine**: 本地缓存

### AI 集成
- **LangChain4j**: AI 服务抽象层
- **OpenAI API**: GPT 系列模型
- **Anthropic API**: Claude 系列模型
- **Google Gemini API**: Gemini 系列模型

### 工具库
- **Lombok**: 简化代码
- **SpringDoc OpenAPI**: API 文档
- **Jackson**: JSON 处理

### 依赖库
- **ql-web-common**: QL 团队通用组件库

## 快速开始

### 环境要求

- JDK 21+
- Maven 3.8+
- MariaDB 11.x

### 本地开发

#### 1. 克隆项目

```bash
git clone <repository-url>
cd backend
```

#### 2. 配置数据库

创建数据库：

```sql
CREATE DATABASE ai_classroom CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 3. 配置应用

编辑 `application-local.yml` 配置敏感信息：

```yaml
spring:
  # 数据库配置
  datasource:
    url: jdbc:mariadb://localhost:3306/ai_classroom?useUnicode=true&characterEncoding=utf8mb4
    username: root
    password: "your_database_password"  # 修改为你的数据库密码

# 访问码保护（可选）
access:
  code: ""  # 留空表示系统完全开放；设置密码则需要访问码

# AI 服务配置
ai:
  default-provider: "openai"  # 可选：openai, anthropic, google
  default-model: "gpt-4"
  
  providers:
    openai:
      api-key: "sk-your-openai-api-key"  # 填入你的 OpenAI API Key
```

**配置说明**：

- `application.yml`：基础配置，包含所有配置项和详细注释，不需要修改
- `application-local.yml`：本地配置，覆盖敏感信息（数据库密码、API Key），不会提交到 Git
- `application-remote.yml`：开发(远程)环境配置，已预配置开发模式
- `application-prod.yml`：生产环境配置，用于部署

**激活本地配置**：

在 `application.yml` 中修改：

```yaml
spring:
  profiles:
    active: local  # 改为 local
```

或启动时指定：

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

#### 4. 运行应用

```bash
# 使用 Maven
mvn spring-boot:run

# 或直接运行
mvn clean install
java -jar target/ai-classroom-backend-0.1.0.jar
```

应用默认运行在 `http://localhost:8080`

#### 5. 访问 API 文档

打开浏览器访问：`http://localhost:8080/swagger-ui.html`

### 生产环境部署

#### 1. 构建可执行 JAR

```bash
mvn clean package -DskipTests
```

生成的 JAR 文件位于：`target/ai-classroom-backend-0.1.0.jar`

#### 2. 运行应用

```bash
# 使用环境变量运行
java -jar target/ai-classroom-backend-0.1.0.jar \
  --spring.profiles.active=prod \
  --spring.datasource.url=jdbc:mariadb://your-db-host:3306/ai_classroom \
  --spring.datasource.username=your_username \
  --spring.datasource.password=your_password
```

#### 3. 配置为系统服务（可选）

创建 systemd 服务文件 `/etc/systemd/system/ai-classroom.service`：

```ini
[Unit]
Description=AI Classroom Backend Service
After=network.target mariadb.service

[Service]
Type=simple
User=aiclassroom
WorkingDirectory=/opt/ai-classroom
ExecStart=/usr/bin/java -jar /opt/ai-classroom/ai-classroom-backend-0.1.0.jar
Environment="SPRING_PROFILES_ACTIVE=prod"
Environment="SPRING_DATASOURCE_URL=jdbc:mariadb://localhost:3306/ai_classroom"
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

启动服务：

```bash
sudo systemctl daemon-reload
sudo systemctl enable ai-classroom
sudo systemctl start ai-classroom
sudo systemctl status ai-classroom
```

## 项目结构

```
backend/
├── src/main/java/cc/qianlang/aiclassroom/
│   ├── QlAiClassroomApplication.java    # 应用入口
│   │
│   ├── config/                          # 配置类
│   │   ├── SecurityConfig.java          # Spring Security 配置
│   │   ├── JpaConfig.java               # JPA 配置
│   │   ├── WebConfig.java               # Web MVC 配置
│   │   └── ...
│   │
│   ├── common/                          # 公共组件
│   │   ├── exception/                   # 异常定义
│   │   ├── constant/                    # 常量定义
│   │   └── util/                        # 工具类
│   │
│   ├── account/                         # 用户账号模块
│   │   ├── entity/Account.java
│   │   ├── repo/AccountRepo.java
│   │   ├── srv/AccountSrv.java
│   │   └── web/AccountCtrl.java
│   │
│   ├── classroom/                       # 课程模块
│   │   ├── entity/
│   │   │   ├── Classroom.java           # 课程实体
│   │   │   ├── Stage.java               # 舞台实体
│   │   │   └── Scene.java               # 场景实体
│   │   ├── repo/
│   │   ├── srv/
│   │   └── web/
│   │
│   ├── chat/                            # 聊天模块
│   │   ├── entity/
│   │   │   ├── ChatSession.java
│   │   │   └── ChatMessage.java
│   │   ├── srv/
│   │   │   ├── ChatSrv.java
│   │   │   └── StreamSrv.java           # SSE 流式服务
│   │   └── web/ChatCtrl.java
│   │
│   ├── generation/                      # 生成模块
│   │   ├── entity/GenerationJob.java
│   │   ├── srv/
│   │   │   ├── OutlineGenerationSrv.java
│   │   │   ├── SceneGenerationSrv.java
│   │   │   └── AsyncJobSrv.java
│   │   └── web/
│   │
│   ├── quiz/                            # 测验模块
│   ├── pbl/                             # 项目式学习模块
│   ├── storage/                         # 文件存储模块
│   ├── ai/                              # AI 服务集成
│   └── ...
│
├── src/main/resources/
│   ├── application.yml                  # 主配置文件
│   ├── application-dev.yml              # 开发环境配置
│   ├── application-prod.yml             # 生产环境配置
│   └── db/migration/                    # Flyway 迁移脚本
│       ├── V1__init_schema.sql
│       ├── V2__add_classroom_tables.sql
│       └── ...
│
├── src/test/                            # 测试代码
├── docker-compose.yml                   # Docker Compose 配置
├── Dockerfile                           # Docker 镜像构建
├── pom.xml                              # Maven 配置
├── README.md                            # 本文件
└── REFACTORING_GUIDE.md                 # 重构工作指导
```

## 开发指南

### 分层架构

本项目采用经典的 DDD 分层架构：

```
┌─────────────────┐
│   Web Layer     │  @RestController - 接口控制器
│   (web/)        │  处理 HTTP 请求和响应
└────────┬────────┘
         │
┌────────▼────────┐
│ Service Layer   │  @Service - 业务逻辑层
│   (srv/)        │  处理业务规则和流程
└────────┬────────┘
         │
┌────────▼────────┐
│  Repo Layer     │  @Repository - 数据访问层
│   (repo/)       │  JpaRepository 数据库操作
└────────┬────────┘
         │
┌────────▼────────┐
│  Entity Layer   │  @Entity - 实体类
│  (entity/)      │  对应数据库表
└─────────────────┘
```

### 命名规范

- **实体类**: `Classroom.java` (对应表 `cr_classroom`)
- **仓库类**: `ClassroomRepo.java` (extends JpaRepository)
- **服务类**: `ClassroomSrv.java` (业务逻辑)
- **控制器**: `ClassroomCtrl.java` (HTTP 接口)
- **请求对象**: `ClassroomCreateReq.java` (放在 `web/req/`)
- **响应对象**: `ClassroomVo.java` (放在 `web/vo/`)

### 开发流程

遵循 **模块开发四步设计流程**：

1. **需求分析**: 明确功能需求和业务规则
2. **API 设计**: 设计接口路径、参数、响应格式
3. **数据模型设计**: 设计实体表结构和字段
4. **实施方案确认**: 评审设计方案后开始编码

### 代码规范

```java
// 1. 实体类示例
@Entity
@Table(name = "cr_classroom")
@Getter
@Setter
public class Classroom extends SoftDeleteBaseEntity {
    @Column(length = 64, unique = true, nullable = false)
    private String classroomId;
    
    @Column(length = 255)
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Account owner;
}

// 2. 仓库类示例
public interface ClassroomRepo extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByClassroomId(String classroomId);
    Page<Classroom> findByOwner(Account owner, Pageable pageable);
}

// 3. 服务类示例
@Service
@Transactional
public class ClassroomSrv {
    @Resource
    private ClassroomRepo classroomRepo;
    
    public Classroom create(Account owner, String title) {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(UUID.randomUUID().toString());
        classroom.setTitle(title);
        classroom.setOwner(owner);
        return classroomRepo.save(classroom);
    }
}

// 4. 控制器示例
@RestController
@RequestMapping("/api/classroom")
public class ClassroomCtrl {
    @Resource
    private ClassroomSrv classroomSrv;
    
    @PostMapping
    public Response<ID> create(
        @RequestBody @Validated ClassroomCreateReq req,
        @AuthenticationPrincipal AccountPrincipal principal
    ) {
        Classroom classroom = classroomSrv.create(
            principal.getAccount(), 
            req.getTitle()
        );
        return ResponseUtils.succ(new ID(classroom.getId()));
    }
}
```

### 数据库迁移

使用 Flyway 管理数据库版本：

```sql
-- V1__init_schema.sql
CREATE TABLE cr_classroom (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    classroom_id VARCHAR(64) UNIQUE NOT NULL,
    title VARCHAR(255),
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

迁移脚本命名规范：`V{version}__{description}.sql`

### 测试

```bash
# 运行所有测试
mvn test

# 运行单个测试类
mvn test -Dtest=ClassroomSrvTest

# 查看测试覆盖率
mvn clean test jacoco:report
```

## API 文档

### Swagger UI

启动应用后访问：`http://localhost:8080/swagger-ui.html`

### 核心接口

#### 课程管理

```http
# 创建课程
POST /api/classroom
Content-Type: application/json
Authorization: Bearer {token}

{
  "title": "量子力学入门",
  "topic": "quantum mechanics",
  "durationMinutes": 30
}

# 获取课程详情
GET /api/classroom?id={classroomId}
Authorization: Bearer {token}

# 获取课程列表
GET /api/classroom/list?page=0&size=20
Authorization: Bearer {token}
```

#### 聊天接口

```http
# SSE 流式聊天
POST /api/chat
Content-Type: application/json
Authorization: Bearer {token}

{
  "messages": [...],
  "storeState": {...},
  "config": {
    "agentIds": ["teacher", "student1"]
  }
}

# 响应格式（SSE）
data: {"type":"text","content":"你好"}
data: {"type":"text","content":"，"}
data: {"type":"done"}
```

#### 生成任务

```http
# 提交生成任务
POST /api/generate-classroom/submit
Content-Type: application/json
Authorization: Bearer {token}

{
  "topic": "机器学习基础",
  "durationMinutes": 45
}

# 响应
{
  "code": 0,
  "message": "success",
  "data": {
    "jobId": "job_123456"
  }
}

# 查询任务状态
GET /api/generate-classroom/status/{jobId}
Authorization: Bearer {token}
```

## 配置说明

### 环境变量

| 变量名 | 说明 | 示例 |
|-------|------|------|
| `SPRING_DATASOURCE_URL` | 数据库连接 URL | `jdbc:mariadb://localhost:3306/ai_classroom` |
| `SPRING_DATASOURCE_USERNAME` | 数据库用户名 | `root` |
| `SPRING_DATASOURCE_PASSWORD` | 数据库密码 | `password` |
| `ACCESS_CODE` | 访问码（可选，不设置则开放访问） | `my-secret-2024` |
| `JWT_SECRET` | JWT 签名密钥（可选） | `your-secret-key` |
| `OPENAI_API_KEY` | OpenAI API Key | `sk-...` |
| `ANTHROPIC_API_KEY` | Anthropic API Key | `sk-ant-...` |
| `GOOGLE_API_KEY` | Google Gemini API Key | `AIza...` |

### 应用配置

主要配置项（`application.yml`）：

```yaml
# 服务器配置
server:
  port: 8080
  servlet:
    context-path: /

# 数据库配置
spring:
  datasource:
    url: jdbc:mariadb://localhost:3306/ai_classroom
    username: root
    password: password
    driver-class-name: org.mariadb.jdbc.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5

# JPA 配置
  jpa:
    hibernate:
      ddl-auto: validate  # 生产环境使用 validate
    show-sql: false
    properties:
      hibernate:
        format_sql: true

# 认证配置
access:
  code: ${ACCESS_CODE:}  # 访问码，不配置则开放访问

jwt:
  secret: ${JWT_SECRET:change-me-in-production}
  expiration: 86400  # Token 有效期（秒），24小时

# 用户系统配置
account:
  registration-enabled: true  # 是否允许注册
  email-verification: false   # 是否需要邮箱验证

# AI 服务配置
ai:
  default-provider: openai
  default-model: gpt-4
  providers:
    openai:
      api-key: ${OPENAI_API_KEY}
      base-url: https://api.openai.com/v1
    anthropic:
      api-key: ${ANTHROPIC_API_KEY}

# 异步任务配置
async:
  core-pool-size: 5
  max-pool-size: 10
  queue-capacity: 100
```

## 性能优化

### 数据库优化

- 为高频查询字段添加索引
- 使用连接池优化连接管理
- 使用 DTO Projection 减少数据传输
- 避免 N+1 查询问题

### 缓存策略

- 课程列表：本地缓存 5 分钟
- AI 模型配置：本地缓存 1 小时
- 生成结果：按需缓存

### 并发优化

- 使用线程池处理异步任务
- 场景生成支持并行处理
- 数据库连接池合理配置

## 安全

### 认证授权（混合方案）

本项目采用 **ACCESS_CODE + JWT Token** 混合认证方案，分阶段实施：

#### 第一阶段：ACCESS_CODE（与现有前端兼容）

**访问码保护机制**：
- 环境变量配置：`ACCESS_CODE=your-secret-code`
- 未配置则系统完全开放
- 已配置则需要输入访问码才能使用

**工作流程**：
```
1. 用户访问系统
2. 前端检测需要访问码 (GET /api/access-code/status)
3. 用户输入访问码 (POST /api/access-code/verify)
4. 验证通过后设置 HttpOnly Cookie (openmaic_access)
5. 后续请求自动携带 Cookie，有效期 7 天
```

**Token 格式**：
```
timestamp.hmac_signature
```

**安全特性**：
- HMAC-SHA256 签名防篡改
- HttpOnly Cookie 防 XSS
- SameSite=Lax 防 CSRF
- 常量时间比较防时序攻击

#### 第二阶段：JWT Token（可选用户系统）

**用户注册登录**：
- 支持邮箱 + 密码注册
- 登录成功返回 JWT Token
- Token 有效期 24 小时

**Token 使用**：
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Token 内容**：
```json
{
  "sub": "123456",        // 用户 ID
  "email": "user@example.com",
  "nickname": "张三",
  "iat": 1735660800,
  "exp": 1735747200
}
```

**数据隔离**：
- 匿名用户：owner_id = 0
- 登录用户：owner_id = 用户 ID
- 查询时自动过滤当前用户数据

#### 认证优先级

```
请求 → ACCESS_CODE 验证（如配置）→ JWT Token 验证（可选）→ SecurityContext
```

**四种部署模式**：

| 模式 | ACCESS_CODE | 用户系统 | 说明 |
|-----|------------|---------|------|
| 完全开放 | 未配置 | 未启用 | 无限制访问 |
| 访问码保护 | 已配置 | 未启用 | 团队共享密码 |
| 可选登录 | 已配置 | 已启用 | 推荐注册，允许匿名 |
| 强制登录 | 未配置 | 已启用 | 必须注册登录 |

### 数据安全

- 用户密码 BCrypt 加密存储（强度 10）
- JWT Token 签名验证（HS256/RS256）
- 敏感配置环境变量管理
- SQL 注入防护（参数化查询）
- API Key 加密存储

### 接口安全

- 接口访问频率限制（Rate Limiting）
- 请求参数校验（Bean Validation）
- 文件上传类型和大小检查
- CORS 跨域配置
- HTTPS 强制（生产环境）

## 故障排查

### 常见问题

**问题 1: 数据库连接失败**

```
Caused by: java.sql.SQLException: Access denied for user 'root'@'localhost'
```

解决方案：检查数据库用户名密码配置

**问题 2: Flyway 迁移失败**

```
FlywayException: Found non-empty schema(s) "ai_classroom" but no schema history table
```

解决方案：首次运行需清空数据库或使用 `flyway.baseline-on-migrate=true`

**问题 3: AI API 调用超时**

```
SocketTimeoutException: Read timed out
```

解决方案：检查网络连接或增加超时时间配置

### 日志查看

```bash
# 查看实时日志
tail -f logs/app.log

# 查看错误日志
grep ERROR logs/app.log

# 查看特定时间段日志
grep "2026-05-31 10:" logs/app.log
```

## 贡献指南

### 贡献流程

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交改动 (`git commit -m 'feat: add amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 提交 Pull Request

### 提交信息规范

- `feat:` 新功能
- `fix:` 修复问题
- `refactor:` 重构代码
- `docs:` 文档更新
- `test:` 测试相关
- `chore:` 构建/工具配置

### Code Review 要求

- 代码符合规范
- 通过所有测试
- 有必要的注释
- 更新相关文档

## 许可证

本项目采用 AGPL-3.0 许可证，详见 [LICENSE](../LICENSE) 文件。

## 联系方式

- 项目主页: https://github.com/THU-MAIC/OpenMAIC
- 问题反馈: https://github.com/THU-MAIC/OpenMAIC/issues
- 技术交流: （待补充）

---

## 附录

### 相关项目

- [OpenMAIC Frontend](../): Next.js 前端项目
- [ql-web-common](https://mvn.qianlang.cc): QL 团队通用组件库
- [ql-spectra-chat-backend](https://github.com/ql/spectra-chat-backend): 参考架构项目

### 参考文档

- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [Spring Data JPA 文档](https://spring.io/projects/spring-data-jpa)
- [Spring Security 文档](https://spring.io/projects/spring-security)
- [LangChain4j 文档](https://docs.langchain4j.dev/)
- [重构工作指导](./REFACTORING_GUIDE.md)

---

**更新时间**: 2026-05-31  
**版本**: 0.1.0-SNAPSHOT
