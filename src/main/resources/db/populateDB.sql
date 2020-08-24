DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Завтрак', '2020-01-30 10:00', 500),
       (100000, 'Обед', '2020-01-30 13:00', 1000),
       (100000, 'Ужин', '2020-01-30 20:00', 500),
       (100000, 'Еда на граничное значение', '2020-01-31 00:00', 100),
       (100000, 'Завтрак', '2020-01-31 10:00', 1000),
       (100000, 'Обед', '2020-01-31 13:00', 500),
       (100000, 'Ужин', '2020-01-31 20:00', 410),
       (100001, 'Завтрак админ', '2020-08-24 14:00', 600),
       (100001, 'Ужин админ', '2020-08-24 21:00', 1500);