create database ssm;
use ssm;
create table account(
	id int PRIMARY key auto_increment,
	name varchar(20),
	money DOUBLE
);

insert into account(name, money) VALUES("吴彦祖", 200.0);
insert into account(name, money) VALUES("彭于晏", 100.0);
insert into account(name, money) VALUES("刘德华", 1200.0);