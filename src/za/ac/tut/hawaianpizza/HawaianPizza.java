/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.hawaianpizza;

import za.ac.tut.pizza.Pizza;
import za.ac.tut.sizeexception.SizeException;

/**
 *
 * @author user
 */
public class HawaianPizza extends Pizza{
    
    public HawaianPizza(){
        
    }
    
     public HawaianPizza(int size, int quantity,String pizzaName) throws SizeException{
         super(size,quantity,pizzaName);
    }
    

    @Override
    public double determinePizzaPrice() {
       
        double pizzaPrice =0;
        
        if(getSize()==1){
            
            pizzaPrice = (SIZE_LARGE_PRICE + HAWAIIN_UNIT_PRICE)* getQuantity();
        }else if(getSize()==2){
            
            pizzaPrice = (SIZE_MEDIUM_PRICE + HAWAIIN_UNIT_PRICE) * getQuantity();
            
        }else{
             pizzaPrice = (SIZE_SMALL_PRICE + HAWAIIN_UNIT_PRICE) * getQuantity();
        }
        
        return pizzaPrice;
        
        
    }

   
}
