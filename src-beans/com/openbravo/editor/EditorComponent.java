//    KASWARE  - POS Solution
//    Copyright (c) KASWARE & previous Openbravo POS works
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

package com.openbravo.editor;

import java.awt.Component;

/**
 *
 * @author JG uniCenta
 */
public interface EditorComponent {
    
    /**
     *
     * @param ed
     */
    public void addEditorKeys(EditorKeys ed);    

    /**
     *
     * @return
     */
    public Component getComponent();
    
    /**
     *
     */
    public void deactivate();

    /**
     *
     * @param c
     */
    public void typeChar(char c);

    /**
     *
     * @param c
     */
    public void transChar(char c);
}
