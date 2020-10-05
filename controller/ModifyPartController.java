package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * ModifyPartController controller class
 * @author Adam Mayer
 */

public class ModifyPartController implements Initializable {
        Stage stage;
        Parent scene;


        @FXML
        private TextField idTxt;

        @FXML
        private TextField nameTxt;

        @FXML
        private TextField invTxt;

        @FXML
        private TextField priceTxt;

        @FXML
        private TextField machineIdTxt;

        @FXML
        private Label machineIdLbl;

        @FXML
        private RadioButton inhouseRbtn;

        @FXML
        private RadioButton outSourcedRbtn;

        @FXML
        private TextField maxTxt;

        @FXML
        private TextField minTxt;

        @FXML
        private Button saveBtn;

        @FXML
        private Button cancelBtn;

        @FXML
        void onActionOutsourced(ActionEvent event) {
                machineIdLbl.setText("Company Name: ");
        }

        @FXML
        void onActionInHouse(ActionEvent event) {
                machineIdLbl.setText("Machine ID: ");
        }

        /**
         * Cancel Button loads Main Menu
         * @param event on-click cancel button
         * @throws IOException
         */
        @FXML
        void onActionDisplayMainMenu(ActionEvent event) throws IOException {

                Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave? All unsaved data " +
                        "will be lost.");

                Optional<ButtonType> result = warning.showAndWait();

                if(result.isPresent() && result.get() == ButtonType.OK) {

                        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();

                }

        }

        /**
         * Save Button saves info and returns to Main Menu
         * @param event on-click save button
         * @throws IOException
         */
        @FXML
        void onActionSavePart(ActionEvent event) throws IOException{
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
                        stock = Integer.parseInt(invTxt.getText());
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

                               MainMenuController.updatePart(new InHouse(id, name, price, stock, min, max, machineId));

                       } else {
                               inhouse = false;
                       }
                       //Button to see if outsourced
                       if(outSourcedRbtn.isSelected()) {
                               companyName = machineIdTxt.getText();

                               if(!companyName.matches("[a-zA-Z_]+")) {

                                       Inventory.companyNameError();
                                       return;

                               }
                               outsourced = true;

                               MainMenuController.updatePart(new Outsourced(id, name, price, stock, min, max, companyName));

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
         * Send selected part info to textFields
         * @param part
         * @throws IOException
         */
        public void sendPart(Part part) throws IOException {

                        idTxt.setText(String.valueOf(part.getId()));
                        nameTxt.setText(part.getName());
                        invTxt.setText(String.valueOf(part.getStock()));
                        priceTxt.setText(String.valueOf(part.getPrice()));
                        minTxt.setText(String.valueOf(part.getMin()));
                        maxTxt.setText(String.valueOf(part.getMax()));

                        if(part instanceof InHouse) {
                                //Convert from int to String
                                String machineId = String.valueOf(((InHouse) part).getMachineId()); //Casting

                                inhouseRbtn.setSelected(true);

                                machineIdTxt.setText(machineId);

                        }

                        if(part instanceof Outsourced) {

                                outSourcedRbtn.setSelected(true);
                                machineIdLbl.setText("Company Name");
                                //Casting
                                machineIdTxt.setText(((Outsourced) part).getCompanyName());
                        }



        }


        /**
         * Controller initializer
         * @param url
         * @param rb
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {


        }


}
