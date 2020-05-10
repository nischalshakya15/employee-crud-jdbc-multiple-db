drop table if exists employees;

drop table if exists departments;

create table departments
(
    id          bigint auto_increment,
    name        varchar(255) null,
    description varchar(255) null,
    created_at  datetime     not null,
    updated_at  datetime     null,
    constraint departments_pk
        primary key (id)
);

create unique index departments_name_unique_index
    on departments (name);

create table employees
(
    id            bigint auto_increment,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    email         varchar(255) not null,
    salary        DOUBLE       not null,
    created_at    datetime     not null,
    updated_at    datetime     null,
    department_id bigint       null,
    constraint employees_pk
        primary key (id)
);

alter table employees
    add constraint employees_departments__fk
        foreign key (department_id) references departments (id);

insert into departments (name, description, created_at, updated_at)
values ('Sales', 'Sales Management', now(), now());
insert into departments (name, description, created_at, updated_at)
values ('Human Resource', 'Human Resource Management', now(), now());
insert into departments (name, description, created_at, updated_at)
values ('Marketing', 'Marketing Management', now(), now());

insert into employees (first_name, last_name, email, salary, created_at, updated_at, department_id)
values ('Ram', 'Shrestha', 'shrestharam@gmail.com', 9000, now(), now(), 1);
insert into employees (first_name, last_name, email, salary, created_at, updated_at, department_id)
values ('Hari', 'Khadka', 'khadkahari@gmail.com', 20000, now(), now(), 2);