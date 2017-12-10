package sample;

import javafx.fxml.FXML;

import java.awt.*;

/**
 * Created by Gregory on 2017-12-10.
 */
public class Error {

    @FXML
    Label errorLabel;
    @FXML
    Button errorButton;

    public void setError(String errorMessage){
        errorLabel.setText(errorMessage);
    }

    public void closeErrorScene(){

    }

}
