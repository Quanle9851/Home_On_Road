INSERT INTO DEPARTMENT(ID,NAME) VALUES(1,'SE');
INSERT INTO DEPARTMENT(ID,NAME) VALUES(2,'AI');
INSERT INTO DEPARTMENT(ID,NAME) VALUES(3,'IA');

INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Quan', 'Le','BEN TRE', 'ACCESS');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Hung', 'Le','BEN TRE', 'ACCESS');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Hai', 'Tran','Ha Tinh', 'DELETE');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Tu', 'Nguyen','Gia Lai', 'DE_ACTIVE');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Doan', 'Nguyen','Quang Nam', 'ACCESS');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Nam', 'Tran','Vung Tau', 'ACCESS');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Quang', 'Nguyen','Binh Thuan', 'DELETE');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Chuong', 'Nguyen','Ninh Thuan', 'DELETE');
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME,ADDRESS,STATUS) VALUES('Tri', 'Le','BEN TRE', 'DE_ACTIVE');

UPDATE EMPLOYEE SET DEPARTMENT_ID = 1 WHERE ID = 1;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 2 WHERE ID = 2;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 2 WHERE ID = 3;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 3 WHERE ID = 4;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 2 WHERE ID = 5;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 2 WHERE ID = 6;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 2 WHERE ID = 7;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 1 WHERE ID = 8;
UPDATE EMPLOYEE SET DEPARTMENT_ID = 1 WHERE ID = 9;




