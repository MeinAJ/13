CREATE DATABASE `ds_0` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
CREATE DATABASE `ds_1` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE TABLE `user`(
	id bigint(64) not null,
	city varchar(20) not null,
	name varchar(20) not null,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into user values(1001,'上海','尹吉欢');
insert into user values(1002,'北京','张三');