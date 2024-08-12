import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    Label title;
    Label empty;

    public SignUpChoice() {
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);;

        signUpAsCustomer = ButtonCreater.createButton("Sign up as customer");
        signUpAsCustomer.setMinWidth(500);

        signUpAsCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpAsCustomer customerSignUp = new SignUpAsCustomer();
                customerSignUp.navigate();
            }
        });

        signUpAsRestaurantOwner = ButtonCreater.createButton("Sign up as restaurant owner");
        signUpAsRestaurantOwner.setMinWidth(500);

        signUpAsRestaurantOwner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpAsRestaurantOwner restaurantOwnerSignUp = new SignUpAsRestaurantOwner();
                restaurantOwnerSignUp.navigate();
            }
        });

        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Login login = new Login();
                login.navigate();
            }
            
        });

        title = new Label("Choose what to sign up as");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        empty = new Label();
        
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        root.getChildren().addAll(title, empty, signUpAsCustomer, signUpAsRestaurantOwner, back);  
    }

    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
