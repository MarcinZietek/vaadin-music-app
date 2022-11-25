

-- # Publisher
insert into publisher (id, name) values ('ac63a577-bea6-46f0-af38-424bd354397b', 'Warner Bros. Records');

-- -- # Performers
insert into performer (id, first_name, last_name, pseudonym) values
('72b11cec-0080-496a-a3a9-92494007befb', 'null', 'null', 'Kimbra'),
('5ce3bbc1-6561-4c71-aa33-382692322b40', 'Mark', 'Foster', 'null'),
('d6c3fe4e-e7c9-4961-b7fe-9350242992e9', 'null', 'null', 'A-Trak');
--
-- -- # Vows Original release
insert into song (id, duration, title) values
('983b41a6-f1b6-4309-87df-5bfd2048392f', 4.17, 'Settle Down'),
('b501cfdc-ea0b-4659-b6d5-0449467a9ca0', 4.02, 'Cameo Lover'),
('48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e', 4.28, 'Two Way Street'),
('c58f8dd9-95dc-4d01-836b-4df6b37de527', 4.27, 'Old Flame'),
('8cf87de5-574d-44ab-bf7f-25dec35cca73', 3.32, 'Good Intent'),
('2fb669ec-0659-49fb-af9f-e6f71ce8156f', 4.02, 'Plain Gold Ring (live)'),
('8db2356e-c5a4-461d-b231-6e13d4b48757', 4.32, 'Call Me'),
('d3bbd40d-8394-4e1c-b516-7d7808a08534', 3.51, 'Limbo'),
('dd280051-9ec2-450d-992a-c55b33e45afe', 5.26, 'Wandering Limbs'),
('6bc84da4-cc04-4289-895f-eb8bf50b45a1', 4.06, 'Withdraw'),
('99412746-4875-4693-8afc-a732e5629412', 5.02, 'The Build Up'),
('3f0fafe0-3db8-4b45-baa4-d493d30c019c', 2.20, 'Somebody Please (hidden track after 1:00 of silence)');
--
-- -- # Vows – North American and European iTunes standard edition + Original release
insert into song (id, duration, title) values
('b1f32996-c3bf-42a9-a6d3-8262c4100ff6', 4.23, 'Something in the Way You Are'),
('37f6329e-ff30-48ac-b3aa-aa4f2df0ba80', 4.39, 'Come into My Head'),
('70a4c29c-f813-4818-8434-4ef374679ac1', 3.58, 'Sally I Can See You'),
('764f8256-217e-411c-9377-e1689c1ede54', 5.07, 'Posse'),
('861d4ce6-e2f7-485d-bede-6462e42ac3c8', 3.04, 'Home'),
('cb2be9bc-129e-4218-bc28-802197dfbbf4', 4.16, 'Warrior');

-- # Albums
insert into album (id, released_date, title,publisher_id, region)  values ('ac63a577-bea6-46f0-af38-424bd354397a', '2011-08-29', 'Vows', 'ac63a577-bea6-46f0-af38-424bd354397b', 'Australia / New Zealand');
insert into album (id, released_date, title,publisher_id, region)  values ('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '2012-05-22', 'Vows', 'ac63a577-bea6-46f0-af38-424bd354397b', 'North America / European');

-- -- # Albums / Tracks
insert into albums_tracks (album_id, tracks_id) values
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '983b41a6-f1b6-4309-87df-5bfd2048392f'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', 'b501cfdc-ea0b-4659-b6d5-0449467a9ca0'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', 'c58f8dd9-95dc-4d01-836b-4df6b37de527'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '8cf87de5-574d-44ab-bf7f-25dec35cca73'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '2fb669ec-0659-49fb-af9f-e6f71ce8156f'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '8db2356e-c5a4-461d-b231-6e13d4b48757'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', 'd3bbd40d-8394-4e1c-b516-7d7808a08534'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', 'dd280051-9ec2-450d-992a-c55b33e45afe'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '6bc84da4-cc04-4289-895f-eb8bf50b45a1'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '99412746-4875-4693-8afc-a732e5629412'),
('51df6789-1b4f-4e32-b075-ef99e86ad9a5', '3f0fafe0-3db8-4b45-baa4-d493d30c019c');

insert into albums_tracks (album_id, tracks_id) values
('ac63a577-bea6-46f0-af38-424bd354397a', '983b41a6-f1b6-4309-87df-5bfd2048392f'),
('ac63a577-bea6-46f0-af38-424bd354397a', 'b1f32996-c3bf-42a9-a6d3-8262c4100ff6'),
('ac63a577-bea6-46f0-af38-424bd354397a', 'b501cfdc-ea0b-4659-b6d5-0449467a9ca0'),
('ac63a577-bea6-46f0-af38-424bd354397a', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e'),
('ac63a577-bea6-46f0-af38-424bd354397a', 'c58f8dd9-95dc-4d01-836b-4df6b37de527'),
('ac63a577-bea6-46f0-af38-424bd354397a', '8cf87de5-574d-44ab-bf7f-25dec35cca73'),
('ac63a577-bea6-46f0-af38-424bd354397a', '2fb669ec-0659-49fb-af9f-e6f71ce8156f'),
('ac63a577-bea6-46f0-af38-424bd354397a', '37f6329e-ff30-48ac-b3aa-aa4f2df0ba80'),
('ac63a577-bea6-46f0-af38-424bd354397a', '70a4c29c-f813-4818-8434-4ef374679ac1'),
('ac63a577-bea6-46f0-af38-424bd354397a', '764f8256-217e-411c-9377-e1689c1ede54'),
('ac63a577-bea6-46f0-af38-424bd354397a', '861d4ce6-e2f7-485d-bede-6462e42ac3c8'),
('ac63a577-bea6-46f0-af38-424bd354397a', '3f0fafe0-3db8-4b45-baa4-d493d30c019c'),
('ac63a577-bea6-46f0-af38-424bd354397a', 'cb2be9bc-129e-4218-bc28-802197dfbbf4');