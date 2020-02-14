create schema taximanagement;
CREATE USER 'taxiuser'@'%' IDENTIFIED BY 'taxiuser';
GRANT ALL PRIVILEGES ON taximanagement.* TO 'taxiuser'@'localhost';
FLUSH PRIVILEGES;