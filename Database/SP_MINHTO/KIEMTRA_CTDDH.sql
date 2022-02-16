USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_KiemTra_CTDHH]    Script Date: 15/02/2022 12:30:08 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_KiemTra_CTDHH]
-- Add the parameters for the stored procedure here
	@MaDHH nchar(8),@MaVT nchar(4)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
    -- Insert statements for procedure here
	if exists(select MasoDDH from QLVT.DBO.CTDDH where MasoDDH=@MaDHH and MAVT=@MaVT)
	return 1;
	else return 0

END
GO

