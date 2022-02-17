USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[spDeleteKho]    Script Date: 15/02/2022 12:46:53 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[spDeleteKho] @maKho nchar(4)
AS
BEGIN
	Delete from dbo.Kho
	WHERE MAKHO = @maKho;
END
GO

