/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorto;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author engmu
 */
/// الاستقبال
public class PatientPanel extends javax.swing.JPanel {

    Vector<String> priceclincname = new Vector();// 
    Vector<String> idclincname = new Vector();
    Vector<String> iddoctors = new Vector();
    Vector<String> selectdoctors = new Vector();
    

    /**
     * Creates new form PatientPanel
     */
    public PatientPanel() {
        initComponents();
        Tools.setcolor(this);
        setItemcombospecial();
        updateTable();
        try {
            priseclinic.setText(priceclincname.get(combospecial.getSelectedIndex()));
        } catch (Exception ex) {
        }
        gettxtDoctorName();
        txtvisitdate.setText(Tools.date());
        this.setBackground(new Color(173,216,230));

    }

    public void setItemcombospecial() {// هذه دالة وضع العيادات في خانة اختيار عيادة
        combospecial.removeAllItems();
        String statement = "SELECT * FROM clincname";
        ResultSet rs = Tools.select_Query(statement);
        try {
            while (rs.next()) {
                idclincname.add(rs.getString(1));// لمعرفة رقم العيادة او لتخزين ارقام العيادات التي زارها
                combospecial.addItem(rs.getString(2));// لوضع العيادات كعناصر في الخانة 
                priceclincname.add(rs.getString(3));/// اسعار العيادات يمكن
                gettxtDoctorName();
            }
        } catch (Exception ex) {
        }
        Tools.CloseConnection();
    }

    public void updateTable() {
        try {
            // الاستعلام عن اسعار العيادات وسمائها خدمات العيادات وليس العيادات
            String statement = "SELECT name, price FROM clincservice WHERE id = " + idclincname.get(combospecial.getSelectedIndex()) + " ;";
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Tools.tableUpdate(model, statement, 2);/// تحديث ووضع الاسماء في الجدول
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();//تنسيق للجدول///
            renderer.setHorizontalAlignment(JLabel.CENTER);//   محاذاة الخلايا
            for (int i = 0; i < table.getColumnCount() - 1; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        } catch (Exception ex) {
        }
    }
//  هذه تجمع كل الاسعار التي في الجدول
    public double getTotalTable() {
        
        double total = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            try {
                /// هنا اذا كان مختار للخدمة فسوف يرجع ترو وتتحقق الشرط
                if ((boolean)table.getValueAt(i, 2)) {
                    double sum = Double.parseDouble(String.valueOf(table.getValueAt(i, 1)));
                    total += sum;
                }
                
            } catch (Exception ex) {
            }
        }
        //  هذا يضع مجموع الجدول في المبلغ
        sumtotaly.setText(""+total+"");
        
        return total;
        
    }



    public void gettxtDoctorName() {
        
        iddoctors.removeAllElements();
        selectdoctors.removeAllElements();
        txtdoctorname.removeAllItems();
        
        String statement = "SELECT id, name,special FROM doctors ; " ;
        ResultSet rs = Tools.select_Query(statement);
        try {
            while (rs.next()) {
                iddoctors.add(rs.getString(1));
                selectdoctors.add(rs.getString(3));//     هنا تخزين ارقام العيادات او المفاتيح الخارجية
                // هنا قمنا باضافة شرط ان يكون الدكتور مرتبط بالعيادة
                //  لان الايدي حق الدكتور هو مفتاح خارجي للعيادة فأا كان الايدي يساوي الايدي حق العيادة المختارة اضفه
                if(rs.getString(3).equals(idclincname.get(combospecial.getSelectedIndex()))){
                        txtdoctorname.addItem(rs.getString(2));
                }
            }
        } catch (Exception ex) {
        }
        Tools.CloseConnection();
    }

