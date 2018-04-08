/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/8 9:55:29                             */
/*==============================================================*/


drop table if exists uupm_dictionary;

drop table if exists uupm_organization;

drop table if exists uupm_re_user_org;

drop table if exists uupm_re_user_role;

drop table if exists uupm_role;

drop table if exists uupm_tenant;

drop table if exists uupm_user;

drop table if exists uupm_resource;

drop table if exists uupm_application;

drop table if exists uupm_re_role_resource;

drop table if exists uupm_sys_tree;

/*==============================================================*/
/* Table: uupm_sys_tree                                         */
/*==============================================================*/
create table uupm_sys_tree
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(64) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(64) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID            varchar(64) comment '租户ID',
   NODE_CODE            varchar(64) comment '节点编号',
   NODE_NAME            varchar(64) comment '节点名称',
   NODE_VALUE           varchar(64) comment '节点值',
   NODE_TYPE            varchar(64) comment '节点类型',
   NODE_STATUS          varchar(64) comment '节点状态',
   SEQ_NO               int comment '序号',
   NODE_LEFT            bigint comment '节点左编号',
   NODE_RIGH            bigint comment '节点右编号',
   NODE_LAYER           bigint comment '节点层次',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_sys_tree comment '系统树信息';

/*==============================================================*/
/* Table: uupm_re_role_resource                                 */
/*==============================================================*/
create table uupm_re_role_resource
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   APP_CODE             varchar(30) comment '应用系统编号',
   RS_CODE              varchar(30) comment '资源编号',
   ROLE_CODE            varchar(30) comment '角色编号',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_re_role_resource comment '角色-资源信息';

/*==============================================================*/
/* Table: uupm_dictionary                                       */
/*==============================================================*/
create table uupm_dictionary
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   CATEGORY_CODE        varchar(30) comment '数据类别:CAT_DICT、CAT_OA等',
   ITEM_NAME            varchar(30) comment '项名称',
   ITEM_CODE            varchar(30) comment '项编号',
   PARENT_CODE          varchar(30) comment '父机构编号',
   DATA_PATH            varchar(100) comment '数据路径，用点（.）分隔',
   DATA_LABEL           varchar(30) comment '数据标签：主要用于查询tree（包括其下的所有子节点），一般是数据范围的子集',
   DATA_SCOPE           varchar(30) comment '数据范围，CATEGORY、DICT等',
   SEQ_NO               int comment '序号',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_dictionary comment '字典信息';

/*==============================================================*/
/* Table: uupm_organization                                     */
/*==============================================================*/
create table uupm_organization
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   ORG_CODE             varchar(30) comment '机构编号',
   ORG_NAME             varchar(30) comment '机构名称',
   PARENT_CODE          varchar(30) comment '父机构编号',
   SEQ_NO               int comment '菜单序号',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_organization comment '组织机构信息';

/*==============================================================*/
/* Table: uupm_re_user_org                                      */
/*==============================================================*/
create table uupm_re_user_org
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   USER_CODE            varchar(30) comment '用户编号',
   ORG_CODE             varchar(30) comment '机构编号',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_re_user_org comment '用户-机构关系信息';

/*==============================================================*/
/* Table: uupm_re_user_role                                     */
/*==============================================================*/
create table uupm_re_user_role
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   USER_CODE            varchar(30) comment '用户编号',
   ROLE_CODE            varchar(30) comment '角色编号',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_re_user_role comment '用户-角色关系信息';

/*==============================================================*/
/* Table: uupm_role                                             */
/*==============================================================*/
create table uupm_role
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   ROLE_CODE            varchar(30) comment '角色编号',
   ROLE_NAME            varchar(30) comment '角色名称',
   ROLE_STATUS          char(10) comment '角色状态：1=激活、0=冻结',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_role comment '角色信息';

/*==============================================================*/
/* Table: uupm_tenant                                           */
/*==============================================================*/
create table uupm_tenant
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   TENANT_NAME          varchar(30) comment '租户名称',
   TENANT_TYPE          char(10) comment '租户类型：1=运营中心、2=企业、3=个人、4=其它',
   TENANT_STATUS        char(10) comment '租户状态：1=初始化、2=试用中、3=生产中、4=已过期',
   SERVE_TYPE           char(10) comment '服务方式：1=收费、0=免费',
   START_TIME           datetime comment '租赁服务开始时间',
   END_TIME             datetime comment '租赁服务结束时间',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_tenant comment '租户信息';

/*==============================================================*/
/* Table: uupm_user                                             */
/*==============================================================*/
create table uupm_user
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   TENANT_ID		varchar(30) comment '租户编号',
   USER_CODE            varchar(30) comment '用户编号',
   USER_NAME            varchar(30) comment '用户名称',
   USER_TYPE            char(10) comment '用户类型：1=内部用户、2=外部用户',
   LOGIN_ID             varchar(30) comment '账户ID',
   LOGIN_PWD            varchar(30) comment '账户密码',
   LOGIN_STATUS         char(10) comment '账户状态：1=激活、0=冻结',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_user comment '用户信息';

/*==============================================================*/
/* Table: uupm_resource                                         */
/*==============================================================*/
create table uupm_resource
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   APP_CODE             varchar(30) comment '应用系统编号',
   RS_NAME              varchar(30) comment '资源名称',
   RS_CODE              varchar(30) comment '资源编号',
   PARENT_CODE          varchar(30) comment '父资源名称',
   RS_PATH              varchar(30) comment '资源路径',
   RS_TYPE              varchar(30) comment '资源类型：M=菜单、O=操作',
   RS_STATUS            varchar(30) comment '资源状态：1=激活、0=冻结',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_resource comment '应用资源信息';

/*==============================================================*/
/* Table: uupm_application                                      */
/*==============================================================*/
create table uupm_application
(
   ID                   bigint not null auto_increment comment '主键',
   VERSION              int comment '版本号',
   DEL_FLAG             char(1) comment '逻辑删除标识：0=未删除、1=已删除',
   CREATE_BY            varchar(30) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_BY            varchar(30) comment '最后修改人',
   UPDATE_TIME          datetime comment '最后修改时间',
   APP_CODE             varchar(30) comment '应用系统编号',
   APP_NAME             varchar(30) comment '应用系统名称',
   APP_DOMAIN           varchar(100) comment '应用系统域名',
   APP_PORT             varchar(30) comment '应用系统端口',
   APP_CONTEXT_PATH     varchar(30) comment '应用系统上下文路径',
   APP_TYPE             char(10) comment '应用系统类型：app-local=本地系统、app-inner=内部系统、app-outer=外部系统',
   APP_STATUS           char(10) comment '应用系统状态：1=激活、0=冻结',
   REMARK               varchar(100) comment '备注',
   primary key (ID)
)
auto_increment = 2000;

alter table uupm_application comment '应用系统信息';