alter table KINDERKANKERFONDS_PERSOON add constraint FK_KINDERKANKERFONDS_PERSOON_ON_ZIEKENHUIS foreign key (ZIEKENHUIS_ID) references KINDERKANKERFONDS_ZIEKENHUIS(ID);
create index IDX_KINDERKANKERFONDS_PERSOON_ON_ZIEKENHUIS on KINDERKANKERFONDS_PERSOON (ZIEKENHUIS_ID);
