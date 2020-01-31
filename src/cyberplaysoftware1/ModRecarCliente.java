/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;

import ConexionBd.CBDPLAY;
import java.awt.Color;
import java.util.*;
import java.math.BigDecimal;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gtecno
 */
public class ModRecarCliente extends javax.swing.JFrame {
    CBDPLAY cc = new CBDPLAY();
 Connection cn = cc.Conectar();
   Statement  st;ResultSet rs;
  String SQL,SQL2,SQL3,SQL4;
  DefaultTableModel modelo;
    PreparedStatement psd;
    // BigDecimal Monto;
    String id_Cuenta="",id_seguimiento="",CedulaC="",RecargaRet_bsf="",RecargaRet_bss="",ProcesoC="",fechaP="",rutafo="";
String Monto1="0",Monto2="",NumeroR1="",Cedula="",Moneda="",SMonto1="",SMonto2="",Fondo1="",Fondo2="",Nombre="",Apellido="";
int cont,cont2,selec,Montor, Recargabss3; 
//////////// 
 BigDecimal Monto3= new BigDecimal(0),Monto4= new BigDecimal(0),Recargabss= new BigDecimal(0),Suma= new BigDecimal(0),Recargabss2= new BigDecimal(0);  
 BigDecimal MonedaCon= new BigDecimal(100000);
  BigDecimal Montotxt;
  BigDecimal fondobd;
 
 /////  
 int Activi,Activi2,Activi3,Activi4,Activi5;/**
 
    * Creates new form ModSeguClii
     */
 
