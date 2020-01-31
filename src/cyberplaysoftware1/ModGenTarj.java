/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;

import ConexionBd.CBDPLAY;

import com.barcodelib.barcode.QRCode;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Gtecno
 */
public class ModGenTarj extends javax.swing.JFrame {
String nomb,codigoc,codigoc3="null", codigoc4="null",codigoc5="null",codigoc2,rutafo,codigotar,tipo,estado,clave,fechac,fechav,codigotar3,codigotar4,codigotar5,idta,tipota,estadota,fechata,rutaf,nomb1,ape1,rufoq,cliented;
double cuenta_bsf, cuenta_bss;
    CBDPLAY cc = new CBDPLAY();
 Connection cn = cc.Conectar();
   Statement  st;ResultSet rs;
  String SQL,SQL2,SQL3,SQL4;
  DefaultTableModel modelo;
    PreparedStatement psd;
  int Activi,Activi2,cont,tele2,dia,mes,ano,ano2,mes2,dia2, fecha;
    private static int udm =0;
 private static int resolution=72;
 private static float margeniz=0.000f;
 private static float margende=0.000f;
 private static float margensu=0.000f;
 private static float margenin=0.000f;
 private static int rot =0;
 private static float tamanomodulo =5.000f;
    
    
    /**
     * Creates new form ModGenTarj
     */
    public ModGenTarj() {
        initComponents();
           setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       i();
 fechaActual();
        jbfecha.setText(fechaActual());
         btimpri.setEnabled(false);
         spaño.setEnabled(false);
         btedf.setEnabled(false);
         btgf.setEnabled(false);
          tacuenta.setEnabled(false);
          tacuenta.setVisible(false);
          bloque();
    }
      void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/ico.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
    void Expira(){
       ano2= Integer.valueOf(fechaActual2());
        mes2=Integer.valueOf(fechaActual2_1());
        dia2= Integer.valueOf(fechaActual2_2());
        if(ano2>=ano){
           if(ano2>ano){
                JOptionPane.showMessageDialog(this,"La Tarjeta a Expirado año!");
                estado="Expirada";
                try{
                 SQL="UPDATE tarjeta_cliente set estado_tarjeta=? WHERE cedula_cliente=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,estado);
               
                 psd.setString(2,encriptaEnMD5(codigoc));
                int f=  psd.executeUpdate();
                }catch(Exception ex){
                    
                }
           }
            if(ano2==ano){
               if(mes2>=mes){
                  if(mes2>mes){
                  JOptionPane.showMessageDialog(this,"La Tarjeta a Expirado mes!"); 
                     estado="Expirada";
                     try{
                 SQL="UPDATE tarjeta_cliente set estado_tarjeta=? WHERE cedula_cliente=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,estado);
               
                 psd.setString(2,encriptaEnMD5(codigoc));
                int f=  psd.executeUpdate();
                }catch(Exception ex){
                    
                }
               } 
                  if(mes2==mes){
                   if(dia2>=dia){
                   JOptionPane.showMessageDialog(this,"La Tarjeta a Expirado dia!");  
                      estado="Expirada";
                      try{
                 SQL="UPDATE tarjeta_cliente set estado_tarjeta=? WHERE cedula_cliente=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,estado);
               
                 psd.setString(2,encriptaEnMD5(codigoc));
                int f=  psd.executeUpdate();
                }catch(Exception ex){
                    
                }
               }  
               }  
               }
           }
           
        }
    }
    void Seguridad(){
     cont=0;
     clave=txtclave.getText();
     
     tipo= String.valueOf(jboxt.getSelectedItem());
   
  if(tipo.equals("--Selección--")||clave.isEmpty()){
       JOptionPane.showMessageDialog(this,"Campos Vacios!");
       cont=1;
  }else{
   cont=0;
    String tele1=clave;  
    char[] arrayCha= tele1.toCharArray();
    for(int i=0; i< arrayCha.length; i++){
    arrayCha[i]='0';
    tele2=tele1.length();
    }
    if(tele2 <4||tele2 >4 ){
     cont=1;   
     if(tele2 <4){
         txtclave.setBackground(Color.red);
      JOptionPane.showMessageDialog(this,"Clave Corta!"); 
     }else{
         txtclave.setBackground(Color.red);
          JOptionPane.showMessageDialog(this,"Solo son 4 digitos para la Clave!"); 
     }
    }else{
        cont=0;
    }
   
    
    if(cont!=1){
        NumeroV();
         
    }
     //if(cont!=1&&(Activi==3||Activi==1)){
     //   ComproCedula();
        
   // }
    

  }
    }
   void NumeroV(){
     String cedu1;
       byte contLetraMay = 0, contLetraMin=0;
        cedu1 =txtclave.getText();
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
           txtclave.setBackground(Color.red);
       JOptionPane.showMessageDialog(this,"Introdusca Solo Numeros!");
       
     } else{
           cont=0;
             
        } 
}
    void GenerarQR(){
         QRCode codigoQR= new QRCode(); 
    codigoQR.setData(codigotar4);
    codigoQR.setDataMode(QRCode.MODE_BYTE);
     codigoQR.setUOM(udm);
      codigoQR.setLeftMargin(margeniz);
       codigoQR.setResolution(resolution);
       codigoQR.setRightMargin(margende);
       codigoQR.setTopMargin(margensu);
       codigoQR.setBottomMargin(margenin);
       codigoQR.setRotate( rot);
        codigoQR.setModuleSize(tamanomodulo);
           
        try {
            
          
           
            File f = new File("C:/Qr");
               f.mkdirs();
     FileOutputStream  fos = new FileOutputStream("C:/Qr/".concat(codigotar4).concat(".jpg"));
     codigoQR.renderBarcode(fos);
        } catch (Exception ex) {
            Logger.getLogger(ModGenTarj.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          // Desktop d=Desktop.getDesktop();
        //try {
            //d.open(new File(Archivo));
       // } catch (IOException ex) {
         //   Logger.getLogger(ModGenTarj.class.getName()).log(Level.SEVERE, null, ex);
       // }
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
  void expi(){
      int ano1;
     fecha=Integer.valueOf(fechaActual2());
    
        try{
  
     
   SQL4 = "SELECT * FROM expira_tarjeta WHERE id LIKE '%" +1+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL4);
      rs.next();        
      //desbloqueo();
      
     ano1=( rs.getInt("fecha_expi"));
        
           
  
               
   fecha=fecha+ano1;
    mes=Integer.valueOf(fechaActual2_1());
     dia=Integer.valueOf(fechaActual2_2());
   
  fechav=fechaActual3()+"/"+String.valueOf(fecha);
     jbexp.setText("Exp:"+" "+fechav);          
            }
       
          
      
   
        catch (SQLException ex) {}
  }
  void limpiar(){
      lebfoto3.setIcon(null);
      jbnon.setText("");
      jbape.setText("");
      lebfoto.setIcon(null);
      txtnuro1.setText("Codigo:");
      jbem.setText("Emi:");
       jbexp.setText("Exp:");
       jboxt.setSelectedItem("--Selección--");
       txtclave.setText("");
  }
  void bloque(){
    jboxt.setEnabled(false);  
    txtclave.setEnabled(false);
    btimpri.setEnabled(false);
     tacuenta.setEnabled(false);
    tacuenta.setVisible(false);
  }
  void desbloq(){
     jboxt.setEnabled(true);  
    txtclave.setEnabled(true);  
    // tacuenta.setEnabled(true);
   // tacuenta.setVisible(true);
  }
  void nuevor(){
     
      codigoc3="null"; codigoc4="null";
      cuenta_bsf=0;
      cuenta_bss=0;
   // nuevo();
    //fecha2="";
      try{
   codigoc = JOptionPane.showInputDialog("Cedula de Cliente:");
   if(codigoc.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula!");
       bloque();
                 
   }else{
  codigoc2=codigoc;
  /*
 SQL3 = "select * from tarjeta_cliente where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc5 = rs.getString("cedula_cliente");
         codigoc5 = rs.getString("tipo_tarjeta");
           codigoc5 = rs.getString("cedula_cliente");
       
           } 
        if((codigoc5.equals(encriptaEnMD5(codigoc)))){
        
        
        }
        * 
        */
    SQL3 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc3 = rs.getString("cedula_cliente");
           } 
        if((codigoc3.equals(encriptaEnMD5(codigoc)))){
       
              SQL3 = "select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc4 = rs.getString("cedula_cliente");
      cuenta_bsf=rs.getDouble("fondos_cuenta_bsf");
       cuenta_bss=rs.getDouble("fondos_cuenta_bss");
           } 
        if((codigoc4.equals(encriptaEnMD5(codigoc)))){
            if( (cuenta_bsf<=0)||(cuenta_bss<=0) ){
                  JOptionPane.showMessageDialog(this, "No posee suficientes fondos");
            }else{
   SQL2 = "SELECT * FROM cliente_cyberplay WHERE cedula_cliente LIKE '%" +encriptaEnMD5(codigoc)+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
      desbloq();
      
        jbnon.setText( rs.getString("nombre_cliente"));
        nomb1=jbnon.getText();
            jbape.setText(rs.getString("apellido_cliente")); 
        ape1=jbape.getText();
               
   rutafo=(rs.getString("rutafoto_cliente"));
    
    String fu=rutafo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lebfoto3.setIcon(new ImageIcon(foto));
             lebfoto3.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No fo")){
         lebfoto.setIcon(null);
             lebfoto3.setText("FOTO");
        }else{
               lebfoto3.setText("");  
              }
             jbem.setText("Emi:"+" "+fechaActual());
         expi();
        
            }
          
        }else{
              JOptionPane.showMessageDialog(this, "No posee Cuenta");
        }
           
        }else{
              JOptionPane.showMessageDialog(this, "NO esta en Sistema");
        }
   }
        }catch (SQLException ex) {}
}
  void Editar(){
       codigoc3="null"; codigoc4="null";
      cuenta_bsf=0;
      cuenta_bss=0;
   // nuevo();
    //fecha2="";
      try{
   codigoc = JOptionPane.showInputDialog("Cedula de Cliente:");
   if(codigoc.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula!");
         bloque();
                 
   }else{
  codigoc2=codigoc;
 
    SQL3 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc3 = rs.getString("cedula_cliente");
           } 
        if((codigoc3.equals(encriptaEnMD5(codigoc)))){
       
              SQL3 = "select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc4 = rs.getString("cedula_cliente");
     // cuenta_bsf=rs.getDouble("fondos_cuenta_bsf");
      // cuenta_bss=rs.getDouble("fondos_cuenta_bss");
           } 
        if((codigoc4.equals(encriptaEnMD5(codigoc)))){
           
                 SQL3 = "select * from tarjeta_cliente where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc5 = rs.getString("cedula_cliente");
           } 
        if((codigoc5.equals(encriptaEnMD5(codigoc)))){
         
            SQL2 = "SELECT * FROM tarjeta_cliente WHERE cedula_cliente LIKE '%" +encriptaEnMD5(codigoc)+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
      desbloq();
      
       codigotar3=  rs.getString("tarjetaid_tarjeta");
          txtnuro1.setText("Codigo:"+" "+codigotar3);
           jboxt.setSelectedItem(rs.getString("tipo_tarjeta"));
           
            	estado=rs.getString("estado_tarjeta");
                clave=rs.getString("clave_tarjeta");
                fechac= rs.getString("fechacrea_tarjeta");
                jbem.setText( "Emi:"+" "+fechac);
                fechav= rs.getString("fechaven_tarjeta");
                jbexp.setText( "Expi:"+" "+fechav);
                dia= rs.getInt("diae");
                mes= rs.getInt("mese");
                ano= rs.getInt("anoe");
                rutaf=(rs.getString("rutafoto")); 
    String fu1=rutaf;
     File ruta1 = new File(fu1);
          String d1=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(d1);
             foto1= foto1.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lebfoto.setIcon(new ImageIcon(foto1));
             lebfoto.setIcon(new ImageIcon(foto1));
            if(fu1.equals("")||fu1.equals("No fo")){
         lebfoto.setIcon(null);
            lebfoto.setText("FOTO");
        }else{
               lebfoto.setText("");  
              }
       
   SQL2 = "SELECT * FROM cliente_cyberplay WHERE cedula_cliente LIKE '%" +encriptaEnMD5(codigoc)+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
      //desbloqueo();
      
        jbnon.setText( rs.getString("nombre_cliente"));
        
            jbape.setText(rs.getString("apellido_cliente")); 
  
               
   rutafo=(rs.getString("rutafoto_cliente")); 
    String fu=rutafo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lebfoto3.setIcon(new ImageIcon(foto));
             lebfoto3.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No fo")){
         lebfoto3.setIcon(null);
             lebfoto3.setText("FOTO");
        }else{
               lebfoto3.setText("");  
              }
             jbem.setText("Emi:"+" "+fechaActual());
         //expi();
         Expira();
            }
           else{
              JOptionPane.showMessageDialog(this, "No posee Tarjeta");
        }
            
        }else{
              JOptionPane.showMessageDialog(this, "No posee Cuenta");
        }
           
        }else{
              JOptionPane.showMessageDialog(this, "NO esta en Sistema");
        }
   }
        }catch (SQLException ex) {}
  }
