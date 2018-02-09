package price;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    JFileChooser fileChooser;
    FileNameExtensionFilter extensionFilter;
    FileReader fileReader;
    BufferedReader bufferedReader;
    private String finis;
    private String path;
    private String finisInRow;
    private String marketInRow;
    private ArrayList<Label> layoutMarketLabels;
    private ArrayList<String> marketShortcut;

    @FXML
    Label axLabel, bxLabel, chLabel, csLabel, dkLabel, dxLabel, edLabel, exLabel, fxLabel, gbLabel, grLabel,
            hxLabel, irLabel, ixLabel, nlLabel, nxLabel, plLabel, pxLabel, ruLabel, roLabel, sfLabel, sxLabel;
    @FXML
    Button findButton, pathButton, restartButton, exitButton;
    @FXML
    TextField finisTextField, pathTextField;

    /** Method allows to choose price file from user hard drive */
    public void chooseFile() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the newest price file");
        extensionFilter = new FileNameExtensionFilter("*.csv", "csv", "csv");
        fileChooser.setFileFilter(extensionFilter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            pathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    /** Method checks if finis/path textFields are empty */
    public void checkIfEmpty() throws IOException {
        if ((finisTextField.getText().equals("")) || (pathTextField.getText().equals(""))) {
            showAlert("Specify part number and path to price file first");
        } else
            readFile();
    }

    /** Method initialize process of searching through price file */
    public void readFile() throws IOException {
        createList();
        finis = finisTextField.getText().toString();
        path = pathTextField.getText().toString();
        String row = "";
        boolean isFinisFound = false;

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            while ((row = bufferedReader.readLine()) != null) {
                readFinisFromRow(row);
                if (finisInRow.equals(finis)) {
                    isFinisFound = true;
                    for (int i = 0; i < layoutMarketLabels.size(); i++) {
                        readMarketFromRow(row);
                        if (marketInRow.contains(marketShortcut.get(i))) {
                            layoutMarketLabels.get(i).setTextFill(javafx.scene.paint.Paint.valueOf(String.valueOf(Color.GREEN)));
                            layoutMarketLabels.remove(i);
                            marketShortcut.remove(i);
                        } else
                            layoutMarketLabels.get(i).setTextFill(javafx.scene.paint.Paint.valueOf(String.valueOf(Color.RED)));
                    }
                }
            }
            if (!isFinisFound) {
                showAlert("Part not found in price file");
            }
            bufferedReader.close();
        } catch (Exception e1) {
            showAlert("Invalid or missing file");
            e1.printStackTrace();
        }
    }

    /** Method searches through first seven characters in row (first seven characters always represents FINIS number) */
    public void readFinisFromRow(String row) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            stringBuilder.append(String.valueOf(row.charAt(i)));
        }
        finisInRow = stringBuilder.toString();
        stringBuilder.setLength(0);
    }

    /** Method searches through characters 9-11 in row (characters 9-11 always represent market shortcut) */
    public void readMarketFromRow(String row) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 8; i < 10; i++) {
            stringBuilder.append(String.valueOf(row.charAt(i)));
        }
        marketInRow = stringBuilder.toString();
        stringBuilder.setLength(0);
    }

    /** Method allows to show information alerts */
    public void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
        alert.setHeaderText(text);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    /** Method creates ArrayList of market labels (javafx elements) and ArrayList with markets shortcuts */
    private void createList() {
        layoutMarketLabels = new ArrayList<>();
        marketShortcut = new ArrayList<>();

        Label[] labels = {axLabel, bxLabel, chLabel, csLabel, dkLabel, dxLabel, edLabel, exLabel, fxLabel,
                gbLabel, grLabel, hxLabel, irLabel, ixLabel, nlLabel, nxLabel, plLabel, pxLabel,
                ruLabel, roLabel, sfLabel, sxLabel};
        String[] marketTab = {"AX", "BX", "CH", "CS", "DK", "DX", "ED", "EX", "FX", "GB", "GR", "HX", "IR", "IX",
                "NL", "NX", "PL", "PX", "RU", "RO", "SF", "SX"};

        for (Label l : labels)
            layoutMarketLabels.add(l);
        for (String s : marketTab)
            marketShortcut.add(s);
    }

    /** Method allows to restart app */
    public void restartApp() throws IOException {
        Stage stage = (Stage) restartButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("priceLayout.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Part Finder v.1.0 2017");
        primaryStage.setScene(new Scene(root, 800, 200));
        primaryStage.show();

    }

    /** Method allows to close app */
    public void closeApp() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}






