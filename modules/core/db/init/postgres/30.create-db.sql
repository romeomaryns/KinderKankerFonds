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
	
	
	
INSERT INTO public.kinderkankerfonds_persoon(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, voornaam, familienaam, geboortedatum, geslacht_id)
	VALUES ('4427a984-bd8a-7327-9751-4fd368d9608b', 1, current_date, 'admin', current_date, null, null, null, 'Eric', 'Maryns', '1952-08-24', 'a324c5d0-56bb-2f45-c5ee-ecb3dcf39b86');	
INSERT INTO public.kinderkankerfonds_persoon(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, voornaam, familienaam, geboortedatum, geslacht_id)
	VALUES ('5241fe77-31d4-c55b-b964-5b316f913ec4', 1, current_date, 'admin', current_date, null, null, null, 'Claudia', 'Maryns', '1983-09-06', 'd404e03b-3fb4-80e5-3023-8a9ffaf511d0');	
INSERT INTO public.kinderkankerfonds_persoon(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, voornaam, familienaam, geboortedatum, geslacht_id)
	VALUES ('b6cfc1e2-77d7-d257-9e5a-04d3797955d4', 1, current_date, 'admin', current_date, null, null, null, 'Romeo', 'Maryns', '1985-03-03', 'a324c5d0-56bb-2f45-c5ee-ecb3dcf39b86');	
INSERT INTO public.kinderkankerfonds_persoon(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, voornaam, familienaam, geboortedatum, geslacht_id)
	VALUES ('ced561a9-37a1-0246-f9fc-31c299d3513f', 1, current_date, 'admin', current_date, null, null, null, 'Magda', 'Körmöczi', '1955-11-30', 'd404e03b-3fb4-80e5-3023-8a9ffaf511d0');	

INSERT INTO public.kinderkankerfonds_adres(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, straatnaam, huisnummer, bus, postcode, stad, persoon_id, type_id)
	VALUES ('06cb96e1-70e8-87e5-13ff-53fb1999ea1e', 1, current_date, 'admin', current_date, null, null, null, 'Martelaarslaan', '393', 'null', '9000', 'Gent', '4427a984-bd8a-7327-9751-4fd368d9608b',  '9fbba1a1-eb25-3c86-d554-5d363dc7b86e');	
INSERT INTO public.kinderkankerfonds_adres(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, straatnaam, huisnummer, bus, postcode, stad, persoon_id, type_id)
	VALUES ('06cb96e1-70e8-87e5-13ff-53fb1999ea12', 1, current_date, 'admin', current_date, null, null, null, 'Serskampstraat', '35', 'null', '9230', 'Wetteren', 'b6cfc1e2-77d7-d257-9e5a-04d3797955d4',  '9fbba1a1-eb25-3c86-d554-5d363dc7b86e');	
INSERT INTO public.kinderkankerfonds_adres(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, straatnaam, huisnummer, bus, postcode, stad, persoon_id, type_id)
	VALUES ('4fb73566-743e-19e0-a4db-6625b555f5d6', 1, current_date, 'admin', current_date, null, null, null, 'Martelaarslaan', '401', 'null', '9000', 'Gent', '5241fe77-31d4-c55b-b964-5b316f913ec4',  'bf8daae3-0a2c-13de-934d-5c0a70cd608c');	
INSERT INTO public.kinderkankerfonds_adres(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, straatnaam, huisnummer, bus, postcode, stad, persoon_id, type_id)
	VALUES ('9f07d909-bbb1-2b4d-5634-12d302abe68f', 1, current_date, 'admin', current_date, null, null, null, 'Hortensiastraat', '117', 'null', '9000', 'Gent', '5241fe77-31d4-c55b-b964-5b316f913ec4',  '9fbba1a1-eb25-3c86-d554-5d363dc7b86e');	
INSERT INTO public.kinderkankerfonds_adres(
	id, version, create_ts, created_by, update_ts, updated_by, delete_ts, deleted_by, straatnaam, huisnummer, bus, postcode, stad, persoon_id, type_id)
	VALUES ('d43393a2-eb54-fca3-7198-726c344cfd63', 1, current_date, 'admin', current_date, null, null, null, 'Martelaarslaan', '393', 'null', '9000', 'Gent', 'ced561a9-37a1-0246-f9fc-31c299d3513f',  '9fbba1a1-eb25-3c86-d554-5d363dc7b86e');
