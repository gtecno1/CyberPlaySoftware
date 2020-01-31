/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;

import ConexionBd.CBDPLAY;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gtecno
 */
public class ModGesCliente extends javax.swing.JFrame {
int Activi,cont,tele2;;
CBDPLAY cc = new CBDPLAY();
 Connection cn = cc.Conectar();
   Statement  st;ResultSet rs;
  String SQL,SQL2,SQL3,SQL4;
  DefaultTableModel modelo;
    PreparedStatement psd;
    String nacionalidad,Cedula,nombre,apellido,cod,telefono,numeroT,tipoC,fecha,cliented="CyberPlay",  rutafo="C:/Archivos de programa/CyberPlaySoftware/Graficos/Client_Male_Dark (1).jpg",foto="ee",codigoc,fecha2,codigoc2,codigoc3="0",codigo4="0",Cuenta,Fondos_cuenta="0",Fondos_cuenta2="0",moneda="Bsf",error="0";
     BigDecimal Fondos_cuenta4=new BigDecimal(0);
      BigDecimal   Fondos_cuenta3=new BigDecimal(0);
    public ModGesCliente(GraphicsConfiguration gc) {
        super(gc);
    }
    
    /**
     * Creates new form ModGesCliente
     */
    public ModGesCliente() {
        
        initComponents();
          setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        i();
       
        bloqueo();
        panecu.setVisible(false);
        fechaActual();
        jbfecha.setText(fechaActual());
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/ico.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
    public static String fechaActual()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyy");
        return formatoFecha.format(fecha);
                }
void  nuevo(){
    codigoc="";
    codigoc3="0";
    jbnac.setSelectedItem("V");
    txtcedu.setText("");
    txtcedu.setBackground(Color.WHITE); 
  txtnom.setText("");
  txtape.setText("");
  jbcodi.setSelectedItem("-Sel-");
  txttelf.setText("");
  jbNu.setText("No Posee");
  jbtc.setText("No Posee");
 lebfoto.setText("FOTO");
  lebfoto.setIcon(null);
  rutafo="C:/Archivos de programa/CyberPlaySoftware/Graficos/Client_Male_Dark (1).jpg";
    panecu.setVisible(false);
   
}
void  desbloqueo(){
    jbnac.setEnabled(true);
    txtcedu.setEnabled(true);
  txtnom.setEnabled(true);
  txtape.setEnabled(true);
  jbcodi.setEnabled(true);
  txttelf.setEnabled(true);
  jbNu.setEnabled(true);
  jbtc.setEnabled(true);
  lebfoto.setEnabled(true);
  btgen.setEnabled(true);
}
void bloqueo(){
    jbnac.setEnabled(false);
    txtcedu.setEnabled(false);
  txtnom.setEnabled(false);
  txtape.setEnabled(false);
  jbcodi.setEnabled(false);
  txttelf.setEnabled(false);
  jbNu.setEnabled(false);
  jbtc.setEnabled(false);
  lebfoto.setEnabled(false);
  panecu.setVisible(false);
  btgen.setEnabled(false); 
  
}
void Seguridad(){
     cont=0;
     
      nacionalidad= String.valueOf(jbnac.getSelectedItem());
    Cedula=txtcedu.getText();
  nombre=txtnom.getText();
  apellido=txtape.getText();
  cod=String.valueOf(jbcodi.getSelectedItem());
 telefono= txttelf.getText();
  numeroT=jbNu.getText();
  tipoC=jbtc.getText();
  fecha=jbfecha.getText();
  if(Cedula.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||cod.equals("-Sel-")||telefono.isEmpty()){
       JOptionPane.showMessageDialog(this,"Campos Vacios!");
       cont=1;
  }else{
   cont=0;
    String tele1=telefono;  
    char[] arrayCha= tele1.toCharArray();
    for(int i=0; i< arrayCha.length; i++){
    arrayCha[i]='0';
    tele2=tele1.length();
    }
    if(tele2 <7||tele2 >7 ){
     cont=1;   
     if(tele2 <7){
      JOptionPane.showMessageDialog(this,"Telefono Incompleto!"); 
     }else{
          JOptionPane.showMessageDialog(this,"Telefono Extenso!"); 
     }
    }else{
        cont=0;
    }
   
    
    if(cont!=1){
        NumeroV();
         
    }
     if(cont!=1&&(Activi==3||Activi==1)){
        ComproCedula();
        
    }
    

  }
}
void NumeroV(){
     String cedu1;
       byte contLetraMay = 0, contLetraMin=0;
        cedu1 =Cedula;
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

 void ComproCedula(){
          try{
            SQL4 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(Cedula) + "%'";
      
      st = this.cn.createStatement();
       rs = st.executeQuery(SQL4);
      while (rs.next()) {
       codigo4 = rs.getString("cedula_cliente");
           } 
        if(codigo4.equals(encriptaEnMD5(Cedula))){
             panecu.setVisible(false);
              txtcedu.setBackground(Color.red);
               cont=1;
        JOptionPane.showMessageDialog(this,"ERROR: Esta cedula "+Cedula+" ya esta en Sistema!!");  
        
        }else{
           cont=0;
             
        } 
          }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
 }   

void Guardar(){
  
    Cedula=txtcedu.getText();
  nombre=txtnom.getText();
  apellido=txtape.getText();
  cod=String.valueOf(jbcodi.getSelectedItem());
 telefono= txttelf.getText();
  numeroT=jbNu.getText();
  tipoC=jbtc.getText();
  fecha=jbfecha.getText(); 
      
 
        switch(Activi){
            case 1:
              JOptionPane.showMessageDialog(this,"ERROR: Genere Cuenta Porfavor!!");
                break;
            case 3:

            try{
           int resp = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
      if (resp == 0) {
       
        
                SQL="INSERT INTO cliente_cyberplay(nacionalidad_cliente,cedula_cliente,nombre_cliente,apellido_cliente,codigotele_cliente,telefono_cliente,clientede_cliente,rutafoto_cliente,foto_cliente,fecharegistro_cliente)VALUES (?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,nacionalidad);
                psd.setString(2,encriptaEnMD5(Cedula));
                psd.setString(3,nombre);
                psd.setString(4,apellido);
                psd.setString(5,cod);
                psd.setString(6,telefono);
                 
               // psd.setString(7,numeroT);
                psd.setString(7,cliented);
               // psd.setString(9,tipoC);
                 psd.setString(8,rutafo);
                psd.setString(9,foto);
                psd.setString(10,fecha);
               
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                  nuevo();
                  generarcuenta();
                    Activi=0;
                  bloqueo();
                
                
                   nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
                    JOptionPane.showMessageDialog(null,"Guardado con exito!");
                    }
        }else{
          
      }
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
          

            break;
            case 2:
            
            try {
                   int resp1 = JOptionPane.showConfirmDialog(this, "¿Quiere guardar los datos?", "Guardar datos", 0);
      if (resp1 == 0) {
       
        
if(encriptaEnMD5(Cedula).endsWith(encriptaEnMD5(codigoc))){
             
                SQL="UPDATE cliente_cyberplay set nacionalidad_cliente=?,cedula_cliente=?,nombre_cliente=?,apellido_cliente=?,codigotele_cliente=?,telefono_cliente=?,clientede_cliente=?,rutafoto_cliente=?,foto_cliente=?,fecharegistro_cliente=? WHERE cedula_cliente=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,nacionalidad);
                psd.setString(2,encriptaEnMD5(Cedula));
                psd.setString(3,nombre);
                psd.setString(4,apellido);
                psd.setString(5,cod);
                psd.setString(6,telefono);
                 
                //psd.setString(7,numeroT);
                psd.setString(7,cliented);
               // psd.setString(9,tipoC);
                 psd.setString(8,rutafo);
                psd.setString(9,foto);
                psd.setString(10,fecha);
                 psd.setString(11,encriptaEnMD5(codigoc));
                int f=  psd.executeUpdate();
                

             
                 if((f>0)){
                  
                   nuevo();
                   
                    bloqueo();
              //  Editarcuenta();
                   Activi=0;
             
                    JOptionPane.showMessageDialog(null,"Guardado con exito!");

                }
}else{
    int resp = JOptionPane.showConfirmDialog(this, "RECUERDE: Si modifica cedula tambien se modificara el Número de Cuenta", "¿Esta Seguro?", 0);
      if (resp == 0) {
       
        {
       SQL="UPDATE cliente_cyberplay set nacionalidad_cliente=?,cedula_cliente=?,nombre_cliente=?,apellido_cliente=?,codigotele_cliente=?,telefono_cliente=?,clientede_cliente=?,rutafoto_cliente=?,foto_cliente=?,fecharegistro_cliente=? WHERE cedula_cliente=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,nacionalidad);
                psd.setString(2,encriptaEnMD5(Cedula));
                psd.setString(3,nombre);
                psd.setString(4,apellido);
                psd.setString(5,cod);
                psd.setString(6,telefono);
                 
                //psd.setString(7,numeroT);
                psd.setString(7,cliented);
                //psd.setString(9,tipoC);
                 psd.setString(8,rutafo);
                psd.setString(9,foto);
                psd.setString(10,fecha);
                 psd.setString(11,encriptaEnMD5(codigoc));
                int f=  psd.executeUpdate();
                

             
                 if((f>0)){
                  
                   nuevo();
                   
                    bloqueo();
                Editarcuenta();
                   Activi=0;
             
                    JOptionPane.showMessageDialog(null,"Guardado con exito!");

                }
        }
}else{
          
      }
            }
      }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }

            break;

        }
        }
void Editar(){
    nuevo();
    fecha2="";
      try{
   codigoc = JOptionPane.showInputDialog("Cedula de Cliente:");
   if(codigoc.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula!");
          bloqueo();
                 
   }else{
  codigoc2=codigoc;
 
    SQL3 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc3 = rs.getString("cedula_cliente");
           } 
        if((codigoc3.equals(encriptaEnMD5(codigoc)))){
       
   SQL2 = "SELECT * FROM cliente_cyberplay WHERE cedula_cliente LIKE '%" +encriptaEnMD5(codigoc)+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
      desbloqueo();
      
        jbnac.setSelectedItem( rs.getString("nacionalidad_cliente"));
           txtcedu.setText(codigoc2); 
            txtnom.setText(rs.getString("nombre_cliente")); 
             txtape.setText(rs.getString("apellido_cliente")); 
   jbcodi.setSelectedItem( rs.getString("codigotele_cliente"));
           txttelf.setText(rs.getString("telefono_cliente")); 
              txttelf.setText(rs.getString("telefono_cliente")); 
             // jbNu.setText(rs.getString("numerotarjeta_cliente")); 
               //jbtc.setText(rs.getString("tipodetarjeta_cliente"));
               
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
    
             cargarCuenta();
          
               
                 SQL="SELECT count(*)  FROM tarjeta_cliente where CONCAT (cedula_cliente) LIKE '%"+encriptaEnMD5(codigoc)+"%'   ";
   
  
            
           st = cn.createStatement();
                rs=st.executeQuery( SQL);
               while(rs.next()){
                 
                  jbNu.setText(rs.getString("count(*)"));
        
          
                   
               }
                int tip=Integer.valueOf(jbNu.getText());
               
               
                 
        if(tip>=1){
            jbtc.setText("STANDAR");
            SQL2 = "SELECT * FROM tarjeta_cliente WHERE cedula_cliente LIKE '%" +encriptaEnMD5(codigoc)+ "%' AND tipo_tarjeta LIKE '%" +"VIP"+ "%'";
  st = this.cn.createStatement();
 
    rs = st.executeQuery(SQL2);
      rs.next();        
     
        
       String tip1= rs.getString("tipo_tarjeta");
        if(tip1.equals("VIP")){
          jbtc.setText("VIP");
        }
        }else{
          jbtc.setText("No Posee");  
        }
        fecha2=(rs.getString("fecharegistro_cliente"));
          
        }else{
              JOptionPane.showMessageDialog(this, "NO esta en Sistema");
        }
   }
        }catch (SQLException ex) {}
}
    
