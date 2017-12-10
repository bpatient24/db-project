insert into Manager(taxID, name, username, password, address, state, phone, email, ssn) values (1000, 'John Admin', 'admin', 'secret', 'Stock Company SB', 'CA', '8056374632', 'admin@stock.com', '606-60-6060');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (1022,'Alfred Hitchcock','alfred','hi','6667 El Colegio #40 SB','CA','(805)2574499','alfred@hotmail.com','606-76-7900');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (3045,'Billy Clinton','billy','cl','5777 Hollister SB','CA','(805)5629999','billy@yahoo.com','606-76-7903');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (2034,'Cindy Laugher','cindy','la','7000 Hollister SB','CA','(805)6930011','cindy@hotmail.com','606-70-7900');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (4093,'David Copperfill','david','co','1357 State St SB','CA','(805)8240011','david@yahoo.com','506-78-7900');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (1234,'Elizabeth Sailor','sailor','sa','4321 State St SB','CA','(805)1234567','sailor@hotmail.com','436-76-7900');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (8956,'George Brush','brush','br','5346 Foothill Av','CA','(805)1357999','george@hotmail.com','632-45-7900');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (2341,'Ivan Stock','ivan','st','1235 Johnson Dr','NJ','(805)3223243','ivan@yahoo.com','609-23-7900 ');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (0456,'Joe Pepsi','joe','pe','3210 State St','CA','(805)5668123','pepsi@pepsi.com','646-76-3430');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (3455,'Magic Jordon','magic','jo','3852 Court Rd','NJ','(805)4535539','jordon@jordon.org','646-76-8843');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (1123,'Olive Stoner','olive','st','6689 El Colegio #151','CA','(805)2574499','olive@yahoo.com','645-34-7900');
insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (3306,'Frank Olson','frank','ol','6910 Whittier Dr','CA','(805)3456789','frank@gmail.com','345-23-2134');
insert into marketAccount(taxID,maID,balance) values (1022,001,10000);
insert into marketAccount(taxID,maID,balance) values (3045,002,100000);
insert into marketAccount(taxID,maID,balance) values (2034,003,50000);
insert into marketAccount(taxID,maID,balance) values (4093,004,45000);
insert into marketAccount(taxID,maID,balance) values (1234,005,200000);
insert into marketAccount(taxID,maID,balance) values (8956,006,5000);
insert into marketAccount(taxID,maID,balance) values (2341,007,2000);
insert into marketAccount(taxID,maID,balance) values (0456,008,10000);
insert into marketAccount(taxID,maID,balance) values (3455,009,130200);
insert into marketAccount(taxID,maID,balance) values (1123,010,35000);
insert into marketAccount(taxID,maID,balance) values (3306,011,30500);
insert into Stock(stockID,closingprice,currentprice) values ('SKB',40.00,40.00);
insert into Stock(stockID,closingprice,currentprice) values ('SMD',71.00,71.00);
insert into Stock(stockID,closingprice,currentprice) values ('STC',32.50,32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (1022,100,'SKB',40.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (3045,500,'SMD',71.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (3045,100,'STC',32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (2034,250,'STC',32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (4093,100,'SKB',40.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (4093,500,'SMD',71.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (4093,50,'STC',32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (1234,1000,'SMD',71.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (8956,100,'SKB',40.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (2341,300,'SMD',71.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (0456,500,'SKB',40.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (0456,100,'STC',32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (0456,200,'SMD',71.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (3455,1000,'SKB',40.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (1123,100,'SKB',40.000);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (1123,100,'SMD',71.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (1123,100,'STC',32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (3306,100,'SKB',40.00);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (3306,200,'STC',32.50);
insert into stockAccount(taxID,numshares,stockID,boughtAt) values (3306,100,'SMD',71.00);
insert into Actor(stockID,name,birthday) values ('SKB','Kim Basinger','8 December 1958');
insert into Actor(stockID,name,birthday) values ('SMD','Michael Douglas','25 September 1944');
insert into Actor(stockID,name,birthday) values ('STC','Tom Cruise','3 July 1962');
insert into Contract(title,totalvalue,year,role) values ('L.A. Confidential',5000000,1997,'Actor');
insert into Contract(title,totalvalue,year,role) values ('A Perfect Murder',10000000,1998,'Actor');
insert into Contract(title,totalvalue,year,role) values ('Jerry Maguire',5000000,1996,'Actor');
insert into Date(date) values ('3/18/2013');