@echo off
REM    KASWARE POS Solutions designed for Touch Screen

REM
set DIRNAME=%~dp0
set CP="%DIRNAME%kasware.jar"
set CP=%CP%;"%DIRNAME%locales/"

start /B javaw -cp %CP% com.openbravo.pos.config.JFrmConfig