USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_CAPNHAT_PN]    Script Date: 15/02/2022 12:23:20 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_CAPNHAT_PN] @MAPN nchar(8),@NGAY date,@MasoDDH nchar(8),@MANV int,@MAKHO nchar(4)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	
	update PhieuNhap set NGAY=@NGAY,MAKHO=@MAKHO,MANV=@MANV WHERE MAPN=@MAPN
    -- Insert statements for procedure here
	
END
GO

