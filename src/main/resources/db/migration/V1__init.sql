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