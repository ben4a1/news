TRUNCATE comment, news, users;

INSERT INTO news (id, creation_time, subject, title)
VALUES (1, '2021-01-01 10:00:00-05', 'subject', 'title'),
       (2, '2021-01-01 10:00:00-05', 'subject', 'title'),
       (3, '2021-01-01 10:00:00-05', 'subject', 'title'),
       (4, '2021-01-01 10:00:00-05', 'subject', 'title');
SELECT SETVAL('news_id_seq', (SELECT MAX(id) FROM news));

INSERT INTO users (id, username, password, role)
VALUES (1, 'username1', '1234', 'ADMIN'),
       (2, 'username2', '1235', 'JOURNALIST'),
       (3, 'username3', '1236', 'JOURNALIST'),
       (4, 'username4', '1237', 'SUBSCRIBER');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO comment (id, creation_time, subject, news_id, user_id)
VALUES (1, '2022-01-01 10:00:00-05', 'subject', 1, 1),
       (2, '2022-01-01 10:00:00-05', 'subject', 1, 2),
       (3, '2022-01-01 10:00:00-05', 'subject', 1, 3),
       (4, '2022-01-01 10:00:00-05', 'subject', 2, 1),
       (5, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (6, '2022-01-01 10:00:00-05', 'subject', 3, 1),
       (7, '2022-01-01 10:00:00-05', 'subject', 4, 3),
       (8, '2022-01-01 10:00:00-05', 'subject', 4, 4);
SELECT SETVAL('comment_id_seq', (SELECT MAX(id) FROM comment));