/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helper.J;
import helpers.Func;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import models.Adm_user_bean;
import models.DBConnQueue;
import models.PMS_Queue_Name_Bean;
import models.PMS_Queue_Type_Bean;

/**
 *
 * @author umarmukhtar
 */
public class MainMenuQueue extends javax.swing.JFrame {

    private static int limit = 100;
    private static int cols1 = 3;
    private static int cols2 = 5;
    private static int cols3 = 8;
    private static ArrayList<ArrayList<String>> data3 = new ArrayList<ArrayList<String>>();
    
    /**
     * Creates new form MainMenuQueue
     */
    public MainMenuQueue(String hostname, int port) {
        initComponents();
        
        DBConnQueue.setConfigQueue(hostname, port);
        
        setList1();
        
        setComboList1();
        setList2();
        
        setComboList2();
        setList3();
    }
    
    private static void setComboList2() {
        String sql = "SELECT * "
                + "FROM pms_queue_name ";
        DBConnQueue dbc = new DBConnQueue();
        ArrayList<ArrayList<String>> data = dbc.getQuery(sql);
        
        cbx_queuename.removeAllItems();
        for (int i = 0; i < data.size(); i++) {
            PMS_Queue_Name_Bean pqnb = new PMS_Queue_Name_Bean();
            pqnb.setQueue_type(data.get(i).get(0));
            pqnb.setQueue_name(data.get(i).get(1));
            pqnb.setQueue_description(data.get(i).get(2));
            pqnb.setUser_id(data.get(i).get(3));
            int quota = 0;
            try {
                quota = Integer.parseInt(data.get(i).get(4));
            } catch (Exception e) {
                quota = 0;
            }
            pqnb.setQuota(quota);
            cbx_queuename.addItem(pqnb);
        }
        cbx_queuename.setSelectedIndex(0);
        
        String sql2 = "SELECT au.user_id, au.health_facility_code, "
                + "au.user_name, aua.discipline_code, aua.subdiscipline_code "
                + "FROM adm_user au, adm_user_access aua "
                + "WHERE au.user_id = aua.user_id ";
        DBConnQueue dbc2 = new DBConnQueue();
        ArrayList<ArrayList<String>> data2 = dbc2.getQuery(sql2);
        
        cbx_staffassigned.removeAllItems();
        for (int i = 0; i < data2.size(); i++) {
            Adm_user_bean au = new Adm_user_bean();
            au.setUser_id(data2.get(i).get(0));
            au.setHealth_facility_code(data2.get(i).get(1));
            au.setUser_name(data2.get(i).get(2));
            au.setDiscipline(data2.get(i).get(3));
            au.setSubdiscipline(data2.get(i).get(4));
            cbx_staffassigned.addItem(au);
        }
        cbx_staffassigned.setSelectedIndex(0);
    }
    
    private static void setComboList1() {
        String sql = "SELECT * "
                + "FROM pms_queue_type ";
        DBConnQueue dbc = new DBConnQueue();
        ArrayList<ArrayList<String>> data = dbc.getQuery(sql);
        
        cbx_qtc.removeAllItems();
        for (int i = 0; i < data.size(); i++) {
            PMS_Queue_Type_Bean pqt = new PMS_Queue_Type_Bean();
            pqt.setQueue_type(data.get(i).get(0));
            pqt.setQueue_type_name(data.get(i).get(1));
            pqt.setStatus(data.get(i).get(2));
            cbx_qtc.addItem(pqt);
        }
        cbx_qtc.setSelectedIndex(0);
        
        String sql2 = "SELECT * "
                + "FROM adm_user ";
        DBConnQueue dbc2 = new DBConnQueue();
        ArrayList<ArrayList<String>> data2 = dbc2.getQuery(sql2);
        
        cbx_userid.removeAllItems();
        for (int i = 0; i < data2.size(); i++) {
            Adm_user_bean au = new Adm_user_bean();
            au.setUser_id(data2.get(i).get(0));
            au.setHealth_facility_code(data2.get(i).get(1));
            au.setUser_name(data2.get(i).get(3));
            cbx_userid.addItem(au);
        }
        cbx_userid.setSelectedIndex(0);
    }
    
