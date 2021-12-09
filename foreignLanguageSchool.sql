-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema foreignlanguageschool
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema foreignlanguageschool
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `foreignlanguageschool` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `foreignlanguageschool` ;

-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`keys`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`keys` (
  `id_keys` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_keys`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`admins` (
  `idadmins` INT NOT NULL AUTO_INCREMENT,
  `id_keys` INT NOT NULL,
  PRIMARY KEY (`idadmins`),
  UNIQUE INDEX `id_keys_UNIQUE` (`id_keys` ASC) VISIBLE,
  CONSTRAINT `fk_admins_keys`
    FOREIGN KEY (`id_keys`)
    REFERENCES `foreignlanguageschool`.`keys` (`id_keys`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`languages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`languages` (
  `idlanguage` INT NOT NULL AUTO_INCREMENT,
  `language` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idlanguage`),
  UNIQUE INDEX `language_UNIQUE` (`language` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`courses` (
  `idcourse` INT NOT NULL AUTO_INCREMENT,
  `coursename` VARCHAR(45) NOT NULL,
  `idlanguage` INT NOT NULL,
  PRIMARY KEY (`idcourse`),
  INDEX `fk_courses_langages_idx` (`idlanguage` ASC) VISIBLE,
  CONSTRAINT `fk_courses_langages`
    FOREIGN KEY (`idlanguage`)
    REFERENCES `foreignlanguageschool`.`languages` (`idlanguage`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`teachers` (
  `idteacher` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `id_keys` INT NOT NULL,
  `idlanguage` INT NOT NULL,
  PRIMARY KEY (`idteacher`),
  UNIQUE INDEX `id_keys_UNIQUE` (`id_keys` ASC) VISIBLE,
  INDEX `fk_teachers_keys_idx` (`id_keys` ASC) VISIBLE,
  INDEX `fk_teachers_languages_idx` (`idlanguage` ASC) VISIBLE,
  CONSTRAINT `fk_teachers_keys`
    FOREIGN KEY (`id_keys`)
    REFERENCES `foreignlanguageschool`.`keys` (`id_keys`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_teachers_languages`
    FOREIGN KEY (`idlanguage`)
    REFERENCES `foreignlanguageschool`.`languages` (`idlanguage`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`timetables`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`timetables` (
  `idtimetable` INT NOT NULL AUTO_INCREMENT,
  `class` INT NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtimetable`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`groups` (
  `idgroup` INT NOT NULL AUTO_INCREMENT,
  `numberGroup` INT NOT NULL,
  `numberOfStud` INT NULL DEFAULT NULL,
  `dateOfBegin` DATE NULL DEFAULT NULL,
  `idcourse` INT NOT NULL,
  `idteacher` INT NOT NULL,
  `idtimetable` INT NOT NULL,
  PRIMARY KEY (`idgroup`),
  INDEX `fk_groups_courses_idx` (`idcourse` ASC) VISIBLE,
  INDEX `fk_groups_teachers_idx` (`idteacher` ASC) VISIBLE,
  INDEX `fk_groups_timetables_idx` (`idtimetable` ASC) VISIBLE,
  CONSTRAINT `fk_groups_courses`
    FOREIGN KEY (`idcourse`)
    REFERENCES `foreignlanguageschool`.`courses` (`idcourse`),
  CONSTRAINT `fk_groups_teachers`
    FOREIGN KEY (`idteacher`)
    REFERENCES `foreignlanguageschool`.`teachers` (`idteacher`),
  CONSTRAINT `fk_groups_timetables`
    FOREIGN KEY (`idtimetable`)
    REFERENCES `foreignlanguageschool`.`timetables` (`idtimetable`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`payments` (
  `idpayment` INT NOT NULL AUTO_INCREMENT,
  `payment` INT NOT NULL,
  `balance` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idpayment`),
  UNIQUE INDEX `payment_UNIQUE` (`payment` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `foreignlanguageschool`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foreignlanguageschool`.`students` (
  `idstudent` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `averageMark` VARCHAR(45) NULL DEFAULT NULL,
  `colMarks` INT NULL DEFAULT NULL,
  `id_keys` INT NOT NULL,
  `idgroup` INT NULL DEFAULT NULL,
  `idpayment` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idstudent`),
  UNIQUE INDEX `id_keys_UNIQUE` (`id_keys` ASC) VISIBLE,
  UNIQUE INDEX `idpayment_UNIQUE` (`idpayment` ASC) VISIBLE,
  INDEX `fk_students_keys_idx` (`id_keys` ASC) VISIBLE,
  INDEX `fk_students_groups_idx` (`idgroup` ASC) VISIBLE,
  CONSTRAINT `fk_students_groups`
    FOREIGN KEY (`idgroup`)
    REFERENCES `foreignlanguageschool`.`groups` (`idgroup`),
  CONSTRAINT `fk_students_keys`
    FOREIGN KEY (`id_keys`)
    REFERENCES `foreignlanguageschool`.`keys` (`id_keys`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_students_payments`
    FOREIGN KEY (`idpayment`)
    REFERENCES `foreignlanguageschool`.`payments` (`idpayment`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `foreignlanguageschool` ;

-- -----------------------------------------------------
-- procedure alter_group
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alter_group`(grNumber int, classN int, t varchar(45))
BEGIN
	CALL insert_timetable(classN, t, @idt);
    UPDATE `foreignlanguageschool`.`groups`
    SET `idtimetable` = @idt
	where `numberGroup` = grNumber;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure alter_student
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alter_student`(idk int, course varchar(45), lang varchar(45), 
payment int, balance int, numberOfGroup int)
BEGIN
	CALL find_idgroup(lang, course, numberOfGroup, @idgr);
	CALL insert_payment(payment, balance, @idpayment);
    UPDATE `foreignlanguageschool`.`students`
    SET `idpayment` = @idpayment, `idgroup` = @idgr
	where `id_keys` = idk;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure change_teacher
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `change_teacher`(lastlogin varchar(45), firstn varchar(45), lastn varchar(45),
cat varchar(45), logi varchar(45), passw varchar(45))
BEGIN
	select `id_keys` into @id_k from `keys` where `login` = lastlogin;
	update `teachers` set
    `firstname` = firstn,
    `lastname` = lastn,
    `category` = cat
    where id_keys = @id_k;
    update `keys` set
    `login` = logi,
    `password` = passw
    where login = lastlogin;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure change_techer
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `change_techer`(lastlogin varchar(45), firstn varchar(45), lastn varchar(45),
cat varchar(45), logi varchar(45), passw varchar(45))
BEGIN
	select `id_keys` into @id_k from `keys` where `login` = lastlogin;
	update `teachers` set
    `firsname` = firstn,
    `lastname` = lastn,
    `category` = cat
    where id_keys = @id_k;
    update `keys` set
    `login` = logi,
    `passwors` = passw
    where login = lastlogin;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure change_timetable
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `change_timetable`(grNumber int, classN int, t varchar(45))
BEGIN
	select `idtimetable` into @idtimet from `groups` where `numberGroup` = grNumber;
	update `timetables` set
    `class` = classN,
    `time` = t
    where idtimetable = @idtimet;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure delete_student
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_student`(login_ varchar(45))
BEGIN
	select `id_keys` into @id_k from `keys` where `login` = login_;
	select `idgroup` into @gr from `students` where `id_keys` = @id_k;
    update `groups`
    set numberOfStud = numberOfStud - 1
    where idgroup = @gr;
    select `idpayment` into @id_p from `students` where id_keys = @id_k;
    delete from `payments`
    where idpayment = @id_p;
	delete from `keys`
    where id_keys = @id_k;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure find_grTimetable
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_grTimetable`(gr int)
BEGIN
	Select `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`,
    `teachers`.`firstname`, `teachers`.`lastname` 
    from `groups`
    join timetables on timetables.idtimetable = `groups`.`idtimetable`
    join teachers on teachers.idteacher = `groups`.`idteacher`
    join courses on courses.idcourse = `groups`.`idcourse`
    join languages on languages.idlanguage = courses.idlanguage
    where numberGroup = gr;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure find_idgroup
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_idgroup`(lang varchar(45), course varchar(45), 
numberOfGroup int, out idgr int)
BEGIN
	call get_idcourse(lang, course, @idc);
    set idgr = 0;
    set @numberOfStud = 0;
    select `idgroup`, `numberOfStud` into idgr, @numberOfStud from `groups` where `idcourse` = @idc ORDER BY idgroup DESC LIMIT 1;
    case
		when idgr = 0 or @numberOfStud >= 5
			then call insert_group(lang, course, numberOfGroup, @numberOfStud, idgr);
		else update `groups`
				set `numberOfStud` = @numberOfStud + 1
                where idgroup = idgr;
	end case;
	
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure find_login
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_login`(uslogin VARCHAR(45), uspass VARCHAR(45), OUT id_user INT, OUT usrole VARCHAR(10))
BEGIN
SET id_user = 0;
SET usrole = "";
SELECT id_keys INTO id_user FROM `keys`
WHERE `login` = uslogin AND `password` = uspass;

SELECT COALESCE(ur, "") into usrole
FROM ( select "student" as ur from `students` where id_keys = id_user
union
select "teacher" as ur from `teachers` where id_keys = id_user
union
select "admin" as ur from `admins` where id_keys = id_user
) as T;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure find_teacherTimetable
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_teacherTimetable`(k int)
BEGIN
	Select `keys`.`login`, `teachers`.`lastname`, `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`
    from `groups`
    join timetables on timetables.idtimetable = `groups`.`idtimetable`
    join teachers on teachers.idteacher = `groups`.`idteacher`
    join `keys` on `keys`.id_keys = teachers.id_keys
    join courses on courses.idcourse = `groups`.`idcourse`
    join languages on languages.idlanguage = courses.idlanguage
    where `keys`.id_keys = k;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_admin
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_admin`()
BEGIN
	select `keys`.login, `keys`.`password` from admins
    join `keys` on `keys`.id_keys = admins.id_keys;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_chartPayments
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_chartPayments`()
BEGIN
	select count(payments.balance) as receive, languages.`language`
    from students
    join `groups` on `groups`.idgroup = students.idgroup
    join `courses` on `courses`.idcourse = `groups`.idcourse
    join languages on `languages`.idlanguage = courses.idlanguage
    left join `payments` on `payments`.idpayment = students.idpayment
    group by languages.`language`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_chartProgress
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_chartProgress`()
BEGIN
	select col(payments.balance) as receive, languages.`language`
    from students
    join `groups` on `groups`.idgroup = students.idgroup
    join `courses` on `courses`.idcourse = `groups`.idcourse
    join languages on `languages`.idlanguage = courses.idlanguage
    left join `payments` on `payments`.idpayment = students.idpayment
    group by languages.`language`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_course
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_course`(l varchar(45))
BEGIN
	select coursename, languages.`language` from courses
    join languages on languages.idlanguage = courses.idlanguage
    where languages.`language` = l;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_grNumber
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_grNumber`(st int, out gr int)
BEGIN
	select idgroup into @id from students where id_keys = st;
    select numberGroup into gr from `groups` where idgroup = @id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_grTimetable
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_grTimetable`()
BEGIN
	Select `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`,
    `teachers`.`firstname`, `teachers`.`lastname`
    from `groups`
    join timetables on timetables.idtimetable = `groups`.`idtimetable`
    join teachers on teachers.idteacher = `groups`.`idteacher`
    join courses on courses.idcourse = `groups`.`idcourse`
    join languages on languages.idlanguage = courses.idlanguage;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_idcourse
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_idcourse`(lang varchar(45), course varchar(45), out idcrs int)
BEGIN
	CALL get_idlanguage(lang, @idlang);
	select `idcourse` into idcrs from courses where (idlanguage = @idlang and coursename = course);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_idkeys_bystudents
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_idkeys_bystudents`(idstud int, out idkeys int)
BEGIN
	select idgroup into @gr from students where id_keys = idstud;
    select idteacher into @t from `groups` where idgroup = @gr;
    select id_keys into idkeys from teachers where idteacher = @t;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_idlanguage
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_idlanguage`(lang varchar(45), out id_lang int)
BEGIN
	select `idlanguage` into id_lang from languages where `language` = lang;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_idteacher
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_idteacher`(lang varchar(45), out idteach int)
BEGIN
	CALL get_idlanguage(lang, @idlang);
	select `idteacher` into idteach from teachers where `idlanguage` = @idlang;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_idteacher_bykeys
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_idteacher_bykeys`(idkeys int, out id int)
BEGIN
	select `idteacher` into id from teachers where id_keys = idkeys;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_marks
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_marks`(idkeys int, stud varchar(45), out mark varchar(45), out col int, out s int)
BEGIN
    select id_keys into s from `keys` where login = stud;
	select `averageMark`, `colMarks` into mark, col from students
    where id_keys = s;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_payments
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_payments`()
BEGIN
	select sum(payments.balance) as receive, languages.`language`
    from students
    join `groups` on `groups`.idgroup = students.idgroup
    join `courses` on `courses`.idcourse = `groups`.idcourse
    join languages on `languages`.idlanguage = courses.idlanguage
    left join `payments` on `payments`.idpayment = students.idpayment
    group by languages.`language`;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_progress
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_progress`()
BEGIN
	select `keys`.login, averageMark from students
    join `keys` on `keys`.id_keys = students.id_keys;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_student
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_student`(k int)
BEGIN
	select `keys`.login, firstname, lastname, averageMark, `groups`.numberGroup, payments.payment
    from students
    join `keys` on `keys`.`id_keys` = students.id_keys
    left join `groups` on `groups`.idgroup = students.idgroup
    left join payments on payments.idpayment = students.idpayment
    where students.id_keys = k;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_teacher
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher`(idkeys int)
BEGIN
	select `keys`.login, firstname, lastname, category, languages.`language`
    from teachers
    join `keys` on `keys`.id_keys = teachers.id_keys
    left join languages on languages.idlanguage = teachers.idlanguage
    where `keys`.id_keys = idkeys;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_teacherTimetable
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacherTimetable`()
BEGIN
	Select `keys`.`login`, `teachers`.`lastname`, `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`
    from `groups`
    join timetables on timetables.idtimetable = `groups`.`idtimetable`
    join teachers on teachers.idteacher = `groups`.`idteacher`
    join `keys` on `keys`.id_keys = teachers.id_keys
    join courses on courses.idcourse = `groups`.`idcourse`
    join languages on languages.idlanguage = courses.idlanguage
    order by teachers.lastname;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_teacher_students
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher_students`(idkeys int)
BEGIN
	select `idteacher` into @id from teachers where id_keys = idkeys;
	select `keys`.login, firstname, lastname, averageMark, `groups`.numberGroup, payments.payment
    from students
    join `keys` on `keys`.`id_keys` = students.id_keys
    left join `groups` on `groups`.idgroup = students.idgroup
    left join payments on payments.idpayment = students.idpayment
    where idteacher = @id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_admin
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_admin`(login varchar(45), pass varchar(45))
BEGIN
	CALL insert_keys(login, pass, @id_keys);
    INSERT INTO `foreignlanguageschool`.`admins` (`id_keys`)
	VALUES (@id_keys);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_course
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_course`(coursen varchar(45), lang varchar(45))
BEGIN
	set @idlang = 0;
	CALL get_idlanguage(lang, @idlang);
    case 
		when @idlang != NULL
			then begin end;
		when @idlang > 0
			then begin end;
		else call insert_language(lang, @idlang);
	end case;
    insert into `foreignlanguageschool`.`courses`(`coursename`, `idlanguage`)
	VALUES (coursen, @idlang);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_group
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_group`(lang varchar(45), course varchar(45), 
numberOfGroup int, out numberOfStud int, out idgr int)
BEGIN
	call get_idteacher(lang, @idteach);
	call get_idcourse(lang, course, @idcourse);
	insert into `foreignlanguageschool`.`groups`(`numberGroup`, `numberOfStud`, `idcourse`, `idteacher`, `idtimetable`)
    values (numberOfGroup, 1, @idcourse, @idteach, 1);
    select last_insert_id() into idgr;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_keys
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_keys`(login varchar(45), pass varchar(45), out id_keys int)
BEGIN
	insert into `foreignlanguageschool`.`keys`(`login`, `password`)
    values (login, pass);
    select last_insert_id() into id_keys;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_language
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_language`(lang varchar(45), out idlang int)
BEGIN
	insert into `foreignlanguageschool`.`languages`(`language`)
    values (lang);
    select last_insert_id() into idlang;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_payment
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_payment`(payment int, balance int, out idpayment int)
BEGIN
	insert into `foreignlanguageschool`.`payments`(`payment`, `balance`)
    values (payment, balance);
    select last_insert_id() into idpayment;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_student
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_student`(firstname varchar(45), lastname varchar(45),
login varchar(45), pass varchar(45), out id_keys int)
BEGIN
	CALL insert_keys(login, pass, id_keys);
    INSERT INTO `foreignlanguageschool`.`students` (`firstname`, `lastname`, `id_keys`)
	VALUES (firstname, lastname, id_keys);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_teacher
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_teacher`(firstname varchar(45), lastname varchar(45), category varchar(45), 
login varchar(45), pass varchar(45), lang varchar(45))
BEGIN
	set @idlanguage = 0;
	CALL get_idlanguage(lang, @idlanguage);
    case
		when @idlanguage != 0
			then CALL insert_keys(login, pass, @id_keys);
		ELSE BEGIN END;
	end case;
    INSERT INTO `foreignlanguageschool`.`teachers` (`firstname`, `lastname`, `category`, `id_keys`, `idlanguage`)
	VALUES (firstname, lastname, category, @id_keys, @idlanguage);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_timetable
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_timetable`(cl int, t varchar(45), out idtimet int)
BEGIN
	insert into `foreignlanguageschool`.`timetables`(`class`, `time`)
    values (cl, t);
    select last_insert_id() into idtimet;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure set_marks
-- -----------------------------------------------------

DELIMITER $$
USE `foreignlanguageschool`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_marks`(s int, mark varchar(45), col int)
BEGIN
	update students
    set averageMark = mark,
    colMarks = col
    where id_keys = s;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
