-- Target DB: MS SQL Server

-- Create Database
CREATE DATABASE uobtokenizer;

-- Create Table
CREATE TABLE token_mapping (
	id bigint NOT NULL IDENTITY(1,1),
	cipher_text numeric(20,0),
	token_name varchar(255),
	CONSTRAINT PK_token_ma PRIMARY KEY (id)
);
