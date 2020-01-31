/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;

/**
 *
 * @author black
 */
public class Tarjeta {
  
  
 
  
     
  
     private String nombre1;
      private String apellido1;
    private String rufo2;
    private String rufo3;
       private String fechac;
        private String fechae;
       
   //  private String sexo1;
   
     
 
    public Tarjeta( String nombre1,String apellido1,String rufo2,String rufo3,String fechac,String fechae) {
      
     
    
 
      this.nombre1= nombre1;
       this.apellido1=apellido1;
        
         this.rufo2=rufo2;
       this.rufo3= rufo3;
        this.fechac=fechac;
        this.fechae=fechae;
       
    }

  
 public String getNombre1() {
        return nombre1;
    }
    public String getApellido1() {
        return apellido1;
    }
  
    
 
  public String getRufo2() {
        return rufo2;
    }
 public String getRufo3() {
      return rufo3;
    }
    
       public String getFechac() {
        return fechac;
    }
     public String getFechae() {
        return fechae;
    }
     
  //  public String getSexo1() {
    //    return sexo1;
    //}

   
    

    

  
}
