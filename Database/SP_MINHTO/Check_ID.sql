USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[Check_ID]    Script Date: 15/02/2022 12:20:22 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Check_ID] @id nvarchar(10), @type nvarchar(10)
as
begin
-- Đặt hàng
	IF(@Type = 'DatHang')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.DatHang WHERE dbo.DatHang.MasoDDH = @id)
			RETURN 1; -- Mã DDH tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK0.QLVT.dbo.DatHang AS DH WHERE DH.MasoDDH = @id)
			RETURN 2; -- Mã DDH tồn tại ở phân mảnh khác
	END

	-- Phiếu Xuất
	IF(@Type = 'PhieuXuat')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.PhieuXuat WHERE dbo.PhieuXuat.MAPX =@id)
			RETURN 1; -- Mã PX tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK0.QLVT.dbo.PhieuXuat AS PX WHERE PX.MAPX = @id)
			RETURN 2; -- Mã PX tồn tại ở phân mảnh khác
	END

	-- Phiếu Nhập
	IF(@Type = 'PhieuNhap')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.PhieuNhap WHERE dbo.PhieuNhap.MAPN = @id)
			RETURN 1; -- Mã PN tồn tại ở phân mảnh hiện tại
		ELSE IF EXISTS(SELECT * FROM LINK0.QLVT.dbo.PhieuNhap AS PN WHERE PN.MAPN =@id)
			RETURN 2; -- Mã PN tồn tại ở phân mảnh khác
	END

	-- Vật tư, chỉ cần check ở site hiện tại
	IF(@Type = 'VatTu')
	BEGIN
		IF EXISTS(SELECT * FROM dbo.Vattu WHERE MAVT = @id)
		RETURN 1;
	END
	RETURN 0 -- Không bị trùng

end
GO