    public ModRecarCliente() {
        initComponents();
         setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        i();
      
        paneb.setVisible(false);
        panecu.setVisible(false);
        panep.setVisible(false);
        panefo.setVisible(false);
        lbno.setVisible(false);
        lbap.setVisible(false);
        jbnom.setVisible(false);
        jbape.setVisible(false);
         jbfon.setVisible(false);
          jvfondo.setVisible(false);
          panecar.setVisible(false);
           btregre.setVisible(false);
             bloqueo();
     pnCono.setVisible(true);
    }
    void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/ico.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
    void bloqueo(){
        txtCedula.setEnabled(false);
        jButton2.setEnabled(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        txtMonto.setEnabled(false);
        btaceptar.setEnabled(false);
        jRadioButton3.setEnabled(false);
        jRadioButton4.setEnabled(false);
    }
   void bloqueosu(){
         pnCono.setVisible(false); 
 paneb.setVisible(true);
        panecu.setVisible(true);
        panep.setVisible(true);
        panefo.setVisible(true);
        lbno.setVisible(true);
        lbap.setVisible(true);
        jbnom.setVisible(true);
        jbape.setVisible(true);
         jbfon.setVisible(true);
          jvfondo.setVisible(true);
          panecar.setVisible(true);
           btregre.setVisible(true);
 txtRef.setVisible(false);  
jLabel3.setVisible(false);
jRadioButton1.setSelected(false);
jRadioButton1.setSelected(false);
jLabel2.setText("MONTO A RECARGAR"); 
     txtMonto.setForeground(Color.BLUE);
     jRadioButton3.setSelected(true);
     jRadioButton4.setSelected(false);
   }
     void desblobloqueo(){
        txtCedula.setEnabled(true);
        jButton2.setEnabled(true);
       
        
    }
    void nuevo(){
        
       
        jbnom.setText("");
        jbape.setText("");
        txtMonto.setText("");
          jvfondo.setText("");
          txtCedula.setText("");
        txtRef.setText("");
           id_Cuenta="";
           jvfondo.setText("");
            lebfoto.setIcon(null);
           jRadioButton3.setSelected(true);
     jRadioButton4.setSelected(false);
           jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
             txtMonto.setForeground(Color.BLUE);
            jLabel2.setText("MONTO A RECARGAR"); 
           CedulaC="";RecargaRet_bsf="";RecargaRet_bss="";fechaP="";
 Monto1="0";Monto2="";NumeroR1="";Cedula="";Moneda="";SMonto1="";SMonto2="";Fondo1="";Fondo2="";Nombre="";Apellido="";rutafo="";
 // Monto3;
 // Monto4;
//  MonedaCon;
//  Recargabss;
 // Suma;
//  Recargabss2;
 selec=0;
  cont=0;cont=02;  
        
         
    }
     void nuevo2(){
        
        
     
        txtMonto.setText("");
         
         
           txtRef.setText("");
           txtMonto.setForeground(Color.BLUE);
           jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
          jLabel2.setText("MONTO A RECARGAR"); 
          jRadioButton3.setSelected(true);
     jRadioButton4.setSelected(false);
         
    }
      void cargarCuenta() {
         String bsf1,bsf2,titu,titu2,bssin;
          DecimalFormat dnFormat13;
         DecimalFormat dnFormat = new  DecimalFormat("#,##0.00");
           DecimalFormatSymbols simbolo=new  DecimalFormatSymbols();
                                        simbolo.setDecimalSeparator('.');
                                        simbolo.setGroupingSeparator(',');
                                        
                  DecimalFormat dnFormat1 = new  DecimalFormat("#.#####",simbolo);
      
            
             
       if(Activi5==1){
           dnFormat13=dnFormat;
           titu2="MONTO BSF";
           titu="FONDO EN BSF";
            bsf1="fondos_cuenta_bsf";
             bsf2="recarga_retiro_cuenta_bsf";
             bssin=".BsF";
       }else{
             dnFormat13=dnFormat1;
           titu2="MONTO BSS";
           titu="FONDO EN BSS";
            bsf1="fondos_cuenta_bss";
             bsf2="recarga_retiro_cuenta_bss";
             bssin="BsS";
       }
        try{
           
            String [] titulos={"ID","NÚMERO",titu,titu2,"PROCESO","NÚMERO DE REF","FECHA"};
            String [] registros= new String[7];
            modelo=new DefaultTableModel(null,titulos);
            
             SQL3="select * from seguimiento_cuenta_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(Cedula) + "%'";
             st= cn.createStatement();
             rs = st.executeQuery(SQL3);
            while(rs.next()){
                 registros[0]=rs.getString("id_cuenta");
               registros[1]=rs.getString("id_cuentacliente");
                registros[2]=bssin+" "+ dnFormat13.format(rs.getBigDecimal(bsf1));
                 registros[3]=bssin+" "+ dnFormat13.format(rs.getBigDecimal(bsf2));
                registros[4]=rs.getString("proceso_cuenta");
                 registros[5]=rs.getString("numero_referencia");
                registros[6]=rs.getString("fecharecar_cuenta");
        modelo.addRow(registros);   
           } 
                  
                
         tacuenta.setModel(modelo);
          tacuenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tacuenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            tacuenta.getColumnModel().getColumn(2).setPreferredWidth(100);
             
        }catch(Exception e){
                System.out.println(e.getMessage());
                 }
}
    void formulaConver(){
    // String ak=   txtMonto.getText();
      //  fl(ak);
      
    }
    public void Montx(String... s){
       Montotxt= new BigDecimal(s[0]);  
    }
    public void fondobd1(String... fon){
       fondobd= new BigDecimal(fon[0]);  
    }
    //public void Montx(String... s){
      // Montotxt= new BigDecimal(s[0]);  
   // }
    void Suma(){
         String ak=  txtMonto.getText();
       Montx(ak);
        fondobd1(Fondo1);
          
    //  Monto1= txtMonto.getText();
      
      Suma=fondobd;
      Suma=Suma.add(Montotxt);
       Monto4=(Suma.divide(MonedaCon)); 
     Recargabss = Montotxt;
     Recargabss2=(Recargabss.divide(MonedaCon));
      
    }
    void Suma2(){
       //Monto1= txtMonto.getText();
        String ak=  txtMonto.getText();
       Montx(ak);
        fondobd1(Fondo2); 
      Suma=fondobd;
      Suma= Suma.add(Montotxt);
       Monto4=(Suma.multiply(MonedaCon)); 
      double mr= Double.valueOf(String.valueOf(Monto4));
      
       Montor= (int)Math.floor(mr);
      Recargabss= Montotxt;
     Recargabss2=(Recargabss.multiply(MonedaCon));
     
       double mr2= Double.valueOf(String.valueOf( Recargabss2));
      
        Recargabss3= (int)Math.floor(mr2);
      
    }
     void Resta(){
      // Monto1= txtMonto.getText();
       String ak=  txtMonto.getText();
       Montx(ak);
        fondobd1(Fondo1); 
      Suma=fondobd;
     Suma=  Suma.subtract(Montotxt);
       Monto4=(Suma.divide(MonedaCon)); 
      Recargabss= Montotxt;
     Recargabss2=(Recargabss.divide(MonedaCon));
      
    }
    void Resta2(){
     // Monto1= txtMonto.getText();
         String ak=  txtMonto.getText();
       Montx(ak);
        fondobd1(Fondo2); 
      Suma= fondobd;
      Suma= Suma.subtract(Montotxt);
       Monto4=(Suma.multiply(MonedaCon)); 
      double mr= Double.valueOf(String.valueOf(Monto4));
      
       Montor= (int)Math.floor(mr);
      Recargabss= Montotxt;
     Recargabss2=(Recargabss.multiply(MonedaCon));
      double mr2= Double.valueOf(String.valueOf( Recargabss2));
      
        Recargabss3= (int)Math.floor(mr2);
      
    }
     public static String fechaActual()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyy");
        return formatoFecha.format(fecha);
                }
      private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' }; 
     public static String encriptaEnMD5(String stringAEncriptar)  
     {        
         try        {     
             MessageDigest msgd = MessageDigest.getInstance("MD5");      
             byte[] bytes = msgd.digest(stringAEncriptar.getBytes());     
             StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length); 
             for (int i = 0; i < bytes.length; i++)    
             {            
                 int bajo = (int)(bytes[i] & 0x0f);  
             int alto = (int)((bytes[i] & 0xf0) >> 4);    
             strbCadenaMD5.append(CONSTS_HEX[alto]);      
             strbCadenaMD5.append(CONSTS_HEX[bajo]);           }  
             return strbCadenaMD5.toString();      
         } catch (NoSuchAlgorithmException e) {         
             return null;        }  
     }    
  
   
     
      void Busqueda(){
           id_Cuenta="";
          Cedula=txtCedula.getText();
          BigDecimal fondo11= new BigDecimal(0);
           BigDecimal fondo22= new BigDecimal(0);
          DecimalFormat dnFormat = new  DecimalFormat("#,##0.00");
          DecimalFormatSymbols simbolo=new  DecimalFormatSymbols();
                                        simbolo.setDecimalSeparator('.');
                                        simbolo.setGroupingSeparator(',');
                                        
                  DecimalFormat dnFormat1 = new  DecimalFormat("#.#####",simbolo);
            
          try{
       SQL2 = "select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5( Cedula )+ "%'";
      
     st = this.cn.createStatement();
     rs = st.executeQuery(SQL2);
      while (rs.next()) {
        id_Cuenta= rs.getString("id_cuentacliente");
        CedulaC= rs.getString("cedula_cliente");
          Fondo1= rs.getString("fondos_cuenta_bsf");
        Fondo2= rs.getString("fondos_cuenta_bss");
          fondo11= rs.getBigDecimal("fondos_cuenta_bsf");
       fondo22= rs.getBigDecimal("fondos_cuenta_bss");

           } 
      if(Activi5==1){
          jvfondo.setText("BsF"+" "+dnFormat.format( fondo11));
         
      
      }else{
          jvfondo.setText("BsS"+" "+dnFormat1.format(fondo22));
      }
      if(id_Cuenta.isEmpty()){
        Activi2=0;
        nuevo();
        
      }else{
      Activi2=1; 
      nuevo2();
     
      }
      
         } catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
}
      void Busqueda2(){
          Cedula=txtCedula.getText();
          try{
       SQL2 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5( Cedula )+ "%'";
      
     st = this.cn.createStatement();
  rs = st.executeQuery(SQL2);
      while (rs.next()) {
       Nombre= rs.getString("nombre_cliente");
       Apellido= rs.getString("apellido_cliente");
        jbnom.setText( Nombre);
        jbape.setText( Apellido);
        
         rutafo=(rs.getString("rutafoto_cliente"));
    
    String fu=rutafo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lebfoto.setIcon(new ImageIcon(foto));
             lebfoto.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No fo")){
         lebfoto.setIcon(null);
              lebfoto.setText("FOTO");
        }else{
                lebfoto.setText("");  
              }
            panecu.setVisible(true);
    
           } 
     
       
      
      
         } catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
}
      void Busqueda3(){
          Cedula=txtCedula.getText();
           BigDecimal fondo11= new BigDecimal(0);
           BigDecimal fondo22= new BigDecimal(0);
            DecimalFormat dnFormat = new  DecimalFormat("#,##0.00");
             DecimalFormatSymbols simbolo=new  DecimalFormatSymbols();
                                       simbolo.setDecimalSeparator('.');
                                        simbolo.setGroupingSeparator(',');
                                        
                  DecimalFormat dnFormat1 = new  DecimalFormat("#.#####",simbolo);
           
          try{
      SQL2 = "select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5( Cedula )+ "%'";
      
     st = this.cn.createStatement();
     rs = st.executeQuery(SQL2);
      while (rs.next()) {
       
          Fondo1= rs.getString("fondos_cuenta_bsf");
        Fondo2= rs.getString("fondos_cuenta_bss");
   fondo11= rs.getBigDecimal("fondos_cuenta_bsf");
       fondo22= rs.getBigDecimal("fondos_cuenta_bss");
           } 
      if(Activi5==1){
           
        jvfondo.setText("BsF"+" "+dnFormat.format( fondo11));
       
      
      }else{
         jvfondo.setText("BsS"+" "+dnFormat1.format(fondo22));
      }
     
           
     
       
      
      
         } catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
}
void Guardar(){
  
    Cedula=txtCedula.getText();
    String mo=jbsin.getText();
    if(jRadioButton2.isSelected()){
     NumeroR1="0";   
    }else{
        NumeroR1=txtRef.getText();
    }
    if(jRadioButton3.isSelected()){
    ProcesoC="RECARGA CyberPlay";   
    }else{
      ProcesoC="DEVITO CyberPlay";   
    }
  //NumeroR1=txtRef.getText();
 
        
 
        switch(Activi){
            
            case 1:

            try{
           int resp = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
     if (resp == 0) {
       
        
                SQL="UPDATE cuentac_cliente_cyberplay set fondos_cuenta_bsf=?,fondos_cuenta_bss=? WHERE cedula_cliente=? ";
                psd = cn.prepareStatement(SQL);
                psd.setBigDecimal(1,Suma);
                psd.setBigDecimal(2,Monto4);
                psd.setString(3,encriptaEnMD5(Cedula));
               
                 int m= psd.executeUpdate();
                  
              
                   SQL2= "INSERT INTO seguimiento_cuenta_cyberplay (id_cuentacliente,cedula_cliente,fondos_cuenta_bsf,fondos_cuenta_bss,recarga_retiro_cuenta_bsf,recarga_retiro_cuenta_bss,proceso_cuenta,numero_referencia,fecharecar_cuenta) values (?,?,?,?,?,?,?,?,?) ";
                psd = cn.prepareStatement(SQL2);
            
                psd.setString(1,id_Cuenta);
                psd.setString(2,CedulaC);
                 psd.setBigDecimal(3,Suma);
                psd.setBigDecimal(4,Monto4);
                 psd.setBigDecimal(5,Montotxt);
                psd.setBigDecimal(6,Recargabss2);
                
                 psd.setString(7, ProcesoC);
                    psd.setString(8, NumeroR1);
                 
                psd.setString(9,fechaP=fechaActual());
               
               
               
                 int n= psd.executeUpdate();
                

                if((n>0)){
                      DecimalFormat dnFormat = new  DecimalFormat("#,##0.00");
            Busqueda3();   
if(jRadioButton3.isSelected()) {  
    
JOptionPane.showMessageDialog(null,"Recargado con exito: "+jbsin.getText()+" "+dnFormat.format(Montotxt));
                   
      }else{
          JOptionPane.showMessageDialog(null,"Devitado con exito: "+jbsin.getText()+" "+dnFormat.format(Montotxt));
      }
             nuevo();
                 // generarcuenta();
                 Activi3=0;
                  bloqueo();
                  Busqueda();
         cargarCuenta() ;
          txtRef.setVisible(false);  
jLabel3.setVisible(false);      
                  // nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
                
                    }
       }else{
          
      }
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
          

            break;
        

               
            case 2:
            
             try{
           int resp = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
     if (resp == 0) {
       
        
                SQL="UPDATE cuentac_cliente_cyberplay set fondos_cuenta_bsf=?,fondos_cuenta_bss=? WHERE cedula_cliente=? ";
                psd = cn.prepareStatement(SQL);
                psd.setInt(1, Montor);
                psd.setBigDecimal(2,Suma);
                psd.setString(3,encriptaEnMD5(Cedula));
               
                 int m= psd.executeUpdate();
                  
              
                   SQL2= "INSERT INTO seguimiento_cuenta_cyberplay (id_cuentacliente,cedula_cliente,fondos_cuenta_bsf,fondos_cuenta_bss,recarga_retiro_cuenta_bsf,recarga_retiro_cuenta_bss,proceso_cuenta,numero_referencia,fecharecar_cuenta) values (?,?,?,?,?,?,?,?,?) ";
                psd = cn.prepareStatement(SQL2);
            
                psd.setString(1,id_Cuenta);
                psd.setString(2,CedulaC);
                 psd.setInt(3,Montor);
                psd.setBigDecimal(4,Suma);
                psd.setInt(5,Recargabss3);
                 psd.setBigDecimal(6,Montotxt);
                 
                 psd.setString(7, ProcesoC);
                 psd.setString(8, NumeroR1);
                psd.setString(9,fechaP=fechaActual());
               
               
               
                 int n= psd.executeUpdate();
                

                if((n>0)){
               Busqueda3();   
               DecimalFormatSymbols simbolo=new  DecimalFormatSymbols();
                                       simbolo.setDecimalSeparator('.');
                                        simbolo.setGroupingSeparator(',');
                                        
                  DecimalFormat dnFormat = new  DecimalFormat("#.#####",simbolo);
                  // nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
      if(jRadioButton3.isSelected()) {            
JOptionPane.showMessageDialog(null,"Recargado con exito: "+jbsin.getText()+" "+dnFormat.format(Montotxt));
                   
      }else{
          JOptionPane.showMessageDialog(null,"Devitado con exito: "+jbsin.getText()+" "+dnFormat.format(Montotxt));
      }
         nuevo();
                 // generarcuenta();
                Activi3=0;
                 bloqueo();
                
                  Busqueda();
         cargarCuenta();
          txtRef.setVisible(false);  
jLabel3.setVisible(false);
jRadioButton1.setSelected(false);
      }
       }else{
          
      }
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
          

            break;
                 case 3:
                       try{
           int resp = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
     if (resp == 0) {
       
        
                SQL="UPDATE seguimiento_cuenta_cyberplay  set numero_referencia=? WHERE id_cuenta=? ";
                psd = cn.prepareStatement(SQL);
               
                psd.setString(1, NumeroR1);
                psd.setString(2,id_seguimiento);
               
                 int m= psd.executeUpdate();
                  
              
                  

                if((m>0)){
               Busqueda3();   
                  // nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
              
JOptionPane.showMessageDialog(null,"Guardado con exito!");
    
         nuevo();
                 // generarcuenta();
                Activi3=0;
                 Activi=1;
                 bloqueo();
                
                  Busqueda();
         cargarCuenta();
          txtRef.setVisible(false);  
jLabel3.setVisible(false);
jRadioButton1.setSelected(false);
 
      }
       }else{
          
      }
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
                     break;
                     
        }
}
void NumeroV(){
     String cedu1;
       byte contLetraMay = 0, contLetraMin=0;
        cedu1 =txtMonto.getText();
         char cedu2;
       for (byte i = 0; i < cedu1.length(); i++) {
                cedu2 = cedu1.charAt(i);
               String ceduValue = String.valueOf(cedu2);
        
               
                 if (ceduValue.matches("[A-Z]")) {
                    contLetraMay++;
                } 
                if (ceduValue.matches("[a-z]")) {
                    contLetraMin++;
                } 
        }
        if(   contLetraMay!=0 ||contLetraMin!=0 ){
            cont=1;
       JOptionPane.showMessageDialog(this,"Introdusca Solo Numeros!"); 
     } else{
           cont=0;
             
        } 
}
void Numero2(){
     String cedu1;
       byte contLetraMay = 0, contLetraMin=0;
        cedu1 =txtRef.getText();
         char cedu2;
       for (byte i = 0; i < cedu1.length(); i++) {
                cedu2 = cedu1.charAt(i);
               String ceduValue = String.valueOf(cedu2);
        
               
                 if (ceduValue.matches("[A-Z]")) {
                    contLetraMay++;
                } 
                if (ceduValue.matches("[a-z]")) {
                    contLetraMin++;
                } 
        }
        if(   contLetraMay!=0 ||contLetraMin!=0 ){
            cont2=1;
       JOptionPane.showMessageDialog(this,"Introdusca Solo Numeros!"); 
     } else{
           cont2=0;
             
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
        lbno = new javax.swing.JLabel();
        lbap = new javax.swing.JLabel();
        jvfondo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panep = new javax.swing.JPanel();
        btnuevo = new javax.swing.JButton();
        btguardar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btregre = new javax.swing.JButton();
        panefo = new javax.swing.JPanel();
        lebfoto = new javax.swing.JLabel();
        jbfon = new javax.swing.JLabel();
        panecu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacuenta = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jbape = new javax.swing.JLabel();
        jbnom = new javax.swing.JLabel();
        paneb = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        panecar = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        txtRef = new javax.swing.JTextField();
        btaceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbsin = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        txtMonto = new javax.swing.JFormattedTextField();
        pnCono = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btbsf = new javax.swing.JButton();
        btbss = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Jjblogo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CyberPlayPagos ");

        jPanel1.setBackground(new java.awt.Color(173, 207, 249));
        jPanel1.setLayout(null);

        lbno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbno.setForeground(new java.awt.Color(255, 255, 255));
        lbno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbno.setText("NOMBRE");
        lbno.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbno);
        lbno.setBounds(130, 140, 130, 19);

        lbap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbap.setForeground(new java.awt.Color(255, 255, 255));
        lbap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbap.setText("APELLIDO");
        lbap.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbap);
        lbap.setBounds(280, 140, 130, 19);

        jvfondo.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jvfondo.setForeground(new java.awt.Color(255, 255, 255));
        jvfondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jvfondo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jvfondo);
        jvfondo.setBounds(130, 190, 280, 60);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Android", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-----------------------CUENTE DEL CLIENTE-----------------------");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 740, 40);

        panep.setBackground(new java.awt.Color(0, 0, 0));
        panep.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), null));

        btnuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnuevo.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btnuevo.setText("NUEVO");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
            }
        });

        btguardar.setBackground(new java.awt.Color(255, 255, 255));
        btguardar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/download.png"))); // NOI18N
        btguardar.setText("GARDAR");
        btguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btguardarActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/File_List.png"))); // NOI18N
        jButton3.setText("EDITAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btregre.setBackground(new java.awt.Color(255, 153, 153));
        btregre.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btregre.setText("<<Regresar");
        btregre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btregreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panepLayout = new javax.swing.GroupLayout(panep);
        panep.setLayout(panepLayout);
        panepLayout.setHorizontalGroup(
            panepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btregre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panepLayout.setVerticalGroup(
            panepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panepLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btregre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel1.add(panep);
        panep.setBounds(10, 50, 117, 270);

        panefo.setBackground(new java.awt.Color(72, 97, 231));
        panefo.setForeground(new java.awt.Color(204, 204, 204));

        lebfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lebfoto.setText("FOTO");
        lebfoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lebfotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panefoLayout = new javax.swing.GroupLayout(panefo);
        panefo.setLayout(panefoLayout);
        panefoLayout.setHorizontalGroup(
            panefoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        panefoLayout.setVerticalGroup(
            panefoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel1.add(panefo);
        panefo.setBounds(220, 50, 100, 80);

        jbfon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbfon.setForeground(new java.awt.Color(255, 255, 255));
        jbfon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbfon.setText("FONDO ACTUAL");
        jbfon.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jbfon);
        jbfon.setBounds(180, 260, 190, 20);

        panecu.setBackground(new java.awt.Color(255, 255, 255));
        panecu.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        tacuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tacuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tacuentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tacuenta);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SEGUIMIENTO DE CUENTA ");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panecuLayout = new javax.swing.GroupLayout(panecu);
        panecu.setLayout(panecuLayout);
        panecuLayout.setHorizontalGroup(
            panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panecuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panecuLayout.setVerticalGroup(
            panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panecuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panecu);
        panecu.setBounds(10, 330, 710, 140);

        jbape.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbape.setForeground(new java.awt.Color(255, 255, 255));
        jbape.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jbape);
        jbape.setBounds(280, 160, 130, 20);

        jbnom.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbnom.setForeground(new java.awt.Color(255, 255, 255));
        jbnom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jbnom);
        jbnom.setBounds(130, 160, 130, 20);

        paneb.setBackground(new java.awt.Color(255, 255, 255));
        paneb.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        paneb.setLayout(null);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        paneb.add(jButton2);
        jButton2.setBounds(170, 10, 110, 40);

        txtCedula.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        paneb.add(txtCedula);
        txtCedula.setBounds(10, 20, 155, 26);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cedula del Cliente");
        paneb.add(jLabel6);
        jLabel6.setBounds(10, 0, 155, 20);

        jPanel1.add(paneb);
        paneb.setBounds(430, 50, 290, 60);

        panecar.setBackground(new java.awt.Color(255, 255, 255));
        panecar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));
        panecar.setLayout(null);

        jRadioButton1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jRadioButton1.setText("SI");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        panecar.add(jRadioButton1);
        jRadioButton1.setBounds(50, 20, 50, 20);

        jRadioButton2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jRadioButton2.setText("NO");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        panecar.add(jRadioButton2);
        jRadioButton2.setBounds(160, 20, 50, 20);

        txtRef.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        txtRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRefActionPerformed(evt);
            }
        });
        panecar.add(txtRef);
        txtRef.setBounds(20, 62, 220, 30);

        btaceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btaceptar.setText("ACEPTAR");
        btaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaceptarActionPerformed(evt);
            }
        });
        panecar.add(btaceptar);
        btaceptar.setBounds(90, 160, 100, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("     ¿POSEE CAPTURA?");
        panecar.add(jLabel1);
        jLabel1.setBounds(4, 4, 240, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MONTO A ");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        panecar.add(jLabel2);
        jLabel2.setBounds(20, 100, 220, 18);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NUMERO DE REFERENCIA");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        panecar.add(jLabel3);
        jLabel3.setBounds(20, 40, 220, 18);

        jbsin.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbsin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbsin.setText("BsF");
        panecar.add(jbsin);
        jbsin.setBounds(20, 120, 50, 30);

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(51, 102, 255));
        jRadioButton3.setText("+");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        panecar.add(jRadioButton3);
        jRadioButton3.setBounds(20, 160, 60, 20);

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 0, 51));
        jRadioButton4.setText(" -");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        panecar.add(jRadioButton4);
        jRadioButton4.setBounds(20, 180, 60, 20);

        txtMonto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMonto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMonto.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        txtMonto.setFocusLostBehavior(javax.swing.JFormattedTextField.REVERT);
        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMonto.setName("");
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        panecar.add(txtMonto);
        txtMonto.setBounds(70, 120, 170, 30);

        jPanel1.add(panecar);
        panecar.setBounds(450, 110, 250, 210);

        pnCono.setBackground(new java.awt.Color(255, 255, 255));
        pnCono.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ESCOJA CONO MONETARIO EN QUE DESA USTED TRABAJAR");
        pnCono.add(jLabel5);
        jLabel5.setBounds(0, 64, 740, 77);

        btbsf.setBackground(new java.awt.Color(51, 51, 51));
        btbsf.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        btbsf.setForeground(new java.awt.Color(255, 255, 255));
        btbsf.setText("Bolivar Fuerte");
        btbsf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbsfActionPerformed(evt);
            }
        });
        pnCono.add(btbsf);
        btbsf.setBounds(120, 360, 220, 54);

        btbss.setBackground(new java.awt.Color(51, 51, 51));
        btbss.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        btbss.setForeground(new java.awt.Color(255, 255, 255));
        btbss.setText("Bolivar Soberano");
        btbss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbssActionPerformed(evt);
            }
        });
        pnCono.add(btbss);
        btbss.setBounds(390, 360, 220, 54);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/moneda2.png"))); // NOI18N
        pnCono.add(jLabel8);
        jLabel8.setBounds(360, 120, 280, 240);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/moneda1.png"))); // NOI18N
        pnCono.add(jLabel10);
        jLabel10.setBounds(110, 130, 250, 220);

        jPanel1.add(pnCono);
        pnCono.setBounds(0, 0, 740, 490);

        Jjblogo.setBackground(new java.awt.Color(255, 255, 255));
        Jjblogo.setFont(new java.awt.Font("Android", 1, 24)); // NOI18N
        Jjblogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Jjblogo.setOpaque(true);
        jPanel1.add(Jjblogo);
        Jjblogo.setBounds(0, 490, 740, 80);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(-6, 0, 750, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaceptarActionPerformed
  cont=0;cont2=0;  selec=0;
        NumeroV();
  
  if(jRadioButton1.isSelected()){
      String ref= txtRef.getText();
       Numero2();
      if(ref.isEmpty()){
          cont2=1;
           JOptionPane.showMessageDialog(this,"Escriba referencia!");
      } 
      selec=1;
  }
  if(jRadioButton2.isSelected()){
   selec=1;    
  }
  if(selec==0){
        JOptionPane.showMessageDialog(this,"Por favor seleccione respuesta!");
  }
  String Txtmo=txtMonto.getText();
  
  if(Txtmo.isEmpty()){
     JOptionPane.showMessageDialog(this,"Campos Vacios!");
  }else{
  if((cont==0 && cont2==0) && selec==1){
      
         DecimalFormat dnFormat = new  DecimalFormat("#,##0.00");
             String ak=  txtMonto.getText();
       Montx(ak);
String Muestra=dnFormat.format(Montotxt);

 
           String refe=txtRef.getText();                
    if(refe.isEmpty()){
       refe="Ninguna"; 
    }
        int resp = JOptionPane.showConfirmDialog(this, "¿Son correctos los datos?"+"\n        "+jbsin.getText()+"  "+Muestra+"\n         Ref: "+refe, "Validación de datos", 0);
     if (resp == 0) {
       
        if(Activi==1){
            if(jRadioButton3.isSelected()){
    Suma();  
    }else{
          
                Resta();
            }
     txtMonto.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            btaceptar.setEnabled(false);
             jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
         txtRef.setEnabled(false);
            
}else{
              if(jRadioButton3.isSelected()){
    Suma2();  
    }else{
                Resta2();
            }
   
    txtMonto.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            btaceptar.setEnabled(false);
             jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        txtRef.setEnabled(false);
}
        Activi3=1;
     }
  }}
            // TODO add your handling code here:
    }//GEN-LAST:event_btaceptarActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
