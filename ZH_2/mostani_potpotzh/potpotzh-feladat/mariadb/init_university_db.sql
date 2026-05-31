CREATE DATABASE potpotzh_university_database;
CREATE USER 'potpotzh_university_admin_user'@'%' IDENTIFIED BY 'PotPotZH2026Password111';
GRANT ALL PRIVILEGES ON potpotzh_university_database.* to 'potpotzh_university_admin_user';

CREATE TABLE potpotzh_university_database.hallgato (
    ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    hallgato_nev varchar(255),
    szak varchar(255),
    felev int,
    egyetemi_atlag decimal(3,2)
);

insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Kovács Anna', 'Programtervező informatikus', 2, 4.75);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Nagy Péter', 'Gazdaságinformatikus', 4, 3.90);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Szabó Bence', 'Villamosmérnök', 6, 4.20);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Tóth Luca', 'Mérnökinformatikus', 1, 4.95);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Varga Dániel', 'Jogász', 8, 3.50);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Kiss Zsófia', 'Orvostudomány', 10, 4.60);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Molnár Márk', 'Gépészmérnök', 5, 3.80);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Horváth Réka', 'Pszichológia', 3, 4.40);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Balogh Máté', 'Nemzetközi gazdálkodás', 7, 3.70);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Farkas Eszter', 'Matematika', 2, 4.85);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Lakatos Gergő', 'Közgazdász', 6, 3.65);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Papp Vivien', 'Biológia', 4, 4.10);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Oláh Martin', 'Kémia', 2, 3.95);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Juhász Nóra', 'Építészmérnök', 9, 4.55);
insert into potpotzh_university_database.hallgato (hallgato_nev, szak, felev, egyetemi_atlag) values ('Mészáros Dávid', 'Történelem', 1, 4.00);
