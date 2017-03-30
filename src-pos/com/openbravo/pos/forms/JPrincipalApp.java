//    KASWARE POS Solution
//    Copyright (c) KASWARE #
//    http://kasware.com//product
//
//    This file is part of KASWARE.
//
//    KASWARE is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    KASWARE is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.Hashcypher;
import com.openbravo.pos.util.StringUtils;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author adrianromero
 */
public class JPrincipalApp extends javax.swing.JPanel implements AppUserView {

    private static final Logger logger = Logger.getLogger("com.openbravo.pos.forms.JPrincipalApp");
    
    private final JRootApp m_appview;
    private final AppUser m_appuser;
    private  AppView m_App;
    private DataLogicSystem m_dlSystem;
    
    private JLabel m_principalnotificator;
    
    private JPanelView m_jLastView;    
    private Action m_actionfirst;
    
    private Map<String, JPanelView> m_aPreparedViews; // Prepared views   
    private Map<String, JPanelView> m_aCreatedViews;
    
    private Icon menu_open;
    private Icon menu_close;
    
    //HS Updates
    private CustomerInfo customerInfo;
    private List<String> list_layout;
    private static List<String> list_title;
    private String breadcrumd_duplicated_title="null";
    private static final String[][] MENU_VALUES = new String[][] {
        {"Sales", "com.openbravo.pos.sales.JPanelTicketSales"},
        {"Edit Sales", "com.openbravo.pos.sales.JPanelTicketEdits"},
        {"Customer Payment", "com.openbravo.pos.customers.CustomersPayment"},
        {"Payments", "com.openbravo.pos.panels.JPanelPayments"},
        {"Close Cash", "com.openbravo.pos.panels.JPanelCloseMoney"},
        {"Customers", "com.openbravo.pos.forms.MenuCustomers"},
        {"Customers", "com.openbravo.pos.customers.CustomersPanel"},
        {"Customers", "/com/openbravo/reports/customers.bs"},
        {"Sales", "/com/openbravo/reports/customers_sales.bs"},
        {"Debtors", "/com/openbravo/reports/customers_debtors.bs"},
        {"Cards", "/com/openbravo/reports/customers_cards.bs"},
        {"List", "/com/openbravo/reports/customers_list.bs"},
        {"List Export", "/com/openbravo/reports/customers_export.bs"},
        {"Suppliers", "com.openbravo.pos.forms.MenuSuppliers"},
        {"Suppliers", "com.openbravo.pos.suppliers.SuppliersPanel"},
        {"Suppliers", "/com/openbravo/reports/suppliers.bs"},
        {"Products", "/com/openbravo/reports/suppliers_products.bs"},
        {"Creditors", "/com/openbravo/reports/suppliers_creditors.bs"},
        {"Diary", "/com/openbravo/reports/suppliers_diary.bs"},
        {"Sales", "/com/openbravo/reports/suppliers_sales.bs"},
        {"List", "/com/openbravo/reports/suppliers_list.bs"},
        {"List Export", "/com/openbravo/reports/suppliers_export.bs"},
        {"Stock", "com.openbravo.pos.forms.MenuStockManagement"},
        {"Categories", "com.openbravo.pos.inventory.CategoriesPanel"},
        {"Products", "com.openbravo.pos.inventory.ProductsPanel"},
        {"Products Location", "com.openbravo.pos.inventory.ProductsWarehousePanel"},
        {"Stock Management", "com.openbravo.pos.inventory.StockManagement"},
        {"Auxiliar", "com.openbravo.pos.inventory.AuxiliarPanel"},
        {"Product Bundle", "com.openbravo.pos.inventory.BundlePanel"},
        {"Attributes", "com.openbravo.pos.inventory.AttributesPanel"},
        {"Attribute Values", "com.openbravo.pos.inventory.AttributeValuesPanel"},
        {"Attribute Sets", "com.openbravo.pos.inventory.AttributeSetsPanel"},
        {"Attribute Use", "com.openbravo.pos.inventory.AttributeUsePanel"},
        {"Uom", "com.openbravo.pos.inventory.UomPanel"},
        {"Inventory", "/com/openbravo/reports/inventory.bs"},
        {"Inventory Current", "/com/openbravo/reports/inventoryb.bs"},
        {"Inventory Diary", "/com/openbravo/reports/inventory_diary.bs"},
        {"Inventory Broken", "/com/openbravo/reports/inventorybroken.bs"},
        {"Inventory Diff", "/com/openbravo/reports/inventorydiff.bs"},
        {"Inventory Diff Detail", "/com/openbravo/reports/inventorydiffdetail.bs"},
        {"Inventory List Detail", "/com/openbravo/reports/inventorylistdetail.bs"},
        {"Products", "/com/openbravo/reports/products.bs"},
        {"Sale Catalog", "/com/openbravo/reports/salecatalog.bs"},
        {"Labels", "/com/openbravo/reports/productlabels.bs"},
        {"Labels Barcode", "/com/openbravo/reports/barcode_sheet.bs"},
        {"Labels Shelf-Edge", "/com/openbravo/reports/barcode_shelfedgelabels.bs"},
        {"Sales", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"Cash Closed", "/com/openbravo/reports/sales_closedpos.bs"},
        {"Cash Closed Export", "/com/openbravo/reports/sales_closedpos_1.bs"},
        {"Transactions", "/com/openbravo/reports/sales_cashregisterlog.bs"},
        {"Transaction Extended", "/com/openbravo/reports/sales_extendedcashregisterlog.bs"},
        {"Cash-Flow", "/com/openbravo/reports/sales_cashflow.bs"},
        {"Payment", "/com/openbravo/reports/sales_paymentreport.bs"},
        {"Category Summary", "/com/openbravo/reports/sales_categorysales.bs"},
        {"Category Detail", "/com/openbravo/reports/sales_categorysales_1.bs"},
        {"Sales by Product", "/com/openbravo/reports/sales_closedproducts.bs"},
        {"Sales by Category", "/com/openbravo/reports/sales_closedproducts_1.bs"},
        {"Sales by Customer", "/com/openbravo/reports/sales_extproducts.bs"},
        {"Sales Profit", "/com/openbravo/reports/sales_productsalesprofit.bs"},
        {"Taxes", "/com/openbravo/reports/sales_saletaxes.bs"},
        {"Tax Category", "/com/openbravo/reports/sales_taxcatsales.bs"},
        {"Tax: Summary", "/com/openbravo/reports/sales_taxes.bs"},
        {"Product Sales", "/com/openbravo/reports/sales_chart_productsales.bs"},
        {"Category Pie Chart", "/com/openbravo/reports/sales_chart_piesalescat.bs"},
        {"Sales Chart", "/com/openbravo/reports/sales_chart_chartsales.bs"},
        {"Time Series Product", "/com/openbravo/reports/sales_chart_timeseriesproduct.bs"},
        {"Top 10 Sales", "/com/openbravo/reports/sales_chart_top10sales.bs"},
        {"Maintenance", "com.openbravo.pos.forms.MenuMaintenance"},
        {"Resources", "com.openbravo.pos.admin.ResourcesPanel"},
        {"Roles", "com.openbravo.pos.admin.RolesPanel"},
        {"Users", "com.openbravo.pos.admin.PeoplePanel"},
        {"Tax Categories", "com.openbravo.pos.inventory.TaxCategoriesPanel"},
        {"Tax Customer Categories", "com.openbravo.pos.inventory.TaxCustCategoriesPanel"},
        {"Taxes", "com.openbravo.pos.inventory.TaxPanel"},
        {"Locations", "com.openbravo.pos.inventory.LocationsPanel"},
        {"Floors", "com.openbravo.pos.mant.JPanelFloors"},
        {"Tables", "com.openbravo.pos.mant.JPanelPlaces"},
        {"Vouchers", "com.openbravo.pos.voucher.VoucherPanel"},
        {"Users Report", "/com/openbravo/reports/people.bs"},
        {"Sales by User", "/com/openbravo/reports/usersales.bs"},
        {"No Sales by User", "/com/openbravo/reports/usernosales.bs"},
        {"Presence Management", "com.openbravo.pos.forms.MenuEmployees"},
        {"Breaks", "com.openbravo.pos.epm.BreaksPanel"},
        {"Leaves", "com.openbravo.pos.epm.LeavesPanel"},
        {"Daily Presence Report", "/com/openbravo/reports/dailypresencereport.bs"},
        {"Daily Schedule Report", "/com/openbravo/reports/dailyschedulereport.bs"},
        {"Performance Report", "/com/openbravo/reports/performancereport.bs"},
        {"Tools", "com.openbravo.pos.imports.JPanelCSV"},
        {"CSV Import", "com.openbravo.pos.imports.JPanelCSVImport"},
        {"Customer CSV Import", "com.openbravo.pos.imports.CustomerCSVImport"},
        {"CSV Reset", "com.openbravo.pos.imports.JPanelCSVCleardb"},
        {"Transfer", "com.unicenta.pos.transfer.Transfer"},
        {"Updated Prices", "/com/openbravo/reports/updatedprices.bs"},
        {"New Products", "/com/openbravo/reports/newproducts.bs"},
        {"Missing Data", "/com/openbravo/reports/missingdata.bs"},
        {"Invalid Data", "/com/openbravo/reports/invaliddata.bs"},
        {"Configuration", "com.openbravo.pos.config.JPanelConfiguration"},
        {"Printer", "com.openbravo.pos.panels.JPanelPrinter"},
        {"Check In Check Out", "com.openbravo.pos.epm.JPanelEmployeePresence"}
    };
    private static final String[][] SUB_MENU_VALUES = new String[][]{
        {"com.openbravo.pos.sales.JPanelTicketSales", "null"},
        {"com.openbravo.pos.sales.JPanelTicketEdits", "null"},
        {"com.openbravo.pos.customers.CustomersPayment", "null"},
        {"com.openbravo.pos.panels.JPanelPayments", "null"},
        {"com.openbravo.pos.panels.JPanelCloseMoney", "null"},
        {"com.openbravo.pos.forms.MenuCustomers", "null"},
        {"com.openbravo.pos.forms.MenuSuppliers", "null"},
        {"com.openbravo.pos.customers.CustomersPanel", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers_sales.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers_debtors.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers_diary.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers_cards.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers_list.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"/com/openbravo/reports/customers_export.bs", "com.openbravo.pos.forms.MenuCustomers"},
        {"com.openbravo.pos.suppliers.SuppliersPanel", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers_products.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers_creditors.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers_diary.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers_sales.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers_list.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"/com/openbravo/reports/suppliers_export.bs", "com.openbravo.pos.forms.MenuSuppliers"},
        {"com.openbravo.pos.inventory.CategoriesPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.forms.MenuStockManagement", "null"},
        {"com.openbravo.pos.inventory.ProductsPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.ProductsWarehousePanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.StockManagement", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.AuxiliarPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.BundlePanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.AttributesPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.AttributeValuesPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.AttributeSetsPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.AttributeUsePanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.inventory.UomPanel", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventory.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventoryb.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventory_diary.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventorybroken.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventorydiff.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventorydiffdetail.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/inventorylistdetail.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/products.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/productscatalog.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/salecatalog.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/productlabels.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/barcode_sheet.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/barcode_sheet_jm.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"/com/openbravo/reports/barcode_shelfedgelabels.bs", "com.openbravo.pos.forms.MenuStockManagement"},
        {"com.openbravo.pos.forms.MenuSalesManagement", "null"},
        {"/com/openbravo/reports/sales_closedpos.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_closedpos_1.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_cashregisterlog.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_extendedcashregisterlog.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_cashflow.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_paymentreport.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_categorysales.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_categorysales_1.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_closedproducts.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_closedproducts_1.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_extproducts.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_productsalesprofit.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_saletaxes.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_taxcatsales.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_taxes.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_chart_productsales.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_chart_piesalescat.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_chart_chartsales.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_chart_timeseriesproduct.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"/com/openbravo/reports/sales_chart_top10sales.bs", "com.openbravo.pos.forms.MenuSalesManagement"},
        {"com.openbravo.pos.forms.MenuMaintenance", "null"},
        {"com.openbravo.pos.admin.ResourcesPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.admin.RolesPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.admin.PeoplePanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.inventory.TaxCategoriesPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.inventory.TaxCustCategoriesPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.inventory.TaxPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.inventory.LocationsPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.mant.JPanelFloors", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.mant.JPanelPlaces", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.voucher.VoucherPanel", "com.openbravo.pos.forms.MenuMaintenance"},
        {"/com/openbravo/reports/people.bs", "com.openbravo.pos.forms.MenuMaintenance"},
        {"/com/openbravo/reports/usersales.bs", "com.openbravo.pos.forms.MenuMaintenance"},
        {"/com/openbravo/reports/usernosales.bs", "com.openbravo.pos.forms.MenuMaintenance"},
        {"com.openbravo.pos.forms.MenuEmployees", "null"},
        {"com.openbravo.pos.epm.BreaksPanel", "com.openbravo.pos.forms.MenuEmployees"},
        {"com.openbravo.pos.epm.LeavesPanel", "com.openbravo.pos.forms.MenuEmployees"},
        {"/com/openbravo/reports/dailypresencereport.bs", "com.openbravo.pos.forms.MenuEmployees"},
        {"/com/openbravo/reports/dailyschedulereport.bs", "com.openbravo.pos.forms.MenuEmployees"},
        {"/com/openbravo/reports/performancereport.bs", "com.openbravo.pos.forms.MenuEmployees"},
        {"com.openbravo.pos.imports.JPanelCSV", "null"},
        {"com.openbravo.pos.imports.JPanelCSVImport", "com.openbravo.pos.imports.JPanelCSV"},
        {"com.openbravo.pos.imports.CustomerCSVImport", "com.openbravo.pos.imports.JPanelCSV"},
        {"com.openbravo.pos.imports.JPanelCSVCleardb", "com.openbravo.pos.imports.JPanelCSV"},
        {"com.unicenta.pos.transfer.Transfer", "com.openbravo.pos.imports.JPanelCSV"},
        {"/com/openbravo/reports/updatedprices.bs", "com.openbravo.pos.imports.JPanelCSV"},
        {"/com/openbravo/reports/newproducts.bs", "com.openbravo.pos.imports.JPanelCSV"},
        {"/com/openbravo/reports/missingdata.bs", "com.openbravo.pos.imports.JPanelCSV"},
        {"/com/openbravo/reports/invaliddata.bs", "com.openbravo.pos.imports.JPanelCSV"},
        {"com.openbravo.pos.config.JPanelConfiguration", "null"},
        {"com.openbravo.pos.panels.JPanelPrinter", "null"},
        {"com.openbravo.pos.epm.JPanelEmployeePresence", "null"}
    };
    private static int layout = 0; 
    /** Creates new form JPrincipalApp
     * @param appview
     * @param appuser */
    public JPrincipalApp(JRootApp appview, AppUser appuser) {
        list_layout = new ArrayList<String>();
        list_title = new ArrayList<String>();
        m_appview = appview; 
        m_appuser = appuser;
                   
        m_dlSystem = (DataLogicSystem) m_appview.getBean("com.openbravo.pos.forms.DataLogicSystem");
        
        m_appuser.fillPermissions(m_dlSystem);
               
        m_actionfirst = null;
        m_jLastView = null;
     
        m_aPreparedViews = new HashMap<>();
        m_aCreatedViews = new HashMap<>();
                
        initComponents();
        
        jPanel2.add(Box.createVerticalStrut(50), 0);
        m_jPanelLeft.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));
        
 //       m_jPanelLeft.setBackground(Color.black);
        
        applyComponentOrientation(appview.getComponentOrientation());
       
        m_principalnotificator = new JLabel();
        m_principalnotificator.applyComponentOrientation(getComponentOrientation());
        m_principalnotificator.setText(m_appuser.getName());
        m_principalnotificator.setIcon(m_appuser.getIcon());
        
        if (jButton1.getComponentOrientation().isLeftToRight()) {
            menu_open = new javax.swing.ImageIcon(getClass().getResource(
                "/com/openbravo/images/menu-right.png"));
            menu_close = new javax.swing.ImageIcon(getClass().getResource(
                "/com/openbravo/images/menu-left.png"));
        } else {
            menu_open = new javax.swing.ImageIcon(getClass().getResource(
                "/com/openbravo/images/menu-left.png"));
            menu_close = new javax.swing.ImageIcon(getClass().getResource(
                "/com/openbravo/images/menu-right.png"));
        }
        assignMenuButtonIcon();        
                
        m_jPanelTitle.setVisible(false);
        
        m_jPanelContainer.add(new JPanel(), "<NULL>");

        showView("<NULL>");
        
        try {

            m_jPanelLeft.setViewportView(getScriptMenu(
            m_dlSystem.getResourceAsText("Menu.Root")));
            
            
            
        } catch (ScriptException e) {
            logger.log(Level.SEVERE, "Cannot read Menu.Root resource. Trying default menu.", e);
            try {
                m_jPanelLeft.setViewportView(getScriptMenu(
                    StringUtils.readResource("/com/openbravo/pos/templates/Menu.Root.txt")));
            } catch (    IOException | ScriptException ex) {
                logger.log(Level.SEVERE, "Cannot read default menu", ex);
            }
        }      
        
    }

