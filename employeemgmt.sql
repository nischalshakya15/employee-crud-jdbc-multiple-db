create table departments
(
    id          bigint auto_increment,
    name        varchar(255) null,
    description varchar(255) null,
    created_at  datetime     not null,
    updated_at  datetime     null,
    constraint departments_pk
        primary key (id)
)
    comment 'contains department information';

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
