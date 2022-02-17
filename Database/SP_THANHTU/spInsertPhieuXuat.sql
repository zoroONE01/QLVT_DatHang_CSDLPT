USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spInsertPhieuXuat]    Script Date: 17/02/22 5:04:30 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[spInsertPhieuXuat]
@maPhieuXuat nchar(8), @ngay date, @khachHang nvarchar(100), @MaNV int, @MaKho nchar(4)
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;

begin
			--KIỂM TRA TỒN TẠI
			--exec @isExistVT=Check_ID @MaVT,'VatTu'
			--if(@isExistVT=0) return 0
			--exec @isExistDDH=Check_ID @MaDHH,'DatHang'
			--if(@isExistDDH=0) return 0;
			insert into PhieuXuat (MAPX, NGAY, HOTENKH, MANV, MAKHO) 
			values (@maPhieuXuat, @ngay, @khachHang, @MaNV, @MaKho)
			--return 1;
end