    private Component getScriptMenu(String menutext) throws ScriptException {
        ScriptMenu menu = new ScriptMenu();

        ScriptEngine eng = ScriptFactory.getScriptEngine(ScriptFactory.BEANSHELL);
        eng.put("menu", menu);
        eng.eval(menutext);
        
        return menu.getTaskPane();
    }
    
    private void assignMenuButtonIcon() {
        jButton1.setIcon(m_jPanelLeft.isVisible()
                ? menu_close
                : menu_open);
    }
    
    /**
     *
     */
    public class ScriptMenu {
        private final JXTaskPaneContainer taskPane;
        
        private ScriptMenu() {
            taskPane = new JXTaskPaneContainer();
            taskPane.applyComponentOrientation(getComponentOrientation());
        }
        
        /**
         *
         * @param key
         * @return
         */
        public ScriptGroup addGroup(String key) {
            
            ScriptGroup group = new ScriptGroup(key);
            taskPane.add(group.getTaskGroup());
            return group;
        }
        
            
        /**
         *
         * @return
        */
        public JXTaskPaneContainer getTaskPane() {            
            return taskPane;
        }
    }
    
    /**
     *
     */
    public class ScriptGroup {

        private final JXTaskPane taskGroup;
        
        private ScriptGroup(String key) {
            
            taskGroup = new JXTaskPane();
            taskGroup.applyComponentOrientation(getComponentOrientation());
            taskGroup.setFocusable(false);
            taskGroup.setRequestFocusEnabled(false);
            taskGroup.setTitle(AppLocal.getIntString(key));
            taskGroup.setVisible(false);
//            taskGroup.setFont(new java.awt.Font("Arial",0,16));
        }
        