void Guardar(){
  
   codigotar="0";
tipo=String.valueOf(jboxt.getSelectedItem());

clave=txtclave.getText();
fechac= fechaActual();
 expi();
        
 
        switch(Activi){
           
            case 1:

            try{
           int resp = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
      if (resp == 0) {
       
        
                SQL="INSERT INTO tarjeta_cliente(cedula_cliente,codigo_tarjeta,tipo_tarjeta,estado_tarjeta,clave_tarjeta,fechacrea_tarjeta,fechaven_tarjeta,diae,mese,anoe,rutafoto)VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,encriptaEnMD5(codigoc));
                psd.setString(2, codigotar);
                psd.setString(3,tipo);
                psd.setString(4,estado="Activa");
                psd.setString(5,encriptaEnMD5(clave));
                psd.setString(6,fechac);
                psd.setString(7,fechav);
                 psd.setInt(8,dia);
                psd.setInt(9,mes);
                psd.setInt(10,fecha);
                psd.setString(11,rutaf="No");
               
               
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                    
     SQL3 = "select * from tarjeta_cliente where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
      codigotar3= rs.getString("tarjetaid_tarjeta");
      
           } 
  txtnuro1.setText("Codigo:"+" "+codigotar3);
  
  SQL4 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL4);
      while (rs.next()) {
      cliented= rs.getString("clientede_cliente");
   
           } 
       codigotar4=cliented+codigotar3+encriptaEnMD5(codigoc);
       
          SQL="UPDATE tarjeta_cliente set codigo_tarjeta=?,rutafoto=? WHERE tarjetaid_tarjeta=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,codigotar4);
                 psd.setString(2,rutaf="C:/Qr/"+codigotar4+".jpg");
               
                 psd.setString(3,codigotar3);
                int f=  psd.executeUpdate();
     
                 // nuevo();
                 // generarcuenta();
                   // Activi=0;
                 // bloqueo();
               
                if((f>0)){
                   JOptionPane.showMessageDialog(null,"EXITO! CODIGO TAJETA:"+" "+codigotar3);
                    btimpri.setEnabled(true);
                   //limpiar();
                   //bloque();
                     GenerarQR(); 
    lebfoto.setIcon(null);  
   File   ruta2 = new File("C:/Qr/".concat(codigotar4).concat(".jpg"));
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
            foto2 = foto2.getScaledInstance(90, 90, Image.SCALE_DEFAULT);   
        lebfoto.setIcon(new ImageIcon(foto2)); 
                }
                
                //   nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
                  //  JOptionPane.showMessageDialog(null,"Guardado con exito!");
                    }
        }else{
          
      }
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
          

            break;
           
        }}
         void cargarCuenta() {
        // String bsf1,bsf2,titu,titu2,bssin;
       
        try{
           
            String [] titulos={"CODIGO","TIPO DE TARJETA","ESTADO","FECHA CREA"};
            String [] registros= new String[4];
            modelo=new DefaultTableModel(null,titulos);
            
             SQL3="select * from tarjeta_cliente where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
             st= cn.createStatement();
             rs = st.executeQuery(SQL3);
            while(rs.next()){
                 registros[0]=rs.getString("tarjetaid_tarjeta");
               registros[1]=rs.getString("tipo_tarjeta");
                registros[2]=rs.getString("estado_tarjeta");
                 registros[3]=rs.getString("fechacrea_tarjeta");
              
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
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnue = new javax.swing.JButton();
        btgua = new javax.swing.JButton();
        btedi = new javax.swing.JButton();
        bteli = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lebfoto = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lebfoto3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbnon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbape = new javax.swing.JLabel();
        txtnuro1 = new javax.swing.JLabel();
        jbem = new javax.swing.JLabel();
        jbexp = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jboxt = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtclave = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jbfecha = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btedf = new javax.swing.JButton();
        btgf = new javax.swing.JButton();
        spaño = new com.toedter.components.JSpinField();
        jLabel6 = new javax.swing.JLabel();
        btimpri = new javax.swing.JButton();
        panecu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacuenta = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CyberPlayPagos ");
        setPreferredSize(new java.awt.Dimension(850, 650));

        jPanel1.setBackground(new java.awt.Color(173, 207, 249));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btnue.setBackground(new java.awt.Color(255, 255, 255));
        btnue.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btnue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btnue.setText("NUEVO");
        btnue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnueActionPerformed(evt);
            }
        });

        btgua.setBackground(new java.awt.Color(255, 255, 255));
        btgua.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btgua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/download.png"))); // NOI18N
        btgua.setText("GUARDAR");
        btgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btguaActionPerformed(evt);
            }
        });

        btedi.setBackground(new java.awt.Color(255, 255, 255));
        btedi.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btedi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/File_List.png"))); // NOI18N
        btedi.setText("EDITAR");
        btedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btediActionPerformed(evt);
            }
        });

        bteli.setBackground(new java.awt.Color(255, 255, 255));
        bteli.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        bteli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/unlock.png"))); // NOI18N
        bteli.setText("BLO|DES");
        bteli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btgua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btedi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bteli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btgua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btedi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bteli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(130, 40, 120, 230);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setForeground(new java.awt.Color(204, 204, 204));

        lebfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lebfoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lebfotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));
        jPanel7.setForeground(new java.awt.Color(204, 204, 204));

        lebfoto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lebfoto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lebfoto3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto3, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lebfoto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Apellido:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jbnon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbnon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbape, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtnuro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtnuro1.setText("Codigo:");
        txtnuro1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));

        jbem.setText("Emi:");
        jbem.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));

        jbexp.setText("Exp:");
        jbexp.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnuro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbexp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnuro1)
                    .addComponent(jbem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbexp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(400, 50, 420, 210);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Android", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-----------------------Tarjeta de Cliente-----------------------");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 850, 40);

        jboxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jboxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selección--", "STANDAR", "VIP" }));
        jPanel1.add(jboxt);
        jboxt.setBounds(440, 300, 130, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLAVE");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(640, 270, 140, 18);

        txtclave.setText("jPasswordField1");
        jPanel1.add(txtclave);
        txtclave.setBounds(640, 300, 140, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TIPO DE TARJETA");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(440, 270, 130, 18);

        jbfecha.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jbfecha);
        jbfecha.setBounds(730, 570, 100, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Fecha");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel9);
        jLabel9.setBounds(730, 550, 100, 19);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), null));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("FECHA DE EXPIRACÓN");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        btedf.setBackground(new java.awt.Color(255, 255, 255));
        btedf.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btedf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/File_List.png"))); // NOI18N
        btedf.setText("Editar");
        btedf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedfActionPerformed(evt);
            }
        });

        btgf.setBackground(new java.awt.Color(255, 255, 255));
        btgf.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btgf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/download.png"))); // NOI18N
        btgf.setText("Guardar");
        btgf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgfActionPerformed(evt);
            }
        });

        spaño.setMinimum(1);
        spaño.setValue(1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("AÑOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btedf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btgf, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spaño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btedf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btgf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spaño, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(440, 400, 340, 140);

        btimpri.setBackground(new java.awt.Color(255, 255, 255));
        btimpri.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btimpri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/full_page.png"))); // NOI18N
        btimpri.setText("Imprimir");
        btimpri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btimpriActionPerformed(evt);
            }
        });
        jPanel1.add(btimpri);
        btimpri.setBounds(530, 350, 160, 40);

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

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CONTROL DE TARJETA ");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panecuLayout = new javax.swing.GroupLayout(panecu);
        panecu.setLayout(panecuLayout);
        panecuLayout.setHorizontalGroup(
            panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panecuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panecuLayout.setVerticalGroup(
            panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panecuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panecu);
        panecu.setBounds(10, 270, 390, 270);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(0, 544, 840, 80);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(0, 0, 840, 620);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnueActionPerformed
 tacuenta.setEnabled(false);
 tacuenta.setVisible(false);
 limpiar();
 bloque();
        nuevor();  

        Activi=1;
// TODO add your handling code here:
    }//GEN-LAST:event_btnueActionPerformed

    private void btguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguaActionPerformed
