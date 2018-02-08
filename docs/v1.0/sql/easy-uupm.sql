/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/2/1 11:38:12                            */
/*==============================================================*/


drop table if exists uupm_application;

drop table if exists uupm_dictionary;

drop table if exists uupm_function;

drop table if exists uupm_menu;

drop table if exists uupm_organization;

drop table if exists uupm_re_role_function;

drop table if exists uupm_re_tenant_function;

drop table if exists uupm_re_user_org;

drop table if exists uupm_re_user_role;

drop table if exists uupm_role;

drop table if exists uupm_tenant;

drop table if exists uupm_user;

/*==============================================================*/
/* Table: uupm_application                                      */
/*==============================================================*/
create table uupm_application
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   APPLICATION_CODE     varchar(30) comment '应用系统编号',
   APPLICATION_NAME     varchar(30) comment '应用系统名称',
   APPLICATION_TYPE     char(10) comment '应用系统类型：1=基础系统、2=内部系统、3=外部系统',
   APPLICATION_STATUS   char(10) comment '应用系统状态：1=激活、0=冻结',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_application comment '应用系统信息';

/*==============================================================*/
/* Table: uupm_dictionary                                       */
/*==============================================================*/
create table uupm_dictionary
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   ITEM_CODE            varchar(30) comment '项编号',
   ITEM_NAME            varchar(30) comment '项名称',
   PARENT_CODE          varchar(30) comment '父机构编号',
   ACCESS_TYPE          char(10) comment '访问类型：1=公开、0=私有',
   SEQ_NO               int comment '菜单序号',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_dictionary comment '字典信息';

/*==============================================================*/
/* Table: uupm_function                                         */
/*==============================================================*/
create table uupm_function
(
   ID                   bigint not null comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   APPLICATION_CODE     varchar(30) comment '应用系统编号',
   FUNCTION_CODE        varchar(30) comment '功能编号',
   FUNCTION_NAME        varchar(30) comment '功能名称',
   FUNCTION_STATUS      char(10) comment '功能状态：1=激活、0=冻结',
   INNER_URL            varchar(300) comment '内部链接',
   OUTER_URL            varchar(300) comment '外部链接',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_function comment '应用系统功能信息';

/*==============================================================*/
/* Table: uupm_menu                                             */
/*==============================================================*/
create table uupm_menu
(
   ID                   bigint not null comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   APPLICATION_CODE     varchar(30) comment '应用系统编号',
   FUNCTION_CODE        varchar(30) comment '功能编号',
   MENU_NAME            varchar(30) comment '菜单名称',
   MENU_ICONS           varchar(300) comment '菜单图标',
   SEQ_NO               int comment '菜单序号',
   MENU_URL             varchar(300) comment '菜单链接',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_menu comment '菜单信息';

/*==============================================================*/
/* Table: uupm_organization                                     */
/*==============================================================*/
create table uupm_organization
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   ORG_CODE             varchar(30) comment '机构编号',
   ORG_NAME             varchar(30) comment '机构名称',
   PARENT_CODE          varchar(30) comment '父机构编号',
   SEQ_NO               int comment '菜单序号',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_organization comment '组织机构信息';

/*==============================================================*/
/* Table: uupm_re_role_function                                 */
/*==============================================================*/
create table uupm_re_role_function
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   APPLICATION_CODE     varchar(30) comment '应用系统编号',
   FUNCTION_CODE        varchar(30) comment '功能编号',
   ROLE_CODE            varchar(30) comment '角色编号',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_re_role_function comment '角色-功能关系信息';

/*==============================================================*/
/* Table: uupm_re_tenant_function                               */
/*==============================================================*/
create table uupm_re_tenant_function
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   APPLICATION_CODE     varchar(30) comment '应用系统编号',
   FUNCTION_CODE        varchar(30) comment '功能编号',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_re_tenant_function comment '租户-功能关系信息';

/*==============================================================*/
/* Table: uupm_re_user_org                                      */
/*==============================================================*/
create table uupm_re_user_org
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   USER_CODE            varchar(30) comment '用户编号',
   ORG_CODE             varchar(30) comment '机构编号',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_re_user_org comment '用户-机构关系信息';

/*==============================================================*/
/* Table: uupm_re_user_role                                     */
/*==============================================================*/
create table uupm_re_user_role
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   USER_CODE            varchar(30) comment '用户编号',
   ROLE_CODE            varchar(30) comment '角色编号',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_re_user_role comment '用户-角色关系信息';

/*==============================================================*/
/* Table: uupm_role                                             */
/*==============================================================*/
create table uupm_role
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   ROLE_CODE            varchar(30) comment '角色编号',
   ROLE_NAME            varchar(30) comment '角色名称',
   ROLE_STATUS          char(10) comment '角色状态：1=激活、0=冻结',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_role comment '角色信息';

/*==============================================================*/
/* Table: uupm_tenant                                           */
/*==============================================================*/
create table uupm_tenant
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   TENANT_NAME          varchar(30) comment '租户名称',
   TENANT_TYPE          char(10) comment '租户类型：1=运营中心、2=企业、3=个人、4=其它',
   TENANT_STATUS        char(10) comment '租户状态：1=初始化、2=试用中、3=生产中、4=已过期',
   SERVE_TYPE           char(10) comment '服务方式：1=收费、0=免费',
   START_TIME           datetime comment '租赁服务开始时间',
   END_TIME             datetime comment '租赁服务结束时间',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_tenant comment '租户信息';

/*==============================================================*/
/* Table: uupm_user                                             */
/*==============================================================*/
create table uupm_user
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATOR              varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   EDITOR               varchar(30) comment '最后编辑人',
   EDIT_TIME            datetime comment '最后编辑时间',
   TENANT_CODE          varchar(30) comment '租户编号',
   USER_CODE            varchar(30) comment '用户编号',
   USER_NAME            varchar(30) comment '用户名称',
   USER_TYPE            char(10) comment '用户类型：1=内部用户、2=外部用户',
   LOGIN_ID             varchar(30) comment '账户ID',
   LOGIN_PWD            varchar(30) comment '账户密码',
   LOGIN_STATUS         char(10) comment '账户状态：1=激活、0=冻结',
   primary key (ID)
)
auto_increment = 1000;

alter table uupm_user comment '用户信息';

