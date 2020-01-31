/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyberplaysoftware1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
/**
 *
 * @author Gtecno
 */
public final class big {
     private BigDecimal  faAmountOne;
         private BigDecimal  faAmountTwo;
        private static BigDecimal Two1= new BigDecimal("4");
        private static BigDecimal Two2= new BigDecimal("3");
          private BigDecimal getSum(){
             return faAmountOne.add(faAmountTwo);
              }
                  private BigDecimal getDifference(){
             return faAmountOne.subtract(faAmountTwo);
              }
                //    private void log(String aText){
              //  System.out.print(aText);  
              //}
         public static void main(String args[]) {
            
         }
       void big1 (){
         
          faAmountOne=Two1;
            faAmountTwo=  Two2; 
           
            System.out.print("sw"+ faAmountOne.add(faAmountTwo));
     }
       public void doCalculos(){
          // log("s"+ faAmountOne);
          // log ("w"+faAmountTwo);
            
          //   log("s"+ getDifference());
           //  log("w"+ getAverage());
          /// log("s"+ getPercentage());
          //  log("s"+ getPercentageChange());
                    
                   
       }
      
     //    private static int Rounding_mode= BigDecimal.ROUND_HALF_EVEN;
       //    private static int Decimals= 2;
         //   private static int exDecimals= 4;
           //  private static BigDecimal Two= new BigDecimal("2");
            // private static BigDecimal Hundred= new BigDecimal("100");
            //  private static BigDecimal Por= new BigDecimal("5.25");
            
              
            //        private BigDecimal getAverage(){
            // return getSum().divide(Two,Rounding_mode );
             // }
               //       private BigDecimal getPercentage(){
             //BigDecimal result= faAmountOne.multiply(Por);
             //result=result.divide(Hundred,Rounding_mode);
           //  return rounded(result);
             // }
               //         private BigDecimal getPercentageChange(){
            // BigDecimal fra= getDifference().divide(faAmountOne, exDecimals,Rounding_mode);
            
            // return rounded(fra.multiply(Hundred));
           //   }
             //   private BigDecimal rounded(BigDecimal aNumber){
           
            
            // return  aNumber.setScale(Decimals,Rounding_mode);
              //}
}
