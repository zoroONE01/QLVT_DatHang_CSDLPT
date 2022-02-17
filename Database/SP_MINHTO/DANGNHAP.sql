USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_DANGNHAP]    Script Date: 15/02/2022 12:25:13 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_DANGNHAP] @TENLOGIN NVARCHAR(50)
as 
DECLARE @TENUSER nvarchar(10) --MUỐN BIẾT TÊN USER THÌ DỰA VÀO LOGIN, BIẾT TÊN NHOMS DỰA VÀO GROUPID, muốn biết group id dựa vào MEMBERUID trong bảng sysmember
select @TENUSER=NAME FROM sys.sysusers where sid=SUSER_SID(@TENLOGIN) --lấy ra SID của login name

SELECT MANV=@TENUSER,HOTEN=(SELECT HO + ' '+ TEN FROM NhanVien where MANV=@TENUSER),
			TENNHOM =
				(		--lấy ra tên nhóm của user(role database) (quyền database của user) 
					SELECT NAME FROM SYS.sysusers WHERE uid= 
						(select groupuid from sys.sysmembers where memberuid=
							(select uid from sys.sysusers where name=@TENUSER)
						)
				)

--
GO

