USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_Xoa_CTDonDatHang]    Script Date: 17/02/22 5:08:45 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_Xoa_CTDonDatHang]
@MaDHH nchar(8),@MaVT nchar(4),@SoLuong int,@DonGia float
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;
DECLARE @isExistCTDHH int = 0;
begin	

		--kiểm tra ràng buộc khoá gọi
			--nếu tồn tại phiếu nhập thì không xoá
		if exists (select* from CTDDH ctddh join PhieuNhap pn on ctddh.MasoDDH=pn.MasoDDH and ctddh.MasoDDH=@MaDHH)
		delete CTDDH  WHERE MasoDDH=@MaDHH and MAVT=@MaVT
end