        /**
         *
         * @param icon
         * @param key
         * @param classname
         */
        public void addPanel(String icon, String key, String classname) {    
            addAction(new MenuPanelAction(m_appview, icon, key, classname));
        }        

        /**
         *
         * @param icon
         * @param key
         * @param classname
         */
        public void addExecution(String icon, String key, String classname) {
            addAction(new MenuExecAction(m_appview, icon, key, classname));
        }        

        /**
         *
         * @param icon
         * @param key
         * @param classname
         * @return
         */
        public ScriptSubmenu addSubmenu(String icon, String key, String classname) {
            ScriptSubmenu submenu = new ScriptSubmenu(key); 
            m_aPreparedViews.put(classname, new JPanelMenu(submenu.getMenuDefinition()));
            addAction(new MenuPanelAction(m_appview, icon, key, classname));
            return submenu;
        }        

        /**
         *
         */
        public void addChangePasswordAction() {            
            addAction(new ChangePasswordAction("/com/openbravo/images/password.png", "Menu.ChangePassword"));
        }       

        /**
         *
         */
        public void addExitAction() {            
            addAction(new ExitAction("/com/openbravo/images/logout.png", "Menu.Exit"));
        }
        
        private void addAction(Action act) {
            
            if (m_appuser.hasPermission((String) act.getValue(AppUserView.ACTION_TASKNAME))) {
                Component c = taskGroup.add(act);
                c.applyComponentOrientation(getComponentOrientation());
                c.setFocusable(false);
                
                taskGroup.setVisible(true);

                if (m_actionfirst == null) {
                    m_actionfirst = act;
                }
            }
        }
        

