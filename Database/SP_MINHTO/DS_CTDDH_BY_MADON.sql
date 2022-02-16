USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DS_CTDDH_BY_MADON]    Script Date: 15/02/2022 12:25:43 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[SP_DS_CTDDH_BY_MADON] @maDDH nchar(8)
as
begin
   	SELECT	item.MasoDDH, item.MAVT,vt.TENVT, item.SOLUONG, item.DONGIA
   	FROM dbo.CTDDH as item
	inner join Vattu vt
	on  item.MasoDDH = @maDDH and vt.MAVT=item.MAVT
	order by item.rowguid DESC
end;
GO

