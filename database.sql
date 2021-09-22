create database BSPkh;
use BSPkh;
create table user (
	account varchar(20) NOT NULL UNIQUE,
	password varchar(20) NOT NULL,
	email varchar(30) NOT NULL UNIQUE,
	primary key(account)
);

create table message (
	clientId varchar(20) not null,
	info varchar(50),
	value int not null,
	alert int not null check(alert=0 or alert=1),
	lng double not null,
	lat double not null,
	timestamp bigint not null,
	primary key(clientId, timestamp)
);

create table userDevice(
	account varchar(20) not null,
	deviceId varchar(20) not null,
	deviceName varchar(20) not null,
	deviceInfo varchar(100),
	primary key(account, deviceId),
	foreign key(account) references user(account)	
);
