USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DS_CTDDH]    Script Date: 15/02/2022 12:25:24 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_DS_CTDDH]
	-- Add the parameters for the stored procedure here
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT MasoDDH,MAVT,SOLUONG,DONGIA from CTDDH
END

GO

