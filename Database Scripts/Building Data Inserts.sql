INSERT INTO BUILDING_DATA(elementType, x1, y1, x2, y2, floor, room, isExterior, isExit) VALUES('Wall', 12, 18, 32, 80, 1, 112, 0, 0);
INSERT INTO BUILDING_DATA(elementType, x1, y1, x2, y2, floor, room, isExterior, isExit) VALUES('Door', 5, 5, 7, 7, 5, 509, 0, 1);
INSERT INTO BUILDING_DATA(elementType, x1, y1, x2, y2, floor, room, isExterior, isExit) VALUES('Stairs', 54, 23, 14, 8, 2, 0, 0, 0);
INSERT INTO BUILDING_DATA(elementType, x1, y1, x2, y2, floor, room, isExterior, isExit) VALUES('Window', 4, 4, 3, 3, 3, 302, 1, 0);
INSERT INTO BUILDING_DATA(elementType, x1, y1, x2, y2, floor, room, isExterior, isExit) VALUES('Wall', 90, 65, 43, 34, 1, 112, 1, 1);
/

DELETE FROM BUILDING_DATA;
/

COMMIT;
