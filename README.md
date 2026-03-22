# 孤独症儿童健康信息管理系统设计与实现

> 本项目面向本科毕业设计场景，围绕“孤独症儿童健康信息管理系统设计与实现”题目，给出完整的系统分析、数据库设计、后端与前端实现方案、联调说明、测试方案，以及数据库本地快速启动方式。

---

## 一、整体方案概览

### 1.1 项目背景与研究意义
孤独症儿童在成长过程中往往需要长期的健康监测、康复训练、随访干预与阶段性评估。传统纸质档案或分散式 Excel 管理方式存在信息孤岛、查询效率低、统计分析困难、协同管理不足等问题。因此，设计并实现一个基于 Web 的孤独症儿童健康信息管理系统，具有较强的理论价值与现实意义。

本系统以儿童健康管理为核心，整合儿童档案、家长信息、诊断信息、健康记录、康复训练、随访记录、评估记录、统计分析与系统日志等模块，帮助管理员、医生/康复师、家长等不同角色进行协同管理。该系统可提升数据管理规范性、提高随访效率，并为后续康复决策与论文展示提供数据支撑。

### 1.2 技术路线
- 前端：Vue 3、Vite、Element Plus、Axios、Vue Router、Pinia、ECharts。
- 后端：Java 17、Spring Boot、Spring MVC、MyBatis-Plus、MySQL、Maven。
- 数据库：MySQL 8.0，字符集统一为 utf8mb4。
- 部署：支持本地 MySQL 手动初始化与 Docker Compose 一键启动两种方式。

### 1.3 建议系统角色
- **管理员**：用户、角色、日志、基础数据与全局统计管理。
- **医生/康复师**：儿童档案、诊断、健康、训练、随访、评估信息维护。
- **家长**：查看儿童信息、提交健康记录、查看训练与随访结果。

---

## 二、第一部分：系统分析与设计

### 2.1 系统需求分析
系统需满足以下核心需求：
1. 对孤独症儿童建立规范化电子档案。
2. 支持家长信息与监护关系管理。
3. 支持诊断、健康、训练、随访、评估全过程信息管理。
4. 支持多角色登录、权限控制、日志留痕。
5. 支持条件搜索、分页列表、详情查看、统计分析、图表展示。
6. 支持后续论文写作中的模块介绍、流程描述与数据库设计展示。

### 2.2 用户角色分析
#### （1）管理员
- 维护系统用户与角色分配。
- 查看全量业务数据与统计分析报表。
- 查询系统日志，保障系统可追踪性。

#### （2）医生/康复师
- 建立儿童档案与诊断信息。
- 录入健康、康复训练、随访与评估记录。
- 通过统计图表观察儿童阶段变化趋势。

#### （3）家长
- 查看儿童档案与历史记录。
- 提交日常健康情况与家庭反馈。
- 配合医生完成随访与康复计划执行。

### 2.3 业务流程分析
#### （1）档案建档流程
管理员/医生登录系统 → 新增儿童档案 → 维护家长信息 → 绑定监护关系 → 新增诊断信息 → 完成建档。

#### （2）健康管理流程
家长/医生录入健康数据 → 医生查看异常情况 → 生成护理建议 → 进入统计分析模块形成趋势数据。

#### （3）康复训练流程
康复师创建训练记录 → 记录训练主题、时长、表现与下次计划 → 家长查看训练反馈 → 作为阶段评估依据。

#### （4）随访评估流程
医生定期随访 → 记录家庭反馈、问题与建议 → 定期开展量化评估 → 形成阶段性康复结论。

