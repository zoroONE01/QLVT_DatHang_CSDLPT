USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_Tim_CTDonDatHang]    Script Date: 15/02/2022 12:38:41 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_Tim_CTDonDatHang]
@MaDHH nchar(8),@MaVT nchar(4)
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;
DECLARE @isExistCTDHH int = 0;
begin
	
		
		select MasoDDH,MAVT,SOLUONG,DONGIA from CTDDH where MasoDDH=@MaDHH and MAVT=@MaVT
			
end
GO

