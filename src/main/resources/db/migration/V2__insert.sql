insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_KINESHMA'),
       ('ROLE_ZAVOLZHSK'),
       ('ROLE_MAKER'),
       ('ROLE_ADMIN'),
       ('ROLE_SYSADMIN');

insert into users (username, first_name, password, location, email)
values ('admin', 'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin', 'admin@local.org'),
       ('director', 'director', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Кинешма', 'director@local.org'),
       ('manager1', 'manager1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Кинешма', 'manager1@local.org'),
       ('manager2', 'manager2','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Заволжск', 'manager2@local.org'),
       ('maker', 'maker','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Производство', 'manufacture@local.org');

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2),
       (1, 6),
       (1, 7),
       (2, 1),
       (2, 2),
       (2, 6),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 4),
       (5, 1),
       (5, 5);

insert into options (name, letter_name_cost, letter_epitaph_cost, digit_cost)
values ('Price', 35, 35, 35);