delete from temporary_data;

--Floor 1
        --Floor exterior walls
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',10,27,15,27,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',2,17,2,22,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,27,10,27,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',15,27,24,27,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,27,24,18,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,18,36,18,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,18,36,2,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,2,24,2,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,11,2,17,1,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,22,2,27,1,'-1',1,0);

        --room '101'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',5,11,10,11,1,'101',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,11,5,11,1,'101',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',10,11,13,11,1,'101',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,11,13,2,1,'101',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,2,2,1,'101',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,2,2,11,1,'101',1,0);

        --room '102'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',24,9,24,5,1,'102',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,11,24,11,1,'102',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,11,24,9,1,'102',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,5,24,2,1,'102',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,2,13,2,1,'102',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,13,11,1,'102',0,0);

        --stairs 's1'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',29,16,32,16,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',34,13,34,10,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',27,16,29,16,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',32,16,34,16,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,16,34,13,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,10,34,4,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,4,27,4,1,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',27,4,27,16,1,'s1',0,0);

        --Floor 2
        --Floor exterior walls
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',10,27,15,27,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',2,17,2,22,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,27,10,27,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',15,27,24,27,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,27,24,18,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,18,36,18,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,18,36,2,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,2,13,2,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,11,2,17,2,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,22,2,27,2,'-1',1,0);

        --room '201'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',5,11,10,11,2,'201',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,11,5,11,2,'201',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',10,11,13,11,2,'201',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,11,13,2,2,'201',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,2,2,2,'201',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,2,2,11,2,'201',1,0);

        --stairs 's1'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',29,16,32,16,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,13,34,10,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',27,16,29,16,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',32,16,34,16,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,16,34,13,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,10,34,4,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',34,4,27,4,2,'s1',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',27,4,27,16,2,'s1',0,0);

        --stairs 's2'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',10,23,13,23,2,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',13,23,15,23,2,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',15,23,15,17,2,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',15,17,7,17,2,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',7,17,7,23,2,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',7,23,10,23,2,'s2',0,0);


        --Floor 3
        --Floor exterior walls
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',10,27,15,27,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',2,17,2,22,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,27,10,27,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',15,27,24,27,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,27,24,18,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,18,36,18,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,18,36,2,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,2,24,2,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,2,2,3,'-1',1,0);

        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,2,2,17,3,'-1',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,22,2,27,3,'-1',1,0);

        --room '301'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',24,9,24,5,3,'301',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,11,24,11,3,'301',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,11,24,9,3,'301',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,5,24,2,3,'301',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,2,13,2,3,'301',1,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,13,11,3,'301',0,0);

        --stairs 's2'
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',10,23,13,23,3,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',13,23,15,23,3,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',15,23,15,17,3,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',15,17,7,17,3,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',7,17,7,23,3,'s2',0,0);
        insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('stairs',7,23,10,23,3,'s2',0,0);
commit;