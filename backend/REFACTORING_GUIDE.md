# ql-ai-classroom 后端重构工作指导

## 文档概述

本文档为 ql-ai-classroom 项目的后端重构提供分阶段的工作指导。重构目标是将现有 Next.js API Routes 后端迁移至基于 Spring Boot + Spring JPA + Spring Security + MariaDB 的标准 Java 后端架构。

**项目路径**: `/backend`  
**参考项目**: `ql-spectra-chat-backend`  
**组织前缀**: `ql` (量化团队前缀)

---

## 重构原则

### 设计原则
1. **遵循四步设计流程**：每个模块开发前需经过需求分析、API 设计、数据模型设计、实施方案确认
2. **参考标准架构**：严格参照 `ql-spectra-chat-backend` 的分层结构和命名规范
3. **渐进式迁移**：分阶段实施，确保每个阶段可独立验证
4. **向前兼容**：保持与现有前端 API 的兼容性，最小化前端改动

### 技术约束
- Spring Boot 版本：3.5.x
- Java 版本：21
- 数据库：MariaDB 11.x
- 依赖 ql-web-common 基础库（版本需确认）

### 命名规范
- 包名：`cc.qianlang.aiclassroom`
- 模块命名：与业务领域对应（classroom, chat, generation, quiz, pbl 等）
- 类命名后缀：Entity, Repo, Srv, Ctrl, Req, Vo

---

## 技术选型确认

### AI 框架
**确定方案**: LangChain4j

- 使用 LangChain4j 作为统一的 AI 服务抽象层
- 支持多种 AI 提供商（OpenAI、Anthropic、Google Gemini 等）
- 支持流式响应和多代理编排

**依赖版本**: langchain4j-spring-boot-starter 0.37.0+

### 认证方案
**确定方案**: 混合方案（ACCESS_CODE + JWT Token）

采用分阶段演进策略：

**第一阶段（兼容现有）**:
- 保留 ACCESS_CODE 访问码机制（与现有前端完全兼容）
- 添加可选的用户登录系统（JWT Token）
- 支持匿名用户和登录用户并存
- 数据按用户隔离（owner_id，0 表示匿名）

**第二阶段（逐步迁移）**:
- 前端添加登录/注册功能
- 鼓励用户注册登录
- 数据迁移到具体用户

**认证流程**:
```
请求 → ACCESS_CODE 验证（如果配置了）→ JWT Token 验证（可选）→ 设置 SecurityContext
```

### 多租户支持
**确定方案**: 第一阶段不启用，预留字段

- 所有业务表预留 `tenant_id` 字段（默认值 0）
- 暂不实现多租户隔离逻辑
- 为未来扩展预留可能性

---

## 阶段 1：基础框架搭建

**预计工时**: 1-2 周  
**目标**: 完成项目骨架、基础配置、统一组件

### 1.1 项目初始化

**任务描述**:
- 使用 Spring Initializr 或 Maven Archetype 创建 Spring Boot 项目
- 配置 pom.xml 依赖管理
- 确认 Java 21 运行环境
- 配置 Maven 仓库（aliyun, qianlang-mvn-repo）

**验收标准**:
- [ ] 项目可成功编译打包
- [ ] Maven 依赖可正常下载
- [ ] 应用可成功启动并监听端口

**关键依赖**:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-webflux (用于 SSE)
- mariadb-java-client
- ql-web-common
- lombok
- flyway-core

### 1.2 数据库配置

**任务描述**:
- 创建 MariaDB 数据库实例
- 配置数据源连接信息
- 配置 JPA/Hibernate 参数
- 配置 Flyway 数据库版本管理

**验收标准**:
- [ ] 应用可成功连接数据库
- [ ] Flyway 可正常执行迁移脚本
- [ ] JPA 可正常创建 SessionFactory

**配置文件**:
- `application.yml`: 主配置
- `application-dev.yml`: 开发环境配置
- `application-test.yml`: 测试环境配置
- `application-prod.yml`: 生产环境配置

### 1.3 Spring Security 配置（混合认证方案）

**任务描述**:
实现 ACCESS_CODE + JWT Token 混合认证机制，分两个阶段完成。

**第一阶段：ACCESS_CODE 机制**
- 实现 AccessCodeFilter（参照现有 middleware.ts 逻辑）
- 检查环境变量 `access.code` 是否配置
- 验证 Cookie 中的 HMAC 签名 Token
- 实现 `/api/access-code/verify` 接口（验证访问码并设置 Cookie）
- 实现 `/api/access-code/status` 接口（查询认证状态）

