drop table if exists pmi_account;

drop table if exists pmi_organization;

drop table if exists pmi_resource;

drop table if exists pmi_role;

drop table if exists pmi_role_resource;

drop table if exists pmi_role_user;

drop table if exists pmi_user;

/*==============================================================*/
/* Table: pmi_account                                           */
/*==============================================================*/
create table pmi_account
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) default NULL comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) default NULL comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   ACCOUNT              varchar(50) default NULL comment '账户',
   PASSWORD             varchar(50) default NULL comment '密码',
   SHORT_NAME           varchar(50) default NULL comment '简称',
   primary key (ID)
);

alter table pmi_account comment '权限管理系统》账户';

/*==============================================================*/
/* Table: pmi_organization                                      */
/*==============================================================*/
create table pmi_organization
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) default NULL comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) default NULL comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   ORG_CODE             varchar(50) default NULL comment '机构编号',
   ORG_NAME             varchar(50) default NULL comment '机构名称',
   PARENT_CODE          varchar(50) default NULL comment '父机构编号',
   FIRST_MANAGER_CODE   varchar(50) default NULL comment '主负责人',
   SECOND_MANAGER_CODE  varchar(50) default NULL comment '副负责人',
   REMARK               varchar(255) default NULL comment '备注',
   primary key (ID)
);

alter table pmi_organization comment '权限管理系统》机构';

/*==============================================================*/
/* Table: pmi_resource                                          */
/*==============================================================*/
create table pmi_resource
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) default NULL comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) default NULL comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   RS_CODE              varchar(50) default NULL comment '资源编号',
   RS_NAME              varchar(50) default NULL comment '资源名称',
   PARENT_CODE          varchar(50) default NULL comment '父资源编号',
   IN_URL               varchar(255) default NULL comment '内部链接',
   RS_TYPE              char(1) default NULL comment '资源类型',
   RS_NUM               int default NULL comment '顺序编号',
   RS_STATE             char(1) default NULL comment 'A:active、I:inactive',
   REMARK               varchar(255) default NULL comment '备注',
   primary key (ID)
);

alter table pmi_resource comment '权限管理系统》资源';

/*==============================================================*/
/* Table: pmi_role                                              */
/*==============================================================*/
create table pmi_role
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) default NULL comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) default NULL comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   ROLE_CODE            varchar(50) default NULL comment '角色编号',
   ROLE_NAME            varchar(50) default NULL comment '角色名称',
   REMARK               varchar(255) default NULL comment '备注',
   primary key (ID)
);

alter table pmi_role comment '权限管理系统》角色';

/*==============================================================*/
/* Table: pmi_role_resource                                     */
/*==============================================================*/
create table pmi_role_resource
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   ROLE_CODE            varchar(50) default NULL comment '角色编号',
   RS_CODE              VARCHAR(50) default NULL comment '资源编号',
   primary key (ID)
);

alter table pmi_role_resource comment '角色-资源关系';

/*==============================================================*/
/* Table: pmi_role_user                                         */
/*==============================================================*/
create table pmi_role_user
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   ROLE_CODE            varchar(50) default NULL comment '角色编号',
   USER_CODE            varchar(50) default NULL comment '用户编号',
   primary key (ID)
);

alter table pmi_role_user comment '角色-用户关系';

/*==============================================================*/
/* Table: pmi_user                                              */
/*==============================================================*/
create table pmi_user
(
   ID                   bigint not null auto_increment comment '主键',
   DEL_FLAG             char(1) default '0' comment '1：删除、0：未删除',
   VERSION              int default 1 comment '版本号',
   CREATER              varchar(50) default NULL comment '创建人',
   CREATE_TIME          datetime default NULL comment '创建时间',
   EDITOR               varchar(50) default NULL comment '修改人',
   EDIT_TIME            datetime default NULL comment '修改时间',
   USER_CODE            varchar(50) default NULL comment '用户编号',
   USER_NAME            varchar(50) default NULL comment '用户名称',
   ORG_CODE             varchar(50) default NULL comment '机构编号',
   USER_STATE           char(1) default NULL comment 'A:active、I:inactive',
   TEL                  varchar(50) default NULL comment '电话',
   EMAIL                varchar(50) default NULL comment '邮箱',
   REMARK               varchar(255) default NULL comment '备注',
   primary key (ID)
);

alter table pmi_user comment '权限管理系统》用户';