Seguridad();
        if(cont==0){
            txtclave.setBackground(Color.WHITE);
        Guardar(); 
       
        
}   
   // TODO add your handling code here:
    }//GEN-LAST:event_btguaActionPerformed

    private void btediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btediActionPerformed
   tacuenta.setEnabled(false);
    tacuenta.setVisible(false);
    limpiar();
   
        Editar();              // TODO add your handling code here:
    }//GEN-LAST:event_btediActionPerformed

    private void bteliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteliActionPerformed
limpiar();
bloque();
        codigoc = JOptionPane.showInputDialog("Cedula de Cliente:");
    try{
   if(codigoc.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula!");
          bloque();
                 
   }else{
  codigoc2=codigoc;
 
    SQL3 = "select * from tarjeta_cliente where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc3 = rs.getString("cedula_cliente");
           } 
        if((codigoc3.equals(encriptaEnMD5(codigoc)))){
            
            tacuenta.setEnabled(true);
             tacuenta.setVisible(true);
            cargarCuenta();    
        }else{
            
        }}}
        catch(Exception e){
                System.out.println(e.getMessage());
                 }
        
                   // TODO add your handling code here:
    }//GEN-LAST:event_bteliActionPerformed

    private void lebfotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lebfotoMouseClicked

    }//GEN-LAST:event_lebfotoMouseClicked

    private void lebfoto3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lebfoto3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lebfoto3MouseClicked

    private void btedfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedfActionPerformed
