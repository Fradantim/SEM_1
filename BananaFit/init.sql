CREATE DATABASE SEMINARIO1;

CREATE LOGIN SEMINARIO1 WITH password='SEMINARIO1', default_database= SEMINARIO1, check_policy=OFF;

USE SEMINARIO1;

exec sp_changedbowner SEMINARIO1;

/*

sp_helpdb SEMINARIO1;
^ para buchonear quien es el owner de la bbdd

*/