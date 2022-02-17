USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spInsertVatTu]    Script Date: 17/02/22 5:04:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spInsertVatTu] @maVT nchar(4), @tenVT NVARCHAR(30), @DVT nvarchar(15)
AS
BEGIN
	insert into dbo.Vattu(MAVT, TENVT,DVT)
	values (@maVT, @tenVT, @DVT)
END