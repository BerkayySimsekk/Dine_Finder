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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    VBox subroot1OfSubroot1;
    ScrollPane subroot1;
    HBox subroot2;
    HBox subroot3;
    VBox subroot4;
    HBox subroot1OfSubroot4;


    public RestaurantPage() {
        subroot1OfSubroot1 = new VBox();
        subroot1OfSubroot1.setAlignment(Pos.CENTER);

        subroot1 = new ScrollPane(subroot1OfSubroot1);
        subroot1.setMaxHeight(330);
        subroot1.setVbarPolicy(ScrollBarPolicy.NEVER);

        subroot1OfSubroot1.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot1.getViewportBounds().getWidth(), subroot1.viewportBoundsProperty()));

        TextField titleForItems = createUneditableTextField("Menu");
        titleForItems.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));
        titleForItems.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        subroot1OfSubroot1.getChildren().add(titleForItems);

        for(int n = 0; n < SearchPage.clickedRestaurantPage.getMenu().getMenuAsArrayList().size(); n++) {
            TextField itemInfo = createUneditableTextField(SearchPage.clickedRestaurantPage.getMenu().getMenuAsArrayList().get(n).toString());
            itemInfo.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));
            subroot1OfSubroot1.getChildren().add(itemInfo);
        }

        TextField address = createUneditableTextField(SearchPage.clickedRestaurantPage.getAddress().toString());
        address.setMinWidth(720);

        TextField restaurantRating = createUneditableTextField("Rating: " + SearchPage.clickedRestaurantPage.calculateRating());
        restaurantRating.setMinWidth(720);

        TextField description = createUneditableTextField(SearchPage.clickedRestaurantPage.getDescription());
        description.setMaxWidth(1450);

        TextField givenRatingByCustomer = createEditableTextField("Enter a rating between 0 and 5");
        givenRatingByCustomer.setMinWidth(720);
        givenRatingByCustomer.setVisible(false);

        Button applyRating = createButton("Apply rating");
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

        Button back = createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Label title = new Label(SearchPage.clickedRestaurantPage.getRestaurantName());
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        Label empty = new Label();
        Label empty2 = new Label();
        Label empty3 = new Label();

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

    public TextField createEditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setMaxWidth(720);

        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(textField.getText().equals(text)){
                    textField.setText("");   
                }
            }    
        });

        textField.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(textField.getText().equals("")) {
                    textField.setText(text); 
                } 
            }
            
        });

        return textField;
    }

    public TextField createUneditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setEditable(false);

        return textField;
    }

    public Button createButtonForStarsWithGivenImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        Button button = createButtonForStars("");
        button.setGraphic(imageView);

        return button;
    }

    public Button createButtonForStars(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(30), new Insets(0))));

        return button;
    }

    public Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.VIOLET, new CornerRadii(30), new Insets(0))));
            }
            
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        return button;
    }

    public Button createButtonWithGivenImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        Button button = createButton("");
        button.setGraphic(imageView);

        return button;
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }   
}