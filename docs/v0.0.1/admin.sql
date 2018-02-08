drop table if exists pms_account;

drop table if exists pms_organization;

drop table if exists pms_resource;

drop table if exists pms_role;

drop table if exists pms_role_resource;

drop table if exists pms_role_user;

drop table if exists pms_user;

/*==============================================================*/
/* Table: pms_account                                           */
/*==============================================================*/
create table pms_account
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ACCOUNT_NAME         varchar(50) default NULL comment '�˻����루Ψһ��',
   ACCOUNT_PWD          varchar(50) default NULL comment '����',
   NICK_NAME            varchar(50) default NULL comment '���',
   SALT                 varchar(256) default NULL comment '�����������',
   STATUS               char(1) default 'A' comment 'A:ACTIVE,I:INACTIVE',
   TYPE                 char(1) default 'S' comment 'T��tourist��S��staff',
   IS_ONLINE            char(1) default 'N' comment 'Y:yes,N:no',
   VISIT_IP             varchar(50) default NULL comment '����IP',
   VISIT_COUNT          int default 0 comment '��¼����',
   VISIT_FIRST          datetime default NULL comment '��һ�ε�¼ʱ��',
   VISIT_PREVIOUS       datetime default NULL comment '��һ�ε�¼ʱ��',
   VISIT_LAST           datetime default NULL comment '���һ�ε�¼ʱ��',
   primary key (ID)
);

alter table pms_account comment 'Ȩ�޹���ϵͳ���˻�';

/*==============================================================*/
/* Table: pms_organization                                      */
/*==============================================================*/
create table pms_organization
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

alter table pms_organization comment 'Ȩ�޹���ϵͳ������';

/*==============================================================*/
/* Table: pms_resource                                          */
/*==============================================================*/
create table pms_resource
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

alter table pms_resource comment 'Ȩ�޹���ϵͳ����Դ';

/*==============================================================*/
/* Table: pms_role                                              */
/*==============================================================*/
create table pms_role
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

alter table pms_role comment 'Ȩ�޹���ϵͳ����ɫ';

/*==============================================================*/
/* Table: pms_role_resource                                     */
/*==============================================================*/
create table pms_role_resource
(
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ROLE_CODE            varchar(50) default NULL comment '��ɫ���',
   PMS_CODE             VARCHAR(50) default NULL comment '��ԴȨ�ޱ��',
   primary key (ID)
);

alter table pms_role_resource comment '��ɫ-��Դ��ϵ';

/*==============================================================*/
/* Table: pms_role_user                                         */
/*==============================================================*/
create table pms_role_user
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

alter table pms_role_user comment '��ɫ-�û���ϵ';

/*==============================================================*/
/* Table: pms_user                                              */
/*==============================================================*/
create table pms_user
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
   USER_STATUS          char(1) default NULL comment 'A:active��I:inactive',
   TEL                  varchar(50) default NULL comment '�绰',
   EMAIL                varchar(50) default NULL comment '����',
   REMARK               varchar(255) default NULL comment '��ע',
   PASSWORD             varchar(50) default NULL comment '����',
   SALT                 varchar(100) default NULL comment '��',
   primary key (ID)
);

alter table pms_user comment 'Ȩ�޹���ϵͳ���û�';
