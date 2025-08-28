                /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package doctorto;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TFP
 */
public class ServiceInfo extends javax.swing.JFrame {

    int row = -1;
    DefaultTableModel model, modelservice; // عررفنا 2 مودل واحد للجدول وواحد لجدول الخدمات
    Vector id_clinicname = new Vector();

    /**
     * Creates new form ServiceInfo
     */
    public ServiceInfo() {
        initComponents();
        table.getTableHeader().setFont(new Font("TimesNewRoman",Font.BOLD,18));// تنسيق عناوين  الجدول  الخط 
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);//// جعلناه من اليمين الى الشمال
        tableservice.getTableHeader().setFont(new Font("TimesNewRoman",Font.BOLD,18));// تنسيق عناوين  الجدول  الخط 
        tableservice.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);//// جعلناه من اليمين الى الشمال
        model = (DefaultTableModel) table.getModel();// ربطناه بالججدول
        modelservice = (DefaultTableModel) tableservice.getModel();// ربطناه بجدول الخدمات
        updateTable();
    }

    ///   هذه الدالة وظيفتها تأخذ المعلومات من جدول العيادات  من قاعدة البيانات وتعرضها في جدول العيادات 
    public void updateTable() {
        model.setRowCount(0);// صفر الجدول او مسح كل ماهو ظاهر فيه
        id_clinicname.removeAllElements();// حذف كل ما في الفكتور
        String statement = "SELECT * FROM clincname;";
        ResultSet rs = Tools.select_Query(statement);// عملنا تنفيذ وخزننا الناتج في ريزلتسيت
        try {
            while (rs.next()) {/// نبدأ نلف على الداتا التي جاي او على الصفوف
                id_clinicname.add(rs.getString(1));/// اخذنا الايدي وخزناه في الفكتور على شان نقدر نعدل ونحذف من نظهر في الجدول 
                Vector row = new Vector();// هذا فكتور جديد نخزن فيه الاسم والسعر
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                model.addRow(row); //  الصف للجدول(الفكتور) نظيف 
            }
        } catch (Exception ex) {
        }
        Tools.CloseConnection();/// نغلق قاعدة البيانات
    }

    //  الجدول السابق يحتاج ايفنت بحيث اذا ضغطنا على صف او ام عيادة يظهر معلوماته في باقي الفريم
    public void setTableService(String id) {
        String statement = "SELECT * FROM clincservice WHERE id= " + id + " ;";
        ResultSet rs = Tools.select_Query(statement);
        int i = 1;
        try {
            while (rs.next()) {
                Vector row = new Vector();
                row.add(i);
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                modelservice.addRow(row);
                i++;
            }
        } catch (Exception e) {
        }
        Tools.CloseConnection();
    }

    //// هذة الدالة هي ايفنت لجدول العيادات بحيث اذا ضغطنا على احد الصفوف تتفاعل الواجهه معي 
    public void tableClicked() {
        reset_Value();
        int row = table.getSelectedRow();
        txtname.setText(table.getValueAt(row, 0).toString());
        txtprice.setText(table.getValueAt(row, 1).toString());
        setTableService(id_clinicname.get(row).toString());
    }

    ///
    public void reset_Value() {
        modelservice.setRowCount(0);
        txtname.setText("");
        txtprice.setText("");
    }

    public void btnUpdate() {
        //  هذا تعديل لجدول الخدمات اولا نقوم بحذف كل الخدمات ثم نظيف الذي نريد
        if (table.getSelectedRow() > -1) {
            String statement = "DELETE FROM clincservice WHERE id =" + id_clinicname.get(table.getSelectedRow()) + " ;";
            boolean check = Tools.ExexcuteStatement(statement);
            if (check) {
                statement = "UPDATE clincname SET name='" + txtname.getText() + "', price =" + txtprice.getText() + " WHERE id = " + id_clinicname.get(table.getSelectedRow()) + " ;";
                Tools.ExexcuteStatement(statement);
                for (int i = 0; i < tableservice.getRowCount(); i++) {
                    statement = "INSERT INTO clincservice (id,name,price,description) "
                            + "VALUES (" + id_clinicname.get(table.getSelectedRow()) + ",'" + tableservice.getValueAt(i, 1) + "'," + tableservice.getValueAt(i, 2) + ",'" + tableservice.getValueAt(i, 3) + "') ;";
                    Tools.ExexcuteStatement(statement);
                }
                JOptionPane.showMessageDialog(null, "تم التعديل بنجاح");
                reset_Value();
                updateTable();
            }
            Tools.CloseConnection();
        } else {
            JOptionPane.showMessageDialog(null, "يجب تحديد سطر من الجدول");
        }
    }
    //  دالة الحذف يجب حذف الجدول البيانات التي هي ذات مفتاح خارجي اولا ثم حذف البيانات ذات المفتاح الرئيسي
    public void btnDelete(){
        if (table.getSelectedRow() > -1) {
            String statement = "DELETE FROM clincservice WHERE id =" + id_clinicname.get(table.getSelectedRow()) + " ;";
            boolean check = Tools.ExexcuteStatement(statement);
            if (check){
                statement = "DELETE FROM clincname WHERE id =" + id_clinicname.get(table.getSelectedRow()) + " ;";
                Tools.ExexcuteStatement(statement);
                JOptionPane.showMessageDialog(null, "تم الحذف بنجاح ");
                reset_Value();
                updateTable();
            }
            Tools.CloseConnection();
        }else{
            JOptionPane.showMessageDialog(null, "انتبه يجب تحديد صف من الجدول");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableservice = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("اسم العيادة");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("سعر المعاينة");

        txtname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtname.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtprice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtprice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpriceActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "اسم العيادة", "سعر المعاينة"
            }
        ));
        table.setRowHeight(35);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("اضافة خدمات");

        tableservice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tableservice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم", "اسم الخدمة", "سعر الخدمة", "الوصف"
            }
        ));
        tableservice.setRowHeight(30);
        tableservice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableserviceMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableservice);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("تعديل");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setText("حذف");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setText("اضافة صف");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("حذف صف");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtname, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(txtprice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton2)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
