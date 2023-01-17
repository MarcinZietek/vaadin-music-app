
-- # Publisher
insert into publisher values ('72b11cec-0080-496a-a3a9-92494007befb', 'Warner Bros. Records');
insert into publisher values ('5ce3bbc1-6561-4c71-aa33-382692322b40', 'Sony Records');
insert into publisher values ('d6c3fe4e-e7c9-4961-b7fe-9350242992e9', 'EMI / Parlophone');
insert into publisher values ('983b41a6-f1b6-4309-87df-5bfd2048392f', 'Hollywood');

-- # Performers
insert into performer values ('b501cfdc-ea0b-4659-b6d5-0449467a9ca0', 'null', 'null', 'Kimbra');
insert into performer values ('8db2356e-c5a4-461d-b231-6e13d4b48757', 'Mark', 'Foster', 'null');
insert into performer values ('51df6789-1b4f-4e32-b075-ef99e86ad9a5', 'null', 'null', 'A-Trak');

-- # Albums
insert into album values ('48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e', 'ART_POP', 'Australia / New Zealand', '2011-08-29', 'Vows', '72b11cec-0080-496a-a3a9-92494007befb');
insert into album values ('c58f8dd9-95dc-4d01-836b-4df6b37de527', 'ART_POP', 'North America / European', '2012-05-22', 'Vows', '72b11cec-0080-496a-a3a9-92494007befb');
insert into album values ('8cf87de5-574d-44ab-bf7f-25dec35cca73', 'ROCK', 'UK / European', '1975-11-21', 'A Night At The Opera ', 'd6c3fe4e-e7c9-4961-b7fe-9350242992e9');
insert into album values ('2fb669ec-0659-49fb-af9f-e6f71ce8156f', 'ROCK', 'North America', '1975-12-02', 'A Night At The Opera', '983b41a6-f1b6-4309-87df-5bfd2048392f');

-- # Vows Original release
insert into song values ('d3bbd40d-8394-4e1c-b516-7d7808a08534', 4.17, 'Settle Down' ,'48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('dd280051-9ec2-450d-992a-c55b33e45afe', 4.02, 'Cameo Lover', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('6bc84da4-cc04-4289-895f-eb8bf50b45a1', 4.28, 'Two Way Street', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('99412746-4875-4693-8afc-a732e5629412', 4.27, 'Old Flame', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('3f0fafe0-3db8-4b45-baa4-d493d30c019c', 3.32, 'Good Intent', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('b1f32996-c3bf-42a9-a6d3-8262c4100ff6', 4.02, 'Plain Gold Ring (live)', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('37f6329e-ff30-48ac-b3aa-aa4f2df0ba80', 4.32, 'Call Me', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('70a4c29c-f813-4818-8434-4ef374679ac1', 3.51, 'Limbo', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('764f8256-217e-411c-9377-e1689c1ede54', 5.26, 'Wandering Limbs', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('861d4ce6-e2f7-485d-bede-6462e42ac3c8', 4.06, 'Withdraw', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('cb2be9bc-129e-4218-bc28-802197dfbbf4', 5.02, 'The Build Up', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');
insert into song values ('ac63a577-bea6-46f0-af38-424bd354397a', 2.20, 'Somebody Please (hidden track after 1:00 of silence)', '48767ba4-a9a2-4c0b-9ce9-cf0c53e8e45e');

-- -- # Vows – North American and European iTunes standard edition + Original release
-- insert into song values ('41a54093-0e0e-4cb9-99b0-d387f4417f84', 4.23, 'Something in the Way You Are');
-- insert into song values ('62ceb860-f08a-49fd-8e56-d87034ade042', 4.39, 'Come into My Head');
-- insert into song values ('30250d77-f781-496c-824f-8ca0b35954fe', 3.58, 'Sally I Can See You');
-- insert into song values ('a1d172ae-1516-4b91-a9b4-bf0c0068606e', 5.07, 'Posse');
-- insert into song values ('15d87411-5e30-4f5e-928e-caf2205a785e', 3.04, 'Home');
-- insert into song values ('af5d1ecd-1c2d-48ef-a3a4-8a0e923d0d75', 4.16, 'Warrior');

-- # A Night at the Opera
insert into song values ('41a54093-0e0e-4cb9-99b0-d387f4417f84', 3.43, 'Death on Two Legs (Dedicated to...)', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('62ceb860-f08a-49fd-8e56-d87034ade042', 1.08, 'Lazing on a Sunday Afternoon', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('30250d77-f781-496c-824f-8ca0b35954fe', 3.05, 'I''m in Love with My Car', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('a1d172ae-1516-4b91-a9b4-bf0c0068606e', 2.50, 'You are My Best Friend', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('15d87411-5e30-4f5e-928e-caf2205a785e', 3.30, '39', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('af5d1ecd-1c2d-48ef-a3a4-8a0e923d0d75', 4.01, 'Sweet Lady', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('c67b6d2e-3f88-4724-acba-e7dec819d535', 8.21, 'The Prophet''s Song', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('2b836024-a9e1-43e3-be20-7a02d28975c8', 3.38, 'Love of My Life', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('c3698fbf-194f-41c8-8391-e890d5bd4c55', 3.26, 'Good Company', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('489e5de7-115f-46e4-b881-22aeea879cd7', 5.55, 'Bohemian Rhapsody', '8cf87de5-574d-44ab-bf7f-25dec35cca73');
insert into song values ('128dde1f-46ad-4401-8901-6097a6e4dc37', 1.11, 'God Save the Queen', '8cf87de5-574d-44ab-bf7f-25dec35cca73');