spaño.setEnabled(true); 
 Activi2=1;
     
   // nuevo();
    //fecha2="";
      try{
  
     
   SQL2 = "SELECT * FROM expira_tarjeta WHERE id LIKE '%" +1+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
      //desbloqueo();
      
       spaño.setValue( rs.getInt("fecha_expi"));
        
           
  
               
  
            }
          
      
   
        catch (SQLException ex) {}
// TODO add your handling code here:
    }//GEN-LAST:event_btedfActionPerformed

    private void btgfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgfActionPerformed

 String ano= String.valueOf(spaño.getValue());
        
 
        switch(Activi2){
           
            case 1:

            try{
           int resp = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
      if (resp == 0) {
       
        
      
      
       
          SQL="UPDATE expira_tarjeta set fecha_expi=? WHERE id=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,ano);
               
                 psd.setString(2,"1");
                int f=  psd.executeUpdate();
       
                 // nuevo();
                 // generarcuenta();
                   // Activi=0;
                 // bloqueo();
                if((f>0)){
                       
                   JOptionPane.showMessageDialog(null,"Guardado con exito!");  
                }
                
                //   nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
                    
      
       }else{
          
      }
            }  catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
        
        
           
          
 break;
           
        }       
               // TODO add your handling code here:
    }//GEN-LAST:event_btgfActionPerformed

    private void btimpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btimpriActionPerformed
  
     
