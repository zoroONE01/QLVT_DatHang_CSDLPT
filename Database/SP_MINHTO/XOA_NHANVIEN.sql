USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_XOA_NHANVIEN]    Script Date: 15/02/2022 12:43:32 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_XOA_NHANVIEN] @ID INT 
as
DECLARE @LGNAME VARCHAR(50)
DECLARE @USERNAME VARCHAR(50)
begin
	begin try	
		BEGIN TRAN
	--kiểm tra nhân viên có lập phiếu chưa 
		if exists(select nv.MANV from NhanVien nv where nv.MANV =@ID and 
			exists (select MasoDDH from DatHang where @ID=DatHang.MANV)
			OR EXISTS(SELECT MAPX FROM PhieuXuat WHERE PhieuXuat.MANV = @ID) 
							OR EXISTS(SELECT MasoDDH FROM DatHang WHERE DatHang.MANV =@ID))
			begin 
				update NhanVien set TrangThaiXoa=1
				where MANV=@ID
			end
		else
			begin 
				delete from NhanVien where MANV=@ID
			end
		commit tran
	----kiểm tra nhân viên có user login
		if exists (select SUSER_SNAME(sid) from sys.sysusers where name =CAST(@ID AS nvarchar))
	--xoa user
			begin
				SET @LGNAME = CAST((SELECT SUSER_SNAME(sid) FROM sys.sysusers WHERE name = CAST(@ID AS NVARCHAR)) AS VARCHAR(50))
					SET @USERNAME = CAST(@ID AS VARCHAR(50))
					EXEC SP_DROPUSER @USERNAME;
					EXEC SP_DROPLOGIN @LGNAME;
			end	
		
	end try
	begin catch
		if(@@TRANCOUNT >0)
			begin
			rollback tran
			raiserror('Delete NhanVien failed',16,1,@ID)
			end
	end catch
	----kiểm tra nhân viên có user login
end
GO

