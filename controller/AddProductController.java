package controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * AddProductController controller class
 * @author Adam Mayer
 */


public class AddProductController implements Initializable {
        Stage stage;
        Parent scene;
        Product product;

        @FXML
        private TextField idTxt;

        @FXML
        private TextField nameTxt;

        @FXML
        private TextField stockTxt;

        @FXML
        private TextField priceTxt;

        @FXML
        private TextField maxTxt;

        @FXML
        private TextField minTxt;

        @FXML
        private TextField searchPartsTxt;

        @FXML
        private Button topAddBtn;

        @FXML
        private TableView<Part> partsTableView;

        @FXML
        private TableColumn<Part, Integer> idTopCol;

        @FXML
        private TableColumn<Part, String> nameTopCol;

        @FXML
        private TableColumn<Part, Integer> invTopCol;

        @FXML
        private TableColumn<Part, Double> priceTopCol;

        @FXML
        private Button cancelBtn;

        @FXML
        private Button saveBtn;

        @FXML
        private Button removePartBtn;

        @FXML
        private TableView<Part> asscPartsTableView;

        @FXML
        private TableColumn<Part, Integer> idBtmCol;

        @FXML
        private TableColumn<Part, String> nameBtmCol;

        @FXML
        private TableColumn<Part, Integer> invBtmCol;

        @FXML
        private TableColumn<Part, Double> priceBtmCol;

        /**
         * Gather user input and filter from search menu
         * @param event on return in search bar
         */
        @FXML
        void onActionPartsSearch(ActionEvent event) {

                try {
                        String userInput = searchPartsTxt.getText().toLowerCase();

                        partFilter(userInput);

                        int partNum = Integer.parseInt(userInput);
                        lookupPart(partNum);

                }catch(NumberFormatException e) {

                }

        }

        /**
         * Add button to add associated parts for product
         * @param event on-click add button
         */
        @FXML
        void onActionAddPart(ActionEvent event) {


                Part partSelect = partsTableView.getSelectionModel().getSelectedItem();

                if(!(partSelect == null)) {

                        product.addAssociatedPart(partsTableView.getSelectionModel().getSelectedItem());

                }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("Please select a part.");
                        alert.showAndWait();
                }



        }

        /**
         * Cancel button returns to Main Menu without saving
         * @param event on-click cancel button
         * @throws IOException
         */

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
         * Delete button deletes associated parts for product
         * @param event on-click delete button
         */
        @FXML
        void onActionRemovePart(ActionEvent event) {

                Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?");

                Optional<ButtonType> result = warning.showAndWait();

                if(result.isPresent() && result.get() == ButtonType.OK) {
                        Part selectedItem;

                        selectedItem = asscPartsTableView.getSelectionModel().getSelectedItem();

                }

        }

        /**
         * Save button saves all changes made
         * @param event on-click save button
         * @throws IOException
         */
        @FXML
        void onActionSave(ActionEvent event) throws IOException {
                int id = Integer.parseInt(idTxt.getText());
                String name = nameTxt.getText();
                int stock = 0;
                double price = 0;
                int max = 0;
                int min = 0;


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

                       product.setId(id);
                       product.setName(name);
                       product.setStock(stock);
                       product.setPrice(price);
                       product.setMax(max);
                       product.setMin(min);

                       Inventory.addProduct(product);

                       stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                       scene = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                       stage.setScene(new Scene(scene));
                       stage.show();
                        }





        }

        /**
         * Generates Products IDs
         */
        private void generateProductId() {

                Object allParts[] = Inventory.getAllProducts().toArray();

                int length = allParts.length;

                int id = length + 1;

                idTxt.setText(String.valueOf(id));

        }

        /**
         * Part ID search/filter
         * @param partId takes in part ID from user input in search
         */
        //Part search by Part ID
        public void lookupPart(int partId) {

                //Check to see if list is not empty and clear filter
                if(!(Inventory.getAllFilteredParts().isEmpty())) {
                        Inventory.getAllFilteredParts().clear();
                }
                for(Part parts : Inventory.getAllParts()) {
                        if(parts.getId() == partId) {
                                Inventory.getAllFilteredParts().add(parts);
                                partsTableView.setItems(Inventory.getAllFilteredParts());

                        }
                }

        }

        /**
         * Part name search/filter
         * @param partName takes in part name from user input in search
         * @return either all parts list or all filtered parts list
         */
        //Filter Parts by name
        public ObservableList<Part> partFilter(String partName) {

                //Check to see if list is not empty and clear filter
                if(!(Inventory.getAllFilteredParts().isEmpty())) {
                        Inventory.getAllFilteredParts().clear();
                }
                for(Part parts : Inventory.getAllParts()) {
                        if(parts.getName().toLowerCase().contains(partName)) {
                                Inventory.getAllFilteredParts().add(parts);
                                partsTableView.setItems(Inventory.getAllFilteredParts());
                        }

                }
                if(Inventory.getAllFilteredParts().isEmpty()) {
                        return Inventory.getAllParts();
                } else {
                        return Inventory.getAllFilteredParts();
                }

        }


        /**
         * Controller Initializer loads tables and generates product ID
         * @param url
         * @param rb
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

                //Id generator
                generateProductId();

                partsTableView.setItems(Inventory.getAllParts());

                idTopCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameTopCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                invTopCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                priceTopCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                product = new Product(0, null, 0.0, 0, 0, 0);

                //Products List
                asscPartsTableView.setItems(product.getAllAssociatedParts());

                idBtmCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameBtmCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                invBtmCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                priceBtmCol.setCellValueFactory(new PropertyValueFactory<>("price"));







        }


}
