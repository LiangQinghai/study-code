CREATE TABLE If Not Exists `t_user` (
                                              `id` int(11) NOT NULL AUTO_INCREMENT,
                                              `name` varchar(50) DEFAULT NULL,
                                              `password` varchar(50) DEFAULT NULL ,
                                              PRIMARY KEY (`id`)
) ENGINE=InnoDB;
insert into `t_user` (`name`, `password`)
values('one', 'one'),
       ('two', 'two');


CREATE TABLE If Not Exists `t_permission_user_info` (
                                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                                          `employee_number` varchar(200) DEFAULT NULL COMMENT '用户编号',
                                          `display_name` varchar(100) DEFAULT NULL COMMENT '用户名字',
                                          `account` varchar(100) DEFAULT NULL COMMENT '用户账号',
                                          `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
                                          `telephone` varchar(255) DEFAULT NULL COMMENT '用户手机号码',
                                          `valid_date` datetime DEFAULT NULL COMMENT '账号有效时间',
                                          `status` int(1) DEFAULT NULL COMMENT '是否冻结',
                                          `create_user` varchar(100) DEFAULT NULL COMMENT '创建者',
                                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                          `update_user` varchar(100) DEFAULT NULL COMMENT '更新者',
                                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                          `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户id',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB;