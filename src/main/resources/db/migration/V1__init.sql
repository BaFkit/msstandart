create table orders
(
    id                           bigserial primary key,
    full_name                    varchar(255) not null,
    phone                        varchar(12)  not null,
    phone_second                 varchar(12),
    address                      varchar(255),
    email                        varchar(255),
    id_stone                     bigint,
    vendor_code                  varchar(10),
    monument_cost                numeric(8, 2),
    stone_material               varchar(255),
    stone_polishing              varchar(255),
    stone_size                   varchar(255),
    stone_kit                    varchar(255),
    stone_kit_cost               numeric(8, 2),
    stone_figure                 varchar(255),
    name_on_monument             varchar(255),
    name_on_monument_cost        numeric(8, 2),
    date_on_monument             varchar(255),
    date_on_monument_cost        numeric(8, 2),
    holes_in_stand               int,
    holes_in_stand_cost          numeric(8, 2),
    portrait                     varchar(255),
    portrait_cost                numeric(8, 2),
    tile                         varchar(255),
    tile_cost                    numeric(8, 2),
    tile_fastening_cost          numeric(8, 2),
    portrait_fastening_cost      numeric(8, 2),
    crucifix                     varchar(255),
    crucifix_cost                numeric(8, 2),
    flowers                      varchar(255),
    flowers_cost                 numeric(8, 2),
    picture_one                  varchar(255),
    picture_one_cost             numeric(8, 2),
    picture_two                  varchar(255),
    picture_two_cost             numeric(8, 2),
    picture_three                varchar(255),
    picture_three_cost           numeric(8, 2),
    frame                        varchar(255),
    frame_cost                   numeric(8, 2),
    epitaph                      text,
    epitaph_cost                 numeric(8, 2),
    other_works_on_monument      text,
    other_works_on_monument_cost numeric(8, 2),
    installation_location        varchar(255),
    monument_installation_cost   numeric(8, 2),
    other_installation           varchar(255),
    other_installation_cost      numeric(8, 2),
    other_info                   text,
    stone_cost                   numeric(8, 2),
    work_cost                    numeric(8, 2),
    installation_cost            numeric(8, 2),
    order_cost                   numeric(8, 2),
    order_locations              varchar(80),
    status                       varchar(50),
    preview_image_id             bigint,
    created_at                   timestamp default current_timestamp,
    updated_at                   timestamp default current_timestamp
);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    location   varchar(80) not null,
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

create table images
(
    id                 bigserial primary key,
    name               varchar(255),
    original_file_name varchar(255),
    size               bigint not null,
    content_type       varchar(50),
    is_preview_image   boolean,
    bytes              bytea,
    order_id           bigserial references orders (id),
    created_at         timestamp default current_timestamp,
    updated_at         timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_MANAGER1'),
       ('ROLE_MANAGER2'),
       ('ROLE_MAKE'),
       ('ROLE_ADMIN'),
       ('ROLE_SYSADMIN');

insert into users (username, password, location, email)
values ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin', 'admin@local.org'),
       ('director', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Кинешма', 'director@local.org'),
       ('manager1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Кинешма', 'manager1@local.org'),
       ('manager2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Заволжск', 'manager2@local.org'),
       ('manufacture', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Производство', 'manufacture@local.org');

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 5),
       (1, 6),
       (2, 1),
       (2, 5),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 3),
       (5, 1),
       (5, 4);