### 2.4 功能需求分析
1. **用户与权限管理**：登录、退出、角色分配、状态启停、JWT 认证、菜单权限控制。
2. **儿童档案管理**：儿童基本信息录入、编辑、删除、分页查询、详情查看。
3. **家长信息管理**：家长信息维护、与儿童关联、紧急联系人管理。
4. **诊断信息管理**：诊断机构、诊断时间、诊断结果、等级、建议维护。
5. **健康记录管理**：体温、心率、睡眠、情绪、异常症状、护理建议管理。
6. **康复训练记录管理**：训练主题、训练类型、训练时长、训练表现、下次计划维护。
7. **随访记录管理**：随访方式、家长反馈、问题、建议、下次随访时间维护。
8. **评估记录管理**：量表分项得分、总分、评估结论、干预建议维护。
9. **统计分析管理**：儿童数量、训练次数、随访次数、评估趋势、健康变化图表展示。
10. **系统日志管理**：记录操作用户、操作类型、URI、状态与耗时。

### 2.5 非功能需求分析
- **易用性**：界面简洁清晰，适合演示与答辩。
- **安全性**：密码加密、JWT 鉴权、参数校验、日志审计。
- **可维护性**：模块分层清晰，前后端分离，便于扩展。
- **可靠性**：关键业务数据入库，支持逻辑删除与日志记录。
- **性能要求**：支持常规分页查询与图表统计，满足本科项目规模。

### 2.6 可行性分析
#### 技术可行性
采用成熟的 Spring Boot + Vue 3 技术体系，开发资料丰富，组件生态完善，适合毕业设计周期内完成。

#### 经济可行性
开发环境需求低，可在普通笔记本通过本地 MySQL 或 Docker 部署完成。

#### 操作可行性
系统面向管理员、医生/康复师、家长三类用户，页面操作符合常见后台系统习惯，学习成本低。

### 2.7 系统总体架构设计
采用前后端分离架构：
- **表示层**：Vue 3 + Element Plus，负责页面展示、表单交互、图表渲染。
- **接口层**：Spring MVC Controller，对外提供 RESTful API。
- **业务层**：Service 处理业务逻辑与权限控制。
- **数据访问层**：MyBatis-Plus Mapper 负责数据库交互。
- **数据层**：MySQL 存储业务数据与日志数据。

### 2.8 系统功能模块设计
- 用户与权限管理模块
- 儿童档案管理模块
- 家长信息管理模块
- 诊断信息管理模块
- 健康记录管理模块
- 康复训练记录管理模块
- 随访记录管理模块
- 评估记录管理模块
- 统计分析管理模块
- 系统日志管理模块

### 2.9 系统功能结构图（文字版）
- 孤独症儿童健康信息管理系统
  - 用户与权限管理
    - 用户登录/退出
    - 用户管理
    - 角色管理
    - 权限控制
  - 儿童档案管理
    - 档案新增
    - 档案编辑
    - 档案查询
    - 档案删除
    - 档案详情
  - 家长信息管理
    - 家长维护
    - 监护关系绑定
  - 诊断信息管理
    - 诊断记录新增/编辑/查询/删除
  - 健康记录管理
    - 健康数据维护
    - 异常情况查看
  - 康复训练记录管理
    - 训练记录维护
    - 训练计划跟踪
  - 随访记录管理
    - 随访记录维护
    - 家长反馈管理
  - 评估记录管理
    - 评估量表记录
    - 阶段结论维护
  - 统计分析管理
    - 基础统计
    - 趋势图分析
  - 系统日志管理
    - 操作日志查询
    - 异常日志追踪

---

## 三、第二部分：数据库设计

### 3.1 实体分析
系统包含以下核心实体：
- 系统用户（sys_user）
- 系统角色（sys_role）
- 用户角色关联（sys_user_role）
- 儿童档案（child_profile）
- 家长信息（parent_info）
- 儿童与监护人关系（child_guardian_rel）
- 诊断信息（diagnosis_info）
- 健康记录（health_record）
- 康复训练记录（rehabilitation_record）
- 随访记录（follow_up_record）
- 评估记录（assessment_record）
- 系统日志（sys_log）

