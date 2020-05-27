-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(20) NULL DEFAULT NULL,
  `PARENT_NAME` varchar(20)  DEFAULT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `URL` varchar(50) DEFAULT NULL,
  `PERMS` varchar(100) DEFAULT NULL,
  `TYPE` int(2) NULL DEFAULT NULL,
  `ORDER_NUM` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) NOT NULL,
  `REMARK` varchar(30) DEFAULT NULL,
  `CREATE_TIME` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NOT NULL,
  `MENU_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `SEX` int(4) NULL DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `LAST_LOGIN_TIME` bigint(20) NULL DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(50) DEFAULT NULL,
  `AVATAR_URL` varchar(100) DEFAULT NULL,
  `CREATE_TIME` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGINT_TIME` bigint(20) NULL DEFAULT NULL,
  `LOGIN_IP` varchar(50) DEFAULT NULL,
  `USER_ID` bigint(20) NULL DEFAULT NULL,
  `OPERATION_SYSTEM` varchar(50) DEFAULT NULL,
  `BROWSER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
);
