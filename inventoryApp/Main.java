package inventoryApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


/**
 * Main.java
 *
 * @author Adam Mayer
 */

public class Main extends Application {

    @Override
    public void init(){
        System.out.println("Starting app!!!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        primaryStage.setTitle("Inventory App");
        primaryStage.setScene(new Scene(root, 754, 400));
        primaryStage.show();
    }

    /**
     * Add parts to table
     * @param args
     */
    public static void main(String[] args) {

        InHouse part1 = new InHouse(1,"Sage Rod", 10.99, 2, 1, 12, 1);
        InHouse part2 = new InHouse(2,"Orvis Rod", 15.99, 2, 1, 12, 2);
        InHouse part3 = new InHouse(3,"Simms Reel", 12.99, 2, 1, 12, 3);
        InHouse part4 = new InHouse(4,"Hackle", 1.99, 2, 1, 12, 4);
        InHouse part5 = new InHouse(5,"Hooks", 5.99, 2, 1, 12, 5);
        Outsourced part6 = new Outsourced(6, "Tenkara Rod", 20, 6, 1, 12, "Tenkara");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);

        Product product1 = new Product(1, "Breathable Waders", 20.99, 10, 1, 20);
        Product product2 = new Product(2, "Wading Boots", 13.99, 20, 1, 20);
        Product product3 = new Product(3, "Net", 13.99, 8, 1, 20);
        Product product4 = new Product(4, "Vest", 13.99, 4, 1, 20);
        Product product5 = new Product(5, "Hat", 13.99, 9, 1, 20);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);


        launch(args);
    }
}




/*
Issue I ran into was not being able to update the parts list. I was trying to pull the info through product.
It took me forever to figure out that since the Parts class was an abstract class I had to pull it through InHouse
I ended up building the Product table first to make sure my code was right. Once I verified my code was indeed correct
I was able to pull up my error codes that it was giving me and it kept recommending that I didn't make the Part class
abstract. I went back and read more on the abstract class and inheritance and was able to finally resolve the issue.

I had a text field labled as invTxt but my constructor was calling it stock. I changed the invTxt to stockTxt to match
my variable name change from inv to stock. When I did this I forgot that it didn't change it in my fxml file.

Product search txt. I had some weird things going on with my search feature. Product search wouldn't work unless I typed
somthing into the parts search. turned out it was because I never changed my wrapper for user input.
 */