**第二阶段：JWT Token 机制**
- 实现 JwtAuthenticationFilter
- 支持 Bearer Token 认证
- 支持用户登录/注册接口
- 实现 SecurityContext 用户信息设置

**认证优先级**:
```java
1. 检查 ACCESS_CODE（如果配置了）
   - 未配置 → 跳过
   - 已配置 → 验证 Cookie Token，失败返回 401
   
2. 检查 JWT Token（可选）
   - 无 Token → 匿名用户（owner_id = 0）
   - 有 Token → 登录用户（owner_id = 用户ID）
```

**配置 CORS 跨域**:
- 允许前端域名跨域请求
- 支持 Cookie 携带
- 支持 Authorization Header

**公开接口白名单**:
- `/api/health`
- `/api/access-code/**`
- `/api/account/register`
- `/api/account/login`
- `/swagger-ui/**`
- `/v3/api-docs/**`

**验收标准**:
- [ ] ACCESS_CODE 认证机制与现有前端完全兼容
- [ ] JWT Token 认证可选启用
- [ ] 匿名用户可正常使用（owner_id = 0）
- [ ] 登录用户数据正确隔离
- [ ] 前端可正常进行跨域请求
- [ ] 健康检查接口无需认证

**技术要点**:
- HMAC-SHA256 签名验证（与现有 Node.js 实现一致）
- HttpOnly Cookie 防止 XSS
- JWT Token 使用 HS256 或 RS256 签名
- Token 有效期：Cookie 7天，JWT 24小时

### 1.4 统一异常处理

**任务描述**:
- 创建统一异常处理器（@ControllerAdvice）
- 定义业务异常枚举（参照参考项目的 Err 类）
- 实现异常与 HTTP 状态码的映射
- 配置统一错误响应格式

**验收标准**:
- [ ] 所有异常可被统一拦截处理
- [ ] 异常响应格式符合前端约定
- [ ] 包含必要的错误信息（code, message, detail）

### 1.5 统一响应格式

**任务描述**:
- 定义统一响应封装类（Response）
- 实现响应工具类（ResponseUtils）
- 配置全局响应拦截器
- 定义响应状态码枚举

**验收标准**:
- [ ] 所有成功响应格式一致
- [ ] 包含必要字段（code, message, data, timestamp）
- [ ] 与现有前端响应格式兼容

### 1.6 API 文档配置

**任务描述**:
- 集成 SpringDoc OpenAPI 3
- 配置 Swagger UI 访问路径
- 配置 API 文档基础信息（标题、版本、描述）
- 配置认证方式说明

**验收标准**:
- [ ] 可访问 Swagger UI 页面
- [ ] API 文档自动生成
- [ ] 可通过文档进行接口测试

### 1.7 日志配置

**任务描述**:
- 配置 Logback 日志框架
- 定义日志级别策略
- 配置日志输出格式
- 配置日志文件滚动策略

**验收标准**:
- [ ] 日志可正常输出到控制台
- [ ] 日志可正常写入文件
- [ ] 不同环境使用不同日志级别
- [ ] 包含必要的请求追踪信息

---

## 阶段 2：核心模块迁移

**预计工时**: 2-3 周  
**目标**: 完成 Classroom、Storage、Account 等核心模块

### 2.1 Account (用户账号) 模块（可选功能）

**任务描述**:
实现可选的用户账号系统，支持混合认证方案。

**数据库设计**:
- 用户账号实体表结构
- 预留字段：avatar_url, nickname, bio（对应前端 localStorage 数据）
- owner_id 关联设计（0 表示匿名用户）

**业务逻辑**:
- 用户注册（邮箱 + 密码）
- 用户登录（返回 JWT Token）
- 用户信息查询和更新
- 密码 BCrypt 加密存储

**认证集成**:
- 登录成功返回 JWT Token
- Token 包含用户 ID、邮箱等信息
- SecurityContext 中设置当前用户
- 支持获取当前登录用户（`getCurrentUser()`）

**匿名用户支持**:
- 未登录用户 owner_id = 0
- 登录用户 owner_id = 实际用户ID
- 课程等资源按 owner_id 隔离

**验收标准**:
- [ ] 用户可注册、登录（可选功能）
- [ ] 用户信息可查询、更新
- [ ] 用户密码 BCrypt 加密存储
- [ ] JWT Token 正确签发和验证
- [ ] 匿名用户和登录用户数据正确隔离
- [ ] 前端 localStorage 数据可迁移到数据库

