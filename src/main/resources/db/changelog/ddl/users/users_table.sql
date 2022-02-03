--liquibase formatted sql

--changeset tauno:users_table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    created_dtime TIMESTAMP,
    modified_dtime TIMESTAMP
)