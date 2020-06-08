insert into `sys_role`(`ID`, `ROLE_NAME`, `REMARK`, `CREATE_TIME`)
VALUES (1, 'admin', 'admin', 12003004);

-- admin user
insert into `sys_user`(`ID`, `USER_NAME`, `PASSWORD`, `SEX`, `EMAIL`,
`LAST_LOGIN_TIME`, `LAST_LOGIN_IP`, `AVATAR_URL`, `CREATE_TIME`)
VALUES (1, 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
1, 'liangqinghai@live.com', 1591539775, '127.0.0.1', '', 1591539775);

insert into `sys_menu`(`ID`, `PARENT_ID`, `PARENT_NAME`, `NAME`, `URL`, `TYPE`, `PERMS`, `ORDER_NUM`)
VALUES
(1,0,'系统管理','','',0,'fa fa-cog',0),
(2,1,'管理员列表','admin/sys/admin.html','',1,'fa fa-user',1),

(3,1,'角色管理','admin/sys/role.html',NULL,1,'fa fa-user-secret',2),

(4,1,'菜单管理','admin/sys/menu.html',NULL,1,'fa fa-th-list',3),

(5,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),

(6,2,'新增','','sys:user:save,sys:role:select',2,'',0),

(7,2,'修改','','sys:user:update,sys:role:select',2,'',0),

(8,2,'删除',NULL,'sys:user:delete',2,NULL,0),

(9,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),

(10,3,'新增',NULL,'sys:role:save,sys:menu:perms',2,NULL,0),

(11,3,'修改',NULL,'sys:role:update,sys:menu:perms',2,NULL,0),

(12,3,'删除',NULL,'sys:role:delete',2,NULL,0),

(13,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),

(14,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),

(15,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),

(16,4,'删除',NULL,'sys:menu:delete',2,NULL,0),

(17,1,'监控管理','druid/index.html','druid:druid:manager',1,'fa fa-cogs',3),

(18,1,'Swagger管理','swagger-ui.html','swaggerui:swaggerui:manager',1,'fa fa-diamond',4);


insert  into `sys_role_menu`(`ID`,`ROLE_ID`,`MENU_ID`) values

(241,1,1),

(242,1,2),

(243,1,5),

(244,1,6),

(245,1,3),

(246,1,9),

(247,1,4),

(248,1,1),

(249,1,17),

(250,1,18);