USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spDeleteVatTu]    Script Date: 09/18/21 01:02:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spDeleteVatTu] @maVT nchar(4)
AS
BEGIN
	Delete from dbo.Vattu
	WHERE MAVT = @maVT;
END