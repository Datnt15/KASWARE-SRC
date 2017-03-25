//    KASWARE  - POS Solution
//    Copyright (c) 2009-2015 uniCenta & previous Openbravo POS works
//    http://kasware.com/
//
//    This file is part of KASWARE
//
//    KASWARE is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   KASWARE is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with KASWARE.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author JG uniCenta
 */
public interface SentenceEnum {
    
    /**
     *
     * @throws BasicException
     */
    public void load() throws BasicException;

    /**
     *
     * @param params
     * @throws BasicException
     */
    public void load(Object params) throws BasicException;

    /**
     *
     * @return
     * @throws BasicException
     */
    public Object getCurrent() throws BasicException;

    /**
     *
     * @return
     * @throws BasicException
     */
    public boolean next() throws BasicException;    
}