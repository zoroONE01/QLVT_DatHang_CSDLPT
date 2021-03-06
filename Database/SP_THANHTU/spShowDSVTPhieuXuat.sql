USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spShowDSVTPhieuXuat]    Script Date: 17/02/22 5:01:10 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spShowDSVTPhieuXuat] @maPhieuXuat nchar(8)
as
begin
   	SELECT	item.MAPX, item.MAVT, item.SOLUONG, item.DONGIA
   	FROM dbo.CTPX as item
	where item.MAPX = @maPhieuXuat
	order by item.rowguid DESC
end;