echo off
echo Loading MySQL tar file
docker load -i mysql-server2.tar
docker tag e799c7f9ae9c mysql-db-unpopulated2
echo Image "mysql-db-unpopulated" now available
echo 