    public void resetValue() {
        txtcode.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtphone.setText("");
        txtemail.setText("");
        txtbirthdate.setText("");
        try {
            combosex.setSelectedIndex(0);
        } catch (Exception ex) {
        }
        try {
            combospecial.setSelectedIndex(0);
        } catch (Exception ex) {
        }
        txtvisitdate.setText(Tools.date());
        txtvisitnumber.setValue(1);
        try {
            txtdoctorname.setSelectedIndex(0);
        } catch (Exception ex) {
        }
        txtnotes.setText("");
        priseclinic.setText(priceclincname.get(combospecial.getSelectedIndex()));
        txtdiscount.setText("0");
        txtpay.setText("0");
        txtrest.setText("0");
        updateTable();
    }
///  دالة لحساب باقي القيمة
    public double sumrest() {
        double sum = 0.0;
        try {
            sum = Double.parseDouble(txtvalue.getText());
            sum -= Double.parseDouble(txtpay.getText());
            sum -= Double.parseDouble(txtdiscount.getText());
        } catch (Exception ex) {
        }
        return sum;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        txtcode = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        combospecial = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        combosex = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtvisitdate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtbirthdate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtvisitnumber = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnotes = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtvalue = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdiscount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtrest = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnadd1 = new javax.swing.JButton();
        btnupdate1 = new javax.swing.JButton();
        btndelete1 = new javax.swing.JButton();
        txtdoctorname = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        sumtotaly = new javax.swing.JLabel();
        priseclinic = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rdname = new javax.swing.JRadioButton();
        rdcode = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        txtcode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("الاسم");

        txtname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtname.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("العنوان");

        txtaddress.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtaddress.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("الهاتف");

        txtphone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtphone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("الايميل");

        txtemail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("تاريخ الميلاد");

        combospecial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        combospecial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combospecialItemStateChanged(evt);
            }
        });
        combospecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combospecialActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("الجنس");

        combosex.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        combosex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ذكر", "انثى" }));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("تاريخ الزياره");

        txtvisitdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtvisitdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtvisitdate.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("العياده");

        txtbirthdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("رقم الزياره");

        txtvisitnumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtvisitnumber.setToolTipText("رقم الزياره الحاليه");
        txtvisitnumber.setRequestFocusEnabled(false);
        txtvisitnumber.setValue(1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("د/ المعالج");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ملاحظات");

        txtnotes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtnotes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 255, 102), 2), "الحسابات", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("القيمه");

        txtvalue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtvalue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtvalue.setText("0");
        txtvalue.setToolTipText("اجمالي المبلغ المستحق");
        txtvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalueActionPerformed(evt);
            }
        });
        txtvalue.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtvaluePropertyChange(evt);
            }
        });
        txtvalue.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtvalueVetoableChange(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("الخصم");

        txtdiscount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtdiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdiscount.setText("0");
        txtdiscount.setToolTipText("وضع القيمه بالنسبه المئويه");
        txtdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiscountActionPerformed(evt);
            }
        });
        txtdiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdiscountKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("المدفوع");

        txtpay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtpay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpay.setText("0");
        txtpay.setToolTipText("المبلغ المدفوع من قبل المريض");
        txtpay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpayKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("الباقي");

        txtrest.setEditable(false);
        txtrest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtrest.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrest.setText("0");
        txtrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtrest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        table.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        table.getTableHeader().setFont(new Font("NewTimeRoman", Font.BOLD, 18));
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "اسم الخدمه", "السعر", "اختيار"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setGridColor(new java.awt.Color(0, 255, 255));
        table.setRowHeight(40);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        btnadd1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnadd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnadd1.setText("اضافه");
        btnadd1.setToolTipText("اضافه مريض جديد او اضافه زياره جديده لمريض قديم");
        btnadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd1ActionPerformed(evt);
            }
        });

        btnupdate1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnupdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_1.png"))); // NOI18N
        btnupdate1.setText("تعديل");
        btnupdate1.setToolTipText("تعديل المعلومات الشخصيه فقط");
        btnupdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdate1ActionPerformed(evt);
            }
        });

        btndelete1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btndelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btndelete1.setText("حذف");
        btndelete1.setToolTipText("حذف جميع معلومات المريض");
        btndelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelete1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btndelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        txtdoctorname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtdoctorname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtdoctornameItemStateChanged(evt);
            }
        });
        txtdoctorname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdoctornameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("المبلغ");

        sumtotaly.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sumtotaly.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sumtotaly.setText("0");

        priseclinic.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        priseclinic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priseclinic.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtvisitnumber)
                            .addComponent(txtphone)
                            .addComponent(combosex, 0, 250, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtbirthdate, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(txtvisitdate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(priseclinic, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(22, 22, 22)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtemail)
                                    .addComponent(combospecial, 0, 220, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtnotes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdoctorname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sumtotaly, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combosex, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtbirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtvisitdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combospecial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtvisitnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(priseclinic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnotes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdoctorname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sumtotaly, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        btnsearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_1.png"))); // NOI18N
        btnsearch.setText("ابحث");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtsearch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        buttonGroup1.add(rdname);
        rdname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdname.setSelected(true);
        rdname.setText("الاسم");
        rdname.setToolTipText("بحث عن طريق الاسم");

        buttonGroup1.add(rdcode);
        rdcode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdcode.setText("الكود");
        rdcode.setToolTipText("بحث عن طريق الكود");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdcode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdname)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdname)
                    .addComponent(rdcode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(0, 0, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reseticon.png"))); // NOI18N
        jButton2.setToolTipText("اعاده تهيئه الجميع");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/previous.png"))); // NOI18N
        jButton3.setToolTipText("الرجوع الى الشاشه الرئيسيه");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void combospecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combospecialActionPerformed
        // TODO add your handling code here:
       System.out.println("yes");
       updateTable();
    }//GEN-LAST:event_combospecialActionPerformed

    private void combospecialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combospecialItemStateChanged
        // TODO add your handling code here:
        try {
            //  هنا نختار العيادة موقع العيادة في الفكتور ونضع القيمة في سعر الكشف
            priseclinic.setText(priceclincname.get(combospecial.getSelectedIndex()));
            txtrest.setText(""+sumrest());
            gettxtDoctorName();// هذه الدالة كلما اخترنا عيادة يقوم بتحديد الاطباء الذين في تلك العيادة
        } catch (Exception ex) {
        }
        try {
            updateTable();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_combospecialItemStateChanged

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        txtrest.setText(""+sumrest());
        try {
            int row = table.getSelectedRow();
            double total = getTotalTable();
             System.out.println(total);
            //total += Double.parseDouble(priceclincname.get(combospecial.getSelectedIndex()));
            total += Double.parseDouble(priseclinic.getText());
            txtvalue.setText(String.valueOf(total));
            //updateTable();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_tableMouseClicked

    private void txtdiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiscountKeyReleased
 
    }//GEN-LAST:event_txtdiscountKeyReleased
//  الاضافة
    private void btnadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd1ActionPerformed
        // TODO add your handling code here:
        ResultSet rs;// = Tools.select_Query("SELECT * FROM patient; ");
        
            if (!txtname.getText().isEmpty()) {
                String statement = "INSERT INTO next(name, clinecname, doctorname, date, time) VALUES "
                        + "('" + txtname.getText() + "', '" + combospecial.getSelectedItem() + "', '" + txtdoctorname.getSelectedItem() + "', "
                        + "'" + Tools.date() + "', '" + Tools.time() + "' ) ;";
                Tools.ExexcuteStatement(statement);
                Tools.CloseConnection();
            }
            if (txtcode.getText().isEmpty()) {
                if (!txtname.getText().isEmpty() && combospecial.getSelectedIndex() > -1) {
                    String statement = "INSERT INTO patient (name, address, phone, email, birthdate, sex) "
                            + "VALUES('" + txtname.getText() + "', '" + txtaddress.getText() + "', '" + txtphone.getText() + "', '" + txtemail.getText() + "',"
                            + " '" + txtbirthdate.getText() + "', " + combosex.getSelectedIndex() + " ) ;";
                    boolean cheack = Tools.ExexcuteStatement(statement);
                    if (cheack) {
                        ///  معرفة رقم اليدي
                        //  موقع الايدي هو العمود رقم واحد في rs 
                        String id = "";
                        statement = "SELECT id FROM patient ;";
                        rs = Tools.select_Query(statement);
                        try {
                            while (rs.next()) {
                                id = rs.getString(1);
                            }
                        } catch (Exception ex) {
                        }
                        
                       
                        // هنا نظيف الى جدول مريض عيادة  الزيارة
                        statement = "INSERT INTO patientclinic(id, clinicid, visitdate, visitnumber, doctorid, notes) "
                                + "VALUES (" + id + ", " + idclincname.get(combospecial.getSelectedIndex()) + ", '" + txtvisitdate.getText() + "', "
                                + "" + txtvisitnumber.getValue() + ", " + iddoctors.get(txtdoctorname.getSelectedIndex()) + ", '" + txtnotes.getText() + "' );";
                        Tools.ExexcuteStatement(statement);

                       
                        statement = "INSERT INTO patientaccounts(id, value, rest) VALUES(" + id + ", " + txtvalue.getText() + ", " + txtrest.getText() + " ) ;";
                        Tools.ExexcuteStatement(statement);

                        for (int i = 0; i < table.getRowCount(); i++) {
                            try {
                                if ((boolean) table.getValueAt(i, 2)) {
                                    statement = "INSERT INTO patientservice(id, name, price) VALUES (" + id + ", '" + table.getValueAt(i, 0) + "', " + table.getValueAt(i, 1) + ") ;";
                                    Tools.ExexcuteStatement(statement);
                                }
                            } catch (Exception ex) {
                            }
                        }
                        JOptionPane.showMessageDialog(null, "تمت الاضافه بنجاح");
                        resetValue();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "يجب اضافه اسم المريض  والعياده");
                }
                Tools.CloseConnection();
            }
            if (!txtcode.getText().isEmpty()) {
                String statement = "INSERT INTO patientdoctor(patientid, doctorid, price) VALUES (" + txtcode.getText() + ", " + iddoctors.get(txtdoctorname.getSelectedIndex()) + ", " + txtvalue.getText() + " ) ;";
                Tools.ExexcuteStatement(statement);

                statement = "INSERT INTO patientclinic(id, clinicid, visitdate, visitnumber, doctorid, notes) "
                        + "VALUES (" + txtcode.getText() + ", " + idclincname.get(combospecial.getSelectedIndex()) + ", '" + txtvisitdate.getText() + "', "
                        + "" + txtvisitnumber.getValue() + ", " + iddoctors.get(txtdoctorname.getSelectedIndex()) + ", '" + txtnotes.getText() + "' );";
                Tools.ExexcuteStatement(statement);

                statement = "INSERT INTO patientaccounts(id, value, rest) VALUES(" + txtcode.getText() + ", " + txtvalue.getText() + ", " + txtpay.getText() + " ) ;";
                Tools.ExexcuteStatement(statement);

                for (int i = 0; i < table.getRowCount(); i++) {
                    try {
                        if ((boolean) table.getValueAt(i, 2)) {
                            statement = "INSERT INTO patientservice(id, name, price) VALUES (" + txtcode.getText() + ", '" + table.getValueAt(i, 0) + "', " + table.getValueAt(i, 1) + ") ;";
                            Tools.ExexcuteStatement(statement);
                        }
                    } catch (Exception ex) {
                    }
                }
                JOptionPane.showMessageDialog(null, "تمت الاضافه بنجاح");
                resetValue();
                Tools.CloseConnection();
            }
        
    }//GEN-LAST:event_btnadd1ActionPerformed

    private void txtdoctornameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtdoctornameItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdoctornameItemStateChanged

    private void txtdoctornameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdoctornameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdoctornameActionPerformed
// الحذف
    private void btndelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelete1ActionPerformed
        // TODO add your handling code here:
        if (!txtcode.getText().isEmpty()) {
            boolean cheack = false;
            String statement = "DELETE FROM patientaccounts WHERE id = " + txtcode.getText() + " ;";
            cheack = Tools.ExexcuteStatement(statement);
            statement = "DELETE FROM patientservice WHERE id = " + txtcode.getText() + " ;";
            cheack = Tools.ExexcuteStatement(statement);
            statement = "DELETE FROM patientclinic WHERE id = " + txtcode.getText() + " ;";
            cheack = Tools.ExexcuteStatement(statement);
            statement = "DELETE FROM patient WHERE id = " + txtcode.getText() + " ;";
            cheack = Tools.ExexcuteStatement(statement);
            if (cheack) {
                JOptionPane.showMessageDialog(null, "تم الحذف بنجاح");
                resetValue();
            }
            Tools.CloseConnection();
        } else {
            JOptionPane.showMessageDialog(null, "يجب اجراء عمليه بحث اولا");
        }
    }//GEN-LAST:event_btndelete1ActionPerformed
//  التعديل
    private void btnupdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdate1ActionPerformed
        // TODO add your handling code here:
        if (!txtcode.getText().isEmpty()) {
            boolean cheack = false;
        
// لا نستطيع التعديل سوا على جدول المرضى اما بقيت الجداول فلا استطيع
            String statement = "UPDATE patient SET name = '" + txtname.getText() + "', address = '" + txtaddress.getText() + "', "
                    + "phone = '" + txtphone.getText() + "', email = '" + txtemail.getText() + "', birthdate = '" + txtbirthdate.getText() + "', "
                    + "sex = " + combosex.getSelectedIndex() + " "
                    + " WHERE id = " + txtcode.getText() + " ;";
            cheack = Tools.ExexcuteStatement(statement);
            if (cheack) {
                JOptionPane.showMessageDialog(null, "تم التعديل بنجاح");
                resetValue();
            }
            Tools.CloseConnection();
        } else {
            JOptionPane.showMessageDialog(null, "يجب اجراء عمليه بحث اولا");
        }
    }//GEN-LAST:event_btnupdate1ActionPerformed
// البحث 
    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        if (!txtsearch.getText().isEmpty()) {
            String statement;
            boolean check = false;
            if (rdname.isSelected()) {
                statement = "SELECT * FROM patient WHERE name = '" + txtsearch.getText() + "' ;";
            } else {
                statement = "SELECT * FROM patient WHERE id = " + txtsearch.getText() + " ;";
            }
            ResultSet rs = Tools.select_Query(statement);
            try {
                while (rs.next()) {
                    check = true;
                    txtcode.setText(rs.getString(1));
                    txtname.setText(rs.getString(2));
                    txtaddress.setText(rs.getString(3));
                    txtphone.setText(rs.getString(4));
                    txtemail.setText(rs.getString(5));
                    txtbirthdate.setText(rs.getString(6));
                    combosex.setSelectedIndex(Integer.parseInt(rs.getString(7)));
                }
                if (!txtcode.getText().isEmpty()) {
                    statement = "SELECT visitnumber FROM patientclinic WHERE id = " + txtcode.getText() + " ;";
                    rs = Tools.select_Query(statement);
                    Vector<String> v = new Vector();
                    while (rs.next()) {
                        v.add(rs.getString(1));
                    }
                    int num = 0;
                    for (int i = 0; i < v.size(); i++) {
                        if (Integer.parseInt(v.get(i)) > num) {
                            num = Integer.parseInt(v.get(i));
                        }
                    }
                    num++;
                    txtvisitnumber.setValue(num);
                }
                if (!check) {
                    JOptionPane.showMessageDialog(null, "لم يتم العثور على اي معلومات");
                }
            } catch (Exception ex) {
            }
            Tools.CloseConnection();
        } else {
            JOptionPane.showMessageDialog(null, "يجب كتابه اسم او كود المريض");
        }
    }//GEN-LAST:event_btnsearchActionPerformed
