--liquibase formatted sql

--changeset paramonov:1
INSERT INTO news (creation_time, subject, title)
VALUES ('2012-01-01 09:03:00-00',
        'The comedy film "The Dictator" with the famous English actor Sacha Baron Cohen will not be shown in Tajikistan',
        'Comedy film "The Dictator" will not be shown in Tajikistan'),
       ('1222-01-01 10:00:00-05', 'Penguin was killed by Kenny', 'What a joke!'),
       ('2013-01-01 10:00:00-05', 'really nothing', 'nothing'),
       ('2014-01-01 08:00:00-05', 'really nothing', 'And what happened?'),
       ('2015-01-01 10:00:00-05', 'The Russian grandmaster started hard, but changed during the match.',
        'Excellent victory'),
       ('2016-01-01 09:00:00-05', 'The year turned out to be long, very working and with a sad ending.',
        'New Year`s Eve'),
       ('2016-10-19 10:00:00-05',
        'Petersburg concert "Citizens of Demons", in which famous media people read Dmitry Bykov`s poems, has been cancelled.',
        'In St. Petersburg canceled the concert "Citizens of Demons"'),
       ('2018-03-23 12:12:00-05',
        'Donald Trump signed a memorandum "On Combating China`s Economic Aggression", which allowed for restrictions on products from China. According to the head of the White House, the increase in tariffs will cost China about $60 billion. The United States, in particular, introduced import duties on steel at a rate of 25 percent and on aluminum at a rate of 10 percent.',
        'US imposes tariffs on goods from China'),
       ('2018-09-25 10:00:00-05',
        'On October 1, 2018, an amendment to the law "On Education" comes into force, according to which all Russian citizens who call themselves atheists will need to pass an annual qualification exam for atheism. The final list of disciplines for the exam is under approval, but it is known that it will include the synthetic theory of evolution, the basics of molecular genetics, quantum mechanics and special relativity.',
        'Russia will introduce a mandatory exam for atheists'),
       ('2018-01-01 10:00:00-05',
        'In connection with the increasing complaints of British sailors about increased noise and insufficient secrecy of submarines, the British Ministry of Defense decided to create a completely new project of the Silent Shark submarine, the main driving force of which will be specially trained rowers.',
        'UK to build silent paddle-powered submarine'),
       ('2019-01-01 23:54:00-05',
        'Chinese Minister of Social Development and Communist Reform Hong Jing knelt at a press conference to repent to the people for canceling utility bills too late.',
        'China`s Social Development Minister knelt down to repent to the people for the fact that utility bills were canceled just now'),
       ('2020-08-01 10:00:00-05', 'Arsenal 8 - 3 Fulham', 'Glorious victory'),
       ('2020-01-01 10:00:00-05',
        'The US presidential administration has confirmed receipt of a $102,000 cash transfer from the United Nations paid for Donald Trump''s stand-up comedy speech to the General Assembly. The President on your Twitter has already announced his intention to send all the money from this speech, left over after taxes and pension contributions, to charity.',
        'Donald Trump received $102,000 for a stand-up speech at the UN General Assembly'),
       ('2020-01-01 10:00:00-05',
        'At a September 27 press conference, Turkmen President Ko Lastname made an unexpected statement. He spoke about the country''s plans to join NATO, as well as about the obstacles that await it on the way of integration into the European-American community.',
        'For the sake of NATO, Turkmenistan is ready to change the borders of the Atlantic'),
       ('2021-01-01 14:00:00-05',
        'American Jarod Henderson became the winner of the popular musical television project American talent thanks to the performance of Mikhail Krug`s song "Vladimir Central", which was appreciated by both the audience and the jury.',
        'The American won a major vocal competition by performing "Vladimirsky Central"'),
       ('2021-01-01 10:00:00-05', 'subject for title again','title again'),
       ('2022-01-01 10:05:00-04', 'bun hung himself', 'nonsense'),
       ('2022-01-01 10:00:00-05', 'A bad workman blames his tools.', 'IntelliJ IDEA'),
       ('2023-01-01 10:00:00-05', 'A golden key can open any door.', 'reason'),
       ('2023-01-01 10:00:00-05', 'A picture is worth a thousand words.', 'internship');
