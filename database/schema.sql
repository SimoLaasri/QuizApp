CREATE DATABASE IF NOT EXISTS quizapp;
USE quizapp;

CREATE TABLE utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    role ENUM('ETUDIANT', 'ENSEIGNANT') NOT NULL
);

CREATE TABLE quiz (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL
);

CREATE TABLE question (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quiz_id INT NOT NULL,
    enonce TEXT NOT NULL,
    choixA VARCHAR(255) NOT NULL,
    choixB VARCHAR(255) NOT NULL,
    choixC VARCHAR(255) NOT NULL,
    choixD VARCHAR(255) NOT NULL,
    bonneReponse INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);

CREATE TABLE tentative (
    id INT PRIMARY KEY AUTO_INCREMENT,
    etudiant_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT NOT NULL,
    date_tentative TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (etudiant_id) REFERENCES utilisateur(id),
    FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);

-- Sample test data
INSERT INTO utilisateur (username, password, nom, prenom, role) VALUES 
('prof', 'prof123', 'Dupont', 'Jean', 'ENSEIGNANT'),
('etudiant1', 'etu123', 'Martin', 'Marie', 'ETUDIANT');

INSERT INTO quiz (titre) VALUES ('Quiz Java Basics');

INSERT INTO question (quiz_id, enonce, choixA, choixB, choixC, choixD, bonneReponse) VALUES
(1, 'Quel mot-clé est utilisé pour créer une classe en Java?', 'class', 'Class', 'new', 'create', 0),
(1, 'Quelle est la méthode principale en Java?', 'start()', 'main()', 'run()', 'init()', 1);
