import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Login implements Navigable{
    VBox root;
    Button login;

    public Login() {
        //the layout of the stage is created
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);

        //Text Field objects
        TextField usernameOrEmail = createTextField("Enter your username or email");
        TextField password = createTextField("Enter your password");

        //a button object that proceed the user to main page
        login = createButton("Login");

        //after clicking the login button, the given information is checked if there is a match
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean isFound;

                isFound = false;

                for(int n = 0; n < DineFinderApplication.listOfUsers.getUserList().size(); n++) {
                    if((DineFinderApplication.listOfUsers.getUserList().get(n).getUsername().equals(usernameOrEmail.getText()) && DineFinderApplication.listOfUsers.getUserList().get(n).getPassword().equals(password.getText())) || (DineFinderApplication.listOfUsers.getUserList().get(n).getEmail().equals(usernameOrEmail.getText()) && DineFinderApplication.listOfUsers.getUserList().get(n).getPassword().equals(password.getText()))) {
                        isFound = true;
                        DineFinderApplication.currentUser = DineFinderApplication.listOfUsers.getUserList().get(n);
                        MainScreen mainScreen = new MainScreen();
                        // change mainScreen.navigate();
                    }
                }

                if(!isFound) {
                    login.setText("User not found");
                    usernameOrEmail.setText("Enter your username or email");
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
                signUpChoice.navigate();
            }

        });

        //an empty label to create gaps
        Label empty = new Label();

        //Label object to create the main title of this page
        Label title = new Label("Welcome to Dine Finder!");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);
        
        //the nodes are added to the root
        root.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
        root.getChildren().addAll(title, empty, usernameOrEmail, password, login, haveNotSignedUpYet);  
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

    //a method to create a text field with the given text
    public TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setMaxWidth(650);

        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                login.setText("Login");

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

    //this method is to set the root of the stage to the root created within this particular class
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
