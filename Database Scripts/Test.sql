DROP TABLE TEST CASCADE CONSTRAINTS;
CREATE TABLE TEST ( id number primary key );

insert into test values(2);

commit;

SELECT COUNT(*) FROM TEST;