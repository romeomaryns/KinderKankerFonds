-- begin KINDERKANKERFONDS_PERSOON
create table KINDERKANKERFONDS_PERSOON (
    ID uuid,
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
    OVERLIJDENSDATUM date,
    FAMILIEDAG boolean,
    ONTMOETINGSDAG boolean,
    RAAKPUNT boolean,
    OUDERCOMITE boolean,
    ACTIEF boolean,
    GESLACHT_ID uuid,
    ZIEKENHUIS_ID uuid,
    UNIEKEID varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_PERSOON
-- begin KINDERKANKERFONDS_ADRES
create table KINDERKANKERFONDS_ADRES (
    ID uuid,
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
    LAND_ID uuid,
    PERSOON_ID uuid,
    TYPE_ID uuid,
    ACTIEF boolean,
    UNIEKEID varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_ADRES
-- begin KINDERKANKERFONDS_ADRES_TYPE
create table KINDERKANKERFONDS_ADRES_TYPE (
    ID uuid,
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
    ID uuid,
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
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TYPE_ID uuid,
    PERSOON_ORIGINE_ID uuid,
    PERSOON_DOEL_ID uuid,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_RELATIE
-- begin KINDERKANKERFONDS_RELATIE_TYPE
create table KINDERKANKERFONDS_RELATIE_TYPE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAAM varchar(255),
    TUSSENVOEGSEL varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_RELATIE_TYPE
-- begin KINDERKANKERFONDS_CATEGORIE
create table KINDERKANKERFONDS_CATEGORIE (
    ID uuid,
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
-- end KINDERKANKERFONDS_CATEGORIE
-- begin KINDERKANKERFONDS_ZIEKENHUIS
create table KINDERKANKERFONDS_ZIEKENHUIS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAAM varchar(255),
    AFKORTING varchar(255),
    ADRES_ID uuid,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_ZIEKENHUIS
-- begin KINDERKANKERFONDS_CONTACT_INFO
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
    ACTIEF boolean,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_CONTACT_INFO
-- begin KINDERKANKERFONDS_NOTITIE
create table KINDERKANKERFONDS_NOTITIE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    OMSCHRIJVING text,
    ADRESSEN_ID uuid,
    ZIEKENHUIS_ID uuid,
    CONTACTINFO_ID uuid,
    PERSOON_ID uuid,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_NOTITIE
-- begin KINDERKANKERFONDS_LAND
create table KINDERKANKERFONDS_LAND (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAAM varchar(255),
    LANDCODE varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_LAND
-- begin KINDERKANKERFONDS_PERSOON_CATEGORIE_LINK
create table KINDERKANKERFONDS_PERSOON_CATEGORIE_LINK (
    CATEGORIE_ID uuid,
    PERSOON_ID uuid,
    primary key (CATEGORIE_ID, PERSOON_ID)
)^
-- end KINDERKANKERFONDS_PERSOON_CATEGORIE_LINK
-- begin KINDERKANKERFONDS_ADRES_CATEGORIE_LINK
create table KINDERKANKERFONDS_ADRES_CATEGORIE_LINK (
    ADRES_ID uuid,
    CATEGORIE_ID uuid,
    primary key (ADRES_ID, CATEGORIE_ID)
)^
-- end KINDERKANKERFONDS_ADRES_CATEGORIE_LINK