    private void setList1() {
        
        txt_qtc.setText("");
        txt_qtd.setText("");
        cbx_status.setSelectedIndex(0);
        
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < cols1; j++) {
                tbl_qt.setValueAt("", i, j);
            }
        }
        
        String sql = "SELECT * "
                + "FROM pms_queue_type ";
        DBConnQueue dbc = new DBConnQueue();
        ArrayList<ArrayList<String>> data = dbc.getQuery(sql);
        
        for (int i = 0; i < limit && i < data.size(); i++) {
            tbl_qt.setValueAt(data.get(i).get(0), i, 0);
            tbl_qt.setValueAt(data.get(i).get(1), i, 1);
            tbl_qt.setValueAt(data.get(i).get(2), i, 2);
        }
    }
    
    private static void setList2() {
        
        cbx_qtc.setSelectedIndex(0);
        txt_qnc.setText("");
        cbx_userid.setSelectedIndex(0);
        txt_desc.setText("");
        txt_quota.setText("0");
        cbx_status1.setSelectedIndex(0);
        
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < cols2; j++) {
                tbl_qt1.setValueAt("", i, j);
            }
        }
        
        String sql = "SELECT * "
                + "FROM pms_queue_name ";
        DBConnQueue dbc = new DBConnQueue();
        ArrayList<ArrayList<String>> data = dbc.getQuery(sql);
        
        for (int i = 0; i < limit && i < data.size(); i++) {
            tbl_qt1.setValueAt(data.get(i).get(0), i, 0);
            tbl_qt1.setValueAt(data.get(i).get(1), i, 1);
            tbl_qt1.setValueAt(data.get(i).get(3), i, 2);
            tbl_qt1.setValueAt(data.get(i).get(2), i, 3);
            tbl_qt1.setValueAt(data.get(i).get(4), i, 4);
        }
    }
            
    private static void setList3() {
        
        txt_hfc.setText("");
        txt_discipline.setText("");
        txt_subdiscipline.setText("");
        cbx_queuename.setSelectedIndex(0);
        cbx_staffassigned.setSelectedIndex(0);
        cbx_status2.setSelectedIndex(0);
        Date date = new Date();
        dc_startdate.setDate(date);
        dc_enddate.setDate(date);
        
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < cols3; j++) {
                tbl_qt2.setValueAt("", i, j);
            }
        }
        
        String sql = "SELECT * "
                + "FROM pms_queue_list pql, adm_user au "
                + "WHERE pql.user_id = au.user_id ";
        DBConnQueue dbc = new DBConnQueue();
        data3.clear();
        data3 = dbc.getQuery(sql);
        
        for (int i = 0; i < limit && i < data3.size(); i++) {
            tbl_qt2.setValueAt(data3.get(i).get(1), i, 0);
            tbl_qt2.setValueAt(data3.get(i).get(12), i, 1);
            tbl_qt2.setValueAt(data3.get(i).get(5), i, 2);
            tbl_qt2.setValueAt(data3.get(i).get(6), i, 3);
            tbl_qt2.setValueAt(data3.get(i).get(7), i, 4);
            tbl_qt2.setValueAt(data3.get(i).get(3), i, 5);
            tbl_qt2.setValueAt(data3.get(i).get(4), i, 6);
            tbl_qt2.setValueAt(data3.get(i).get(8), i, 7);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_qtc = new javax.swing.JTextField();
        txt_qtd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_qt = new javax.swing.JTable();
        cbx_status = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbx_qtc = new javax.swing.JComboBox();
        cbx_userid = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txt_desc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_quota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbx_status1 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_qt1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_qnc = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbx_queuename = new javax.swing.JComboBox();
        cbx_staffassigned = new javax.swing.JComboBox();
        txt_hfc = new javax.swing.JTextField();
        txt_discipline = new javax.swing.JTextField();
        dc_startdate = new com.toedter.calendar.JDateChooser();
        dc_enddate = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_qt2 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txt_subdiscipline = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbx_status2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Maintain Queue Type"));

        jLabel1.setText("Queue Type Code : ");

        jLabel2.setText("Queue Type Description : ");

        jLabel3.setText("Status : ");

        tbl_qt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Queue Type Code", "Description", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_qt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_qtMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_qt);

        cbx_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbx_status, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_qtc, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(541, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_qtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_qtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbx_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Maintain Queue Type", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Maintain Queue Name"));

        jLabel4.setText("Queue Type Code : ");

        jLabel5.setText("Queue Name Code : ");

        cbx_qtc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_qtcItemStateChanged(evt);
            }
        });
        cbx_qtc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_qtcActionPerformed(evt);
            }
        });

        cbx_userid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_userid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_useridItemStateChanged(evt);
            }
        });

        jLabel6.setText("Description : ");

        jLabel7.setText("Quota : ");

        txt_quota.setText("0");

        jLabel8.setText("Status : ");

        cbx_status1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        tbl_qt1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Queue Type Code", "Queue Name Code", "Staff ID", "Description", "Quota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_qt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_qt1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_qt1);

        jLabel9.setText("Staff (optional) : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_qtc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_userid, 0, 249, Short.MAX_VALUE)
                            .addComponent(txt_qnc)
                            .addComponent(txt_desc)
                            .addComponent(txt_quota, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_status1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbx_qtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_qnc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbx_userid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_quota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbx_status1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Maintain Queue Name", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Maintain Queue List"));

        jLabel10.setText("Queue Name : ");

        jLabel11.setText("Select staff to be assigned : ");

        jLabel12.setText("Health facility : ");

        jLabel13.setText("Discipline : ");

        jLabel14.setText("Start date : ");

        jLabel15.setText("End date : ");

        cbx_queuename.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_queuename.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_queuenameItemStateChanged(evt);
            }
        });

        cbx_staffassigned.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_staffassigned.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_staffassignedItemStateChanged(evt);
            }
        });

        txt_hfc.setEditable(false);

        txt_discipline.setEditable(false);

        dc_startdate.setDateFormatString("yyyy-MM-dd");

        dc_enddate.setDateFormatString("yyyy-MM-dd");

        jButton9.setText("Back");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Update");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Delete");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tbl_qt2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Queue Name", "Staff Name", "Health Facility", "Discipline", "Sub-Discipline", "Start Date", "End Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_qt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_qt2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_qt2);

        jLabel16.setText("Sub-Discipline : ");

        txt_subdiscipline.setEditable(false);

        jLabel17.setText("Status : ");

        cbx_status2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_queuename, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_staffassigned, 0, 245, Short.MAX_VALUE)
                            .addComponent(txt_hfc)
                            .addComponent(txt_discipline)
                            .addComponent(dc_startdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dc_enddate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_subdiscipline)
                            .addComponent(cbx_status2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbx_queuename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_staffassigned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_discipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_subdiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dc_startdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dc_enddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_status2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Maintain Queue List", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_qtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_qtMouseClicked
        // TODO add your handling code here:
        int index = tbl_qt.getSelectedRow();
        String qtc = tbl_qt.getValueAt(index, 0).toString();
        String qtd = tbl_qt.getValueAt(index, 1).toString();
        String status = tbl_qt.getValueAt(index, 2).toString();

        txt_qtc.setText(qtc);
        txt_qtd.setText(qtd);
        Func.cmbSelectInput(cbx_status, status, false);
    }//GEN-LAST:event_tbl_qtMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String qtc = txt_qtc.getText();
        String qtd = txt_qtd.getText();
        String status = cbx_status.getSelectedItem().toString();

        String sql = "INSERT INTO pms_queue_type(queue_type, queue_type_name, status) "
        + "VALUES('"+qtc+"', '"+qtd+"', '"+status+"')";
        DBConnQueue dbc = new DBConnQueue();
        boolean stats = dbc.setQuery(sql);

        if (stats) {
            J.o("Success", "Add Success.", 1);
            setList1();
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String qtc = txt_qtc.getText();
        String qtd = txt_qtd.getText();
        String status = cbx_status.getSelectedItem().toString();

        String sql = "UPDATE pms_queue_type "
        + "SET queue_type_name = '" + qtd + "', "
        + "status = '" + status + "' "
        + "WHERE queue_type = '" + qtc + "' ";
        DBConnQueue dbc = new DBConnQueue();
        boolean stats = dbc.setQuery(sql);

        if (stats) {
            J.o("Success", "Update Success.", 1);
            setList1();
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String qtc = txt_qtc.getText();

        String sql = "DELETE FROM pms_queue_type "
        + "WHERE queue_type = '" + qtc + "' ";
        DBConnQueue dbc = new DBConnQueue();
        boolean stats = dbc.setQuery(sql);

        if (stats) {
            J.o("Success", "Delete Success.", 1);
            setList1();
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbx_qtcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_qtcItemStateChanged
        // TODO add your handling code here:
        PMS_Queue_Type_Bean pqt = (PMS_Queue_Type_Bean) cbx_qtc.getSelectedItem();
        String code = pqt.getQueue_type();
        if (code.toUpperCase().equals("PN".toUpperCase())) {
            Adm_user_bean au = (Adm_user_bean) cbx_userid.getSelectedItem();
            String name = au.getUser_name();
            txt_qnc.setText(name);
            txt_qnc.setEditable(false);
        } else {
            txt_qnc.setText("");
            txt_qnc.setEditable(true);
        }
    }//GEN-LAST:event_cbx_qtcItemStateChanged

    private void cbx_useridItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_useridItemStateChanged
        // TODO add your handling code here:
        PMS_Queue_Type_Bean pqt2 = (PMS_Queue_Type_Bean) cbx_qtc.getSelectedItem();
        String code = pqt2.getQueue_type();
        if (code.toUpperCase().equals("PN".toUpperCase())) {
            Adm_user_bean au = (Adm_user_bean) cbx_userid.getSelectedItem();
            String name = au.getUser_name();
            txt_qnc.setText(name);
            txt_qnc.setEditable(false);
        } else {
            //            txt_qnc.setText("");
            txt_qnc.setEditable(true);
        }
    }//GEN-LAST:event_cbx_useridItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        PMS_Queue_Type_Bean pqt = (PMS_Queue_Type_Bean) cbx_qtc.getSelectedItem();
        String queue_type = pqt.getQueue_type();
        String qnc = txt_qnc.getText();
        Adm_user_bean au = (Adm_user_bean) cbx_userid.getSelectedItem();
        String user_id = au.getUser_id();
        String queue_name = "";
        if (queue_type.toUpperCase().equals("PN".toUpperCase())) {
            queue_name = au.getUser_name();
        } else {
            queue_name = qnc;
        }
        String queue_desc = txt_desc.getText();
        int quota_temp = 0;
        try {
            quota_temp = Integer.parseInt(txt_quota.getText());
        } catch (Exception e) {
            quota_temp = 0;
            J.o("Invalid Quota", "Invalid number of quota!", 0);
            return;
        }
        String quota = quota_temp + "";

        String sql = "INSERT INTO pms_queue_name(queue_type, queue_name, "
        + "queue_description, user_id, quota) "
        + "VALUES('"+queue_type+"', '"+queue_name+"', '"+queue_desc+"', "
        + "'"+user_id+"', '"+quota+"') ";
        DBConnQueue dbc = new DBConnQueue();
        boolean isAdd = dbc.setQuery(sql);

        if (isAdd) {
            J.o("Success", "Add Success.", 1);
            setList2();
        } else {
            J.o("Failed", "Add Failed!", 0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        PMS_Queue_Type_Bean pqt = (PMS_Queue_Type_Bean) cbx_qtc.getSelectedItem();
        String queue_type = pqt.getQueue_type();
        String qnc = txt_qnc.getText();

        cbx_qtc.setEditable(false);
        txt_qnc.setEditable(false);

        Adm_user_bean au = (Adm_user_bean) cbx_userid.getSelectedItem();
        String user_id = au.getUser_id();
        String queue_name = "";
        if (queue_type.toUpperCase().equals("PN".toUpperCase())) {
            queue_name = au.getUser_name();
        } else {
            queue_name = qnc;
        }
        String queue_desc = txt_desc.getText();
        int quota_temp = 0;
        try {
            quota_temp = Integer.parseInt(txt_quota.getText());
        } catch (Exception e) {
            quota_temp = 0;
            J.o("Invalid Quota", "Invalid number of quota!", 0);
            return;
        }
        String quota = quota_temp + "";

        String sql = "UPDATE pms_queue_name "
            + "SET queue_description = '"+queue_desc+"', "
            + "user_id = '"+user_id+"', "
            + "quota = '"+quota+"' "
            + "WHERE queue_type = '"+queue_type+"' "
            + "AND queue_name = '"+queue_name+"' ";
        DBConnQueue dbc = new DBConnQueue();
        boolean isUpdate = dbc.setQuery(sql);

        if (isUpdate) {
            J.o("Success", "Update Success.", 1);
            cbx_qtc.setEditable(true);
            txt_qnc.setEditable(true);
            setList2();
        } else {
            J.o("Failed", "Update Failed!", 0);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        PMS_Queue_Type_Bean pqt = (PMS_Queue_Type_Bean) cbx_qtc.getSelectedItem();
        String queue_type = pqt.getQueue_type();
        String queue_name = txt_qnc.getText();

        String sql = "DELETE FROM pms_queue_name "
        + "WHERE queue_type = '" + queue_type + "' "
        + "AND queue_name = '" + queue_name + "' ";
        DBConnQueue dbc = new DBConnQueue();
        boolean isDelete = dbc.setQuery(sql);

        if (isDelete) {
            J.o("Success", "Delete Success.", 1);
            setList2();
        } else {
            J.o("Failed", "Delete Failed!", 0);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbl_qt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_qt1MouseClicked
        // TODO add your handling code here:
        int index = tbl_qt1.getSelectedRow();
        String qtc = tbl_qt1.getValueAt(index, 0).toString();
        String qnc = tbl_qt1.getValueAt(index, 1).toString();
        String user_id = tbl_qt1.getValueAt(index, 2).toString();
        String desc = tbl_qt1.getValueAt(index, 3).toString();
        String quota = tbl_qt1.getValueAt(index, 4).toString();

        Func.cmbSelectInput(cbx_qtc, qtc, true);
        txt_qnc.setText(qnc);
        Func.cmbSelectInput(cbx_userid, user_id, true);
        txt_desc.setText(desc);
        txt_quota.setText(quota);

        cbx_qtc.setEditable(false);
        txt_qnc.setEditable(false);
    }//GEN-LAST:event_tbl_qt1MouseClicked

    private void cbx_qtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_qtcActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbx_qtcActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            PMS_Queue_Name_Bean pqnb = (PMS_Queue_Name_Bean) cbx_queuename.getSelectedItem();
            Adm_user_bean aub = (Adm_user_bean) cbx_staffassigned.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start_date_temp = dc_startdate.getDate();
            Date end_date_temp = dc_enddate.getDate();
            
            String queue_type = pqnb.getQueue_type();
            String queue_name = pqnb.getQueue_name();
            String user_id = aub.getUser_id();
            String start_date = sdf.format(start_date_temp);
            String end_date = sdf.format(end_date_temp);
            String hfc = txt_hfc.getText();
            String dis = txt_discipline.getText();
            String subdis = txt_subdiscipline.getText();
            String status = cbx_status2.getSelectedItem().toString();

            String sql = "INSERT INTO pms_queue_list(queue_type, queue_name, "
                    + "user_id, start_date, end_date, hfc_cd, discipline_cd, "
                    + "sub_discipline_cd, status) "
                    + "VALUES('" + queue_type + "', '" + queue_name + "', '" + user_id + "', "
                    + "'" + start_date + "', '" + end_date + "', '" + hfc + "', "
                    + "'" + dis + "', '" + subdis + "', '" + status + "') ";
            DBConnQueue dbc = new DBConnQueue();
            boolean isAdd = dbc.setQuery(sql);

            if (isAdd) {
                J.o("Success", "Add Success.", 1);
                setList3();
            } else {
                J.o("Failed", "Add Failed!", 0);
            }
            
        } catch (Exception e) {
            J.o("Error", "Error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
            
            PMS_Queue_Name_Bean pqnb = (PMS_Queue_Name_Bean) cbx_queuename.getSelectedItem();
            String queue_type = pqnb.getQueue_type();
            String queue_name = pqnb.getQueue_name();

            Adm_user_bean aub = (Adm_user_bean) cbx_staffassigned.getSelectedItem();
            String user_id = aub.getUser_id();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start_date_temp = dc_startdate.getDate();
            Date end_date_temp = dc_enddate.getDate();
            String start_date = sdf.format(start_date_temp);
            String end_date = sdf.format(end_date_temp);
            String hfc = txt_hfc.getText();
            String dis = txt_discipline.getText();
            String subdis = txt_subdiscipline.getText();
            String status = cbx_status2.getSelectedItem().toString();
            
            String sql = "UPDATE pms_queue_list "
                    + "SET start_date = '" + start_date + "', "
                    + "end_date = '" + end_date + "', "
                    + "hfc_cd = '" + hfc + "', "
                    + "discipline_cd = '" + dis + "', "
                    + "sub_discipline_cd = '" + subdis + "', "
                    + "status = '" + status + "' "
                    + "WHERE queue_type = '" + queue_type + "' "
                    + "AND queue_name = '" + queue_name + "' "
                    + "AND user_id = '" + user_id + "' ";
            DBConnQueue dbc = new DBConnQueue();
            boolean isUpdate = dbc.setQuery(sql);

            if (isUpdate) {
                J.o("Success", "Update Success.", 1);
                setList3();
            } else {
                J.o("Failed", "Update Failed!", 0);
            }
            
        } catch (Exception e) {
            J.o("Error", "Error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        try {
            
            PMS_Queue_Name_Bean pqnb = (PMS_Queue_Name_Bean) cbx_queuename.getSelectedItem();
            String queue_type = pqnb.getQueue_type();
            String queue_name = pqnb.getQueue_name();
            
            Adm_user_bean aub = (Adm_user_bean) cbx_staffassigned.getSelectedItem();
            String user_id = aub.getUser_id();
            
            String sql = "DELETE FROM pms_queue_list "
                    + "WHERE queue_type = '" + queue_type + "' "
                    + "AND queue_name = '" + queue_name + "' "
                    + "AND user_id = '" + user_id + "' ";
            DBConnQueue dbc = new DBConnQueue();
            boolean isDelete = dbc.setQuery(sql);

            if (isDelete) {
                J.o("Success", "Delete Success.", 1);
                setList3();
            } else {
                J.o("Failed", "Delete Failed!", 0);
            }
            
        } catch (Exception e) {
            J.o("Error", "Error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tbl_qt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_qt2MouseClicked
        // TODO add your handling code here:
        try {
            int index = tbl_qt2.getSelectedRow();
            ArrayList<String> data = data3.get(index);

            txt_hfc.setText(data.get(5));
            txt_discipline.setText(data.get(6));
            txt_subdiscipline.setText(data.get(7));

            String queue_name = "(" + data.get(0) + ") " + data.get(1);
            Func.cmbSelectInput(cbx_queuename, queue_name, false);

            String staff_assigned = "(" + data.get(9) + ") " + data.get(12);
            Func.cmbSelectInput(cbx_staffassigned, staff_assigned, false);

            Func.cmbSelectInput(cbx_status2, data.get(8), false);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDate = data.get(3);
            String endDate = data.get(4);
            Date start_date = formatter.parse(startDate);
            Date end_date = formatter.parse(endDate);
            dc_startdate.setDate(start_date);
            dc_enddate.setDate(end_date);
            
        } catch (Exception e) {
            J.o("Error", "Error: "+e.getMessage(), 0);
        }
    }//GEN-LAST:event_tbl_qt2MouseClicked

    private void cbx_staffassignedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_staffassignedItemStateChanged
        // TODO add your handling code here:
        Adm_user_bean aub = (Adm_user_bean) cbx_staffassigned.getSelectedItem();
        try {
            txt_hfc.setText(aub.getHealth_facility_code());
            txt_discipline.setText(aub.getDiscipline());
            txt_subdiscipline.setText(aub.getSubdiscipline());
        } catch (Exception e) {
            txt_hfc.setText("");
            txt_discipline.setText("");
        }
    }//GEN-LAST:event_cbx_staffassignedItemStateChanged

    private void cbx_queuenameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_queuenameItemStateChanged
        // TODO add your handling code here:
        PMS_Queue_Name_Bean pqnb = (PMS_Queue_Name_Bean) cbx_queuename.getSelectedItem();
        try {
            String queue_type = pqnb.getQueue_type();
            
            if (queue_type.toUpperCase().equals("PN".toUpperCase())) {
                String user_id = pqnb.getUser_id();
                Func.cmbSelectInput(cbx_staffassigned, user_id, true);
            }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbx_queuenameItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenuQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenuQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenuQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuQueue(DBConnQueue.getHostname(), DBConnQueue.getPort()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JComboBox cbx_qtc;
    protected static javax.swing.JComboBox cbx_queuename;
    protected static javax.swing.JComboBox cbx_staffassigned;
    protected static javax.swing.JComboBox cbx_status;
    protected static javax.swing.JComboBox cbx_status1;
    protected static javax.swing.JComboBox cbx_status2;
    protected static javax.swing.JComboBox cbx_userid;
    protected static com.toedter.calendar.JDateChooser dc_enddate;
    protected static com.toedter.calendar.JDateChooser dc_startdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    protected static javax.swing.JTable tbl_qt;
    protected static javax.swing.JTable tbl_qt1;
    protected static javax.swing.JTable tbl_qt2;
    protected static javax.swing.JTextField txt_desc;
    protected static javax.swing.JTextField txt_discipline;
    protected static javax.swing.JTextField txt_hfc;
    protected static javax.swing.JTextField txt_qnc;
    protected static javax.swing.JTextField txt_qtc;
    protected static javax.swing.JTextField txt_qtd;
    protected static javax.swing.JTextField txt_quota;
    protected static javax.swing.JTextField txt_subdiscipline;
    // End of variables declaration//GEN-END:variables
}
