alter table KINDERKANKERFONDS_GESLACHT add column CLIENT integer ^
update KINDERKANKERFONDS_GESLACHT set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_GESLACHT alter column CLIENT set not null ;
