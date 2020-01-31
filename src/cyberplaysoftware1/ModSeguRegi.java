/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;

import ConexionBd.CBDPLAY;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gtecno
 */
public class ModSeguRegi extends javax.swing.JFrame {
int Ac;
  CBDPLAY cc = new CBDPLAY();
 Connection cn = cc.Conectar();
   Statement  st;ResultSet rs;
   String SQL,SQL2,SQL3,SQL4;
   
  //   int Ac,vali=0;
        String ced,ape,sex="",usu,con,foru="23",fo="333",ceco="0";
        
         String codigoc2,codigoc3="0",codigo4="0",Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0",rutafo="No pose";  
        String cod,US,CO;
        int cancar=0,teln=0,cont,tele2;
    /**
     * Creates new form ModSeguRegi
     */
    public ModSeguRegi() {
        initComponents();
        this.setLocationRelativeTo(null);
       
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        borrar();
        bloqueo();
    }
    void Seguridad(){
     cont=0;
     
    ced=txtcedu.getText();   
nom=txtnombre.getText();
ape=txtapellido.getText();

     if(rf.isSelected()){
         sex="Femenino";  
     } 
       if(rm.isSelected()){
         sex="Masculino";   
     }
// corr=txtcorr.getText();
 //code=String.valueOf(boxco.getSelectedItem());
// tel=txtTel.getText();
 usu=txtusu.getText();
 con=txtclave.getText();
 //String descripcion=con;

  if(ced.isEmpty()||nom.isEmpty()||ape.isEmpty()||usu.isEmpty()||con.isEmpty()){
       JOptionPane.showMessageDialog(this,"Campos Vacios!");
       cont=1;
  }else{
   cont=0;
    String tele1=con;  
    char[] arrayCha= tele1.toCharArray();
    for(int i=0; i< arrayCha.length; i++){
    arrayCha[i]='0';
    tele2=tele1.length();
    }
    if(tele2 <8 ){
     cont=1;   
     
      JOptionPane.showMessageDialog(this,"Clave Corta!"); 
    
    }else{
        cont=0;
    }
   
    
    if(cont!=1){
        NumeroV();
         
    }
     if(cont!=1&&(Ac==1||Ac==2)){
        ComproCedula();
        
    }
    

  }
}
void NumeroV(){
     String cedu1;
       byte contLetraMay = 0, contLetraMin=0;
        cedu1 =ced;
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
       JOptionPane.showMessageDialog(this,"Introdusca Solo Números!"); 
     } else{
           cont=0;
             
        } 
}

 void ComproCedula(){
          try{
            SQL4 = "select * from usuario where cedula_usuario LIKE '%" + encriptaEnMD5(ced) + "%'";
      
      st = this.cn.createStatement();
       rs = st.executeQuery(SQL4);
      while (rs.next()) {
       codigo4 = rs.getString("cedula_usuario");
           } 
        if(codigo4.equals(encriptaEnMD5(ced))){
             //panecu.setVisible(false);
              txtcedu.setBackground(Color.red);
               cont=1;
        JOptionPane.showMessageDialog(this,"ERROR: Esta cedula "+ced+" ya esta en Sistema!!");  
        
        }else{
           cont=0;
             
        } 
          }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
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
 void borrar(){
     rm.setSelected(false);
   rf.setSelected(false);
  txtapellido.setText("");
     txtnombre.setText("");
    txtcedu.setText("");
   
    txtusu.setText("");
   txtclave.setText("");
     
  lbfoto.setIcon(null);
   lbfoto.setText("FOTO");
   rutafo="No pose";
    txtclave.setBackground(Color.WHITE); 
   
    
        
    }
     void bloqueo(){
         
        rm.setEnabled(false);
   rf.setEnabled(false);
  txtapellido.setEnabled(false);
     txtnombre.setEnabled(false);
    txtcedu.setEnabled(false);
   
    txtusu.setEnabled(false);
   txtclave.setEnabled(false);
     
  lbfoto.setEnabled(false);
  
   
   
    }
      void desbloqueo(){
              rm.setEnabled(true);
   rf.setEnabled(true);
  txtapellido.setEnabled(true);
     txtnombre.setEnabled(true);
    txtcedu.setEnabled(true);
   
    txtusu.setEnabled(true);
   txtclave.setEnabled(true);
     
  lbfoto.setEnabled(true);
    }
      void Guardar(){
          String Estado="0";
        ced=txtcedu.getText();   
nom=txtnombre.getText();
ape=txtapellido.getText();

     if(rf.isSelected()){
         sex="Femenino";  
     } 
       if(rm.isSelected()){
         sex="Masculino";   
     }
// corr=txtcorr.getText();
 //code=String.valueOf(boxco.getSelectedItem());
// tel=txtTel.getText();
 usu=txtusu.getText();
 con=txtclave.getText();
 String descripcion=con;

  
  
        String SQL;
        
        

        
      

        PreparedStatement psd;

        switch(Ac){
           
            case 1:

            try{
                          String  SQL0 = "select * from usuario where cedula_usuario LIKE '%" + ced + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQL0);
      while (rs41.next()) {
       ceco = rs41.getString("cedula_usuario");
           } 
        if(ceco.equals(ced)){
        JOptionPane.showMessageDialog(this,"Cedula Ya Existe");     
        }else{

                SQL="INSERT INTO usuario(cedula_usuario,nombre_usuario,apellido_usuario,sexo_usuario,usuario_usuario,contraseña_usuario,estado,rutafoto_usuario,foto_usuario)VALUES (?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,ced);
                psd.setString(2,nom);
                psd.setString(3,ape);
                psd.setString(4,sex);
              //  psd.setString(5,code);
                 
              //  psd.setString(6,tel);
              //  psd.setString(7,corr);
                psd.setString(5,encriptaEnMD5(usu));
                psd.setString(6,encriptaEnMD5(con));
               
          
                       psd.setString(7, Estado);
                  psd.setString(8,rutafo);
                 psd.setString(9,fo);
                 
                
   
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                  borrar();
                   bloqueo();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
        }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
            

            break;
            case 2:
            
            try {

             
                SQL="UPDATE usuario set cedula_usuario=?,nombre_usuario=?,apellido_usuario=?,sexo_usuario=?,usuario_usuario=?,contraseña_usuario=?,rutafoto_usuario=?,foto_usuario=? WHERE cedula_usuario=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,ced);
                psd.setString(2,nom);
                psd.setString(3,ape);
                psd.setString(4,sex);
              //  psd.setString(5,code);
                 
              //  psd.setString(6,tel);
              //  psd.setString(7,corr);
                psd.setString(5,encriptaEnMD5(usu));
                psd.setString(6,encriptaEnMD5(con));
               
              
                  psd.setString(7,rutafo);
                 psd.setString(8,fo);
                  psd.setString(9,cod);
               
                
                int f=  psd.executeUpdate();
                
               
             
                 if((f>0)){
                   borrar();
                    bloqueo();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");

                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }

            break;

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
        jLabel4 = new javax.swing.JLabel();
        txtcedu = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        rm = new javax.swing.JRadioButton();
        rf = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lbfoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnue = new javax.swing.JButton();
        btgua = new javax.swing.JButton();
        btedi = new javax.swing.JButton();
        bteli = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtusu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtclave = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Android", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-----------------------Gestión de Usuario-----------------------");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 620, 40);

        txtcedu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcedu.setText("jTextField1");
        jPanel1.add(txtcedu);
        txtcedu.setBounds(360, 100, 177, 28);

        txtnombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombre.setText("jTextField2");
        jPanel1.add(txtnombre);
        txtnombre.setBounds(170, 160, 177, 28);

        txtapellido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtapellido.setText("jTextField3");
        jPanel1.add(txtapellido);
        txtapellido.setBounds(360, 160, 177, 28);

        rm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rm.setText("M");
        rm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmActionPerformed(evt);
            }
        });
        jPanel1.add(rm);
        rm.setBounds(310, 220, 37, 23);

        rf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rf.setText("F");
        rf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfActionPerformed(evt);
            }
        });
        jPanel1.add(rf);
        rf.setBounds(360, 220, 40, 23);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        lbfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfoto.setText("jLabel1");
        lbfoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbfoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbfotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbfoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(210, 50, 100, 83);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btgua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btedi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bteli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 50, 120, 230);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CEDULA");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(360, 80, 180, 18);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NOMBRE");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(170, 140, 180, 18);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("APELLIDO");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel6);
        jLabel6.setBounds(360, 140, 180, 18);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SEXO");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel7);
        jLabel7.setBounds(310, 200, 90, 18);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));

        txtusu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtusu.setText("jTextField4");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("USUARIO");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CONTRASEÑA");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        txtclave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtclave.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(170, 260, 370, 130);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 620, 400);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnueActionPerformed
 desbloqueo();
     borrar();
     Ac=1;        
