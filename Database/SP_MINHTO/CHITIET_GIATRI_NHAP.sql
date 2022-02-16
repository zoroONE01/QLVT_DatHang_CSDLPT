USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[CHITIET_GIATRI_NHAP]    Script Date: 15/02/2022 12:20:54 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[CHITIET_GIATRI_NHAP]
@Nhom nchar(20),@loai nchar(10), @ThangFrom int ,@ThangTo int , @NamFrom int, @NamTo int
	--declare @ThangFrom int=1 ,@ThangTo int=12 , @NamFrom int=1990, @NamTo int=2022
AS 
BEGIN
	
	if(@Nhom='CongTy')
	begin 
		if(@loai='NHAP') 
		begin
			select pn.NGAY,vt.TENVT,SUM(ctpn.SOLUONG) as soluong,SUM(ctpn.SOLUONG*ctpn.DONGIA) tongtrigia
			FROM
			(	
				(select MAPN, NGAY from PhieuNhap where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))
					union
				(select MAPN, NGAY from LINK1.QLVT.DBO.PhieuNhap pn where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))
			) as pn
			join 
			(
				(select*  from CTPN)
				union (select * from LINK1.QLVT.DBO.CTPN)
			) ctpn 
			on ctpn.MAPN=pn.MAPN
			join (select MAVT,TENVT from Vattu) vt on ctpn.MAVT=vt.MAVT
			group by vt.TENVT,pn.NGAY
			order by pn.NGAY asc
		end
		else if(@loai='XUAT')
		begin
			select px.NGAY,vt.TENVT,SUM(ctpx.SOLUONG) as soluong,SUM(ctpx.SOLUONG*ctpx.DONGIA) tongtrigia
			FROM
			(	
				(select MAPX, NGAY from PhieuXuat where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))
					union
				(select MAPX, NGAY from LINK1.QLVT.DBO.PhieuXuat pn where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))
			) as px
			join 
			(
				(select*  from CTPX)
				union (select * from LINK1.QLVT.DBO.CTPX)
			) ctpx 
			on ctpx.MAPX=px.MAPX
			join (select MAVT,TENVT from Vattu) vt on ctpx.MAVT=vt.MAVT
			group by vt.TENVT,px.NGAY
			order by px.NGAY asc
		end
	end
	else if(@Nhom='ChiNhanh')
	begin
	if(@loai='Nhap') 
		begin
			select pn.NGAY,vt.TENVT,SUM(ctpn.SOLUONG) as soluong,SUM(ctpn.SOLUONG*ctpn.DONGIA) tongtrigia
			FROM
			(	
				(select MAPN, NGAY from PhieuNhap where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))
				
			) as pn
			join 
			(
				(select*  from CTPN)
				
			) ctpn 
			on ctpn.MAPN=pn.MAPN
			join (select MAVT,TENVT from Vattu) vt on ctpn.MAVT=vt.MAVT
			group by vt.TENVT,pn.NGAY
			order by pn.NGAY asc
		end
		else if(@loai='XUAT')
		begin
			select px.NGAY,vt.TENVT,SUM(ctpx.SOLUONG) as soluong,SUM(ctpx.SOLUONG*ctpx.DONGIA) tongtrigia
			FROM
			(	
				(select MAPX, NGAY from PhieuXuat where ((Year(NGAY)=@NamFrom and MONTH(NGAY) >=@ThangFrom) or Year(NGAY)>@NamFrom )
										and  ((YEAR(NGAY) < @NamTo) or (YEAR(NGAY)=@NamTo  and  MONTH(NGAY)<=@ThangTo)))
			
			) as px
			join 
			(
				(select*  from CTPX)
			
			) ctpx 
			on ctpx.MAPX=px.MAPX
			join (select MAVT,TENVT from Vattu) vt on ctpx.MAVT=vt.MAVT
			group by vt.TENVT,px.NGAY
			order by px.NGAY asc
		end
	end
		
END
GO

