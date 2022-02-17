USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spInsertDDH]    Script Date: 17/02/22 5:04:46 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[spInsertDDH]
@MaDHH nchar(8), @ngay date, @NCC nvarchar(100), @MaNV int, @MaKho nchar(4)
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;

begin
			--KIỂM TRA TỒN TẠI
			--exec @isExistVT=Check_ID @MaVT,'VatTu'
			--if(@isExistVT=0) return 0
			--exec @isExistDDH=Check_ID @MaDHH,'DatHang'
			--if(@isExistDDH=0) return 0;
			insert into DatHang (MasoDDH, NGAY, NhaCC, MANV, MAKHO) 
			values (@MaDHH, @ngay, @NCC, @MaNV, @MaKho)
			--return 1;
end