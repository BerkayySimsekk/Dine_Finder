import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SearchPage implements Navigable {
    public static RestaurantFilter restaurantFilter;
    public static RestaurantOwner clickedRestaurantPage;
    public static TextField searchFood;

    VBox root;
    VBox subroot1;
    HBox subroot2;
    ScrollPane subroot3;
    VBox subroot1OfSubroot3;
    HBox subroot1OfSubroot1;
    HBox subroot1OfSubroot2;

    public SearchPage() {
        subroot1OfSubroot3 = new VBox();
        subroot1OfSubroot3.setAlignment(Pos.CENTER);

        subroot3 = new ScrollPane(subroot1OfSubroot3);
        subroot3.setMaxHeight(600);
        subroot3.setVbarPolicy(ScrollBarPolicy.NEVER);
        subroot3.setHbarPolicy(ScrollBarPolicy.NEVER);

        subroot1OfSubroot3.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot3.getViewportBounds().getWidth(), subroot3.viewportBoundsProperty()));

        TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
        searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));

        subroot1OfSubroot3.getChildren().add(searchResults);

        for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
            Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());
            subroot1OfSubroot3.getChildren().add(restaurantName);

            restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                            clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                            RestaurantPage restaurantPage = new RestaurantPage();
                            restaurantPage.navigate();
                        }
                    }
                }

            });
        }

        Button search = ButtonCreater.createButtonWithGivenImage(new Image("Images/SearchIcon.png"), 35, 35);

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                restaurantFilter.resetFilter();
                restaurantFilter.includesGivenItem(searchFood.getText());

                if(SearchPage.restaurantFilter.getFilteredRestaurants().size() == 0) {
                    SearchPage.restaurantFilter.resetFilter();
                    SearchPage.restaurantFilter.includesGivenItemType(searchFood.getText());
                }

                subroot1OfSubroot3.getChildren().clear();
                TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                subroot1OfSubroot3.getChildren().add(searchResults);

                for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                    Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                    restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                    clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                    RestaurantPage restaurantPage = new RestaurantPage();
                                    restaurantPage.navigate();
                                }
                            }
                        }

                    });

                    subroot1OfSubroot3.getChildren().add(restaurantName);
                }
            }

        });

        ComboBox<String> filter = new ComboBox<String>();
        filter.setMinHeight(35);
        filter.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));

        ObservableList<String> filterList = filter.getItems();
        filterList.add("Reset");
        filterList.add("Sort by alphabetical order");
        filterList.add("Sort by rating");
        filterList.add("Sort by popularity");
        filterList.add("Price range for items: 0-50");
        filterList.add("Price range for items: 51-100");
        filterList.add("Price range for items: 101-150");
        filterList.add("Price range for items: 151-200");

        if(DineFinderApplication.currentUser instanceof Customer) {
            filterList.add("Find restaurants within your city");
            filterList.add("Find restaurants within your district");
            filterList.add("Find restaurants within your street");
        }

        filter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(filter.valueProperty().get().equals("Reset")) {
                    restaurantFilter.resetFilter();

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false , 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Sort by alphabetical order")) {
                    restaurantFilter.sortByAlphabeticalOrderOfRestaurantName();

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Sort by rating")) {
                    restaurantFilter.sortByRating();

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Sort by popularity")) {
                    restaurantFilter.sortByPopularity();

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Price range for items: 0-50")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(0, 50);

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Price range for items: 51-100")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(51, 100);

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Price range for items: 101-150")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(101, 150);

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Price range for items: 151-200")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(151, 200);

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Find restaurants within your city")) {
                    Customer currentCustomer = (Customer) DineFinderApplication.currentUser;

                    restaurantFilter.resetFilter();
                    restaurantFilter.isFoundInGivenCity(currentCustomer.getAddress().getCityName());

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Find restaurants within your district")) {
                    Customer currentCustomer = (Customer) DineFinderApplication.currentUser;

                    restaurantFilter.resetFilter();
                    restaurantFilter.isFoundInGivenDistrict(currentCustomer.getAddress().getDistrictName());

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }

                if(filter.valueProperty().get().equals("Find restaurants within your street")) {
                    Customer currentCustomer = (Customer) DineFinderApplication.currentUser;

                    restaurantFilter.resetFilter();
                    restaurantFilter.isFoundInGivenStreet(currentCustomer.getAddress().getStreetName());

                    subroot1OfSubroot3.getChildren().clear();
                    TextField searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        restaurantName.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                for(int n = 0 ; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                                    if(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName().equals(restaurantName.getText())) {
                                        clickedRestaurantPage = restaurantFilter.getFilteredRestaurants().get(n);

                                        RestaurantPage restaurantPage = new RestaurantPage();
                                        restaurantPage.navigate();
                                    }
                                }
                            }

                        });

                        subroot1OfSubroot3.getChildren().add(restaurantName);
                    }
                }
            }

        });

        Button back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.navigate();
            }
            
        });

        Button filterIcon = ButtonCreater.createButtonWithGivenImage(new Image("Images/Filter.png"), 30, 30);
        filterIcon.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY)));

        filterIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            }
            
        });

        filterIcon.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            }
            
        });

        Label empty = new Label();
        Label empty2 = new Label();
        Label empty3 = new Label();
        Label empty4 = new Label();

        subroot1OfSubroot1 = new HBox(30);
        subroot1OfSubroot1.getChildren().addAll(empty, back);

        subroot1 = new VBox(10);
        subroot1.setAlignment(Pos.TOP_LEFT);
        subroot1.getChildren().addAll(empty2, subroot1OfSubroot1);

        subroot1OfSubroot2 = new HBox(0);
        subroot1OfSubroot2.setAlignment(Pos.CENTER);
        subroot1OfSubroot2.getChildren().addAll(filterIcon, filter);

        subroot2 = new HBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(subroot1OfSubroot2, searchFood, search);

        root = new VBox(30);
        root.setAlignment(Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(subroot1, empty3, subroot2, empty4, subroot3);

    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}