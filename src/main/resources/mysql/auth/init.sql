DROP TABLE IF EXISTS AUTH_USER;
DROP TABLE IF EXISTS AUTH_ROLE;
DROP TABLE IF EXISTS AUTH_RESOURCE;
DROP TABLE IF EXISTS AUTH_PERMISSION;
DROP TABLE IF EXISTS AUTH_AUTHORIZATION;

# 用户表。 CDT - 创建日期时间； UDT - 更新日期时间。以下略
CREATE TABLE IF NOT EXISTS AUTH_USER (
  ID       INT(10)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME     VARCHAR(30)  NOT NULL COMMENT '用户名',
  PASSWORD VARCHAR(100) NOT NULL,
  CDT      DATETIME     NOT NULL,
  UDT      DATETIME     NOT NULL,
  UNIQUE KEY IDX_AUTH_USER(NAME)
)
  ENGINE = INNODB
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

# 角色表。
CREATE TABLE IF NOT EXISTS AUTH_ROLE (
  ID   INT(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(30) NOT NULL,
  CDT  DATETIME    NOT NULL,
  UDT  DATETIME    NOT NULL,
  UNIQUE KEY IDX_AUTH_ROLE(NAME)
)
  ENGINE = INNODB
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

# 资源表。
# CATEGORY意思是资源类型。d - 目录。 m - 菜单。 a - 表示操作。
# SEQ表示次序。比如菜单有一定的排列顺序。
# URI表示统一资源描述符。菜单按钮用URL来表示。如果是数据权限，用java类方法来解决
CREATE TABLE IF NOT EXISTS AUTH_RESOURCE (
  ID       INT(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PID      INT(10)              DEFAULT NULL,
  NAME     VARCHAR(30) NOT NULL,
  CATEGORY CHAR(1)     NOT NULL,
  SEQ      TINYINT(4)           DEFAULT NULL,
  URI      VARCHAR(100)         DEFAULT NULL,
  CDT      DATETIME    NOT NULL,
  UDT      DATETIME    NOT NULL
)
  ENGINE = INNODB
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

# 权限表。
CREATE TABLE IF NOT EXISTS AUTH_PERMISSION (
  ID      INT(10)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  RES_ID  INT(10)  NOT NULL,
  ROLE_ID INT(10)  NOT NULL,
  CDT     DATETIME NOT NULL,
  UDT     DATETIME NOT NULL,
  UNIQUE KEY IDX_AUTH_PERMISSION(RES_ID, ROLE_ID)
)
  ENGINE = INNODB
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

# 授权表。
CREATE TABLE IF NOT EXISTS AUTH_AUTHORIZATION (
  ID      INT(10)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  USER_ID INT(10)  NOT NULL,
  ROLE_ID INT(10)  NOT NULL,
  CDT     DATETIME NOT NULL,
  UDT     DATETIME NOT NULL,
  UNIQUE KEY IDX_AUTH_AUTHORIZATION(USER_ID, ROLE_ID)
)
  ENGINE = INNODB
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

# 初始化auth_user表
INSERT INTO `auth_user` VALUES
  (20, 'root', '$shiro1$SHA-256$500000$T+i9nlwsVckQ+GGpA/AUmQ==$pxx6Nbk272Lg3Fc7iBJtl8hTo6Wmoe+LzqdiAeGSv+Y=', now( ), now( ));

# 初始化auth_role表
INSERT INTO `auth_role` VALUES (1, '系统管理员', now( ), now( ));

# 初始化auth_resource表
INSERT INTO `auth_resource`
VALUES (1, 0, '系统管理', 'd', 1, '', now( ), now( )), (2, 1, '权限管理', 'm', 1, '/auth/main', now( ), now( )),
  (46, 2, '角色查询', 'a', NULL, '/auth/role/proto/index', now( ), now( )),
  (47, 2, '资源查询', 'a', NULL, '/auth/resource/proto/index', now( ), now( )),
  (48, 2, '用户查询', 'a', NULL, '/auth/user/proto/index', now( ), now( )),
  (49, 2, '用户添加', 'a', NULL, '/auth/user/proto/post', now( ), now( )),
  (50, 2, '角色添加', 'a', NULL, '/auth/role/proto/post', now( ), now( )),
  (51, 2, '资源添加', 'a', NULL, '/auth/resource/proto/post', now( ), now( )),
  (52, 2, '用户查看', 'a', NULL, '/auth/user/proto/get', now( ), now( )),
  (53, 2, '角色查看', 'a', NULL, '/auth/role/proto/get', now( ), now( )),
  (54, 2, '资源查看', 'a', NULL, '/auth/resource/proto/get', now( ), now( )),
  (55, 2, '用户修改', 'a', NULL, '/auth/user/proto/put', now( ), now( )),
  (56, 2, '角色修改', 'a', NULL, '/auth/role/proto/put', now( ), now( )),
  (57, 2, '资源修改', 'a', NULL, '/auth/resource/proto/put', now( ), now( )),
  (58, 2, '用户删除', 'a', NULL, '/auth/user/proto/delete', now( 角色分配资源), now( )),
  (59, 2, '角色删除', 'a', NULL, '/auth/role/proto/delete', now( ), now( )),
  (60, 2, '资源删除', 'a', NULL, '/auth/resource/proto/delete', now( ), now( )),
  (61, 2, '用户批量删除', 'a', NULL, '/auth/user/proto/deleteList', now( ), now( )),
  (62, 2, '角色批量删除', 'a', NULL, '/auth/role/proto/deleteList', now( ), now( )),
  (63, 2, '分配角色查询', 'a', NULL, '/auth/authz/get', now( ), now( )),
  (64, 2, '分配资源查询', 'a', NULL, '/auth/permission/get', now( ), now( )),
  (66, 2, '用户分配角色', 'a', NULL, '/auth/authz/authz', now( ), now( )),
  (67, 2, '角色分配资源', 'a', NULL, '/auth/permission/authz', now( ), now( )),
  (68, 2, '角色授权', 'a', NULL, '/auth/authz', now( ), now( ));

# 初始化auth_authorization
INSERT INTO `auth_authorization` VALUES (17, 20, 1, now( ), now( ));

# 初始化auth_permission
INSERT INTO `auth_permission`
VALUES (234, 1, 1, now( ), now( )), (235, 2, 1, now( ), now( )), (236, 67, 1, now( ), now( )),
  (237, 57, 1, now( ), now( )), (238, 58, 1, now( ), now( )), (239, 59, 1, now( ), now( )),
  (240, 60, 1, now( ), now( )), (241, 61, 1, now( ), now( )), (242, 62, 1, now( ), now( )),
  (243, 63, 1, now( ), now( )), (244, 64, 1, now( ), now( )), (245, 66, 1, now( ), now( )),
  (246, 56, 1, now( ), now( )), (247, 55, 1, now( ), now( )), (248, 54, 1, now( ), now( )),
  (249, 46, 1, now( ), now( )), (250, 47, 1, now( ), now( )), (251, 48, 1, now( ), now( )),
  (252, 49, 1, now( ), now( )), (253, 50, 1, now( ), now( )), (254, 51, 1, now( ), now( )),
  (255, 52, 1, now( ), now( )), (256, 53, 1, now( ), now( )), (257, 68, 1, now( ), now( ));
