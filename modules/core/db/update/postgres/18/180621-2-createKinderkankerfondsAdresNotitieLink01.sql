alter table KINDERKANKERFONDS_ADRES_NOTITIE_LINK add constraint FK_ADRNOT_ON_NOTITIE foreign key (NOTITIE_ID) references KINDERKANKERFONDS_NOTITIE(ID);
alter table KINDERKANKERFONDS_ADRES_NOTITIE_LINK add constraint FK_ADRNOT_ON_ADRES foreign key (ADRES_ID) references KINDERKANKERFONDS_ADRES(ID);