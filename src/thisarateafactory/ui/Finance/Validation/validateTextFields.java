package thisarateafactory.ui.Finance.Validation;

import java.util.regex.Pattern;



public class validateTextFields {
    
    
    public static boolean validateNumbersOnly(String input){
        Pattern pattern = Pattern.compile("^[0-9]{1,}$");
        return pattern.matcher(input).matches();
    }
    
    public static boolean validateCurrency(String input){
        Pattern pattern = Pattern.compile("^[0-9]{1,}[\\\\.]{0,1}[0-9]{0,2}$");
        return pattern.matcher(input).matches();
    }
    

    
}

    

