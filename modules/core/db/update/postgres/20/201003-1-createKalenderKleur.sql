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
);