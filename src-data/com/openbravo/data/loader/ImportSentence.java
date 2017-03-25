//    KASWARE  - POS Solution
//    Copyright (c)  uniCenta & previous Openbravo POS works
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
 * @author  adrian
 */
public class ImportSentence extends BaseSentence {
    
    /** Creates a new instance of ImportSentence */
    public ImportSentence() {
    }
    
    /**
     *
     * @throws BasicException
     */
    @Override
    public void closeExec() throws BasicException {
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public DataResultSet moreResults() throws BasicException {
        return null;
    }
    
    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public DataResultSet openExec(Object params) throws BasicException {
        return null;
    }
}
