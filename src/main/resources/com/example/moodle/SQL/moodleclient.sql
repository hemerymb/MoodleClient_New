DROP DATABASE IF EXISTS moodleclient;
CREATE DATABASE IF NOT EXISTS moodleclient;
USE moodleclient;

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
    userid INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    token VARCHAR(255),
    picture VARCHAR(255),
    role INT
);

DROP TABLE IF EXISTS course;
CREATE TABLE IF NOT EXISTS course (
    courseid INT PRIMARY KEY,
    fullname VARCHAR(255),
    shortname VARCHAR(50),
    summary TEXT,
    numsections INT,
    startdate INT,
    enddate INT,
    created INT,
    updated INT,
    teacherid INT,
    FOREIGN KEY (teacherid) REFERENCES user(userid)
);

DROP TABLE IF EXISTS section;
CREATE TABLE IF NOT EXISTS section (
    sectionid INT PRIMARY KEY,
    sectionname VARCHAR(255),
    courseid INT,
    FOREIGN KEY (courseid) REFERENCES course(courseid)
);

DROP TABLE IF EXISTS module;
DROP TABLE IF NOT EXISTS module (
    cmid INT PRIMARY KEY,
    sectionid INT,
    name VARCHAR(255),
    modname VARCHAR(255),
    modplural VARCHAR(255),
    downloadcontent INT,
    FOREIGN KEY (sectionid) REFERENCES section(sectionid)
);

DROP TABLE IF EXISTS assignment;
CREATE TABLE IF NOT EXISTS assignment (
    assignmentid INT PRIMARY KEY,
    moduleid INT,
    assignmentname VARCHAR(255) NOT NULL,
    created INT,
    duedate INT,
    attemptnumber INT,
    FOREIGN KEY (moduleid) REFERENCES module(moduleid);
);

DROP TABLE IF EXISTS private_files;
CREATE TABLE IF NOT EXISTS private_files (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fileName VARCHAR(255) NOT NULL,
    fileSize BIGINT NOT NULL,
    fileType VARCHAR(50) NOT NULL,
    filePath VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS file;
CREATE TABLE IF NOT EXISTS file (
    fileid INT AUTO_INCREMENT PRIMARY KEY,
    moduleid INT,
    filename VARCHAR(255),
    filepath VARCHAR(255),
    filesize BIGINT,
    fileurl VARCHAR(255),
    created INT,
    updated INT,
    isexternalfile INT,
    repositorytype VARCHAR(255),
    mimetype VARCHAR(255),
    FOREIGN KEY (moduleid) REFERENCES module(moduleid)
);

DROP TABLE IF EXISTS submission;
CREATE TABLE IF NOT EXISTS submission (
    submissionid INT PRIMARY KEY,
    assignmentid INT,
    status VARCHAR(255),
    created INT,
    updated INT,
    studentid INT,
    FOREIGN KEY (assignmentid) REFERENCES assignment(assignmentid),
    FOREIGN KEY (studentid) REFERENCES user(userid)
);

DROP TABLE IF EXISTS grade;
CREATE TABLE IF NOT EXISTS grade (
    gradeid INT AUTO_INCREMENT PRIMARY KEY,
    submissionid INT,
    grade INT,
    comment VARCHAR(255),
    FOREIGN KEY (submissionid) REFERENCES submission(submissionid)
);