**关键实体**:
- Account: 用户账号实体
  - id (主键)
  - email (唯一，登录凭证)
  - password_hash (BCrypt 加密)
  - nickname (昵称)
  - avatar_url (头像URL)
  - bio (个人简介)
  - created_at, updated_at

**关键接口**:
- POST /api/account/register: 用户注册（可选，未配置 ACCESS_CODE 时开放）
- POST /api/account/login: 用户登录（返回 JWT Token）
- GET /api/account/me: 获取当前用户信息（需要 JWT Token）
- PUT /api/account/me: 更新用户信息（需要 JWT Token）
- GET /api/account/profile: 获取用户公开资料

**实施策略**:
- 第一阶段：实现基础功能，但前端暂不启用
- 第二阶段：前端添加登录/注册入口
- 第三阶段：引导用户从匿名迁移到注册用户

### 2.2 Storage (文件存储) 模块

**任务描述**:
- 设计文件存储实体表结构
- 实现文件上传服务
- 实现文件下载服务
- 实现文件删除服务
- 配置文件存储路径策略

**验收标准**:
- [ ] 文件可正常上传
- [ ] 文件可正常下载
- [ ] 文件元数据可持久化
- [ ] 支持文件类型验证
- [ ] 支持文件大小限制

**关键实体**:
- StorageFile: 文件存储实体

**关键接口**:
- POST /api/storage/upload: 文件上传
- GET /api/storage/download/{fileId}: 文件下载
- DELETE /api/storage/{fileId}: 删除文件
- GET /api/storage/{fileId}/info: 获取文件信息

**技术选型**:
- 第一阶段：本地文件系统存储
- 后续扩展：OSS 对象存储（阿里云/腾讯云）

### 2.3 Classroom (课程) 模块

**任务描述**:
- 设计课程相关实体表结构（Classroom, Stage, Scene）
- 实现课程 CRUD 服务
- 实现课程场景管理服务
- 创建课程管理 API 接口
- 实现从文件系统到数据库的数据迁移工具
- 实现用户数据隔离机制

**用户数据隔离策略**:
- 所有课程关联 owner_id
- owner_id = 0: 匿名用户创建（或迁移的旧数据）
- owner_id > 0: 登录用户创建
- 查询时自动过滤当前用户的课程
- 支持"公开课程"概念（后期扩展）

**验收标准**:
- [ ] 课程可创建、查询、更新、删除
- [ ] 课程场景可添加、排序、删除
- [ ] 课程数据可持久化到数据库
- [ ] 支持课程状态管理（草稿/发布/归档）
- [ ] 用户数据正确隔离（按 owner_id）
- [ ] 匿名用户和登录用户都可正常使用
- [ ] 与现有前端 API 完全兼容

**关键实体**:
- Classroom: 课程实体
  - owner_id: 创建者ID（0 表示匿名）
  - tenant_id: 租户ID（预留，默认 0）
- Stage: 舞台配置实体
- Scene: 场景实体

**关键接口**:
- POST /api/classroom: 创建课程
- GET /api/classroom?id={classroomId}: 获取课程详情
- PUT /api/classroom/{classroomId}: 更新课程
- DELETE /api/classroom/{classroomId}: 删除课程
- GET /api/classroom/list: 获取课程列表
- POST /api/classroom/{classroomId}/scene: 添加场景
- PUT /api/classroom/{classroomId}/scene/order: 调整场景顺序

**数据迁移**:
- 读取现有 `data/classrooms/*.json` 文件
- 解析 JSON 结构并转换为实体对象
- 批量写入数据库
- 保留原文件作为备份

### 2.4 AI Provider (AI 服务) 模块

**任务描述**:
- 集成 LangChain4j 框架
- 配置多种 AI 服务提供商
- 实现 Provider 工厂和配置管理
- 实现 Provider 选择策略
- 实现流式和非流式调用支持

**技术选型**:
使用 **LangChain4j** 作为统一的 AI 服务抽象层

**依赖配置**:
```xml
<!-- LangChain4j 核心 -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-spring-boot-starter</artifactId>
    <version>0.37.0</version>
</dependency>

<!-- 各 Provider -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai</artifactId>
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-anthropic</artifactId>
</dependency>
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-google-ai-gemini</artifactId>
</dependency>
```

**验收标准**:
- [ ] LangChain4j 集成完成
- [ ] 支持 OpenAI、Anthropic、Google Gemini
- [ ] 可动态切换 Provider 和 Model
- [ ] 支持流式和非流式调用
- [ ] 统一的错误处理机制
- [ ] 支持 API Key 配置管理
- [ ] 与现有 Provider 配置兼容

