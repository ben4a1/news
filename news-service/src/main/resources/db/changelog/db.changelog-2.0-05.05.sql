--liquibase formatted sql

--changeset paramonov:1
INSERT INTO news (id, creation_time, subject, title)
VALUES (1, '2012-01-01 09:03:00-00',
        'The comedy film "The Dictator" with the famous English actor Sacha Baron Cohen will not be shown in Tajikistan',
        'Comedy film "The Dictator" will not be shown in Tajikistan'),
       (2, '1222-01-01 10:00:00-05', 'Penguin was killed by Kenny', 'What a joke!'),
       (3, '2013-01-01 10:00:00-05', 'really nothing', 'nothing'),
       (4, '2014-01-01 08:00:00-05', 'really nothing', 'And what happened?'),
       (5, '2015-01-01 10:00:00-05', 'The Russian grandmaster started hard, but changed during the match.',
        'Excellent victory'),
       (6, '2016-01-01 09:00:00-05', 'The year turned out to be long, very working and with a sad ending.',
        'New Year`s Eve'),
       (7, '2016-10-19 10:00:00-05',
        'Petersburg concert "Citizens of Demons", in which famous media people read Dmitry Bykov`s poems, has been cancelled.',
        'In St. Petersburg canceled the concert "Citizens of Demons"'),
       (8, '2018-03-23 12:12:00-05',
        'Donald Trump signed a memorandum "On Combating China`s Economic Aggression", which allowed for restrictions on products from China. According to the head of the White House, the increase in tariffs will cost China about $60 billion. The United States, in particular, introduced import duties on steel at a rate of 25 percent and on aluminum at a rate of 10 percent.',
        'US imposes tariffs on goods from China'),
       (9, '2018-09-25 10:00:00-05',
        'On October 1, 2018, an amendment to the law "On Education" comes into force, according to which all Russian citizens who call themselves atheists will need to pass an annual qualification exam for atheism. The final list of disciplines for the exam is under approval, but it is known that it will include the synthetic theory of evolution, the basics of molecular genetics, quantum mechanics and special relativity.',
        'Russia will introduce a mandatory exam for atheists'),
       (10, '2018-01-01 10:00:00-05',
        'In connection with the increasing complaints of British sailors about increased noise and insufficient secrecy of submarines, the British Ministry of Defense decided to create a completely new project of the Silent Shark submarine, the main driving force of which will be specially trained rowers.',
        'UK to build silent paddle-powered submarine'),
       (11, '2019-01-01 23:54:00-05',
        'Chinese Minister of Social Development and Communist Reform Hong Jing knelt at a press conference to repent to the people for canceling utility bills too late.',
        'China`s Social Development Minister knelt down to repent to the people for the fact that utility bills were canceled just now'),
       (12, '2020-08-01 10:00:00-05', 'Arsenal 8 - 3 Fulham', 'Glorious victory'),
       (13, '2020-01-01 10:00:00-05',
        'The US presidential administration has confirmed receipt of a $102,000 cash transfer from the United Nations paid for Donald Trump''s stand-up comedy speech to the General Assembly. The President on your Twitter has already announced his intention to send all the money from this speech, left over after taxes and pension contributions, to charity.',
        'Donald Trump received $102,000 for a stand-up speech at the UN General Assembly'),
       (14, '2020-01-01 10:00:00-05',
        'At a September 27 press conference, Turkmen President Ko Lastname made an unexpected statement. He spoke about the country''s plans to join NATO, as well as about the obstacles that await it on the way of integration into the European-American community.',
        'For the sake of NATO, Turkmenistan is ready to change the borders of the Atlantic'),
       (15, '2021-01-01 14:00:00-05',
        'American Jarod Henderson became the winner of the popular musical television project American talent thanks to the performance of Mikhail Krug`s song "Vladimir Central", which was appreciated by both the audience and the jury.',
        'The American won a major vocal competition by performing "Vladimirsky Central"'),
       (16, '2021-01-01 10:00:00-05', 'subject for title again','title again'),
       (17, '2022-01-01 10:05:00-04', 'bun hung himself', 'nonsense'),
       (18, '2022-01-01 10:00:00-05', 'A bad workman blames his tools.', 'IntelliJ IDEA'),
       (19, '2023-01-01 10:00:00-05', 'A golden key can open any door.', 'reason'),
       (20, '2023-01-01 10:00:00-05', 'A picture is worth a thousand words.', 'internship');

--changeset paramonov:2
INSERT INTO users (id, username, password, role)
VALUES (1, 'fatal', '1234', 'ADMIN'),
       (2, 'error', '1235', 'JOURNALIST'),
       (3, 'throwable', '1236', 'JOURNALIST'),
       (4, 'exception', '1236', 'SUBSCRIBER'),
       (5, 'deploy', '1237', 'SUBSCRIBER');

