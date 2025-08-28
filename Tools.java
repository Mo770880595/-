
package doctorto;
/////////  هذا الكلاس يعمل اتصال لقاعدة البيانات وفيه بعض الاكواد التي سنحتاجها اكثر من مرة

import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tools {
    private static Connection con = null;
    private static DefaultTableCellRenderer renderer = null;
    ////        دالة الاتصال بقاعدة البيانات
    public static Connection connectionToSql(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctorto", "root", "");
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("هذا الخطاء مندالة الاتصال بالقاعدة اول دالة في التولز");
        }
        return con;
    }
    //  هذه الدالة عملها تستقبل اسم بنل وتغير لون خلفيته
    public static void setcolor(JPanel p){
        p.setBackground(new Color(173,216,230));
    }
    ///     دالة اغلاق قاعدة البيانات
    public static void CloseConnection(){
        try {
            con.close();
        } catch (Exception ex) {}
    }
    ///// دالة اضافة وتحديث وتعديل  من القاعدة
    public static boolean ExexcuteStatement (String sql){
        try {//// هذه الدوال من اصل لغة جافا عملها تنشئ قاعدة البيانات
            connectionToSql();////  اول دالة في هذا الكلاس تعمل اتصال تشير هذه
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex,"ExexcuteStatement Method",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    //  هذه الدالة تقوم بجعل راس الجدول وسط
    public static void HeaderRendererCenter(JTable table) {
        renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }
//  هذه الدالة تجعل صفوف الجدول وسط
    public static void rowRenderCenter(JTable table) {
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
    public static String date() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
        return sdf.format(date);
    }

    public static String time() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:m:s a");
        return sdf.format(date);
    }
////  هذا استخدمناه لتحديث الجدالو عند عمليات البحث وما الا ذلك
    public static void tableUpdate(DefaultTableModel model, String statement, int column) {
        try {
            model.setRowCount(0);
            ResultSet rs = select_Query(statement);
            while (rs.next()) {
                Vector arr_table = new Vector();
                for (int i = 1; i <= column; i++) {
                    arr_table.add(rs.getString(i));
                }
                model.addRow(arr_table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        CloseConnection();
    }
    

    public static ResultSet select_Query(String sql){
        try {
            connectionToSql();////  اول دالة في هذا الكلاس تعمل اتصال تشير هذه
            Statement st=con.createStatement();//  هذا النوع من الكائن يستخدم لارسال جمل الاتصالات بقاعدة البيانات عبر جي دي بي سي
            ResultSet rs=st.executeQuery(sql);
            return rs;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex,"select_Query Method",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
}
