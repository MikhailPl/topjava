DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (description, calories, user_id, date_time)
VALUES ('завтрак',  250, 100000, timestamp '2020-10-23 07:30'),
       ('обед',     450, 100000, timestamp '2020-10-23 12:30'),
       ('ужин',     550, 100000, timestamp '2020-10-23 18:30'),
       ('завтрак',  300, 100000, timestamp '2020-10-24 08:15'),
       ('обед',     500, 100000, timestamp '2020-10-24 13:15'),
       ('ужин',     550, 100000, timestamp '2020-10-24 19:25'),

       ('завтрак',  250, 100001, timestamp '2020-10-23 07:45'),
       ('обед',     450, 100001, timestamp '2020-10-23 12:45'),
       ('ужин',     550, 100001, timestamp '2020-10-23 18:45'),
       ('завтрак',  300, 100001, timestamp '2020-10-24 08:10'),
       ('обед',     500, 100001, timestamp '2020-10-24 13:10'),
       ('ужин',     550, 100001, timestamp '2020-10-24 19:10')
;
