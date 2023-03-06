-- Spring loads this file if it's in src/main/resources

CREATE TABLE `users` (
	id BIGINT AUTO_INCREMENT NOT NULL,
	username VARCHAR(40) NOT NULL,
	password VARCHAR(127) NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE expense_type (
	id BIGINT AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE expense_data (
	id						BIGINT(20) AUTO_INCREMENT NOT NULL,
	expense_date			DATE NOT NULL,
	creation_datetime		DATETIME(3) NOT NULL,
	modification_datetime	DATETIME(3) NOT NULL,
	type_id					BIGINT(20) NOT NULL,
	PRIMARY KEY (id),
	INDEX `fk_expense_data_type_idx` (`type_id` ASC),
    CONSTRAINT `fk_expense_type`
    	FOREIGN KEY (`type_id`)
        REFERENCES `expense_type` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE expense_split (
	expense_id 				BIGINT(20) NOT NULL,
	record_date				DATE NOT NULL,
	`amount`				DECIMAL(20,2) NOT NULL,
	PRIMARY KEY (expense_id, record_date),
	INDEX `fk_expense_split_id_idx` (`expense_id` ASC),
    CONSTRAINT `fk_expense_split_id`
        	FOREIGN KEY (`expense_id`)
            REFERENCES `expense_data` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;
