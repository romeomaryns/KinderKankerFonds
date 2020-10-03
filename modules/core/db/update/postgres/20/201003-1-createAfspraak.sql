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
    AFDELING_ID uuid,
    DESCRIPTION varchar(255),
    KALENDER_KLEUR_ID uuid,
    INGEPLAND_ID uuid,
    UITGEVOERD_ID uuid,
    --
    primary key (ID)
);