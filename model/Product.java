package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Product.java Class
 *
 * @author Adam Mayer
 */

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor to build product
     * @param id the id of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock of the product
     * @param min the min of the product
     * @param max the max of the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //Parts List. put all Associated parts inside observable list allParts
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Add associated parts to the all associated parts list
     * @param newAssociatedPart part that was selected by user
     */
    //add Associated parts to the allAssociatedParts list
    public void addAssociatedPart(Part newAssociatedPart) {

        associatedParts.add(newAssociatedPart);

    }

    /**
     * Get all associated parts
     * @return returns all associated parts from list
     */
    //get all AssociatedParts
    public ObservableList<Part> getAllAssociatedParts() {

        return associatedParts;

    }

    /**
     * Removes parts from associated parts list
     * @param removeAssociatedPart remove user selected parts
     */
    public void deleteAssociatedPart(Part removeAssociatedPart) {

        associatedParts.remove(removeAssociatedPart);

    }


    /***
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /***
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /***
     *
     * @param price the price to set
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /***
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /***
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /***
     *
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /***
     *
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /***
     *
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /***
     *
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
