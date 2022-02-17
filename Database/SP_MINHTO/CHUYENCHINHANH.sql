USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_ChuyenChiNhanh]    Script Date: 15/02/2022 12:24:58 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_ChuyenChiNhanh] @MaNV int , @MaCN nchar(10)
as
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE  --trong qua trình kiểm tra không cho CRUD data trên bảng nhân viên
begin 
	begin try
		
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
		--Kiểm tra thông tin nhânvien và login và user ở site hiện tại
			 -- xoá user và xoá login	
				exec SP_XOA_NHANVIEN @MANV  
			
		
	end try
	begin catch
		  raiserror('Chuyển chi nhánh thất bại',16,1,@MaNV,@MaCN)
	end catch
end
GO