/// ايفنت ماوس للجدول
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        //// هذة الدالة هي ايفنت لجدول العيادات بحيث اذا ضغطنا على احد الصفوف تتفاعل الواجهه معي 
        tableClicked();
    }//GEN-LAST:event_tableMouseClicked
// ايفنت ماوس لجدول الخدمات
    private void tableserviceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableserviceMouseClicked
        // TODO add your handling code here:
        row = tableservice.getSelectedRow();///  هذا عمله يأخذ قيمة او الرقم السطر الذي الماوس نقر عليه على شان اسوي له مثلا حذف
    }//GEN-LAST:event_tableserviceMouseClicked
// تعديل 
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        btnUpdate();
    }//GEN-LAST:event_jButton4ActionPerformed
/// حذف
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        btnDelete();
        
    }//GEN-LAST:event_jButton5ActionPerformed
/// بوتون اضافة صف تبقى اكوادة مثل ما هي
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //  اضافة صف في كل مرة نظغطه
        /// هذه المرة سيكون اسم الجدول تابل سرفس وليس تابل
        modelservice.addRow(new Vector());
        //// نعدل قيمتها
        tableservice.setValueAt(tableservice.getRowCount(), tableservice.getRowCount()-1, 0);// وظيفته يزود صف ويزود رقم الصف
    }//GEN-LAST:event_jButton2ActionPerformed
 //    بوتون حذف صف تبقى اكوادة مثل ماهي ونغير اسم الجدول فقط
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:    هذا البوتن هو لحذف صف من الجدول
        if(row>-1){
            modelservice.removeRow(row);
            for (int i = 0; i < tableservice.getRowCount(); i++) {/// هذا الفور لكي نعيد ترتيب الصفوف  او بالاصح يرتب الاندكس
                tableservice.setValueAt((i+1), i, 0);
            }
            row=-1;//نعيد الرو يساوي سالب واحد لأجل لا يحذف عشوائي
        }else{
            JOptionPane.showMessageDialog(null, "يجب اختيار صف من الجدول");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpriceActionPerformed

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
            java.util.logging.Logger.getLogger(ServiceInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServiceInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServiceInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServiceInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServiceInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTable tableservice;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtprice;
    // End of variables declaration//GEN-END:variables
}
