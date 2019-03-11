use master
go
	if (exists(select * from sys.sysdatabases where name='quanlykhoahoc'))
		drop database quanlykhoahoc

go
create database quanlykhoahoc
on primary (
	name='quanlykhoahoc',
	filename='G:\SQL\quanlykhoahoc.mdf',
	size=5mb,
	maxsize=unlimited,
	filegrowth=10%
)
log on(
	name='quanlykhoahoc_log',
	filename='G:\SQL\quanlykhoahoc_log.ldf',
	size=5mb,
	maxsize=unlimited,
	filegrowth=10%
)
go
use quanlykhoahoc
go
create table GIANGVIEN(
	id_GV int primary key identity,
	ten_GV nvarchar(30) not null,
	ngaysinh_GV date not null,
	sodt_GV varchar(10) not null unique,
	diachi_GV nvarchar(100) not null,
	ghichu_GV text 
)
go
create table PHONGHOC(
	id_PH int primary key identity,
	ten_PH nvarchar(30) not null unique,
	ghichu_PH text
)
go
create table KHOAHOC(
	id_KH int primary key identity,
	ten_KH nvarchar(50) not null unique,
	gia_KH int not null,
	ghichu_KH text
)
go
create table LOPHOC(
	id_LH int primary key identity,
	id_KH int not null,
	ngaybatdau date not null,
	ngayketthuc date not null,
	id_GV int not null
)
go
alter table LOPHOC add id_PH int not null
alter table LOPHOC add constraint LH_GV foreign key(id_GV) references GIANGVIEN(id_GV)
alter table LOPHOC add constraint LH_KH foreign key(id_KH) references KHOAHOC(id_KH)
alter table LOPHOC add constraint LH_PH foreign key(id_PH) references PHONGHOC(id_PH)
go
create table HOCVIEN(
	id_HV int primary key identity,
	ten_HV nvarchar(30) not null,
	sodt_HV varchar(10) not null unique,
	diachi_HV nvarchar(100) not null
)
go
create table HOCVIEN_LOPHOC(
	id_HV int not null,
	id_LH int not null,
	primary key(id_HV,id_LH)
)
go
alter table HOCVIEN_LOPHOC add constraint HVLH_HV foreign key(id_HV) references HOCVIEN(id_HV)
alter table HOCVIEN_LOPHOC add constraint HVLH_LH foreign key(id_LH) references LOPHOC(id_LH)

