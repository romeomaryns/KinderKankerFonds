create table KINDERKANKERFONDS_DAGBOEK_ENTRY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    CLIENT integer not null,
    --
    TEXT varchar(255),
    PERSOON_ID uuid,
    ATTENTION boolean,
    DATE date,
    --
    primary key (ID)
);