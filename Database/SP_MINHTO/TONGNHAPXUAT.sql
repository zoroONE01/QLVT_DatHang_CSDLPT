USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_REPORT_TONG_NHAP_XUAT]    Script Date: 15/02/2022 12:36:15 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--select  CAST(987654321 AS money)
--SELECT CONVERT(varchar, CAST(987654321 AS money), 1)
--SELECT FORMAT(3231,'#,###,##0.0')
--SELECT REPLACE(CONVERT(varchar(20), (CAST(1312313 AS money)), 1), '.00', '')
--FORMAT((SOLUONG*DONGIA),'#,###,##0.0') 
CREATE PROCEDURE [dbo].[SP_REPORT_TONG_NHAP_XUAT]  @DateFrom date, @DateTo date
as
begin 
DECLARE @thangFrom int, @thangTo int,@namFrom int, @namTo int
set @thangFrom=MONTH(@dateFrom)
set @thangTo=MONTH(@dateTo)
set @namFrom=YEAR(@dateFrom)
set @namTo=Year(@dateTo)

declare @tongNhap float,@tongXuat float
--@DateFrom date, @DateTo date
--set @DateFrom='2015-05-21'
--set @DateTo='2022-05-30'

set @tongNhap=(
	select sum(SOLUONG*DONGIA)
	from PhieuNhap pn
	join CTPN ctpn on ctpn.MAPN=pn.MAPN 
	where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))

	select pn.NGAY as ngay,@tongNhap as TONGNHAP, SUM(SOLUONG*DONGIA) AS NHAP,SUM(SOLUONG*DONGIA)/@tongNhap AS tile into #TONGNHAPTEMP
	from PhieuNhap pn
	join CTPN ctpn on ctpn.MAPN=pn.MAPN 
	where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo))
	group by pn.NGAY

set @tongXuat=(
	select sum(SOLUONG*DONGIA)
	from PhieuXuat px
	join CTPX ctpx on ctpx.MAPX=px.MAPX
	where NGAY>@DateFrom and NGAY<@DateTo)


	select px.NGAY as ngay,@tongXuat as TONGXUAT,SUM(SOLUONG*DONGIA) AS XUAT,SUM(SOLUONG*DONGIA)/@tongXuat AS tile into #TONGXUATTEMP
	from PhieuXuat px
	join CTPX ctpx on ctpx.MAPX=px.MAPX 
	where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo))
	group by px.NGAY

	select
		ISNULL(tongNhap.ngay,tongXuat.ngay) NGAY,
		ISNULL(tongNhap.NHAP,0) NHAP,
		ISNULL(tongNhap.tile,0) TILENHAP,
		ISNULL(tongXuat.XUAT,0) XUAT,
		ISNULL(tongXuat.tile,0) TILEXUAT,
		ISNULL(@tongNhap,0) TONGNHAP,ISNULL(@tongXuat,0) TONGXUAT
	from #TONGNHAPTEMP tongNhap
	full join #TONGXUATTEMP tongXuat
	on tongNhap.ngay =tongXuat.ngay
	order by  NGAY
end
GO