SELECT SETVAL('news_id_seq', (SELECT MAX(id) FROM news));

--changeset paramonov:2
INSERT INTO users (username, password, role)
VALUES ('fatal', '1234', 'ADMIN'),
       ('error', '1235', 'JOURNALIST'),
       ('throwable', '1236', 'JOURNALIST'),
       ('exception', '1236', 'SUBSCRIBER'),
       ('deploy', '1237', 'SUBSCRIBER');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

--changeset paramonov:3
INSERT INTO comment (creation_time, subject, news_id, user_id)
VALUES ( '2022-01-01 10:00:00-05', 'so what?', 1, 1),
       ( '2022-01-01 10:00:00-05', 'zavod', 1, 2),
       ( '2022-01-01 10:00:00-05', 'some water', 1, 3),
       ( '2022-01-01 10:00:00-05', 'athwart', 1, 3),
       ( '2022-01-01 10:00:00-05', 'noisily', 1, 3),
       ( '2022-01-01 10:00:00-05', 'lettuce', 1, 3),
       ( '2022-01-01 10:00:00-05', 'produce', 1, 3),
       ( '2022-01-01 10:00:00-05', 'instead', 1, 3),
       ( '2022-01-01 10:00:00-05', 'outside', 1, 3),
       ( '2022-01-01 10:00:00-05', 'ovulate', 1, 3),
       ( '2022-01-01 10:00:00-05', 'warrant', 2, 1),
       ( '2022-01-01 10:00:00-05', 'amongst', 2, 2),
       ( '2022-01-01 10:00:00-05', 'apropos', 2, 3),
       ( '2022-01-01 10:00:00-05', 'eminent', 2, 4),
       ( '2022-01-01 10:00:00-05', 'whoever', 2, 1),
       ( '2022-01-01 10:00:00-05', 'because', 2, 2),
       ( '2022-01-01 10:00:00-05', 'worship', 2, 3),
       ( '2022-01-01 10:00:00-05', 'quietly', 2, 4),
       ( '2022-01-01 10:00:00-05', 'acetate', 2, 1),
       ( '2022-01-01 10:00:00-05', 'happily', 2, 2),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 3),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 3, 3),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       ( '2022-01-01 10:00:00-05', 'happily', 3, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 3),
       ( '2022-01-01 10:00:00-05', 'warrant', 3, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 2),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       ( '2022-01-01 10:00:00-05', 'happily', 4, 2),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 2),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       ( '2022-01-01 10:00:00-05', 'happily', 4, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 4, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 4),
       ( '2022-01-01 10:00:00-05', 'happily', 5, 1),
       ( '2022-01-01 10:00:00-05', 'warrant', 5, 3),
       ( '2022-01-01 10:00:00-05', 'happily', 5, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 2),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 2),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 4),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 6, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 3),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 2),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 3),
       ( '2022-01-01 10:00:00-05', 'whoequietlyver', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 7, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 3),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 8, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 2),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 4),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 8, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 4),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'whoever', 9, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 2),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 10, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 3),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 11, 2),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 4),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 12, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 3),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 4),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 13, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 2),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 14, 3),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 4),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 4),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 2),
       ( '2022-01-01 10:00:00-05', 'quietly', 15, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 16, 2),
       ( '2022-01-01 10:00:00-05', 'Comment', 16, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 17, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 3),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'a chto, tut vse anglichane?', 18, 4),
       ( '2022-01-01 10:00:00-05', 'quietly', 18, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 19, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'net, mi v vdoem', 20, 2),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1),
       ( '2022-01-01 10:00:00-05', 'quietly', 20, 1);
SELECT SETVAL('comment_id_seq', (SELECT MAX(id) FROM comment));