    void Eliminar(){
    nuevo();
    fecha2="";
      try{
          codigoc = JOptionPane.showInputDialog("Cedula de Cliente:");
   if(codigoc.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula!");
          bloqueo();
                 
   }else{
  codigoc2=codigoc;
 
    SQL3 = "select * from cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
      
       st = this.cn.createStatement();
       rs = st.executeQuery(SQL3);
      while (rs.next()) {
       codigoc3 = rs.getString("cedula_cliente");
           } 
        if((codigoc3.equals(encriptaEnMD5(codigoc)))){
       
 
   SQL2 = "SELECT * FROM cliente_cyberplay WHERE cedula_cliente LIKE '%"  +encriptaEnMD5(codigoc)+ "%'";
  st = this.cn.createStatement();
    rs = st.executeQuery(SQL2);
      rs.next();        
      desbloqueo();
      
        jbnac.setSelectedItem( rs.getString("nacionalidad_cliente"));
           txtcedu.setText(codigoc2); 
            txtnom.setText(rs.getString("nombre_cliente")); 
             txtape.setText(rs.getString("apellido_cliente")); 
   jbcodi.setSelectedItem( rs.getString("codigotele_cliente"));
           txttelf.setText(rs.getString("telefono_cliente")); 
              txttelf.setText(rs.getString("telefono_cliente")); 
              //jbNu.setText(rs.getString("numerotarjeta_cliente")); 
               //jbtc.setText(rs.getString("tipodetarjeta_cliente"));
               
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
          fecha2=(rs.getString("fecharegistro_cliente"));
             bloqueo();
           int resp = JOptionPane.showConfirmDialog(this, "¿Desea Eliminar TODA la información del cliente?", "Eliminar Dato", 0);
      if (resp == 0) {
        try
        {
          PreparedStatement psd = null;
        String  SQL0 = " DELETE FROM cliente_cyberplay WHERE cedula_cliente ='" +encriptaEnMD5(codigoc)+ "'";
          psd = this.cn.prepareStatement(SQL0);
          psd.execute();
          psd.close();
           
          nuevo();
         bloqueo();
          
             
          JOptionPane.showMessageDialog(this, "Información ELIMINADA!");
        }
        
        catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }
      
     } else {
        nuevo();
         bloqueo();
          
             
             //JOptionPane.showMessageDialog(null, "No existe");
     }
        }else{
              JOptionPane.showMessageDialog(this, "No esta en Sistema ");
        }
   }
        }catch (SQLException ex) {}
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
    
void CreadorNumero(){ 
    Cuenta="C-"+Cedula; 
      
}
void Editarcuenta(){
      try{
      Cuenta="C-"+Cedula;
                SQL="UPDATE cuentac_cliente_cyberplay set id_cuentacliente=?,cedula_cliente=? WHERE cedula_cliente=? ";
                psd = cn.prepareStatement(SQL);
                 psd.setString(1,Cuenta);
                psd.setString(2,encriptaEnMD5(Cedula));
                psd.setString(3,encriptaEnMD5(codigoc));
                
               
                 int s= psd.executeUpdate();
                  
                if((s>0)){
                //  nuevo();
                  //bloqueo();
                 
                  // nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
                    //JOptionPane.showMessageDialog(null,"Guardo con exito");
                }}
                 catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
}
void generarcuenta(){
 
  
   
 
  
        
 
        

            try{
                /*
            SQL4 = "select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(Cedula) + "%'";
      
      st = this.cn.createStatement();
       rs = st.executeQuery(SQL4);
      while (rs.next()) {
       codigo4 = rs.getString("cedula_cliente");
           } 
        if(codigo4.equals(encriptaEnMD5(Cedula))){
        JOptionPane.showMessageDialog(this,"Cliente Ya posee Cuenta");     
        }else{
        * 
        */
            fecha=jbfecha.getText(); 
                Cuenta="C-"+Cedula; 
                SQL="INSERT INTO cuentac_cliente_cyberplay(id_cuentacliente,cedula_cliente,fondos_cuenta_bsf,fondos_cuenta_bss,fechacrea_cuenta)VALUES (?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,Cuenta);
                psd.setString(2,encriptaEnMD5(Cedula));
                psd.setString(3,Fondos_cuenta);
             psd.setString(4,Fondos_cuenta2);
             //   psd.setString(5,moneda);
                psd.setString(5,fecha);
               
                 int m= psd.executeUpdate();
                  
                if((m>0)){
                //  nuevo();
                  //bloqueo();
                 
                  // nacionalidad=""; Cedula=""; nombre=""; apellido="";cod="";telefono= ""; numeroT="";tipoC="";fecha=""; 
                    //JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
      
               
            
            
             
       }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
            

            
           
        
    
}

     void cargarCuenta() {
        DecimalFormat dnFormat = new  DecimalFormat("#,##0.00");
       
        try{
           
            String [] titulos={"NUMERO","FONDO EN BSF","FONDO EN BSS","FECHA DE CREACIÓN"};
            String [] registros= new String[4];
            modelo=new DefaultTableModel(null,titulos);
            
            String cons="select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(codigoc) + "%'";
             st= cn.createStatement();
             rs = st.executeQuery(cons);
            while(rs.next()){
               registros[0]=rs.getString("id_cuentacliente");
                registros[1]=dnFormat.format(rs.getBigDecimal("fondos_cuenta_bsf"));
                 registros[2]=dnFormat.format(rs.getBigDecimal("fondos_cuenta_bss"));
               // registros[3]=rs.getString("moneda_cuenta");
                registros[3]=rs.getString("fechacrea_cuenta");
       
           } 
                modelo.addRow(registros);      
                
         tacuenta.setModel(modelo);
          tacuenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tacuenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            tacuenta.getColumnModel().getColumn(2).setPreferredWidth(100);
             
        }catch(Exception e){
                System.out.println(e.getMessage());
                 }
}
   void MostrarCuenta() {
         
       
        try{
           
            String [] titulos={"NUMERO","FONDO EN BSF","FONDO EN BSS","FECHA DE CREACIÓN"};
            String [] registros= new String[4];
            modelo=new DefaultTableModel(null,titulos);
            
           // String cons="select * from cuentac_cliente_cyberplay where cedula_cliente LIKE '%" + encriptaEnMD5(Cedula) + "%'";
            
            
               
                registros[0]=Cuenta;
                registros[1]=Fondos_cuenta;
                 registros[2]=Fondos_cuenta2;
                //registros[3]=moneda;
                registros[3]=fecha;
           
                modelo.addRow(registros);      
                
         tacuenta.setModel(modelo);
          tacuenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tacuenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            tacuenta.getColumnModel().getColumn(2).setPreferredWidth(100);
             
        }catch(Exception e){
                System.out.println(e.getMessage());
        }    }
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
        jLabel1 = new javax.swing.JLabel();
        jbnac = new javax.swing.JComboBox();
        txtcedu = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lebfoto = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        txtape = new javax.swing.JTextField();
        jbcodi = new javax.swing.JComboBox();
        txttelf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbNu = new javax.swing.JLabel();
        jbtc = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jbfecha = new javax.swing.JLabel();
        panecu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacuenta = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btgen = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CyberPlayPagos ");
        setPreferredSize(new java.awt.Dimension(650, 560));

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
        bteli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        bteli.setText("ELIMINAR");
        bteli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btedi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bteli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(btgua, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btgua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btedi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(bteli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 120, 250);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Android", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-----------------------GESTIÓN DE CLIENTE-----------------------");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 640, 40);

