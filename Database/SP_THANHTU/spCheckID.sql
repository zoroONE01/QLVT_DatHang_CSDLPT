USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[spCheckID]    Script Date: 17/02/22 5:08:01 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[spCheckID]
@Code NVARCHAR(15), @Type NVARCHAR(30)
AS
BEGIN
	-- Nhân viên
	IF(@Type = 'MANV')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.NhanVien WHERE dbo.NhanVien.MANV = @Code)
		begin
			select 1;
			return;
			end-- Mã NV tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK3.QLVT.dbo.NhanVien AS NV WHERE NV.MANV = @Code)
			begin
			select 2;
			return;
			end-- Mã NV tồn tại ở phân mảnh khác
	END

	-- Kho
	IF(@Type = 'MAKHO')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.Kho WHERE dbo.Kho.MAKHO = @Code)
			begin
			select 1;
			return;
			end-- Mã kho tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK3.QLVT.dbo.Kho AS KHO WHERE KHO.MAKHO = @Code)
		begin

			select 2;
			return;
			end-- Mã Kho tồn tại ở phân mảnh khác
	END
	IF(@Type = 'TENKHO')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.Kho WHERE dbo.Kho.TENKHO = @Code)
			begin
			select 1;
			return
			end-- Tên kho tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK3.QLVT.dbo.Kho AS KHO WHERE KHO.TENKHO = @Code)
		begin
			select 2;
			return;
			end
			-- Tên Kho tồn tại ở phân mảnh khác
	END

	-- Đặt hàng
	IF(@Type = 'MasoDDH')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.DatHang WHERE dbo.DatHang.MasoDDH = @Code)
			begin
			select 1;
			return;
			end-- Mã DDH tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK0.QLVT.dbo.DatHang AS DH WHERE DH.MasoDDH = @Code)
			begin
			select 2;
			return;
			end -- Mã DDH tồn tại ở phân mảnh khác
	END

	-- Phiếu Xuất
	IF(@Type = 'MAPX')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.PhieuXuat WHERE dbo.PhieuXuat.MAPX = @Code)
			begin
			select 1;
			return;
			end
			-- Mã PX tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK0.QLVT.dbo.PhieuXuat AS PX WHERE PX.MAPX = @Code)
		begin
			select 2;
			return;
			end-- Mã PX tồn tại ở phân mảnh khác
	END

	-- Phiếu Nhập
	IF(@Type = 'MAPN')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.PhieuNhap WHERE dbo.PhieuNhap.MAPN = @Code)
		begin
			select 1;
			return;
			end-- Mã PN tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK0.QLVT.dbo.PhieuNhap AS PN WHERE PN.MAPN = @Code)
		begin
			select 2;
			return;
			end-- Mã PN tồn tại ở phân mảnh khác
	END

	-- Vật tư, chỉ cần check ở site hiện tại
	IF(@Type = 'MAVT')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.Vattu WHERE MAVT = @Code)
		begin
		select 1;
		return;
		end
	END


	IF(@Type = 'FK_DATHANG_KHO')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.DatHang WHERE dbo.DatHang.MAKHO = @Code)
			begin
			select 1;
			return;
			end-- Kho có đơn đặt hàng
	END

	IF(@Type = 'FK_PHIEUNHAP_KHO')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.PhieuNhap WHERE dbo.PhieuNhap.MAKHO = @Code)
			begin
			select 1;
			return;
			end-- Kho có phiếu nhập
	END

	IF(@Type = 'FK_PHIEUXUAT_KHO')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.PhieuXuat WHERE dbo.PhieuXuat.MAKHO = @Code)
			begin
			select 1;
			return;
			end-- Kho có phiếu xuất
	END
	IF(@Type = 'FK_DATHANG_VATTU')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.CTDDH WHERE dbo.CTDDH.MAVT = @Code)
			begin
			select 1;
			return;
			end-- Kho có đơn đặt hàng
	END

	IF(@Type = 'FK_PHIEUNHAP_VATTU')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.CTPN WHERE dbo.CTPN.MAVT = @Code)
			begin
			select 1;
			return;
			end-- Kho có phiếu nhập
	END

	IF(@Type = 'FK_PHIEUXUAT_VATTU')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.CTPX WHERE dbo.CTPX.MAVT = @Code)
			begin
			select 1;
			return;
			end-- Kho có phiếu xuất
	END

	

	select 0
	return-- Không bị trùng
END