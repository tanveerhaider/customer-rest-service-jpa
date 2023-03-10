--CREATE DATABASE customer_management;
use customer_management;
DROP TABLE IF EXISTS CUSTOMER;

create table IF NOT EXISTS CUSTOMER (
	id INT NOT NULL AUTO_INCREMENT,
	email_id VARCHAR(50) NOT NULL,
	first_name VARCHAR(50) ,
	last_name VARCHAR(50),
	gender VARCHAR(1),
	cell_phone VARCHAR(50),
	zip_code VARCHAR(50),
	city VARCHAR(50),
	PRIMARY KEY (id),
	UNIQUE KEY `Uidx_email_id` (`email_id`)
) ENGINE=InnoDB;

