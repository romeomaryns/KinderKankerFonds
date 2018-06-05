-- begin KINDERKANKERFONDS_PERSOON
create table KINDERKANKERFONDS_PERSOON (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    VOORNAAM varchar(255),
    FAMILIENAAM varchar(255),
    GEBOORTEDATUM date,
    GESLACHT_ID varchar(36),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_PERSOON
-- begin KINDERKANKERFONDS_ADRES
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
    POSTCODE varchar(255),
    STAD varchar(255),
    PERSOON_ID varchar(36),
    TYPE_ID varchar(36),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_ADRES
-- begin KINDERKANKERFONDS_ADRES_TYPE
create table KINDERKANKERFONDS_ADRES_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAAM varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_ADRES_TYPE
-- begin KINDERKANKERFONDS_GESLACHT
create table KINDERKANKERFONDS_GESLACHT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAAM varchar(255) not null,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_GESLACHT
-- begin KINDERKANKERFONDS_RELATIE
create table KINDERKANKERFONDS_RELATIE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TYPE_ID varchar(36),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_RELATIE
-- begin KINDERKANKERFONDS_RELATIE_TYPE
create table KINDERKANKERFONDS_RELATIE_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAAM varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_RELATIE_TYPE
-- begin KINDERKANKERFONDS_PERSOON_RELATIE_LINK
create table KINDERKANKERFONDS_PERSOON_RELATIE_LINK (
    RELATIE_ID varchar(36) not null,
    PERSOON_ID varchar(36) not null,
    primary key (RELATIE_ID, PERSOON_ID)
)^
-- end KINDERKANKERFONDS_PERSOON_RELATIE_LINK
