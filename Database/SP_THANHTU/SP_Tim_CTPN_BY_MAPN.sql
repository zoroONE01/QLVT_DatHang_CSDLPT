USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_Tim_CTPN_BY_MAPN]    Script Date: 17/02/22 5:09:44 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[SP_Tim_CTPN_BY_MAPN]
	-- Add the parameters for the stored procedure here
@MAPN nchar(8)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	select MAPN,ctpn.MAVT, vt.TENVT, SOLUONG,DONGIA  from CTPN ctpn
		join (select TENVT,MAVT from Vattu) vt on  
	 MAPN=@MAPN and vt.MAVT=ctpn.MAVT
END
