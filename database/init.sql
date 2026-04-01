-- 孤独症儿童健康信息管理系统数据库初始化脚本
-- MySQL 8.0 / utf8mb4

CREATE DATABASE IF NOT EXISTS autism_health_db
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE autism_health_db;

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

INSERT INTO sys_role (id, role_name, role_code, description) VALUES
(1, '系统管理员', 'admin', '负责用户、权限、系统配置与日志管理'),
(2, '医生/康复师', 'doctor', '负责儿童档案、诊断、健康、训练、随访与评估管理'),
(3, '家长', 'parent', '负责查看儿童信息、反馈健康与配合随访');

INSERT INTO sys_user (id, username, password, real_name, gender, phone, email, role_code, status, last_login_time, remark) VALUES
(1, 'admin', '123456', '系统管理员', 1, '13800000001', 'admin@autismhealth.com', 'admin', 1, NOW(), '默认管理员账号'),
(2, 'doctor01', '123456', '李医生', 1, '13800000002', 'doctor01@autismhealth.com', 'doctor', 1, NOW(), '儿童发育行为科医生'),
(3, 'therapist01', '123456', '王康复师', 2, '13800000003', 'therapist01@autismhealth.com', 'doctor', 1, NOW(), '感觉统合训练师'),
(4, 'parent01', '123456', '张敏', 2, '13800000004', 'parent01@autismhealth.com', 'parent', 1, NOW(), '家长测试账号'),
(5, 'parent02', '123456', '刘强', 1, '13800000005', 'parent02@autismhealth.com', 'parent', 1, NOW(), '家长测试账号');

INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1), (2, 2), (3, 2), (4, 3), (5, 3);

INSERT INTO parent_info (id, user_id, parent_name, gender, relation_type, phone, wechat, occupation, education_level, address, emergency_contact, emergency_phone, remark) VALUES
(1, 4, '张敏', 2, '母亲', '13800000004', 'zm_parent', '会计', '本科', '杭州市西湖区文三路88号', '张勇', '13800010001', '主要照护人'),
(2, NULL, '张勇', 1, '父亲', '13800010001', 'zy_parent', '工程师', '本科', '杭州市西湖区文三路88号', '张敏', '13800000004', '工作日接送'),
(3, 5, '刘强', 1, '父亲', '13800000005', 'lq_parent', '教师', '硕士', '宁波市海曙区中山西路66号', '陈芳', '13800010002', '主要照护人'),
(4, NULL, '陈芳', 2, '母亲', '13800010002', 'cf_parent', '护士', '本科', '宁波市海曙区中山西路66号', '刘强', '13800000005', '配合家庭训练');

INSERT INTO child_profile (id, child_no, child_name, gender, birth_date, id_card, blood_type, height, weight, allergy_history, family_medical_history, autism_level, school_name, address, admission_date, status, remark, create_by, update_by) VALUES
(1, 'CH20250001', '张晨曦', 1, '2018-05-12', '330106201805120011', 'A', 112.50, 20.50, '花粉轻微过敏', '无明显家族遗传病史', '中度', '星光融合幼儿园', '杭州市西湖区文三路88号', '2025-01-10', 1, '语言发展迟缓，需持续跟踪', 2, 2),
(2, 'CH20250002', '刘雨桐', 2, '2017-11-03', '330203201711030022', 'O', 118.20, 22.80, '无', '父系家族有注意力障碍史', '轻度', '海曙区启智学校', '宁波市海曙区中山西路66号', '2025-01-15', 1, '社交互动改善明显', 2, 2),
(3, 'CH20250003', '王子轩', 1, '2019-02-18', '330102201902180033', 'B', 108.30, 19.10, '牛奶蛋白过敏', '无', '重度', '居家康复', '杭州市上城区庆春路18号', '2025-02-02', 1, '情绪波动较明显', 2, 3);

INSERT INTO child_guardian_rel (child_id, parent_id, is_primary) VALUES
(1, 1, 1), (1, 2, 0),
(2, 3, 1), (2, 4, 0),
(3, 2, 1);

INSERT INTO diagnosis_info (child_id, diagnosis_date, diagnosis_hospital, diagnosis_doctor, diagnosis_result, autism_level, comorbidity, suggestion, create_by) VALUES
(1, '2024-12-20', '杭州市儿童医院', '李建华', '符合孤独症谱系障碍诊断标准', '中度', '轻度语言发育迟缓', '建议每周开展3次语言训练和2次感觉统合训练', 2),
(2, '2024-11-15', '宁波市妇女儿童医院', '周敏', '符合孤独症谱系障碍诊断标准', '轻度', '注意力维持时间偏短', '建议加强社交情景训练与家庭协同干预', 2),
(3, '2025-01-05', '浙江大学医学院附属儿童医院', '陈立', '符合孤独症谱系障碍诊断标准', '重度', '感觉统合失调', '建议建立个体化干预方案并缩短随访周期', 2);

