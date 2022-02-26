







































































USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DS_CTDDH_PN_BY_MADON]    Script Date: 16/02/2022 11:38:22 CH ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[SP_DS_CTDDH_PN_BY_MADON] @maDDH  nchar(8)
as
begin
	select * 
from
(   	
		select ctddh.MasoDDH, ctddh.MAVT,vt.TENVT, ctddh.SOLUONG, ctddh.DONGIA
		from
			(
			SELECT	item.MasoDDH, item.MAVT, item.SOLUONG, item.DONGIA
   			FROM dbo.CTDDH as item
			where item.MasoDDH=@maDDH
			) ctddh 
		inner join Vattu vt
		on vt.MAVT=ctddh.MAVT
	
) chiTietDatHang 
where chiTietDatHang.MAVT
not in(select ctpn.MAVT
			from(
				select pn.MAPN,pn.MasoDDH
				from PhieuNhap pn
				where pn.MasoDDH=@maDDH
			) as phieuNhap
			join CTPN as ctpn
			ON ctpn.MAPN=phieuNhap.MAPN
			)
end;
GO

