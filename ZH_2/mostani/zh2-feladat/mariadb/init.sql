CREATE DATABASE zh2_2026_database;
GRANT ALL PRIVILEGES ON zh2_2026_database.* to 'zh2_user_2026';

CREATE TABLE zh2_2026_database.car (
                     ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     type varchar(255),
                     model varchar(255),
                     production_year varchar(4),
                     list_price varchar(255)
);

CREATE TABLE zh2_2026_database.user (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      username varchar(255) NOT NULL UNIQUE,
                      password varchar(100) NOT NULL
);

CREATE TABLE zh2_2026_database.role (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      code varchar(255) NOT NULL UNIQUE,
                      description varchar(255)
);

create table zh2_2026_database.user_role (
                           user_id int,
                           role_id int,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES user(id),
                           FOREIGN KEY (role_id) REFERENCES role(id)
);

insert into zh2_2026_database.car (type, model, production_year, list_price) values ("Toyota", "Corolla", "2020", "8500000");
insert into zh2_2026_database.car (type, model, production_year, list_price) values ("Volkswagen", "Golf", "2018", "6200000");
insert into zh2_2026_database.car (type, model, production_year, list_price) values ("Suzuki", "Vitara", "2022", "7900000");
insert into zh2_2026_database.car (type, model, production_year, list_price) values ("Ford", "Focus", "2019", "5800000");
insert into zh2_2026_database.car (type, model, production_year, list_price) values ("Skoda", "Octavia", "2023", "11500000");

insert into zh2_2026_database.role (code, description) values ("user", "Use the web app.");
insert into zh2_2026_database.role (code, description) values ("dealer", "Has right to create new objects.");

insert into zh2_2026_database.user (username, password) values ("user1", "$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.");
insert into zh2_2026_database.user (username, password) values ("user2", "$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.");

insert into zh2_2026_database.user_role (user_id, role_id) values ((select id from zh2_2026_database.user where username = "user1"), (select id from zh2_2026_database.role where code = "user"));
insert into zh2_2026_database.user_role (user_id, role_id) values ((select id from zh2_2026_database.user where username = "user2"), (select id from zh2_2026_database.role where code = "user"));
insert into zh2_2026_database.user_role (user_id, role_id) values ((select id from zh2_2026_database.user where username = "user2"), (select id from zh2_2026_database.role where code = "dealer"));