        /**
         *
         * @return
         */
        public JXTaskPane getTaskGroup() {
            return taskGroup;
        }   
    }
    
    /**
     *
     */
    public class ScriptSubmenu {
        private final MenuDefinition menudef;
        
        private ScriptSubmenu(String key) {
            menudef = new MenuDefinition(key);
        }
        
        /**
         *
         * @param key
         */
        public void addTitle(String key) {
            
            menudef.addMenuTitle(key);
        }
        
        /**
         *
         * @param icon
         * @param key
         * @param classname
         */
        public void addPanel(String icon, String key, String classname) {
            
            menudef.addMenuItem(new MenuPanelAction(m_appview, icon, key, classname));
        }

        /**
         *
         * @param icon
         * @param key
         * @param classname
         */
        public void addExecution(String icon, String key, String classname) {
            menudef.addMenuItem(new MenuExecAction(m_appview, icon, key, classname));
        }                

        /**
         *
         * @param icon
         * @param key
         * @param classname
         * @return
         */
        public ScriptSubmenu addSubmenu(String icon, String key, String classname) {
            
            ScriptSubmenu submenu = new ScriptSubmenu(key); 
            m_aPreparedViews.put(classname, new JPanelMenu(submenu.getMenuDefinition()));
            menudef.addMenuItem(new MenuPanelAction(m_appview, icon, key, classname));
            return submenu;
        } 

