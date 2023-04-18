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