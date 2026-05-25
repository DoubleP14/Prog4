CREATE DATABASE potzh_pet_database;
GRANT ALL PRIVILEGES ON potzh_pet_database.* to 'pet_admin_user';

CREATE TABLE potzh_pet_database.pet (
                       ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       pet_name varchar(255),
                       species varchar(255),
                       age int,
                       owner_name varchar(255)
);

CREATE TABLE potzh_pet_database.user (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      username varchar(255) NOT NULL UNIQUE,
                      password varchar(100) NOT NULL
);

CREATE TABLE potzh_pet_database.role (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      code varchar(255) NOT NULL UNIQUE,
                      description varchar(255)
);

create table potzh_pet_database.user_role (
                           user_id int,
                           role_id int,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES potzh_pet_database.user(id),
                           FOREIGN KEY (role_id) REFERENCES potzh_pet_database.role(id)
);

insert into potzh_pet_database.pet (pet_name, species, age, owner_name) values ('Cirmi', 'Macska', 3, 'Kiss Béla');
insert into potzh_pet_database.pet (pet_name, species, age, owner_name) values ('Bodri', 'Kutya', 5, 'Nagy Anna');
insert into potzh_pet_database.pet (pet_name, species, age, owner_name) values ('Pihe', 'Nyúl', 2, 'Tóth Gergő');
insert into potzh_pet_database.pet (pet_name, species, age, owner_name) values ('Morzsi', 'Hörcsög', 1, 'Szabó Lili');
insert into potzh_pet_database.pet (pet_name, species, age, owner_name) values ('Luna', 'Macska', 4, 'Kovács Petra');

insert into potzh_pet_database.role (code, description) values ('searcher', 'Use the web app.');
insert into potzh_pet_database.role (code, description) values ('creator', 'Has right to create new objects.');

insert into potzh_pet_database.user (username, password) values ('user1', '$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.');
insert into potzh_pet_database.user (username, password) values ('user2', '$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.');

insert into potzh_pet_database.user_role (user_id, role_id) values ((select id from potzh_pet_database.user where username = 'user1'), (select id from potzh_pet_database.role where code = 'searcher'));
insert into potzh_pet_database.user_role (user_id, role_id) values ((select id from potzh_pet_database.user where username = 'user2'), (select id from potzh_pet_database.role where code = 'searcher'));
insert into potzh_pet_database.user_role (user_id, role_id) values ((select id from potzh_pet_database.user where username = 'user2'), (select id from potzh_pet_database.role where code = 'creator'));
