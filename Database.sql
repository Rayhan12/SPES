CREATE DATABASE SPES;
USE SPES;

CREATE TABLE PROGRAM(
p_ID VARCHAR(10) NOT NULL,
Name VARCHAR(100),
PRIMARY KEY(p_ID)
);

insert into PROGRAM(p_ID,Name) values ('B.Sc.CSE',' Bachelors of Science in Computer Science & Engineering');

CREATE TABLE COURSE(
c_ID VARCHAR(10) NOT NULL,
Name VARCHAR(100),
p_ID VARCHAR(10),
PRIMARY KEY(c_ID),
FOREIGN KEY(p_ID) REFERENCES PROGRAM(p_ID)
);


CREATE TABLE PLO(
pl_ID VARCHAR(10) NOT NULL,
pl_no int,
oMARK FLOAT(3),
fMARK FLOAT(3),
p_ID VARCHAR(10),
PRIMARY KEY(pl_ID),
FOREIGN KEY(p_ID) REFERENCES PROGRAM(p_ID)
);

CREATE TABLE CO(
co_ID VARCHAR(10) NOT NULL,
oMARK FLOAT(3),
fMARK FLOAT(3),
c_ID VARCHAR(10),
pl_ID VARCHAR(10),
PRIMARY KEY(co_ID),
FOREIGN KEY(c_ID) REFERENCES COURSE(c_ID),
FOREIGN KEY(pl_ID) REFERENCES PLO(pl_ID)
);

CREATE TABLE FACULTY(
f_ID VARCHAR(10) NOT NULL,
Name VARCHAR(100),
gender VARCHAR(10),
dob DATE,
email VARCHAR(100),
PRIMARY KEY(f_ID)
);

CREATE TABLE SECTION(
s_ID VARCHAR(20) NOT NULL,
s_no int,
sem VARCHAR(10),
year YEAR,
c_ID VARCHAR(10),
f_ID VARCHAR(10),
PRIMARY KEY(s_ID),
FOREIGN KEY(c_ID) REFERENCES COURSE(c_ID),
FOREIGN KEY(f_ID) REFERENCES FACULTY(f_ID)
);


CREATE TABLE QUESTION(
q_ID VARCHAR(10) NOT NULL,
q_name VARCHAR(20)
oMARK FLOAT(3),
fMARK FLOAT(3),
s_ID VARCHAR(10),
f_ID VARCHAR(10),
co_ID VARCHAR(10),
PRIMARY KEY(q_ID),
FOREIGN KEY(s_ID) REFERENCES SECTION(s_ID),
FOREIGN KEY(co_ID) REFERENCES CO(co_ID),
FOREIGN KEY(f_ID) REFERENCES FACULTY(f_ID)
);


CREATE TABLE STUDENT(
st_ID INT NOT NULL,
Name VARCHAR(100),
gender VARCHAR(10),
dob DATE,
email VARCHAR(100),
PRIMARY KEY(st_ID)
);

CREATE TABLE REG(
r_ID VARCHAR(10) NOT NULL,
st_ID INT,
s_ID VARCHAR(10),
PRIMARY KEY(r_ID),
FOREIGN KEY(st_ID) REFERENCES STUDENT(st_ID),
FOREIGN KEY(s_ID) REFERENCES SECTION(s_ID)
);

CREATE TABLE ANSWER(
a_ID VARCHAR(10) NOT NULL,
oMARK FLOAT(3),
fMARK FLOAT(3),
q_ID VARCHAR(10),
r_ID VARCHAR(10),
f_ID VARCHAR(10),
PRIMARY KEY(a_ID),
FOREIGN KEY(q_ID) REFERENCES QUESTION(q_ID),
FOREIGN KEY(r_ID) REFERENCES REG(r_ID),
FOREIGN KEY(f_ID) REFERENCES FACULTY(f_ID)
);

Create Table DataPassing
(
st_id int
);

Create Table CoursePassing
(
c_id varchar(20)
);

Create Table users
(
username varchar(50) not null,
password varchar(100) not null
);

insert into users (username,password) 
value ('Rayhan1279','spesiub'), ('Zafore_spes','spesiub'); 


