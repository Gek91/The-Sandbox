-- Spring loads this file if it's in src/main/resources

INSERT INTO expense_type (id, name) values
(1,	'type1'),
(2,	'type2'),
(3,	'type3'),
(4,	'type4'),
(5,	'type5');

INSERT INTO expense_data
(id, 	expense_date, 	creation_datetime, 		modification_datetime, type_id)
values
(1,		'2022-11-21',	'2022-11-21 18:00:00',	'2022-11-21 18:00:00',	1);

INSERT INTO expense_split
(expense_id,		record_date,		`amount`)
values
(1,					'2022-11-21',		30),
(1,					'2022-12-21',		20);