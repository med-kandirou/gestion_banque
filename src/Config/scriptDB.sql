create database EasyBank;

\c EasyBank;
-- create enum
create type etat as enum ('active','desactive');
create type typeOp as enum ('versement','retrait');

create table client (
                        code varchar(20) primary key ,
                        nom varchar(50) not null,
                        prenom varchar(50) not null,
                        dateNaissance date not null,
                        telephone varchar(50) not null,
                        adresse varchar(50) not null
);

create table Employe (
                         matricule varchar(20) primary key,
                         nom varchar(50) not null,
                         prenom varchar(50) not null,
                         dateNaissance date not null,
                         telephone varchar(50) not null,
                         dateDeRecrutement date not null,
                         adresseEmail varchar(50) not null
);

create table compte(
                code varchar(20) primary key,
                solde float not null,
                dateCreation date not null ,
                etat etat not null,
                client_id varchar(20) not null,
                Emp_mat varchar(20) not null,
                foreign key (client_id) references client(code) on delete cascade on update cascade,
                foreign key (Emp_mat) references employe(matricule) on delete cascade on update cascade)
;

create table CompteCourant (
                code varchar(20) primary key,
                decouvert float not null,
                foreign key (code) references Compte(code) on delete cascade on update cascade
);
create table CompteEpargne (
                code varchar(20) primary key,
                taux float not null,
                foreign key (code) references Compte(code) on delete cascade on update cascade
);

create table Mission (
                         code serial primary key,
                         nom varchar(50) not null,
                         description varchar(50) not null
);
create table Operation (
                           numero serial primary key,
                           dateOperation date not null,
                           montant float not null,
                           type typeOp not null,
                           compte_id varchar(20) not null,
                           Emp_mat varchar(20) not null,
                           FOREIGN KEY (Emp_mat) REFERENCES Employe(matricule) ON DELETE CASCADE ON UPDATE CASCADE,
                           FOREIGN KEY (compte_id) REFERENCES CompteCourant(code) ON DELETE CASCADE ON UPDATE CASCADE ,
                           FOREIGN KEY (compte_id) REFERENCES CompteEpargne(code) ON DELETE CASCADE ON UPDATE CASCADE
);
create table affectation(
                            id serial,
                            Emp_mat varchar(20),
                            codeMission int,
                            dateChangement date not null,
                            primary key (id,Emp_mat,codeMission),
                            foreign key (Emp_mat) references Employe(matricule) on delete cascade on update cascade,
                            foreign key (codeMission) references Mission(code) on delete cascade on update cascade
);

