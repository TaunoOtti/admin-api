--liquibase formatted sql

--changeset tauno:init_loans
INSERT INTO LOAN (loan_application_name, start_date, loan_period_in_months, amount, customer_id, created_dtime, modified_dtime) VALUES
('Loan no 1', '2021-02-15', 36, 15000, 2, NOW(), NOW()),
('Loan no 2', '2012-12-12', 44, 5000, 2, NOW(), NOW()),
('Loan no 3', '2001-01-01', 50, 9650, 2, NOW(), NOW()),
('Loan no XX/123', '2011-01-01', 90, 550000, 1, NOW(), NOW()),
('Loan no YY/456', '2012-12-12', 68, 550000, 1, NOW(), NOW()),
('Loan no 52782', '2012-12-12', 10, 12000, 3, NOW(), NOW())