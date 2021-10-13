/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.fanta;

import za.ac.tut.extra.Extra;

/**
 *
 * @author tresorkl
 */
public class Fanta extends Extra {
    
    public Fanta(){
        
    }
    
    public Fanta(int qty,String extraName){
        super(extraName, qty);
    }

    @Override
    public double determineExtraPrice() {
             
        
         double extraPrice =0;
        
        extraPrice = getQty() * FANTA_PRICE;
        
        
        return extraPrice;
    }
    
}
