alter table KINDERKANKERFONDS_NOTITIE add constraint FK_KINDERKANKERFONDS_NOTITIE_ON_ZIEKENHUIS foreign key (ZIEKENHUIS_ID) references KINDERKANKERFONDS_ZIEKENHUIS(ID);
create index IDX_KINDERKANKERFONDS_NOTITIE_ON_ZIEKENHUIS on KINDERKANKERFONDS_NOTITIE (ZIEKENHUIS_ID);
