--SP DANG NHAP
USE QLVT
SELECT DN.sid, DN.loginname,U.name FROM master.sys.syslogins DN,sys.sysusers U where U.sid=DN.sid
 select uid,name,gid,sid from sys.sysusers -- USERs or ROLEs(mức độ database) of login bao gổm uid, name, gid(nhóm quyền), sid(khoá ngoại tới LOGIN)(giá trị đinh dạnh tự động tạo khi thực hiện câu lệnh create role or user tham chiếu tới id login)
 select sid,loginname, * from master.sys.syslogins  -- danh sách Login of sever bao gồm sid là giá trị định danh sid, LoginName  
 select * from sys.sysmembers -- lưu trữ thông tin user(memberid) và roles(groupid)(nhóm quyền group id ví dụ chi nhánh, congty,user,DATAREADER,owner) nào đó quan hệ n->1

 
USE QLVT
go
--LẤY RA MÃ NHÂN VIÊN(SYS.SYSUSERS), HỌ TÊN(BẢNG NHÂN VIÊN), TÊN NHÓM(NHÓM QUYỀN)  sys.sysusers
create procedure SP_DANGNHAP  @TENLOGIN NVARCHAR(50), @MANV int output, @HOTEN nvarchar(50) output, @TENNHOM varchar(20) output
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
DECLARE  @MANV nvarchar(10) , @HOTEN nvarchar(50), @TENNHOM varchar(20)
exec SP_DANGNHAP 'MT' , @MANV OUTPUT , @HOTEN OUTPUT , @TENNHOM OUTPUT
print @MANV+'  '+ @HOTEN+' '+ @TENNHOM