//  دالة الباقي
    private void txtpayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpayKeyReleased
        // TODO add your handling code here:
        txtrest.setText(String.valueOf(sumrest()));
    }//GEN-LAST:event_txtpayKeyReleased
//زر التحديث
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ///  زر التحديث
        resetValue();
        setItemcombospecial();
        updateTable();
        txtvalue.setText(priceclincname.get(combospecial.getSelectedIndex()));
        gettxtDoctorName();
        txtvisitdate.setText(Tools.date());
        txtsearch.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalueActionPerformed

    private void txtdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiscountActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtdiscountActionPerformed

    private void txtrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrestActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtrestActionPerformed

    private void txtvaluePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtvaluePropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtvaluePropertyChange

    private void txtvalueVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtvalueVetoableChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtvalueVetoableChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btndelete1;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnupdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combosex;
    private javax.swing.JComboBox<String> combospecial;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel priseclinic;
    private javax.swing.JRadioButton rdcode;
    private javax.swing.JRadioButton rdname;
    private javax.swing.JLabel sumtotaly;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtbirthdate;
    private javax.swing.JLabel txtcode;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JComboBox<String> txtdoctorname;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnotes;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtrest;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtvalue;
    private javax.swing.JTextField txtvisitdate;
    private javax.swing.JSpinner txtvisitnumber;
    // End of variables declaration//GEN-END:variables
}