### 3.2 E-R 关系说明
1. 一个系统用户可以对应一个或多个角色。
2. 一个儿童可以关联多个家长，一个家长也可关联多个儿童，二者通过中间表 `child_guardian_rel` 建立关联。
3. 一个儿童可拥有多条诊断记录、健康记录、康复训练记录、随访记录和评估记录。
4. 系统日志与操作用户之间为多对一关系。

### 3.3 数据库表设计与字段说明
详见 SQL 文件：`database/init.sql`。该脚本包含：
- 建库 SQL
- 建表 SQL
- 索引设计
- 初始化测试数据

#### 表设计亮点
- 统一使用 `BIGINT` 主键，便于后续扩展。
- 关键业务表均保留 `create_time`、`update_time` 字段。
- 部分表支持 `deleted` 逻辑删除，适合后台管理业务场景。
- 统计分析常用字段如 `record_date`、`training_date`、`follow_up_date`、`assessment_date` 均建立组合索引，便于后续按儿童与时间范围统计。

### 3.4 主键、外键、索引设计
- 主键：所有表使用自增主键 `id`。
- 外键：确保儿童与诊断、健康、训练、随访、评估等业务数据保持一致性。
- 索引：
  - `idx_health_child_date`
  - `idx_rehab_child_date`
  - `idx_follow_child_date`
  - `idx_assessment_child_date`
  - `idx_log_module_time`

### 3.5 数据库设计说明文档
#### 设计思路
1. **围绕儿童主档展开**：儿童档案作为系统核心实体，其他业务数据围绕儿童维度展开，便于构建完整的个案画像。
2. **兼顾业务与统计**：除基础 CRUD 字段外，特别增加日期字段和评分字段，为后续图表统计与趋势分析提供支持。
3. **适配毕业设计演示**：初始化测试数据具备一定真实性，便于直接用于答辩演示。
4. **保证扩展性**：角色、日志、监护人关系等均采用标准化设计，后续可扩展菜单权限、机构管理等功能。

---

## 四、第三部分：数据库本地启动方案

### 4.1 方式A：本地安装 MySQL 后手动执行 SQL 初始化

#### 步骤1：安装 MySQL 8.0
建议使用 MySQL Community Server 8.0，并确保字符集支持 utf8mb4。

#### 步骤2：启动 MySQL 服务
确保本地 MySQL 已正常启动，默认端口可使用 3306。

#### 步骤3：执行初始化脚本
```bash
mysql -uroot -p123456 < database/init.sql
```

如果你已经登录 MySQL，也可手动执行：
```sql
source /你的项目路径/database/init.sql;
```

#### 步骤4：检查库表
```sql
SHOW DATABASES;
USE autism_health_db;
SHOW TABLES;
SELECT * FROM sys_user;
```

### 4.2 方式B：Docker + Docker Compose 一键启动 MySQL
项目已提供 `docker-compose.yml`，执行以下命令即可：

```bash
docker compose up -d
```

查看容器状态：
```bash
docker compose ps
```

进入数据库：
```bash
docker exec -it autism-health-mysql mysql -uroot -p123456 autism_health_db
```

停止服务：
```bash
docker compose down
```

如需连同数据卷一起清理：
```bash
docker compose down -v
```

### 4.3 推荐数据库配置
- 数据库名称：`autism_health_db`
- 用户名：`root`
- 密码：`123456`
- 端口：`3306`
- MySQL 版本：`8.0`
- 字符集：`utf8mb4`

### 4.4 SQL 导入说明
- 手动方式：执行 `database/init.sql`。
- Docker 方式：容器首次启动会自动执行 `database/init.sql`。
- 若容器已启动过且数据目录不为空，初始化脚本不会自动再次执行，此时应先 `docker compose down -v` 后重新启动。

### 4.5 常见报错与解决方案
#### 问题1：`Access denied for user 'root'@'localhost'`
- 检查密码是否为 `123456`。
- 确认连接的是否为正确实例与端口。

