DROP TABLE TEMPORARY_DATA CASCADE CONSTRAINTS;
/
CREATE TABLE TEMPORARY_DATA (
  id integer primary key,
  type varchar2(100),
  x1 number NOT NULL,
  y1 number NOT NULL,
  x2 number NOT NULL,
  y2 number NOT NULL,
  floor number NOT NULL,
  room varchar(100),
  isExitWay integer,
  isExterior integer
);
/
DROP SEQUENCE TEMPORARY_DATA_ID;
/
CREATE SEQUENCE TEMPORARY_DATA_ID INCREMENT BY 1 START WITH 1 MAXVALUE 99999;
/
CREATE OR REPLACE TRIGGER TEMPORARY_DATA_ID_TRG
  BEFORE INSERT ON TEMPORARY_DATA
  FOR EACH ROW
BEGIN
  :new.id := TEMPORARY_DATA_ID.NEXTVAL;
END;
/
CREATE OR REPLACE PROCEDURE REPLICATE_DATA IS
  v_sqlDrop varchar2(100);
  v_sqlCreate varchar2(100);
BEGIN
  v_sqlDrop := 'DROP TABLE FINAL_DATA CASCADE CONSTRAINTS';
  v_sqlCreate := 'CREATE TABLE FINAL_DATA AS SELECT * FROM TEMPORARY_DATA';
  EXECUTE IMMEDIATE v_sqlDrop;
  EXECUTE IMMEDIATE v_sqlCreate;
  commit;
END;
/
select * from temporary_data;
/
SELECT TYPE, x1, y1, x2, y2, floor, room, isExterior, isExitWay from temporary_data
/
select * from final_data;
/
BEGIN
  REPLICATE_DATA;
END;