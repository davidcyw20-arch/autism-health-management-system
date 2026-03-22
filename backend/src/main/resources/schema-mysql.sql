-- 孤独症儿童健康信息管理系统数据库初始化脚本
-- MySQL 8.0 / utf8mb4



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS sys_log;
DROP TABLE IF EXISTS assessment_record;
DROP TABLE IF EXISTS follow_up_record;
DROP TABLE IF EXISTS rehabilitation_record;
DROP TABLE IF EXISTS health_record;
DROP TABLE IF EXISTS diagnosis_info;
DROP TABLE IF EXISTS child_guardian_rel;
DROP TABLE IF EXISTS parent_info;
DROP TABLE IF EXISTS child_profile;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录用户名',
  password VARCHAR(255) NOT NULL COMMENT '登录密码（建议存储加密后的哈希值）',
  real_name VARCHAR(50) NOT NULL COMMENT '真实姓名',
  gender TINYINT DEFAULT 0 COMMENT '性别：0未知 1男 2女',
  phone VARCHAR(20) DEFAULT NULL COMMENT '手机号',
  email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  role_code VARCHAR(30) NOT NULL COMMENT '角色编码：admin/doctor/parent',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0禁用 1启用',
  last_login_time DATETIME DEFAULT NULL COMMENT '最后登录时间',
  remark VARCHAR(255) DEFAULT NULL COMMENT '备注',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0否 1是',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='系统用户表';

CREATE TABLE sys_role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  role_name VARCHAR(50) NOT NULL COMMENT '角色名称',
  role_code VARCHAR(30) NOT NULL UNIQUE COMMENT '角色编码',
  description VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='系统角色表';

CREATE TABLE sys_user_role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  user_id BIGINT NOT NULL COMMENT '用户ID',
  role_id BIGINT NOT NULL COMMENT '角色ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES sys_user(id),
  CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES sys_role(id)
) COMMENT='用户角色关联表';

CREATE TABLE child_profile (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_no VARCHAR(32) NOT NULL UNIQUE COMMENT '儿童档案编号',
  child_name VARCHAR(50) NOT NULL COMMENT '儿童姓名',
  gender TINYINT NOT NULL COMMENT '性别：1男 2女',
  birth_date DATE NOT NULL COMMENT '出生日期',
  id_card VARCHAR(32) DEFAULT NULL COMMENT '证件号码',
  blood_type VARCHAR(10) DEFAULT NULL COMMENT '血型',
  height DECIMAL(5,2) DEFAULT NULL COMMENT '身高(cm)',
  weight DECIMAL(5,2) DEFAULT NULL COMMENT '体重(kg)',
  allergy_history VARCHAR(255) DEFAULT NULL COMMENT '过敏史',
  family_medical_history VARCHAR(255) DEFAULT NULL COMMENT '家族病史',
  autism_level VARCHAR(30) DEFAULT NULL COMMENT '孤独症程度',
  school_name VARCHAR(100) DEFAULT NULL COMMENT '就读学校或机构',
  address VARCHAR(255) DEFAULT NULL COMMENT '家庭住址',
  admission_date DATE DEFAULT NULL COMMENT '建档日期',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1在管 0结档',
  remark VARCHAR(255) DEFAULT NULL COMMENT '备注',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0否 1是',
  create_by BIGINT DEFAULT NULL COMMENT '创建人ID',
  update_by BIGINT DEFAULT NULL COMMENT '更新人ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='儿童档案表';

CREATE TABLE parent_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  user_id BIGINT DEFAULT NULL COMMENT '关联用户ID',
  parent_name VARCHAR(50) NOT NULL COMMENT '家长姓名',
  gender TINYINT DEFAULT 0 COMMENT '性别：0未知 1男 2女',
  relation_type VARCHAR(20) NOT NULL COMMENT '与儿童关系：父亲/母亲/祖父母/监护人',
  phone VARCHAR(20) NOT NULL COMMENT '联系电话',
  wechat VARCHAR(50) DEFAULT NULL COMMENT '微信号',
  occupation VARCHAR(50) DEFAULT NULL COMMENT '职业',
  education_level VARCHAR(50) DEFAULT NULL COMMENT '学历',
  address VARCHAR(255) DEFAULT NULL COMMENT '居住地址',
  emergency_contact VARCHAR(50) DEFAULT NULL COMMENT '紧急联系人',
  emergency_phone VARCHAR(20) DEFAULT NULL COMMENT '紧急联系电话',
  remark VARCHAR(255) DEFAULT NULL COMMENT '备注',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0否 1是',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT fk_parent_user FOREIGN KEY (user_id) REFERENCES sys_user(id)
) COMMENT='家长信息表';

