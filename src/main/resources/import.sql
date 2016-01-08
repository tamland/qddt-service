--Add primary agency
INSERT INTO agency (id, name) VALUES('1359dede-9f18-11e5-8994-feff819cdc9f', 'NSD-qddt');

--Add two demo accounts
--admin:password & user:password (bcrypt(10) passwords)
INSERT INTO user_account(id, username, password, email, agency_id) VALUES('83d4c034-4ff9-11e5-885d-feff819cdc9f', 'admin', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'admin@example.org', '1359dede-9f18-11e5-8994-feff819cdc9f');
INSERT INTO user_account(id, username, password, email, agency_id) VALUES('83d4c39a-4ff9-11e5-885d-feff819cdc9f', 'user', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'user@example.org', '1359dede-9f18-11e5-8994-feff819cdc9f');
INSERT INTO user_account(id, username, password, email, agency_id) VALUES('83d4c30a-4ff9-11e5-885d-feff819cdc9f', 'Author1', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'author1@example.org', '1359dede-9f18-11e5-8994-feff819cdc9f');
INSERT INTO user_account(id, username, password, email, agency_id) VALUES('83d4c3aa-4ff9-11e5-885d-feff819cdc9f', 'Author2', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'author2@example.org', '1359dede-9f18-11e5-8994-feff819cdc9f');
-- INSERT INTO user_account(id, username, password, email) VALUES('83d4c034-4ff9-11e5-885d-feff819cdc9f', 'admin', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'admin@example.org');
-- INSERT INTO user_account(id, username, password, email) VALUES('83d4c39a-4ff9-11e5-885d-feff819cdc9f', 'user', '$2a$10$O1MMi3SLcvwtJIT9CSZyN.aLtFKN.K2LtKyHZ52wElo0zh5gI1EyW', 'user@example.org');

--Create ADMIN and USER authorities
INSERT INTO authority (id, name, authority) VALUES ('9bec2c02-4ff9-11e5-885d-feff819cdc9f', 'admin', 'ROLE_ADMIN');
INSERT INTO authority (id, name, authority) VALUES('9bec2d6a-4ff9-11e5-885d-feff819cdc9f', 'user', 'ROLE_USER');

--Set up admin authorities
--admin:
INSERT INTO user_authority (user_id, authority_id) VALUES('83d4c034-4ff9-11e5-885d-feff819cdc9f', '9bec2c02-4ff9-11e5-885d-feff819cdc9f');
INSERT INTO user_authority (user_id, authority_id) VALUES('83d4c034-4ff9-11e5-885d-feff819cdc9f', '9bec2d6a-4ff9-11e5-885d-feff819cdc9f');
--user:
INSERT INTO user_authority (user_id, authority_id) VALUES('83d4c39a-4ff9-11e5-885d-feff819cdc9f', '9bec2d6a-4ff9-11e5-885d-feff819cdc9f');

