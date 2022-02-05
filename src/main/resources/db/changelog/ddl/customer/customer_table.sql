--liquibase formatted sql

--changeset tauno:customer_table
CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_no VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    created_dtime TIMESTAMP,
    modified_dtime TIMESTAMP
)