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
    //nodes used for this class
    private VBox root;
    private Button signUpAsCustomer;
    private Button signUpAsRestaurantOwner;
    private Button back;
    private Label title;
    private Label empty;

    public SignUpChoice() {
        //main root that adds the nodes vertically
        root = new VBox(30);
        //positions the nodes at the center
        root.setAlignment(Pos.CENTER);
        //sets the background color of the root
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        //a Button object with predetermined width that navigates the user to the page where the user can sign up as customer
        signUpAsCustomer = ButtonCreater.createButton("Sign up as customer");
        signUpAsCustomer.setMinWidth(500);

        //clicking this button navigates the user to the Sign Up As Customer class
        signUpAsCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpAsCustomer customerSignUp = new SignUpAsCustomer();
                customerSignUp.navigate();
            }
        });

        //a Button object with predetermined width that navigates the user to the page where the user can sign up as restaurant owner
        signUpAsRestaurantOwner = ButtonCreater.createButton("Sign up as restaurant owner");
        signUpAsRestaurantOwner.setMinWidth(500);

        //clicking this button navigates the user to the Sign Up As Restaurant Owner class
        signUpAsRestaurantOwner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpAsRestaurantOwner restaurantOwnerSignUp = new SignUpAsRestaurantOwner();
                restaurantOwnerSignUp.navigate();
            }
        });

        //a Button that lets the user go back to the previous page
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        //clicking this button directs the user to the page where the user can log in to the application
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Login login = new Login();
                login.navigate();
            }
            
        });

        //a Label object to set the title of the page
        title = new Label("Choose what to sign up as");
        //sets the style and size of the text
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //sets the color of the text
        title.setTextFill(Color.WHITE);

        //an empty Label object to leave gaps between nodes
        empty = new Label();
        
        //adds the nodes to the main root vertically
        root.getChildren().addAll(title, empty, signUpAsCustomer, signUpAsRestaurantOwner, back);  
    }

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
