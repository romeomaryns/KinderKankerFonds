create table KINDERKANKERFONDS_PERSOON_RELATIE_LINK (
    RELATIE_ID varchar(36) not null,
    PERSOON_ID varchar(36) not null,
    primary key (RELATIE_ID, PERSOON_ID)
);
