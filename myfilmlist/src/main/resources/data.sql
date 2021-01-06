CREATE TABLE IF NOT EXISTS Realisateur(id INT primary key auto_increment, nom VARCHAR(100), prenom VARCHAR(100), date_naissance DATE);
CREATE TABLE IF NOT EXISTS Film(id INT primary key auto_increment, titre VARCHAR(100), duration INT, realisateurID INT, FOREIGN KEY (realisateurID) REFERENCES Realisateur(id));
INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES('Kubrick', 'Stanley', '1928-07-26');
INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES('Tarantino', 'Quentin', '1963-03-27');
INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES('Demme', 'Jonathan', '1944-02-22');
INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES('Jackson', 'Peter', '1961-10-31');
INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES('Wan', 'James', '1977-02-26');
INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES('Wachowski', 'Lily & Lana', '1965-06-21');
INSERT INTO Film(titre, duration, realisateurID) VALUES('Matrix', 150, 6);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Le silence des Agneaux', 138, 3);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Saw', 103, 5);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Le seigneur des anneaux 1', 190,4);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Le seigneur des anneaux 2', 205,4);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Le seigneur des anneaux 3', 220,4);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Les huits Salopards', 220,2);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Django : Unchained', 205,2);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Inglorious Basterds', 220,2);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Kill Bill', 220,2);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Kill Bill : Vol 2', 250,2);
INSERT INTO Film(titre, duration, realisateurID) VALUES('Shining', 180,1);
INSERT INTO Film(titre, duration, realisateurID) VALUES('2001 , lOdyssée de lEspace', 192,1);