--changeset paramonov:3
INSERT INTO comment (id, creation_time, subject, news_id, user_id)
VALUES (1, '2022-01-01 10:00:00-05', 'so what?', 1, 1),
       (2, '2022-01-01 10:00:00-05', 'zavod', 1, 2),
       (3, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (4, '2022-01-01 10:00:00-05', 'athwart', 1, 3),
       (5, '2022-01-01 10:00:00-05', 'noisily', 1, 3),
       (6, '2022-01-01 10:00:00-05', 'lettuce', 1, 3),
       (7, '2022-01-01 10:00:00-05', 'produce', 1, 3),
       (8, '2022-01-01 10:00:00-05', 'instead', 1, 3),
       (9, '2022-01-01 10:00:00-05', 'outside', 1, 3),
       (10, '2022-01-01 10:00:00-05', 'ovulate', 1, 3),
       (11, '2022-01-01 10:00:00-05', 'warrant', 2, 1),
       (12, '2022-01-01 10:00:00-05', 'amongst', 2, 2),
       (13, '2022-01-01 10:00:00-05', 'apropos', 2, 3),
       (14, '2022-01-01 10:00:00-05', 'eminent', 2, 4),
       (15, '2022-01-01 10:00:00-05', 'whoever', 2, 1),
       (16, '2022-01-01 10:00:00-05', 'because', 2, 2),
       (17, '2022-01-01 10:00:00-05', 'worship', 2, 3),
       (18, '2022-01-01 10:00:00-05', 'quietly', 2, 4),
       (19, '2022-01-01 10:00:00-05', 'acetate', 2, 1),
       (20, '2022-01-01 10:00:00-05', 'happily', 2, 2),
       (21, '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       (22, '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       (23, '2022-01-01 10:00:00-05', 'warrant', 3, 3),
       (24, '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       (25, '2022-01-01 10:00:00-05', 'whoever', 3, 3),
       (26, '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       (27, '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       (28, '2022-01-01 10:00:00-05', 'happily', 3, 1),
       (29, '2022-01-01 10:00:00-05', 'warrant', 3, 3),
       (30, '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       (31, '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       (32, '2022-01-01 10:00:00-05', 'warrant', 4, 2),
       (33, '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       (34, '2022-01-01 10:00:00-05', 'happily', 4, 2),
       (35, '2022-01-01 10:00:00-05', 'warrant', 4, 2),
       (36, '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       (37, '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       (38, '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       (39, '2022-01-01 10:00:00-05', 'happily', 4, 1),
       (40, '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       (41, '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       (42, '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       (43, '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       (44, '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       (45, '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       (46, '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       (47, '2022-01-01 10:00:00-05', 'warrant', 5, 4),
       (48, '2022-01-01 10:00:00-05', 'happily', 5, 1),
       (49, '2022-01-01 10:00:00-05', 'warrant', 5, 3),
       (50, '2022-01-01 10:00:00-05', 'happily', 5, 1),
       (51, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (52, '2022-01-01 10:00:00-05', 'whoever', 6, 2),
       (53, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (54, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (55, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (56, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (57, '2022-01-01 10:00:00-05', 'whoever', 6, 2),
       (58, '2022-01-01 10:00:00-05', 'whoever', 6, 4),
       (59, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (60, '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       (61, '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       (62, '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       (63, '2022-01-01 10:00:00-05', 'whoever', 7, 3),
       (64, '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       (65, '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       (66, '2022-01-01 10:00:00-05', 'whoever', 7, 2),
       (67, '2022-01-01 10:00:00-05', 'whoever', 7, 3),
       (68, '2022-01-01 10:00:00-05', 'whoequietlyver', 7, 1),
       (69, '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       (70, '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       (71, '2022-01-01 10:00:00-05', 'whoever', 8, 3),
       (72, '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       (73, '2022-01-01 10:00:00-05', 'quietly', 8, 1),
       (74, '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       (75, '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       (76, '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       (77, '2022-01-01 10:00:00-05', 'whoever', 8, 2),
       (78, '2022-01-01 10:00:00-05', 'whoever', 8, 4),
       (79, '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       (80, '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       (81, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (82, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (83, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (84, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (85, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (86, '2022-01-01 10:00:00-05', 'whoever', 9, 4),
       (87, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (88, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (89, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (90, '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       (91, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (92, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (93, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (94, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (95, '2022-01-01 10:00:00-05', 'quietly', 10, 2),
       (96, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (97, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (98, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (99, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (100, '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       (101, '2022-01-01 10:00:00-05', 'quietly', 11, 3),
       (102, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (103, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (104, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (105, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (106, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (107, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (108, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (109, '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       (110, '2022-01-01 10:00:00-05', 'quietly', 11, 2),
       (111, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (112, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (113, '2022-01-01 10:00:00-05', 'quietly', 12, 4),
       (114, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (115, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (116, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (117, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (118, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (119, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (120, '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       (121, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (122, '2022-01-01 10:00:00-05', 'quietly', 13, 3),
       (123, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (124, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (125, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (126, '2022-01-01 10:00:00-05', 'quietly', 13, 4),
       (127, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (128, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (129, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (130, '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       (131, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (132, '2022-01-01 10:00:00-05', 'quietly', 14, 2),
       (133, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (134, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (135, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (136, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (137, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (138, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (139, '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       (140, '2022-01-01 10:00:00-05', 'quietly', 14, 3),
       (141, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (142, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (143, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (144, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (145, '2022-01-01 10:00:00-05', 'quietly', 15, 4),
       (146, '2022-01-01 10:00:00-05', 'quietly', 15, 4),
       (147, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (148, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (149, '2022-01-01 10:00:00-05', 'quietly', 15, 2),
       (150, '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       (151, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (152, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (153, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (154, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (155, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (156, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (157, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (158, '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       (159, '2022-01-01 10:00:00-05', 'quietly', 16, 2),
       (160, '2022-01-01 10:00:00-05', 'Comment', 16, 1),
       (161, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (162, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (163, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (164, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (165, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (166, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (167, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (168, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (169, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (170, '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       (171, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (172, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (173, '2022-01-01 10:00:00-05', 'quietly', 18, 3),
       (174, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (175, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (176, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (177, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (178, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (179, '2022-01-01 10:00:00-05', 'a chto, tut vse anglichane?', 18, 4),
       (180, '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       (181, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (182, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (183, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (184, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (185, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (186, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (187, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (188, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (189, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (190, '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       (191, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (192, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (193, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (194, '2022-01-01 10:00:00-05', 'net, mi v vdoem', 20, 2),
       (195, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (196, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (197, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (198, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (199, '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       (200, '2022-01-01 10:00:00-05', 'quietly', 20, 1);