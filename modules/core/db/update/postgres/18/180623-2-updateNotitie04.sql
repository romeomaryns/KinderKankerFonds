alter table KINDERKANKERFONDS_NOTITIE add constraint FK_KINDERKANKERFONDS_NOTITIE_ON_PERSOON foreign key (PERSOON_ID) references KINDERKANKERFONDS_PERSOON(ID);
create index IDX_KINDERKANKERFONDS_NOTITIE_ON_PERSOON on KINDERKANKERFONDS_NOTITIE (PERSOON_ID);