alter table KINDERKANKERFONDS_RELATIE add column CLIENT integer ^
update KINDERKANKERFONDS_RELATIE set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_RELATIE alter column CLIENT set not null ;
