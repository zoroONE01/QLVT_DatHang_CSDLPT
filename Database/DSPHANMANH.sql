use QLVT
go
CREATE VIEW V_DS_PHANMANH 
AS 
SELECT TENCN=PUBS.description ,TENSERVER=SUBS.subscriber_server 
from sysmergepublications PUBS,sysmergesubscriptions SUBS
where SUBS.pubid=PUBS.pubid and  SUBS.subscriber_server !=PUBS.publisher 
	and SUBS.subscriber_server!='MINHTO-PC\MTSITE3'--khong lay CHI NHANH 3

go

SELECT * from V_DS_PHANMANH