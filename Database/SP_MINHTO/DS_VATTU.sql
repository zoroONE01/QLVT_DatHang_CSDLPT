USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DSVT]    Script Date: 15/02/2022 12:29:58 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PRocedure [dbo].[SP_DSVT]
as 
begin 
select MAVT, TENVT, DVT, SOLUONGTON from Vattu order by TENVT
END


GO

