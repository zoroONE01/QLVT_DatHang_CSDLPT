USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_LayNhanVien]    Script Date: 15/02/2022 12:34:35 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_LayNhanVien] @id int
as
begin 
select MANV, HO, TEN , NGAYSINH, DIACHI, LUONG, MACN 
from NhanVien where MANV=@id
end
GO