        /**
         *
         */
        public void addChangePasswordAction() {            
            menudef.addMenuItem(new ChangePasswordAction("/com/openbravo/images/password.png", "Menu.ChangePassword"));
        }        

        /**
         *
         */
        public void addExitAction() {
            menudef.addMenuItem(new ExitAction("/com/openbravo/images/logout.png", "Menu.Exit"));
        }

        /**
         *
         * @return
         */
        public MenuDefinition getMenuDefinition() {
            return menudef;
        }
    }
    
    private void setMenuVisible(boolean value) {
        
        m_jPanelLeft.setVisible(value);
        assignMenuButtonIcon();
        revalidate();
    }
        
    /**
     *
     * @return
     */
    public JComponent getNotificator() {
        return m_principalnotificator;
    }
    
    /**
     *
     */
    public void activate() {
        
        setMenuVisible(getBounds().width > 800);
        
        if (m_actionfirst != null) {
            m_actionfirst.actionPerformed(null);
            m_actionfirst = null;
        }
    }
    
    /**
     *
     * @return
     */
    public boolean deactivate() {
        if (m_jLastView == null) {
            return true;
        } else if (m_jLastView.deactivate()) {
            m_jLastView = null;
            showView("<NULL>");       
            return true;
        } else {
            return false;
        }
        
    }
    