#### 问题2：`Unknown database 'autism_health_db'`
- 说明初始化脚本未执行，请手动导入 `database/init.sql`。

#### 问题3：Docker 启动后表不存在
- 可能是数据库卷已存在，导致初始化脚本未重复执行。
- 解决方案：`docker compose down -v && docker compose up -d`。

#### 问题4：中文乱码
- 确保连接串包含 `useUnicode=true&characterEncoding=utf8`。
- 确保数据库与表字符集为 `utf8mb4`。

---

## 五、第四部分：后端项目设计与代码

### 5.1 后端标准目录结构
```text
backend
├─ pom.xml
├─ src/main/java/com/autismhealth
│  ├─ AutismHealthApplication.java
│  ├─ common
│  │  ├─ result
│  │  │  ├─ Result.java
│  │  │  └─ ResultCode.java
│  │  ├─ exception
│  │  │  ├─ BusinessException.java
│  │  │  └─ GlobalExceptionHandler.java
│  │  ├─ config
│  │  │  ├─ MybatisPlusConfig.java
│  │  │  ├─ JwtConfig.java
│  │  │  └─ SwaggerConfig.java
│  │  ├─ security
│  │  │  ├─ JwtTokenUtil.java
│  │  │  ├─ JwtAuthenticationFilter.java
│  │  │  └─ LoginUser.java
│  │  └─ annotation
│  │     └─ OperationLog.java
│  └─ modules
│     ├─ auth
│     ├─ user
│     ├─ child
│     ├─ parent
│     ├─ diagnosis
│     ├─ health
│     ├─ rehabilitation
│     ├─ followup
│     ├─ assessment
│     ├─ statistics
│     └─ log
│        ├─ entity
│        ├─ mapper
│        ├─ service
│        ├─ service/impl
│        └─ controller
└─ src/main/resources
   ├─ application.yml
   ├─ application-dev.yml
   └─ mapper
```

### 5.2 分层设计思路
每个业务模块按以下结构组织：
- `entity`：数据库实体类。
- `mapper`：MyBatis-Plus 数据访问接口。
- `service`：业务接口。
- `service/impl`：业务实现。
- `controller`：接口层。
- `dto/vo`：请求参数与视图对象。

### 5.3 统一返回结果类示例
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(500, message, null);
    }
}
```

### 5.4 全局异常处理示例
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(message);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.fail(e.getMessage());
    }
}
```

### 5.5 登录认证模块设计（JWT）
#### 登录流程
1. 用户输入用户名和密码。
2. 后端校验账号密码。
3. 校验通过后签发 JWT。
4. 前端保存 token 到 Pinia 与 LocalStorage。
5. 后续请求通过请求头 `Authorization: Bearer token` 传递。

#### 登录接口示例
- `POST /api/auth/login`
- 请求参数：`username`、`password`
- 返回：`token`、`userInfo`、`roleCode`

### 5.6 后端接口设计建议
#### 用户模块
- `GET /api/users`
- `POST /api/users`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`
- `GET /api/users/{id}`

#### 儿童档案模块
- `GET /api/children`
- `POST /api/children`
- `PUT /api/children/{id}`
- `DELETE /api/children/{id}`
- `GET /api/children/{id}`

#### 其他业务模块
按照相同 RESTful 风格统一设计，支持：
- 列表分页
- 条件搜索
- 新增
- 编辑
- 删除
- 详情查询

### 5.7 接口文档说明建议
可使用 Knife4j / OpenAPI 生成接口文档：
- 本地访问：`http://localhost:8080/api/doc.html`
- 按模块分组展示接口。
- 每个接口定义请求参数、返回结构、示例数据。

### 5.8 application 配置说明
项目已提供：
- `backend/src/main/resources/application.yml`
- `backend/src/main/resources/application-dev.yml`

配置中已包含：
- 服务端口
- Spring Profile
- MyBatis-Plus
- JWT
- MySQL 数据源模板
- 文件上传限制

