05.30
create table meminfo(
mno int auto_increment,
id varchar(100),
password varchar(100),
email varchar(100),
age int,
reg_date datetime default now(),
last_login datetime default null,
primary key(mno));
