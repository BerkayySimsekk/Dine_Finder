import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    VBox subroot2;
    VBox subroot3;
    HBox subroot1OfSubroot1;
    HBox subroot1OfSubroot2;
    HBox subroot2OfSubroot2;
    HBox subroot1OfSubroot3;
    Button profile;
    Button search;
    Button burger;
    Button pizza;
    Button frenchFries;
    Button chicken;
    Button steak;
    Button salad;
    Button hotdog;
    Button pasta;
    Button back;
    TextField searchFood;
    Label title;
    Label empty;
    Label empty2;
    Label empty3;
    Label empty4;
    Label empty5;
    Label empty6;

    public MainScreen() {
        SearchPage.restaurantFilter = new RestaurantFilter();
        SearchPage.searchFood = TextFieldCreater.createEditableTextField("What food would you like to search for?", true, true, false, 710, 65, 0, false);

        profile = ButtonCreater.createButtonWithGivenImage(new Image("Images/ProfileIcon.png"), 70, 70);

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

        searchFood = TextFieldCreater.createEditableTextField("What food would you like to search for?", true, true, false, 710, 65, 0, false);

        search = ButtonCreater.createButtonWithGivenImage(new Image("Images/SearchIcon.png"), 50, 50);

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.searchFood.setText(searchFood.getText());
                SearchPage.restaurantFilter.includesGivenItem(searchFood.getText());

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                    SearchPage.restaurantFilter.includesGivenItemType(searchFood.getText());
                }

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0 && searchFood.getText().equals("What food would you like to search for?")) {
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        burger = ButtonCreater.createButtonWithGivenImage(new Image("Images/Hamburger.png"), 50, 50);

        burger.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("burger");
                SearchPage.searchFood.setText("burger");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        pizza = ButtonCreater.createButtonWithGivenImage(new Image("Images/Pizza.png"), 50, 50);

        pizza.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("pizza");
                SearchPage.searchFood.setText("pizza");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        frenchFries = ButtonCreater.createButtonWithGivenImage(new Image("Images/FrenchFries.png"), 50, 50);

        frenchFries.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("fries");
                SearchPage.searchFood.setText("fries");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        chicken = ButtonCreater.createButtonWithGivenImage(new Image("Images/Chicken.png"), 50, 50);

        chicken.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("chicken");
                SearchPage.searchFood.setText("chicken");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        steak = ButtonCreater.createButtonWithGivenImage(new Image("Images/Steak.png"), 50, 50);

        steak.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("steak");
                SearchPage.searchFood.setText("steak");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        salad = ButtonCreater.createButtonWithGivenImage(new Image("Images/Salad.png"), 50, 50);

        salad.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("salad");
                SearchPage.searchFood.setText("salad");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        hotdog = ButtonCreater.createButtonWithGivenImage(new Image("Images/Hotdog.png"), 50, 50);

        hotdog.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("hotdog");
                SearchPage.searchFood.setText("hotdog");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        pasta = ButtonCreater.createButtonWithGivenImage(new Image("Images/Pasta.png"), 50, 50);

        pasta.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("pasta");
                SearchPage.searchFood.setText("pasta");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);
        
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DineFinderApplication.currentUser = null;
                Login login = new Login();
                login.navigate();
            }
            
        });

        title = new Label("What would you like today?");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        empty = new Label();
        empty2 = new Label();
        empty3 = new Label();
        empty4 = new Label();
        empty5 = new Label();
        empty6 = new Label();

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

        subroot2 = new VBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(title, empty3, empty4, subroot1OfSubroot2, subroot2OfSubroot2);

        subroot1OfSubroot3 = new HBox(30);
        subroot1OfSubroot3.getChildren().addAll(empty5, back);

        subroot3 = new VBox(10);
        subroot3.getChildren().addAll(empty6, subroot1OfSubroot3);

        root = new BorderPane(subroot2, null, subroot1, null, subroot3);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}