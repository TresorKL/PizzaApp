/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.salade;

import za.ac.tut.extra.Extra;

/**
 *
 * @author tresorkl
 */
public class Salade extends Extra {

    
    public Salade(){
        
        
    }
    
    
    public Salade( String extraName, int qty){
     super(extraName, qty);   
     }
    
    @Override
    public double determineExtraPrice() {
        double extraPrice =0;
        
        extraPrice = getQty() * SALADE_PRICE;
        
        
        return extraPrice;
    }
    
}
