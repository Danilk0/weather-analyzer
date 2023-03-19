--liquibase formatted sql

--changeset dmoskalyuk:1
CREATE TABLE IF NOT EXISTS weather
(
    id SERIAL PRIMARY KEY ,
    temp DOUBLE PRECISION,
    wind DOUBLE PRECISION,
    pressure DOUBLE PRECISION,
    humidity DOUBLE PRECISION,
    condition VARCHAR(64),
    location VARCHAR(64),
    date TIMESTAMP without time zone UNIQUE

);