if(jRadioButton1.isSelected()){
        txtRef.setVisible(true); 

jLabel3.setVisible(true);
jRadioButton2.setSelected(false);

}// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
if(jRadioButton2.isSelected()){
        txtRef.setVisible(false);  
jLabel3.setVisible(false);
jRadioButton1.setSelected(false);
 NumeroR1="PENDIENTE";
 
}// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void btguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguardarActionPerformed
 if(Activi3==1){
        Guardar();
 } else{
     JOptionPane.showMessageDialog(null,"ERROR: Por favor valide los datos!"); 
 }
      // TODO add your handling code here:
    }//GEN-LAST:event_btguardarActionPerformed

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
 Activi4=0;
 Activi=Activi5;
 bloqueosu();
        desblobloqueo(); 

nuevo();

                
                  Busqueda();
         cargarCuenta() ;// TODO add your handling code here:
    }//GEN-LAST:event_btnuevoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    Busqueda2();
        Busqueda();
        if(Activi4==1){
            txtMonto.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            btaceptar.setEnabled(false);
             jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        txtRef.setEnabled(true);  
         txtRef.setVisible(true); 
jLabel3.setVisible(true);
jRadioButton1.setSelected(true);
        }else{
        if( Activi2==1 ){
            txtMonto.setEnabled(true);
            jRadioButton3.setEnabled(true);
            jRadioButton4.setEnabled(true);
            btaceptar.setEnabled(true);
             jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
        txtRef.setEnabled(true);
       
       
        }else{
             txtMonto.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            btaceptar.setEnabled(false);
             jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        txtRef.setEnabled(false);
        }
        }
         // txtRef.setVisible(false); 
