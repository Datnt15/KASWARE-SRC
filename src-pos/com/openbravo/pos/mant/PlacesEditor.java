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

package com.openbravo.pos.mant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/**
 *
 * @author adrianromero
 */
public final class PlacesEditor extends JPanel implements EditorRecord {
    
    private SentenceList m_sentfloor;
    private ComboBoxValModel m_FloorModel;
    
    private String m_sID;
    protected int count_init = 0;
    
    /** Creates new form PlacesEditor
     * @param dlSales
     * @param dirty */
    public PlacesEditor(DataLogicSales dlSales, DirtyManager dirty) {
        initComponents();
        
        m_sentfloor = dlSales.getFloorsList();
        
//        m_sentfloor = dlSales.getFloorTablesList();
        m_FloorModel = new ComboBoxValModel();
        
        m_jName.getDocument().addDocumentListener(dirty);
        m_jFloor.addActionListener(dirty);
        m_jX.getDocument().addDocumentListener(dirty);
        m_jY.getDocument().addDocumentListener(dirty);
        writeValueEOF();
    }
    
    /**
     *
     * @throws BasicException
     */
    public void activate() throws BasicException {
        
        m_FloorModel = new ComboBoxValModel(m_sentfloor.list());
        m_jFloor.setModel(m_FloorModel);
    }
    
    /**
     *
     */
    @Override
    public void refresh() {
    }

    /**
     *
     */
    @Override
    public void writeValueEOF() {
        
        m_sID = null;
        m_jName.setText(null);
        m_FloorModel.setSelectedKey(null);
        m_jX.setText(null);
        m_jY.setText(null);

        m_jName.setEnabled(false);
        m_jFloor.setEnabled(false);
        m_jX.setEnabled(false);
        m_jY.setEnabled(false);
        
    }

    /**
     *
     */
    @Override
    public void writeValueInsert() {

        m_sID = UUID.randomUUID().toString(); 
        m_jName.setText(null);
        m_FloorModel.setSelectedKey(null);
        m_jX.setText(null);
        m_jY.setText(null);
        
        m_jName.setEnabled(true);
        m_jFloor.setEnabled(true);
        m_jX.setEnabled(true);
        m_jY.setEnabled(true);
    }

    /**
     *
     * @param value
     */
    @Override
    public void writeValueDelete(Object value) {
        
        Object[] place = (Object[]) value;
        m_sID = Formats.STRING.formatValue(place[0]);
        m_jName.setText(Formats.STRING.formatValue(place[1]));
        m_jX.setText(Formats.INT.formatValue(place[2]));
        m_jY.setText(Formats.INT.formatValue(place[3]));
        m_FloorModel.setSelectedKey(place[4]);
        

        m_jName.setEnabled(false);
        m_jFloor.setEnabled(false);
        m_jX.setEnabled(false);
        m_jY.setEnabled(false);
    }

    /**
     *
     * @param value
     */
    @Override
    public void writeValueEdit(Object value) {
        Object[] place = (Object[]) value;
        m_sID = Formats.STRING.formatValue(place[0]);
        m_jName.setText(Formats.STRING.formatValue(place[1]));
        m_jX.setText(Formats.INT.formatValue(place[2]));
        m_jY.setText(Formats.INT.formatValue(place[3]));
        m_FloorModel.setSelectedKey(place[4]);

        m_jName.setEnabled(true);
        m_jFloor.setEnabled(true);
        m_jX.setEnabled(true);
        m_jY.setEnabled(true);
    }

    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public Object createValue() throws BasicException {
        Object[] place = new Object[6];
        place[0] = m_sID;
        place[1] = m_jName.getText();
        place[2] = Formats.INT.parseValue(m_jX.getText());
        place[3] = Formats.INT.parseValue(m_jY.getText());
        place[4] = m_FloorModel.getSelectedKey();
        place[5] = "qwerty";
        return place;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Component getComponent() {
        return this;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        m_jFloor = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        m_jX = new javax.swing.JTextField();
        m_jY = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        floor_layout = new javax.swing.JPanel();

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText(AppLocal.getIntString("label.name")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(110, 30));

        m_jName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jName.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText(AppLocal.getIntString("label.placefloor")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(110, 30));

        m_jFloor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jFloor.setPreferredSize(new java.awt.Dimension(0, 30));
        m_jFloor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                update_floor(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText(AppLocal.getIntString("label.placeposition")); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Across :");
        jLabel5.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Down:");
        jLabel3.setPreferredSize(new java.awt.Dimension(0, 30));

        m_jX.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jX.setPreferredSize(new java.awt.Dimension(0, 30));
        m_jX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jXActionPerformed(evt);
            }
        });

        m_jY.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jY.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("<html>Position buttons in your Table plan graphic set in the Floor option <br><br> Start Position 0, 0 is Top Left");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setMinimumSize(new java.awt.Dimension(50, 40));
        jLabel7.setPreferredSize(new java.awt.Dimension(489, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout floor_layoutLayout = new javax.swing.GroupLayout(floor_layout);
        floor_layout.setLayout(floor_layoutLayout);
        floor_layoutLayout.setHorizontalGroup(
            floor_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        floor_layoutLayout.setVerticalGroup(
            floor_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(floor_layout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m_jX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m_jY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m_jFloor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(floor_layout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void m_jXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jXActionPerformed

    private void update_floor(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_update_floor
        if(++count_init > 2){
            System.out.println(m_jFloor.getSelectedItem());
        }
        JLabel pic1,pic2,pic3;

        pic1 = new JLabel();
        pic2 = new JLabel();
        pic3 = new JLabel();
        pic1.setBounds(20, 30, 100, 100);
        pic2.setBounds(250, 30, 100, 100);
        pic3.setBounds(20, 140, 100, 100);
        pic1.setIcon(new ImageIcon("C:\\Users\\Dat\\Desktop\\1.png"));
        pic2.setIcon(new ImageIcon("C:\\Users\\Dat\\Desktop\\2.png"));
        pic3.setIcon(new ImageIcon("C:\\Users\\Dat\\Desktop\\3.png"));

        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
//                JComponent table = (JComponent)e.getSource();
//                table.setBounds(e.getX(), e.getY(), 100, 100);
//                TransferHandler th = table.getTransferHandler();
//                Point des = th.getDropImageOffset();
//                th.exportAsDrag(table, e, TransferHandler.COPY);
//                System.out.println(des);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JComponent table = (JComponent)e.getSource();
                table.setBounds(e.getX(), e.getY(), 100, 100);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            
        };

        pic1.addMouseListener(ml);
        pic2.addMouseListener(ml);
        pic3.addMouseListener(ml);

        pic1.setTransferHandler(new TransferHandler("icon"));
        pic2.setTransferHandler(new TransferHandler("icon"));
        pic3.setTransferHandler(new TransferHandler("icon"));

        floor_layout.add(pic1);
        floor_layout.add(pic2);
        floor_layout.add(pic3);
    }//GEN-LAST:event_update_floor
        
        
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel floor_layout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox m_jFloor;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jX;
    private javax.swing.JTextField m_jY;
    // End of variables declaration//GEN-END:variables
    
}
