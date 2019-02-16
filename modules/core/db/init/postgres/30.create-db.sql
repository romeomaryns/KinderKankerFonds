INSERT INTO public.kinderkankerfonds_adres_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam)
	VALUES ('49a9a797-3b47-a6af-8904-7cfa6b2417b3',1, current_date, 'admin', current_date, null, null, null, 'Verblijfplaats');
INSERT INTO public.kinderkankerfonds_adres_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam)
	VALUES ('9fbba1a1-eb25-3c86-d554-5d363dc7b86e',1, current_date, 'admin', current_date, null, null, null, 'Domicilie');
INSERT INTO public.kinderkankerfonds_adres_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam)
	VALUES ('bf8daae3-0a2c-13de-934d-5c0a70cd608c',1, current_date, 'admin', current_date, null, null, null, 'Werk');

INSERT INTO public.kinderkankerfonds_geslacht(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam)
	VALUES ('a324c5d0-56bb-2f45-c5ee-ecb3dcf39b86', 1 , current_date ,'admin', current_date, null, null, null, 'Man');	
INSERT INTO public.kinderkankerfonds_geslacht(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam)
	VALUES ('d404e03b-3fb4-80e5-3023-8a9ffaf511d0', 1 , current_date ,'admin', current_date, null, null, null, 'Vrouw');	
INSERT INTO public.kinderkankerfonds_geslacht(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam)
	VALUES ('d9ebf0da-6a0f-bf65-c57d-c2a5dbad6a90', 1 , current_date ,'admin', current_date, null, null, null, 'Ander');
	
	
INSERT INTO public.kinderkankerfonds_relatie_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam, tussenvoegsel)
	VALUES ('5daa1e18-c15e-0c5c-39ca-296d1aa8c8b9', 1 , current_date ,'admin', current_date, null, null, null, 'Gehuwd' , 'met');
INSERT INTO public.kinderkankerfonds_relatie_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam, tussenvoegsel)
	VALUES ('81c49514-6495-0af7-426e-c13a04b6926f', 1 , current_date ,'admin', current_date, null, null, null, 'Gescheiden', 'van');
INSERT INTO public.kinderkankerfonds_relatie_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam, tussenvoegsel)
	VALUES ('9055fb35-6ab8-a0fe-dc4e-bf9b9a31fb6f', 1 , current_date ,'admin', current_date, null, null, null, 'Broer/Zus', 'van');
INSERT INTO public.kinderkankerfonds_relatie_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam, tussenvoegsel)
	VALUES ('7e8a595e-db61-d436-d9c0-e01a1495dbe0', 1 , current_date ,'admin', current_date, null, null, null, 'Ouder', 'van');
INSERT INTO public.kinderkankerfonds_relatie_type(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, naam, tussenvoegsel)
	VALUES ('f603a4f1-f6a7-f9a3-d1fd-66a4f85d39e8', 1 , current_date ,'admin', current_date, null, null, null, 'Kind' , 'van');
	
	

insert into KINDERKANKERFONDS_ZIEKENHUIS 
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAAM, AFKORTING, ADRES_ID) 
values ('3cab7815-cc99-7f7d-7e00-3c2a0b8629e1', 1, '2018-08-02 17:28:49', 'admin', '2018-08-02 17:28:49', null, null, null, 'Universitair Ziekenhuis Gent', 'UZG', null);
insert into KINDERKANKERFONDS_ZIEKENHUIS 
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAAM, AFKORTING, ADRES_ID) 
values ('8efc98a0-eb6a-157d-a537-6b4d71ca61ca', 1, '2018-08-02 17:29:02', 'admin', '2018-08-02 17:29:02', null, null, null, 'Universitair Ziekenhuis Antwerpen', 'UZA', null);
insert into KINDERKANKERFONDS_ZIEKENHUIS 
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAAM, AFKORTING, ADRES_ID) 
values ('e07a9f27-6024-b6a5-13d6-f05ec96726df', 1, '2018-08-02 17:29:19', 'admin', '2018-08-02 17:29:19', null, null, null, 'Universitair Ziekenhuis Brussel', 'UZB', null);


insert into KINDERKANKERFONDS_LAND 
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAAM, LANDCODE) 
values ('6428fcf2-ab51-ec23-bfb1-642d34dd032d', 1, '2018-08-02 17:31:46', 'admin', '2018-08-02 17:31:46', null, null, null, 'Frankrijk', 'FR');
insert into KINDERKANKERFONDS_LAND 
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAAM, LANDCODE) 
values ('f792cc4e-e9a6-9ef9-b97b-52e7cb722cf5', 1, '2018-08-02 17:31:41', 'admin', '2018-08-02 17:31:41', null, null, null, 'Nederland', 'NL');
insert into KINDERKANKERFONDS_LAND 
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAAM, LANDCODE) 
values ('9736d156-2f04-fdac-3feb-63a6d0a3da04', 1, '2018-08-02 17:31:27', 'admin', '2018-08-02 17:31:27', null, null, null, 'België', 'BE');
