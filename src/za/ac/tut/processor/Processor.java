/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import za.ac.tut.hawaianpizza.HawaianPizza;
import za.ac.tut.pepperoni.Pepperoni;
import za.ac.tut.pizza.Pizza;
import za.ac.tut.pizzaprocessorinterface.PizzaProcessorInterface;
import za.ac.tut.sizeexception.SizeException;
import za.ac.tut.supremepizza.SupremePizza;

/**
 *
 * @author user
 */
public class Processor implements PizzaProcessorInterface {

    @Override
    public void determineBestPizzaType(List<Pizza> pizzaTypes,String [] bestPizzaInfo) {
        
        int pepperoniQty  =0;
         int hawaianPizzaQty =0;
        int supremePizzaQty =0;
        String bestpizzaType = "";
        String bestPizzaName ="";
        
        int highistQty = 0;
        
        for(int i=0; i< pizzaTypes.size(); i++){
            
            if(pizzaTypes.get(i) instanceof Pepperoni ){
                
                pepperoniQty += pizzaTypes.get(i).getQuantity();
                
            }else if(pizzaTypes.get(i) instanceof HawaianPizza ){
                
                hawaianPizzaQty += pizzaTypes.get(i).getQuantity();
                
            }else if(pizzaTypes.get(i) instanceof SupremePizza){
                
                supremePizzaQty += pizzaTypes.get(i).getQuantity();
            }
            
            highistQty = Math.max(Math.max(pepperoniQty, hawaianPizzaQty), supremePizzaQty);
            
           if(highistQty == pepperoniQty){
               bestpizzaType = "Best Pizza types: PEPPERONI"+"\n"+ "Quantity bougth: "+highistQty;
               bestPizzaName = "PEPPERONI";
           }else if(highistQty == hawaianPizzaQty){
               
                bestpizzaType = "Best Pizza types: HAWAIAN PIZZA"+"\n"+ "Quantity bougth: "+highistQty;
                bestPizzaName ="HAWAIANPIZZA";
           }else{
            
               bestpizzaType = "Best Pizza types: SUPREME PIZZA"+"\n"+ "Quantity bougth: "+highistQty;
               bestPizzaName = "SUPREME PIZZA";
           }
           
           bestPizzaInfo[0] = bestpizzaType;
           bestPizzaInfo[1] = bestPizzaName;
        }
        
        
    }

    @Override
    public void determineNumOfPizzaTypeBought(List<Pizza> pizzaTypes,int[] NumOfPizzaOfEachTypeBought) {
        
        int pepperoniQty  =0;
        int hawaianPizzaQty =0;
        int supremePizzaQty =0;
        String NumOfPizzaTypeBought = "";
        
        int highistQty = 0;
        
        for(int i=0; i< pizzaTypes.size(); i++){
            
            
            
            if(pizzaTypes.get(i) instanceof Pepperoni ){
                
                pepperoniQty += pizzaTypes.get(i).getQuantity();
                
            }else if(pizzaTypes.get(i) instanceof HawaianPizza ){
                
                hawaianPizzaQty += pizzaTypes.get(i).getQuantity();
                
            }else if(pizzaTypes.get(i) instanceof SupremePizza){
                
                supremePizzaQty += pizzaTypes.get(i).getQuantity();
            }
            
            NumOfPizzaOfEachTypeBought[0] =pepperoniQty;
            NumOfPizzaOfEachTypeBought[1] =hawaianPizzaQty;
            NumOfPizzaOfEachTypeBought[2] =supremePizzaQty;
             
            
    }
   
    
   
}

    @Override
    public void writePizzaInfoToFile(List<Pizza> currentClientOrders,File file)throws IOException{
        
        int latestOrderIndex = currentClientOrders.size() -1;
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
        
        String data ="";
        
        data = data + currentClientOrders.get(latestOrderIndex).getPizzaName() +" - "+
                 currentClientOrders.get(latestOrderIndex).getQuantity() +" - "+
                currentClientOrders.get(latestOrderIndex).determinePizzaPrice();
        
       bw.write(data);
       bw.newLine();
       bw.close();
        
    }

