CREATE SCHEMA IF NOT EXISTS `javase2_bedu_pw`;
CREATE USER 'pwadmin'@'localhost' IDENTIFIED BY 'ADMIN_PW_PWD';
GRANT ALL ON javase2_bedu_pw.* TO 'pwadmin'@'localhost';


