package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.List;

public class Controller {

    JFileChooser fileChooser;
    FileReader fileReader;
    BufferedReader bufferedReader;
    private String finis;
    private String path;
    private ArrayList<Label> listLabel;
    private ArrayList<String> listString;
    @FXML
    private List<Label> markets;

    @FXML
    Label axLabel, bxLabel, chLabel, csLabel, dkLabel, dxLabel, edLabel, exLabel, fxLabel, gbLabel, grLabel,
            hxLabel, irLabel, ixLabel, nlLabel, nxLabel, plLabel, pxLabel, ruLabel, roLabel, sfLabel, sxLabel;
    @FXML
    Button findButton, pathButton, restartButton, exitButton;
    @FXML
    TextField finisTextField, pathTextField;

    public void chooseFile() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the newest price file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv", "csv");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            pathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    public void checkIfEmpty() throws IOException {
        if ((finisTextField.getText().equals("")) || (pathTextField.getText().equals(""))) {
            showAlert("Specify part number and path to price file first");
        } else
            readFile();
    }

    public void readFile() throws IOException {
        createList();
        finis = finisTextField.getText().toString();
        path = pathTextField.getText().toString();
        String row = "";
        String finisInRow;
        String marketInRow;
        int counter = 0;

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            while ((row = bufferedReader.readLine()) != null) {
                finisInRow = String.valueOf(row.charAt(0)) + String.valueOf(row.charAt(1)) + String.valueOf(row.charAt(2)) +
                        String.valueOf(row.charAt(3)) + String.valueOf(row.charAt(4)) + String.valueOf(row.charAt(5)) +
                        String.valueOf(row.charAt(6));
                if (finisInRow.equals(finis)) {
                    counter++;
                    for (int i = 0; i < listLabel.size(); i++) {
                        marketInRow = String.valueOf(row.charAt(8) + String.valueOf(row.charAt(9)));
                        if (marketInRow.contains(listString.get(i))) {
                            listLabel.get(i).setTextFill(javafx.scene.paint.Paint.valueOf(String.valueOf(Color.GREEN)));
                            listLabel.remove(i);
                            listString.remove(i);

                        } else
                            listLabel.get(i).setTextFill(javafx.scene.paint.Paint.valueOf(String.valueOf(Color.RED)));
                    }
                }
            }
            if (counter == 0) {
                showAlert("Part not found in price file");
            }
            bufferedReader.close();
        } catch (Exception e1) {
            showAlert("Invalid or missing file");

            e1.printStackTrace();
        }

    }

    public void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
        alert.setHeaderText(text);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    private void createList() {
        listLabel = new ArrayList<>();
        listString = new ArrayList<>();

        Label[] labels = {axLabel, bxLabel, chLabel, csLabel, dkLabel, dxLabel, edLabel, exLabel, fxLabel,
                gbLabel, grLabel, hxLabel, irLabel, ixLabel, nlLabel, nxLabel, plLabel, pxLabel,
                ruLabel, roLabel, sfLabel, sxLabel};
        String[] marketTab = {"AX", "BX", "CH", "CS", "DK", "DX", "ED", "EX", "FX", "GB", "GR", "HX", "IR", "IX",
                "NL", "NX", "PL", "PX", "RU", "RO", "SF", "SX"};

        for (Label l : labels)
            listLabel.add(l);
        for (String s : marketTab)
            listString.add(s);
    }

    public void restartApp() throws IOException {
        Stage stage = (Stage) restartButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Part Finder v.1.0 2017");
        primaryStage.setScene(new Scene(root, 800, 200));
        primaryStage.show();

    }

    public void closeApp() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}






