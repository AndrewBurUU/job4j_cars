create table if not exists car
(
    id serial primary key,
    name varchar not null,
	engine_id int references engine (id) not null
);