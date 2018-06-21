create table KINDERKANKERFONDS_CONTACT_INFO (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TELEFOON varchar(255),
    GSM varchar(255),
    EMAIL varchar(255),
    PERSOON_ID uuid,
    --
    primary key (ID)
);
