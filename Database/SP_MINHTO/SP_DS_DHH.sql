USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DS_DHH]    Script Date: 15/02/2022 12:29:30 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_DS_DHH] as
begin 
		--select MasoDDH,NGAY,NhaCC,MANV,MAKHO from DatHang
		select MasoDDH,NGAY,NhaCC,nv.MANV,nv.HO,nv.TEN,k.MAKHO,k.TENKHO from DatHang dh join NhanVien nv on nv.MANV=dh.MANV join Kho k on k.MAKHO=dh.MAKHO
end
GO

