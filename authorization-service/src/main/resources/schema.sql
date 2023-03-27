create table users
(
    id         serial primary key,
    email      varchar(255)                                     not null unique,
    first_name varchar(50)                                      not null,
    last_name  varchar(100)                                     not null,
    password   varchar(255)                                     not null,
    role       varchar(20) default 'USER':: character varying   not null,
    status     varchar(20) default 'ACTIVE':: character varying not null
);