/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;



import ConexionBd.CBDPLAY;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gtecno
 */
public class ModPresentacion extends javax.swing.JFrame {
 Timer contador;
  int a;
   int Activi=0,Activi2,cont,tele2,dia,mes,ano,ano2,mes2,dia2, fecha;
   String nomb,codigoc,codigoc3="null", codigoc4="null",codigoc5="null",codigoc2,rutafo,codigotar,tipo,estado,clave,fechac,fechav,codigotar3,codigotar4,codigotar5,idta,tipota,estadota,fechata,rutaf,nomb1,ape1,rufoq,cliented;
      CBDPLAY cc = new CBDPLAY();
 Connection cn = cc.Conectar();
   Statement  st;ResultSet rs;
  String SQL,SQL2,SQL3,SQL4;
    PreparedStatement psd;
   /**
     * Creates new form ModPresentacion
     */
    public ModPresentacion() {
        setUndecorated(true);
        initComponents();
         fechaActual();
          
        
         setLocationRelativeTo(null);
         this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        i();
        
          bloqueo();
          
        contador = new Timer(50, new claseTimer());
    contador.start();
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/ico.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
  void  bloqueo(){
       lb1.setVisible(false); 
       
         lb2.setVisible(false); 
          lb3.setVisible(false); 
          
              
            
             
           
           
    }
    
public class claseTimer implements ActionListener
  {
   public claseTimer() { 
   }
    
    public void actionPerformed(ActionEvent e)
    {
     
      if(a<=100){
           a+=1;
       if(a<=20){
          
           jLabel13.setVisible(true);
        jLabel13.setVisible(true);
       
          lb1.setVisible(true);
           jLabel13.setText(" CyberPlay Pagos V1.1 2018: Verificando.");
           
           
            
      }
        if(a>20&& a<=40){
           
         
            
                 

          
         } 
         if(a>40&& a<=60){
            lb2.setVisible(true);
          
           jLabel13.setText(" CyberPlay Pagos V1.1 2018: Verificando..");       

          
         } 
         
           if(a>60&& a<=80){
            
           
          lb3.setVisible(true);
              jLabel13.setText(" CyberPlay Pagos V1.1 2018: Verificando..");
                 

          
         } 
          
          if(a>80&& a<=100){
          // jLabel9.setVisible(true);
           jLabel13.setText(" CyberPlay Pagos V1.1 2018: Lidto");
        
   
         dispose();
          
         }
                    

           
      }
      else
      {
            ModPresentacion.this.contador.stop(); 
      
        traer();
            Expira();
    
       
    
      
       
      }
            
      
      }
     
      }
  void salir(){
      
  }
    public static String fechaActual()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyy");
        return formatoFecha.format(fecha);
                }
     
     public static String fechaActual2()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy");
        return formatoFecha.format(fecha);
                }
      public static String fechaActual2_1()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM");
        return formatoFecha.format(fecha);
                }
      public static String fechaActual2_2()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd");
        return formatoFecha.format(fecha);
                }
     
      public static String fechaActual3()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM");
        return formatoFecha.format(fecha);
                }
  
          
      
   
     
       void traer(){
           try{
             SQL2 = "SELECT * FROM sistemab WHERE id = 1 ";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
     
      
      
                dia= rs.getInt("dia");
                mes= rs.getInt("mes");
                ano= rs.getInt("ano");
           
           
           }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error");
           }
       }
  void Expira(){
        ano2= Integer.valueOf(fechaActual2());
        mes2=Integer.valueOf(fechaActual2_1());
        dia2= Integer.valueOf(fechaActual2_2());
      
              
        if(ano2>=ano){
           if(ano2>ano){
                Activi=1;
               // new  ModPresentacion().setVisible(false);
                JOptionPane.showMessageDialog(this,"Su licencia a Expirado!");
                 new  code().setVisible(true);
                
        
           }
            if(ano2==ano){
               if(mes2>=mes){
                  if(mes2>mes){
                      Activi=1;
                 // new  ModPresentacion().setVisible(false);
                JOptionPane.showMessageDialog(this,"Su licencia a Expirado!");
                 new  code().setVisible(true);
                 
               } 
                  if(mes2==mes){
                   if(dia2>=dia){
                       Activi=1;
                  // new  ModPresentacion().setVisible(false);
                JOptionPane.showMessageDialog(this,"Su licencia a Expirado!");
                 new  code().setVisible(true);
                    
               }  
               }  
               }
           }
           
        }
        if(Activi!=1){
         new   ModUsu().setVisible(true);  
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

        jPanel1 = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CyberPlayPagos ");

        jPanel1.setLayout(null);

        lb3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3.png"))); // NOI18N
        jPanel1.add(lb3);
        lb3.setBounds(210, 170, 50, 40);

        lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.png"))); // NOI18N
        jPanel1.add(lb2);
        lb2.setBounds(170, 170, 50, 40);

        lb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1.png"))); // NOI18N
        jPanel1.add(lb1);
        lb1.setBounds(140, 180, 40, 30);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel13);
        jLabel13.setBounds(4, 220, 350, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pre2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-6, 0, 410, 250);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModPresentacion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    // End of variables declaration//GEN-END:variables
}
