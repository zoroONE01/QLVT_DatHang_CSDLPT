USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[spShowDSVatTuDatHang]    Script Date: 15/02/2022 12:47:17 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[spShowDSVatTuDatHang] @maDDH nchar(8)
as
begin
   	SELECT	item.MasoDDH, item.MAVT, item.SOLUONG, item.DONGIA
   	FROM dbo.CTDDH as item
	where item.MasoDDH = @maDDH
	order by item.rowguid DESC
end;
GO

