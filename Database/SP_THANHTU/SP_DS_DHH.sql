USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_DS_DHH]    Script Date: 17/02/22 5:11:03 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_DS_DHH] as
begin 
		--select MasoDDH,NGAY,NhaCC,MANV,MAKHO from DatHang
		select dh.MasoDDH,dh.NGAY,NhaCC,nv.MANV,nv.HO,nv.TEN,k.MAKHO,k.TENKHO,pn.MAPN from DatHang dh left join PhieuNhap pn on dh.MasoDDH = pn.MasoDDH join NhanVien nv on nv.MANV=dh.MANV join Kho k on k.MAKHO=dh.MAKHO 
end