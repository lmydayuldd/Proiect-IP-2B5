@".\Create User DataRepresentation.sql"
@".\Temporary Data.sql"
@".\Final Data.sql"
commit;
--@".\populate.sql"


select * from temporary_data;
select * from final_data;