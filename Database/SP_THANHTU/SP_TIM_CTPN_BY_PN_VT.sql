USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_TIM_CTPN_BY_PN_VT]    Script Date: 17/02/22 5:09:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER  PROCEDURE [dbo].[SP_TIM_CTPN_BY_PN_VT]
@MaPN nchar(8), @MAVT nchar(4) 
AS 
BEGIN 
	SELECT MAPN,MAVT FROM CTPN WHERE MAPN=@MaPN and MAVT=@MAVT
end

