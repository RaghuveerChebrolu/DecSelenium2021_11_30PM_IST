package com.java.controlStatements;

public class continueStatement {  
public static void main(String[] args) {  
    //for loop  
    for(int i=1;i<=10;i++){  
        if(i==5){  
            //using continue statement  
            continue;//it will skip the rest statement  
          //  System.out.println("hello");
        }  
        System.out.println(i);  
    }  
}  
} 