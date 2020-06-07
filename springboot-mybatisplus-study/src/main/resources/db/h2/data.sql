INSERT INTO `sys_role`(`ID`, `ROLE_NAME`, `REMARK`, `CREATE_TIME`)
VALUES (1, 'admin', 'admin', 12003004);

-- admin user
INSERT INTO `sys_user`(`ID`, `USER_NAME`, `PASSWORD`, `SEX`, `EMAIL`,
`LAST_LOGIN_TIME`, `LAST_LOGIN_IP`, `AVATAR_URL`, `CREATE_TIME`)
VALUES (1, 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
1, 'liangqinghai@live.com', 1591539775, '127.0.0.1', '', 1591539775);