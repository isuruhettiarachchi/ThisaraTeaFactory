/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.validation;

import java.util.regex.Pattern;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class validateTextFields {
    public static boolean validateNIC(String input){
        Pattern pattern = Pattern.compile("^[0-9]{9}[V-v]$");
        return pattern.matcher(input).matches();
    }
    
    public static boolean validatePhone(String input){
        Pattern pattern = Pattern.compile("^[0]{1}[0-9]{9}$");
        return pattern.matcher(input).matches();
    }
    
    public static boolean validateName(String input){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        return pattern.matcher(input).matches();
    }
    
    public static boolean validateCurrency(String input){
        Pattern pattern = Pattern.compile("^[0-9]{1,}[\\.]{0,1}[0-9]{0,2}$");
        return pattern.matcher(input).matches();
    }
    
    public static boolean validateNumbersOnly(String input){
        Pattern pattern = Pattern.compile("^[0-9]{1,}$");
        return pattern.matcher(input).matches();
    }
    
    public static boolean validateTextOnly(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z]{1,}$");
        return pattern.matcher(input).matches();
    }
}