        jbnac.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jbnac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "E" }));
        jbnac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnacActionPerformed(evt);
            }
        });
        jPanel1.add(jbnac);
        jbnac.setBounds(310, 110, 50, 26);

        txtcedu.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        txtcedu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtceduActionPerformed(evt);
            }
        });
        jPanel1.add(txtcedu);
        txtcedu.setBounds(370, 110, 149, 30);

        jPanel3.setBackground(new java.awt.Color(72, 97, 231));
        jPanel3.setForeground(new java.awt.Color(204, 204, 204));

        lebfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lebfoto.setText("FOTO");
        lebfoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lebfotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lebfoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(200, 50, 100, 80);

        txtnom.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jPanel1.add(txtnom);
        txtnom.setBounds(200, 170, 158, 30);

        txtape.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        txtape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapeActionPerformed(evt);
            }
        });
        jPanel1.add(txtape);
        txtape.setBounds(370, 170, 149, 30);

        jbcodi.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jbcodi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Sel-", "412", "414", "424", "416", "426", "263" }));
        jPanel1.add(jbcodi);
        jbcodi.setBounds(290, 230, 70, 26);

        txttelf.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jPanel1.add(txttelf);
        txttelf.setBounds(370, 230, 148, 26);

        jPanel4.setBackground(new java.awt.Color(173, 207, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jPanel4.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Numero de Tarjetas");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tipo de Cliente");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        jbNu.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbNu.setForeground(new java.awt.Color(255, 255, 255));
        jbNu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbNu.setText("No Posee");

        jbtc.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbtc.setForeground(new java.awt.Color(255, 255, 255));
        jbtc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbtc.setText("No Posee");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(jbNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtc))
                .addContainerGap())
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(200, 260, 320, 70);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TELEFONO");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(290, 210, 230, 18);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CEDULA");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(310, 90, 210, 18);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("NOMBRE");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel6);
        jLabel6.setBounds(200, 150, 160, 18);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("APELLIDO");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel7);
        jLabel7.setBounds(370, 150, 150, 18);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Fecha");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel8);
        jLabel8.setBounds(510, 450, 100, 19);

        jbfecha.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jbfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jbfecha);
        jbfecha.setBounds(510, 470, 100, 30);

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
        jScrollPane1.setViewportView(tacuenta);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("CUENTA DEL CLIENTE");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panecuLayout = new javax.swing.GroupLayout(panecu);
        panecu.setLayout(panecuLayout);
        panecuLayout.setHorizontalGroup(
            panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panecuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panecuLayout.setVerticalGroup(
            panecuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panecuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(panecu);
        panecu.setBounds(10, 340, 610, 100);

        btgen.setBackground(new java.awt.Color(255, 255, 255));
        btgen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/chart_pie.png"))); // NOI18N
        btgen.setText("GENERAR CUENTA");
        btgen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgenActionPerformed(evt);
            }
        });
        jPanel1.add(btgen);
        btgen.setBounds(300, 350, 220, 40);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setOpaque(true);
        jPanel1.add(jLabel10);
        jLabel10.setBounds(0, 444, 640, 70);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(0, 0, 630, 510);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguaActionPerformed
 Seguridad();
     if(cont!=1){
            Guardar(); 
         } // TODO add your handling code here:
    }//GEN-LAST:event_btguaActionPerformed

    private void jbnacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbnacActionPerformed

    private void txtceduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtceduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtceduActionPerformed

    private void btnueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnueActionPerformed
  nuevo();
  desbloqueo();
  Activi=1;
