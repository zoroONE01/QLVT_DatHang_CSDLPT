USE [QLVT]
GO
/****** Object:  StoredProcedure [dbo].[SP_TAO_PN]    Script Date: 17/02/22 5:09:58 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER procedure [dbo].[SP_TAO_PN]
@MaPN nchar(8),@Ngay date,@MaSoDDH nchar(8),@MaNV int ,@MaKho nchar(4)
as
begin
	declare @rs int =0
	--kiểm tra tồn tại
	exec @rs=Check_ID @MaPN,'PhieuNhap'
	if(@rs!=0) 
	begin return 0;
		raiserror('Phieu nhap ton tai',16,1)
	end
	insert into PhieuNhap (MAPN,NGAY,MasoDDH,MANV,MAKHO) values(@MaPN,@Ngay,@MaSoDDH,@MaNV,@MaKho)
end 

