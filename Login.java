package doctorto;

import com.sun.org.glassfish.gmbal.ManagedObjectManagerFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
// خاص بالواجهة
    Font f18 = new Font("TimesNewRoman", Font.BOLD, 18);
    JButton login = new JButton("تسجيل الدخول");
    JButton cancel = new JButton("اغلاق");
    JLabel l_name = new JLabel("اسم المستخدم");
    JLabel l_pass = new JLabel("كلمة المرور");
    JTextField txt_name = new JTextField();
    JPasswordField pass = new JPasswordField();
    JCheckBox show = new JCheckBox("اضهار كلمة المرور");
    JLabel loginform = new JLabel("تسجيل الدخول");
    JPanel panel = new JPanel(null);

    public Login() {
        this.setTitle("تسجيل الدخول");
        this.setLayout(null);
        this.setSize(800, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
//  خاص بواجهة تسجيل الدخول
        panel.setBounds(0, 0, 800, 140);
        loginform.setBounds(330, 50, 200, 40);
        l_name.setBounds(620, 200, 100, 35);
        l_pass.setBounds(620, 250, 100, 35);
        txt_name.setBounds(250, 200, 350, 35);
        pass.setBounds(250, 250, 350, 35);
        login.setBounds(250, 330, 150, 35);
        cancel.setBounds(450, 330, 150, 35);
        show.setBounds(100, 260, 125, 20);
        panel.setBackground(Color.red);
        this.getContentPane().setBackground(Color.decode("#330033"));
        loginform.setFont(new Font("NewTimesRoman", Font.BOLD, 32));
        loginform.setForeground(Color.WHITE);
        l_name.setForeground(Color.WHITE);
        l_pass.setForeground(Color.WHITE);
        show.setBackground(Color.decode("#330033"));
        show.setForeground(Color.WHITE);

        l_name.setFont(f18);
        l_pass.setFont(f18);
        //show.setFont(f18);
        login.setFont(f18);
        cancel.setFont(f18);

        panel.add(loginform);

        this.add(panel);
        this.add(l_name);
        this.add(l_pass);
        this.add(txt_name);
        this.add(pass);
        this.add(login);
        this.add(cancel);
        this.add(show);
//////////////////////////////////////////////////////////////////
        ////     لتفعيل عمل تسجيل الدخول والاغلاق وعرض كلمة المرور قمنا اولا بايضافة امبلمنت بعد اكستند الوراثة في الكلاس ثم 
        ////     ظهرت لنا دالة اوفررايد تلقائية ثم هذه الاكواد
        show.addActionListener(this);
        login.addActionListener(this);
        cancel.addActionListener(this);

    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ////   دالة لتأكد من عرض كلمة المرور
        if (e.getSource() == show) {
            if (show.isSelected()) {
                pass.setEchoChar('\0');//اظهر كلمة المرور
                show.setText("اخفاء كلمة المرور");

            } else {
                pass.setEchoChar('*');//   اذا المستخدم ضغط اظهار كلمة المرور ثم الغاء سوف يبقى المربع مؤشر عليه ولكن لم يختارة
                show.setText("اضهار كلمة المرور");
            }
        } else if (e.getSource() == cancel) {
            /////      اذا قرر المستخدم الخروج نبعث رسالة اذا كان يريد الخروج
            int c = JOptionPane.showConfirmDialog(null, "هل تريد الخروج", "اختيار", JOptionPane.YES_NO_OPTION);
            ///   نتأكد انه اختار نعم يريد الخروج ونغلق البرنامج
            if (c == 0) {
                System.exit(0);
            }
        } else if (e.getSource() == login) {
           checkEmail();

        }
    }

    public  void checkEmail() {
        try {
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctorto", "root", "");*/
            if (!txt_name.getText().isEmpty()){
                String username = txt_name.getText();
                String password = pass.getText();
                String sql = "SELECT * FROM users WHERE name='"+ username + "' AND password ='" + password + "' ;";
                /*Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);*/
                //  هذا النوع من الكائنات يستخدم لتخزين الاستعلامات من قاعدة البيانات كصفوف واعمدة
                ResultSet rs =Tools.select_Query(sql);//// هذا من التول وقام بدل كل المعلق في هذه الدالة وشكرا
                String user = "", pass = "";
                while (rs.next()) {
                    user = rs.getString(2);
                    pass = rs.getString(3);
                }
                if (username.equals(user) && password.equals(pass)) {
                    this.dispose();
                    new MainFrame().setVisible(true);//  فتح نافذة البرنامج
                    System.out.println("successfully\nاحسنت صنعا !!!!!!!!!!");


                }else{
                    JOptionPane.showMessageDialog(null, "من فظلك تأكد من اسم المستخدم وكلمة المرور");
                }
            }else{
                JOptionPane.showMessageDialog(null, "يجب ادخال كلمة المرور واسم المستخدم");
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
