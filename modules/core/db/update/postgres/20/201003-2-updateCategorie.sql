alter table KINDERKANKERFONDS_CATEGORIE add column CLIENT integer ^
update KINDERKANKERFONDS_CATEGORIE set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_CATEGORIE alter column CLIENT set not null ;
