
package doctorto;

///  وظيفة هذا الكلاس لتخزين كل اومر الداتا بيز التي سنحتاجها او بمعنى اصح نخزن فيه الجداول بمعنى اصح

import java.util.Vector;

public class DatabaseStatement {
    
    ////  ???//
     static Vector database= new Vector();
    
    
    ///  دالة تملي الفكتور اللي طالع
    public static void setDatabase(){
        ///    جدول الاطباء
        database.add("CREATE TABLE doctors(\n"
                + "	id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "	name VARCHAR(50),\n"
                + "	address VARCHAR(30),\n"
                + "	phone INTEGER(9),\n"
                + "	email VARCHAR(30),\n"
                + "	special INTEGER,\n"
                + "	sex INT,\n"
                + "	salary DECIMAL(10,2),\n"
                + "	imagename VARCHAR(30),\n"
                + "	imagefile MEDIUMBLOB,\n"
                + "	CONSTRAINT special_d_fk FOREIGN KEY(special) REFERENCES clincname(id)\n"
                + "	);");
        // جدول الموضفين
        database.add("CREATE TABLE employee(\n"
                + "	id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "	name VARCHAR(30),\n"
                + "	address VARCHAR(30),\n"
                + "	phone VARCHAR(20),\n"
                + "	email VARCHAR(30),\n"
                + "	jobname VARCHAR(30),\n"
                + "	salary DECIMAL(10,2),\n"
                + "	sex INT,\n"
                + "	ssd VARCHAR(35),\n"// الرقم القومي
                + "	datebegain VARCHAR(12),\n"
                + "	imagename VARCHAR(30),\n"
                + "	imagefile MEDIUMBLOB\n"
                + "	);");
        //  جدول المصروفات
        database.add("CREATE TABLE expenserecord(\n"
                + "	id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "	name VARCHAR(30),\n"
                + "	price DECIMAL(12,2),\n"
                + "	date VARCHAR(12),\n"
                + "	description VARCHAR(50)\n"
                + "	);");
        // جدول المرضى
        database.add("CREATE TABLE patient(\n"
                + "	id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "	name VARCHAR(50),\n"
                + "	address VARCHAR(30),\n"
                + "	phone INTEGER(9),\n"
                + "	email VARCHAR(30),\n"
                + "	birthdate VARCHAR(12),\n"
                + "	sex INT\n"
                + "	);");
        database.add("CREATE TABLE patientclinic(\n"//  هذا جدول وسيط بين العيادات والمرضى
                + "	id INTEGER,\n"
                + "	clinicid INTEGER,\n"
                + "	visitdate VARCHAR(12),\n"
                + "	visitnumber INT,\n"
                + "	doctorid INTEGER,\n"
                + "	notes VARCHAR(50),\n"
                + "	CONSTRAINT  pa_pa_c_fk FOREIGN KEY(id) REFERENCES patient(id),\n"
                + "	CONSTRAINT  cl_pa_cc_fk FOREIGN KEY(clinicid) REFERENCES clincname(id),\n"
                + "	CONSTRAINT  do_pa_ccc_fk FOREIGN KEY(doctorid) REFERENCES doctors(id)\n"
                + "	);");
        // جدول خدمات المرضى
        database.add("CREATE TABLE patientservice(\n"
                + "	id INTEGER,\n"
                + "	name VARCHAR(30) NOT NULL, \n"// اسم الخدمة
                + "	price DECIMAL,\n"
                + "	CONSTRAINT id_p_fk FOREIGN KEY(id) REFERENCES patient(id)\n"
                + "	);");
        // حساب المرضى
        database.add("CREATE TABLE patientaccounts(\n"
                + "	id INTEGER,\n"
                + "	value DECIMAL(12,2),\n"
                + "	rest DECIMAL(12,2),\n"
                + "	CONSTRAINT id_pa_fk FOREIGN KEY(id) REFERENCES patient(id)\n"
                + "	);");
        database.add("ALTER TABLE patientaccounts \n"
                + "MODIFY COLUMN rest DECIMAL(12,3);");

        // جدول  معلومات عامة 
        database.add("CREATE TABLE next(\n"
                + "	id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "	name VARCHAR(30),\n"
                + "	clinecname VARCHAR(30),\n"
                + "	doctorname VARCHAR(30),\n"
                + "	date VARCHAR(14),\n"
                + "	time VARCHAR(14)\n"
                + "	)");
        
        // المستخدمين  
        database.add("CREATE TABLE users(\n"
                + "	id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "	name VARCHAR(50),\n"
                + "	password VARCHAR(20)\n"
                + "	);");
        

             //  حسابات الموظفين
        database.add("CREATE TABLE accountemployee(\n"
                + "	id INTEGER,\n"
                + "	value DECIMAL(10,2),\n"
                + "	date VARCHAR(14),\n"
                + "	notes VARCHAR(70),\n"
                + "	CONSTRAINT id_acc_fk FOREIGN KEY(id) REFERENCES employee(id)\n"
                + "	);");
        //  جدول حسابات الاطباء
        database.add("CREATE TABLE accountdoctor(\n"
                + "	id INTEGER,\n"
                + "	value DECIMAL(10,2),\n"
                + "	date VARCHAR(14),\n"
                + "	notes VARCHAR(70),\n"
                + "	CONSTRAINT id_accd_fk FOREIGN KEY(id) REFERENCES doctors(id)\n"
                + "	);");
        

        
      // اسماء العيادات  
        database.add("CREATE TABLE clincname(\n"
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                + "name VARCHAR(40) NOT NULL,\n"
                + "price DECIMAL(10,2) \n"
                + ");");
        //  خدمات العيادات
        database.add("CREATE TABLE clincservice ( \n"
                +"id INTEGER,\n"
                + "name VARCHAR(40) NOT NULL, \n"
                + "price DECIMAL(10,2), \n"
                + "description VARCHAR(40), \n"
                + "CONSTRAINT clincservice_id_fk  FOREIGN KEY(id) REFERENCES clincname(id) \n"
                + ");");
   }
    
    public static void main(String[] args) {
        setDatabase();
        for (int i = 0; i < database.size(); i++) {
            boolean check =Tools.ExexcuteStatement(database.get(i).toString());
            if(check){
                System.out.println(i+"_ Successfully");
            }else{
                System.out.println(i+"_ Failed");
            }
            
        }
    }
}
