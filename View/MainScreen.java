import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MainScreen implements Navigable {
    BorderPane root;

    VBox subroot1;
    HBox subroot1OfSubroot1;

    VBox subRoot2;
    HBox subroot1OfSubroot2;
    HBox subroot2OfSubroot2;

    VBox subRoot3;
    HBox subroot1OfSubroot3;


    public MainScreen() {
        SearchPage.restaurantFilter = new RestaurantFilter();

        Button profile = createButtonWithGivenImage(new Image("Images/ProfileIcon.png"), 70, 70);

        profile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(DineFinderApplication.currentUser instanceof Customer) {
                    CustomerProfile customerProfile = new CustomerProfile();
                    customerProfile.navigate();
                }
                else {
                    RestaurantOwnerProfile restaurantOwnerProfile = new RestaurantOwnerProfile();
                    restaurantOwnerProfile.navigate();
                }
            }
            
        });

        TextField searchFood = createTextField("What food would you like to search for?");

        Button search = createButtonWithGivenImage(new Image("Images/SearchIcon.png"), 50, 50);

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem(searchFood.getText());

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                    SearchPage.restaurantFilter.includesGivenItemType(searchFood.getText());
                }

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button burger = createButtonWithGivenImage(new Image("Images/Hamburger.png"), 50, 50);

        burger.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("burger");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button pizza = createButtonWithGivenImage(new Image("Images/Pizza.png"), 50, 50);

        pizza.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("pizza");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button frenchFries = createButtonWithGivenImage(new Image("Images/FrenchFries.png"), 50, 50);

        frenchFries.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("fries");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button chicken = createButtonWithGivenImage(new Image("Images/Chicken.png"), 50, 50);

        chicken.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("chicken");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button steak = createButtonWithGivenImage(new Image("Images/Steak.png"), 50, 50);

        steak.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("steak");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button salad = createButtonWithGivenImage(new Image("Images/Salad.png"), 50, 50);

        salad.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("salad");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button hotdog = createButtonWithGivenImage(new Image("Images/Hotdog.png"), 50, 50);

        hotdog.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("hotdog");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button pasta = createButtonWithGivenImage(new Image("Images/Pasta.png"), 50, 50);

        pasta.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("pasta");

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        Button back = createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);
        
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DineFinderApplication.currentUser = null;
                Login login = new Login();
                login.navigate();
            }
            
        });

        Label title = new Label("What would you like today?");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        Label empty = new Label();
        Label empty2 = new Label();
        Label empty3 = new Label();
        Label empty4 = new Label();
        Label empty5 = new Label();
        Label empty6 = new Label();

        subroot1OfSubroot1 = new HBox(30);
        subroot1OfSubroot1.getChildren().addAll(profile, empty);

        subroot1 = new VBox(10);
        subroot1.getChildren().addAll(empty2, subroot1OfSubroot1);

        subroot1OfSubroot2 = new HBox(10);
        subroot1OfSubroot2.setAlignment(Pos.CENTER);
        subroot1OfSubroot2.getChildren().addAll(searchFood, search);

        subroot2OfSubroot2 = new HBox(10);
        subroot2OfSubroot2.setAlignment(Pos.CENTER);
        subroot2OfSubroot2.getChildren().addAll(burger, pizza, frenchFries, chicken, steak, salad, hotdog, pasta);

        subRoot2 = new VBox(10);
        subRoot2.setAlignment(Pos.CENTER);
        subRoot2.getChildren().addAll(title, empty3, empty4, subroot1OfSubroot2, subroot2OfSubroot2);

        subroot1OfSubroot3 = new HBox(30);
        subroot1OfSubroot3.getChildren().addAll(empty5, back);

        subRoot3 = new VBox(10);
        subRoot3.getChildren().addAll(empty6, subroot1OfSubroot3);

        root = new BorderPane(subRoot2, null, subroot1, null, subRoot3);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
    }

    public Button createButtonWithGivenImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        Button button = createButton("");
        button.setGraphic(imageView);

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

    public TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setMinWidth(710);
        textField.setMinHeight(65);

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

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}