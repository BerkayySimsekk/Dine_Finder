import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SignUpChoice implements Navigable {
    VBox root;
    Button signUpAsCustomer;
    Button signUpAsRestaurantOwner;
    Button back;

    public SignUpChoice() {
        //the layout of the stage is created
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);;

        ImageView image = new ImageView(new Image("BackButton.png"));
        image.setFitHeight(30);
        image.setFitWidth(30);

        signUpAsCustomer = createButton("Sign up as customer");
        signUpAsCustomer.setMinWidth(500);

        signUpAsCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpAsCustomer customerSignUp = new SignUpAsCustomer();
                customerSignUp.navigate();
            }
        });

        signUpAsRestaurantOwner = createButton("Sign up as restaurant owner");
        signUpAsRestaurantOwner.setMinWidth(500);

        signUpAsRestaurantOwner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpAsRestaurantOwner restaurantOwnerSignUp = new SignUpAsRestaurantOwner();
                restaurantOwnerSignUp.navigate();
            }
        });

        back = createButton("");
        back.setGraphic(image);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Login login = new Login();
                login.navigate();
            }
            
        });

        Label title = new Label("Choose what to sign up as");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        Label empty = new Label();
        
        //the nodes are added to the root
        root.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
        root.getChildren().addAll(title, empty, signUpAsCustomer, signUpAsRestaurantOwner, back);  
    }

    //a method to create a button with the given text
    public Button createButton(String text) {
        //a button object is created with the desired traits
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));

        //the color of the button's background is changed if the mouse enters the area that this button can be found at
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.ORANGERED, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        //the color of the button's background is changed back to its previous color if the mouse exits the area that this button can be found at
        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        return button;
    }

    //this method is to set the root of the stage to the root created within this particular class
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
