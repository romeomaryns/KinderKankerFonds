alter table KINDERKANKERFONDS_CONTACT_INFO add constraint FK_KINDERKANKERFONDS_CONTACT_INFO_ON_PERSOON foreign key (PERSOON_ID) references KINDERKANKERFONDS_PERSOON(ID);
create index IDX_KINDERKANKERFONDS_CONTACT_INFO_ON_PERSOON on KINDERKANKERFONDS_CONTACT_INFO (PERSOON_ID);
