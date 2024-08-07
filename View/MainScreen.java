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

    VBox subRoot1;
    HBox subRoot1OfSubRoot1;

    VBox subRoot2;
    HBox subRoot1OfSubRoot2;
    HBox subRoot2OfSubRoot2;

    VBox subRoot3;
    HBox subRoot1OfSubRoot3;


    public MainScreen() {
        Button profile = createButtonWithGivenImage(new Image("ProfileIcon.png"), 70, 70);

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

        //TODO add functionality to these buttons

        Button search = createButtonWithGivenImage(new Image("SearchIcon.png"), 50, 50);

        TextField searchFood = createTextField("What food would you like to search for?");

        Button hamburger = createButtonWithGivenImage(new Image("Hamburger.png"), 50, 50);

        Button pizza = createButtonWithGivenImage(new Image("Pizza.png"), 50, 50);

        Button frenchFries = createButtonWithGivenImage(new Image("FrenchFries.png"), 50, 50);

        Button chicken = createButtonWithGivenImage(new Image("Chicken.png"), 50, 50);

        Button steak = createButtonWithGivenImage(new Image("Steak.png"), 50, 50);

        Button salad = createButtonWithGivenImage(new Image("Salad.png"), 50, 50);

        Button hotdog = createButtonWithGivenImage(new Image("Hotdog.png"), 50, 50);

        Button pasta = createButtonWithGivenImage(new Image("Pasta.png"), 50, 50);

        Button back = createButtonWithGivenImage(new Image("BackButton.png"), 70, 70);

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

        subRoot1OfSubRoot1 = new HBox(30);
        subRoot1OfSubRoot1.getChildren().addAll(profile, empty);

        subRoot1 = new VBox(10);
        subRoot1.getChildren().addAll(empty2, subRoot1OfSubRoot1);

        subRoot1OfSubRoot2 = new HBox(10);
        subRoot1OfSubRoot2.setAlignment(Pos.CENTER);
        subRoot1OfSubRoot2.getChildren().addAll(searchFood, search);

        subRoot2OfSubRoot2 = new HBox(10);
        subRoot2OfSubRoot2.setAlignment(Pos.CENTER);
        subRoot2OfSubRoot2.getChildren().addAll(hamburger, pizza, frenchFries, chicken, steak, salad, hotdog, pasta);

        subRoot2 = new VBox(10);
        subRoot2.setAlignment(Pos.CENTER);
        subRoot2.getChildren().addAll(title, empty3, empty4, subRoot1OfSubRoot2, subRoot2OfSubRoot2);

        subRoot1OfSubRoot3 = new HBox(30);
        subRoot1OfSubRoot3.getChildren().addAll(empty5, back);

        subRoot3 = new VBox(10);
        subRoot3.getChildren().addAll(empty6, subRoot1OfSubRoot3);

        root = new BorderPane(subRoot2, null, subRoot1, null, subRoot3);
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
    
    //a method to create a button with the given text
    public Button createButton(String text) {
        //a button object is created with the desired traits
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));

        //the color of the button's background is changed if the mouse enters the area that this button can be found at
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.VIOLET, new CornerRadii(30), new Insets(0))));
            }
            
        });

        //the color of the button's background is changed back to its previous color if the mouse exits the area that this button can be found at
        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        return button;
    }

    //a method to create a text field with the given text
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