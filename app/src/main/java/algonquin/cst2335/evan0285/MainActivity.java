package algonquin.cst2335.evan0285;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Chris Evans
 * @version 1.0
 * Class that checks password validity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * this holds the text at the centre of the screen
     */
    private TextView tv = null;
    /**
     * this holds the edit text
     */
    private EditText et = null;
    /**
     * this holds the button
     */
    private Button btn = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editTextText);
        Button btn = findViewById(R.id.button);


        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();
            if(checkPasswordComplexity( password )){
                tv.setText("Your password meets the requirements");
            }
            else{
                tv.setText("You shall not pass!");
            }


        });

    }
    /**
     *  This function checks password validity
     * @param pw the string object we are checking
     * @return Returns true if password matches validity requirements
     */
    boolean checkPasswordComplexity(String pw){
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;

        for(int i = 0; i < pw.length(); i++){
            char c = pw.charAt(i);

            if(Character.isUpperCase(c)){
                foundUpperCase = true;
            }
            else if(Character.isLowerCase(c)){
                foundLowerCase = true;
            }
            else if(Character.isDigit(c)){
                foundNumber = true;
            }
            else if(isSpecialCharacter(c)){
                foundSpecial = true;
            }
        }

        if(!foundUpperCase){
            Toast.makeText(this, "Password is missing an upper case letter", Toast.LENGTH_SHORT);

            return false;
        }
        else if(!foundLowerCase){
            Toast.makeText(this, "Password is missing a lower case letter", Toast.LENGTH_SHORT);

            return false;
        }
        else if (!foundNumber){
            Toast.makeText(this, "Password is missing a number", Toast.LENGTH_SHORT);

            return false;
        }
        else if (!foundSpecial){
            Toast.makeText(this, "Password is missing a special character", Toast.LENGTH_SHORT);

            return false;
        }
        else{
            return true;
        }



    }
    /**
     * method to check if char is special char
     * @param c
     * @return boolean value
     */
    boolean isSpecialCharacter(char c){
        switch (c){
            case '#':
            case '?':
            case '*':
            case '$':
            case '%':
            case '^':
            case '!':
            case '@':
                return true;
            default:
                return false;
        }
    }
}