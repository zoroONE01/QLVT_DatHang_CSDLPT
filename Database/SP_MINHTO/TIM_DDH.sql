USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_Tim_DHH]    Script Date: 15/02/2022 12:39:27 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_Tim_DHH] @MaDHH nchar(8)
AS
BEGIN
	select MasoDDH,NGAY,NhaCC,nv.MANV,nv.HO,nv.TEN,k.MAKHO,k.TENKHO from DatHang dh join NhanVien nv on dh.MasoDDH=@MaDHH and nv.MANV=dh.MANV join Kho k on k.MAKHO=dh.MAKHO
END
GO

