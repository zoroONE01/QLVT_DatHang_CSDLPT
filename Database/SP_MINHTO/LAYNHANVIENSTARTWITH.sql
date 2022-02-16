USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_LayPhieuNhapStartingWith]    Script Date: 15/02/2022 12:34:56 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_LayPhieuNhapStartingWith] @MAPN nchar(8)
as
begin 
	select MAPN,NGAY,MasoDDH,MANV,MAKHO from PhieuNhap where MAPN like '%'+LTRIM(RTRIM(@MAPN))+'%'
end
GO

