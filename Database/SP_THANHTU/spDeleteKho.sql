USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spDeleteKho]    Script Date: 17/02/22 5:07:50 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spDeleteKho] @maKho nchar(4)
AS
BEGIN
	Delete from dbo.Kho
	WHERE MAKHO = @maKho;
END