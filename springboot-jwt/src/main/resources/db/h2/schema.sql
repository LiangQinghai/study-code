create table `user`(
    `id` bigint(20) auto_increment NOT NULL,
    `name` varchar(25) not null ,
    `password` varchar(100) not null ,
    `age` int(5) default null,
    primary key (`id`)
);