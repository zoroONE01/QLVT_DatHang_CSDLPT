USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_Tim_DHH]    Script Date: 17/02/22 5:09:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_Tim_DHH] @MaDHH nchar(8)
AS
BEGIN
	select MasoDDH,NGAY,NhaCC,nv.MANV,nv.HO,nv.TEN,k.MAKHO,k.TENKHO from DatHang dh join NhanVien nv on dh.MasoDDH=@MaDHH and nv.MANV=dh.MANV join Kho k on k.MAKHO=dh.MAKHO
END
