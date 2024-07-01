CREATE DATABASE IF NOT EXISTS moodleclient;
USE moodleclient;

CREATE TABLE IF NOT EXISTS Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    statut VARCHAR(255),
    syncedbyte int default 0
);

CREATE TABLE IF NOT EXISTS Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    courseName VARCHAR(255),
    courseAbr VARCHAR(50),
    courseDescription TEXT,
    nbChapters INT,
    nbAssignments INT,
    teacherId INT,
    FOREIGN KEY (teacherId) REFERENCES Users(id),
    syncedbyte int default 0
);

CREATE TABLE IF NOT EXISTS Chapters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    num INT,
    content VARCHAR(255),
    courseId INT,
    FOREIGN KEY (courseId) REFERENCES Users(id),
    syncedbyte int default 0
);

CREATE TABLE IF NOT EXISTS assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assignmentName VARCHAR(255) NOT NULL,
    createdDate DATE NOT NULL,
    limitedDate DATE NOT NULL,
    courseName VARCHAR(255),
    statut ENUM('in progress', 'finish') NOT NULL,
    syncedbyte int default 0
);
CREATE TABLE IF NOT EXISTS private_files (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fileName VARCHAR(255) NOT NULL,
    fileSize BIGINT NOT NULL,
    fileType VARCHAR(50) NOT NULL,
    filePath VARCHAR(255) NOT NULL,
    syncedbyte int default 0
);
CREATE TABLE IF NOT EXISTS documents_files (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fileName VARCHAR(255) NOT NULL,
    fileSize BIGINT NOT NULL,
    fileType VARCHAR(50) NOT NULL,
    filePath VARCHAR(255) NOT NULL,
    chapterId INT,
    FOREIGN KEY (chapterId) REFERENCES Chapters(id)

);

