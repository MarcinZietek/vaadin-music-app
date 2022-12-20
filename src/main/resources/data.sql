
-- # Publisher
insert into publisher values (1, 'Warner Bros. Records');

-- # Performers
insert into performer values (1,'null', 'null', 'Kimbra');
insert into performer values (2,'Mark', 'Foster', 'null');
insert into performer values (3,'null', 'null', 'A-Trak');

-- # Vows Original release
insert into song values (1, 4.17, 'Settle Down');
insert into song values (2, 4.02, 'Cameo Lover');
insert into song values (3, 4.28, 'Two Way Street');
insert into song values (4, 4.27, 'Old Flame');
insert into song values (5, 3.32, 'Good Intent');
insert into song values (6, 4.02, 'Plain Gold Ring (live)');
insert into song values (7, 4.32, 'Call Me');
insert into song values (8, 3.51, 'Limbo');
insert into song values (9, 5.26, 'Wandering Limbs');
insert into song values (10, 4.06, 'Withdraw');
insert into song values (11, 5.02, 'The Build Up');
insert into song values (12, 2.20, 'Somebody Please (hidden track after 1:00 of silence)');

-- # Vows – North American and European iTunes standard edition + Original release
insert into song values (13, 4.23, 'Something in the Way You Are');
insert into song values (14, 4.39, 'Come into My Head');
insert into song values (15, 3.58, 'Sally I Can See You');
insert into song values (16, 5.07, 'Posse');
insert into song values (17, 3.04, 'Home');
insert into song values (18, 4.16, 'Warrior');

-- -- # Albums
insert into album values (1, 'Vows', '2011-08-29', 'Australia/New Zealand', 1);
insert into album values (2, 'Vows', '2012-05-22', 'North America/European', 1);

-- -- # Albums / Tracks
insert into albums_tracks (album_id, song_id) values (1,1);
insert into albums_tracks (album_id, song_id) values (1,2);
insert into albums_tracks (album_id, song_id) values (1,3);
insert into albums_tracks (album_id, song_id) values (1,4);
insert into albums_tracks (album_id, song_id) values (1,5);
insert into albums_tracks (album_id, song_id) values (1,6);
insert into albums_tracks (album_id, song_id) values (1,7);
insert into albums_tracks (album_id, song_id) values (1,8);
insert into albums_tracks (album_id, song_id) values (1,9);
insert into albums_tracks (album_id, song_id) values (1,10);
insert into albums_tracks (album_id, song_id) values (1,11);
insert into albums_tracks (album_id, song_id) values (1,12);

insert into albums_tracks (album_id, song_id) values (2,1);
insert into albums_tracks (album_id, song_id) values (2,13);
insert into albums_tracks (album_id, song_id) values (2,2);
insert into albums_tracks (album_id, song_id) values (2,3);
insert into albums_tracks (album_id, song_id) values (2,4);
insert into albums_tracks (album_id, song_id) values (2,5);
insert into albums_tracks (album_id, song_id) values (2,6);
insert into albums_tracks (album_id, song_id) values (2,14);
insert into albums_tracks (album_id, song_id) values (2,15);
insert into albums_tracks (album_id, song_id) values (2,16);
insert into albums_tracks (album_id, song_id) values (2,17);
insert into albums_tracks (album_id, song_id) values (2,11);
insert into albums_tracks (album_id, song_id) values (2,18);