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
	id_GV int not null,
	ten_LH nvarchar(30) not null,
	id_PH int not null,
	ghichu_LH ntext,
	constraint LH_GV foreign key(id_GV) references GIANGVIEN(id_GV),
	constraint LH_KH foreign key(id_KH) references KHOAHOC(id_KH),
	constraint LH_PH foreign key(id_PH) references PHONGHOC(id_PH)
)

go
create table HOCVIEN(
	id_HV int primary key identity,
	ten_HV nvarchar(30) not null,
	sodt_HV varchar(10) not null unique,
	ngaysinh_HV date not null,
	diachi_HV nvarchar(100) not null
)

-------------------------------
go
create table HOCVIEN_LOPHOC(
	id_HV int not null,
	id_LH int not null,
	primary key(id_HV,id_LH),
	diem_1 float not null default -1,
	diem_2 float not null default -1,
	diem_3 float not null default -1,
	diem_4 float not null default -1,
	ghichu_HVLH ntext null,
	constraint HVLH_HV foreign key(id_HV) references HOCVIEN(id_HV),
	constraint HVLH_LH foreign key(id_LH) references LOPHOC(id_LH),	
)

----  VER 1.2 ------ GIANG

go
create table LICHHOC(
	id_LIH int identity primary key,
	id_LH int not null,
	thu int not null,
	tiet varchar(30) not null,
	ghichu_LIH ntext,
	constraint FK_LIH_LH foreign key(id_LH) references LOPHOC(id_LH)
)
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

	--- Thêm Func tìm lớp theo tên
go
create function fn_findLopByName(@ten_LH nvarchar(30))
returns @result table(
	id_LH int,
	id_KH int,
	ngaybatdau date,
	ngayketthuc date,
	id_GV int,
	ten_LH nvarchar(30),
	id_PH int,
	ghichu_LH ntext,
	siso_LH int
)
as
	begin
		insert into @result 
			select * 
			from LOPHOC
			where LOPHOC.ten_LH like ('%'+@ten_LH+'%')
		return
	end
	
	
--- VER 1.8  -- GIANG

	--- Thêm trigger xoá học viên khỏi HOCVIEN_LOPHOC thì giảm sĩ số ở LOPHOC
go

create trigger trg_deleteHV_LH_giamSiSo
on HOCVIEN_LOPHOC
for delete
as
	begin
		declare @id_LH int
		set @id_LH = (select id_LH from inserted)
		update LOPHOC set siso_LH = siso_LH - 1 where id_LH = @id_LH
	end 

--- VER 2.0 -- GIANG
	-- Lấy ra lịch sử dụng theo mã phòng trong 1 khoảng thời gian
alter function fn_GetLichHoc(@id_PH int, @ngaybatdau date, @ngayketthuc date)
returns @tbresult table (
	id_PH int,
	ten_PH nvarchar(30),
	id_LH int,
	ten_LH nvarchar(30),
	thu int,
	tiet varchar(30),
	ngaybatdau date,
	ngayketthuc date
)
as
begin
	insert into @tbresult select PhongHoc.id_PH, ten_PH, LOPHOC.id_LH, ten_LH, thu, tiet, ngaybatdau, ngayketthuc
	from PHONGHOC inner join LOPHOC on (LOPHOC.id_PH = PHONGHOC.id_PH) 
						inner join LICHHOC on (LOPHOC.id_LH = LICHHOC.id_LH)
	where PHONGHOC.id_PH = @id_PH 
		and (
				(ngaybatdau between @ngaybatdau and @ngayketthuc) 
				or (ngayketthuc between @ngaybatdau and @ngayketthuc)
				or (ngaybatdau < @ngaybatdau and ngayketthuc > @ngayketthuc)
			)
	return
end

--- VER 2.1 -- GIANG
go
	-- Trigger nếu cập nhật lớp mà cập nhật mã phòng thì xoá lịch học
alter trigger trg_updateLH_PhongHoc_dellLIH
on LOPHOC
for update
as
begin
	declare @id_PH_old int
	set @id_PH_old = (select id_PH from deleted)
	
	declare @id_PH_new int
	set @id_PH_new = (select id_PH from inserted)
	
	declare @id_LH int
	set @id_LH = (select id_LH from deleted)
	
	if (@id_PH_new != @id_PH_old)
		delete from LICHHOC where id_LH = @id_LH
end

