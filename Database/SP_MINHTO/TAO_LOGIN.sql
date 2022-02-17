USE [QLVT]
GO

/****** Object:  StoredProcedure [dbo].[SP_TAO_LOGIN]    Script Date: 15/02/2022 12:36:53 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_TAO_LOGIN] @LGNAME varchar(50),
@PASS varchar(50),
@USERNAME varchar(50),
@ROLE varchar(50),
@rs int out 
as 
 declare @RET INT 
SET XACT_ABORT ON

 BEGIN TRY
	SELECT @@TRANCOUNT as beforeAddLogin
	exec @RET=SP_ADDLOGIN @LGNAME, @PASS ,'QLVT'
	SELECT @@TRANCOUNT as afterAddLogin
 END TRY
 BEGIN CATCH
	SELECT ERROR_MESSAGE() as error,@@TRANCOUNT as trancount
	 set @rs=1
	return 1 -- login name was existed
 END CATCH

 BEGIN TRY
  declare @RET2 INT 
		SELECT @@TRANCOUNT as beforeAddLogin
	 exec @RET2 =sp_grantdbaccess @LGNAME,@USERNAME
	 SELECT @@TRANCOUNT as afterAddLogin
 END TRY
 BEGIN CATCH
		SELECT ERROR_MESSAGE() as error,@@TRANCOUNT as trancount,@RET2 as ret
		if(@@TRANCOUNT>0) rollback
		EXEC SP_DROPLOGIN @LGNAME
		set @rs=2
		RETURN 2
 END CATCH

  BEGIN TRY
	 EXEC sp_addrolemember @ROLE, @USERNAME
	 IF @ROLE='CongTy' or @ROLE='ChiNhanh'
	begin 
		exec sp_addsrvrolemember @LGNAME,'securityadmin'
	end
	--raiserror('khong the them',16,1)
	set @rs=0
	return 0
 END TRY
 BEGIN CATCH
	SELECT ERROR_MESSAGE() as error,@@TRANCOUNT as trancount
	set @rs=-1
	return -1 -- login name was existed
 END CATCH
 
GO

