package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    JFileChooser fileChooser;
    FileReader fileReader;
    BufferedReader bufferedReader;
    private String finis;
    private String path;
    private ArrayList<Label> listLabel;
    private ArrayList<String> listString;

    @FXML
    Label axLabel, bxLabel, chLabel, csLabel, dkLabel, dxLabel, edLabel, exLabel, fxLabel, gbLabel, grLabel,
            hxLabel, irLabel, ixLabel, nlLabel, nxLabel, plLabel, pxLabel, ruLabel, roLabel, sfLabel, sxLabel;

    @FXML
    Button findButton, pathButton, exitButton;

    @FXML
    TextField finisTextField, pathTextField;
//TODO: Wymuszenie właściwego pliku
    public void chooseFile() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the newest price file");
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*", "csv"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            pathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
    //TODO: Okienko z błędem
    public void checkIfEmpty() throws IOException {
        if ((finisTextField.getText().equals("")) || (pathTextField.getText().equals(""))) {
            System.out.println("dupa");
        } else
            readFile();
    }

    public void readFile() throws IOException {
        createList();
        finis = finisTextField.getText().toString();
        path = pathTextField.getText().toString();
        String row = "";

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            while ((row = bufferedReader.readLine()) != null) {

                if (row.toUpperCase().contains(finis)) {
                    for (int i = 0; i < listLabel.size(); i++) {
                        if (row.contains(listString.get(i))) {
                            listLabel.get(i).setTextFill(javafx.scene.paint.Paint.valueOf(String.valueOf(Color.GREEN)));
                            listLabel.remove(i);
                            listString.remove(i);

                        } else
                            listLabel.get(i).setTextFill(javafx.scene.paint.Paint.valueOf(String.valueOf(Color.RED)));
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e1) {
            //TODO: Obsługa błędu
            System.out.println("oj");

            e1.printStackTrace();
        }

    }

    private void createList() {
        listLabel = new ArrayList<>();
        listLabel.add(axLabel);
        listLabel.add(bxLabel);
        listLabel.add(chLabel);
        listLabel.add(csLabel);
        listLabel.add(dkLabel);
        listLabel.add(dxLabel);
        listLabel.add(edLabel);
        listLabel.add(exLabel);
        listLabel.add(fxLabel);
        listLabel.add(gbLabel);
        listLabel.add(grLabel);
        listLabel.add(hxLabel);
        listLabel.add(irLabel);
        listLabel.add(ixLabel);
        listLabel.add(nlLabel);
        listLabel.add(nxLabel);
        listLabel.add(plLabel);
        listLabel.add(pxLabel);
        listLabel.add(ruLabel);
        listLabel.add(roLabel);
        listLabel.add(sfLabel);
        listLabel.add(sxLabel);

        listString = new ArrayList<>();
        listString.add("AX");
        listString.add("BX");
        listString.add("CH");
        listString.add("CS");
        listString.add("DK");
        listString.add("DX");
        listString.add("ED");
        listString.add("EX");
        listString.add("FX");
        listString.add("GB");
        listString.add("GR");
        listString.add("HX");
        listString.add("IR");
        listString.add("IX");
        listString.add("NL");
        listString.add("NX");
        listString.add("PL");
        listString.add("PX");
        listString.add("RU");
        listString.add("RO");
        listString.add("SF");
        listString.add("DX");
    }
//TODO: Zrobić uruchamianie metody
    public void testMethod() throws IOException {
        Stage stage =  new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("error.fxml"));
        stage.setScene(new Scene(root, 150, 150));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}






