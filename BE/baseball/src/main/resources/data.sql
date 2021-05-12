INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Captain', 'selected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Marvel', 'unselected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Twins', 'selected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Tigers', 'selected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Rockets', 'selected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Dodgers', 'selected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Heros', 'selected');
INSERT INTO `baseball`.`team` (`name`, `status`) VALUES ('Pintos', 'selected');

INSERT INTO `baseball`.`game` (`home_team_id`, `away_team_id`) VALUES (2, 1);
INSERT INTO `baseball`.`game` (`home_team_id`, `away_team_id`) VALUES (4, 3);
INSERT INTO `baseball`.`game` (`home_team_id`, `away_team_id`) VALUES (6, 5);
INSERT INTO `baseball`.`game` (`home_team_id`, `away_team_id`) VALUES (8, 7);

INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '김광진', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '이동규', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '김진수', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '박영권', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '추신수', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '이용대', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '류현진', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '최동수', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '한양범', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (1, '리아', '투수');

INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '시온', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '롤로', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '사사', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '가가', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '나나', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '다다', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '라라', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '마마', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '바바', '타자');
INSERT INTO `baseball`.`player` (`team_id`, `name`, `position`) VALUES (2, '최동원', '투수');