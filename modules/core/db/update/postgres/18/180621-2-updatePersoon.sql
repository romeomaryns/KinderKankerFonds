alter table KINDERKANKERFONDS_PERSOON add column OVERLIJDENSDATUM date ;
alter table KINDERKANKERFONDS_PERSOON add column FAMILIEDAG boolean ;
alter table KINDERKANKERFONDS_PERSOON add column ONTMOETINGSDAG boolean ;
alter table KINDERKANKERFONDS_PERSOON add column RAAKPUNT boolean ;
alter table KINDERKANKERFONDS_PERSOON add column OUDERCOMITE varchar(255) ;
alter table KINDERKANKERFONDS_PERSOON add column ACTIEF boolean ;
alter table KINDERKANKERFONDS_PERSOON add column ZIEKENHUIS_ID uuid ;
