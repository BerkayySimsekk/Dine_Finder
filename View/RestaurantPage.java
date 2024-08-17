import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class RestaurantPage implements Navigable {
    //nodes used for this class
    VBox root;
    ScrollPane subroot1;
    HBox subroot2;
    HBox subroot3;
    VBox subroot4;
    VBox subroot1OfSubroot1;
    HBox subroot1OfSubroot4;
    TextField titleForItems;
    TextField itemInfo;
    TextField address;
    TextField restaurantRating;
    TextField description;
    TextField givenRatingByCustomer;
    Button applyRating;
    Button back;
    Label title;
    Label empty;
    Label empty2;
    Label empty3;

    public RestaurantPage() {
        //to add the restaurants vertically
        subroot1OfSubroot1 = new VBox();
        subroot1OfSubroot1.setAlignment(Pos.CENTER);

        //to add the added restaurants to a Scroll Pane layout in order to be able to scroll down
        subroot1 = new ScrollPane(subroot1OfSubroot1);
        subroot1.setMaxHeight(340);
        subroot1.setVbarPolicy(ScrollBarPolicy.NEVER);

        //positions the added restaurants at the center of the space that uses the Scroll Pane layout
        subroot1OfSubroot1.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot1.getViewportBounds().getWidth(), subroot1.viewportBoundsProperty()));

        //an uneditable Text Field object to create a title for the menu items
        titleForItems = TextFieldCreater.createUneditableTextField("Menu", false, 0);
        //sets the background of the Text Field object
        titleForItems.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));
        //sets the style and the size of the text
        titleForItems.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        subroot1OfSubroot1.getChildren().add(titleForItems);

        //a loop to add the items found at the menu to the subroot of the first subroot
        for(int n = 0; n < SearchPage.clickedRestaurantPage.getMenu().getMenuAsArrayList().size(); n++) {
            itemInfo = TextFieldCreater.createUneditableTextField(SearchPage.clickedRestaurantPage.getMenu().getMenuAsArrayList().get(n).toString(), false, 0);
            itemInfo.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));
            subroot1OfSubroot1.getChildren().add(itemInfo);
        }

        //an uneditable Text Field object to display the address of the restaurant
        address = TextFieldCreater.createUneditableTextField(SearchPage.clickedRestaurantPage.getAddress().toString(), true, 720);

        //an uneditable Text Field object to display the rating of the restaurant
        restaurantRating = TextFieldCreater.createUneditableTextField("Rating: " + SearchPage.clickedRestaurantPage.calculateRating(), true, 720);

        //an uneditable Text Field object to display the description of the restaurant
        description = TextFieldCreater.createUneditableTextField(SearchPage.clickedRestaurantPage.getDescription(), false, 0);
        description.setMaxWidth(1450);

        //an editable Text Field that only should be visible if the logged in user is a customer, it lets the customer rate the restaurant
        givenRatingByCustomer = TextFieldCreater.createEditableTextField("Enter a rating between 0 and 5", true, false, true, 720, 0, 720, true);
        givenRatingByCustomer.setVisible(false);

        //a Button to apply the given rating
        applyRating = ButtonCreater.createButton("Apply rating");
        applyRating.setVisible(false);

        //clicking this button will apply rating unless the given rating is not a number or it is not between 0 and 5
        applyRating.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //forces the user to enter a rating between 0 and 5
                    if(Double.valueOf(givenRatingByCustomer.getText()) < 0 || Double.valueOf(givenRatingByCustomer.getText()) > 5) {
                        givenRatingByCustomer.setText("Enter a rating between 0 and 5");
                    }
                    //if the given rating is valid, the apply button disappears and the given rating can be seen
                    else {
                        SearchPage.clickedRestaurantPage.getRatingGivers().add(DineFinderApplication.currentUser);
                        SearchPage.clickedRestaurantPage.getGivenRatings().add(Double.valueOf(givenRatingByCustomer.getText()));
    
                        restaurantRating.setText("Rating: " + SearchPage.clickedRestaurantPage.calculateRating());

                        givenRatingByCustomer.setText("Given rating: " + givenRatingByCustomer.getText());
                        givenRatingByCustomer.setEditable(false);
                        applyRating.setVisible(false);    
                    }
                }
                //avoids getting an error from an input that is not a number
                catch (NumberFormatException exception) {
                    givenRatingByCustomer.setText("Enter a rating between 0 and 5");
                }
            }
            
        });

        //checking whether the logged in user is a customer
        if(DineFinderApplication.currentUser instanceof Customer) {
            //the option to give a rating should be available to a customer
            givenRatingByCustomer.setVisible(true);
            //customer should only be able to give only one rating per restaurant
            boolean ratingIsGiven = false;
            int customerIndexForRating = 0;

            //a loop to check whether the customer already gave a rating for the restaurant
            for(int n = 0; n < SearchPage.clickedRestaurantPage.getRatingGivers().size(); n++) {
                if(SearchPage.clickedRestaurantPage.getRatingGivers().get(n).getUsername().equals(DineFinderApplication.currentUser.getUsername())) {
                    ratingIsGiven = true;
                    customerIndexForRating = n;
                }
            }

            //if the rating is already given, the given rating can be seen and the apply button is not visible
            if(ratingIsGiven) {
                givenRatingByCustomer.setText("Given rating: " + SearchPage.clickedRestaurantPage.getGivenRatings().get(customerIndexForRating));
                givenRatingByCustomer.setVisible(true);
                givenRatingByCustomer.setEditable(false);
            }
            //if the rating is not given already, the option to give a rating and the apply button for it can be seen
            else {
                applyRating.setVisible(true);
                givenRatingByCustomer.setVisible(true);
            }
        }

        //a Button object that allows the user to return back to the previous page
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);

        //clicking this button will direct the user to the Search Page class
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //a Label object to determine the title of this page
        title = new Label(SearchPage.clickedRestaurantPage.getRestaurantName());
        //setting the style and size of the text
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //setting the color of the text
        title.setTextFill(Color.WHITE);

        //empty Label objects to leave gaps between the nodes
        empty = new Label();
        empty2 = new Label();
        empty3 = new Label();

        //the reason why there is a subroot for the fourth subroot is because it is convenient to leave gaps horizontally and vertically this way
        subroot1OfSubroot4 = new HBox(30);
        subroot1OfSubroot4.getChildren().addAll(empty, back);
        subroot4 = new VBox(10);
        //positions the nodes found in the fourth subroot to the top left corner of the space allocated for fourth subroot
        subroot4.setAlignment(Pos.TOP_LEFT);
        subroot4.getChildren().addAll(empty2, subroot1OfSubroot4);

        //a subroot to add the uneditable fields for displaying address and restaurant rating next to each other
        subroot3 = new HBox(10);
        //positions the nodes at the center
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(address, restaurantRating);

        //a main root to add all of the nodes vertically
        root = new VBox(30);
        root.getChildren().addAll(subroot4, title, description, subroot3, subroot1, givenRatingByCustomer, applyRating, empty3);
        //sets the background of the main root
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        //sets the positioning of the main root to the top center
        root.setAlignment(Pos.TOP_CENTER);
    }   

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }   
}