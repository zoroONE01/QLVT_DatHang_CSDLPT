USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_CAPNHAT_SOLUONG_VATTU]    Script Date: 15/02/2022 12:23:31 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[SP_CAPNHAT_SOLUONG_VATTU] @MAVT nchar(4),@SOLUONG int
as
begin
set xact_abort on
		begin distributed transaction
			update Vattu 
			set SOLUONGTON=SOLUONGTON+@SOLUONG
			WHERE MAVT= @MAVT
			update LINK1.QLVT.dbo.Vattu
			set SOLUONGTON=SOLUONGTON+@SOLUONG
			WHERE MAVT= @MAVT
		commit tran
end
GO

