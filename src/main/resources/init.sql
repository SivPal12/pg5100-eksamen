INSERT INTO Genre VALUES (1,'pop');
INSERT INTO Genre VALUES (2,'blues');
INSERT INTO Genre VALUES (3,'country');
INSERT INTO Genre VALUES (4,'hard');
INSERT INTO Genre VALUES (5,'rock');

INSERT INTO Artist VALUES (1,'Buckcherry',5);
INSERT INTO Artist VALUES (2,'Johnny Cash',3);
INSERT INTO Artist VALUES (3,'Seether',5);
INSERT INTO Artist VALUES (4,'Meghan Trainor',1);
INSERT INTO Artist VALUES (5,'The Blues Brothers',2);
INSERT INTO Artist VALUES (5,'AC/DC',4);
INSERT INTO Artist VALUES (5,'Kiss',2);

insert into Concert ( id, date, description, location, name, numtickets, price, artist_id, ticketssold) values
    ( 1, '2015-01-11', 'Desc', 'Loc', 'Concert name1', 500, 9001, 1, 0),
    ( 2, '2015-02-12', 'Desc', 'Loc', 'Concert name2', 500, 9001, 2, 0),
    ( 3, '2015-03-13', 'Desc', 'Loc', 'Concert name3', 500, 9001, 3, 0),
    ( 4, '2015-04-14', 'Desc', 'Loc', 'Concert name4', 500, 9001, 4, 0),
    ( 5, '2016-04-14', 'Desc', 'Loc', 'Top1', 1000, 9001, 4, 1000),
    ( 6, '2016-04-14', 'Desc', 'Loc', 'Top2', 1000, 9001, 4, 999),
    ( 7, '2016-04-14', 'Desc', 'Loc', 'Top3', 1000, 9001, 4, 998),
    ( 8, '2016-04-14', 'Desc', 'Loc', 'Top4', 1000, 9001, 4, 997),
    ( 9, '2016-04-14', 'Desc', 'Loc', 'Top5', 1000, 9001, 4, 996);
