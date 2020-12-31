read가 될 파일

# JSP 블로그 프로젝트

## 환경
- Windows 10
- JDK 1.8
- Tomcat 9.0
- STS Tool
- MySQL 8.0
- Postman
- Lombok
- Gson
- Encoding : UTF-8

## MySQL 데이터베이스 생성 및 사용자 생성
```sql
CREATE USER 'bloguser'@'%' IDENTIFIED BY 'bitc5600';
GRANT ALL privileges ON *.* TO 'bloguser'@'%';
CREATE DATABASE blog;
```

## MySQL 테이블 생성
```sql
use blog;

CREATE TABLE users(
    id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    address varchar(100),
    userRole varchar(20),
    createDate timestamp
) engine=InnoDB default charset=utf8;

CREATE TABLE board(
    id int PRIMARY KEY AUTO_INCREMENT,
    userId int,
    title varchar(100) NOT NULL,
    content longtext,
    readCount int DEFAULT 0,
    createDate timestamp,
    foreign key (userId) references user (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE reply(
    id int PRIMARY KEY AUTO_INCREMENT,
    userId int,
    boardId int,
    content varchar(300) NOT NULL,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
    foreign key (boardId) references board (id) on delete cascade
) engine=InnoDB default charset=utf8;
```