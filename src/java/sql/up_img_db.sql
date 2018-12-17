CREATE DATABASE up_img_db;

USE up_img_db;

CREATE TABLE imagenes
(
    id serial NOT NULL,
    img text NOT NULL,
    PRIMARY KEY (id)
);