CREATE TABLE child_guardian_rel (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_id BIGINT NOT NULL COMMENT '儿童ID',
  parent_id BIGINT NOT NULL COMMENT '家长ID',
  is_primary TINYINT NOT NULL DEFAULT 0 COMMENT '是否主要监护人：0否 1是',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  CONSTRAINT fk_child_guardian_child FOREIGN KEY (child_id) REFERENCES child_profile(id),
  CONSTRAINT fk_child_guardian_parent FOREIGN KEY (parent_id) REFERENCES parent_info(id)
) COMMENT='儿童与监护人关联表';

CREATE TABLE diagnosis_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_id BIGINT NOT NULL COMMENT '儿童ID',
  diagnosis_date DATE NOT NULL COMMENT '诊断日期',
  diagnosis_hospital VARCHAR(100) NOT NULL COMMENT '诊断机构',
  diagnosis_doctor VARCHAR(50) DEFAULT NULL COMMENT '诊断医生',
  diagnosis_result VARCHAR(255) NOT NULL COMMENT '诊断结果',
  autism_level VARCHAR(30) DEFAULT NULL COMMENT '诊断等级',
  comorbidity VARCHAR(255) DEFAULT NULL COMMENT '共病情况',
  suggestion VARCHAR(500) DEFAULT NULL COMMENT '诊疗建议',
  attachment_url VARCHAR(255) DEFAULT NULL COMMENT '附件地址',
  create_by BIGINT DEFAULT NULL COMMENT '创建人ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT fk_diagnosis_child FOREIGN KEY (child_id) REFERENCES child_profile(id)
) COMMENT='诊断信息表';

CREATE TABLE health_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_id BIGINT NOT NULL COMMENT '儿童ID',
  record_date DATE NOT NULL COMMENT '记录日期',
  temperature DECIMAL(4,1) DEFAULT NULL COMMENT '体温(℃)',
  heart_rate INT DEFAULT NULL COMMENT '心率(次/分)',
  sleep_hours DECIMAL(4,1) DEFAULT NULL COMMENT '睡眠时长(小时)',
  appetite_level VARCHAR(20) DEFAULT NULL COMMENT '食欲情况',
  emotion_state VARCHAR(50) DEFAULT NULL COMMENT '情绪状态',
  bowel_status VARCHAR(50) DEFAULT NULL COMMENT '排便情况',
  allergy_reaction VARCHAR(255) DEFAULT NULL COMMENT '过敏反应',
  abnormal_symptom VARCHAR(255) DEFAULT NULL COMMENT '异常症状',
  care_advice VARCHAR(255) DEFAULT NULL COMMENT '护理建议',
  recorded_by BIGINT DEFAULT NULL COMMENT '记录人ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT fk_health_child FOREIGN KEY (child_id) REFERENCES child_profile(id)
) COMMENT='健康记录表';

CREATE TABLE rehabilitation_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_id BIGINT NOT NULL COMMENT '儿童ID',
  trainer_id BIGINT DEFAULT NULL COMMENT '康复师ID',
  training_date DATE NOT NULL COMMENT '训练日期',
  training_type VARCHAR(50) NOT NULL COMMENT '训练类型',
  training_theme VARCHAR(100) DEFAULT NULL COMMENT '训练主题',
  duration_minutes INT NOT NULL COMMENT '训练时长（分钟）',
  participation_level VARCHAR(20) DEFAULT NULL COMMENT '参与度',
  performance_summary VARCHAR(500) DEFAULT NULL COMMENT '训练表现',
  training_goal VARCHAR(255) DEFAULT NULL COMMENT '训练目标',
  next_plan VARCHAR(255) DEFAULT NULL COMMENT '下次计划',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT fk_rehab_child FOREIGN KEY (child_id) REFERENCES child_profile(id),
  CONSTRAINT fk_rehab_trainer FOREIGN KEY (trainer_id) REFERENCES sys_user(id)
) COMMENT='康复训练记录表';

