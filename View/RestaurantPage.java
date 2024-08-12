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
        subroot1OfSubroot1 = new VBox();
        subroot1OfSubroot1.setAlignment(Pos.CENTER);

        subroot1 = new ScrollPane(subroot1OfSubroot1);
        subroot1.setMaxHeight(330);
        subroot1.setVbarPolicy(ScrollBarPolicy.NEVER);

        subroot1OfSubroot1.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot1.getViewportBounds().getWidth(), subroot1.viewportBoundsProperty()));

        titleForItems = TextFieldCreater.createUneditableTextField("Menu", false, 0);
        titleForItems.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));
        titleForItems.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        subroot1OfSubroot1.getChildren().add(titleForItems);

        for(int n = 0; n < SearchPage.clickedRestaurantPage.getMenu().getMenuAsArrayList().size(); n++) {
            itemInfo = TextFieldCreater.createUneditableTextField(SearchPage.clickedRestaurantPage.getMenu().getMenuAsArrayList().get(n).toString(), false, 0);
            itemInfo.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));
            subroot1OfSubroot1.getChildren().add(itemInfo);
        }

        address = TextFieldCreater.createUneditableTextField(SearchPage.clickedRestaurantPage.getAddress().toString(), false, 0);
        address.setMinWidth(720);

        restaurantRating = TextFieldCreater.createUneditableTextField("Rating: " + SearchPage.clickedRestaurantPage.calculateRating(), false, 0);
        restaurantRating.setMinWidth(720);

        description = TextFieldCreater.createUneditableTextField(SearchPage.clickedRestaurantPage.getDescription(), false, 0);
        description.setMaxWidth(1450);

        givenRatingByCustomer = TextFieldCreater.createEditableTextField("Enter a rating between 0 and 5", false, false, true, 0, 0, 720, true);
        givenRatingByCustomer.setMinWidth(720);
        givenRatingByCustomer.setVisible(false);

        applyRating = ButtonCreater.createButton("Apply rating");
        applyRating.setVisible(false);

        applyRating.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if(Double.valueOf(givenRatingByCustomer.getText()) < 0 || Double.valueOf(givenRatingByCustomer.getText()) > 5) {
                        givenRatingByCustomer.setText("Enter a rating between 0 and 5");
                    }
                    else {
                        SearchPage.clickedRestaurantPage.getRatingGivers().add(DineFinderApplication.currentUser);
                        SearchPage.clickedRestaurantPage.getGivenRatings().add(Double.valueOf(givenRatingByCustomer.getText()));
    
                        restaurantRating.setText("Rating: " + SearchPage.clickedRestaurantPage.calculateRating());

                        givenRatingByCustomer.setText("Given rating: " + givenRatingByCustomer.getText());
                        givenRatingByCustomer.setEditable(false);
                        applyRating.setVisible(false);    
                    }
                }
                catch (NumberFormatException exception) {
                    givenRatingByCustomer.setText("Enter a rating between 0 and 5");
                }
            }
            
        });

        if(DineFinderApplication.currentUser instanceof Customer) {
            givenRatingByCustomer.setVisible(true);
            boolean ratingIsGiven = false;
            int customerIndexForRating = 0;

            for(int n = 0; n < SearchPage.clickedRestaurantPage.getRatingGivers().size(); n++) {
                if(SearchPage.clickedRestaurantPage.getRatingGivers().get(n).getUsername().equals(DineFinderApplication.currentUser.getUsername())) {
                    ratingIsGiven = true;
                    customerIndexForRating = n;
                }
            }

            if(ratingIsGiven) {
                givenRatingByCustomer.setText("Given rating: " + SearchPage.clickedRestaurantPage.getGivenRatings().get(customerIndexForRating));
                givenRatingByCustomer.setVisible(true);
                givenRatingByCustomer.setEditable(false);
            }
            else {
                applyRating.setVisible(true);
                givenRatingByCustomer.setVisible(true);
            }
        }

        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        title = new Label(SearchPage.clickedRestaurantPage.getRestaurantName());
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        empty = new Label();
        empty2 = new Label();
        empty3 = new Label();

        subroot1OfSubroot4 = new HBox(30);
        subroot1OfSubroot4.getChildren().addAll(empty, back);

        subroot4 = new VBox(10);
        subroot4.setAlignment(Pos.TOP_LEFT);
        subroot4.getChildren().addAll(empty2, subroot1OfSubroot4);

        subroot3 = new HBox(10);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(address, restaurantRating);

        root = new VBox(30);
        root.getChildren().addAll(subroot4, title, empty3, description, subroot3, subroot1, givenRatingByCustomer, applyRating);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        root.setAlignment(Pos.TOP_CENTER);
    }   

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }   
}