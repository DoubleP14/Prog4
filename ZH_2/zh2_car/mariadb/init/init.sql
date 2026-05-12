CREATE DATABASE zh2_db;
GRANT ALL PRIVILEGES ON zh2_db.* to 'mariadb';

CREATE TABLE zh2_db.car (
                     ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     manufacturer varchar(255),
                     type varchar(255),
                     license_plate varchar(255)
);

CREATE TABLE zh2_db.user (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      username varchar(255) NOT NULL UNIQUE,
                      pass varchar(100) NOT NULL
);

CREATE TABLE zh2_db.role (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      code varchar(255) NOT NULL UNIQUE,
                      description varchar(255)
);

create table zh2_db.user_role (
                           user_id int,
                           role_id int,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES user(id),
                           FOREIGN KEY (role_id) REFERENCES role(id)
);

insert into zh2_db.car (manufacturer, type, license_plate) values ("Ford", "Mustang", "SGP-234");
insert into zh2_db.car (manufacturer, type, license_plate) values ("Ford", "Range rover", "SXT-642");
insert into zh2_db.car (manufacturer, type, license_plate) values ("Lada", "Niva", "AAC-634");
insert into zh2_db.car (manufacturer, type, license_plate) values ("Peugeot", "307", "ILF-307");
insert into zh2_db.car (manufacturer, type, license_plate) values ("Toyota", "Corolla", "SYV-323");

insert into zh2_db.role (code, description) values ("user", "Use the web app.");
insert into zh2_db.role (code, description) values ("creator", "Has right to create new objects.");

insert into zh2_db.user (username, pass) values ("user1", "$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.");
insert into zh2_db.user (username, pass) values ("user2", "$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.");

insert into zh2_db.user_role (user_id, role_id) values ((select id from zh2_db.user where username = "user1"), (select id from zh2_db.role where code = "user"));
insert into zh2_db.user_role (user_id, role_id) values ((select id from zh2_db.user where username = "user2"), (select id from zh2_db.role where code = "user"));
insert into zh2_db.user_role (user_id, role_id) values ((select id from zh2_db.user where username = "user2"), (select id from zh2_db.role where code = "creator"));
