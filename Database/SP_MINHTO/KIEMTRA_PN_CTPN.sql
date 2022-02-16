USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[KIEMTRA_PN_CTPN]    Script Date: 15/02/2022 12:21:29 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[KIEMTRA_PN_CTPN]
	-- Add the parameters for the stored procedure here
	@PN nchar(8)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
		
		select MAPN FROM CTPN where MAPN=@PN 
    
END
GO

