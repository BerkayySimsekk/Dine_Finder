import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class LoginChoice implements Navigable {
    VBox root;

    public LoginChoice() {
        //the layout of the stage is created
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);

        //an image for the logo of the application
        Image image = new Image("Meal Reel Logo.png");
        ImageView view = new ImageView(image);
        view.setFitHeight(100);
        view.setPreserveRatio(true);

        //a button object that proceeds the user to the page where he can log in as a customer
        Button loginAsCustomer = createButton("Login as customer", 40, 800);

        //proceeding the user to the login as customer page
        loginAsCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginAsCustomer loginAsCustomer = new LoginAsCustomer();
                loginAsCustomer.navigate();        
            }   
        });

        //a button object that proceeds the user to the page where he can log in as a restaurant owner
        Button loginAsRestaurantOwner = createButton("Login as restaurant owner", 40, 800);

        //proceeding the user to the login as restaurant owner page
        loginAsRestaurantOwner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginAsRestaurantOwner loginAsRestaurantOwner = new LoginAsRestaurantOwner();
                loginAsRestaurantOwner.navigate();         
            }   
        });

        //a button object to proceed the user to the page where he can sign up
        Button haveNotSignedUpYet = createButton("You haven't signed up yet?", 30, 550);
        
        //proceeding the user to the sign up page
        haveNotSignedUpYet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                //signUpChoice.navigate();
            }
        });

        //a button object to exit the application
        Button exit = createButton("Exit", 30, 550);

        //exiting the application by closing the stage
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MealReelApplication.stage.close();   
            }
            
        });

        //empty labels to create gaps in desired locations
        Label empty1 = new Label();
        Label empty2 = new Label();
        Label empty3 = new Label();

        //Label object that creates the main title of this page
        Label title = new Label("Welcome to Meal Reel", view);
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 100));
        title.setTextFill(Color.WHITE);
        title.setGraphicTextGap(40);
        
        //adding the nodes to the root
        root.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
        root.getChildren().addAll(title, empty1, empty2, empty3, loginAsCustomer, loginAsRestaurantOwner, haveNotSignedUpYet, exit);
    }

    //a method to create a button object with given text as a String, text size as an integer, minimum width of the button as an integer
    public Button createButton(String text, int textSize, int minButtonWidth) {
        //creating the button with the desired traits
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, textSize));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));
        button.setMinWidth(minButtonWidth);

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
        MealReelApplication.stage.getScene().setRoot(root);
    }
}
