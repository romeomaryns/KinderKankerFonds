alter table KINDERKANKERFONDS_LAND add column CLIENT integer ^
update KINDERKANKERFONDS_LAND set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_LAND alter column CLIENT set not null ;
