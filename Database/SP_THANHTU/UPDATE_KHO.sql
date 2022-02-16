USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[spUpdateKho]    Script Date: 15/02/2022 12:47:34 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[spUpdateKho] @maKho nchar(4), @tenKho NVARCHAR(30), @diaChi nvarchar(100)
AS
BEGIN
	UPDATE dbo.Kho
	SET TENKHO = @tenKho, DIACHI = @diaChi
	WHERE MAKHO = @maKho;
END
GO

