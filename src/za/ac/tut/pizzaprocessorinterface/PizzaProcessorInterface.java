/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.pizzaprocessorinterface;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import za.ac.tut.pizza.Pizza;
import za.ac.tut.sizeexception.SizeException;

/**
 *
 * @author tresorkl
 */
public interface PizzaProcessorInterface {
    
    public void determineBestPizzaType(List<Pizza> pizzaTypes, String [] bestPizzaArray);
    
    public void determineNumOfPizzaTypeBought(List<Pizza> pizzaTypes,int[] NumOfPizzaOfEachTypeBought);
    
    public void writePizzaInfoToFile(List<Pizza>currentCleantOrders,File file) throws IOException;
    
    public void writeDailySummaryToFile(double  dailyAmountMade,int [] NumOfPizzaOfEachTypeBought,String bestPizzaName, File summaryFile) throws IOException;
    
    public void readAndDisplayPizzaInfoFromFile(File file) throws IOException;
    
    public void updateOrdersInDataBase(List<Pizza>currentClientOrders)throws SQLException,Exception;
    
    public void updateDailySummary(double  dailyAmountMade,int [] NumOfPizzaOfEachTypeBought,String bestPizzaName)throws SQLException,Exception;


    
}
