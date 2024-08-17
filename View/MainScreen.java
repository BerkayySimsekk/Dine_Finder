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
    //nodes used for this class
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
    Button fries;
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
        //Restaurant Filter object is instantiated in the constructor of this class so that every time we reach this page, the updated version the restaurants can be seen in the search page
        //and restaurant page
        SearchPage.restaurantFilter = new RestaurantFilter();
        //the Text Field for searching food in the page for searching is instantiated here for the convenience of changing the text of this object by clicking certain buttons on the main screen
        SearchPage.searchFood = TextFieldCreater.createEditableTextField("What food would you like to search for?", true, true, false, 710, 65, 0, false);

        //a Button object to navigate to the profile page
        profile = ButtonCreater.createButtonWithGivenImage(new Image("Images/ProfileIcon.png"), 70, 70);

        //depending on the type of user that is logged in, clicking this button navigates to the appropriate profile page
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

        //an editable Text Field that allows the user to search for the name or type of the items
        searchFood = TextFieldCreater.createEditableTextField("What food would you like to search for?", true, true, false, 710, 65, 0, false);

        //a Button object with the classic image for searching
        search = ButtonCreater.createButtonWithGivenImage(new Image("Images/SearchIcon.png"), 50, 50);

        //clicking this button updates the Text Field object in the Search Page class, filters the restaurants according to the input given
        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.searchFood.setText(searchFood.getText());
                SearchPage.restaurantFilter.includesGivenItem(searchFood.getText());

                //if the size of the Array List found in the Restaurant Filter class is zero, it means that user might have entered an item type instead of an item name
                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                    SearchPage.restaurantFilter.includesGivenItemType(searchFood.getText());
                }

                //if the size of the Array List found in the Restaurant Filter class is still zero, it means one of the three possibilities: the user entered an input that is unrelated to the
                //purpose of the searching feature, the entered item name is not found in any of the restaurants or the entered item type is not available in any of the restaurants
                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0 && searchFood.getText().equals("What food would you like to search for?")) {
                    //the filter shows every restaurant available which indicates that no restaurant was found with the given input
                    SearchPage.restaurantFilter.resetFilter();
                }

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //Button objects that changes the field for searching with the corresponding word
        burger = ButtonCreater.createButtonWithGivenImage(new Image("Images/Hamburger.png"), 50, 50);
        pizza = ButtonCreater.createButtonWithGivenImage(new Image("Images/Pizza.png"), 50, 50);
        fries = ButtonCreater.createButtonWithGivenImage(new Image("Images/FrenchFries.png"), 50, 50);
        chicken = ButtonCreater.createButtonWithGivenImage(new Image("Images/Chicken.png"), 50, 50);
        steak = ButtonCreater.createButtonWithGivenImage(new Image("Images/Steak.png"), 50, 50);
        salad = ButtonCreater.createButtonWithGivenImage(new Image("Images/Salad.png"), 50, 50);
        hotdog = ButtonCreater.createButtonWithGivenImage(new Image("Images/Hotdog.png"), 50, 50);
        pasta = ButtonCreater.createButtonWithGivenImage(new Image("Images/Pasta.png"), 50, 50);

        //clicking the button with the burger image changes the field for searching in the Search Page class with "burger" and then navigates to the Search Page class
        burger.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("burger");
                SearchPage.searchFood.setText("burger");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the pizza image changes the field for searching in the Search Page class with "pizza" and then navigates to the Search Page class
        pizza.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("pizza");
                SearchPage.searchFood.setText("pizza");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the fries image changes the field for searching in the Search Page class with "fries" and then navigates to the Search Page class
        fries.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("fries");
                SearchPage.searchFood.setText("fries");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the chicken image changes the field for searching in the Search Page class with "chicken" and then navigates to the Search Page class
        chicken.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("chicken");
                SearchPage.searchFood.setText("chicken");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the steak image changes the field for searching in the Search Page class with "steak" and then navigates to the Search Page class
        steak.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("steak");
                SearchPage.searchFood.setText("steak");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the salad image changes the field for searching in the Search Page class with "salad" and then navigates to the Search Page class
        salad.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("salad");
                SearchPage.searchFood.setText("salad");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the hotdog image changes the field for searching in the Search Page class with "hotdog" and then navigates to the Search Page class
        hotdog.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("hotdog");
                SearchPage.searchFood.setText("hotdog");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //clicking the button with the pasta image changes the field for searching in the Search Page class with "pasta" and then navigates to the Search Page class
        pasta.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SearchPage.restaurantFilter.includesGivenItem("pasta");
                SearchPage.searchFood.setText("pasta");

                SearchPage searchPage = new SearchPage();
                searchPage.navigate();
            }
            
        });

        //a Button object that allows the user to navigate to the previous page
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);
        
        //clicking this button navigates to the page for login and the logged in user is set to null that indicates there is no one logged in
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DineFinderApplication.currentUser = null;
                Login login = new Login();
                login.navigate();
            }
            
        });

        //a Label object to set the title of this class
        title = new Label("What would you like today?");
        //style and size of the text is changed
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //color of the text is changed
        title.setTextFill(Color.WHITE);

        //empty Label objects to leave gaps between nodes
        empty = new Label();
        empty2 = new Label();
        empty3 = new Label();
        empty4 = new Label();
        empty5 = new Label();
        empty6 = new Label();

        //the reason why there is a subroot for the first subroot is because it is convenient to leave gaps horizontally and vertically this way
        subroot1OfSubroot1 = new HBox(30);
        subroot1OfSubroot1.getChildren().addAll(profile, empty);
        subroot1 = new VBox(10);
        subroot1.getChildren().addAll(empty2, subroot1OfSubroot1);

        //the first subroot of the second subroot adds the text field for searching and the button for searching next to each other
        subroot1OfSubroot2 = new HBox(10);
        subroot1OfSubroot2.setAlignment(Pos.CENTER);
        subroot1OfSubroot2.getChildren().addAll(searchFood, search);

        //the second subroot of the second subroot adds the buttons with food images next to each other
        subroot2OfSubroot2 = new HBox(10);
        subroot2OfSubroot2.setAlignment(Pos.CENTER);
        subroot2OfSubroot2.getChildren().addAll(burger, pizza, fries, chicken, steak, salad, hotdog, pasta);

        //the second subroot adds the previous subroots vertically
        subroot2 = new VBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(title, empty3, empty4, subroot1OfSubroot2, subroot2OfSubroot2);

        //the reason why there is a subroot for the third subroot is because it is convenient to leave gaps horizontally and vertically this way
        subroot1OfSubroot3 = new HBox(30);
        subroot1OfSubroot3.getChildren().addAll(empty5, back);
        subroot3 = new VBox(10);
        subroot3.getChildren().addAll(empty6, subroot1OfSubroot3);

        //the main root that adds the subroots to the desired locations in the border pane layout
        root = new BorderPane(subroot2, null, subroot1, null, subroot3);
        //sets the background of the main root
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
    }
    
    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}