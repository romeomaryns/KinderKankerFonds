create table KINDERKANKERFONDS_ADRES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STRAATNAAM varchar(255),
    HUISNUMMER integer,
    BUS varchar(10),
    POSTCODE integer,
    STAD varchar(255),
    PERSOON_ID varchar(36),
    --
    primary key (ID)
);
