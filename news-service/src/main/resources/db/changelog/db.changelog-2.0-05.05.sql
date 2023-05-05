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
VALUES (1, 'username1', '1234', 'ADMIN'),
       (2, 'username2', '1235', 'JOURNALIST'),
       (3, 'username3', '1236', 'JOURNALIST'),
       (4, 'username4', '1237', 'SUBSCRIBER');

--changeset paramonov:3
INSERT INTO comment (id, creation_time, subject, news_id, user_id)
VALUES (1, '2022-01-01 10:00:00-05', 'so what?', 1, 1),
       (2, '2022-01-01 10:00:00-05', 'zavod', 1, 2),
       (3, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (4, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (5, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (6, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (7, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (8, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (9, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (10, '2022-01-01 10:00:00-05', 'some water', 1, 3),
       (11, '2022-01-01 10:00:00-05', 'subject', 2, 1),
       (12, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (13, '2022-01-01 10:00:00-05', 'subject', 2, 1),
       (14, '2022-01-01 10:00:00-05', 'subject', 2, 3),
       (15, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (16, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (17, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (18, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (19, '2022-01-01 10:00:00-05', 'subject', 2, 4),
       (20, '2022-01-01 10:00:00-05', 'subject', 2, 4),