//jLabel3.setVisible(false);
   cargarCuenta() ;// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lebfotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lebfotoMouseClicked
// TODO add your handling code here:

    }//GEN-LAST:event_lebfotoMouseClicked

    private void btbsfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbsfActionPerformed
Activi5=Activi=1;
        pnCono.setVisible(false); 
 paneb.setVisible(true);
        panecu.setVisible(true);
        panep.setVisible(true);
        panefo.setVisible(true);
        lbno.setVisible(true);
        lbap.setVisible(true);
        jbnom.setVisible(true);
        jbape.setVisible(true);
         jbfon.setVisible(true);
          jvfondo.setVisible(true);
          panecar.setVisible(true);
           btregre.setVisible(true);
 txtRef.setVisible(false);  
jLabel3.setVisible(false);
jRadioButton1.setSelected(false);
jRadioButton1.setSelected(false);
jLabel2.setText("MONTO A RECARGAR"); 
     txtMonto.setForeground(Color.BLUE);
     jRadioButton3.setSelected(true);
     jRadioButton4.setSelected(false);
           jbsin.setText("BsF");
           Jjblogo.setText("BOLIVAR FUERTE");
            cargarCuenta() ;
             Busqueda2();
             bloqueo();
             
// TODO add your handling code here:
    }//GEN-LAST:event_btbsfActionPerformed

    private void btbssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbssActionPerformed
