DROP TABLE BUILDING_DATA CASCADE CONSTRAINTS;
/

CREATE TABLE BUILDING_DATA (
  ID INTEGER PRIMARY KEY,
  type VARCHAR2(100),
  x1 NUMBER,
  y1 NUMBER,
  x2 NUMBER,
  y2 NUMBER,
  floor NUMBER,
  room NUMBER,
  isExterior INTEGER,
  isExit INTEGER
);
/

DROP SEQUENCE BUILDING_DATA_ID;
/

CREATE SEQUENCE BUILDING_DATA_ID INCREMENT BY 1 START WITH 1 MAXVALUE 99999;
/

CREATE OR REPLACE TRIGGER BUILDING_DATA_ID_TRG
  BEFORE INSERT ON BUILDING_DATA
  FOR EACH ROW
BEGIN
  :new.id := BUILDING_DATA_ID.NEXTVAL;
END;
/
SELECT * FROM building_data;
