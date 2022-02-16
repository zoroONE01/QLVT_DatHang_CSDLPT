USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_CapNhat_NhanVien]    Script Date: 15/02/2022 12:23:10 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_CapNhat_NhanVien] @MaNV int , @Ho nvarchar(40) ,@Ten nvarchar(10), @DiaChi nvarchar(100),@NgaySinh datetime,@Luong float, @MaCN nchar(10),@TrangThai int
as
begin	
		update NhanVien set ho=@Ho,ten=@Ten,diachi=@DiaChi,ngaysinh=@NgaySinh,luong=@Luong,macn=@MaCN,TrangThaiXoa=@TrangThai where MANV=@MaNV
		return @MANV;	
end
GO

