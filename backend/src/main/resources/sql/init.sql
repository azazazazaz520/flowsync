-- ================================================
-- FlowSync 数据库初始化脚本
-- ================================================

CREATE DATABASE IF NOT EXISTS flowsync_simple
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE flowsync_simple;

-- ===================== 用户表 =====================
DROP TABLE IF EXISTS task_summary;
DROP TABLE IF EXISTS task_log;
DROP TABLE IF EXISTS task_info;
DROP TABLE IF EXISTS project_info;
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user (
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(100) NOT NULL COMMENT '密码',
    real_name   VARCHAR(50)  NOT NULL COMMENT '真实姓名',
    role        VARCHAR(30)  NOT NULL COMMENT '负责人/成员/管理员',
    phone       VARCHAR(20)  COMMENT '联系电话',
    email       VARCHAR(100) COMMENT '电子邮箱',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB COMMENT='用户表';

-- ===================== 项目表 =====================
CREATE TABLE project_info (
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    name        VARCHAR(100) NOT NULL COMMENT '项目名称',
    description VARCHAR(500) COMMENT '项目说明',
    status      VARCHAR(20)  NOT NULL COMMENT '未开始/进行中/已完成',
    priority    VARCHAR(20)  NOT NULL COMMENT '低/中/高',
    owner_id    BIGINT       COMMENT '项目负责人ID',
    start_date  DATE         COMMENT '开始日期',
    end_date    DATE         COMMENT '结束日期',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (owner_id) REFERENCES sys_user(id)
) ENGINE=InnoDB COMMENT='项目表';

-- ===================== 任务表 =====================
CREATE TABLE task_info (
    id            BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    project_id    BIGINT        NOT NULL COMMENT '所属项目ID',
    parent_id     BIGINT        COMMENT '父任务ID',
    title         VARCHAR(100)  NOT NULL COMMENT '任务标题',
    description   VARCHAR(1000) COMMENT '任务说明',
    assignee_id   BIGINT        COMMENT '负责人ID',
    creator_id    BIGINT        COMMENT '创建人ID',
    status        VARCHAR(20)   NOT NULL COMMENT '未开始/进行中/已完成',
    priority      VARCHAR(20)   NOT NULL COMMENT '低/中/高',
    due_date      DATE          COMMENT '截止日期',
    ai_suggestion TEXT          COMMENT 'AI建议内容',
    create_time   DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (project_id)  REFERENCES project_info(id),
    FOREIGN KEY (parent_id)   REFERENCES task_info(id),
    FOREIGN KEY (assignee_id) REFERENCES sys_user(id),
    FOREIGN KEY (creator_id)  REFERENCES sys_user(id)
) ENGINE=InnoDB COMMENT='任务表';

-- ===================== 进度记录表 =====================
CREATE TABLE task_log (
    id               BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    task_id          BIGINT        NOT NULL COMMENT '关联任务ID',
    progress_percent INT           NOT NULL COMMENT '进度百分比 0-100',
    content          VARCHAR(1000) NOT NULL COMMENT '进度说明',
    operator_id      BIGINT        COMMENT '记录人ID',
    create_time      DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (task_id)     REFERENCES task_info(id),
    FOREIGN KEY (operator_id) REFERENCES sys_user(id)
) ENGINE=InnoDB COMMENT='进度记录表';

-- ===================== 总结表 =====================
CREATE TABLE task_summary (
    id           BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    project_id   BIGINT        NOT NULL COMMENT '所属项目ID',
    task_id      BIGINT        COMMENT '关联任务ID',
    summary_type VARCHAR(30)   NOT NULL COMMENT '阶段总结/最终总结',
    content      VARCHAR(2000) NOT NULL COMMENT '总结内容',
    created_by   BIGINT        COMMENT '创建人ID',
    create_time  DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (project_id) REFERENCES project_info(id),
    FOREIGN KEY (task_id)    REFERENCES task_info(id),
    FOREIGN KEY (created_by) REFERENCES sys_user(id)
) ENGINE=InnoDB COMMENT='总结表';

-- ===================== 测试数据 =====================
INSERT INTO sys_user (username, password, real_name, role) VALUES
('leader',  '123456', '项目负责人', '负责人'),
('member1', '123456', '张三',       '成员'),
('member2', '123456', '李四',       '成员');

INSERT INTO project_info (name, description, status, priority, owner_id, start_date, end_date) VALUES
('FlowSync 开发', '小学期课程项目', '进行中', '高', 1, '2026-07-01', '2026-07-31');

INSERT INTO task_info (project_id, title, description, assignee_id, creator_id, status, priority, due_date) VALUES
(1, '搭建后端框架', 'Spring Boot + MyBatis-Plus 项目初始化', 1, 1, '已完成', '高', '2026-07-05'),
(1, '搭建前端框架', 'Vue 3 + Element Plus 项目初始化', 2, 1, '进行中', '高', '2026-07-08'),
(1, '编写项目管理模块', '项目增删改查 + 前端面板', 3, 1, '未开始', '中', '2026-07-15');

INSERT INTO task_log (task_id, progress_percent, content, operator_id) VALUES
(1, 100, '后端框架搭建完成，所有依赖引入完毕', 1),
(2, 50,  '完成路由和布局搭建，待对接后端接口', 2);

INSERT INTO task_summary (project_id, task_id, summary_type, content, created_by) VALUES
(1, 1, '阶段总结', '后端框架搭建阶段顺利完成，采用 Spring Boot 3.3.5 + MyBatis-Plus 3.5.8', 1);