// TODO add your handling code here:
    }//GEN-LAST:event_btnueActionPerformed

    private void btguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguaActionPerformed
Seguridad();
     if(cont!=1){
            Guardar(); 
         }
 
       

    
        // TODO add your handling code here:
    }//GEN-LAST:event_btguaActionPerformed

    private void btediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btediActionPerformed
Ac=2;
borrar();

String ceco="0";
   
   //cod = JOptionPane.showInputDialog("Cedula:");
   US=JOptionPane.showInputDialog("Usuario:");
   CO=JOptionPane.showInputDialog("Contraseña:");
    if(US.equals("")||CO.equals("")){
         JOptionPane.showMessageDialog(this, "Intentelo de nuevo");
        bloqueo();

    }else{
    try
    {
        String  SQLr = "select * from usuario where usuario_usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña_usuario='"+ encriptaEnMD5(CO)+"'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula_usuario");
       
           } 
        if(ceco.equals("0")){
          JOptionPane.showMessageDialog(null, "Usuario no Existe");
          
        }else{
  String SQL1 = "SELECT * FROM usuario WHERE usuario_usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña_usuario='"+ encriptaEnMD5(CO)+"'";
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
       desbloqueo();      
      
  cod=rs.getString("cedula_usuario");
     txtcedu.setText(rs.getString("cedula_usuario"));
     
    txtnombre.setText(rs.getString("nombre_usuario"));
       txtapellido.setText(rs.getString("apellido_usuario"));
 
     String sex=rs.getString("sexo_usuario");
     
     if(sex.equals("Femenino")) {
       rf.isSelected();
        rf.setSelected(true);
     }else{
        rm.isSelected();
        rm.setSelected(true);
     }
     
   
    
       
      txtusu.setText(US);
     txtclave.setText(CO);
       
        rutafo=(rs.getString("rutafoto_usuario"));
    
    String fu=rutafo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lbfoto.setIcon(new ImageIcon(foto));
            lbfoto.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No fo")){
         lbfoto.setIcon(null);
              lbfoto.setText("FOTO");
        }else{
                lbfoto.setText("");  
              }
    
   
        }
    }
    catch (SQLException ex) {}
    
    }                  // TODO add your handling code here:
    }//GEN-LAST:event_btediActionPerformed

    private void bteliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteliActionPerformed
