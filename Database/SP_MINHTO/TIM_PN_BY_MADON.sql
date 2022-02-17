USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_Tim_PN_BY_MADON]    Script Date: 15/02/2022 12:39:53 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_Tim_PN_BY_MADON]
	-- Add the parameters for the stored procedure here
@MADON nchar(8)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	select MAPN,NGAY,MasoDDH,MANV,MAKHO from PhieuNhap where MasoDDH=@MADON
END
GO

