--TAO LOGIN ACCOUNT
-- 
create procedure [dbo].[SP_LOGIN]
@LGNAME varchar(50),
@PASS varchar(50),
@USERNAME varchar(50),
@ROLE varchar(50)
as 
 declare @RET INT 
 exec @RET=SP_ADDLOGIN @LGNAME, @PASS ,'QLVT'
 if @ret=1
	return 1 -- login name was existed
 exec @RET =sp_grantdbaccess @LGNAME,@USERNAME
 IF @RET=1  -- user name was existed
BEGIN 
	EXEC SP_DROPLOGIN @LGNAME
	RETURN 2
END
EXEC sp_addrolemember @ROLE, @USERNAME	-- add role for user 

 IF @ROLE='CONGTY' or @ROLE='CHINHANH'
begin 
	exec sp_addsrvrolemember @LGNAME,'securityadmin'
end
RETURN 0	--tao thanh cong


