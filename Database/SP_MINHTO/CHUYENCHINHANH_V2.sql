USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_ChuyenChiNhanh]    Script Date: 16/02/2022 10:27:03 CH ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_ChuyenChiNhanh] @MaNV int , @MaCN nchar(10)
as
DECLARE @LGNAME VARCHAR(50)
DECLARE @USERNAME VARCHAR(50)
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE  --trong qua trình kiểm tra không cho CRUD data trên bảng nhân viên
begin 
	set xact_abort on
		set nocount on
		begin distributed transaction
		begin try
		--select @@TRANCOUNT beginTran
		--select @@TRANCOUNT beginUpdateNhanVienMoi
	-- kiểm tra chi nhánh sắp chuyển tới có thông tin nhân viên đó chưa
			if exists (select MaNV from LINK1.QLVT.dbo.NhanVien nv where nv.MANV=@MANV)
				begin  --cập nhật trạng thái
					UPDATE LINK1.QLVT.dbo.NhanVien set TrangThaiXoa=0;
				end 
			else
			begin --THÊM MỚI NHÂN VIÊN VÀO CHI NHANH
				insert into LINK1.QLVT.dbo.NhanVien (MANV,HO,TEN,DIACHI,NGAYSINH,LUONG,MACN)
				SELECT MANV,HO,TEN,DIACHI,NGAYSINH,LUONG,MACN=@MaCN from NhanVien where MANV=@MaNV
			end
			--select @@TRANCOUNT afterpdateNhanVienMoi
		--Kiểm tra thông tin nhânvien và login và user ở site hiện tại
		-- Xoá user và xoá login	
				--select @@TRANCOUNT beforeXoaNhanVien
			--KIEM TRA NHAN VIEN CO LAP PHIEU CHUA
				if exists(select nv.MANV from NhanVien nv where nv.MANV =@MaNV and 
				exists (select MasoDDH from DatHang where @MaNV=DatHang.MANV)
				OR EXISTS(SELECT MAPX FROM PhieuXuat WHERE PhieuXuat.MANV = @MaNV) 
								OR EXISTS(SELECT MasoDDH FROM DatHang WHERE DatHang.MANV =@MaNV))
				begin 
					update NhanVien set TrangThaiXoa=1
					where MANV=@MaNV
				end
				else
					begin 
						delete from NhanVien where MANV=@MaNV
					end
			--select 1/0
				--exec SP_XOA_NHANVIEN @MANV  
		--	select @@TRANCOUNT afterXoaNhanVien	
			--select @@TRANCOUNT BEFORE_COMIT	
			commit tran
			--select @@TRANCOUNT AFTER_COMIT	

		----kiểm tra nhân viên có user login
		if exists (select SUSER_SNAME(sid) from sys.sysusers where name =CAST(@MaNV AS nvarchar))
	--xoa user
			begin
				--SELECT @@TRANCOUNT BEFORERTRAN_DROPUSER
				SET @LGNAME = CAST((SELECT SUSER_SNAME(sid) FROM sys.sysusers WHERE name = CAST(@MaNV AS NVARCHAR)) AS VARCHAR(50))
					SET @USERNAME = CAST(@MaNV AS VARCHAR(50))
					EXEC SP_DROPUSER @USERNAME;
					EXEC SP_DROPLOGIN @LGNAME;
				--SELECT @@TRANCOUNT AFTERTRAN_DROPUSER
			end	
		end try
		begin catch
			begin
			 SELECT ERROR_MESSAGE();  
			  THROW 51000, 'Chuyển chi nhánh thất bại', 1;
			-- raiserror('Chuyển chi nhánh thất bại',16,1,@MANV)
			end
		end catch
end
GO

