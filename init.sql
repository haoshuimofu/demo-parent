DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
	`id` INT AUTO_INCREMENT COMMENT 'ID',
	`username` VARCHAR(40) NOT NULL COMMENT '用户名',
	`password` VARCHAR(50) NOT NULL COMMENT '密码',
	`cellphone` VARCHAR(20) COMMENT '手机号码',
	`email` VARCHAR(255) COMMENT '邮箱',
	`created` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '创建时间',
	`created_by` INT NOT NULL DEFAULT 0 COMMENT '创建人',
	`modified` TIMESTAMP NOT NULL COMMENT '修改时间',
	`modified_by` INT NOT NULL DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY(`id`),
  UNIQUE (`username`)
);
INSERT INTO `t_user`(`id`, `username`, `password`, `cellphone`, `email`, `created`, `created_by`, `modified`, `modified_by`)
	VALUES(1, 'wude', '123qwe', '17717929937', '279759300@qq.com', NOW(), 0, NOW(), 0);


DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`(
	`id` INT AUTO_INCREMENT COMMENT 'ID',
	`role_name` VARCHAR(100) NOT NULL UNIQUE COMMENT '角色名称',
	`role_desc` VARCHAR(1000) COMMENT '角色描述',
	`created` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '创建时间',
	`created_by` INT NOT NULL DEFAULT 0 COMMENT '创建人',
	`modified` TIMESTAMP NOT NULL COMMENT '修改时间',
	`modified_by` INT NOT NULL DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY(`id`),
	UNIQUE (`role_name`)
);
INSERT INTO `t_role`(`id`, `role_name`, `role_desc`, `created`, `created_by`, `modified`, `modified_by`)
  VALUES(1, '管理员', '管理员用户, 拥有系统最大权限', NOW(), 1, NOW(), 1);

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`(
	`user_id` INT NOT NULL COMMENT '用户ID',
	`role_id` INT NOT NULL COMMENT '角色ID',
	PRIMARY KEY (`user_id`, `role_id`)
);
insert into `t_user_role`(`user_id`, `role_id`) values(1, 1);

DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`(
	`id` INT AUTO_INCREMENT COMMENT 'ID',
	`res_name` VARCHAR(255) NOT NULL COMMENT '资源名称',
	`res_desc` VARCHAR(1000) COMMENT '资源描述',
	`type` INT NOT NULL DEFAULT 0 COMMENT '资源类型: 1、Menu; 2、Page; 3、Function',
	`parent_id` INT NOT NULL DEFAULT 0 COMMENT '父资源ID',
	`is_parent` INT NOT NULL DEFAULT 0 COMMENT '是否是父资源: 1、是; 0、不是',
	`res_path` VARCHAR(300) DEFAULT NULL COMMENT '资源请求路径',
	`created` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '创建时间',
	`created_by` INT NOT NULL DEFAULT 0 COMMENT '创建人',
	`modified` TIMESTAMP NOT NULL COMMENT '修改时间',
	`modified_by` INT NOT NULL DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY(`id`),
	UNIQUE (`res_name`)
);
insert into `t_resource`(`id`, `res_name`, `res_desc`, `type`, `parent_id`, `is_parent`, `res_path`, `created`, `created_by`)
	values(1, '系统设置', '系统设置', 1, 0, 0, NULL , NOW(), 1);
insert into `t_resource`(`id`, `res_name`, `res_desc`, `type`, `parent_id`, `is_parent`, `res_path`, `created`, `created_by`)
	values(2, '用户管理', '系统用户管理', 1, 1, 0, '/system/users', NOW(), 1);
insert into `t_resource`(`id`, `res_name`, `res_desc`, `type`, `parent_id`, `is_parent`, `res_path`, `created`, `created_by`)
	values(3, '角色管理', '系统角色管理', 2, 1, 0, '/system/roles', NOW(), 1);


DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource`(
	`role_id` INTEGER NOT NULL COMMENT '角色ID',
	`resource_id` INTEGER NOT NULL COMMENT '资源ID',
	PRIMARY KEY (`role_id`, `resource_id`)
);
insert into `t_role_resource`(`role_id`, `resource_id`) values(1, 1);
insert into `t_role_resource`(`role_id`, `resource_id`) values(1, 2);
insert into `t_role_resource`(`role_id`, `resource_id`) values(1, 3);


