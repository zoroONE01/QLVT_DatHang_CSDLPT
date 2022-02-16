USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_TAO_CTPN]    Script Date: 15/02/2022 12:36:43 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[SP_TAO_CTPN]
@MAPN nchar(8), @MAVT nchar(4) ,@SOLUONG INT , @DONGGIA FLOAT 
AS 
BEGIN 
	DECLARE @SOLUONGTON INT 
	SET @SOLUONGTON= (select ctddh.SOLUONG from CTDDH ctddh JOIN PhieuNhap pn on pn.MasoDDH=ctddh.MasoDDH 
	and pn.MAPN=@MAPN and ctddh.MAVT=@MAVT)
	IF(@SOLUONGTON<@SOLUONG)
		RAISERROR('số lượng tồn không đủ',16,1,@SOLUONGTON)	
	if exists (select MAPN,MAVT FROM CTPN WHERE MAPN=@MAPN AND MAVT=@MAVT)
			RAISERROR('Chi tiet phieu nhap ton tai',16,1)
   insert into CTPN (MAPN,MAVT,SOLUONG,DONGIA) VALUES(@MAPN,@MAVT,@SOLUONG,@DONGGIA)
end
GO

