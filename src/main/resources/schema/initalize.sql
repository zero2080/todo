create database todo_list;

create user todo_list@'%' identified by 'todo_list';

grant all privileges on todo_list.* to todo_list@'%';

use todo_list;

create table user (
    id int primary key auto_increment,
    nickname varchar(200) null,
    login_id varchar(200) not null unique,
    password varchar(60) not null,
    created_at datetime not null default current_timestamp
);

CREATE TABLE todo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    user_id int not null,
    success boolean not null default FALSE,
    created_at datetime default current_timestamp not null,
    constraint fk_todo_user_id foreign key (`user_id`) references user(`id`)
);

ALTER TABLE `todo` ADD COLUMN due_date DATE NULL;