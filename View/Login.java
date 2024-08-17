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
    //nodes used for this class
    VBox root;
    Button login;
    Button haveNotSignedUpYet;
    TextField usernameOrEmail;
    TextField password;
    Label title;
    Label empty;

    public Login() {
        //the root of this class is initialized to add the nodes vertically
        root = new VBox(30);
        //the nodes found in the root are positioned at the center
        root.setAlignment(Pos.CENTER);
        //the background of the root is changed
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        //editable Text Field objects are created which allows the user to enter the username or email, and password
        usernameOrEmail = TextFieldCreater.createEditableTextField("Enter your username or email", false, false, true, 0, 0, 650, true);
        password = TextFieldCreater.createEditableTextField("Enter your password", false, false, true, 0, 0, 650, true);

        //the default behaviour of the editable Text Field object is modified a little so that if the user is not found, the button for login is changed back to its
        //original state if the mouse enters the allocated field for this object
        usernameOrEmail.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(login.getText().equals("User not found")) {
                    login.setText("Login");
                }

                if(usernameOrEmail.getText().equals("Enter your username or email")) {
                    usernameOrEmail.setText(""); 
                } 
            }
            
        });

        //the default behaviour of the editable Text Field object is modified a little so that if the user is not found, the button for login is changed back to its
        //original state if the mouse enters the allocated field for this object
        password.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(login.getText().equals("User not found")) {
                    login.setText("Login");
                }
                
                if(password.getText().equals("Enter your password")) {
                    password.setText(""); 
                } 
            }
            
        });

        //a Button object to let the user login after entering his username/email and password
        login = ButtonCreater.createButton("Login");

        //clicking the login button lets the user to navigate to the main screen unless the entered information is incorrect
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //a loop that iterates through the list that includes every registered user
                for(int n = 0; n < DineFinderApplication.listOfUsers.getUserList().size(); n++) {
                    if((DineFinderApplication.listOfUsers.getUserList().get(n).getUsername().equals(usernameOrEmail.getText()) && DineFinderApplication.listOfUsers.getUserList().get(n).getPassword().equals(password.getText())) || (DineFinderApplication.listOfUsers.getUserList().get(n).getEmail().equals(usernameOrEmail.getText()) && DineFinderApplication.listOfUsers.getUserList().get(n).getPassword().equals(password.getText()))) {
                        //sets the current user which is essential for displaying information correctly in the profile pages
                        DineFinderApplication.currentUser = DineFinderApplication.listOfUsers.getUserList().get(n);
                        //navigates the user to the main screen if an user with entered information is found
                        MainScreen mainScreen = new MainScreen();
                        mainScreen.navigate();
                    }
                }

                //lets the user know that an user with the entered information is not found
                login.setText("User not found");
                usernameOrEmail.setText("Enter your username or email");
                password.setText("Enter your password");
            }

        });

        //a button to allow the user to navigate to the sign up page if the user does not have an existing account
        haveNotSignedUpYet = ButtonCreater.createButton("You haven't signed up yet?");

        //clicking this button navigates the user to the page in which the user can choose what to sign up as
        haveNotSignedUpYet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                signUpChoice.navigate();
            }

        });

        //an empty Label object is created to the leave gaps between nodes
        empty = new Label();

        //a Label object is created to set the title of this page
        title = new Label("Welcome to Dine Finder!");
        //sets the style and size of the text
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //sets the color of the text
        title.setTextFill(Color.WHITE);
        
        root.getChildren().addAll(title, empty, usernameOrEmail, password, login, haveNotSignedUpYet);  
    }

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
