drop table if exists auth_account;

drop table if exists auth_organization;

drop table if exists auth_resource;

drop table if exists auth_role;

drop table if exists auth_role_resource;

drop table if exists auth_role_user;

drop table if exists auth_user;

/*==============================================================*/
/* Table: auth_account                                          */
/*==============================================================*/
create table auth_account
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ACCOUNT_CODE         varchar(50) default NULL comment '�˻����루Ψһ��',
   ACCOUNT_PWD          varchar(50) default NULL comment '����',
   ACCOUNT_NAME         varchar(50) default NULL comment '���',
   ACCOUNT_STATE        char(1) default 'A' comment 'A:ACTIVE,I:INACTIVE',
   primary key (ID)
);

alter table auth_account comment 'Ȩ�޹���ϵͳ���˻�';

/*==============================================================*/
/* Table: auth_organization                                     */
/*==============================================================*/
create table auth_organization
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ORG_CODE             varchar(50) default NULL comment '�������',
   ORG_NAME             varchar(50) default NULL comment '��������',
   PARENT_CODE          varchar(50) default NULL comment '���������',
   FIRST_MANAGER_CODE   varchar(50) default NULL comment '��������',
   SECOND_MANAGER_CODE  varchar(50) default NULL comment '��������',
   REMARK               varchar(255) default NULL comment '��ע',
   primary key (ID)
);

alter table auth_organization comment 'Ȩ�޹���ϵͳ������';

/*==============================================================*/
/* Table: auth_resource                                         */
/*==============================================================*/
create table auth_resource
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   RS_CODE              varchar(50) default NULL comment '��Դ���',
   RS_NAME              varchar(50) default NULL comment '��Դ����',
   PARENT_CODE          varchar(50) default NULL comment '����Դ���',
   IN_URL               varchar(255) default NULL comment '�ڲ�����',
   RS_TYPE              char(1) default NULL comment 'A:APP,M:MENU,O:OPERATION',
   RS_NUM               int default NULL comment '˳����',
   RS_STATE             char(1) default NULL comment 'A:active��I:inactive',
   REMARK               varchar(255) default NULL comment '��ע',
   primary key (ID)
);

alter table auth_resource comment 'Ȩ�޹���ϵͳ����Դ';

/*==============================================================*/
/* Table: auth_role                                             */
/*==============================================================*/
create table auth_role
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ROLE_CODE            varchar(50) default NULL comment '��ɫ���',
   ROLE_NAME            varchar(50) default NULL comment '��ɫ����',
   REMARK               varchar(255) default NULL comment '��ע',
   primary key (ID)
);

alter table auth_role comment 'Ȩ�޹���ϵͳ����ɫ';

/*==============================================================*/
/* Table: auth_role_resource                                    */
/*==============================================================*/
create table auth_role_resource
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ROLE_CODE            varchar(50) default NULL comment '��ɫ���',
   RS_CODE              VARCHAR(50) default NULL comment '��Դ���',
   primary key (ID)
);

alter table auth_role_resource comment '��ɫ-��Դ��ϵ';

/*==============================================================*/
/* Table: auth_role_user                                        */
/*==============================================================*/
create table auth_role_user
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ROLE_CODE            varchar(50) default NULL comment '��ɫ���',
   USER_CODE            varchar(50) default NULL comment '�û����',
   primary key (ID)
);

alter table auth_role_user comment '��ɫ-�û���ϵ';

/*==============================================================*/
/* Table: auth_user                                             */
/*==============================================================*/
create table auth_user
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   USER_CODE            varchar(50) default NULL comment '�û����',
   USER_NAME            varchar(50) default NULL comment '�û�����',
   ORG_CODE             varchar(50) default NULL comment '�������',
   USER_STATE           char(1) default NULL comment 'A:active��I:inactive',
   TEL                  varchar(50) default NULL comment '�绰',
   EMAIL                varchar(50) default NULL comment '����',
   REMARK               varchar(255) default NULL comment '��ע',
   primary key (ID)
);

alter table auth_user comment 'Ȩ�޹���ϵͳ���û�';
