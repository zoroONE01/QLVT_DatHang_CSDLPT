USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_DSVT]    Script Date: 17/02/22 5:10:50 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PRocedure [dbo].[SP_DSVT]
as 
begin 
select MAVT, TENVT, DVT, SOLUONGTON from Vattu order by TENVT
END


