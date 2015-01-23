// Add two demo accounts
// admin:password & user:password (bcrypt(10) passwords)
INSERT INTO user (username, password, email) VALUES('admin', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'admin@example.org');
INSERT INTO user (username, password, email) VALUES('user', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'user@example.org');

// Create ADMIN and USER authorities
INSERT INTO authority (name, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authority (name, authority) VALUES('user', 'ROLE_USER');

// Set up admin authorities
INSERT INTO user_authority (user_id, authority_id) VALUES(1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES(1, 2);
// Set up user authorities
INSERT INTO user_authority (user_id, authority_id) VALUES(2, 2);

// POPULATE THE RESPONSE PACKAGE WITH DATA.
INSERT INTO CODE(category, code_value) VALUES('Dummy Category Alpha', 1);
INSERT INTO CODE(category, code_value) VALUES('Dummy Category Beta', 2);
INSERT INTO CODE(category, code_value) VALUES('Dummy Category Charlie', 3);
INSERT INTO CODE(category, code_value) VALUES('Dummy Category Delta', 4);
INSERT INTO CODE(category, code_value) VALUES('Dummy Category Echo', 5);

INSERT INTO RESPONSE_KIND(code, name) VALUES(1, 'RESPONSE Alpha');
INSERT INTO RESPONSE_KIND(code, name) VALUES(2, 'RESPONSE Beta');
INSERT INTO RESPONSE_KIND(code, name) VALUES(3, 'RESPONSE Charli');
INSERT INTO RESPONSE_KIND(code, name) VALUES(4, 'RESPONSE Delta');
INSERT INTO RESPONSE_KIND(code, name) VALUES(5, 'RESPONSE Echo');

INSERT INTO RESPONSE_DOMAIN (RESPONSE_KIND_ID) VALUES(1);
INSERT INTO RESPONSE_DOMAIN (RESPONSE_KIND_ID) VALUES(1);
INSERT INTO RESPONSE_DOMAIN (RESPONSE_KIND_ID) VALUES(1);
INSERT INTO RESPONSE_DOMAIN (RESPONSE_KIND_ID) VALUES(2);
INSERT INTO RESPONSE_DOMAIN (RESPONSE_KIND_ID) VALUES(2);
INSERT INTO RESPONSE_DOMAIN (RESPONSE_KIND_ID) VALUES(2);

INSERT INTO RESPONSE_DOMAIN_CODE(RESPONSE_DOMAIN_ID, CODE_ID, RANK) VALUES(1, 1, 1);
INSERT INTO RESPONSE_DOMAIN_CODE(RESPONSE_DOMAIN_ID, CODE_ID, RANK) VALUES(1, 2, 2);
INSERT INTO RESPONSE_DOMAIN_CODE(RESPONSE_DOMAIN_ID, CODE_ID, RANK) VALUES(2, 3, 3);
INSERT INTO RESPONSE_DOMAIN_CODE(RESPONSE_DOMAIN_ID, CODE_ID, RANK) VALUES(1, 4, 4);
INSERT INTO RESPONSE_DOMAIN_CODE(RESPONSE_DOMAIN_ID, CODE_ID, RANK) VALUES(2, 5, 5);