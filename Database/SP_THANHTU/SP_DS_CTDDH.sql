USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_DS_CTDDH]    Script Date: 17/02/22 5:11:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_DS_CTDDH]
	-- Add the parameters for the stored procedure here
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT MasoDDH,MAVT,SOLUONG,DONGIA from CTDDH
END

