/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kelebogilepart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author K.O.K
 */
public class Messages {
    
    public static final int maxCharacter = 250;
    private static final String jsonFile = "messages.json";
    private static final ArrayList<Messages> sentMessages = new ArrayList<>();
    private static final ArrayList<Messages> storedMessages = new ArrayList<>();
    private static int numOfSentMessages = 0;
    
    private final String messageID ;
    private final int messageNum ;
    private final String recipient ;
    private final String messageText;
    private final String messageHash;
    
    public Messages(String recipient,String messageText){
        
        this.messageID = generateMessageID();
        this.messageNum = numOfSentMessages;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = messageHashCreate();
        
    }
    
    private static String generateMessageID(){
        
        long number = (long)(new Random().nextDouble()* 10_000_000_000L);
        return String.format("%010d", number);
        
    }
    
    public boolean checkMessageID(){
        
        return messageID != null && messageID.length() <= 10;
    }
    
    public String getMessageIDStatus(){
    
        return "Message ID generated: " + messageID;
    }
    
    public String checkRecipientNumber(){
        
        if (Login.checkCellPhoneNumber(recipient)){
            return "Cellphone number succesfully captured :)";
        }
        
        return "Cell phone number incorrectly formatted "
                + "or does not contain international code";
    }
    
    public String checkMessageLength(){
        
        int length = messageText == null ? 0: messageText.length();
        
        if(length <= maxCharacter){
            return "Message ready to be send ";
        }
        
        return "Message exceeds 250 characters by "+ (length - maxCharacter)
                +", please reduce the number of characters ";
    }
    
    public String messageHashCreate(){
        
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        
        return (messageID.substring(0, 2)+ ":" + messageNum + ":"+ first + last).toUpperCase();
    }
    
    public String sentMessages(int choice){
        
        switch(choice){
            case 1:
                sentMessages.add(this);
                numOfSentMessages++;
                return "\n----- Message successfully sent :) -----";
                
            case 2:
                return "Press 0 to delete the message ";
                
            case 3:
                storeMessage();
                return "Message successfully stored :) ";
               
            default:
                return "Your choice is invalid ";
        }
    }
    
    public String getDetails(){
        
        return "Message ID: " + messageID +"\nMessage Hash: "+ messageHash
                +"\nRecipient: "+ recipient +"\nMessage: "+ messageText;
    }
    
    public static String printMessages(){
        
        if(sentMessages.isEmpty()){
            return "No messages have been sent ";
        }
        
        StringBuilder sb = new StringBuilder();
        for(Messages m: sentMessages){
            sb.append(m.getDetails()).append("\n\n");
        }
        
        return sb.toString().trim();
    }
    
    public static int returnTotalMessages(){
        
        return numOfSentMessages;
    }
    
    public boolean storeMessage(){
        
        storedMessages.add(this);
        
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < storedMessages.size(); i++){
            
            Messages m = storedMessages.get(i);
            json.append(" {\n").append(" \"messageID\": \"")
                    .append(escape(m.messageID)).append("\",\n")
                   .append(" \"messageHash\": \"")
                   .append(escape(m.messageHash)).append("\",\n")
                   .append(" \"recipient\": \"")
                   .append(escape(m.recipient)).append("\",\n")
                   .append(" \"message\": \"")
                   .append(escape(m.messageText)).append("\",\n")
                   .append(" }")
                   .append(i < storedMessages.size()-1 ? "," : "").append("\n");
        }
        
        json.append("]");
        
        try (FileWriter writer = new FileWriter(jsonFile)){
            writer.write(json.toString());
            return true;
        }
        catch (IOException e){
            System.out.println("Could not write to "+ jsonFile + ":" + e.getMessage());
            return false;
        }
    }
    
    private static String escape(String text){
        
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
    
    
}
