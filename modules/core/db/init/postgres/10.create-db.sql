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
    CLIENT integer not null,
    --
    VOORNAAM varchar(255),
    FAMILIENAAM varchar(255),
    AANSPREKING1 varchar(255),
    AANSPREKING2 varchar(255),
    VADER varchar(255),
    MOEDER varchar(255),
    GEBOORTEDATUM date,
    OVERLIJDENSDATUM date,
    FAMILIEDAG boolean,
    ONTMOETINGSDAG boolean,
    RAAKPUNT boolean,
    OUDERCOMITE boolean,
    COMFORTFORFAIT boolean,
    PALLIATIEFFORFAIT boolean,
    PERSONEEL boolean,
    ACTIEF boolean,
    GESLACHT_ID uuid,
    ZIEKENHUIS_ID uuid,
    UNIEKEID varchar(255),
    AFDELING_ID uuid,
    TOESTEMMING_FOTO boolean,
    ADOWERKING boolean,
    NADOWERKING boolean,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
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
    CLIENT integer not null,
    --
    OMSCHRIJVING text,
    ADRESSEN_ID uuid,
    ZIEKENHUIS_ID uuid,
    CONTACTINFO_ID uuid,
    PERSOON_ID uuid,
    AFSPRAAK_ID uuid,
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
    CLIENT integer not null,
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
-- begin KINDERKANKERFONDS_AFSPRAAK
create table KINDERKANKERFONDS_AFSPRAAK (
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
    START_DATE timestamp,
    END_DATE timestamp,
    PLANNED_START_DATE timestamp,
    PLANNED_END_DATE timestamp,
    PERSOON_ID uuid,
    DESCRIPTION varchar(255),
    KALENDER_KLEUR_ID uuid,
    INGEPLAND_ID uuid,
    UITGEVOERD_ID uuid,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_AFSPRAAK
-- begin KINDERKANKERFONDS_AFDELING
create table KINDERKANKERFONDS_AFDELING (
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
    NAAM varchar(255),
    ACTIEF boolean,
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_AFDELING
-- begin KINDERKANKERFONDS_KALENDER_KLEUR
create table KINDERKANKERFONDS_KALENDER_KLEUR (
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
    NAAM varchar(255) not null,
    CSS_STYLE_NAME varchar(255) not null,
    LEGENDE varchar(255),
    --
    primary key (ID)
)^
-- end KINDERKANKERFONDS_KALENDER_KLEUR
-- begin KINDERKANKERFONDS_DAGBOEK_ENTRY
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
)^
-- end KINDERKANKERFONDS_DAGBOEK_ENTRY