Activi5=Activi=2;
        pnCono.setVisible(false); 
 paneb.setVisible(true);
        panecu.setVisible(true);
        panep.setVisible(true);
        panefo.setVisible(true);
        lbno.setVisible(true);
        lbap.setVisible(true);
        jbnom.setVisible(true);
        jbape.setVisible(true);
         jbfon.setVisible(true);
          jvfondo.setVisible(true);
          panecar.setVisible(true);
           btregre.setVisible(true);
           txtRef.setVisible(false);  
jLabel3.setVisible(false);
jRadioButton1.setSelected(false);
jRadioButton1.setSelected(false);
jLabel2.setText("MONTO A RECARGAR"); 
     txtMonto.setForeground(Color.BLUE);
     jRadioButton3.setSelected(true);
     jRadioButton4.setSelected(false);
            jbsin.setText("BsS");
              Jjblogo.setText("BOLIVAR SOBERANO");
            cargarCuenta() ;
             Busqueda2();
             bloqueo();// TODO add your handling code here:
    }//GEN-LAST:event_btbssActionPerformed

    private void btregreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btregreActionPerformed
  paneb.setVisible(false);
        panecu.setVisible(false);
        panep.setVisible(false);
        panefo.setVisible(false);
        lbno.setVisible(false);
        lbap.setVisible(false);
        jbnom.setVisible(false);
        jbape.setVisible(false);
         jbfon.setVisible(false);
          jvfondo.setVisible(false);
          panecar.setVisible(false);
          btregre.setVisible(false);
           nuevo();
           Jjblogo.setText("");
     
     pnCono.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btregreActionPerformed

    private void txtRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRefActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