List lista= new ArrayList();

 Tarjeta Estudiantes= new Tarjeta   (nomb1.toString(),ape1.toString(),rutafo.toString(),rutaf.toString(),fechac.toString(),fechav.toString());  
lista.add(Estudiantes);

        try{
            if(tipo.equals("STANDAR")){
             String fileJasper = "C:\\Archivos de programa\\CyberPlaySoftware\\Impresora\\TarjetaGt.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
            parametro.put("id1",codigotar3);
           
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            bloque();
            limpiar();
            }else{
                 String fileJasper = "C:\\Archivos de programa\\CyberPlaySoftware\\Impresora\\TarjetaGt2.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
            parametro.put("id1",codigotar3);
           
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
             bloque();
            limpiar();
            }
        } catch (JRException ex) {
            Logger.getLogger(ModGenTarj.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btimpriActionPerformed

    private void tacuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tacuentaMouseClicked
     
       try{ 
        int i = tacuenta.getSelectedRow();
        idta = (String) tacuenta.getValueAt(i, 0);
     String   sta = (String) tacuenta.getValueAt(i, 2);
        if(sta.equals("Bloqueada")){
              int resp = JOptionPane.showConfirmDialog(this, "¿Quiere Desbloquear esta Tarjeta?", "Desbloqueo de Tarjeta", 0);
      if (resp == 0) {
          estadota="Activa";
         SQL="UPDATE tarjeta_cliente set estado_tarjeta=? WHERE  tarjetaid_tarjeta=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,estadota);
               
                 psd.setString(2,idta);
                int f=  psd.executeUpdate();
       
                 // nuevo();
                 // generarcuenta();
                   // Activi=0;
                 // bloqueo();
               // btimpri.setEnabled(true);
                if((f>0)){
                     cargarCuenta(); 
                   JOptionPane.showMessageDialog(null,"TARJETA"+" "+idta+" "+"DESBLOQUEADA!"); 
                    bloque();
                   
                }
      }else{}
        }
        if(sta.equals("Activa")){
        int resp = JOptionPane.showConfirmDialog(this, "¿Quiere Bloquear esta Tarjeta?", "Bloqueo de Tarjeta", 0);
      if (resp == 0) {
          estadota="Bloqueada";
         SQL="UPDATE tarjeta_cliente set estado_tarjeta=? WHERE  tarjetaid_tarjeta=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,estadota);
               
                 psd.setString(2,idta);
                int f=  psd.executeUpdate();
       
                 // nuevo();
                 // generarcuenta();
                   // Activi=0;
                 // bloqueo();
               // btimpri.setEnabled(true);
                if((f>0)){
                     cargarCuenta(); 
                   JOptionPane.showMessageDialog(null,"TARJETA"+" "+idta+" "+"BLOQUEADA!"); 
                    bloque();
                   
                }
      }else{}
        }   }  catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_tacuentaMouseClicked

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
            java.util.logging.Logger.getLogger(ModGenTarj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModGenTarj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModGenTarj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModGenTarj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModGenTarj().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedf;
    private javax.swing.JButton btedi;
    private javax.swing.JButton bteli;
    private javax.swing.JButton btgf;
    private javax.swing.JButton btgua;
    private javax.swing.JButton btimpri;
    private javax.swing.JButton btnue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbape;
    private javax.swing.JLabel jbem;
    private javax.swing.JLabel jbexp;
    private javax.swing.JLabel jbfecha;
    private javax.swing.JLabel jbnon;
    private javax.swing.JComboBox jboxt;
    private javax.swing.JLabel lebfoto;
    private javax.swing.JLabel lebfoto3;
    private javax.swing.JPanel panecu;
    private com.toedter.components.JSpinField spaño;
    private javax.swing.JTable tacuenta;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JLabel txtnuro1;
    // End of variables declaration//GEN-END:variables
}
