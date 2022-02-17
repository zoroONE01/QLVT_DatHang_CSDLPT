USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_Tao_CTPhieuXuat]    Script Date: 17/02/22 5:10:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_Tao_CTPhieuXuat]
@maPhieuXuat nchar(8),@MaVT nchar(4),@SoLuong int,@DonGia float
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;

begin
			--KIỂM TRA TỒN TẠI
			--exec @isExistVT=Check_ID @MaVT,'VatTu'
			--if(@isExistVT=0) return 0
			--exec @isExistDDH=Check_ID @MaDHH,'DatHang'
			--if(@isExistDDH=0) return 0;
			insert into CTPX(MAPX,MAVT,SOLUONG,DONGIA) values(@maPhieuXuat,@MaVT,@SoLuong,@DonGia) 
			--return 1;
end