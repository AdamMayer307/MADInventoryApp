package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * AddPartController controller class
 * @author Adam Mayer
 */

public class AddPartController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField stockTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private Label machinceIdLbl;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private RadioButton inhouseRbtn;

    @FXML
    private RadioButton outsourcedRbtn;

    @FXML
    private Button addPartSaveBtn;

    @FXML
    private Button addPartCancelBtn;

    /**
     * Change label to Company Name
     * @param event on-click select Outsourced radio button
     */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        machinceIdLbl.setText("Company Name: ");
    }

    /**
     * Change label to Machine ID
     * @param event on-click select In-House radio button
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        machinceIdLbl.setText("Machine ID: ");
    }

    /**
     * Cancel button returns to main menu
     * @param event on-click cancel button
     * @throws IOException
     */
    //Click cancel. Back to Main Menu with confirmation
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {

        Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave? All unsaved data " +
                "will be lost.");

        Optional<ButtonType> result = warning.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }



    }

    /**
     *  !!!! ISSUE I RESOLVED !!!!!
     * Issue I ran into was not being able to update the parts list. I was trying to pull the info through product.
     * It took me forever to figure out that since the Parts class was an abstract class I had to pull it through
     * InHouse I ended up building the Product table first to make sure my code was right. Once I verified my code
     * was indeed correct I was able to pull up my error codes that it was giving me and it kept recommending that
     * I didn't make the Part class abstract. I went back and read more on the abstract class and inheritance and
     * was able to finally resolve the issue.
     *
     * !!!!!!!! A FEATURE TO ADD FOR THE FUTURE !!!!!!
     * I would add a low limit warning for the inventory stock. If the inventory reaches a certain level a warning
     * would be thrown to tell the user to add more inventory.
     *
     * Save Button and return to Main Menu
     * @param event on-click save button
     * @throws IOException IOException is throw due to using .load
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            int stock = 0;
            double price = 0;
            int max = 0;
            int min = 0;
            int machineId = 0;
            String companyName;
            boolean inhouse;
            boolean outsourced;


            try {
                stock = Integer.parseInt(stockTxt.getText());
            } catch(NumberFormatException e) {
                Inventory.invErrorScreen();
                return;
            }

            try {
                price = Double.parseDouble(priceTxt.getText());
            } catch(NumberFormatException e) {
                Inventory.priceErrorScreen();
                return;
               }

            try {
                max = Integer.parseInt(maxTxt.getText());
            } catch(NumberFormatException e) {
                Inventory.maxErrorScreen();
                return;
            }

            try {
                min = Integer.parseInt(minTxt.getText());
            } catch(NumberFormatException e) {
                Inventory.minErrorScreen();
                return;
            }


            if(!name.matches("[a-zA-Z_]+")) {

                Inventory.nameErrorScreen();
                return;

            } else if(max < min) {

                Inventory.minLessThanMax();
                return;

            } else if(stock > max || stock < min) {

                Inventory.outOfRange();
                return;

            }else {
                //Button to see if in-house
                if(inhouseRbtn.isSelected()) {
                    try {
                        machineId = Integer.parseInt(machineIdTxt.getText());
                    }catch(NumberFormatException e) {
                        Inventory.machineIdErrorScreen();
                        return;
                    }
                    inhouse = true;
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

                } else  {
                    inhouse = false;
                }
                //Button to see if outsourced
                if(outsourcedRbtn.isSelected()) {
                    companyName = machineIdTxt.getText();

                    if(!companyName.matches("[a-zA-Z_]+")) {

                       Inventory.companyNameError();
                       return;

                    }
                    outsourced = true;
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));


                } else {
                    outsourced = false;
                }


                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }

    }

    /**
     * ID Generator
     * generates a custom ID for new part.
     */
    private void generatePartId() {

        Object allParts[] = Inventory.getAllParts().toArray();
        int length = allParts.length;

        int id = length + 1;

        idTxt.setText(String.valueOf(id));


    }

    /**
     * Controller initializer sets radio button to in-house on load and generates part ID
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //inhouse by default
        inhouseRbtn.setSelected(true);
        generatePartId();



    }


}
