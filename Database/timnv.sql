USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[sp_TimNV]    Script Date: 13/09/2021 6:07:22 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[sp_TimNV]
  @X INT
AS
 DECLARE @MACN VARCHAR(10), @HO nvarchar(50), @TEN nvarchar(10)
 IF  exists(select MANV from  dbo.NHANVIEN  where MANV =@X)
 BEGIN
   	SELECT TENCN= (SELECT CHINHANH FROM dbo.CHINHANH),  HO, TEN 
   	FROM NHANVIEN WHERE MANV=@X
 END
 ELSE
  IF  exists(select MANV from  LINK0.QLVT.dbo.NHANVIEN  where MANV =@X)
  BEGIN
    SELECT  @MACN= MACN,  @HO=HO, @TEN=TEN 
   	    FROM LINK0.QLVT.dbo.NHANVIEN WHERE MANV=@X
   	SELECT  TENCN=CHINHANH,  HO=@HO, TEN =@TEN
   	 FROM LINK0.QLVT.dbo.CHINHANH  WHERE MACN=@MACN
   	                  
  END
  ELSE  -- không có nv
     raiserror ( 'Ma nhan vien ban tim khong co', 16, 1)

