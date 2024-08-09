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
    String hiddenPassword;
    String visiblePassword;

    public Login() {
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        TextField usernameOrEmail = createTextField("Enter your username or email");
        TextField password = createTextField("Enter your password");

        login = createButton("Login");

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
                        mainScreen.navigate();
                    }
                }

                if(!isFound) {
                    login.setText("User not found");
                    usernameOrEmail.setText("Enter your username or email");
                    password.setText("Enter your password");
                }
            }

        });

        Button haveNotSignedUpYet = createButton("You haven't signed up yet?");

        haveNotSignedUpYet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                signUpChoice.navigate();
            }

        });

        Label empty = new Label();

        Label title = new Label("Welcome to Dine Finder!");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);
        
        root.getChildren().addAll(title, empty, usernameOrEmail, password, login, haveNotSignedUpYet);  
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


     public TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));
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

    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
