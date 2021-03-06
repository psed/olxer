CREATE TABLE CRITERIA
(
    ID INTEGER PRIMARY KEY,
    CRITERIA_URL VARCHAR(512) UNIQUE NOT NULL,
    EMAIL_TO VARCHAR(64) NOT NULL
)


CREATE SEQUENCE CRITERIA_ID AS INT START WITH 1 INCREMENT BY 1;
-- A sequence that gives next integer to use as criteria id. 
-- USAGE: insert into APP.CRITERIA (ID, CRITERIA_URL, EMAIL_TO) values (NEXT VALUE FOR CRITERIA_ID, 'some_url', 'some@email');