INSERT INTO health_record (child_id, record_date, temperature, heart_rate, sleep_hours, appetite_level, emotion_state, bowel_status, allergy_reaction, abnormal_symptom, care_advice, recorded_by) VALUES
(1, '2025-03-01', 36.5, 92, 9.0, '良好', '平稳', '正常', '无', '无', '保持规律作息', 4),
(1, '2025-03-08', 36.7, 95, 8.5, '一般', '轻微烦躁', '正常', '无', '晨间抗拒训练', '增加过渡性引导', 4),
(2, '2025-03-02', 36.4, 88, 9.5, '良好', '积极', '正常', '无', '无', '继续保持家庭互动训练', 5),
(2, '2025-03-09', 36.6, 90, 8.8, '良好', '平稳', '正常', '无', '无', '维持当前饮食结构', 5),
(3, '2025-03-03', 36.8, 98, 7.5, '一般', '波动较大', '偏干', '轻微皮疹', '午后哭闹明显', '关注过敏源并记录触发因素', 2);

INSERT INTO rehabilitation_record (child_id, trainer_id, training_date, training_type, training_theme, duration_minutes, participation_level, performance_summary, training_goal, next_plan) VALUES
(1, 3, '2025-03-03', '语言训练', '主动表达练习', 45, '较高', '可在提示下完成两词句表达', '提升主动表达频率', '下次增加图片交换训练'),
(1, 3, '2025-03-10', '感觉统合训练', '平衡与前庭刺激', 50, '中等', '前半程配合较好，后半程注意力下降', '改善身体协调性', '配合家庭平衡木练习'),
(2, 3, '2025-03-04', '社交训练', '轮流与等待', 40, '较高', '能够在规则提醒下完成轮流游戏', '增强社交规则意识', '增加同伴互动情境'),
(2, 2, '2025-03-11', '认知训练', '颜色与分类', 45, '高', '分类正确率达到85%', '提高认知分类能力', '增加复杂分类任务'),
(3, 3, '2025-03-06', '感觉统合训练', '触觉脱敏', 35, '中等', '对沙盘材料接触时间明显延长', '降低触觉敏感', '继续渐进式暴露训练');

INSERT INTO follow_up_record (child_id, follow_up_date, follow_up_method, follow_up_person, recovery_progress, medication_status, family_feedback, problem_found, handling_advice, next_follow_up_date) VALUES
(1, '2025-03-05', '电话', 2, '语言表达有小幅提升', '未使用药物', '家庭配合度较高，希望增加家庭训练指导', '训练转换阶段情绪波动', '建议使用视觉日程卡进行过渡', '2025-03-20'),
(2, '2025-03-06', '面访', 2, '社交回应能力提升明显', '未使用药物', '孩子在集体活动中主动性增强', '课堂专注持续时间不足', '建议缩短任务时长并分段强化', '2025-03-21'),
(3, '2025-03-07', '视频', 3, '感觉统合训练有进步', '医生建议观察后再决定是否干预', '家长反映夜间入睡困难', '睡眠规律性差', '建议固定睡前流程并减少屏幕刺激', '2025-03-18');

INSERT INTO assessment_record (child_id, assessment_date, assessment_type, evaluator_id, language_score, social_score, behavior_score, cognition_score, sensory_score, total_score, assessment_conclusion, intervention_suggestion) VALUES
(1, '2025-03-12', '阶段评估', 2, 68.00, 62.00, 70.00, 66.00, 64.00, 330.00, '语言与社交能力稳步提升，仍需强化主动沟通', '建议继续语言训练并增加同伴互动活动'),
(2, '2025-03-12', '阶段评估', 2, 75.00, 78.00, 74.00, 80.00, 72.00, 379.00, '整体发展较为平稳，社交主动性增强', '建议继续社交情景训练与注意力管理'),
(3, '2025-03-13', '入组评估', 3, 50.00, 46.00, 55.00, 58.00, 48.00, 257.00, '感觉统合与情绪调节能力相对薄弱', '建议提高训练频率并加强家庭协同');

INSERT INTO sys_log (user_id, username, module_name, operation_type, request_method, request_uri, operation_desc, ip_address, execution_time, operation_status, error_message) VALUES
(1, 'admin', '用户管理', '新增', 'POST', '/api/users', '新增医生账号doctor01', '127.0.0.1', 32, 1, NULL),
(2, 'doctor01', '儿童档案管理', '查询', 'GET', '/api/children', '分页查询儿童档案', '127.0.0.1', 21, 1, NULL),
(2, 'doctor01', '诊断信息管理', '新增', 'POST', '/api/diagnosis', '新增张晨曦诊断记录', '127.0.0.1', 36, 1, NULL),
(3, 'therapist01', '康复训练记录管理', '新增', 'POST', '/api/rehabilitation', '新增王子轩训练记录', '127.0.0.1', 40, 1, NULL),
(4, 'parent01', '健康记录管理', '新增', 'POST', '/api/health-records', '提交张晨曦健康记录', '127.0.0.1', 28, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
