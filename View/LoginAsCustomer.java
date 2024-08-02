import java.util.ArrayList;

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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LoginAsCustomer implements Navigable{
    VBox root;
    Button login;
    ListOfUsers listOfUsers;
    ArrayList<Customer> listOfCustomers;
    static boolean loggedInAsCus = false;

    public LoginAsCustomer() {
        listOfCustomers = MealReelApplication.listOfUsers.getCustomersFromListOfUsers();

        //the layout of the stage is created
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);

        //an image for the logo of the application
        Image image = new Image("Meal Reel Logo.png");
        ImageView view = new ImageView(image);
        view.setFitHeight(100);
        view.setPreserveRatio(true);

        //Text Field objects
        TextField usernameOrEmail = createTextField("Enter your username");
        TextField password = createTextField("Enter your password");

        //a button object that proceed the user to main page
        login = createButton("Login");

        //after clicking the login button, the given information is checked if there is a match
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean isFound;

                isFound = false;

                for(int n = 0; n < listOfCustomers.size(); n++) {
                    if(listOfCustomers.get(n).getUsername().equals(usernameOrEmail.getText()) && listOfCustomers.get(n).getPassword().equals(password.getText())) {
                        isFound = true;
                        MealReelApplication.listOfUsers.getUserList().add(listOfCustomers.get(n));
                        loggedInAsCus = true;
                        MainScreen mainScreen = new MainScreen();
                        mainScreen.navigate();
                    }
                }

                if(!isFound) {
                    login.setText("User not found");
                    usernameOrEmail.setText("Enter your username");
                    password.setText("Enter your password");
                }
            }

        });

        //a button object to proceed the user to the sign up page
        Button haveNotSignedUpYet = createButton("You haven't signed up yet?");

        //proceeding the user to sign up page if this button is clicked
        haveNotSignedUpYet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                //signUpChoice.navigate();
            }

        });

        //a button object to exit the application
        Button exit = createButton("Exit");

        //if this button is clicked, the stage is closed to exit the application
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MealReelApplication.stage.close();   
            }
            
        });

        //a button object to return to the login choice page
        Button returnToLoginChoice = createButton("Return to login choice");

        //if this button is clicked the user proceeds to the login choice page
        returnToLoginChoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginChoice loginChoice = new LoginChoice();
                loginChoice.navigate();
            }   
        });

        //an empty label to create gaps
        Label empty = new Label();

        //Label object to create the main title of this page
        Label title = new Label("Customer Login", view);
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 100));
        title.setTextFill(Color.WHITE);
        title.setGraphicTextGap(40);
        
        //the nodes are added to the root
        root.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
        root.getChildren().addAll(title, empty, usernameOrEmail, password, login, haveNotSignedUpYet, returnToLoginChoice, exit);  
    }

    //a method to create a button with the given text
    public Button createButton(String text) {
        //a button object is created with the desired traits
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));
        button.setMinWidth(550);

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

    //a method to create a text field with the given text
    public TextField createTextField(String text) {
        //Text Field object is created with the desired traits
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        textField.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setMaxWidth(800);

        //the color of the button's background is changed if the mouse enters the area that this button can be found at
        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                login.setText("Login");

                if(textField.getText().equals(text)){
                    textField.setText("");   
                }
            }    
        });

        //the color of the button's background is changed back to its previous color if the mouse exits the area that this button can be found at
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

    //this method is to set the root of the stage to the root created within this particular class
    public void navigate() {
        MealReelApplication.stage.getScene().setRoot(root);
    }
}
