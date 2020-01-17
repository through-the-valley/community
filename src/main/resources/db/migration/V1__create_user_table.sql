CREATE TABLE USER
    (
    	ID INT auto_increment primary key not null ,
    	NAME VARCHAR(100),
    	ACCOUNT_ID VARCHAR(200),
    	TOKEN CHAR(36),
    	GMT_CREATE BIGINT,
    	GMT_MODIFIED BIGINT
    );