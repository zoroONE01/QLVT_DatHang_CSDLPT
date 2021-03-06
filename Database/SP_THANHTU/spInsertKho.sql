USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spInsertKho]    Script Date: 17/02/22 5:04:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spInsertKho] @maKho nchar(4), @tenKho NVARCHAR(30), @diaChi nvarchar(100), @maCN nchar(10)
AS
BEGIN
	insert into dbo.Kho (MAKHO, TENKHO, DIACHI, MACN)
	values (@maKho, @tenKho, @diaChi, @maCN)
END