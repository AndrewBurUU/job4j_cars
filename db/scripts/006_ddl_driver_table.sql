create table driver
(
    id serial primary key,
    name varchar not null,
	user_id int references auto_user (id) not null
);