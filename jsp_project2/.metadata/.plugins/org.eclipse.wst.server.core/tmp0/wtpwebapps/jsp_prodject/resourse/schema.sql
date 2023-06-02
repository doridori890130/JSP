2023-05-12
Create table member(
Id varchar(100) not null,
password varchar(100) not null,
email varchar(100) not null,
age int,
reg_date datetime default now(),
last_login datetime default null,
Primary key(id));
--------------------------------------
2023-05-17
alter table board add read_count int default 0;

-------------------------------------
2023-05-19
create table comment(
cno int not null auto_increment,
bno int default 0,
writer varchar(100) default “unknown”,
content varchar(1000) not null,
reg_date datetime default now(),
primary key(cno));
2023-05-25
alter table board add image_file text;
