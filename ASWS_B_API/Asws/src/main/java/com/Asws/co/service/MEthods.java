package com.Asws.co.service;

import javax.naming.spi.DirStateFactory.Result;

public class MEthods {


    public static String greets(String string){
        return "Good Morning   " + string;
    }


    public static double calculator(double d , double e ,String operator){
        double Result= 0;

        if(operator == "A"){

            Result = d + e;
        }
        else if(operator == "S"){
            Result = d - e;
        }

        else if(operator == "D"){
            Result = d/e;
        }

        else {
            System.out.println("operator not found");
        }
        return Result;
    }

   


    public static void main(String[] args) {
       System.out.println(calculator(23.4, 67.6,"D"));
    
    
    }








}
