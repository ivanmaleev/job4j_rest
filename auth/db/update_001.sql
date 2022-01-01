create table person (
   id serial primary key not null,
   login varchar(2000),
   password varchar(2000),
   employeeId int
);

insert into person (login, password, employee_id) values ('parsentev', '123', '1');
insert into person (login, password, employee_id) values ('ban', '123', '2');
insert into person (login, password, employee_id) values ('ivan', '123', '1');