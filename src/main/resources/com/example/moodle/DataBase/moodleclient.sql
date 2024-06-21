CREATE DATABASE IF NOT EXISTS moodleclient;
USE moodleclient;

CREATE TABLE IF NOT EXISTS Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    statut VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    courseName VARCHAR(255),
    courseAbr VARCHAR(50),
    courseDescription TEXT,
    nbChapters INT,
    nbAssignments INT
);
CREATE TABLE assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assignmentName VARCHAR(255) NOT NULL,
    createdDate DATE NOT NULL,
    limitedDate DATE NOT NULL,
    statut ENUM('in progress', 'finish') NOT NULL
);