**关键接口**:
- POST /api/verify-model: 验证模型配置
- GET /api/server-providers: 获取可用的 AI 服务列表

**实现要点**:
- 使用 LangChain4j 的 ChatLanguageModel 接口
- 支持自定义 Base URL（兼容国内镜像）
- 实现配置热加载
- 实现 Provider 健康检查

---

## 阶段 3：聊天与流式处理

**预计工时**: 2-3 周  
**目标**: 实现 SSE 流式聊天、多代理编排

### 3.1 Chat (聊天) 模块 - 基础功能

**任务描述**:
- 设计聊天会话和消息实体表结构
- 实现聊天会话 CRUD 服务
- 实现聊天消息存储服务
- 创建聊天会话管理 API

**验收标准**:
- [ ] 聊天会话可创建、查询、删除
- [ ] 聊天消息可持久化存储
- [ ] 支持消息分页查询
- [ ] 支持会话类型区分（QA/Discussion/Roundtable）

**关键实体**:
- ChatSession: 聊天会话实体
- ChatMessage: 聊天消息实体

**关键接口**:
- POST /api/chat/session: 创建会话
- GET /api/chat/session/{sessionId}: 获取会话详情
- GET /api/chat/session/{sessionId}/messages: 获取消息列表
- DELETE /api/chat/session/{sessionId}: 删除会话

### 3.2 Chat (聊天) 模块 - SSE 流式响应

**任务描述**:
- 实现基于 Spring WebFlux SseEmitter 的流式响应
- 实现聊天消息流式生成服务
- 实现 SSE 事件格式定义
- 实现客户端中断处理机制
- 实现心跳保活机制

**验收标准**:
- [ ] 聊天响应可实时流式返回
- [ ] SSE 连接可保持长时间稳定
- [ ] 客户端断开连接时服务端可正确感知
- [ ] 支持心跳保活防止超时
- [ ] 与现有前端 SSE 格式兼容

**关键接口**:
- POST /api/chat: SSE 流式聊天（核心接口）

**技术要点**:
- 使用 SseEmitter 实现服务端推送
- 使用 CompletableFuture 实现异步处理
- 使用 AbortController 感知客户端中断
- 定期发送心跳事件防止连接超时

### 3.3 Orchestration (多代理编排) 模块

**任务描述**:
- 研究 LangChain4j 的多代理支持能力
- 设计多代理编排流程（参照现有 LangGraph 实现）
- 实现 Director Agent 调度逻辑
- 实现 Agent 轮流发言机制
- 实现 Agent 状态管理

**验收标准**:
- [ ] 支持多个 Agent 协作对话
- [ ] Agent 可按规则轮流发言
- [ ] 支持 Agent 主动发起讨论
- [ ] 支持用户随时插入对话
- [ ] 编排逻辑与现有功能等效

**技术选型**:
- 优先使用 LangChain4j 的 Agent 框架
- 如不满足需求则自行实现编排逻辑

**配置管理**:
- Agent 配置信息（角色、人设、能力）
- Agent 发言优先级规则
- Agent 状态转换规则

---

## 阶段 4：生成模块

**预计工时**: 2-3 周  
**目标**: 实现课程生成、异步任务管理

### 4.1 Generation (生成) 模块 - 任务管理

**任务描述**:
- 设计异步生成任务实体表结构
- 实现任务创建、查询、更新服务
- 实现任务状态流转管理
- 实现任务进度跟踪机制
- 创建任务管理 API

**验收标准**:
- [ ] 生成任务可异步执行
- [ ] 任务状态可实时查询
- [ ] 任务进度可动态更新
- [ ] 任务失败可记录错误信息
- [ ] 支持任务结果持久化

**关键实体**:
- GenerationJob: 生成任务实体

**关键接口**:
- POST /api/generate-classroom/submit: 提交生成任务
- GET /api/generate-classroom/status/{jobId}: 查询任务状态
- GET /api/generate-classroom/result/{jobId}: 获取生成结果

**技术要点**:
- 使用 Spring @Async 实现异步执行
- 使用 CompletableFuture 返回异步结果
- 使用数据库记录任务状态和进度

### 4.2 Generation (生成) 模块 - 大纲生成

**任务描述**:
- 实现课程大纲生成服务
- 集成 AI Provider 调用
- 实现 Prompt 模板管理
- 实现生成结果解析和验证
- 实现生成失败重试机制

