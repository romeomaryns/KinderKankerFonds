alter table KINDERKANKERFONDS_ADRES_TYPE add column CLIENT integer ^
update KINDERKANKERFONDS_ADRES_TYPE set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_ADRES_TYPE alter column CLIENT set not null ;
