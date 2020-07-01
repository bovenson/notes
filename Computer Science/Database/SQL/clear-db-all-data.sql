# 数据库名称
SET @dbname='cloudlibrary';
SELECT table_name FROM information_schema.tables WHERE TABLE_SCHEMA=@dbname;
SELECT @rows=@@rowcount
set 