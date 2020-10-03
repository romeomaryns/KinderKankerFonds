alter table KINDERKANKERFONDS_ADRES add column CLIENT integer ^
update KINDERKANKERFONDS_ADRES set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_ADRES alter column CLIENT set not null ;