    @Override
    public void readAndDisplayPizzaInfoFromFile(File file) throws FileNotFoundException, IOException {
        
     BufferedReader br = new BufferedReader(new FileReader(file));
     
     String records = "";
     
     String data = br.readLine();
     
     while(data != null ){
         
         records = records + data+"\n";
          data = br.readLine();
     }
     br.close();
     
     JOptionPane.showMessageDialog(null,records,"PIZZA INFO",JOptionPane.INFORMATION_MESSAGE);
     
        
    }

    @Override
    public void writeDailySummaryToFile(double dailyAmountMade, int[] NumOfPizzaOfEachTypeBought, String bestPizzaName,File summaryFile) throws IOException {
       
       
       BufferedWriter bw = new BufferedWriter(new FileWriter(summaryFile));
       String data ="";
       
      
       data = data + dailyAmountMade +" - "+ NumOfPizzaOfEachTypeBought[0] +" - "+ NumOfPizzaOfEachTypeBought[1] +" - "+
                      NumOfPizzaOfEachTypeBought[2] +" - "+bestPizzaName;
       
       bw.write(data);
       bw.newLine();
       bw.close();
    }

    @Override
    public void updateOrdersInDataBase(List<Pizza> currentClientOrders)throws SQLException,Exception {
    //variables that will help to connect to the databse using the driver manager
    String USER = "root***";
    String PASS_WORD = "Trezor****";
    String DB_URL = "jdbc:mysql://localhost:3306/pizzashop";
    
    
     int latestOrderIndex = currentClientOrders.size() -1;
    

    // connection
    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
    
    PreparedStatement inserValue = conn.prepareStatement("insert into orders values(?,?,?)");
    // inserting values in the database's columns
    
    inserValue.setString(1, currentClientOrders.get(latestOrderIndex).getPizzaName());
    inserValue.setInt(2, currentClientOrders.get(latestOrderIndex).getQuantity());
    inserValue.setDouble(3,currentClientOrders.get(latestOrderIndex).determinePizzaPrice());
    
    inserValue.executeUpdate();
    
    conn.close();
    inserValue.close();
    

    }

    @Override
    public void updateDailySummary(double dailyAmountMade, int[] NumOfPizzaOfEachTypeBought, String bestPizzaName)throws SQLException,Exception {
    //variables that will help to connect to the databse using the driver manager
    String USER = "root***";
    String PASS_WORD = "Trezor****";
    String DB_URL = "jdbc:mysql://localhost:3306/pizzashop";
    
    
         Date date=null ;
    
       
        Connection conn = DriverManager.getConnection(DB_URL,USER, PASS_WORD);
        PreparedStatement dailyValue = conn.prepareStatement("insert into summary values(?,?,?,?,?,?)");
        Statement stmnt = conn.createStatement();
        
         String sql = "select current_date  from dual";
         
         ResultSet rs = stmnt.executeQuery(sql);
      
      
       while(rs.next()!=false){
         date = rs.getDate("current_date");
       }
       //date = rs.getDate("current_date");
       
       // use setter method to set values
        dailyValue.setDate(1,date );
        dailyValue.setDouble(2,  dailyAmountMade);
        dailyValue.setInt(3,  NumOfPizzaOfEachTypeBought[0]);
        dailyValue.setInt(4,  NumOfPizzaOfEachTypeBought[1]);
        dailyValue.setInt(5,  NumOfPizzaOfEachTypeBought[2]);
        dailyValue.setString(6, bestPizzaName);
        
        //execute the prepared statement
        dailyValue.executeUpdate();
        
        stmnt.close();
        conn.close();
        rs.close();
        dailyValue.close();
        
        
  
    
    }

  
    

}