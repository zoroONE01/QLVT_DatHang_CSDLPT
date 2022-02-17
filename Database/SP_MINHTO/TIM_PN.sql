USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_Tim_PN]    Script Date: 15/02/2022 12:39:39 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[SP_Tim_PN]
@MaPN nchar(8)
as
begin
	select MAPN,NGAY,MasoDDH,MANV,MAKHO from PhieuNhap where MAPN=@MaPN
end 

GO

