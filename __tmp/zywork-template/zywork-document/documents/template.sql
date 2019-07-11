CREATE DATABASE IF NOT EXISTS template DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use template;

DROP TABLE IF EXISTS t_template;
CREATE TABLE t_template(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  name varchar(30) NOT NULL COMMENT '姓名',
  password varchar(50) NOT NULL COMMENT '密码'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/**角色表*/
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  title VARCHAR(50) NOT NULL COMMENT '标题',
  description VARCHAR(200) COMMENT '描述',
  create_time DATETIME DEFAULT now() COMMENT '创建时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/**用户表*/
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  email VARCHAR(100) COMMENT '邮箱',
  phone VARCHAR(11) COMMENT '手机号',
  account_name VARCHAR(20) COMMENT '账户名',
  password VARCHAR(50) NOT NULL COMMENT '密码',
  create_time DATETIME DEFAULT now() COMMENT '创建时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/**模块表*/
DROP TABLE IF EXISTS t_module;
CREATE TABLE t_module(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  title VARCHAR(50) NOT NULL COMMENT '标题',
  description VARCHAR(200) COMMENT '描述',
  create_time DATETIME DEFAULT now() COMMENT '创建时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/**权限表*/
DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  title VARCHAR(50) NOT NULL COMMENT '标题',
  permission VARCHAR(100) NOT NULL COMMENT '权限字符串',
  description VARCHAR(200) COMMENT '描述',
  module_id BIGINT NOT NULL COMMENT '所属模块',
  create_time DATETIME DEFAULT now() COMMENT '创建时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE t_permission ADD CONSTRAINT fk_permission_module_id
FOREIGN KEY (module_id) REFERENCES t_module(id);

/**用户角色表*/
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  user_id BIGINT NOT NULL COMMENT '用户编号',
  role_id BIGINT NOT NULL COMMENT '角色编号',
  create_time DATETIME DEFAULT now() COMMENT '创建时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE t_user_role ADD CONSTRAINT fk_user_role_user_id
FOREIGN KEY (user_id) REFERENCES t_user(id);

ALTER TABLE t_user_role ADD CONSTRAINT fk_user_role_rold_id
FOREIGN KEY (role_id) REFERENCES t_role(id);

/**角色权限表*/
DROP TABLE IF EXISTS t_role_permission;
CREATE TABLE t_role_permission(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  role_id BIGINT NOT NULL COMMENT '角色编号',
  permission_id BIGINT NOT NULL COMMENT '权限编号',
  create_time DATETIME DEFAULT now() COMMENT '创建时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE t_role_permission ADD CONSTRAINT fk_role_permission_rold_id
FOREIGN KEY (role_id) REFERENCES t_role(id);

ALTER TABLE t_role_permission ADD CONSTRAINT fk_role_permission_per_id
FOREIGN KEY (permission_id) REFERENCES t_permission(id);

/**流程记录表*/
DROP TABLE IF EXISTS t_process;
CREATE TABLE t_process(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  name VARCHAR(200) NOT NULL COMMENT '流程名字',
  path VARCHAR(500) NOT NULL COMMENT '流程文件路径',
  description VARCHAR(500) COMMENT '流程描述',
  user_id BIGINT NOT NULL COMMENT '上传人编号',
  create_time DATETIME DEFAULT now() COMMENT '上传时间',
  is_deploy TINYINT DEFAULT 0 COMMENT '是否部署',
  deploy_time DATETIME COMMENT '部署时间',
  is_active TINYINT DEFAULT 0 COMMENT '是否激活'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE t_process ADD CONSTRAINT fk_process_user_id
FOREIGN KEY (user_id) REFERENCES t_user(id);