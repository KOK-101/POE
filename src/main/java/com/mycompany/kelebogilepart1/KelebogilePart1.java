/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kelebogilepart1;
import java.util.Scanner;

/**
 *
 * @author K.O.K
 */
public class KelebogilePart1 {

    public static void main(String[] args) {
        
        // Scanner.
        
        Scanner myInput = new Scanner(System.in);
        
        //Declarations.
        
        String name;
        String surName;
        String userName;
        String passWord;
        String cellNumber;
       
        //Prompting user to register an account. 
        
        System.out.println("\n----------Registration----------");
        
        System.out.println("\nEnter your First Name: ");
        name = myInput.nextLine();
        System.out.println("Enter your Surname: ");
        surName = myInput.nextLine();
        
     
       //login object used to check. 
        
       Login check = new Login("","","","",""); 
        
     // Prompting the user and looping if conditions are not met.
     //userName input looping
     
        System.out.println("Enter your Username(Must contain an underscore and be less than 5 characters): ");
        userName = myInput.nextLine();
        while(!check.checkUserName(userName)){
         
        System.out.println("Username is not correctly formatted; "
                   + "please ensure that your username contains an underscore"
                   + "and is no more than five characters in length");
        
        System.out.println("Enter your Username(Must contain an underscore and be less than 5 characters): ");
        userName = myInput.nextLine();
        }
        System.out.println("Username successfully captured :)!!!");
        
     //passWord input looping
     
        System.out.println("Enter your Password(Must contain 8+ character,a capital letter,a number and a special character): ");
        passWord = myInput.nextLine();
        while(!check.checkPasswordComplexity(passWord)){
         
        System.out.println("Password is not correctly formatted; "
                   + "please ensure that your password contains at least eight characters"
                   + "a capital letter, a number and a special character");
            
        System.out.println("Enter your Password(Must contain 8+ character,a capital letter,a number and a special character): ");
        passWord = myInput.nextLine();
        }
        System.out.println("Password successfully captured :)!!!");
      
      //cellphone number input looping
      
        System.out.println("Enter your Cellphone Number e.g.(+27956891234): ");
        cellNumber = myInput.nextLine();
        while(!check.checkCellPhoneNumber(cellNumber)){
    
        System.out.println("Cell phone number incorrectly formatted "
                   + "or does not contain international code");
            
        System.out.println("Enter your Cellphone Number e.g.(+27956891234): ");
        cellNumber = myInput.nextLine();
        }
        System.out.println("Cell phone number successfully added :)!!!");
        
        //This is the real login object
        
        Login register = new Login(name, surName, userName, passWord, cellNumber);
        
        //Using the login object to register the account
      
        System.out.println(register.registerUser());
        
     // boolean to make sure the inputs match  
        
        boolean registeredSuccessfully = register.checkUserName(userName)&& register.checkPasswordComplexity(passWord)&& register.checkCellPhoneNumber(cellNumber);
       
     // Creating the Login 
     
        if(registeredSuccessfully){
            
            System.out.println("\n--------------LOGIN------------- ");
         
         //if Login credentials are wrong we loop using a while loop and a boolean 
         // boolean to help make the loop a success
         
            boolean loginSuccessful = false;
            
            while(!loginSuccessful){
            
            System.out.println("\nEnter your username: ");
            String enteredUsername = myInput.nextLine();
            System.out.println("Enter your password: ");
            String enteredPassword = myInput.nextLine();
            
          //Verifying credentials before print the success message 
          
            loginSuccessful = register.loginUser(enteredUsername, enteredPassword);
            register.returnLoginStatus();
            
            System.out.println(register.returnLoginStatus());
            }
           
            //starting a quick chat
            System.out.println("\n--------Welcome to QuickChat--------");
            
            //creating loops to ensure that quick chat only works when they have loggedin
            int messageMax = 0;
            boolean messageAmount = false;
            
            while(!messageAmount){
                
                System.out.print("\nHow many messages do you want to enter?: ");
                try {
                    messageMax = Integer.parseInt(myInput.nextLine().trim());
                    
                    if ( messageMax > 0){
                        messageAmount = true;
                    }
                    else{
                        System.out.println("Please enter a number greater than 0 ");
                    }
                }
                catch ( NumberFormatException e){
                    System.out.println("Please enter a valid number ");
                }  
            }
            
            //creating loops for a menu selection the user needs to make
            int numMessagesEntered = 0;
            boolean correct = true;
            
            while(correct){
                System.out.println("\n----- Message On Quick Chat -----");
                System.out.println("\n1) Send Messages ");
                System.out.println("2) Send recently sent message ");
                System.out.println("3) Quit ");
                System.out.print("Select an option: ");
                
                int option;
                
                try {
                    option = Integer.parseInt(myInput.nextLine().trim());
                }
                catch(NumberFormatException e) {
                    option = -1;
                }
                
                switch (option){
                    
                    case 1:{
                        
                        if (numMessagesEntered >= messageMax){
                            System.out.println("All "+ messageMax + " messages have be entered ");
                            break;
                        }
                        
                        // continue asking until it is valid
                        String recipient;
                        boolean isRecipientValid = false;
                        
                        do{
                            System.out.print("\nEnter recipients cellphone number e.g.(+27834569922): ");
                            recipient = myInput.nextLine();
                            
                            Messages temp = new Messages(recipient,"temp");
                            
                            System.out.println(temp.checkRecipientNumber());
                            isRecipientValid = Login.checkCellPhoneNumber(recipient);
                        }
                        
                        while(!isRecipientValid);
                       
                        //will hold the message once it is valid
                        Messages message;
                        
                        //loops to ask the user to enter the message
                        while(true){
                            System.out.print("\nEnter message(250 characters max): ");
                            String text = myInput.nextLine();
                            
                            if(text.trim().isEmpty()){
                                System.out.println("The message cannot be empty ");
                            }
                            else if(text.length()> Messages.maxCharacter){
                                System.out.println("Plese enter a message of less than 250 characters ");
                                System.out.println(new Messages(recipient, text).checkMessageLength());
                            }
                            else {
                                message = new Messages(recipient, text);
                                System.out.println(message.checkMessageLength());
                                break;
                            }
                        }
                        
                        //outputting th message ID
                        System.out.println(message.getMessageIDStatus());
                        
                        int choice;
                        
                        //looping for menu selection
                        do{
                            System.out.println("\n-----WHAT DO YOU WANT TO DO WITH THE MESSAGE-----");
                            System.out.println("\n1) Send Message ");
                            System.out.println("2) Disregard message ");
                            System.out.println("3) Store Message to send later ");
                            System.out.print("Select an option: ");
                            
                            try{
                                choice = Integer.parseInt(myInput.nextLine().trim());
                            }
                            catch (NumberFormatException e){
                                choice = -1;
                            }
                        }
                        
                        //checking if the choice is below 1 or greate than 3
                        while (choice < 1 || choice > 3);
                        
                        //doing the choice
                        System.out.print(message.sentMessages(choice));
                        
                        if(choice == 1){
                            System.out.println("\n"+ message.getDetails());
                        }
                        else if(choice == 2){
                            if (myInput.nextLine().trim().equals("0")){
                                System.out.println("Message Deleted");
                            }
                        }
                        
                        //Counting towards the limit of messages
                        numMessagesEntered++; 
                        break;
                        
                    }
                    
                    
                    case 2:
                        System.out.println("Coming Soon ");
                        break;
                        
                    case 3:
                        correct = false;
                        break;
                        
                    default:
                        System.out.println("Option invalid, Please select between option 1, 2 or 3 ");
                }
            }
            
            //outpting the messages and its codes and the total number of messages sent
            System.out.println("\n-------- Messages Sent :) --------");
            System.out.println(Messages.printMessages());
            System.out.println("\nTotal messages sent: " + Messages.returnTotalMessages());
        }
        myInput.close();
    }   
}   
   
   
        
 