---

## 六、第五部分：前端项目设计与代码

### 6.1 前端标准目录结构
```text
frontend
├─ package.json
├─ vite.config.js
├─ src
│  ├─ main.js
│  ├─ App.vue
│  ├─ api
│  │  ├─ request.js
│  │  ├─ auth.js
│  │  ├─ child.js
│  │  ├─ parent.js
│  │  ├─ diagnosis.js
│  │  ├─ health.js
│  │  ├─ rehabilitation.js
│  │  ├─ followup.js
│  │  ├─ assessment.js
│  │  ├─ statistics.js
│  │  ├─ user.js
│  │  └─ log.js
│  ├─ router
│  │  └─ index.js
│  ├─ store
│  │  └─ user.js
│  ├─ layout
│  │  └─ AdminLayout.vue
│  ├─ views
│  │  ├─ Login.vue
│  │  ├─ Dashboard.vue
│  │  ├─ child/ChildList.vue
│  │  ├─ parent/ParentList.vue
│  │  ├─ diagnosis/DiagnosisList.vue
│  │  ├─ health/HealthRecordList.vue
│  │  ├─ rehab/RehabList.vue
│  │  ├─ followup/FollowupList.vue
│  │  ├─ assessment/AssessmentList.vue
│  │  ├─ statistics/StatisticsView.vue
│  │  ├─ user/UserList.vue
│  │  └─ log/LogList.vue
│  ├─ components
│  │  ├─ SearchForm.vue
│  │  ├─ DataTable.vue
│  │  ├─ DetailDrawer.vue
│  │  └─ ChartCard.vue
│  └─ styles
│     └─ index.scss
```

### 6.2 页面设计风格说明
#### 设计思路
- 整体风格采用“现代后台管理系统 + 医疗信息平台”风格。
- 主色建议：蓝色、浅绿色、白色。
- 卡片式布局，留白充分，表单和表格统一圆角与阴影。
- 首页包含欢迎区、统计卡片、快捷入口、趋势图表。
- 登录页采用渐变背景 + 插画区 + 毛玻璃登录卡片。

### 6.3 页面功能要求
以下页面均至少包含：列表、搜索、新增、编辑、删除、详情查看。
- 登录页
- 首页仪表盘
- 儿童档案管理页
- 家长信息管理页
- 诊断信息管理页
- 健康记录管理页
- 康复训练记录页
- 随访记录页
- 评估记录页
- 统计分析页
- 用户管理页
- 系统日志页

### 6.4 Axios 请求模块示例
```javascript
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/store/user'

const service = axios.create({
  baseURL: '/api',
  timeout: 10000
})

service.interceptors.request.use(config => {
  const userStore = useUserStore()
  if (userStore.token) {
    config.headers.Authorization = `Bearer ${userStore.token}`
  }
  return config
})

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 200) {
      ElMessage.error(res.message || '请求失败')
      return Promise.reject(res)
    }
    return res
  },
  error => {
    ElMessage.error(error.message || '网络异常')
    return Promise.reject(error)
  }
)

export default service
```

### 6.5 Pinia 登录状态管理示例
```javascript
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: JSON.parse(localStorage.getItem('userInfo') || '{}')
  }),
  actions: {
    setLoginInfo(token, userInfo) {
      this.token = token
      this.userInfo = userInfo
      localStorage.setItem('token', token)
      localStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    logout() {
      this.token = ''
      this.userInfo = {}
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
    }
  }
})
```

### 6.6 ECharts 图表示例建议
统计分析页可设计以下图表：
1. 儿童数量与在管人数卡片。
2. 最近 7 日健康记录趋势折线图。
3. 各训练类型占比饼图。
4. 月度随访次数柱状图。
5. 评估总分趋势折线图。

---

## 七、第六部分：前后端联调说明

