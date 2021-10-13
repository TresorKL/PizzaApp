/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.extra;

import za.ac.tut.extradatainterface.ExtraDataInterface;
import za.ac.tut.extrainterface.ExtraInterface;

/**
 *
 * @author tresorkl
 */
public abstract class Extra implements ExtraInterface, ExtraDataInterface {
    
    private String extraName;
    private int qty;
    
    public Extra(){
    }
    
      public Extra(String extraName, int qty){
          
          this.extraName= extraName;
          this.qty = qty;
      }
    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }
    

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    @Override
     public abstract double determineExtraPrice();

   
     
    
    
}
