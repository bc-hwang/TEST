USE mysql;
GRANT ALL PRIVILEGES ON *.* TO 'devers'@'localhost' IDENTIFIED BY '1234' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'devers'@'%' IDENTIFIED BY '1234' WITH GRANT OPTION;
FLUSH PRIVILEGES;