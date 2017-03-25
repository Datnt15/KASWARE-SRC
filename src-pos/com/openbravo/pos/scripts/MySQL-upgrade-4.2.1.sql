--    KASWARE - POS Solution
--    Copyright (c) KASWARE
--    http://kasware.com/
--
--    This file is part of KASWARE.
--
--    KASWARE is free software: you can redistribute it and/or modify
--    it under the terms of the GNU General Public License as published by
--    the Free Software Foundation, either version 3 of the License, or
--    (at your option) any later version.
--
--    KASWARE is distributed in the hope that it will be useful,
--    but WITHOUT ANY WARRANTY; without even the implied warranty of
--    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--    GNU General Public License for more details.
--
--    You should have received a copy of the GNU General Public License
--    along with KASWARE.  If not, see <http://www.gnu.org/licenses/>.

-- Database upgrade script for MySQL
-- v4.2.1 - v4.2.2 23DEC2016

--
-- CLEAR THE DECKS
--
DELETE FROM sharedtickets;

-- RESOURCE
UPDATE `resources` SET `content` = $FILE{/com/openbravo/pos/templates/Printer.Inventory.xml} WHERE `id` = '31';

-- UPDATE App' version
UPDATE applications SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};