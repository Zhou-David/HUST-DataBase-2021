INSERT INTO person (id, fullname, telephone) VALUES (1, '张小敏', '13907110001');
INSERT INTO person (id, fullname, telephone) VALUES (2, '李大锤', '18907110002');
INSERT INTO person (id, fullname, telephone) VALUES (3, '孙二娘', '13307100003');

DELETE FROM person WHERE id=2;

UPDATE person SET telephone='13607176668' WHERE id=1;