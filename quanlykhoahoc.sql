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
alter table LOPHOC add ten_LH nvarchar(30) not null
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



----  VER 1.2 ------ GIANG

go
create table LICHHOC(
	id_LIH int identity primary key,
	id_LH int not null,
	thu nchar(10) not null,
	tiet char(30) not null,
	ghichu_LIH text
)
go
alter table LICHHOC add constraint FK_LIH_LH foreign key(id_LH) references LOPHOC(id_LH)
go

create table LOPCHO(
	id_HV int not null,
	id_LH int not null,
	ghichu_LC text,
	primary key(id_HV,id_LH),
	constraint FK_LC_HV foreign key (id_HV) references HOCVIEN (id_HV),
	constraint FK_LC_LH foreign key (id_LH) references LOPHOC (id_LH),
)

---- VER 1.3---- GIANG
go

alter table LOPHOC add ghichu_LH text

---- VER 1.4 --- GIANG
 
	---xoá bảng LOPCHO
drop table LOPCHO

---- VER 1.5 --- GIANG

-- Thêm trigger cho lớp học. xoá 1 lớp thì xoá cả ở bảng HOCVIEN_LOPHOC, LICHHOC

go

create trigger trg_DelLop_HOCVIEN_LOPHOC
on LOPHOC
for delete
as
	begin
		declare @id_LH int
		set @id_LH = (select id_LH from deleted)
		delete from HOCVIEN_LOPHOC
		where id_LH = @id_LH
	end 

go

create trigger trg_DelLop_LICHHOC
on LOPHOC
for delete
as
	begin
		declare @id_LH int
		set @id_LH = (select id_LH from deleted)
		delete from LICHHOC
		where id_LH = @id_LH
	end 

	
---- VER 1.6 ----- GIANG

	--- Disable 2 cái trigger ở 1.5 ------
go
	
disable trigger trg_DelLop_HOCVIEN_LOPHOC on LOPHOC
go
disable trigger trg_DelLop_LICHHOC on LOPHOC
	
	--- Thêm thuộc tính sĩ số cho lớp học
go
alter table LOPHOC add siso_LH int default 0 not null

	--- Thêm trigger tăng sĩ số nếu có học viên vào học
go
create trigger trg_insertHV_LH_tangSiSo
on HOCVIEN_LOPHOC
for insert
as
	begin
		declare @id_LH int
		set @id_LH = (select id_LH from inserted)
		update LOPHOC set siso_LH = siso_LH + 1 where id_LH = @id_LH
	end 

	--- Sửa kiểu ghi chú sang NTEXT
go
alter table LICHHOC alter column ghichu_LIH nvarchar(100)
alter table LOPHOC alter column ghichu_LH nvarchar(100)

go
alter table LICHHOC alter column ghichu_LIH ntext
alter table LOPHOC alter column ghichu_LH ntext

