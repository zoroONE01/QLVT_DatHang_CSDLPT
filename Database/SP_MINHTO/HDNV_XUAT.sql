USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_RP_HDNV_XUAT]    Script Date: 15/02/2022 12:36:23 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER procedure [dbo].[SP_RP_HDNV_XUAT] 
@MaNV int, @dateFrom date, @dateTo date
as
begin 
DECLARE @thangFrom int, @thangTo int,@namFrom int, @namTo int
set @thangFrom=MONTH(@dateFrom)
set @thangTo=MONTH(@dateTo)
set @namFrom=YEAR(@dateFrom)
set @namTo=Year(@dateTo)

select FORMAT(px.NGAY,'MMMM yyyy')as yearMonth,px.NGAY, px.MAPX,px.HOTENKH as HoTenKH,
vt.TENVT,
k.TENKHO,
ctpx.SOLUONG,ctpx.DONGIA,ThanhTien=ctpx.SOLUONG*ctpx.DONGIA 
from
 (select p.MAPX,p.NGAY,p.MAKHO,p.HOTENKH from PhieuXuat p where p.MANV=@MaNV) px
join CTPX ctpx on ctpx.MAPX=px.MAPX
join Vattu vt on vt.MAVT=ctpx.MAVT
join Kho k on k.MAKHO=px.MAKHO
where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo))
order by yearMonth asc
end
GO

