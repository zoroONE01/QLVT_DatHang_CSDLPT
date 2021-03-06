USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spCheckDelKho]    Script Date: 17/02/22 5:08:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spCheckDelKho]
@Code NVARCHAR(15)
AS
BEGIN
Declare @str char(3);
	select CONCAT(@str, '');
		IF EXISTS(SELECT * FROM dbo.DatHang WHERE MAKHO = @Code)
			begin
			select CONCAT(@str, '1');
			end-- Mã kho tồn tại trong bảng đặt hàng
		else select CONCAT(@str, '0');
		IF EXISTS(SELECT * FROM dbo.PhieuNhap WHERE MAKHO = @Code)
			begin
			select CONCAT(@str, '1');
			end-- Mã kho tồn tại trong bảng phiếu nhập
		else select CONCAT(@str, '0');
		IF EXISTS(SELECT * FROM dbo.PhieuXuat WHERE MAKHO = @Code)
			begin
			select CONCAT(@str, '1');
			end-- Mã kho tồn tại trong bảng phiếu xuất
		else select CONCAT(@str, '0');
		select @str;
		return;
	END