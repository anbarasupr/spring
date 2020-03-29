create database bank;

use bank;


CREATE TABLE `icici_bank` (
  `account_no` bigint(11) NOT NULL,
  `account_balance` double DEFAULT NULL,
  `account_type` varchar(45) DEFAULT NULL,
  `account_holder_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_no`)
);


insert into icici_bank values ('1122330056L',10000,'saving','anbu');
insert into icici_bank values ('3355330099L',500,'saving','anbu');

SELECT * FROM icici_bank;

