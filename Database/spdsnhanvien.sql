USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_DSNhanVien]    Script Date: 13/09/2021 6:05:30 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[SP_DSNhanVien]
as 
BEGIN
	select MANV, HO, TEN , NGAYSINH, DIACHI, LUONG, MACN from NhanVien nv where nv.TrangThaiXoa=0
end