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
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);;

        ImageView image = new ImageView(new Image("Images/BackButton.png"));
        image.setFitHeight(35);
        image.setFitWidth(35);

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
        
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        root.getChildren().addAll(title, empty, signUpAsCustomer, signUpAsRestaurantOwner, back);  
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

    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
