/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gtecno
 */
public class CBDPLAY {
      public String db = "cyberplay";
   public String url = "jdbc:mysql://localhost/"+db;
   public String user = "root";
   public String pass = "Gtecno12345678";
   public Statement comando=null;
   
   public  CBDPLAY()
   {
     
   }

   
   public Connection Conectar()
   {
       Connection link = null;
       try
       {
          
           Class.forName ("org.gjt.mm.mysql.Driver");
           
           link = DriverManager.getConnection(this.url, this.user, this.pass);
       }
       catch (Exception e)
       {
        JOptionPane.showMessageDialog (null, e);
       } 
       return link;
   }
}