-- INSERT INTO SURVEY_PROGRAM(id,name,CREATED,CHANGE_KIND,USER_ID) VALUES('a9fe6c00-51af-1ced-8151-af0dd96a0000','MORE SURVEY','01-12-2015 07:00:01','CREATED','83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO SURVEY_PROGRAM(id,name,CREATED,CHANGE_KIND,USER_ID) VALUES('a9fe6c01-51af-1ced-8151-af0dd96a0000','SUCH SURVEY','01-12-2015 09:00:02','CREATED','83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO SURVEY_PROGRAM(id,name,CREATED,CHANGE_KIND,USER_ID) VALUES('a9fe6c02-51af-1ced-8151-af0dd96a0000','MUCH WOW SURVEY','01-12-2015 11:00:03','CREATED','83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO SURVEY_PROGRAM(id,name,CREATED,CHANGE_KIND,USER_ID) VALUES('a9fe6c03-51af-1ced-8151-af0dd96a0000','DOGE SURVEY','01-12-2015 13:00:04','CREATED','83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO COMMENT (id, owner_uuid,COMMENT, CREATED, USER_ID) VALUES('a9fe6d00-51af-1ced-8151-af0dd96a0000','a9fe6c00-51af-1ced-8151-af0dd96a0000','ANOTHER COMMENT YES', '2015-02-10 07:00:01', '83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO COMMENT (id, owner_uuid,COMMENT, CREATED, USER_ID) VALUES('a9fe6d01-51af-1ced-8151-af0dd96a0000','a9fe6c01-51af-1ced-8151-af0dd96a0000','ANOTHER COMMENT YES', '2015-02-10 09:00:01', '83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO COMMENT (id, owner_uuid,COMMENT, CREATED, USER_ID) VALUES('a9fe6d02-51af-1ced-8151-af0dd96a0000','a9fe6c02-51af-1ced-8151-af0dd96a0000','ANOTHER COMMENT YES', '2015-02-10 11:00:01', '83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO COMMENT (id, owner_uuid,COMMENT, CREATED, USER_ID) VALUES('a9fe6d03-51af-1ced-8151-af0dd96a0000','a9fe6c03-51af-1ced-8151-af0dd96a0000','ANOTHER COMMENT YES', '2015-02-10 13:00:01', '83d4c034-4ff9-11e5-885d-feff819cdc9f');
-- INSERT INTO COMMENT (id, owner_uuid,COMMENT, CREATED, USER_ID) VALUES('a9fe6d04-51af-1ced-8151-af0dd96a0000','a9fe6c04-51af-1ced-8151-af0dd96a0000','ANOTHER COMMENT YES', '2015-02-10 15:00:01', '83d4c034-4ff9-11e5-885d-feff819cdc9f');
--
-- INSERT INTO COMMENT (id, owner_uuid,COMMENT, CREATED, USER_ID) VALUES('a9fe6d05-51af-1ced-8151-af0dd96a0000','a9fe6c58-51af-12ae-8151-af82ea6a0000','ANOTHER COMMENT YES', '2015-02-10 07:00:00', '83d4c034-4ff9-11e5-885d-feff819cdc9f');
--
-- update comment set created = '2015-12-10 07:01:01' where created < '2015-12-11'
--
--
-- --POPULATE THE RESPONSE PACKAGE WITH DATA.
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('378949ec-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'Start','START','ENTITY','CODE','CREATED');
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('37894d7a-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'Slutt','SLUTT','ENTITY','CODE','CREATED');
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('37894f32-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'Range','RANGE-START-SLUTT','GROUP_ENTITY','RANGE','CREATED');
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('378953c4-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'NA','NA svar','ENTITY','CODE','CREATED');
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('378955cc-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'Vet ikke','NA svar','ENTITY','CODE','CREATED');
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('378955cd-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'Ikke svar','NA','GROUP_ENTITY','MULTIPLE_SINGLE','CREATED');
-- INSERT INTO category(id,created,label,name,hierarchy_level, category_kind,CHANGE_KIND) VALUES('378955ce-65d0-11e5-9d70-feff819cdc9f','01-01-2015 12:00:01.000', 'Responsdomain Range','NA','ROOT_ENTITY','MIXED','CREATED');
--
-- ALTER TABLE category_category drop CONSTRAINT uk_qef5u1tm9s8i1hhpoqeg0dq9e;
--
-- INSERT INTO category_category (id, parent_id) VALUES ('378949ec-65d0-11e5-9d70-feff819cdc9f', '37894f32-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO category_category (id, parent_id) VALUES ('37894d7a-65d0-11e5-9d70-feff819cdc9f', '37894f32-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO category_category (id, parent_id) VALUES ('378953c4-65d0-11e5-9d70-feff819cdc9f', '378955cd-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO category_category (id, parent_id) VALUES ('378955cc-65d0-11e5-9d70-feff819cdc9f', '378955cd-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO category_category (id, parent_id) VALUES ('378955cc-65d0-11e5-9d70-feff819cdc9f', '378955ce-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO category_category (id, parent_id) VALUES ('37894f32-65d0-11e5-9d70-feff819cdc9f', '378955ce-65d0-11e5-9d70-feff819cdc9f');
--
--
-- INSERT INTO responsedomain(id,created,CHANGE_KIND,name,response_kind,category_id) VALUES('d5dbaebb-65d0-11e5-9d70-feff819cdc9f','01-01-2015','CREATED','Responsdomain Range','Scale','378955ce-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO responsedomain(id,created,CHANGE_KIND,name,response_kind) VALUES('d5dbb12a-65d0-11e5-9d70-feff819cdc9f','01-01-2015','CREATED','TestDomianCategory','Category');
--
-- INSERT INTO responsedomain_code (id, created,  change_kind, name,  category_idx, code_value,  category_id, responsedomain_id) VALUES ('05f9a484-65d1-11e5-9d70-feff819cdc9f', '01-01-2015','CREATED', 'name', 1, '1', '378949ec-65d0-11e5-9d70-feff819cdc9f', 'd5dbaebb-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO responsedomain_code (id, created,  change_kind, name,  category_idx, code_value,  category_id, responsedomain_id) VALUES ('05f9a89e-65d1-11e5-9d70-feff819cdc9f', '01-01-2015','CREATED', 'name', 2, '9', '37894d7a-65d0-11e5-9d70-feff819cdc9f', 'd5dbaebb-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO responsedomain_code (id, created,  change_kind, name,  category_idx, code_value,  category_id, responsedomain_id) VALUES ('05f9a9c0-65d1-11e5-9d70-feff819cdc9f', '01-01-2015','CREATED', 'name', 3, '66', '378953c4-65d0-11e5-9d70-feff819cdc9f','d5dbaebb-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO responsedomain_code (id, created,  change_kind, name,  category_idx, code_value,  category_id, responsedomain_id) VALUES ('05f9abd2-65d1-11e5-9d70-feff819cdc9f', '01-01-2015','CREATED', 'name', 4, '67', '378955cc-65d0-11e5-9d70-feff819cdc9f','d5dbaebb-65d0-11e5-9d70-feff819cdc9f');
--
--
-- INSERT INTO responsedomain_code(id,created,CHANGE_KIND, responsedomain_id, category_id, category_idx, code_value) VALUES('05f9ad12-65d1-11e5-9d70-feff819cdc9f','01-01-2015','CREATED', 'd5dbaebe-65d0-11e5-9d70-feff819cdc9f','378955cc-65d0-11e5-9d70-feff819cdc9f', 5,'15');
--
--
-- --POPULATE SURVEY DUMMY DATA
-- INSERT INTO survey_program(id,created,CHANGE_KIND,name)               VALUES('a9fe6c58-5038-1fe0-8150-382001480000','01-01-2015','CREATED', 'THE SURVEY');
-- INSERT INTO study         (id,created,CHANGE_KIND,name,survey_id)     VALUES('a9fe6c58-5038-1fe0-8150-382001480001','01-01-2015','CREATED','myStudy','a9fe6c58-5038-1fe0-8150-382001480000');
-- INSERT INTO topic_group   (id,created,CHANGE_KIND,name,study_id)      VALUES('a9fe6c58-5038-1fe0-8150-382001480002','01-01-2015','CREATED','Module test','a9fe6c58-5038-1fe0-8150-382001480001');
-- INSERT INTO concept       (id,created,CHANGE_KIND,name,topicgroup_id) VALUES('a9fe6c58-5038-1fe0-8150-382001480003','01-01-2015','CREATED','Test Concept','a9fe6c58-5038-1fe0-8150-382001480002');
-- INSERT INTO question      (id,created,CHANGE_KIND,grid_idx,name,question,responsedomain_id)  VALUES('a9fe6c58-5038-1fe0-8150-382001480004','01-01-2015','CREATED',0,'How to DDI?','How would you DDI if you could?','d5dbaebe-65d0-11e5-9d70-feff819cdc9f');
-- INSERT INTO concept_question (question_id,concept_id) VALUES('a9fe6c58-5038-1fe0-8150-382001480004','a9fe6c58-5038-1fe0-8150-382001480003')