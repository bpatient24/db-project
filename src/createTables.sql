CREATE TABLE Customer(taxID integer, username char(15), password char(15), address char(30), state char(2), phone char(10), email char(20), ssn char(11), PRIMARY KEY(taxID));
CREATE table marketAccount(taxID integer, maID integer, balance float, primary key(taxID), foreign key(taxID) references Customer(taxID));
CREATE TABLE Stock(stockID char(3), closingprice float, currentprice float, primary key(stockID));
CREATE TABLE Actor(stockID char(3), name char(20), birthday char(20), primary key(stockID), foreign key(stockID) references Stock(stockID));
CREATE TABLE stockAccount(saID integer, taxID integer, numshares integer, stockID char(3), boughtAt float, PRIMARY KEY(saID), foreign key(taxID) references Customer(taxID), foreign key(stockID) references Stock(stockID));
CREATE TABLE Transactions(tid integer, type char(10), amount float, date char(10), taxID integer, stockID char(3), numshares integer, primary key(tid), foreign key(taxID) references Customer(taxID), foreign key(stockID) references Stock(stockID));
CREATE TABLE Contract(cid integer, title char(20), totalvalue float, year integer, role char(10), stockID char(3), primary key(cid), foreign key(stockID) references Actor(stockID));
CREATE TABLE Admin(taxID integer, username char(15), password char(15), address char(30), state char(2), phone char(10), email char(20), ssn char(11), PRIMARY KEY(taxID));