CREATE TABLE follow_up_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_id BIGINT NOT NULL COMMENT '儿童ID',
  follow_up_date DATE NOT NULL COMMENT '随访日期',
  follow_up_method VARCHAR(30) NOT NULL COMMENT '随访方式：电话/面访/视频',
  follow_up_person BIGINT DEFAULT NULL COMMENT '随访人ID',
  recovery_progress VARCHAR(255) DEFAULT NULL COMMENT '康复进展',
  medication_status VARCHAR(255) DEFAULT NULL COMMENT '用药情况',
  family_feedback VARCHAR(500) DEFAULT NULL COMMENT '家长反馈',
  problem_found VARCHAR(255) DEFAULT NULL COMMENT '发现问题',
  handling_advice VARCHAR(255) DEFAULT NULL COMMENT '处理建议',
  next_follow_up_date DATE DEFAULT NULL COMMENT '下次随访日期',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT fk_follow_child FOREIGN KEY (child_id) REFERENCES child_profile(id),
  CONSTRAINT fk_follow_user FOREIGN KEY (follow_up_person) REFERENCES sys_user(id)
) COMMENT='随访记录表';

CREATE TABLE assessment_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  child_id BIGINT NOT NULL COMMENT '儿童ID',
  assessment_date DATE NOT NULL COMMENT '评估日期',
  assessment_type VARCHAR(50) NOT NULL COMMENT '评估类型',
  evaluator_id BIGINT DEFAULT NULL COMMENT '评估人ID',
  language_score DECIMAL(5,2) DEFAULT NULL COMMENT '语言能力得分',
  social_score DECIMAL(5,2) DEFAULT NULL COMMENT '社交能力得分',
  behavior_score DECIMAL(5,2) DEFAULT NULL COMMENT '行为能力得分',
  cognition_score DECIMAL(5,2) DEFAULT NULL COMMENT '认知能力得分',
  sensory_score DECIMAL(5,2) DEFAULT NULL COMMENT '感觉统合得分',
  total_score DECIMAL(6,2) DEFAULT NULL COMMENT '总分',
  assessment_conclusion VARCHAR(500) DEFAULT NULL COMMENT '评估结论',
  intervention_suggestion VARCHAR(500) DEFAULT NULL COMMENT '干预建议',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT fk_assessment_child FOREIGN KEY (child_id) REFERENCES child_profile(id),
  CONSTRAINT fk_assessment_user FOREIGN KEY (evaluator_id) REFERENCES sys_user(id)
) COMMENT='评估记录表';

CREATE TABLE sys_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  user_id BIGINT DEFAULT NULL COMMENT '操作用户ID',
  username VARCHAR(50) DEFAULT NULL COMMENT '操作用户名',
  module_name VARCHAR(50) NOT NULL COMMENT '模块名称',
  operation_type VARCHAR(30) NOT NULL COMMENT '操作类型',
  request_method VARCHAR(10) DEFAULT NULL COMMENT '请求方法',
  request_uri VARCHAR(255) DEFAULT NULL COMMENT '请求地址',
  operation_desc VARCHAR(255) DEFAULT NULL COMMENT '操作描述',
  ip_address VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
  execution_time INT DEFAULT NULL COMMENT '执行耗时(ms)',
  operation_status TINYINT NOT NULL DEFAULT 1 COMMENT '操作状态：0失败 1成功',
  error_message VARCHAR(500) DEFAULT NULL COMMENT '异常信息',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  CONSTRAINT fk_log_user FOREIGN KEY (user_id) REFERENCES sys_user(id)
) COMMENT='系统日志表';

CREATE INDEX idx_user_role_code ON sys_user(role_code);
CREATE INDEX idx_child_name ON child_profile(child_name);
CREATE INDEX idx_child_status ON child_profile(status);
CREATE INDEX idx_parent_phone ON parent_info(phone);
CREATE INDEX idx_diagnosis_child_date ON diagnosis_info(child_id, diagnosis_date);
CREATE INDEX idx_health_child_date ON health_record(child_id, record_date);
CREATE INDEX idx_rehab_child_date ON rehabilitation_record(child_id, training_date);
CREATE INDEX idx_follow_child_date ON follow_up_record(child_id, follow_up_date);
CREATE INDEX idx_assessment_child_date ON assessment_record(child_id, assessment_date);
CREATE INDEX idx_log_module_time ON sys_log(module_name, create_time);
