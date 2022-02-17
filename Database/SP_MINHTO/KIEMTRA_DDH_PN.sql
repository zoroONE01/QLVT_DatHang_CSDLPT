USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[KIEMTRA_DDH_PN]    Script Date: 15/02/2022 12:21:16 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[KIEMTRA_DDH_PN]
	-- Add the parameters for the stored procedure here
	@ddh nchar(8)
AS
BEGIN
		-- kiểm tra đơn đặt hàng có phiếu nhập chưa
		select MAPN from PhieuNhap pn where pn.MasoDDH=@ddh;

END
GO

