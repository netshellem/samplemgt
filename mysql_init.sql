-- Create table
create table app_user
(
  USER_ID           BIGINT not null,
  USER_NAME         VARCHAR(36) not null,
  ENCRYTED_PASSWORD VARCHAR(128) not null,
  ENABLED           BIT not null 
) ;


alter table app_user
  add constraint APP_USER_PK primary key (USER_ID);

alter table app_user
  add constraint APP_USER_UK unique (USER_NAME);

create table app_role
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null
) ;

alter table app_role
  add constraint APP_ROLE_PK primary key (ROLE_ID);
  
alter table app_role
  add constraint APP_ROLE_UK unique (ROLE_NAME);

create table user_role
(
  ID      BIGINT not null,
  USER_ID BIGINT not null,
  ROLE_ID BIGINT not null
);
 
alter table user_role
  add constraint USER_ROLE_PK primary key (ID);
  
alter table user_role
  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);
  
alter table user_role
  add constraint USER_ROLE_FK1 foreign key (USER_ID)
  references app_user (USER_ID);
  
alter table user_role
  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
  references app_role (ROLE_ID);

-- Used by Spring Remember Me API.  
CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
     
);
insert into app_user (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED) values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into app_user (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED) values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into app_role (ROLE_ID, ROLE_NAME) values (1, 'ROLE_ADMIN');

insert into app_role (ROLE_ID, ROLE_NAME) values (2, 'ROLE_USER');

insert into user_role (ID, USER_ID, ROLE_ID) values (1, 1, 1);

insert into user_role (ID, USER_ID, ROLE_ID) values (2, 1, 2);

insert into user_role (ID, USER_ID, ROLE_ID) values (3, 2, 2);
