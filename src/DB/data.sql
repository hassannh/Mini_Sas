use mini_sas;

CREATE TABLE book (
                      ISBN VARCHAR(255) PRIMARY KEY,
                      status INT,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL
);

CREATE TABLE mumber (
                        membership_num INT PRIMARY KEY,
                        fullName VARCHAR(255) NOT NULL
);

CREATE TABLE Emprunt (
                         EmpruntID INT PRIMARY KEY,
                         Date_Emprunt TIMESTAMP,
                         Date_Retour TIMESTAMP,
                         Book_ISBN VARCHAR(255),
                         Mumber_membership_num INT,
                         FOREIGN KEY (Book_ISBN) REFERENCES book (ISBN),
                         FOREIGN KEY (Mumber_membership_num) REFERENCES mumber (membership_num)
);










