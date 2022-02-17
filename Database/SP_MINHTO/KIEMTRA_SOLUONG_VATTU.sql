USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_KIEMTRA_SOLUONG_VATTU]    Script Date: 15/02/2022 12:34:23 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_KIEMTRA_SOLUONG_VATTU] @MAVT nchar(4),@rs int out 
AS 
BEGIN 
	DECLARE @soLuongSite1 int,@soLuongSite2 int;
		set @soLuongSite1=(SELECT SOLUONGTON FROM Vattu WHERE MAVT= @MAVT)
		set @soLuongSite2=(SELECT SOLUONGTON FROM LINK1.QLVT.dbo.Vattu WHERE MAVT= @MAVT)
		if(@soLuongSite1!=@soLuongSite2) 
			begin 
			raiserror('Dữ liệu đang được đồng bộ, vui lòng chờ',16,1, @MAVT) set @rs=0;
			end
		else 
			begin 
			set @rs=1
			end
		return @rs; --- du lieu da dc dong bo
end	
GO

