INSERT INTO locale (lang_code,country) VALUES
	 ('en','US'),
	 ('es','US');

INSERT INTO validation_msgs (msg_key,msg_value,locale_id,created_by,created_ts,updated_by,updated_ts,app_name) VALUES
	 ('id.should.not.supplied.from.object','ID should not be set by client systems.',1,'ASDF','2023-05-12 20:48:15',NULL,NULL,'EMPDEPT'),
	 ('id.should.not.supplied.from.object','Los sistemas cliente no deben establecer ID - DB - es_US.',2,'ASDF','2023-05-12 20:48:15',NULL,NULL,'EMPDEPT'),
	 ('address.street1.notBlank','Street information is required.',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.street1.notBlank','Se requiere información de la calle',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.city.notBlank','City is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.city.notBlank','Ciudad es requerida',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.state.notBlank','State is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.state.notBlank','Se requiere estado',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.zipcode.notBlank','ZipCode is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.zipcode.notBlank','Se requiere código postal',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT');
INSERT INTO validation_msgs (msg_key,msg_value,locale_id,created_by,created_ts,updated_by,updated_ts,app_name) VALUES
	 ('address.zipcode.size','ZipCode can be between {min} and {max} characters length.',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.zipcode.size','ZipCode puede tener entre {min} y {max} caracteres de longitud.',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.zipcode.digits','ZipCode should contain only numbers',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.zipcode.digits','ZipCode debe contener solo números',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.type.notNull','Address type is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('address.type.notNull','El tipo de dirección es obligatorio',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('contact.email.invalid','The email format is invalid',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('contact.email.invalid','El formato del correo electrónico no es válido',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('department.name.required','Department name is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('department.name.required','El nombre del departamento es obligatorio',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT');
INSERT INTO validation_msgs (msg_key,msg_value,locale_id,created_by,created_ts,updated_by,updated_ts,app_name) VALUES
	 ('phone.type.required','Phone type is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('phone.type.required','El tipo de teléfono es obligatorio',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('phone.number.required','Se requiere el número de teléfono',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.first.name.required','Employee''s first name is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.first.name.required','Se requiere el nombre del empleado',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.last.name.required','Employee''s last name is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.last.name.required','Se requiere el apellido del empleado',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.date.of.birth.required','Employee''s date of birth is required.',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.date.of.birth.required','Se requiere la fecha de nacimiento del empleado.',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.date.of.birth.in.past','Employee''s date of birth should be in the past.',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT');
INSERT INTO validation_msgs (msg_key,msg_value,locale_id,created_by,created_ts,updated_by,updated_ts,app_name) VALUES
	 ('employee.date.of.birth.in.past','La fecha de nacimiento del empleado debe estar en el pasado.',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.join.date.required','Employee''s join date is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.join.date.required','Se requiere la fecha de ingreso del empleado',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.join.date.present.or.future','Employee''s join date can be today or in future',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.join.date.present.or.future','La fecha de ingreso del empleado puede ser hoy o en el futuro',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('test.common.key','Common key value test - en_US.',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'COMMON'),
	 ('test.common.key','Prueba de valor clave común - es_US.',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'COMMON'),
	 ('phone.number.required','Phone number is required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.id.required','Employee id is Required',1,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT'),
	 ('employee.id.required','Se requiere identificación de empleado',2,'ASDF','2023-05-12 21:08:07',NULL,NULL,'EMPDEPT');
