# 本地联调常见问题排查

## 1. 儿童姓名等中文显示乱码
如果儿童档案中出现类似 `寮犳櫒曦` 这样的乱码，通常不是前端组件问题，而是 **MySQL 导入时连接字符集不一致**。

本项目现已统一为 `utf8mb4`：
- 数据库初始化脚本使用 `SET NAMES utf8mb4`
- Spring Boot JDBC 连接使用 `characterEncoding=utf8`，并通过 `connectionCollation=utf8mb4_unicode_ci` 配合数据库端 `utf8mb4` 字符集，避免 MySQL 驱动报 `Unsupported character encoding 'utf8mb4'`
- SQL 初始化显式声明 `UTF-8` 编码读取

如果你的库里已经写入了乱码数据，需要重新导入示例数据，旧数据不会自动恢复。推荐步骤：

```sql
DROP DATABASE IF EXISTS autism_health_db;
CREATE DATABASE autism_health_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

然后重新启动后端，或重新执行：

```bash
mysql --default-character-set=utf8mb4 -uroot -p123456 < database/init.sql
```

## 2. 前端换端口后请求失败
如果你不是从 `5173` 或 `4173` 启动前端，而是用了别的本地端口，旧配置可能会因为 CORS 拒绝请求。

项目现已放宽为：
- `http://localhost:*`
- `http://127.0.0.1:*`
- `http://[::1]:*`

适用于常见本地开发端口切换。

## 3. 仍提示“后端服务处理请求失败”
请优先检查：
1. Spring Boot 是否实际启动在 `http://127.0.0.1:8080`
2. 前端代理目标是否仍指向 `VITE_BACKEND_TARGET=http://127.0.0.1:8080`
3. 浏览器开发者工具 Network 面板里真实返回的 HTTP 状态码
4. 后端控制台是否有 SQL、参数校验或跨域日志