### 7.1 接口与页面对应关系
- 登录页 → `/api/auth/login`
- 用户管理页 → `/api/users`
- 儿童档案页 → `/api/children`
- 家长信息页 → `/api/parents`
- 诊断信息页 → `/api/diagnosis`
- 健康记录页 → `/api/health-records`
- 康复训练页 → `/api/rehabilitation`
- 随访页 → `/api/followups`
- 评估页 → `/api/assessments`
- 系统日志页 → `/api/logs`
- 统计分析页 → `/api/statistics`

### 7.2 前端如何调用接口
- 在 `src/api` 下按模块封装请求方法。
- 页面通过 `onMounted` 调用列表接口。
- 表单提交时调用新增或编辑接口。
- 删除按钮调用删除接口后刷新列表。

### 7.3 请求拦截器与响应拦截器
- 请求拦截器：自动附带 JWT Token。
- 响应拦截器：统一处理业务错误、登录失效、网络异常。

### 7.4 表单校验方案
前端使用 Element Plus `rules` 完成必填、长度、格式校验。

### 7.5 后端参数校验方案
- 使用 `@Validated`、`@NotBlank`、`@NotNull`、`@Size` 等注解。
- 异常通过全局异常处理器统一返回。

### 7.6 常见错误处理方式
- 401：未登录或 token 失效，跳转登录页。
- 403：无权限访问，提示并拦截。
- 500：系统异常，记录日志并友好提示。
- 参数错误：前后端分别校验，避免脏数据入库。

### 7.7 项目启动说明
#### 启动数据库
```bash
docker compose up -d
```
或：
```bash
mysql -uroot -p123456 < database/init.sql
```

#### 启动后端
```bash
cd backend
mvn spring-boot:run
```

#### 启动前端
```bash
cd frontend
npm install
npm run dev
```

---

## 八、第七部分：系统测试

### 8.1 功能测试方案
对登录、用户管理、儿童档案、健康记录、训练记录、随访记录、评估记录、统计分析等模块进行逐项测试，验证增删改查、分页、搜索、详情功能是否正常。

### 8.2 界面测试方案
检查页面布局、表单对齐、按钮交互、弹窗显示、表格换行、图表渲染、菜单切换、分辨率适配等。

### 8.3 数据库测试方案
验证：
- 初始化 SQL 是否可一次执行成功。
- 外键约束是否生效。
- 典型查询、统计查询是否正常。
- 时间范围查询、分页查询是否正确。

### 8.4 安全性测试方案
- 弱口令提示与账号状态控制。
- 登录鉴权与未授权接口拦截。
- 参数合法性校验。
- SQL 注入风险规避（MyBatis-Plus + 参数化查询）。

### 8.5 基础性能测试方案
- 列表接口分页压力测试。
- 统计接口在 1 万级模拟数据下的响应时间测试。
- 图表加载时间测试。

### 8.6 测试用例表示例
| 测试编号 | 测试模块 | 测试内容 | 输入 | 预期结果 |
|---|---|---|---|---|
| TC001 | 登录模块 | 正确账号密码登录 | admin / 123456 | 登录成功并跳转首页 |
| TC002 | 登录模块 | 错误密码登录 | admin / 111111 | 提示用户名或密码错误 |
| TC003 | 儿童档案 | 新增儿童档案 | 合法表单数据 | 新增成功，列表可见 |
| TC004 | 健康记录 | 查询某儿童健康记录 | childId=1 | 返回对应健康记录列表 |
| TC005 | 康复训练 | 删除训练记录 | id=1 | 删除成功，列表不再显示 |
| TC006 | 随访记录 | 条件搜索 | childName=张晨曦 | 返回匹配记录 |
| TC007 | 评估记录 | 查看详情 | id=1 | 成功显示评估详情 |
| TC008 | 统计分析 | 查看月度训练图表 | month=2025-03 | 图表正常渲染 |