**验收标准**:
- [ ] 可根据主题生成课程大纲
- [ ] 大纲结构符合预期格式
- [ ] 生成失败可自动重试
- [ ] 支持自定义生成参数
- [ ] 与现有功能等效

**关键接口**:
- POST /api/generate/outline: 生成课程大纲

**技术要点**:
- Prompt 模板版本管理
- 生成结果的 JSON 解析和修复
- 超时和重试策略

### 4.3 Generation (生成) 模块 - 场景生成

**任务描述**:
- 实现幻灯片场景生成服务
- 实现测验场景生成服务
- 实现交互式场景生成服务
- 实现 PBL 场景生成服务
- 实现场景并行生成优化

**验收标准**:
- [ ] 支持多种场景类型生成
- [ ] 生成内容质量符合预期
- [ ] 支持场景并行生成以提升速度
- [ ] 生成结果可正确渲染
- [ ] 与现有功能等效

**关键接口**:
- POST /api/generate/scenes: 批量生成场景
- POST /api/generate/slide: 生成单个幻灯片
- POST /api/generate/quiz: 生成测验
- POST /api/generate/interactive: 生成交互式场景

**技术要点**:
- 使用线程池实现并行生成
- 场景生成结果的组装和排序
- 生成进度的实时更新

### 4.4 Generation (生成) 模块 - 媒体资源生成

**任务描述**:
- 实现图片生成服务集成
- 实现视频生成服务集成
- 实现 TTS 语音合成服务集成
- 实现媒体资源缓存机制
- 实现媒体资源存储管理

**验收标准**:
- [ ] 支持图片自动生成
- [ ] 支持 TTS 语音合成
- [ ] 媒体资源可正确存储和访问
- [ ] 支持多种媒体服务提供商
- [ ] 与现有功能等效

**关键接口**:
- POST /api/generate/image: 生成图片
- POST /api/generate/video: 生成视频
- POST /api/generate/tts: 生成语音

**技术集成**:
- 图片生成：DALL-E / MidJourney / Stable Diffusion
- TTS：Azure TTS / OpenAI TTS / VoxCPM2

---

## 阶段 5：扩展功能

**预计工时**: 2-3 周  
**目标**: 实现测验、PBL、PDF 解析等扩展功能

### 5.1 Quiz (测验) 模块

**任务描述**:
- 设计测验相关实体表结构（Quiz, Question, Answer）
- 实现测验创建和管理服务
- 实现测验评分服务
- 实现 AI 自动评分逻辑
- 创建测验管理 API

**验收标准**:
- [ ] 测验可创建、查询、更新
- [ ] 支持多种题型（单选/多选/简答）
- [ ] AI 可自动评分并给出反馈
- [ ] 评分结果可持久化
- [ ] 与现有功能等效

**关键实体**:
- Quiz: 测验实体
- Question: 问题实体
- Answer: 答案实体
- QuizResult: 测验结果实体

**关键接口**:
- POST /api/quiz: 创建测验
- GET /api/quiz/{quizId}: 获取测验详情
- POST /api/quiz-grade: 提交答案并评分

### 5.2 PBL (项目式学习) 模块

**任务描述**:
- 设计 PBL 项目实体表结构
- 实现 PBL 项目创建和管理服务
- 实现角色选择和分配逻辑
- 实现项目进度跟踪
- 创建 PBL 管理 API

**验收标准**:
- [ ] PBL 项目可创建和管理
- [ ] 用户可选择项目角色
- [ ] 项目阶段可推进
- [ ] 协作成果可保存
- [ ] 与现有功能等效

**关键实体**:
- PblProject: PBL 项目实体
- PblRole: 项目角色实体
- PblMilestone: 项目里程碑实体

**关键接口**:
- POST /api/pbl/project: 创建 PBL 项目
- POST /api/pbl/project/{projectId}/role: 选择角色
- POST /api/pbl/project/{projectId}/milestone: 提交里程碑成果

### 5.3 PDF 解析模块

**任务描述**:
- 集成 PDF 解析服务（MinerU 或其他）
- 实现 PDF 文件上传和解析
- 实现解析结果结构化存储
- 实现 OCR 和表格识别
- 创建 PDF 解析 API

**验收标准**:
- [ ] PDF 文件可上传解析
- [ ] 支持文本、图片、表格提取
- [ ] 支持 OCR 识别
- [ ] 解析结果可用于课程生成
- [ ] 与现有功能等效

**关键接口**:
- POST /api/parse-pdf: 上传并解析 PDF

**技术集成**:
- 优先集成 MinerU API
- 备选方案：Apache PDFBox / iText