if(jRadioButton3.isSelected()){
     jLabel2.setText("MONTO A RECARGAR"); 
     txtMonto.setForeground(Color.BLUE);
     jRadioButton4.setSelected(false);
}
             // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
if(jRadioButton4.isSelected()){
     jLabel2.setText("MONTO A DEVITAR"); 
     txtMonto.setForeground(Color.RED);
             //txtcedu.setBackground(Color.WHITE); 
     jRadioButton3.setSelected(false);
}
        
                 // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
Activi=3;  
Activi3=1;
Activi2=0;
Activi4=1;
bloqueosu();
desblobloqueo(); 

nuevo();
 
                
                  Busqueda();
         cargarCuenta() ;// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tacuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tacuentaMouseClicked
int i = tacuenta.getSelectedRow();  
      id_seguimiento=(String) tacuenta.getValueAt(i,0); 
               // TODO add your handling code here:
    }//GEN-LAST:event_tacuentaMouseClicked

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

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
            java.util.logging.Logger.getLogger(ModRecarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModRecarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModRecarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModRecarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModRecarCliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jjblogo;
    private javax.swing.JButton btaceptar;
    private javax.swing.JButton btbsf;
    private javax.swing.JButton btbss;
    private javax.swing.JButton btguardar;
    private javax.swing.JButton btnuevo;
    private javax.swing.JButton btregre;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbape;
    private javax.swing.JLabel jbfon;
    private javax.swing.JLabel jbnom;
    private javax.swing.JLabel jbsin;
    private javax.swing.JLabel jvfondo;
    private javax.swing.JLabel lbap;
    private javax.swing.JLabel lbno;
    private javax.swing.JLabel lebfoto;
    private javax.swing.JPanel paneb;
    private javax.swing.JPanel panecar;
    private javax.swing.JPanel panecu;
    private javax.swing.JPanel panefo;
    private javax.swing.JPanel panep;
    private javax.swing.JPanel pnCono;
    private javax.swing.JTable tacuenta;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JFormattedTextField txtMonto;
    private javax.swing.JTextField txtRef;
    // End of variables declaration//GEN-END:variables
}
