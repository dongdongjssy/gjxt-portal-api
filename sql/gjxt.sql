/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : gjxt-portal

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 25/04/2023 09:45:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table client_role
-- ----------------------------
DROP TABLE IF EXISTS `client_role`;
CREATE TABLE `client_role`
(
    `id`          int UNSIGNED                                            NOT NULL AUTO_INCREMENT,
    `name`        varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '{"title": "角色", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '{"title": "描述", "searchable": "0", "textSearchOpt":"1", "richEditor":"0"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '客户端角色'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of client_role
-- ----------------------------

-- ----------------------------
-- Table client_user
-- ----------------------------
DROP TABLE IF EXISTS `client_user`;
CREATE TABLE `client_user`
(
    `id`                  bigint                                                    NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '{"title": "用户名", "searchable": "1", "textSearchOpt":"1", "richEditor":"0", "checkUnique":"1"}',
    `phone`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '{"title": "手机号", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `status`              tinyint(1)                                                NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `password`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '{"title": "密码", "searchable": "0", "textSearchOpt":"0", "richEditor":"0", "systemField":"1"}',
    `password_reset_date` datetime                                                  NULL DEFAULT NULL COMMENT '{"title": "密码重置时间", "searchable": "0", "textSearchOpt":"0", "type": "date", "systemField":"1"}',
    `user_type`           tinyint(1)                                                     DEFAULT 1 COMMENT '{"title": "用户类型", "type": "dict", "value": "client_user_user_type", "data":{"0":"管理员", "1":"普通用户"}, "searchable": "0"}',
    `avatar`              VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '{"title": "用户头像", "uploadPic":"1"}',
    `id_number`           VARCHAR(20)                                                    DEFAULT NULL COMMENT '{"title": "身份证号", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}',
    `real_name`           VARCHAR(20)                                                    DEFAULT NULL COMMENT '{"title": "真实姓名", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}',
    `education_level`     tinyint(1)                                                     DEFAULT NULL COMMENT '{"title": "教育阶段", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}',
    `resume`              longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title":"个人简介","searchable":"0","richEditor":"2"}',
    `school_name`         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '{"title": "学校名称", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `faculty_name`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '{"title": "院系", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `profession`          varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '{"title": "专业", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `student_id`          varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '{"title": "学号", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `gender`              tinyint(1)                                                     DEFAULT 0 COMMENT '{"title": "性别", "type": "dict", "value": "gender", "data":{"0":"-", "1":"男", "2":"女"}, "searchable": "0"}',
    `nationality`         varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '{"title": "民族", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `admission_time`      datetime                                                  NULL DEFAULT NULL COMMENT '{"title": "入学时间", "searchable": "1", "type": "date"}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_name` (`username`) USING BTREE,
    UNIQUE INDEX `real_name` (`real_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '客户端用户'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of client_user
-- ----------------------------
INSERT INTO `client_user`
VALUES (2, '18222938243', '18222938243', 0, '$2a$10$hLWzeU4BKUCJC7VB7mihceoAbB8VdQQUrTQ0pKhdoRHdanecYWnoy', NULL, 1, NULL,
        '000000000000000000', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table client_user_role
-- ----------------------------
DROP TABLE IF EXISTS `client_user_role`;
CREATE TABLE `client_user_role`
(
    `id`      int UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` bigint       NOT NULL COMMENT '{"title": "用户", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"client_user", "fkColumn":"id", "fkDisplayColumn":"username", "fkInputMethod":"select"}',
    `role_id` int          NOT NULL COMMENT '{"title": "角色", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"client_role", "fkColumn":"id", "fkDisplayColumn":"name", "fkInputMethod":"select"}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_id_2` (`user_id`, `role_id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '客户端用户角色关联'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of client_user_role
-- ----------------------------

-- ----------------------------
-- Table gjxt_certificate
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_certificate`;
CREATE TABLE `gjxt_certificate`
(
    `id`                   bigint     NOT NULL AUTO_INCREMENT COMMENT '证书id',
    `cert_education_level` tinyint(1) UNSIGNED                                     DEFAULT NULL COMMENT '{"title": "教育阶段", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}',
    `cert_number`          VARCHAR(50)                                             DEFAULT NULL COMMENT '{"title": "证书编号", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}',
    `cert_issue_year`      VARCHAR(50)                                             DEFAULT NULL COMMENT '{"title": "获奖学年", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}',
    `image`                VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '{"title": "证书图片", "uploadPic":"1"}',
    `status`               tinyint(1) NULL                                         DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`            bigint     NULL                                         DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time`          datetime   NULL                                         DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`            bigint     NULL                                         DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time`          datetime   NULL                                         DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '获奖证书'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_certificate
-- ----------------------------

-- ----------------------------
-- Table gjxt_client_user_cert
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_client_user_cert`;
CREATE TABLE `gjxt_client_user_cert`
(
    `id`      int UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` bigint       NOT NULL COMMENT '{"title": "用户", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"client_user", "fkColumn":"id", "fkDisplayColumn":"username", "fkInputMethod":"select"}',
    `cert_id` bigint       NOT NULL COMMENT '{"title": "证书", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"certificate", "fkColumn":"id", "fkDisplayColumn":"name", "fkInputMethod":"select"}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_cert_combine_id` (`user_id`, `cert_id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '客户端用户证书关联'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_client_user_cert
-- ----------------------------

-- ----------------------------
-- Table gjxt_message_board
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_message_board`;
CREATE TABLE `gjxt_message_board`
(
    `id`             bigint                                                       NOT NULL AUTO_INCREMENT,
    `content`        longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci    NOT NULL COMMENT '{"title":"内容","searchable":"0","richEditor":"2"}',
    `location`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '{"title":"位置","searchable":"1"}',
    `publish_mode`   smallint                                                     NOT NULL DEFAULT 1 COMMENT '{"title":"发表方式","searchable":"1","type":"dict","value":"publish_mode","data":{"0":"实名","1":"匿名"}}',
    `publish_status` smallint                                                     NOT NULL DEFAULT 0 COMMENT '{"title":"发布状态","searchable":"1","type":"dict","value":"publish_status","data":{"0":"待审核", "1":"通过", "2":"驳回", "3":"回收站"}}',
    `create_by`      bigint                                                       NOT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time`    datetime                                                     NOT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`      bigint                                                       NULL     DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time`    datetime                                                     NULL     DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '获奖心声'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_message_board
-- ----------------------------

-- ----------------------------
-- Table gjxt_most_asked_questions
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_most_asked_questions`;
CREATE TABLE `gjxt_most_asked_questions`
(
    `id`          bigint                                                         NOT NULL AUTO_INCREMENT,
    `question`    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '{"title":"问题","searchable":"0"}',
    `answer`      varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '{"title":"解答","searchable":"0"}',
    `category`    smallint                                                       NOT NULL DEFAULT 0 COMMENT '{"title": "类别", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}',
    `status`      smallint                                                       NULL     DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`   bigint                                                         NOT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                       NOT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`   bigint                                                         NULL     DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                       NULL     DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '常见问题'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_most_asked_questions
-- ----------------------------

-- ----------------------------
-- Table gjxt_policy_explanation
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_policy_explanation`;
CREATE TABLE `gjxt_policy_explanation`
(
    `id`          bigint                                                         NOT NULL AUTO_INCREMENT,
    `policy`      varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '{"title":"政策","searchable":"0"}',
    `explanation` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '{"title":"解读","searchable":"0"}',
    `category`    smallint                                                            DEFAULT 0 COMMENT '{"title": "类别", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}',
    `status`      smallint                                                       NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`   bigint                                                         NOT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                       NOT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`   bigint                                                         NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                       NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '政策解读'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_policy_explanation
-- ----------------------------

-- ----------------------------
-- Table gjxt_carousel
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_carousel`;
CREATE TABLE `gjxt_carousel`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT,
    `carousel_order` int          NOT NULL COMMENT '{"title":"轮播图序号"}',
    `source`         varchar(500) NOT NULL COMMENT '{"title":"轮播图路径"}',
    `description`    varchar(100) NULL DEFAULT NULL COMMENT '{"title":"轮播图说明文字"}',
    `status`         smallint     NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`      bigint       NOT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time`    datetime     NOT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`      bigint       NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time`    datetime     NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '轮播图'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_carousel
-- ----------------------------

-- ----------------------------
-- Table gjxt_multimedia
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_multimedia`;
CREATE TABLE `gjxt_multimedia`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `media_title` varchar(100) NOT NULL COMMENT '{"title":"多媒体名称"}',
    `media_src`   varchar(500) NOT NULL COMMENT '{"title":"多媒体路径"}',
    `media_type`  tinyint(1)   NOT NULL DEFAULT 0 COMMENT '{"title": "多媒体类型","type": "dict", "value": "media_type", "data":{"0":"文件", "1":"图片", "2":"视频"}, "searchable": "1"}',
    `status`      tinyint(1)   NOT NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`   bigint       NOT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime     NOT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`   bigint       NULL     DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime     NULL     DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '多媒体文件'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_multimedia
-- ----------------------------

-- ----------------------------
-- Table gjxt_announcement
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_announcement`;
CREATE TABLE `gjxt_announcement`
(
    `id`          bigint                                                  NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '{"title": "标题", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '{"title": "内容","searchable": "0","textSearchOpt":"1","richEditor":"2"}',
    `status`      tinyint(1)                                              NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`   bigint                                                  NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`   bigint                                                  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '通知公告'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_announcement
-- ----------------------------

-- ----------------------------
-- Table gjxt_announcement_media
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_announcement_media`;
CREATE TABLE `gjxt_announcement_media`
(
    `id`              int UNSIGNED NOT NULL AUTO_INCREMENT,
    `announcement_id` bigint       NOT NULL COMMENT '{"title": "通知公告", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_announcement", "fkColumn":"id", "fkDisplayColumn":"title", "fkInputMethod":"select"}',
    `media_id`        bigint       NOT NULL COMMENT '{"title": "媒体", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_multimedia", "fkColumn":"id", "fkDisplayColumn":"media_title", "fkInputMethod":"select"}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `announcement_media_combine_id` (`announcement_id`, `media_id`) USING BTREE,
    INDEX `announcement_id` (`announcement_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '通知公告关联媒体'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_announcement_media
-- ----------------------------

-- ----------------------------
-- Table gjxt_policy_file
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_policy_file`;
CREATE TABLE `gjxt_policy_file`
(
    `id`              bigint                                                  NOT NULL AUTO_INCREMENT,
    `title`           VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '{"title": "标题", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `content`         longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '{"title": "内容","searchable": "0","textSearchOpt":"1","richEditor":"2"}',
    `education_level` tinyint(1)                                                   DEFAULT NULL COMMENT '{"title": "教育阶段", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}',
    `status`          tinyint(1)                                              NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`       bigint                                                  NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time`     datetime                                                NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`       bigint                                                  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time`     datetime                                                NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '政策文件'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_policy_file
-- ----------------------------

-- ----------------------------
-- Table gjxt_policy_file_media
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_policy_file_media`;
CREATE TABLE `gjxt_policy_file_media`
(
    `id`             int UNSIGNED NOT NULL AUTO_INCREMENT,
    `policy_file_id` bigint       NOT NULL COMMENT '{"title": "政策文件", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_policy_file", "fkColumn":"id", "fkDisplayColumn":"title", "fkInputMethod":"select"}',
    `media_id`       bigint       NOT NULL COMMENT '{"title": "媒体", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_multimedia", "fkColumn":"id", "fkDisplayColumn":"media_title", "fkInputMethod":"select"}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `policy_file_media_combine_id` (`policy_file_id`, `media_id`) USING BTREE,
    INDEX `policy_file_id` (`policy_file_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '政策文件关联媒体'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_policy_file_media
-- ----------------------------

-- ----------------------------
-- Table gjxt_work_update
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_work_update`;
CREATE TABLE `gjxt_work_update`
(
    `id`          bigint                                                  NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '{"title": "标题", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '{"title": "内容","searchable": "0","textSearchOpt":"1","richEditor":"2"}',
    `status`      tinyint(1)                                              NULL DEFAULT 0 COMMENT '{"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}',
    `create_by`   bigint                                                  NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_by`   bigint                                                  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '工作动态'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_work_update
-- ----------------------------

-- ----------------------------
-- Table gjxt_work_update_media
-- ----------------------------
DROP TABLE IF EXISTS `gjxt_work_update_media`;
CREATE TABLE `gjxt_work_update_media`
(
    `id`             int UNSIGNED NOT NULL AUTO_INCREMENT,
    `work_update_id` bigint       NOT NULL COMMENT '{"title": "工作动态", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_work_update", "fkColumn":"id", "fkDisplayColumn":"title", "fkInputMethod":"select"}',
    `media_id`       bigint       NOT NULL COMMENT '{"title": "媒体", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_multimedia", "fkColumn":"id", "fkDisplayColumn":"media_title", "fkInputMethod":"select"}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `work_update_media_combine_id` (`work_update_id`, `media_id`) USING BTREE,
    INDEX `work_update_id` (`work_update_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '工作动态关联媒体'
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of gjxt_work_update_media
-- ----------------------------

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`
(
    `uuid`        char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci    NOT NULL COMMENT '{"title": "uuid"}',
    `code`        varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '{"title": "验证码"}',
    `expire_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "过期时间", "searchable": "1"}',
    `status`      tinyint                                                      NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`   bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统验证码'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`     bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '{"title": "主键id","notShow": "1"}',
    `parent_id`   bigint                                                        NULL DEFAULT NULL COMMENT '{"title": "父部门", "searchable": "1", "textSearchOpt":"1"}',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '{"title": "菜单名称", "searchable": "1", "textSearchOpt":"1","ellipsisText":"1"}',
    `url`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "请求地址"}',
    `perms`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "权限标识(多个用逗号分隔，如：user:list,user:create)"}',
    `type`        int                                                           NULL DEFAULT NULL COMMENT '{"title": "菜单类型","type": "dict", "value": "menu_type", "data":{"0":"目录", "1":"菜单", "2":"按钮"}, "searchable": "1"}',
    `icon`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '{"title": "菜单图标"}',
    `order_num`   int                                                           NULL DEFAULT NULL COMMENT '{"title": "显示顺序","hideInTable":"1","hideInSearch":"1"}',
    `status`      tinyint                                                       NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`   bigint                                                        NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1116
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单管理'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`     bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '{"title": "主键id","notShow": "1"}',
    `role_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "角色名称", "searchable": "1", "textSearchOpt":"1"}',
    `user_number` int                                                           NULL DEFAULT 0 COMMENT '人员数量',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `remark`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "备注","hideInTable":"1","hideInSearch":"1"}',
    `status`      tinyint                                                       NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`   bigint                                                        NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '{"title": "主键id","notShow": "1"}',
    `role_id`     bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "角色id","notShow": "1"}',
    `menu_id`     bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "菜单id","notShow": "1"}',
    `status`      tinyint                                                      NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`   bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 677
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '{"title": "主键id","notShow": "1"}',
    `username`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '{"title": "登陆名", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `password`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "密码", "textSearchOpt":"1", "richEditor":"0"}',
    `phone`               varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "手机号码", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}',
    `remark`              varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `status`              tinyint                                                       NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`           bigint                                                        NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time`         datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time`         datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `password_reset_date` datetime                                                      NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统用户'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (2, '18222938243', '$2a$10$wDJcHe0kfmsUFTtn2RRj1eQTZKGOalEteNFLO3LKgKQLiwVVvPbym', NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '2023-04-24 18:04:48');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '{"title": "主键id","notShow": "1"}',
    `user_id`     bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "用户id","notShow": "1"}',
    `role_id`     bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "角色id","notShow": "1"}',
    `status`      tinyint                                                      NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`   bigint                                                       NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                     NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`
(
    `user_id`     bigint                                                        NOT NULL COMMENT '{"title": "用户id","notShow": "1"}',
    `token`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '{"title": "token","notShow": "1"}',
    `expire_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "过期时间","notShow": "1"}',
    `status`      tinyint                                                       NULL DEFAULT 0 COMMENT '{"title": "状态","type": "dict", "value": "general_status", "data":{"0":"正常", "1":"异常"}, "searchable": "1", "systemField":"1"}',
    `create_id`   bigint                                                        NULL DEFAULT NULL COMMENT '{"title": "创建者", "searchable":"1", "systemField":"1"}',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}',
    `modify_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '{"title": "更新者", "searchable":"1", "systemField":"1"}',
    `modify_time` datetime                                                      NULL DEFAULT NULL COMMENT '{"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `token` (`token`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
