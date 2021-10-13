/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.pizza;

import za.ac.tut.datainterface.DataInterface;
import za.ac.tut.pizzainterface.PizzaInterface;
import za.ac.tut.sizeexception.SizeException;

/**
 *
 * @author user
 */
public abstract class Pizza implements PizzaInterface, DataInterface  {
    
    private int size;
    private int quantity;
    private String pizzaName;

    public Pizza(){ 
    }
    
    public Pizza(int size, int quantity,String pizzaName) throws SizeException{
        setSize(size);
        this.quantity = quantity;
        this.pizzaName = pizzaName;
    }
    
    
    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName)  {
        
        this.pizzaName = pizzaName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) throws SizeException{
        if(isSizeValid(size)){
             this.size = size;
        }else{
            throw new SizeException("INVALID INPUT. Please choose between 1 and 3");
        }
       
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        
            this.quantity = quantity;
        
    }
    
    @Override
    public  abstract double determinePizzaPrice();

    
    
    private boolean isSizeValid(int size) {
        boolean isValid = false;
        
        if(size >= 1 && size<=3){
            isValid = true;
        }
            
       return isValid;     
    }

    
}
