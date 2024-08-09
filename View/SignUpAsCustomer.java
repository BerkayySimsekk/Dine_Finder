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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SignUpAsCustomer implements Navigable {
    VBox root;
    HBox subroot1;
    HBox subroot2;
    HBox subroot3;

    public SignUpAsCustomer() {
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        
        TextField username = createTextField("Enter your username");

        TextField email = createTextField("Enter your email");

        TextField password = createTextField("Enter your password");

        TextField passwordAgain = createTextField("Enter your password again");

        TextField city = createTextField("Enter the city of your address");

        TextField district = createTextField("Enter the district of your address");

        TextField street = createTextField("Enter the street of your address");

        Button signUp = createButton("Sign up");

        signUp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean usernameAlreadyExists = false;
                boolean emailAlreadyExists = false;

                for(User user : DineFinderApplication.listOfUsers.getUserList()) {
                    if(user.getUsername().equals(username.getText())) {
                        usernameAlreadyExists = true;  
                    }

                    if(user.getEmail().equals(email.getText())) {
                        emailAlreadyExists = true;  
                    }
                }

                if(!password.getText().equals(passwordAgain.getText())) {
                    signUp.setText("Passwords do not match");
                    password.setText("Enter your password");
                    passwordAgain.setText("Enter your password again");
                }
                else if(usernameAlreadyExists) {
                    signUp.setText("Username is already taken");
                    username.setText("Enter your username");
                }
                else if(emailAlreadyExists) {
                    signUp.setText("Email is already taken");
                    email.setText("Enter your email");
                }
                else {
                    Address address = new Address(street.getText(), district.getText(), city.getText());
                    Customer customer = new Customer(password.getText(), email.getText(), username.getText(), address);
                    DineFinderApplication.listOfUsers.getUserList().add(customer);
                    Login login = new Login();
                    login.navigate();
                }
            }

        });

        ImageView image = new ImageView(new Image("Images/BackButton.png"));
        image.setFitHeight(35);
        image.setFitWidth(35);

        Button back = createButton("");
        back.setGraphic(image);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpChoice signUpChoice = new SignUpChoice();
                signUpChoice.navigate();
            }
            
        });

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

        Label signUpAsCustomer = new Label("Sign up as customer");
        signUpAsCustomer.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        signUpAsCustomer.setTextFill(Color.WHITE);

        Label empty = new Label();

        subroot1 = new HBox(30);
        subroot1.setAlignment(Pos.CENTER);
        subroot1.getChildren().addAll(username, email);

        subroot2 = new HBox(30);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(password, passwordAgain);

        subroot3 = new HBox(30);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(city, district);

        root.getChildren().addAll(signUpAsCustomer, empty, subroot1, subroot2, subroot3, street, signUp, back);
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
        textField.setMinWidth(650);
        textField.setMaxWidth(650);

        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
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
