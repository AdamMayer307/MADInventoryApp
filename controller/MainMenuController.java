package controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
//correct import to link view with fxml page
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

//****** HONEY DO LIST ********



/**
 * MainMenuController controller class
 * @author Adam Mayer
 */

public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private Button mainMenuAddPartsBtn;

    @FXML
    private Button mainMenuDeletePartsBtn;

    @FXML
    private Button mainMenuModifyPartsBtn;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField searchPartsTxt;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private Button mainMenuAddProductBtn;

    @FXML
    private Button mainMenuDeleteProductBtn;

    @FXML
    private Button mainMenuModifyProductBtn;

    @FXML
    private Button mainMenuExitBtn;

    /**
     * Parts search bar excepts user input and calls filters
     * @param event on return search part name/ID
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
     * Product search bar excepts user input and calls filters
     * @param event on return search product name/ID
     */
    @FXML
    void onActionProductSearch(ActionEvent event) {

        try {
            String userInput = productSearchTxt.getText().toLowerCase();

            productFilter(userInput);

            int partNum = Integer.parseInt(userInput);
            lookupProduct(partNum);

        }catch(NumberFormatException e) {
        }

    }

    /**
     * Add part button loads add part screen
     * @param event on-click add button
     * @throws IOException
     */
    //add part menu
    @FXML
    void onActionAddPartMenu(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Modify Button loads modify part screen
     * @param event on-click parts modify button
     * @throws IOException
     */
    @FXML
    void onActionModifyPartMenu(ActionEvent event) throws IOException {

        try {
            //Populates the modify part menu from selected item in the Parts main menu. Allows different controllers to communicate
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ModifyPartMenu.fxml"));
            loader.load();

            ModifyPartController MPController = loader.getController();
            MPController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();


        }catch(NullPointerException e) {

            Inventory.selectFromTableError();

        }


    }

    /**
     * Delete part button deletes selected part
     * @param event on-click delete button
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {

        Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?");

        Optional<ButtonType> result = warning.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Part selectedItem;

            selectedItem = partsTableView.getSelectionModel().getSelectedItem();

            deletePart(selectedItem);


        }

    }

    /**
     * Add product button loads add product screen
     * @param event on-click product add button
     * @throws IOException
     */
    //Add product menu
    @FXML
    void onActionAddProductMenu(ActionEvent event) throws IOException {


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AddProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * Modify product button loads modify product screen
     * @param event on-click product modify button
     * @throws IOException
     */
    @FXML
    void onActionModifyProductMenu(ActionEvent event) throws IOException {

         try {
            //Populates the modify part menu from selected item in the Parts main menu. Allows different controllers to communicate
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ModifyProductMenu.fxml"));
            loader.load();

            ModifyProductController MPRController = loader.getController();
            MPRController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }catch(NullPointerException e) {

            Inventory.selectFromTableError();

        }

    }

    /**
     * Delete product button deletes product
     * @param event on-click delete product button
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?");

        Optional<ButtonType> result = warning.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Product selectedItem;

            selectedItem = productTableView.getSelectionModel().getSelectedItem();
            deleteProduct(selectedItem);

        }

    }

    /**
     * Exit button shuts down program and exits
     * @param event on-click exit button
     */
    @FXML
    void onActionExit(ActionEvent event) {

        Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");

        Optional<ButtonType> result = warning.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            System.exit(0);

        }



    }

    /**
     * Delete part button deletes parts
     * @param selectedPart user selected part from table
     * @return updated getAllParts list
     */
    //Delete part
    public boolean deletePart(Part selectedPart) {

        selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        productTableView.getItems().remove(selectedPart);
        return Inventory.getAllParts().remove(selectedPart);


    }

    /**
     * Delete product button deletes products
     * @param selectedProduct selectedProduct user selection from table
     * @return updated Product list
     */
    //Delete product
    public boolean deleteProduct(Product selectedProduct) {

        selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if(selectedProduct.getAllAssociatedParts().isEmpty()) {

            productTableView.getItems().remove(selectedProduct);
            return Inventory.getAllParts().remove(selectedProduct);

        } else {

            Inventory.hasAsscParts();

        }

        return false;

    }

    /**
     * Allows to modify part and keep same ID
     * @param part gets selected part to be modified
     */
    public static void updatePart(Part part) {

        int index = part.getId();

        index = index - 1;

        Inventory.getAllParts().set(index, part);

    }

    /**
     * Allows to modify part and keep same ID
     * @param product gets selected product to be modified
     */
    public static void updateProduct (Product product) {
        int index = product.getId();

        index = index - 1;

        Inventory.getAllProducts().set(index, product);
    }

    /**
     * Search by part ID
     * @param partId part ID input into search bar
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
     * Filter by part name
     * @param partName part name input into search bar
     * @return all parts that meet search criteria
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
     * Search by product ID
     * @param productId product ID user input from search field
     */
    //Product Search by ID
    public void lookupProduct(int productId) {

        //Check to see if list is not empty and clear filter
        if(!(Inventory.getAllFilteredProducts().isEmpty())) {
            Inventory.getAllFilteredProducts().clear();
        }
        for(Product products : Inventory.getAllProducts()) {
            if(products.getId() == productId) {
                Inventory.getAllFilteredProducts().add(products);
                productTableView.setItems(Inventory.getAllFilteredProducts());

            }
        }

    }

    /**
     * Search by product name
     * @param productName user input from search bar
     * @return returns all products from search results
     */
    //Filter Products by name
    public ObservableList<Product> productFilter(String productName) {

        //Check to see if list is not empty and clear filter
        if(!(Inventory.getAllFilteredProducts().isEmpty())) {
            Inventory.getAllFilteredProducts().clear();
        }
        for(Product products : Inventory.getAllProducts()) {
            if(products.getName().toLowerCase().contains(productName)) {
                Inventory.getAllFilteredProducts().add(products);
                productTableView.setItems(Inventory.getAllFilteredProducts());
            }

        }
        if(Inventory.getAllFilteredProducts().isEmpty()) {
            return Inventory.getAllProducts();
        } else {
            return Inventory.getAllFilteredProducts();
        }
    }


    /**
     * Initialize controller loads tables info
     * @param url
     * @param rb
     */
//Initialize method. Similar to main for controllers
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populate table with all parts.
        partsTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Products List
        productTableView.setItems(Inventory.getAllProducts());

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }



}



