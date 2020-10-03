alter table KINDERKANKERFONDS_ZIEKENHUIS add column CLIENT integer ^
update KINDERKANKERFONDS_ZIEKENHUIS set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_ZIEKENHUIS alter column CLIENT set not null ;
