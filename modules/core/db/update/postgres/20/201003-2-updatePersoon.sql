alter table KINDERKANKERFONDS_PERSOON add column CLIENT integer ^
update KINDERKANKERFONDS_PERSOON set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_PERSOON alter column CLIENT set not null ;
alter table KINDERKANKERFONDS_PERSOON add column PERSONEEL boolean ;