### 5.4 Web 搜索模块

**任务描述**:
- 集成 Web 搜索服务
- 实现搜索结果缓存
- 实现搜索结果过滤和排序
- 创建 Web 搜索 API

**验收标准**:
- [ ] 支持 Web 实时搜索
- [ ] 搜索结果质量符合预期
- [ ] 支持结果缓存以提升性能
- [ ] 与现有功能等效

**关键接口**:
- POST /api/web-search: 执行 Web 搜索

**技术集成**:
- Google Search API
- Bing Search API
- 或其他搜索服务

---

## 阶段 6：优化与部署

**预计工时**: 1-2 周  
**目标**: 性能优化、安全加固、部署配置

### 6.1 性能优化

**任务描述**:
- 数据库查询性能分析和优化
- 添加必要的数据库索引
- 实现缓存策略（本地缓存 / Redis）
- 优化大对象查询（DTO Projection）
- 优化并发处理性能

**验收标准**:
- [ ] 关键接口响应时间 < 100ms
- [ ] 并发 100 用户无性能问题
- [ ] 数据库查询使用索引
- [ ] 缓存命中率 > 80%

**优化方向**:
- 课程列表查询优化
- 聊天消息分页优化
- 生成任务并行优化
- 媒体资源缓存优化

### 6.2 安全加固

**任务描述**:
- 实现接口访问频率限制（Rate Limiting）
- 实现敏感数据加密存储
- 实现 SQL 注入防护
- 实现 XSS 攻击防护
- 实现 CSRF 防护

**验收标准**:
- [ ] 通过 OWASP Top 10 安全检查
- [ ] 敏感数据加密存储
- [ ] 接口有频率限制保护
- [ ] 通过基础渗透测试

**安全检查清单**:
- [ ] 用户密码 BCrypt 加密
- [ ] JWT Token 签名验证
- [ ] API Key 安全存储
- [ ] 文件上传类型和大小限制
- [ ] SQL 参数化查询

### 6.3 接口文档完善

**任务描述**:
- 完善所有 API 接口的 Swagger 注解
- 添加接口描述、参数说明、响应示例
- 添加错误码说明
- 提供接口调用示例

**验收标准**:
- [ ] 所有接口有完整文档
- [ ] 文档包含请求/响应示例
- [ ] 错误码有详细说明
- [ ] 可通过文档直接测试接口

### 6.4 单元测试

**任务描述**:
- 为核心服务编写单元测试
- 为关键接口编写集成测试
- 配置测试数据库
- 配置测试覆盖率检查

**验收标准**:
- [ ] 核心服务测试覆盖率 > 70%
- [ ] 所有接口有集成测试
- [ ] 测试可自动化执行
- [ ] CI 流程包含测试步骤

### 6.5 CI/CD 配置

**任务描述**:
- 配置 GitHub Actions 或 GitLab CI
- 配置自动化测试流程
- 配置自动化构建流程
- 配置自动化部署流程
- 配置代码质量检查

**验收标准**:
- [ ] 代码提交自动触发 CI
- [ ] CI 自动执行测试和构建
- [ ] 测试失败阻止合并
- [ ] 支持自动部署到测试环境

---

## 认证方案演进策略

### 设计目标

1. **完全兼容现有前端**：第一阶段无需修改前端代码
2. **渐进式引入用户系统**：避免强制用户注册
3. **数据平滑迁移**：匿名数据可后续关联到用户
4. **灵活部署**：支持完全开放、访问码保护、强制登录等多种模式

### 认证模式对比

| 模式 | ACCESS_CODE | 用户系统 | 适用场景 |
|-----|------------|---------|---------|
| **完全开放** | 未配置 | 未启用 | 开源自部署，无限制访问 |
| **访问码保护** | 已配置 | 未启用 | 小团队共享，简单密码保护 |
| **可选登录** | 已配置 | 已启用 | 鼓励注册，但允许匿名使用 |
| **强制登录** | 未配置 | 已启用 | 完整用户系统，强制注册 |

### 第一阶段实施细节

#### 1. AccessCodeFilter 实现

**目标**：完全复现现有 middleware.ts 的逻辑

**流程**:
```
1. 检查环境变量 access.code
   - 未配置 → 直接放行
   - 已配置 → 继续验证

2. 检查白名单接口
   - /api/access-code/** → 放行
   - /api/health → 放行
   
3. 检查 Cookie: openmaic_access
   - 有效 Token → 放行
   - 无效/无 Token → 返回 401 (API 请求) 或放行 (页面请求)
```

