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

package com.openbravo.pos.inventory;

import com.openbravo.pos.forms.AppLocal;
import com.alee.laf.*;


/**
 *
 * @author jack
 */
public class InvEditLine extends javax.swing.JDialog {
    private boolean ok;

    /**
     * Creates new form InvEditLine
     * @param parent
     * @param modal
     */
    public InvEditLine(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public boolean isOK() {
        boolean ok = false;
        return ok;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webLabel1 = new com.alee.laf.label.WebLabel();
        webTxtPriceBuy = new com.alee.laf.text.WebTextField();
        webButtonOK = new com.alee.laf.button.WebButton();
        webButtonCancel = new com.alee.laf.button.WebButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        webLabel1.setText(AppLocal.getIntString("button.exit")); // NOI18N
        webLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        webLabel1.setPreferredSize(new java.awt.Dimension(130, 30));

        webTxtPriceBuy.setText("webTextField1");
        webTxtPriceBuy.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        webTxtPriceBuy.setPreferredSize(new java.awt.Dimension(200, 30));

        webButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/ok.png"))); // NOI18N
        webButtonOK.setPreferredSize(new java.awt.Dimension(80, 45));
        webButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButtonOKActionPerformed(evt);
            }
        });

        webButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cancel.png"))); // NOI18N
        webButtonCancel.setPreferredSize(new java.awt.Dimension(80, 45));
        webButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(webButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(webButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(webTxtPriceBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webTxtPriceBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButtonOKActionPerformed
        ok = true;
        String invlinevalue = (String)webTxtPriceBuy.getText();
        dispose();
    }//GEN-LAST:event_webButtonOKActionPerformed

    private void webButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_webButtonCancelActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton webButtonCancel;
    private com.alee.laf.button.WebButton webButtonOK;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.text.WebTextField webTxtPriceBuy;
    // End of variables declaration//GEN-END:variables
}