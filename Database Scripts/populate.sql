select * from temporary_data;
select * from final_data;

delete from temporary_data;

insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 0, 0, 5, 0, 1, 'C101', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 0, 0, 0, 5, 1, 'C101', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 0, 5, 5, 5, 1, 'C101', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 5, 5, 5, 0, 1, 'C101', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('window', 0, 2, 0, 3, 1, 'C101', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('door', 5, 3, 5, 4, 1, 'C101', 0, 0);

insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('stairs', 0, 15, 3, 15, 1, 'Stairs1', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('stairs', 3, 15, 3, 18, 1, 'Stairs1', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('stairs', 0, 15, 0, 18, 1, 'Stairs1', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('stairs', 0, 18, 3, 18, 1, 'Stairs1', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('stairs', 3, 16, 3, 17, 1, 'Stairs1', 0, 0);

insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 0, 0, 5, 0, 2, 'C201', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 0, 0, 0, 5, 2, 'C201', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 0, 5, 5, 5, 2, 'C201', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('wall', 5, 5, 5, 0, 2, 'C201', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('window', 0, 2, 0, 3, 2, 'C201', 0, 0);
insert into temporary_data(type, x1, y1, x2, y2, floor, room, isexitway, isexterior) values('door', 5, 3, 5, 4, 2, 'C201', 0, 0);

commit;
