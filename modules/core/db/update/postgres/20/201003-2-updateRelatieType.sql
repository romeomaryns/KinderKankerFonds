alter table KINDERKANKERFONDS_RELATIE_TYPE add column CLIENT integer ^
update KINDERKANKERFONDS_RELATIE_TYPE set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_RELATIE_TYPE alter column CLIENT set not null ;
