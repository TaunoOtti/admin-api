--liquibase formatted sql

--changeset tauno:init_customers
INSERT INTO CUSTOMER (first_name, last_name, address, email, phone_no, date_of_birth, created_dtime, modified_dtime) VALUES
('Marko', 'Tamm', 'Mahtra 20, Tallinn', 'marko.tamm@gmail.com', '566830022', '1997-08-24', NOW(), NOW()),
('John', 'Wick', 'Wall streen 55, New York', 'john.wick@email.com', '1-339-610-6676', '1972-01-24', NOW(), NOW()),
('Brandon', 'Wiggins', '5677 Facilisis Road New Zealand', 'eleifend.egestas@outlook.couk', '1-123456789', '1966-06-15', NOW(), NOW()),
('Dylan', 'Rowe', '778-1166 Duis St. Stockholm', 'vitae@hotmail.org', '1-133-288-4128', '1899-09-28', NOW(), NOW()),
('Wallace', 'Miles', 'Ap #612-4177 Mauris Avenue South Korea', 'osuere.at.velit@yahoo.ca', '+372593023', '1997-08-24', NOW(), NOW())
