create database if not exists delivery;

create table account (
  id bigint auto_increment primary key,
	name varchar(255) not null,
	username varchar(255) not null,
	password varchar(255) not null
)