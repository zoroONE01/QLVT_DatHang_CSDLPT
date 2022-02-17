USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_Xoa_CTDDH]    Script Date: 17/02/22 5:08:51 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_Xoa_CTDDH]
@MaDHH nchar(8)
as
--DECLARE @isExistVT int = 0; --không tồn tại
--DECLARE @isExistDDH int = 0;
DECLARE @isExistCTDHH int = 0;
begin	
		--kiểm tra ràng buộc khoá gọi
			--nếu tồn tại phiếu nhập thì không xoá
		--if exists (select ddh.MasoDDH from DatHang ddh join PhieuNhap pn on ddh.MasoDDH=pn.MasoDDH and ddh.MasoDDH=@MaDHH)
		--	return 0;
		--	--nếu tồn tại ctddh
		--if  exists (select ddh.MasoDDH from DatHang ddh join CTDDH ctddh on ddh.MasoDDH=ctddh.MasoDDH and ddh.MasoDDH=@MaDHH) 
		--	return 0;
		delete from CTDDH WHERE MasoDDH=@MaDHH
end