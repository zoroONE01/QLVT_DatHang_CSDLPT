USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spShowDSVatTuDatHang]    Script Date: 17/02/22 5:02:12 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spShowDSVatTuDatHang] @maDDH nchar(8)
as
begin
   	SELECT	item.MasoDDH, item.MAVT, item.SOLUONG, item.DONGIA
   	FROM dbo.CTDDH as item
	where item.MasoDDH = @maDDH
	order by item.rowguid DESC
end;