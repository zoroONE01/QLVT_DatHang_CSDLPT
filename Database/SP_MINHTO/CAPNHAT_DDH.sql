USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_CapNhat_DDH]    Script Date: 15/02/2022 12:23:01 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_CapNhat_DDH]
-- Add the parameters for the stored procedure here
	@MaDHH nchar(8),@NhaCC nvarchar(100),@MaKho nchar(4)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
    -- Insert statements for procedure here
	update DatHang set NhaCC=@NhaCC, MAKHO=@MaKho where MasoDDH=@MaDHH
END

GO

