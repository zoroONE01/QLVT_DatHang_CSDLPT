USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spFindSoLuongTonKhoVT]    Script Date: 17/02/22 5:07:17 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[spFindSoLuongTonKhoVT]
-- Add the parameters for the stored procedure here
	@MAVT nchar(4), @MAKHO nchar(4)
AS
BEGIN
--if exists (select px.MAKHO, sum(px_d.SOLUONG) as SoLuongXuat from
--PhieuXuat px join (select MAPX, SOLUONG from CTPX where CTPX.MAVT = @MAVT) px_d
--on px.MAPX = px_d.MAPX
--group by px.MAKHO)
--	begin
		select isnull(vt_n.SoLuongNhap - vt_x.SoLuongXuat, vt_n.SoLuongNhap) as SoLuongTon 
		from 
			(select pn.MAKHO, sum(pn_d.SOLUONG) as SoLuongNhap from
			PhieuNhap pn join (select MAPN, SOLUONG from CTPN where CTPN.MAVT = @MAVT) pn_d
			on pn.MAPN = pn_d.MAPN
			where pn.MAKHO = @MAKHO
			group by pn.MAKHO) as vt_n
		left join
			(select px.MAKHO, sum(px_d.SOLUONG) as SoLuongXuat from
			PhieuXuat px 
			join (select MAPX, SOLUONG from CTPX where CTPX.MAVT = @MAVT) px_d
			on px.MAPX = px_d.MAPX
			where px.MAKHO = @MAKHO
			group by px.MAKHO) as vt_x
			on vt_n.MAKHO = vt_x.MAKHO
			
--	end
--else
--	begin
--		select sum(pn_d.SOLUONG) as SoLuongNhap from
--		PhieuNhap pn 
--		join 
--			(select MAPN, SOLUONG from CTPN where CTPN.MAVT = @MAVT) pn_d
--		on pn.MAPN = pn_d.MAPN
--		where pn.MAKHO = @MAKHO
--		group by pn.MAKHO
--	end
END