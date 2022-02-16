USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[spInsertKho]    Script Date: 15/02/2022 12:47:03 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[spInsertKho] @maKho nchar(4), @tenKho NVARCHAR(30), @diaChi nvarchar(100), @maCN nchar(10)
AS
BEGIN
	insert into dbo.Kho (MAKHO, TENKHO, DIACHI, MACN)
	values (@maKho, @tenKho, @diaChi, @maCN)
END
GO

