/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.pasta;

import za.ac.tut.extra.Extra;

/**
 *
 * @author tresorkl
 */
public class Pasta extends Extra {
    
    public Pasta(){
    }
    
     public Pasta(String extraName,int qty){
         super( extraName, qty);
    }

    @Override
    public double determineExtraPrice() {
        
        
         double extraPrice =0;
        
        extraPrice = getQty() * PASTA_PRICE;
        
        
        return extraPrice;
    }
    
}