    private class ExitAction extends AbstractAction {
        
        public ExitAction(String icon, String keytext) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(AppUserView.ACTION_TASKNAME, keytext);
        }
        @Override
        public void actionPerformed(ActionEvent evt) {
            m_appview.closeAppView();
        }
    }
    
    /**
     *
     */
    public void exitToLogin() {
        m_appview.closeAppView();
    }    
    
    
    private class ChangePasswordAction extends AbstractAction {
        public ChangePasswordAction(String icon, String keytext) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(AppUserView.ACTION_TASKNAME, keytext);

        }
        @Override
        public void actionPerformed(ActionEvent evt) {
                       
            String sNewPassword = Hashcypher.changePassword(JPrincipalApp.this, m_appuser.getPassword());
            if (sNewPassword != null) {
                try {
                    
                    m_dlSystem.execChangePassword(new Object[] {sNewPassword, m_appuser.getId()});
                    m_appuser.setPassword(sNewPassword);
                } catch (BasicException e) {
                    JMessageDialog.showMessage(JPrincipalApp.this
                        , new MessageInf(MessageInf.SGN_WARNING
                        , AppLocal.getIntString("message.cannotchangepassword")));             
                }
            }
        }
    }
 
    
    
    private void showView(String sView) {
        CardLayout cl = (CardLayout)(m_jPanelContainer.getLayout());
        cl.show(m_jPanelContainer, sView);       
    }
    
    
    
    private void showView(String sView, boolean is_back_btn_clicked) {
        if(this.list_title.size()-1 > 0){
            this.list_title.remove(this.list_title.size()-1);
        }
        m_appview.getAppUserView().showTask(sView);
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public AppUser getUser() {
        return m_appuser;
    }
    
    /**
     *
     * @param sTaskClass
     */
    @Override
    public void showTask(String sTaskClass) {
         
        customerInfo = new CustomerInfo("");
        customerInfo.setName("");
         
        m_appview.waitCursorBegin();       
         
        if (m_appuser.hasPermission(sTaskClass)) {            
            
            JPanelView m_jMyView = (JPanelView) m_aCreatedViews.get(sTaskClass);

            if (m_jLastView == null 
                || (m_jMyView != m_jLastView 
                && m_jLastView.deactivate())) {

                if (m_jMyView == null) {   
                    
                    m_jMyView = m_aPreparedViews.get(sTaskClass);

                    if (m_jMyView == null) {   

                        try {
                            m_jMyView = (JPanelView) m_appview.getBean(sTaskClass);
                        } catch (BeanFactoryException e) {
                            m_jMyView = new JPanelNull(m_appview, e);
                        }
                    }
                    
                    m_jMyView.getComponent().applyComponentOrientation(getComponentOrientation());
                    m_jPanelContainer.add(m_jMyView.getComponent(), sTaskClass);
                    m_aCreatedViews.put(sTaskClass, m_jMyView);
                    
                }
                
                try {
                    m_jMyView.activate();
                } catch (BasicException e) {
                    JMessageDialog.showMessage(this
                        , new MessageInf(MessageInf.SGN_WARNING
                        , AppLocal.getIntString("message.notactive"), e));            
                }

                m_jLastView = m_jMyView;

                setMenuVisible(getBounds().width > 800);
                setMenuVisible(false);
                showView(sTaskClass); 
                this.list_layout.add(sTaskClass);
                m_jPanelTitle.setVisible(true);
                // Kiem tra xem neu menu title in MENU_VALUES (Gan home panel)
                set_breacrumd(sTaskClass);
                this.jPanel4.setBounds(0, 0, 600, 50);
                this.jPanel4.setVisible(true);
            }
        } else  {

            JMessageDialog.showMessage(this
                , new MessageInf(MessageInf.SGN_WARNING
                , AppLocal.getIntString("message.notpermissions")));            
        }
        m_appview.waitCursorEnd();       
    }
    
    /**
     *
     * @param sTaskClass
     */
    @Override
    public void executeTask(String sTaskClass) {
        
        m_appview.waitCursorBegin();       

        if (m_appuser.hasPermission(sTaskClass)) {
            try {
                ProcessAction myProcess = (ProcessAction) m_appview.getBean(sTaskClass);

                try {
                    MessageInf m = myProcess.execute();    
                    if (m != null) {
                        JMessageDialog.showMessage(JPrincipalApp.this, m);
                    }
                } catch (BasicException eb) {
                    JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(eb));            
                }
            } catch (BeanFactoryException e) {
                JMessageDialog.showMessage(JPrincipalApp.this
                    , new MessageInf(MessageInf.SGN_WARNING
                    , AppLocal.getIntString("label.LoadError"), e));            
            }                    
        } else  {
            JMessageDialog.showMessage(JPrincipalApp.this
                , new MessageInf(MessageInf.SGN_WARNING
                , AppLocal.getIntString("message.notpermissions")));            
        }
        m_appview.waitCursorEnd();    
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        m_jPanelLeft = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        m_jPanelRight = new javax.swing.JPanel();
        m_jPanelTitle = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_back = new javax.swing.JButton();
        Stock_sc = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Config_sc = new javax.swing.JButton();
        Salessc = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        m_jPanelContainer = new javax.swing.JPanel();

        m_jTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        m_jTitle.setForeground(new java.awt.Color(0, 168, 223));
        m_jTitle.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        m_jTitle.setMaximumSize(new java.awt.Dimension(100, 35));
        m_jTitle.setMinimumSize(new java.awt.Dimension(30, 25));
        m_jTitle.setPreferredSize(new java.awt.Dimension(100, 35));

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        m_jPanelLeft.setBackground(new java.awt.Color(102, 102, 102));
        m_jPanelLeft.setBorder(null);
        m_jPanelLeft.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jPanelLeft.setPreferredSize(new java.awt.Dimension(250, 2));
        jPanel1.add(m_jPanelLeft, java.awt.BorderLayout.LINE_START);

        jPanel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(45, 45));

        jButton1.setToolTipText(AppLocal.getIntString("tooltip.menu")); // NOI18N
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setIconTextGap(0);
        jButton1.setMargin(new java.awt.Insets(10, 2, 10, 2));
        jButton1.setMaximumSize(new java.awt.Dimension(45, 32224661));
        jButton1.setMinimumSize(new java.awt.Dimension(32, 32));
        jButton1.setPreferredSize(new java.awt.Dimension(36, 45));
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_END);

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        m_jPanelRight.setPreferredSize(new java.awt.Dimension(200, 40));
        m_jPanelRight.setLayout(new java.awt.BorderLayout());

        m_jPanelTitle.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(233, 25));

        btn_back.setBackground(new java.awt.Color(0, 168, 223));
        btn_back.setText("Back");
        btn_back.setActionCommand("back");
        btn_back.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_back.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btn_back.setDefaultCapable(false);
        btn_back.setFocusPainted(false);
        btn_back.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_back.setMargin(new java.awt.Insets(20, 14, 2, 14));
        btn_back.setMaximumSize(new java.awt.Dimension(90, 30));
        btn_back.setPreferredSize(new java.awt.Dimension(80, 30));
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        Stock_sc.setText("Stock");
        Stock_sc.setMaximumSize(new java.awt.Dimension(79, 23));
        Stock_sc.setMinimumSize(new java.awt.Dimension(79, 23));
        Stock_sc.setPreferredSize(new java.awt.Dimension(79, 23));
        Stock_sc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stock_scActionPerformed(evt);
            }
        });

        jButton3.setText("Customer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Config_sc.setText("Setting");
        Config_sc.setMaximumSize(new java.awt.Dimension(79, 23));
        Config_sc.setMinimumSize(new java.awt.Dimension(79, 23));
        Config_sc.setPreferredSize(new java.awt.Dimension(79, 23));
        Config_sc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Config_scActionPerformed(evt);
            }
        });

        Salessc.setText("Sales");
        Salessc.setMaximumSize(new java.awt.Dimension(79, 23));
        Salessc.setPreferredSize(new java.awt.Dimension(79, 23));
        Salessc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesscActionPerformed(evt);
            }
        });

        jPanel4.setMinimumSize(new java.awt.Dimension(600, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(600, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Salessc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stock_sc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Config_sc, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Salessc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Stock_sc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Config_sc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        m_jPanelTitle.add(jPanel3, java.awt.BorderLayout.CENTER);

        m_jPanelRight.add(m_jPanelTitle, java.awt.BorderLayout.NORTH);

        m_jPanelContainer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jPanelContainer.setLayout(new java.awt.CardLayout());
        m_jPanelRight.add(m_jPanelContainer, java.awt.BorderLayout.CENTER);

        add(m_jPanelRight, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    setMenuVisible(!m_jPanelLeft.isVisible());
    
}//GEN-LAST:event_jButton1ActionPerformed

    //Render chuoi cua breadcrumd
    private void set_breacrumd(String taskClass){
        this.jPanel4.removeAll();
        this.jPanel4.setLayout(new FlowLayout());
        
        get_links(taskClass);
    }
    
    private void get_links(String taskClass){
        for (int i = 0; i < SUB_MENU_VALUES.length; i++) {
            if (SUB_MENU_VALUES[i][0] == taskClass) {
                
                if (SUB_MENU_VALUES[i][1] != "null") {
                    get_links(SUB_MENU_VALUES[i][1]);
                }
                JLabel link = new JLabel();
                link.setBounds(0, 50 , 50, 30);
                String[] title = get_alchor(taskClass);
                link.setText(title[0]);
                link.addMouseListener(new MouseAdapter(){  
                    @Override
                    public void mouseClicked(MouseEvent e){  
                       showView(title[1], true);
                    }  
                }); 
                this.jPanel4.add(link);
            }
        }
    }
    private String[] get_alchor(String taskClass){
        for (int i = 0; i < MENU_VALUES.length; i++) {
            if (MENU_VALUES[i][1] == taskClass) {
                return MENU_VALUES[i];
            } 
        }
        return null;
    }
    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        if (this.list_layout.size() > 1) {
            showView(this.list_layout.get(this.list_layout.size()-2), true);
            this.list_layout.remove(this.list_layout.size()-1);

            if (this.list_layout.size()==1){
                this.btn_back.setEnabled(false);                
            }

        }
    }//GEN-LAST:event_btn_backActionPerformed

    private void SalesscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesscActionPerformed
        m_appview.getAppUserView().showTask("com.openbravo.pos.sales.JPanelTicketSales");
        
        
    }//GEN-LAST:event_SalesscActionPerformed

    private void Stock_scActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stock_scActionPerformed
        // TODO add your handling code here:
        m_appview.getAppUserView().showTask("com.openbravo.pos.forms.MenuStockManagement");
    }//GEN-LAST:event_Stock_scActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        m_appview.getAppUserView().showTask("com.openbravo.pos.forms.MenuCustomers");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Config_scActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Config_scActionPerformed
        // TODO add your handling code here:
        m_appview.getAppUserView().showTask("com.openbravo.pos.config.JPanelConfiguration");
    }//GEN-LAST:event_Config_scActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Config_sc;
    private javax.swing.JButton Salessc;
    private javax.swing.JButton Stock_sc;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel m_jPanelContainer;
    private javax.swing.JScrollPane m_jPanelLeft;
    private javax.swing.JPanel m_jPanelRight;
    private javax.swing.JPanel m_jPanelTitle;
    private javax.swing.JLabel m_jTitle;
    // End of variables declaration//GEN-END:variables
    
}
