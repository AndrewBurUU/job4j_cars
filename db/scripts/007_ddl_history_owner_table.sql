create table history_owner
(
    id serial primary key,
	startAt timestamp,
	endAt timestamp,
	car_id int references car (id) not null,
	driver_id int references driver (id) not null
);