CREATE DATABASE IF NOT EXISTS cadastro;

pgAdmin para POSTGRESQL co  senha 'adm' em localhost;

CREATE TABLE IF NOT EXISTS clientes ( 
id SERIAL,  
nome varchar(255) NOT NULL, 
documento  varchar(255) NOT NULL UNIQUE,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS endereco ( 
id SERIAL,  
idCliente int NOT NULL, 
endereco varchar(255) NOT NULL, 
principal BOOLEAN NOT NULL,
FOREIGN KEY (idCliente) REFERENCES clientes(id),
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS telefone ( 
id SERIAL,  
idCliente int NOT NULL, 
telefone varchar(255) NOT NULL, 
FOREIGN KEY (idCliente) REFERENCES clientes(id),
PRIMARY KEY (id));