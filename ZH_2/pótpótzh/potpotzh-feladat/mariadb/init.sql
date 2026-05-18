CREATE DATABASE potpotzh_database;
GRANT ALL PRIVILEGES ON potpotzh_database.* to 'potpotzh_user';

CREATE TABLE potpotzh_database.technologiai_ceg (
    ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nev varchar(255),
    alapitasi_ev int,
    orszag varchar(100),
    ismert_termek varchar(255)
);

CREATE TABLE potpotzh_database.felhasznalo (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      felhasznalonev varchar(255) NOT NULL UNIQUE,
                      jelszo varchar(100) NOT NULL
);

CREATE TABLE potpotzh_database.szerepkor (
                      ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      kod varchar(255) NOT NULL UNIQUE,
                      leiras varchar(255)
);

create table potpotzh_database.felhasznalo_szerepkor (
                           felhasznalo_id int,
                           szerepkor_id int,
                           PRIMARY KEY (felhasznalo_id, szerepkor_id),
                           FOREIGN KEY (felhasznalo_id) REFERENCES felhasznalo(id),
                           FOREIGN KEY (szerepkor_id) REFERENCES szerepkor(id)
);

insert into potpotzh_database.technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) values ("Apple", 1976, "USA", "iPhone");
insert into potpotzh_database.technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) values ("Microsoft", 1975, "USA", "Windows");
insert into potpotzh_database.technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) values ("Samsung", 1938, "Dél-Korea", "Galaxy telefonok");
insert into potpotzh_database.technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) values ("Huawei", 1987, "Kína", "Mate sorozat");
insert into potpotzh_database.technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) values ("Tesla", 2003, "USA", "Model S");

insert into potpotzh_database.szerepkor (kod, leiras) values ("viewer", "Use the web app.");

insert into potpotzh_database.felhasznalo (felhasznalonev, jelszo) values ("user1", "$2a$06$qkg0bF5ZbgNHj/ichvlAWepmKgJjguwGq1E//xVIS5O687BxMh5T.");

insert into potpotzh_database.felhasznalo_szerepkor (felhasznalo_id, szerepkor_id) values ((select id from potpotzh_database.felhasznalo where felhasznalonev = "user1"), (select id from potpotzh_database.szerepkor where kod = "viewer"));
