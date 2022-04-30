CREATE TABLE student_type(
                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                             type VARCHAR(45) UNIQUE
);

CREATE TABLE student(
                        id INTEGER PRIMARY KEY NOT NULL,
                        name VARCHAR(45),
                        surname VARCHAR(45),
                        birth_date DATE,
                        student_type INTEGER,
                        FOREIGN KEY(student_type) REFERENCES student_type(id)
);

CREATE TABLE mark(
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
        value VARCHAR(45),
    student INTEGER NOT NULL ,
    CONSTRAINT fk_student
        FOREIGN KEY(student)
            REFERENCES student(id)
            ON DELETE CASCADE
);