**Token 格式**:
```
timestamp.signature
```

**签名算法**:
```java
String timestamp = String.valueOf(System.currentTimeMillis());
String signature = HmacUtils.hmacSha256Hex(accessCode, timestamp);
String token = timestamp + "." + signature;
```

**验证算法**:
```java
String[] parts = token.split("\\.");
String timestamp = parts[0];
String signature = parts[1];
String expected = HmacUtils.hmacSha256Hex(accessCode, timestamp);
return MessageDigest.isEqual(
    signature.getBytes(),
    expected.getBytes()
);
```

**Cookie 设置**:
```java
Cookie cookie = new Cookie("openmaic_access", token);
cookie.setHttpOnly(true);
cookie.setPath("/");
cookie.setMaxAge(7 * 24 * 60 * 60); // 7 天
cookie.setSecure(isProd);
cookie.setSameSite("Lax");
```

#### 2. 访问码验证接口

**POST /api/access-code/verify**

请求:
```json
{
  "code": "user-input-code"
}
```

响应（成功）:
```json
{
  "code": 0,
  "message": "success",
  "data": {
    "valid": true
  }
}
```

同时设置 Cookie: `openmaic_access=timestamp.signature`

**GET /api/access-code/status**

响应:
```json
{
  "code": 0,
  "data": {
    "enabled": true,     // 是否配置了 ACCESS_CODE
    "authenticated": true // 当前是否已通过验证
  }
}
```

### 第二阶段实施细节

#### 1. JWT Token 集成

**依赖**:
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
</dependency>
```

**Token 生成**:
```java
String token = Jwts.builder()
    .setSubject(user.getId().toString())
    .claim("email", user.getEmail())
    .claim("nickname", user.getNickname())
    .setIssuedAt(new Date())
    .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
    .signWith(SignatureAlgorithm.HS256, jwtSecret)
    .compact();
```

**Token 验证**:
```java
Claims claims = Jwts.parser()
    .setSigningKey(jwtSecret)
    .parseClaimsJws(token)
    .getBody();
Long userId = Long.parseLong(claims.getSubject());
```

#### 2. SecurityContext 设置

**匿名用户**:
```java
SecurityContextHolder.getContext().setAuthentication(
    new AnonymousAuthenticationToken(
        "anonymous",
        "anonymous",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
    )
);
```

**登录用户**:
```java
UserDetails userDetails = loadUserByUserId(userId);
SecurityContextHolder.getContext().setAuthentication(
    new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        userDetails.getAuthorities()
    )
);
```

#### 3. 获取当前用户

**工具方法**:
```java
public Optional<Long> getCurrentUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || auth instanceof AnonymousAuthenticationToken) {
        return Optional.empty();
    }
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    return Optional.of(((AccountPrincipal) userDetails).getAccountId());
}
```

**业务层使用**:
```java
Long ownerId = getCurrentUserId().orElse(0L);
classroom.setOwnerId(ownerId);
```

### 数据库字段设计

**所有业务表添加**:
```sql
owner_id BIGINT DEFAULT 0 COMMENT '创建者ID, 0表示匿名用户',
tenant_id BIGINT DEFAULT 0 COMMENT '租户ID, 预留字段',

INDEX idx_owner (owner_id),
INDEX idx_tenant_owner (tenant_id, owner_id)
```

### 前端对接改造

#### 第一阶段（无需改造）

- 前端完全不感知后端变化
- ACCESS_CODE 验证流程保持不变
- Cookie 机制保持不变

#### 第二阶段（添加可选功能）

**添加登录/注册入口**:
```typescript
// 可选显示登录按钮
{!isAuthenticated && (
  <Button onClick={() => router.push('/login')}>
    登录
  </Button>
)}
```

**登录成功后存储 Token**:
```typescript
// 存储到 localStorage 或 Cookie
const { token } = await login(email, password);
localStorage.setItem('jwt_token', token);

// 后续请求携带 Token
fetch('/api/classroom/list', {
  headers: {
    'Authorization': `Bearer ${token}`
  }
});
```

**迁移本地数据到用户**:
```typescript
// 用户首次登录后，提示迁移本地数据
if (localStorage.getItem('local_classrooms')) {
  const confirm = window.confirm('是否将本地课程关联到您的账号？');
  if (confirm) {
    // 调用迁移接口
    await migrateLocalData();
  }
}
```

### 配置示例

**application.yml**:
```yaml
# 访问码配置（可选）
access:
  code: ${ACCESS_CODE:}  # 未配置则为空，系统完全开放

