USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_CapNhat_CTDonDatHang]    Script Date: 15/02/2022 12:21:55 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_CapNhat_CTDonDatHang]
@MaDHH nchar(8),@MaVT nchar(4),@SoLuong int,@DonGia float
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;
DECLARE @isExistCTDHH int = 0;
begin
		update CTDDH set SOLUONG=@SoLuong,DONGIA=@DonGia WHERE MasoDDH=@MaDHH
end
GO

