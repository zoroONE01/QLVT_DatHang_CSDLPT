USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spShowKho]    Script Date: 17/02/22 5:03:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spShowKho]
as
begin
if  exists(select MACN, ChiNhanh from  dbo.ChiNhanh)
 BEGIN
   	SELECT	k.MAKHO, k.TENKHO, k.DIACHI, k.MACN, cn.ChiNhanh
   	FROM dbo.kho as k join dbo.ChiNhanh as cn
	on k.MACN = cn.MACN
	order by k.rowguid DESC
 END
 ELSE
  IF  exists(select MACN, ChiNhanh from  LINK0.QLVT.dbo.ChiNhanh)
  BEGIN
    SELECT	k.MAKHO, k.TENKHO, k.DIACHI, k.MACN, cn.ChiNhanh
   	FROM dbo.kho as k join dbo.ChiNhanh as cn
	on k.MACN = cn.MACN
	order by k.rowguid DESC
  END
  ELSE  -- không có nv
     raiserror ( 'Khong tim thay kho', 16, 1)
end;