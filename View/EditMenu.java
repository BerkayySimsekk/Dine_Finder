import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class EditMenu implements Navigable {
    //nodes used for this class
    VBox root;
    ScrollPane subroot1;
    HBox subroot2;
    HBox subroot3;
    VBox subroot1OfSubroot1;
    Button create;
    Button delete;
    Button back;
    TextField name;
    TextField type;
    TextField price;
    TextField itemInfo;
    TextField titleForItems;
    Label title;
    Label empty;
    Label empty2;

    public EditMenu() {
        //keeping track of items as Text Field objects in order to display them in the Scroll Pane object
        ArrayList<TextField> items = new ArrayList<TextField>();

        //the User object is turned into a Restaurant Owner object in order to get the menu of the logged in user
        RestaurantOwner currentRestaurantOwner = (RestaurantOwner)DineFinderApplication.currentUser;

        //this root is to add the menu items
        subroot1OfSubroot1 = new VBox();
        subroot1OfSubroot1.setAlignment(Pos.CENTER);

        //this root which has the ability to scroll down is to see the menu items
        subroot1 = new ScrollPane(subroot1OfSubroot1);
        subroot1.setMaxHeight(330);
        subroot1.setVbarPolicy(ScrollBarPolicy.NEVER);

        //the items found in the first subroot is centered
        subroot1OfSubroot1.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot1.getViewportBounds().getWidth(), subroot1.viewportBoundsProperty()));

        //editable Text Field objects are created for the name, type and price of the item which allows the user to add items to the menu
        name = TextFieldCreater.createEditableTextField("Enter a name for the item", true, false, false, 460, 0, 0, true);
        type = TextFieldCreater.createEditableTextField("Enter a type for the item", true, false, false, 460, 0, 0, true);
        price = TextFieldCreater.createEditableTextField("Enter a price for the item", true, false, false, 460, 0, 0, true);

        //an Button object to create the item with the entered information
        create = ButtonCreater.createButton("Create");

        //clicking this button creates an item with the given information; if the item has been created successfully, it lets the user know
        create.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //item with the given information is instantiated
                    Item item = new Item(name.getText(), type.getText(), Double.valueOf(price.getText()));
                    currentRestaurantOwner.getMenu().addItemToMenu(item);

                    //an uneditable Text Field object is created to display the created Item object
                    itemInfo = TextFieldCreater.createUneditableTextField(item.toString(), false, 0);
                    itemInfo.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    items.add(itemInfo);
                    
                    //the created Text Field that has the information regarding the created item is added to the root that displays the items
                    subroot1OfSubroot1.getChildren().add(itemInfo);

                    //letting the user there were not any problems with creating the item
                    name.setText("Created successfully");
                    type.setText("Created successfully");
                    price.setText("Created successfully");

                    //after a while the fields for entering the name, type and price of the item are changed back
                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            name.setText("Enter a name for the item");
                            type.setText("Enter a type for the item");
                            price.setText("Enter a price for the item");
                        }

                    }, 1000);
                }
                //catches the exception which involves not entering a number to the field for price
                catch (NumberFormatException exception) {
                    name.setText("Enter a name for the item");
                    type.setText("Enter a type for the item");
                    price.setText("Enter a price for the item");
                }
            }  
        });

        //a Button object to delete the items found on the menu
        delete = ButtonCreater.createButton("Delete");

        //clicking this button will delete the item if it is found on the menu
        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //a boolean variable to check whether the given item was found
                    boolean isFound = false;

                    //an Item object with the given information is created to be compared with every items on the menu
                    Item item = new Item(name.getText(), type.getText(), Double.valueOf(price.getText()));
                    
                    //a loop that iterates through every item found on the menu
                    for(int n = 0; n < items.size(); n++) {
                        //if an item that matches with the given item is found the boolean variable is set accordingly and the item is deleted for the menu
                        if(item.toString().equals(items.get(n).getText())) {
                            isFound = true;

                            currentRestaurantOwner.getMenu().removeItemFromMenu(item);
                            subroot1OfSubroot1.getChildren().remove(items.get(n));
                            items.remove(n);
                            //deleting the item causes the indexes of the items on the menu to shift, in order to prevent skipping an item the value for getting the
                            //indexes of the items is decreased by one
                            n--;
                        }
                    }

                    //lets the user know that the item was deleted successfully
                    if(isFound) {
                        name.setText("Deleted successfully");
                        type.setText("Deleted successfully");
                        price.setText("Deleted successfully");

                        //after a certain amount of time passes; the fields for name, type and price are returned back to their original state
                        Timer myTimer = new Timer();
                        myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            name.setText("Enter a name for the item");
                            type.setText("Enter a type for the item");
                            price.setText("Enter a price for the item");
                        }

                        }, 1000);
                    }
                    //lets the user know that the given item is not found
                    else {
                        name.setText("Item not found");
                        type.setText("Item not found");
                        price.setText("Item not found");

                        //after a certain amount of time passes; the fields for the name, type and price are returned back to their original state
                        Timer myTimer = new Timer();
                        myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            name.setText("Enter a name for the item");
                            type.setText("Enter a type for the item");
                            price.setText("Enter a price for the item");
                        }

                        }, 1000);
                    }
                }
                //catches the exception which involves not entering a number to the field for price
                catch (NumberFormatException exception) {
                    name.setText("Enter a name for the item");
                    type.setText("Enter a type for the item");
                    price.setText("Enter a price for the item");
                }
            }
            
        });

        //a subroot to add the fields nor name, type and price for the item next to each other
        subroot2 = new HBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(name, type, price);

        //a subroot to add the create and delete buttons next to each other
        subroot3 = new HBox(10);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(create, delete);

        //a Button object to return to the page for restaurant owner profile
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        //clicking this button navigates the user to the restaurant owner profile
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RestaurantOwnerProfile restaurantOwnerProfile = new RestaurantOwnerProfile();
                restaurantOwnerProfile.navigate();
            }
            
        });

        //an uneditable Text Field object is created to create a title for the menu items
        titleForItems = TextFieldCreater.createUneditableTextField("Items", false, 0);
        //sets the size and style of the text
        titleForItems.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        //sets the background of the Text Field object
        titleForItems.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        //the title is added to the field where the menu items are displayed
        subroot1OfSubroot1.getChildren().add(titleForItems);

        //a loop that gets the currently found items on the menu and displays them
        for(int n = 0; n < currentRestaurantOwner.getMenu().getMenuAsArrayList().size(); n++) {
            //a uneditable Text Field object is created with the given Item object which has an overrode method to display it as a String object
            itemInfo = TextFieldCreater.createUneditableTextField(currentRestaurantOwner.getMenu().getMenuAsArrayList().get(n).toString(), false, 0);
            //sets the background of the Text Field object
            itemInfo.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
            items.add(itemInfo);

            subroot1OfSubroot1.getChildren().add(itemInfo);
        }

        //a Label object to set the title of this page
        title = new Label("Edit Menu");
        //the style and size of the text is determined
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //the color of the text is determined
        title.setTextFill(Color.WHITE);
        
        //empty Label objects to create gaps between nodes
        empty = new Label();
        empty2 = new Label();

        //the root is initiliazed to add the items vertically
        root = new VBox(30);
        //the background of the root is changed
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        //the nodes of this root are positioned at the center
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, empty, subroot1, empty2, subroot2, subroot3, back);
    }

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);   
    }
}
