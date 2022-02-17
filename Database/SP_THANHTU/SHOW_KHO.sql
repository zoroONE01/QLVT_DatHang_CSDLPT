USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[spShowKho]    Script Date: 15/02/2022 12:47:26 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[spShowKho]
as
begin
if  exists(select MACN, ChiNhanh from  dbo.ChiNhanh as CNinfo)
 BEGIN
   	SELECT	k.MAKHO, k.TENKHO, k.DIACHI, k.MACN, cn.ChiNhanh
   	FROM dbo.kho as k join dbo.ChiNhanh as cn
	on k.MACN = cn.MACN
	order by k.rowguid DESC
 END
 ELSE
  IF  exists(select MACN, ChiNhanh from  LINK0.QLVT.dbo.ChiNhanh as CNinfo)
  BEGIN
    SELECT	k.MAKHO, k.TENKHO, k.DIACHI, k.MACN, cn.ChiNhanh
   	FROM dbo.kho as k join CNinfo as cn
	on k.MACN = cn.MACN
	order by k.rowguid DESC
  END
  ELSE  -- không có nv
     raiserror ( 'Khong tim thay kho', 16, 1)
end;
GO

