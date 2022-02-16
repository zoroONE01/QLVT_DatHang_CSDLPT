USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_CHECK_TRACUU]    Script Date: 15/02/2022 12:23:42 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_CHECK_TRACUU] @ID nvarchar(4),@Type NVARCHAR(15)
AS 
BEGIN	
declare @rs int
		set @rs=0;
	--Nhân viên
			IF(@Type ='NhanVien')
			begin 
			if exists(select nv.MANV from LINK2.QLVT.dbo.NhanVien nv where cast(nv.MANV as varchar)=@ID) 
				set @rs=1;
				
			end;
	--Kho 
	IF(@TYPE='Kho')
	begin 
	if exists(select MAKHO from link2.qlvt.dbo.kho k where k.MAKHO=@id)
		set @rs=1
	end;
	if(@type ='TenKho')
	begin 
		if exists ( select TenKho from link2.qlvt.dbo.kho k where k.TENKHO=@id)
		set @rs=1
	end;
select @rs
end
GO

