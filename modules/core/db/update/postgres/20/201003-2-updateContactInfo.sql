alter table KINDERKANKERFONDS_CONTACT_INFO add column CLIENT integer ^
update KINDERKANKERFONDS_CONTACT_INFO set CLIENT = 0 where CLIENT is null ;
alter table KINDERKANKERFONDS_CONTACT_INFO alter column CLIENT set not null ;
