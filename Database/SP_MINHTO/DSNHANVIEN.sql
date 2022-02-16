USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DSNhanVien]    Script Date: 15/02/2022 12:29:40 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[SP_DSNhanVien]
as 
BEGIN
	select MANV, HO, TEN , NGAYSINH, DIACHI, LUONG, MACN from NhanVien nv where nv.TrangThaiXoa=0
end
GO