# JWT 配置
jwt:
  secret: ${JWT_SECRET:your-secret-key-change-in-production}
  expiration: 86400  # 24小时

# 用户系统配置
account:
  registration-enabled: true  # 是否允许注册
  email-verification: false   # 是否需要邮箱验证
```

---

## 数据迁移专项

### 现有数据迁移工具开发

**任务描述**:
- 开发数据迁移命令行工具
- 实现从文件系统到数据库的数据转换
- 实现数据校验机制
- 实现回滚机制

**迁移步骤**:
1. 读取 `data/classrooms/*.json` 所有课程文件
2. 解析 JSON 结构
3. 转换为实体对象（Classroom, Stage, Scene）
4. 批量写入数据库
5. 验证数据完整性
6. 保留原文件作为备份

**验收标准**:
- [ ] 所有现有课程数据可成功迁移
- [ ] 迁移前后数据一致性 100%
- [ ] 迁移过程可回滚
- [ ] 迁移日志完整记录

---

## 前后端对接专项

### 前端适配改造

**任务描述**:
- 梳理现有前端 API 调用点
- 修改 API Base URL 配置
- 适配统一响应格式
- 添加 JWT Token 认证
- 处理错误码映射

**对接检查清单**:
- [ ] API 路径映射正确
- [ ] 请求参数格式兼容
- [ ] 响应数据结构兼容
- [ ] 认证机制正常工作
- [ ] 错误处理正确

**建议方案**:
- 第一阶段：保持 API 路径不变，响应格式兼容
- 第二阶段：逐步调整为 RESTful 风格

---

## 开发规范

### 代码规范

1. **包结构规范**:
   - entity: 实体类（对应数据库表）
   - repo: 数据访问层（继承 JpaRepository）
   - srv: 业务逻辑层（@Service）
   - web: 控制器层（@RestController）
   - pojo: 业务对象（DTO、VO、BO）

2. **命名规范**:
   - 类名：大驼峰（PascalCase）
   - 方法名：小驼峰（camelCase）
   - 常量：全大写下划线分隔（UPPER_SNAKE_CASE）
   - 数据库表名：下划线分隔（snake_case）

3. **注解使用**:
   - 实体类必须使用 @Entity, @Table, @Getter, @Setter
   - 服务类必须使用 @Service, @Transactional
   - 控制器必须使用 @RestController, @RequestMapping
   - 请求对象必须使用 @Validated

4. **异常处理**:
   - 使用自定义业务异常（MyException）
   - 不捕获不处理的异常
   - 统一由 @ControllerAdvice 处理

### Git 工作流规范

1. **分支策略**:
   - main: 主分支（稳定版本）
   - develop: 开发分支
   - feature/*: 功能分支
   - bugfix/*: 问题修复分支

2. **提交信息规范**:
   - feat: 新功能
   - fix: 修复问题
   - refactor: 重构
   - docs: 文档更新
   - test: 测试相关
   - chore: 构建/工具配置

3. **代码审查**:
   - 所有代码需经过 Code Review
   - 遵循模块开发四步设计流程
   - 关键模块需进行设计评审

---

## 验收标准总览

### 功能完整性

- [ ] 所有现有 API 功能完整迁移
- [ ] 前端功能无退化
- [ ] 数据完整性保证

### 性能指标

- [ ] 接口响应时间 < 现有系统
- [ ] 支持并发 100+ 用户
- [ ] 数据库查询优化到位

### 代码质量

- [ ] 单元测试覆盖率 > 70%
- [ ] 代码符合规范
- [ ] 无重大安全漏洞

### 部署能力

- [ ] 支持直接 JAR 部署
- [ ] 支持 systemd 服务配置
- [ ] 支持健康检查

---

## 附录

### 参考资源

- ql-spectra-chat-backend: `/Volumes/Data/Projects/ql-spectra-chat-backend`
- ql-web-common 文档: （待补充）
- Spring Boot 官方文档: https://spring.io/projects/spring-boot
- LangChain4j 文档: https://docs.langchain4j.dev/

### 术语表

- **Classroom**: 课程
- **Stage**: 舞台（课程的播放配置）
- **Scene**: 场景（课程的内容单元）
- **Agent**: 智能代理（AI 角色）
- **Orchestration**: 编排（多代理协作逻辑）
- **SSE**: Server-Sent Events（服务器推送事件）
- **PBL**: Project-Based Learning（项目式学习）

### 联系方式

技术问题咨询：（待补充）
