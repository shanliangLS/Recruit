# 创建数据库
CREATE DATABASE recruit;
# 创建用户表
CREATE TABLE users (
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  email    VARCHAR(50) UNIQUE,
  password VARCHAR(50)
);
# 创建个人资料
CREATE TABLE user_info (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  uid       INTEGER UNIQUE,
  name      VARCHAR(50),
  birthday  DATE,
  education VARCHAR(50),
  college   VARCHAR(50),
  major     VARCHAR(50),
  work      TEXT,
  FOREIGN KEY (uid) REFERENCES users (id)
);
# 求职意向
CREATE TABLE intension (
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  uid              INTEGER,
  annual_salary    VARCHAR(50),
  desired_industry VARCHAR(50),
  desired_location VARCHAR(50),
  desired_job      VARCHAR(30),
  FOREIGN KEY (uid) REFERENCES users (id)
);
# 企业
CREATE TABLE company (
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  email    VARCHAR(50) UNIQUE,
  password VARCHAR(50)
);
# 企业信息
CREATE TABLE company_info (
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  cid      INTEGER,
  name     VARCHAR(50),
  industry CHAR(50),
  location VARCHAR(50),
  linkman  VARCHAR(50),
  sex      CHAR(5),
  phone    CHAR(20),
  FOREIGN KEY (cid) REFERENCES company (id)
);
# 招聘信息
CREATE TABLE recruit_info (
  id            INTEGER PRIMARY KEY AUTO_INCREMENT,
  cid           INTEGER,
  job           VARCHAR(50),
  annual_salary VARCHAR(50),
  location      VARCHAR(50),
  experience    VARCHAR(40),
  education     VARCHAR(50),
  job_info      TEXT,
  release_time  DATE,
  FOREIGN KEY (cid) REFERENCES company (id)
);
# 投送简历
CREATE TABLE resume (
  id  INTEGER AUTO_INCREMENT PRIMARY KEY,
  uid INTEGER,
  rid INTEGER,
  deliver_time DATE,
  deliver_status INTEGER,
  FOREIGN KEY (uid) REFERENCES users (id),
  FOREIGN KEY (rid) REFERENCES recruit_info (id)
);








# 管理员
CREATE TABLE admin(
  id INTEGER PRIMARY KEY  AUTO_INCREMENT,
  name CHAR(30) UNIQUE,
  password CHAR(30)
);
# 兼职信息
CREATE TABLE part_time_job(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(30),
  content TEXT,
  release_time DATE
);
# 招聘会
CREATE TABLE job_fair(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(30),
  content TEXT,
  release_time DATE
);






