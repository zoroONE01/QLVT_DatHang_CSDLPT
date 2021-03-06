USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_Tao_CTDonDatHang]    Script Date: 13/09/2021 2:42:48 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_Tao_CTDonDatHang]
@MaDHH nchar(8),@MaVT nchar(4),@SoLuong int,@DonGia float
as
DECLARE @isExistVT int = 0; --không tồn tại
DECLARE @isExistDDH int = 0;

begin
			--KIỂM TRA TỒN TẠI
			exec @isExistVT=Check_ID @MaVT,'VatTu'
			if(@isExistVT=0) return 0
			exec @isExistDDH=Check_ID @MaDHH,'DatHang'
			if(@isExistDDH=0) return 0;
			insert into CTDDH (MasoDDH,MAVT,SOLUONG,DONGIA) values(@MaDHH,@MaVT,@SoLuong,@DonGia) 
			return 1;
end

declare @rs int;
exec @rs=SP_Tao_CTDonDatHang 'MDDH036','MU01',10,10000
print @rs

declare @isExist int;
exec @isExist=Check_ID 'MDDH03','DatHang'
print @isExist