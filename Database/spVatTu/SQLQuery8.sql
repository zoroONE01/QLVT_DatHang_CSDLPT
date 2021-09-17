USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spUpdateVatTu]    Script Date: 09/18/21 01:02:20 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spUpdateVatTu] @maVT nchar(4), @tenVT NVARCHAR(30), @DVT nvarchar(15)
AS
BEGIN
	UPDATE dbo.Vattu
	SET TENVT = @tenVT, DVT = @DVT
	WHERE MAVT = @maVT;
END