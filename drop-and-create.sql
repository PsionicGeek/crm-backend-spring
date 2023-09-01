drop table if exists category cascade ;
drop table if exists employee cascade ;
drop table if exists product_bran_category cascade ;
drop table if exists product_brand cascade ;
drop table if exists role cascade ;
drop table if exists user_role cascade ;
drop sequence if exists employee_seq;
drop sequence if exists role_seq;
create sequence employee_seq start with 1 increment by 50;
create sequence role_seq start with 1 increment by 50;
create table category (id integer generated by default as identity, created_date timestamp(6), updated_date timestamp(6), name varchar(255), primary key (id));
create table employee (id integer not null, is_active boolean not null, date_created timestamp(6), date_modified timestamp(6), employee_email varchar(255), employee_password varchar(255), employee_user_name varchar(255), primary key (id));
create table product_bran_category (brand_id integer not null, category_id integer not null, primary key (brand_id, category_id));
create table product_brand (id integer not null, created_date timestamp(6), updated_date timestamp(6), name varchar(255), primary key (id));
create table role (role_id integer not null, role varchar(255), primary key (role_id));
create table user_role (id integer not null, role_id integer not null unique, primary key (id, role_id));
alter table if exists product_bran_category add constraint FK4gemhoyw1di0q2f2x2jju1j9l foreign key (category_id) references category;
alter table if exists product_bran_category add constraint FKlrxs51q4abvvyq34it5xwukpn foreign key (brand_id) references product_brand;
alter table if exists user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table if exists user_role add constraint FKrial5v7e0pfgaok7t6yejdev0 foreign key (id) references employee;
