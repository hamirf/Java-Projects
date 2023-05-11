--Insert Super Admin
INSERT INTO public.m_user(created_by, created_on)
				  VALUES (1, now());

--Insert Nominal Default
INSERT INTO public.m_wallet_default_nominal (nominal, created_by, created_on)
									 VALUES (50000, 1, now()), (100000, 1, now()), (200000, 1, now());

--Insert Blood Group
INSERT INTO public.m_blood_group (code, description, created_by, created_on)
						  VALUES ('A', '-', 1, now()), 
						  	     ('B', '-', 1, now()), 
						  	     ('O', '-', 1, now()),
						  	     ('AB', '-', 1, now());
	   
-- Insert User Pasien
INSERT INTO public.m_user(biodata_id, role_id, email, password, created_by, created_on)
				  VALUES (1, 1, 'mariana@mail.com', 'mariana23', 1, now()),
				  	     (2, 1, 'burhan@mail.com', 'Burhan23', 1, now()),
				  	     (3, 1, 'bambang@mail.com', 'Bambang23', 1, now());
	   
-- Insert Role
INSERT INTO public.m_role (name, code, created_by, created_on)
				   VALUES ('Pasien', 'PSN', 1, now());
				   
-- Insert Biodata
INSERT INTO public.m_biodata (fullname, mobile_phone, image_path, created_by, created_on)
					  VALUES ('Saitou Asuka', '+6289604890465', '/image/userId_2.jpg', 1, now()),  --ID Biodata : 1, Customer ID : 1
					  		 ('Burhan', '+628123456789', '/image/userId_3.jpg', 1, now()),  --ID Biodata : 2, Customer ID : 2
					  		 ('Bambang', '+628987654321', '/image/userId_4.jpg', 1, now());  --ID Biodata : 3, Customer ID : 3

-- Insert Customer
INSERT INTO public.m_customer (biodata_id, dob, gender, blood_group_id, rhesus_type, created_by, created_on)
					   VALUES (1, '1998-08-10', 'P', 3, 'Rh +', 1, now()),
					   		  (2, '2000-08-21', 'L', 2, 'Rh +', 1, now()),
					   		  (3, '2000-08-19', 'L', 4, 'Rh +', 1, now());
					   		  
-- Insert Biodata (Calon Pasien)
INSERT INTO public.m_biodata (fullname, created_by, created_on)
					  VALUES ('Haidar Amir Faruqi', 1, now()), --ID Biodata : 4, Customer ID : 4
					  		 ('Saitou Haruka', 1, now()),  --ID Biodata : 5, Customer ID : 5
					         ('Saitou Takuma', 1, now()),  --ID Biodata : 6, Customer ID : 6
					         ('Badriah', 1, now()),  --ID Biodata : 7, Customer ID : 7
					         ('Sri', 1, now()),  --ID Biodata : 7, Customer ID : 8
					         ('Ida', 1, now());  --ID Biodata : 8, Customer ID : 9

-- Insert Customer (Calon Pasien)
INSERT INTO public.m_customer (biodata_id, dob, gender, blood_group_id, rhesus_type, created_by, created_on)
					   VALUES (4, '2000-04-30', 'L', 3, 'Rh +', 1, now()),
					   		  (5, '2020-05-22', 'P', 3, 'Rh +', 1, now()),
					   		  (6, '2021-07-28', 'L', 3, 'Rh +', 1, now()),
					   		  (7, '1974-06-09', 'P', 2, 'Rh +', 1, now()),
					   		  (8, '1997-08-04', 'P', 1, 'Rh +', 1, now()),
					   		  (9, '2002-03-03', 'P', 4, 'Rh +', 1, now());

--Insert Customer Relation (Calon Pasien)
INSERT INTO public.m_customer_relation (name, created_by, created_on)
					   			VALUES ('Ibu', 1, now()),
					   				   ('Ayah', 1, now()),
					   				   ('Suami', 1, now()),
					   				   ('Istri', 1, now()),					   				   					   				   
					   				   ('Anak', 1, now()),					   				   					   				   
					   				   ('Kakak', 1, now()),					   				   					   				   
					   				   ('Adik', 1, now()),					   				   					   				   
					   				   ('Paman', 1, now()),					   				   					   				   
					   				   ('Bibi', 1, now()),					   				   					   				   
					   				   ('Kakek', 1, now()),					   				   					   				   
					   				   ('Nenek', 1, now());				   				   					   				   
					   				   					   				   
-- Insert Customer (Calon Pasien)
INSERT INTO public.m_customer_member (parent_biodata_id, customer_id, customer_relation_id, created_by, created_on)
							  VALUES (1, 4, 3, 1, now()),
							  		 (1, 5, 5, 1, now()),
							  		 (1, 6, 5, 1, now()),
							  		 (2, 7, 1, 1, now()),
							  		 (2, 8, 4, 1, now()),
							  		 (3, 9, 4, 1, now());
							  		 
-- Insert Customer Chat (Calon Pasien)
INSERT INTO public.t_customer_chat (customer_id, doctor_id, created_by, created_on)
				   			VALUES (4, 1, 1, now()),
								   (4, 2, 1, now()),
							   	   (4, 3, 1, now()),
							   	   (4, 4, 1, now()),
							   	   (5, 1, 1, now()),
							   	   (5, 2, 1, now()),
							   	   (5, 3, 1, now()),
							   	   (7, 1, 1, now()),
							   	   (7, 2, 1, now()),
							   	   (8, 3, 1, now()),
							   	   (9, 2, 1, now()),
							   	   (9, 3, 1, now());
							   	   
-- Insert Appointment (Calon Pasien)
INSERT INTO public.t_appointment (customer_id, doctor_office_id, doctor_office_schedule_id, doctor_office_treatment_id, appointment_date, created_by, created_on)
						  VALUES (4, 1, 1, 1, now(), 1, now()),
						  		 (4, 2, 2, 2, now(), 1, now()),
						  		 (6, 3, 3, 3, now(), 1, now()),
						  		 (6, 4, 4, 4, now(), 1, now()),
						  		 (7, 1, 1, 1, now(), 1, now()),
						  		 (7, 3, 3, 3, now(), 1, now()),
						  		 (8, 2, 2, 2, now(), 1, now()),
						  		 (9, 1, 1, 1, now(), 1, now());