import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    Button haveNotSignedUpYet;
    TextField usernameOrEmail;
    TextField password;
    Label title;
    Label empty;

    public Login() {
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        usernameOrEmail = TextFieldCreater.createEditableTextField("Enter your username or email", false, false, true, 0, 0, 650, true);
        password = TextFieldCreater.createEditableTextField("Enter your password", false, false, true, 0, 0, 650, true);

        login = ButtonCreater.createButton("Login");

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

        haveNotSignedUpYet = ButtonCreater.createButton("You haven't signed up yet?");

        haveNotSignedUpYet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                signUpChoice.navigate();
            }

        });

        empty = new Label();

        title = new Label("Welcome to Dine Finder!");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);
        
        root.getChildren().addAll(title, empty, usernameOrEmail, password, login, haveNotSignedUpYet);  
    }

    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
