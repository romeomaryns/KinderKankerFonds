alter table KINDERKANKERFONDS_NOTITIE add column AFSPRAAK_ID uuid ;
alter table KINDERKANKERFONDS_NOTITIE add column CLIENT integer ^
update KINDERKANKERFONDS_NOTITIE set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_NOTITIE alter column CLIENT set not null ;