### 8.7 测试结果说明
在本项目设计范围内，通过对核心模块执行功能测试、界面测试、数据库测试和安全性测试，可以验证系统满足本科毕业设计要求。若后续增加更多用户或更复杂的权限控制，可进一步引入接口自动化测试与性能测试工具进行增强验证。

---

## 九、数据库字段说明（可直接写入论文）

### 9.1 `sys_user` 表
用于保存系统登录用户信息，包括管理员、医生/康复师、家长账号信息。

### 9.2 `child_profile` 表
用于保存孤独症儿童的基础档案信息，是整套系统的核心业务主表。

### 9.3 `parent_info` 与 `child_guardian_rel` 表
用于实现儿童与家长/监护人多对多管理，支持主要监护人标记。

### 9.4 `diagnosis_info` 表
用于保存儿童诊断结果、诊断机构、等级与建议信息。

### 9.5 `health_record` 表
用于记录儿童每日或定期健康状况，支持后续健康趋势统计。

### 9.6 `rehabilitation_record` 表
用于记录康复训练内容与效果，为训练分析与评估提供依据。

### 9.7 `follow_up_record` 表
用于记录医生/康复师的随访情况以及家长反馈与干预建议。

### 9.8 `assessment_record` 表
用于保存各类量表或阶段评估结果，便于展示康复效果变化。

### 9.9 `sys_log` 表
用于记录系统关键操作日志，提高审计能力与系统可维护性。

---

## 十、推荐开发顺序
1. 先完成数据库设计与初始化。
2. 再完成 Spring Boot 基础工程、统一返回、异常处理、JWT 登录。
3. 按“儿童档案 → 家长信息 → 诊断信息 → 健康记录 → 康复训练 → 随访记录 → 评估记录 → 统计分析 → 系统日志”的顺序开发后端接口。
4. 前端先完成登录页、基础布局、菜单路由。
5. 再按相同业务顺序开发页面。
6. 最后完成统计图表、联调、测试、论文截图整理。

## 十一、推荐演示顺序
1. 登录系统，展示角色区分。
2. 展示首页仪表盘与统计卡片。
3. 展示儿童档案、家长信息、诊断信息。
4. 展示健康记录、康复训练、随访记录、评估记录的完整管理流程。
5. 展示统计分析图表。
6. 展示用户管理与系统日志，体现系统完整性。
7. 最后说明数据库部署方式与系统技术亮点。

---

## 十三、测试账号说明
| 角色 | 用户名 | 密码 | 说明 |
|---|---|---|---|
| 管理员 | admin | 123456 | 系统管理与全局查看 |
| 医生 | doctor01 | 123456 | 诊断、随访、评估管理 |
| 康复师 | therapist01 | 123456 | 训练记录维护 |
| 家长 | parent01 | 123456 | 儿童健康数据反馈 |
| 家长 | parent02 | 123456 | 另一组家长测试数据 |

## 十四、当前代码完成进度

- 已完成 **第三部分：数据库本地启动方案**。
- 已完成 **第四部分：后端项目设计与代码（第一阶段）**，目前已具备 Spring Boot 工程、统一返回、异常处理、登录接口，以及“用户管理、儿童档案管理”两个模块的 MyBatis-Plus 风格 CRUD 骨架。
- 已完成 **第五部分：前端项目设计与代码（第二阶段）**，目前已具备 Vue 3 管理端基础布局、登录页、仪表盘、统一 CRUD 页面、模块化 API 封装以及基于 ECharts 的统计图表展示。
- 下一步建议继续完成家长、诊断、健康、康复训练、随访、评估等模块的真实后端 Service/Mapper 与前端接口联调。

## 十四、已提供文件
- `database/init.sql`
- `docker-compose.yml`
- `backend/src/main/resources/application.yml`
- `backend/src/main/resources/application-dev.yml`
- `README.md`

以上文件可作为你后续继续扩展后端 Java 代码与前端 Vue 代码的基础模板。