// TODO add your handling code here:
    }//GEN-LAST:event_btnueActionPerformed

    private void txtapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapeActionPerformed

    private void lebfotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lebfotoMouseClicked
// TODO add your handling code here:

        //Creamos nuestra variable archivo en la cual podremos usar todos los metodos de la clase jFileChooser
        JFileChooser archivo = new JFileChooser();
        //Si deseamos crear filtros para la selecion de archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg", "jpeg");
        //Si deseas que se muestre primero los filtros usa la linea q esta abajo de esta.
        //archivo.setFileFilter(filtro);
        // Agregamos el Filtro pero cuidado se mostrara despues de todos los archivos
        archivo.addChoosableFileFilter(filtro);
        // Colocamos titulo a nuestra ventana de Seleccion
        archivo.setDialogTitle("Abrir Archivo");
        //Si deseamos que muestre una carpeta predetermina usa la siguiente linea
        File ruta = new File("C:/xampp/htdocs/www/imagen");

        //Le implementamos a nuestro ventana de seleccion
        archivo.setCurrentDirectory(ruta);
        //Abrimos nuestra Ventana de Selccion
        int ventana = archivo.showOpenDialog(null);
        //hacemos comparacion en caso de aprete el boton abrir
        if (ventana == JFileChooser.APPROVE_OPTION) {
            //Obtenemos la ruta de nuestra imagen seleccionada
            File file = archivo.getSelectedFile();
            //Lo imprimimos en una caja de texto para ver su ruta
            String rus;
           rutafo = file.getName();
           //nombret.setText(String.valueOf(rus));
           // rut.setText(String.valueOf(file));
            
           rutafo =String.valueOf(file);

            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            Image foto = getToolkit().getImage(rutafo);
           
            foto = foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT); 
            lebfoto.setIcon(new ImageIcon(foto));
            lebfoto.setText("");// TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_lebfotoMouseClicked

    private void btediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btediActionPerformed
        Activi=2;
        Editar();        // TODO add your handling code here:
    }//GEN-LAST:event_btediActionPerformed

    private void bteliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteliActionPerformed
Eliminar();        // TODO add your handling code here:
    }//GEN-LAST:event_bteliActionPerformed

    private void btgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgenActionPerformed
Seguridad(); 
if(cont!=1){
    CreadorNumero();
    MostrarCuenta();
    Activi +=2;
    txtcedu.setBackground(Color.WHITE); 
    panecu.setVisible(true);
   
    
         
         }// TODO add your handling code here:
    }//GEN-LAST:event_btgenActionPerformed

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
            java.util.logging.Logger.getLogger(ModGesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModGesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModGesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModGesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModGesCliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedi;
    private javax.swing.JButton bteli;
    private javax.swing.JButton btgen;
    private javax.swing.JButton btgua;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbNu;
    private javax.swing.JComboBox jbcodi;
    private javax.swing.JLabel jbfecha;
    private javax.swing.JComboBox jbnac;
    private javax.swing.JLabel jbtc;
    private javax.swing.JLabel lebfoto;
    private javax.swing.JPanel panecu;
    private javax.swing.JTable tacuenta;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtcedu;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txttelf;
    // End of variables declaration//GEN-END:variables
}
