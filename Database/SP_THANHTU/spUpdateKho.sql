USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spUpdateKho]    Script Date: 17/02/22 5:03:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spUpdateKho] @maKho nchar(4), @tenKho NVARCHAR(30), @diaChi nvarchar(100)
AS
BEGIN
	UPDATE dbo.Kho
	SET TENKHO = @tenKho, DIACHI = @diaChi
	WHERE MAKHO = @maKho;
END