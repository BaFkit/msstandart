create table orders
(
    id             bigserial primary key,
    full_name      varchar(255) not null,
    phone          varchar(12)  not null,
    address        varchar(255),
    email          varchar(50),
    id_stone       bigint,
    stone_material varchar(50),
    stone_size     varchar(50),
    stone_kit      varchar(255),
    stone_figure   varchar(50),
    work_package   text,
    stone_cost     numeric(8, 2),
    work_cost      numeric(8, 2),
    order_cost     numeric(8, 2),
    status         varchar(50),
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin_test@gmail.com'),
       ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user_test@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

insert into orders(full_name, phone, address, email, id_stone, stone_material, stone_size, stone_kit, stone_figure, work_package, stone_cost, work_cost, order_cost, status)
values ('Грибов Виктор Сергеевич', '+79454587156', 'Московская обл. г. Пушкино ул. Гоголя д. 64 кв. 7', 'gribov34@mail.ru', '0', 'Гранит', '100x50x7', '60x25x12 100x5x8 50x5x8', 'Волна', 'портрет фио березка лпс', 25000.00, 8456.00, 33456.00, 'Новый'),
('Грибов Виктор Сергеевич', '+79454587156', 'Московская обл. г. Пушкино ул. Гоголя д. 64 кв. 7', 'gribov34@mail.ru', '0', 'Гранит', '100x50x7', '60x25x12 100x5x8 50x5x8', 'Волна', 'портрет фио березка лпс', 25000.00, 8456.00, 33456.00, 'Новый'),
('Грибов Виктор Сергеевич', '+79454587156', 'Московская обл. г. Пушкино ул. Гоголя д. 64 кв. 7', 'gribov34@mail.ru', '0', 'Гранит', '100x50x7', '60x25x12 100x5x8 50x5x8', 'Волна', 'портрет фио березка лпс', 25000.00, 8456.00, 33456.00, 'Новый'),
('Грибов Виктор Сергеевич', '+79454587156', 'Московская обл. г. Пушкино ул. Гоголя д. 64 кв. 7', 'gribov34@mail.ru', '0', 'Гранит', '100x50x7', '60x25x12 100x5x8 50x5x8', 'Волна', 'портрет фио березка лпс', 25000.00, 8456.00, 33456.00, 'Новый');