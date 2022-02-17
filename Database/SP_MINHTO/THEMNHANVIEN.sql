USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_Them_NhanVien]    Script Date: 15/02/2022 12:37:29 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_Them_NhanVien] @MaNV int , @Ho nvarchar(40) ,@Ten nvarchar(10), @DiaChi nvarchar(100),@NgaySinh datetime,@Luong float, @MaCN nchar(10)
as
declare @result int
begin
	--exec @result=SP_CHECK_TRACUU @MaNV,'NhanVien'
	--if(@result=0)
	--	begin
		insert into NhanVien  (MaNV ,ho,ten,diachi,ngaysinh,luong,macn) values(@MaNV,@Ho,@Ten,@DiaChi,@NgaySinh,@Luong,@MaCN)
		
	--	end
	--else 
		--select 0
end
GO

