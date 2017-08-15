call docker run --detach --name=mysql2 -p 1996:3306  --env="MYSQL_ROOT_PASSWORD=ppp" mysql-db-unpopulated:latest
call docker cp db_grad_cs_1917_2.sql mysql2:/root
call docker cp init-populate-mysql-db_2.sh mysql2:/root
call docker cp create-db-teams-and-lectures-for-2017_2.sql mysql2:/root
echo Waiting for MySQL deamon to initialise, do not interrupt... (CTRL+C will termiate the install)
timeout 30 /NOBREAK
call docker exec -it mysql2 bash -c "cd /root; ./init-populate-mysql-db_2.sh"