#!/bin/sh
#    KASWARE POS Solution designed for Touch Screen
#    Copyright (c) KASWARE & previous Openbravo POS works
#    http://sourceforge.net/projects/KASWARE
#
#    This file is part of KASWARE.
#
#    KASWARE is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    KASWARE is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with KASWARE.  If not, see <http://www.gnu.org/licenses/>.

DIRNAME=`dirname $0`
CP=$DIRNAME/kasware.jar
CP=$CP:$DIRNAME/locales/

java -cp $CP -splash:unicenta_splash_dark.png com.openbravo.pos.config.JFrmConfig
