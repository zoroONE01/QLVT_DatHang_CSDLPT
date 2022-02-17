USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_RP_HDNV2]    Script Date: 15/02/2022 12:36:32 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[SP_RP_HDNV2] 
@MaNV int, @dateFrom date, @dateTo date
as
begin 
DECLARE @thangFrom int, @thangTo int,@namFrom int, @namTo int
set @thangFrom=MONTH(@dateFrom)
set @thangTo=MONTH(@dateTo)
set @namFrom=YEAR(@dateFrom)
set @namTo=Year(@dateTo)

select FORMAT(pn.NGAY,'MMMM yyyy')as yearMonth,pn.NGAY, pn.MAPN,N'Không' as HoTenKH,
vt.TENVT,
k.TENKHO,
ctpn.SOLUONG,ctpn.DONGIA,ThanhTien=ctpn.SOLUONG*ctpn.DONGIA 
from
 (select p.MAPN,p.NGAY,p.MAKHO from PhieuNhap p where p.MANV=@MaNV) pn  
join CTPN ctpn on ctpn.MAPN=pn.MAPN
join Vattu vt on vt.MAVT=ctpn.MAVT
join Kho k on k.MAKHO=pn.MAKHO
where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo))
order by pn.NGAY asc
end
GO

