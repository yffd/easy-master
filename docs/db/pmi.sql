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
   ID                   bigint not null auto_increment comment '����',
   DEL_FLAG             char(1) default '0' comment '1��ɾ����0��δɾ��',
   VERSION              int default 1 comment '�汾��',
   CREATER              varchar(50) default NULL comment '������',
   CREATE_TIME          datetime default NULL comment '����ʱ��',
   EDITOR               varchar(50) default NULL comment '�޸���',
   EDIT_TIME            datetime default NULL comment '�޸�ʱ��',
   ACCOUNT              varchar(50) default NULL comment '�˻�',
   PASSWORD             varchar(50) default NULL comment '����',
   SHORT_NAME           varchar(50) default NULL comment '���',
   primary key (ID)
);

alter table pmi_account comment 'Ȩ�޹���ϵͳ���˻�';

/*==============================================================*/
/* Table: pmi_organization                                      */
/*==============================================================*/
create table pmi_organization
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

alter table pmi_organization comment 'Ȩ�޹���ϵͳ������';

/*==============================================================*/
/* Table: pmi_resource                                          */
/*==============================================================*/
create table pmi_resource
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
   RS_TYPE              char(1) default NULL comment '��Դ����',
   RS_NUM               int default NULL comment '˳����',
   RS_STATE             char(1) default NULL comment 'A:active��I:inactive',
   REMARK               varchar(255) default NULL comment '��ע',
   primary key (ID)
);

alter table pmi_resource comment 'Ȩ�޹���ϵͳ����Դ';

/*==============================================================*/
/* Table: pmi_role                                              */
/*==============================================================*/
create table pmi_role
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

alter table pmi_role comment 'Ȩ�޹���ϵͳ����ɫ';

/*==============================================================*/
/* Table: pmi_role_resource                                     */
/*==============================================================*/
create table pmi_role_resource
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

alter table pmi_role_resource comment '��ɫ-��Դ��ϵ';

/*==============================================================*/
/* Table: pmi_role_user                                         */
/*==============================================================*/
create table pmi_role_user
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

alter table pmi_role_user comment '��ɫ-�û���ϵ';

/*==============================================================*/
/* Table: pmi_user                                              */
/*==============================================================*/
create table pmi_user
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

alter table pmi_user comment 'Ȩ�޹���ϵͳ���û�';
