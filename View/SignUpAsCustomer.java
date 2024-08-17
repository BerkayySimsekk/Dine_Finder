import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SignUpAsCustomer implements Navigable {
    //nodes used for this class
    VBox root;
    HBox subroot1;
    HBox subroot2;
    HBox subroot3;
    TextField username;
    TextField email;
    TextField password;
    TextField passwordAgain;
    TextField city;
    TextField district;
    TextField street;
    Button signUp;
    Button back;
    Label signUpAsCustomer;
    Label empty;

    public SignUpAsCustomer() {
        //main root that adds the nodes vertically
        root = new VBox(30);
        //positions the nodes at the center
        root.setAlignment(Pos.CENTER);
        //sets the background of the root
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        
        //editable Text Field objects to let the user enter the username, email, password and address
        username = TextFieldCreater.createEditableTextField("Enter your username", true, false, false, 710, 0, 0, true);
        email = TextFieldCreater.createEditableTextField("Enter your email", true, false, false, 710, 0, 0, true);
        password = TextFieldCreater.createEditableTextField("Enter your password", true, false, false, 710, 0, 0, true);
        passwordAgain = TextFieldCreater.createEditableTextField("Enter your password again", true, false, false, 710, 0, 0, true);
        city = TextFieldCreater.createEditableTextField("Enter the city of your address", true, false, false, 710, 0, 0, true);
        district = TextFieldCreater.createEditableTextField("Enter the district of your address", true, false, false, 710, 0, 0, true);
        street = TextFieldCreater.createEditableTextField("Enter the street of your address", false, false, true, 0, 0, 1450, true);

        //a Button object to create the user with the given information 
        signUp = ButtonCreater.createButton("Sign up");

        //clicking this button will sign up the currrent user if the given username or email is not already taken and the passwords match
        signUp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //boolean variables to check whether the
                boolean usernameAlreadyExists = false;
                boolean emailAlreadyExists = false;

                //a loop to check whether the entered username or email is already taken
                for(User user : DineFinderApplication.listOfUsers.getUserList()) {
                    if(user.getUsername().equals(username.getText())) {
                        usernameAlreadyExists = true;  
                    }

                    if(user.getEmail().equals(email.getText())) {
                        emailAlreadyExists = true;  
                    }
                }

                //if the passwords do not match, the passwords should be entered again
                if(!password.getText().equals(passwordAgain.getText())) {
                    signUp.setText("Passwords do not match");
                    password.setText("Enter your password");
                    passwordAgain.setText("Enter your password again");
                }
                //if the username already exists, a different username should be picked
                else if(usernameAlreadyExists) {
                    signUp.setText("Username is already taken");
                    username.setText("Enter your username");
                }
                //if the email already exists, a different email should be picked
                else if(emailAlreadyExists) {
                    signUp.setText("Email is already taken");
                    email.setText("Enter your email");
                }
                //if everything is smooth, a Customer object with the given information is created and then the user is navigated back to the page for login
                else {
                    Address address = new Address(street.getText(), district.getText(), city.getText());
                    Customer customer = new Customer(password.getText(), email.getText(), username.getText(), address);
                    DineFinderApplication.listOfUsers.getUserList().add(customer);
                    Login login = new Login();
                    login.navigate();
                }
            }

        });

        //a Button that lets the user go back to the previous page
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        //clicking this button directs the user to the page where the user can choose what to sign up as
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                signUpChoice.navigate();
            }
            
        });

        //an overridden method found in the Text Field Creater class that is designed to let the user know that passwords do not match and the sign up button is returned back to its
        //original state after the mouse cursor enters the field for entering the password
        password.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(password.getText().equals("Enter your password")){
                    password.setText("");   
                }

                if(signUp.getText().equals("Passwords do not match")) {
                    signUp.setText("Sign up");
                }
            }

        });

        //an overridden method found in the Text Field Creater class that is designed to let the user know that passwords do not match and the sign up button is returned back to its
        //original state after the mouse cursor enters the field for entering the password
        passwordAgain.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(passwordAgain.getText().equals("Enter your password again")){
                    passwordAgain.setText("");   
                }

                if(signUp.getText().equals("Passwords do not match")) {
                    signUp.setText("Sign up");
                }
            }

        });

        //an overridden method found in the Text Field Creater class that is designed to let the user know that the username is already taken and the sign up button is returned back 
        //to its original state after the mouse cursor enters the field for entering the username
        username.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(username.getText().equals("Enter your username")){
                    username.setText("");   
                }

                if(signUp.getText().equals("Username is already taken")) {
                    signUp.setText("Sign up");
                }
            }

        });

        //an overridden method found in the Text Field Creater class that is designed to let the user know that the email is already taken and the sign up button is returned back 
        //to its original state after the mouse cursor enters the field for entering the email
        email.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(email.getText().equals("Enter your email")){
                    email.setText("");   
                }

                if(signUp.getText().equals("Email is already taken")) {
                    signUp.setText("Sign up");
                }
            }

        });

        //a Label object to set the title of the page
        signUpAsCustomer = new Label("Sign up as customer");
        //sets the style and size of the text
        signUpAsCustomer.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //sets the color of the text
        signUpAsCustomer.setTextFill(Color.WHITE);

        //an empty Label object to leave gaps between nodes
        empty = new Label();

        //a subroot to add the fields for entering the username and email next to each other
        subroot1 = new HBox(30);
        subroot1.setAlignment(Pos.CENTER);
        subroot1.getChildren().addAll(username, email);

        //a subroot to add the fields for entering the password next to each other
        subroot2 = new HBox(30);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(password, passwordAgain);

        //a subroot to add the fields for entering the city and district of the address next to each other
        subroot3 = new HBox(30);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(city, district);

        //adding the nodes to the main root vertically
        root.getChildren().addAll(signUpAsCustomer, empty, subroot1, subroot2, subroot3, street, signUp, back);
    }

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
