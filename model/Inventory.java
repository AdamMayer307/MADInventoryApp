package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Inventory.java Class
 *
 * @author Adam Mayer
 */

public class Inventory {


    //Parts List. put all parts inside observable list allParts
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Add part to allParts
     * @param newPart adds new part to all parts list
     */
    //add parts to the allParts list
    public static void addPart(Part newPart) { allParts.add(newPart); }

    /**
     * Observable list for all parts
     * @return returns all parts from list list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    //Product Lists.
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add product to allProducts
     * @param newProduct takes new product that was created by user
     */
    //add products to the allProducts list
    public static void addProduct(Product newProduct) { allProducts.add(newProduct); }

    /**
     * Observable list for all products
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    //filtered Parts list
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    /**
     * List for all filtered parts
     * @return returns all filtered parts from search
     */
    public static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }

    //filter Products list
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    /**
     * List for all filtered products
     * @return returns all filtered products from search
     */
    public static ObservableList<Product> getAllFilteredProducts() {
        return filteredProducts;
    }

    /**
     * Error popup when user enters invalid input into name field
     */
    //Alert name cannot contain integers
    public static void nameErrorScreen() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Name cannot contain integers.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into company name field
     */
    public static void companyNameError() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Company Name cannot contain integers.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into inv field
     */
    public static void invErrorScreen() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Inventory must be integers.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into price/cost field
     */
    public static void priceErrorScreen() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Price/Cost must be a double.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into Max field
     */
    public static void maxErrorScreen() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Max must be integers.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into Min field
     */
    public static void minErrorScreen() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Min must be integers.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into Machine ID field
     */
    public static void machineIdErrorScreen() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Machine ID must be integers.");
        alert.showAndWait();

    }

    /**
     * Error popup when user doesn't select from table
     */
    //Alert for no selection in table
    public static void selectFromTableError() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Please make a selection from the table.");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into inv and it is more/less than your max/min
     */
    //Alert input out of range
    public static void outOfRange() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Your inventory is more/less than your max/min");
        alert.showAndWait();

    }

    /**
     * Error popup when user enters invalid input into Max field and it is less than Min field
     */
    //Alert min less than max
    public static void minLessThanMax() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Your max needs to be more than min");
        alert.showAndWait();

    }

    /**
     * Error popup when product has associated parts tied to it
     */
    //Alert can't delete because of associated parts
    public static void hasAsscParts() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("This product has associated parts.");
        alert.showAndWait();

    }

    /**
     * Error popup when user leaves field blank
     */
    public static void blankTextFieldError() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("All fields must be filled out.");
        alert.showAndWait();

    }









}
