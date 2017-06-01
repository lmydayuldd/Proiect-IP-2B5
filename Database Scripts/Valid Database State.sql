delete from temporary_data;
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',10,27,15,27,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',2,17,2,22,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,27,10,27,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',15,27,24,27,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,27,24,18,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,18,36,18,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,18,36,2,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',36,2,24,2,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,11,2,17,0,'-1',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,22,2,27,0,'-1',1,0);

--room '101'
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',5,11,10,11,0,'101',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,11,5,11,0,'101',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',10,11,13,11,0,'101',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,11,13,2,0,'101',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,2,2,0,'101',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',2,2,2,11,0,'101',1,0);

--room '102'
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',24,9,24,5,0,'102',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,11,24,11,0,'102',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,11,24,9,0,'102',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,5,24,2,0,'102',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',24,2,13,2,0,'102',1,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',13,2,13,11,0,'102',0,0);

--room '103'
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',29,16,32,16,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('door',34,13,34,10,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',27,16,29,16,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',32,16,34,16,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',34,16,34,13,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',34,10,34,4,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',34,4,27,4,0,'103',0,0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexterior ,isexitway) values ('wall',27,4,27,16,0,'103',0,0);

commit;