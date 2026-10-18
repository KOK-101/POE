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
    
}
