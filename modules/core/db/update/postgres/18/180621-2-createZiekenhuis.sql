alter table KINDERKANKERFONDS_ZIEKENHUIS add constraint FK_KINDERKANKERFONDS_ZIEKENHUIS_ON_ADRES foreign key (ADRES_ID) references KINDERKANKERFONDS_ADRES(ID);
create index IDX_KINDERKANKERFONDS_ZIEKENHUIS_ON_ADRES on KINDERKANKERFONDS_ZIEKENHUIS (ADRES_ID);