borrar();
borrar();

String ceco="0";
   
   //cod = JOptionPane.showInputDialog("Cedula:");
   US=JOptionPane.showInputDialog("Usuario:");
   CO=JOptionPane.showInputDialog("Contraseña:");
    if(US.equals("")||CO.equals("")){
         JOptionPane.showMessageDialog(this, "Intentelo de nuevo");
         bloqueo();

    }else{
    try
    {
        String  SQLr = "select * from usuario where usuario_usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña_usuario='"+ encriptaEnMD5(CO)+"'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula_usuario");
       bloqueo();
           } 
        if(ceco.equals("0")){
          JOptionPane.showMessageDialog(null, "Usuario no Existe");
          
        }else{
  String SQL1 = "SELECT * FROM usuario WHERE usuario_usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña_usuario='"+ encriptaEnMD5(CO)+"'";
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
       bloqueo();
      
  cod=rs.getString("cedula_usuario");
     txtcedu.setText(rs.getString("cedula_usuario"));
     
    txtnombre.setText(rs.getString("nombre_usuario"));
       txtapellido.setText(rs.getString("apellido_usuario"));
 
     String sex=rs.getString("sexo_usuario");
     
     if(sex.equals("Femenino")) {
       rf.isSelected();
        rf.setSelected(true);
     }else{
        rm.isSelected();
        rm.setSelected(true);
     }
     
   
    
       
      txtusu.setText(US);
     txtclave.setText(CO);
       
        rutafo=(rs.getString("rutafoto_usuario"));
    
    String fu=rutafo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lbfoto.setIcon(new ImageIcon(foto));
            lbfoto.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No fo")){
         lbfoto.setIcon(null);
              lbfoto.setText("FOTO");
        }else{
                lbfoto.setText("");  
              }
    bloqueo();
    int resp = JOptionPane.showConfirmDialog(this, "Deseas Eliminarlo", "Eliminar Dato", 0);
      if (resp == 0) {
       
          PreparedStatement psd = null;
        String  SQL = " DELETE FROM usuario WHERE cedula_usuario ='" +txtcedu.getText() + "'";
          psd = this.cn.prepareStatement(SQL);
          psd.execute();
          psd.close();
          borrar();
        bloqueo();
          JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO");
        
        
      } else {
     borrar();
         bloqueo(); // JOptionPane.showMessageDialog(null, "No existe");
      }
        }
    }
    catch (SQLException ex) {}
    
    }                  // TODO add your handling code here:      
        // TODO add your handling code here:
    }//GEN-LAST:event_bteliActionPerformed

    private void rmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmActionPerformed
if(rm.isSelected()){
        
          rf.setSelected(false);
     }        // TODO add your handling code here:
    }//GEN-LAST:event_rmActionPerformed

    private void rfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rfActionPerformed
if(rf.isSelected()){
        
          rm.setSelected(false);
     }        // TODO add your handling code here:
    }//GEN-LAST:event_rfActionPerformed

    private void lbfotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbfotoMouseClicked
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
            rus = file.getName();
          
            rutafo=(String.valueOf(file));
            rus =  rutafo;

            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            Image foto = getToolkit().getImage(rus);
            //Le damos dimension a nuestro label que tendra la imagen
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            //Imprimimos la imagen en el label
           lbfoto.setIcon(new ImageIcon(foto));
            lbfoto.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_lbfotoMouseClicked

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
            java.util.logging.Logger.getLogger(ModSeguRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModSeguRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModSeguRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModSeguRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModSeguRegi().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedi;
    private javax.swing.JButton bteli;
    private javax.swing.JButton btgua;
    private javax.swing.JButton btnue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbfoto;
    private javax.swing.JRadioButton rf;
    private javax.swing.JRadioButton rm;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcedu;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
