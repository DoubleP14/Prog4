CREATE DATABASE potpotzh_security_database;
CREATE USER 'potpotzh_security_admin_user'@'%' IDENTIFIED BY 'PotPotZH2026SecurityPassword111';
GRANT ALL PRIVILEGES ON potpotzh_security_database.* to 'potpotzh_security_admin_user';

CREATE TABLE potpotzh_security_database.felhasznalo (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      felhasznalonev varchar(255) NOT NULL UNIQUE,
                      jelszo varchar(100) NOT NULL
);

CREATE TABLE potpotzh_security_database.szerepkor (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      kod varchar(255) NOT NULL UNIQUE,
                      leiras varchar(255)
);

create table potpotzh_security_database.felhasznalo_szerepkor (
                           felhasznalo_id int,
                           szerepkor_id int,
                           PRIMARY KEY (felhasznalo_id, szerepkor_id),
                           FOREIGN KEY (felhasznalo_id) REFERENCES potpotzh_security_database.felhasznalo(id),
                           FOREIGN KEY (szerepkor_id) REFERENCES potpotzh_security_database.szerepkor(id)
);

insert into potpotzh_security_database.szerepkor (kod, leiras) values ('remover', 'Törlő szerepkör');
insert into potpotzh_security_database.szerepkor (kod, leiras) values ('creator', 'Létrehozó szerepkör');

insert into potpotzh_security_database.felhasznalo (felhasznalonev, jelszo) values ('user1', '$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.');
insert into potpotzh_security_database.felhasznalo (felhasznalonev, jelszo) values ('user2', '$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.');

insert into potpotzh_security_database.felhasznalo_szerepkor (felhasznalo_id, szerepkor_id) values ((select id from potpotzh_security_database.felhasznalo where felhasznalonev = 'user1'), (select id from potpotzh_security_database.szerepkor where kod = 'creator'));
insert into potpotzh_security_database.felhasznalo_szerepkor (felhasznalo_id, szerepkor_id) values ((select id from potpotzh_security_database.felhasznalo where felhasznalonev = 'user2'), (select id from potpotzh_security_database.szerepkor where kod = 'creator'));
insert into potpotzh_security_database.felhasznalo_szerepkor (felhasznalo_id, szerepkor_id) values ((select id from potpotzh_security_database.felhasznalo where felhasznalonev = 'user2'), (select id from potpotzh_security_database.szerepkor where kod = 'remover'));
