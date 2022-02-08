--liquibase formatted sql

--changeset tauno:loan_table
CREATE TABLE loan (
    loan_id SERIAL PRIMARY KEY,
    loan_application_name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    loan_period_in_months INTEGER NOT NULL,
    amount NUMERIC(12, 2) NOT NULL,
    customer_id INTEGER NOT NULL,
    created_dtime TIMESTAMP,
    modified_dtime TIMESTAMP
)