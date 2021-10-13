/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaapp;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import za.ac.tut.cocacola.CocaCola;
import za.ac.tut.extra.Extra;
import za.ac.tut.fanta.Fanta;
import za.ac.tut.hawaianpizza.HawaianPizza;
import za.ac.tut.pasta.Pasta;
import za.ac.tut.pepperoni.Pepperoni;
import za.ac.tut.pizza.Pizza;
import za.ac.tut.processor.Processor;
import za.ac.tut.salade.Salade;
import za.ac.tut.supremepizza.SupremePizza;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.sizeexception.SizeException;
/**
 *
 * @author tresorkl
 */
public class PizzaFram extends javax.swing.JFrame {
    //DECLARING NEEDED VARIABLES
    List<Pizza> pizzaTypesBought = new ArrayList<Pizza>();
    List<Pizza> currentClientOrders = new ArrayList<Pizza>();
    List<Extra> extras = new ArrayList<Extra>();
    double change=0;
    double extraPrice =0;
    double currentClientpizzaPrice =0;
    boolean keepShopOpen = true;
    String nextClientOptStr;
    int nextClientOpt;
     double amountDue=0;
     double TotalAmountDue =0;
    double dailyAmountMade = 0;
    File pizzaInfo = new File("./pizzaInfoFile");
    File summaryFile = new File("./summaryFile");
    
    //variables that will help to connect to the databse using the driver manager
    String USER = "root";
    String PASS_WORD = "Trezor2001";
    String DB_URL = "jdbc:mysql://localhost:3306/pizzashop";

    /**
     * Creates new form PizzaFram
     */
     //came from jframe
    public PizzaFram() {
        initComponents();
    }
    

     DefaultTableModel model;
    
    //method to determine the price of pizzas bought by a single customer
    public double DetermineCurrentClientpizzaPrice(List<Pizza> pizzaTypesBought, List<Pizza> currentCleantOrders) throws SizeException{ 
       
    String choicestr;
    int choice;
    boolean isValid = true;
    double currentClientpizzaPrice =0;
 
    
        // getting data from the user 
        
        String pizzaTypeStr = pizzaTypeCode.getText().trim();
        String sizeStr = sizeCode.getText().trim();
        String quantitystr = quantityCode.getText().trim();
        
        // converting all to apropriate data type
        int pizzaType = Integer.parseInt(pizzaTypeStr);
        int size = Integer.parseInt(sizeStr);
        int quantity = Integer.parseInt(quantitystr);
   
    // this if statement creates Pizza object from the user input
      if(pizzaType == 1){
        
        
         Pizza pepperoni = new Pepperoni();
        
         // set parameters
         pepperoni.setSize(size);
         pepperoni.setQuantity(quantity);
         pepperoni.setPizzaName("PEPPERONI");
        
         // here we store the object inside a list that will be reset 
          currentCleantOrders.add(pepperoni);
          
         // here we store the object inside a list that will not  be reset it'll to the summary 
          pizzaTypesBought.add(pepperoni);
        
          
       }else if (pizzaType == 2){
        
        Pizza supremePizza = new SupremePizza();
        
       supremePizza.setSize(size);
        supremePizza.setQuantity(quantity);
        supremePizza.setPizzaName("SUPREME PIZZA");
        
        currentCleantOrders.add(supremePizza);
        pizzaTypesBought.add(supremePizza);
      
         
       }else{
          Pizza hawaianPizza = new HawaianPizza();
        
          hawaianPizza.setSize(size);
          hawaianPizza.setQuantity(quantity);
          hawaianPizza.setPizzaName("HAWAIAN PIZZA");
          
          currentCleantOrders.add(hawaianPizza);
          pizzaTypesBought.add(hawaianPizza);
        
           
         }
    
      
      //incrementing general price from the list
       
        
       for(int j =0; j<  currentCleantOrders.size(); j++){
    
          currentClientpizzaPrice += currentCleantOrders.get(j).determinePizzaPrice();
        }

      
           return currentClientpizzaPrice;
      }
    
    
    
    
    //method to determine the price of extra items bought by a single customer
    public  double determineExtraPrice(List <Extra> extras ){
        
      
        double extraPrice = 0;    

        // getting data from the user choices
         String extraQtyStr = extraQtyCode.getText().trim();
         String extraCodeStr = extraCode.getText().trim();
        
         //converting to the wanted data types
        int extraQty = Integer.parseInt(extraQtyStr);
        int extraCodeOpt = Integer.parseInt(extraCodeStr);
        

// this switch statement help to create Extra object from the user choices
 switch(extraCodeOpt){
     case 1:
         
         CocaCola coca = new CocaCola();
         
         // set
         coca.setQty(extraQty);
         coca.setExtraName("COCACOLA");
         
         // here we store object in a list
         extras.add(coca);
         
         
         break;
     case 2: 
         Fanta fanta = new Fanta();
         fanta.setQty(extraQty);
         fanta.setExtraName("FANTA");
         extras.add(fanta);

         break;
     case 3: 
         Salade salade = new Salade();
         salade.setQty(extraQty);
         salade.setExtraName("SALADE");
         extras.add(salade);
         
         break;
     case 4: 
        Pasta pasta = new Pasta();
        pasta.setQty(extraQty);
        pasta.setExtraName("PASTA");
        extras.add(pasta);
     
         break;
      
     }
 
 
          for(int i =0; i<extras.size();i++ ){
            
           extraPrice += extras.get(i).determineExtraPrice();
            
    }
     
        
        return  extraPrice;
    }
    
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pizzaTypeCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        sizeCode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        quantityCode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        AddPizzaOrderButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        extraCode = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        extraQtyCode = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        makePayment = new javax.swing.JButton();
        nextCustomer = new javax.swing.JButton();
        totAmount = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        DisplayInfoFromFile = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        closeShop = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("1 PEPPERONI");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("2 SUPREME PIZZA");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("FLAVOUR CODE: ");

        pizzaTypeCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pizzaTypeCodeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("SIZE CODE");

        sizeCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeCodeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("QUANTITY");

        quantityCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityCodeActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("1 LARGE");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("2 MEDIUM");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("3 SMALL");

        AddPizzaOrderButton.setBackground(new java.awt.Color(255, 0, 51));
        AddPizzaOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        AddPizzaOrderButton.setText("ADD ORDER");
        AddPizzaOrderButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        AddPizzaOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPizzaOrderButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setText("EXTRA CODE");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setText("QUANTITY");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("3.  SALADE R19");

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("4. PASTA R22 ");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("2. FANTA R11");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("1. COCA R10");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(extraCode, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(extraQtyCode, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(401, 401, 401)
                        .addComponent(jLabel13))
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(31, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(extraCode, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(extraQtyCode, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        makePayment.setBackground(new java.awt.Color(204, 204, 255));
        makePayment.setText("MAKE PAYMENT");
        makePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makePaymentActionPerformed(evt);
            }
        });

        nextCustomer.setBackground(new java.awt.Color(0, 0, 0));
        nextCustomer.setForeground(new java.awt.Color(255, 255, 255));
        nextCustomer.setText("NEXT ");
        nextCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextCustomerActionPerformed(evt);
            }
        });

        totAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totAmountActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("TOTAL:");

        DisplayInfoFromFile.setBackground(new java.awt.Color(102, 102, 102));
        DisplayInfoFromFile.setForeground(new java.awt.Color(255, 255, 255));
        DisplayInfoFromFile.setText("DISPLAY DATA FROM FILE");
        DisplayInfoFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayInfoFromFileActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pizzaapp/Pizza-icon2.png"))); // NOI18N

        closeShop.setBackground(new java.awt.Color(255, 0, 0));
        closeShop.setForeground(new java.awt.Color(255, 255, 255));
        closeShop.setText("CLOSE SHOP");
        closeShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeShopActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("3 HAWAIAN PIZZA");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPTION", "QUANTITY", "AMOUNT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pizzaTypeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(143, 143, 143)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sizeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(closeShop))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(quantityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(147, 147, 147)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AddPizzaOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(totAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(173, 173, 173)
                            .addComponent(makePayment))
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(nextCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(DisplayInfoFromFile))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(521, 521, 521))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(makePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(pizzaTypeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(sizeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(quantityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(149, 149, 149))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(AddPizzaOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closeShop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nextCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DisplayInfoFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setBackground(new java.awt.Color(204, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));

        jLabel20.setFont(new java.awt.Font("Microsoft Tai Le", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("PIZZA SHOP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(233, 233, 233)
                        .addComponent(jLabel1)
                        .addGap(200, 200, 200))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void tableUpdate(){
        
        
    }
    private void pizzaTypeCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pizzaTypeCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pizzaTypeCodeActionPerformed

    private void sizeCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeCodeActionPerformed

    private void quantityCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityCodeActionPerformed
// payment button 
    private void makePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makePaymentActionPerformed
    // TODO add your handling code here:
    
    boolean isValid = true;
      double change =0;
      
      do{
         String paymentStr =JOptionPane.showInputDialog(null,"The Amount due is R"+TotalAmountDue+"\n"+"Please Make payment", "PAYMENT", JOptionPane.INFORMATION_MESSAGE);
         double payment = Double.parseDouble(paymentStr);
         
         if(payment >= TotalAmountDue){
             
             isValid = false;
             change = payment - TotalAmountDue  ;
             JOptionPane.showMessageDialog(null,"YOUR CHANGE IS R"+change, "CHANGE",JOptionPane.INFORMATION_MESSAGE);
                    
             
         }else{
             
              isValid = true;
              JOptionPane.showMessageDialog(null,"R"+payment+" IS LESS THAN AMOUNT DUE R"+TotalAmountDue, "INVALID PAYMENT",JOptionPane.WARNING_MESSAGE);
                
         }
          
          
      }while(isValid);
    
    
    }//GEN-LAST:event_makePaymentActionPerformed

    // ADD ORDER BUTTON
    private void AddPizzaOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPizzaOrderButtonActionPerformed

        
        
      Processor ps = new Processor();
      if(!pizzaTypeCode.getText().isEmpty() && !sizeCode.getText().isEmpty() && !quantityCode.getText().isEmpty()){
          
          try {
              currentClientpizzaPrice = DetermineCurrentClientpizzaPrice( pizzaTypesBought, currentClientOrders);
          } catch (SizeException ex) {
              Logger.getLogger(PizzaFram.class.getName()).log(Level.SEVERE, null, ex);
          }

        JOptionPane.showMessageDialog(null,"ORDER ADDED");
        
        int latestOrderIndex = currentClientOrders.size() - 1;
        model = (DefaultTableModel)jTable1.getModel();
        
      // ADDing all order current orders in the fram table
      model.addRow(new Object[]{
          
          currentClientOrders.get(latestOrderIndex).getPizzaName(), 
          currentClientOrders.get(latestOrderIndex).getQuantity(),
          currentClientOrders.get(latestOrderIndex).determinePizzaPrice()
          
      });
      
      
      // here we clean (reset) the input text field for the next order
        pizzaTypeCode.setText("");
        sizeCode.setText("");
        quantityCode.setText("");
        pizzaTypeCode.requestFocus();
        
      }else if(!extraQtyCode.getText().isEmpty() &&  !extraCode.getText().isEmpty()){
          
          //calling extra price method
            extraPrice = determineExtraPrice(extras);
          
           int latestExtraIndex = extras.size() -1;
           model = (DefaultTableModel)jTable1.getModel();
      
         
        model.addRow(new Object[]{
          
          extras.get(latestExtraIndex).getExtraName(),
          extras.get(latestExtraIndex).getQty(),
          extras.get(latestExtraIndex).determineExtraPrice()
          
      });
      
     
        
      extraQtyCode.setText("");
      extraCode.setText("");
   
          
        
      }
      
      // calculating the amount due
      amountDue = (extraPrice) + (currentClientpizzaPrice);
      
      TotalAmountDue = amountDue;
      
      totAmount.setText("R"+TotalAmountDue);
      
      
      // resetting the amount due value in case there will an order so the total amount can be well calculated
      amountDue=0;
      
        try {
            // updating orders int the database
            ps.updateOrdersInDataBase(currentClientOrders);
        } catch (Exception ex) {
            Logger.getLogger(PizzaFram.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      // write pizza description to the file
        try {
            

            ps.writePizzaInfoToFile(currentClientOrders, pizzaInfo);
        } catch (IOException ex) {
            Logger.getLogger(PizzaFram.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_AddPizzaOrderButtonActionPerformed

   
    
    private void totAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totAmountActionPerformed
        // TODO add your handling code here:
        
         
    }//GEN-LAST:event_totAmountActionPerformed
 // CLOSE SHOP BUTTON
    private void closeShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeShopActionPerformed
       
        // incrementing the dailyAmount 
        dailyAmountMade += TotalAmountDue;
        
         String [] bestPizzaInfo={"",""};
        Processor ps = new Processor();

ps.determineBestPizzaType(pizzaTypesBought, bestPizzaInfo);
         String bestPizzaName = bestPizzaInfo[1];

int [] NumOfPizzaOfEachTypeBought = {0,0,0};

 ps.determineNumOfPizzaTypeBought(pizzaTypesBought,NumOfPizzaOfEachTypeBought);

 String NumOfPizzaTypeBought = "PEPEPERONI: "+ NumOfPizzaOfEachTypeBought[0]+"\n"+"HAWAIAN PIZZA: "+  NumOfPizzaOfEachTypeBought[1]+"\n"+
                                   "SUPREME PIZZA: "+ NumOfPizzaOfEachTypeBought[2];
// Displaying the summary
JOptionPane.showMessageDialog(null,bestPizzaInfo[0]+"\n\n"+NumOfPizzaTypeBought+"\n"+
                                   "Daily amount made: "+ dailyAmountMade, "SUMMARY ",JOptionPane.INFORMATION_MESSAGE);

        try {
            //update summary in database
            ps.updateDailySummary(dailyAmountMade, NumOfPizzaOfEachTypeBought, bestPizzaName);
        } catch (Exception ex) {
            Logger.getLogger(PizzaFram.class.getName()).log(Level.SEVERE, null, ex);
        }

// write the daily summary to the file
        try {
            
            ps.writeDailySummaryToFile(dailyAmountMade, NumOfPizzaOfEachTypeBought, bestPizzaName, summaryFile);
        } catch (IOException ex) {
            Logger.getLogger(PizzaFram.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
dispose();
         
    }//GEN-LAST:event_closeShopActionPerformed

      // NEXT customer button
    private void nextCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextCustomerActionPerformed
      
        
          
   
   // increamentting the daily amount made
   dailyAmountMade += TotalAmountDue;
   
   
   // reset/clear every thing from the current client so that it won't crash with the next customers orders
  currentClientOrders.clear();
  extras.clear();
   
  totAmount.setText("");
 
    model = (DefaultTableModel)jTable1.getModel();
        
    model.setRowCount(0);
    extraPrice =0;
    currentClientpizzaPrice =0;    
    amountDue=0;
        
    }//GEN-LAST:event_nextCustomerActionPerformed

    private void DisplayInfoFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayInfoFromFileActionPerformed
       Processor ps = new Processor();
       
        try {
            ps.readAndDisplayPizzaInfoFromFile(pizzaInfo);
        } catch (IOException ex) {
            
            Logger.getLogger(PizzaFram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DisplayInfoFromFileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PizzaFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PizzaFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PizzaFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PizzaFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PizzaFram().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPizzaOrderButton;
    private javax.swing.JButton DisplayInfoFromFile;
    private javax.swing.JButton closeShop;
    private javax.swing.JTextField extraCode;
    private javax.swing.JTextField extraQtyCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton makePayment;
    private javax.swing.JButton nextCustomer;
    private javax.swing.JTextField pizzaTypeCode;
    private javax.swing.JTextField quantityCode;
    private javax.swing.JTextField sizeCode;
    private javax.swing.JTextField totAmount;
    // End of variables declaration//GEN-END:variables
}
