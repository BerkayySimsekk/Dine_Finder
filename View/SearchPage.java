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
    //this static object is to keep track of the currently filtered restaurants to be displayed in this page
    public static RestaurantFilter restaurantFilter;
    //this static object is to keep track of the clicked restaurant page in order to show the accurate information in the Restaurant Page class
    public static RestaurantOwner clickedRestaurantPage;
    //this static object is to be able to modify the text of the text field for searching food from other classes
    public static TextField searchFood;

    //nodes used for this class
    VBox root;
    VBox subroot1;
    HBox subroot2;
    ScrollPane subroot3;
    HBox subroot1OfSubroot1;
    HBox subroot1OfSubroot2;
    VBox subroot1OfSubroot3;
    Button search;
    Button back;
    Button filterIcon;
    Label empty;
    Label empty2;
    Label empty3;
    Label empty4;
    ComboBox<String> filter;
    TextField searchResults;

    public SearchPage() {
        //this root is to add the restaurants as clickable Button objects
        subroot1OfSubroot3 = new VBox();
        subroot1OfSubroot3.setAlignment(Pos.CENTER);

        //this root which has the ability to scroll down is to see the restaurants 
        subroot3 = new ScrollPane(subroot1OfSubroot3);
        subroot3.setMaxHeight(470);
        subroot3.setVbarPolicy(ScrollBarPolicy.NEVER);
        subroot3.setHbarPolicy(ScrollBarPolicy.NEVER);
        
        //the restaurants found in the first subroot is centered
        subroot1OfSubroot3.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot3.getViewportBounds().getWidth(), subroot3.viewportBoundsProperty()));

        //an uneditable Text Field object is created to create a title for the restaurants
        searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
        //sets the background color
        searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        //sets the style and size of the text
        searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));

        subroot1OfSubroot3.getChildren().add(searchResults);

        //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
        for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
            //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a new Button object
            //needs to be created each time we return back to the beginning of this loop
            Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());
            subroot1OfSubroot3.getChildren().add(restaurantName);

            //clicking this Button object directs the user to the restaurant page
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

        //a Button object with the classic image for searching
        search = ButtonCreater.createButtonWithGivenImage(new Image("Images/SearchIcon.png"), 35, 35);

        //clicking this button filters the restaurants according to the input given in the text field for searching
        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                restaurantFilter.resetFilter();
                restaurantFilter.includesGivenItem(searchFood.getText());

                //if the size of the Array List found in the Restaurant Filter class is zero, it means that user might have entered an item type instead of an item name
                if(restaurantFilter.getFilteredRestaurants().size() == 0) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesGivenItemType(searchFood.getText());
                }

                //if the size of the Array List found in the Restaurant Filter class is still zero, it means one of the three possibilities: the user entered an input that is unrelated to the
                //purpose of the searching feature, the entered item name is not found in any of the restaurants or the entered item type is not available in any of the restaurants
                if(restaurantFilter.getFilteredRestaurants().size() == 0 && searchFood.getText().equals("What food would you like to search for?")) {
                    restaurantFilter.resetFilter();
                }

                //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                subroot1OfSubroot3.getChildren().clear();
                //an uneditable Text Field object is created to create a title for the restaurants
                searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                //sets the background color
                searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                //sets the style and size of the text
                searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                subroot1OfSubroot3.getChildren().add(searchResults);

                //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                    //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a new Button object
                    //needs to be created each time we return back to the beginning of this loop
                    Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                    //clicking this Button object directs the user to the restaurant page
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

        //an Combo Box object is created from which the user can filter restaurants in certain ways 
        filter = new ComboBox<String>();
        //setting a minimum height of the Combo Box object
        filter.setMinHeight(35);
        //setting the background color of the Combo Box object
        filter.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(0), new Insets(0))));

        //since the created Observable List here references the Observable List found in the Combo Box object, changing this list directly affects the list found in the Combo Box object
        ObservableList<String> filterList = filter.getItems();
        //adding the options for filtering indirectly to the Combo Box object
        filterList.add("Reset");
        filterList.add("Sort by alphabetical order");
        filterList.add("Sort by rating");
        filterList.add("Sort by popularity");
        filterList.add("Price range for items: 0-50");
        filterList.add("Price range for items: 51-100");
        filterList.add("Price range for items: 101-150");
        filterList.add("Price range for items: 151-200");
        filterList.add("Price range for items: 201 and above");

        //these options for filtering are added only if the logged in user is a customer
        if(DineFinderApplication.currentUser instanceof Customer) {
            filterList.add("Find restaurants within your city");
            filterList.add("Find restaurants within your district");
            filterList.add("Find restaurants within your street");
        }

        //once the items found in the Combo Box are clicked, the restaurants are filtered accordingly
        filter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //resets the restaurant filter which means that every restaurant registered to the application is displayed
                if(filter.valueProperty().get().equals("Reset")) {
                    restaurantFilter.resetFilter();

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the sorted version of the restaurants by alphabetical order
                if(filter.valueProperty().get().equals("Sort by alphabetical order")) {
                    restaurantFilter.sortByAlphabeticalOrderOfRestaurantName();

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the sorted version of the restaurants by rating
                if(filter.valueProperty().get().equals("Sort by rating")) {
                    restaurantFilter.sortByRating();

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the sorted version of the restaurants by number of ratings given
                if(filter.valueProperty().get().equals("Sort by popularity")) {
                    restaurantFilter.sortByPopularity();

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the restaurants that contain at least one item whose price is between 0 and 50
                if(filter.valueProperty().get().equals("Price range for items: 0-50")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(0, 50);

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the restaurants that contain at least one item whose price is between 51 and 100
                if(filter.valueProperty().get().equals("Price range for items: 51-100")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(51, 100);

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the restaurants that contain at least one item whose price is between 101 and 150
                if(filter.valueProperty().get().equals("Price range for items: 101-150")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(101, 150);

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the restaurants that contain at least one item whose price is between 151 and 200
                if(filter.valueProperty().get().equals("Price range for items: 151-200")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(151, 200);

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays the restaurants that contain at least one item whose price is above 200
                if(filter.valueProperty().get().equals("Price range for items: 201 and above")) {
                    restaurantFilter.resetFilter();
                    restaurantFilter.includesItemsBetweenSetPriceRange(201, Double.MAX_VALUE);

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays restaurants that is found in the same city with logged in customer
                if(filter.valueProperty().get().equals("Find restaurants within your city")) {
                    Customer currentCustomer = (Customer) DineFinderApplication.currentUser;

                    restaurantFilter.resetFilter();
                    restaurantFilter.isFoundInGivenCity(currentCustomer.getAddress().getCityName());

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays restaurants that is found in the same district with logged in customer
                if(filter.valueProperty().get().equals("Find restaurants within your district")) {
                    Customer currentCustomer = (Customer) DineFinderApplication.currentUser;

                    restaurantFilter.resetFilter();
                    restaurantFilter.isFoundInGivenDistrict(currentCustomer.getAddress().getDistrictName());

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

                //displays restaurants that is found in the same street with logged in customer
                if(filter.valueProperty().get().equals("Find restaurants within your street")) {
                    Customer currentCustomer = (Customer) DineFinderApplication.currentUser;

                    restaurantFilter.resetFilter();
                    restaurantFilter.isFoundInGivenStreet(currentCustomer.getAddress().getStreetName());

                    //the field that displays the restaurants is cleared so that the recently filtered restaurants can be added to this field
                    subroot1OfSubroot3.getChildren().clear();
                    //an uneditable Text Field object is created to create a title for the restaurants
                    searchResults = TextFieldCreater.createUneditableTextField("Search results", false, 0);
                    //sets the background color
                    searchResults.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    //sets the style and size of the text
                    searchResults.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
                    subroot1OfSubroot3.getChildren().add(searchResults);

                    //a loop to add every Restaurant Owner objects found in the Array List for filtered restaurants to the field that displays these restaurants as Button objects
                    for(int n = 0; n < restaurantFilter.getFilteredRestaurants().size(); n++) {
                        //a Button object is created that contains the restaurant name as its text, notice that this Button object is not declared at the top of this class since a
                        //new Button object needs to be created each time we return back to the beginning of this loop
                        Button restaurantName = ButtonCreater.createButtonForRestaurants(restaurantFilter.getFilteredRestaurants().get(n).getRestaurantName());

                        //clicking this Button object directs the user to the restaurant page
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

        //a Button object that navigates the user to the previous page
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 70, 70);

        //clickin this button directs the user to the main screen
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.navigate();
            }
            
        });

        //a Button object that does not have purposes that a Button object normally would, the only purpose of this object is to provide the image attached to it
        filterIcon = ButtonCreater.createButtonWithGivenImage(new Image("Images/Filter.png"), 30, 30);
        //setting the background color
        filterIcon.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY)));

        //these methods that are available in the Button Creater class are overridden so that this button does not do anything besides displaying the image
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

        //empty Label objects to leave gaps between nodes
        empty = new Label();
        empty2 = new Label();
        empty3 = new Label();
        empty4 = new Label();

        //the reason why there is a subroot for the first subroot is because it is convenient to leave gaps horizontally and vertically this way
        subroot1OfSubroot1 = new HBox(30);
        subroot1OfSubroot1.getChildren().addAll(empty, back);
        subroot1 = new VBox(10);
        //sets the positioning of the nodes found in this subroot to the top left corner of field allocated for this subroot
        subroot1.setAlignment(Pos.TOP_LEFT);
        subroot1.getChildren().addAll(empty2, subroot1OfSubroot1);

        //adds the icon for filter and filter itself next to each other
        subroot1OfSubroot2 = new HBox(0);
        subroot1OfSubroot2.setAlignment(Pos.CENTER);
        subroot1OfSubroot2.getChildren().addAll(filterIcon, filter);

        //adds the icon for filter, filter itself, field allocated for searching food and the search button next to each other
        subroot2 = new HBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(subroot1OfSubroot2, searchFood, search);

        //a main root to add all the nodes vertically
        root = new VBox(30);
        //positions the nodes at the top center of the page
        root.setAlignment(Pos.TOP_CENTER);
        //sets the background color
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(subroot1, empty3, subroot2, empty4, subroot3);
    }

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}