import java.util.Timer;
import java.util.TimerTask;

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

public class CustomerProfile implements Navigable{
    VBox root;
    HBox subRoot1;
    HBox subRoot2;
    HBox subRoot3;
    boolean usernameAlreadyExists;
    boolean emailAlreadyExists;
    Button editUsername;
    Button applyPassword;
    Button applyEmail;
    Button applyUsername;
    Button editEmail;
    Button editPassword;

    public CustomerProfile() {
        Customer currentCustomer = (Customer)DineFinderApplication.currentUser;

        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));

        TextField username = createUneditableTextField("Username: " + currentCustomer.getUsername());

        TextField email = createUneditableTextField("Email: " + currentCustomer.getEmail());

        TextField password = createUneditableTextField("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()));

        applyUsername = createButtonWithGivenImage(new Image("Apply.png"), 30, 30);
        applyUsername.setVisible(false);

        applyUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                usernameAlreadyExists = false;

                for(User user : DineFinderApplication.listOfUsers.getUserList()) {
                    if(user.getUsername().equals(username.getText())) {
                        usernameAlreadyExists = true;  
                    }
                }

                if(usernameAlreadyExists) {
                    username.setText("Username is already taken");

                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            username.setText("");
                        }

                    }, 2000);
                }
                else {
                    if(username.getText().equals("")) {
                        username.setText("Username: " + currentCustomer.getUsername());
                        applyUsername.setVisible(false);
                        editUsername.setVisible(true);
                        username.setEditable(false);
                    }
                    else {
                        currentCustomer.setUsername(username.getText());
                        applyUsername.setVisible(false);
                        editUsername.setVisible(true);
                        username.setEditable(false);
                        username.setText("Username: " + currentCustomer.getUsername());
                    }
    
                }
            }
            
        });

        applyEmail = createButtonWithGivenImage(new Image("Apply.png"), 30, 30);
        applyEmail.setVisible(false);

        applyEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                emailAlreadyExists = false;

                for(User user : DineFinderApplication.listOfUsers.getUserList()) {
                    if(user.getEmail().equals(email.getText())) {
                        emailAlreadyExists = true;  
                    }
                }

                if(emailAlreadyExists) {
                    email.setText("Email is already taken");

                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            email.setText("");
                        }

                    }, 2000);
                }
                else {
                    if(email.getText().equals("")) {
                        email.setText("Email: " + currentCustomer.getEmail());
                        applyEmail.setVisible(false);
                        editEmail.setVisible(true);
                        email.setEditable(false);
                    }
                    else {
                        currentCustomer.setEmail(email.getText());
                        applyEmail.setVisible(false);
                        editEmail.setVisible(true);
                        email.setEditable(false);
                        email.setText("Email: " + currentCustomer.getEmail());
                    }
                }
            }
            
        });

        applyPassword = createButtonWithGivenImage(new Image("Apply.png"), 30, 30);
        applyPassword.setVisible(false);

        applyPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(password.getText().equals("")) {
                    password.setText("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()));
                    applyPassword.setVisible(false);
                    editPassword.setVisible(true);
                    password.setEditable(false);
                }
                else {
                    currentCustomer.setPassword(password.getText());
                    applyPassword.setVisible(false);
                    editPassword.setVisible(true);
                    password.setEditable(false);
                    password.setText("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()));
                }
            }
            
        });

        editUsername = createButtonWithGivenImage(new Image("Edit.png"), 30, 30);

        editUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                username.setText("");
                username.setEditable(true);
                applyUsername.setVisible(true);
                editUsername.setVisible(false);
            }
            
        });

        editEmail = createButtonWithGivenImage(new Image("Edit.png"), 30, 30);

        editEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                email.setText("");
                email.setEditable(true);
                applyEmail.setVisible(true);
                editEmail.setVisible(false);
            }
            
        });

        editPassword = createButtonWithGivenImage(new Image("Edit.png"), 30, 30);

        editPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                password.setText("");
                password.setEditable(true);
                applyPassword.setVisible(true);
                editPassword.setVisible(false);
            }
            
        });

        Button back = createButtonWithGivenImage(new Image("BackButton.png"), 30, 30);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.navigate();
            }
            
        });

        Label title = new Label("Your Profile");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        Label empty = new Label();
        Label empty2 = new Label();

        subRoot1 = new HBox(10);
        subRoot1.setAlignment(Pos.CENTER);
        subRoot1.getChildren().addAll(username, applyUsername, editUsername);

        subRoot2 = new HBox(10);
        subRoot2.setAlignment(Pos.CENTER);
        subRoot2.getChildren().addAll(email, applyEmail, editEmail);

        subRoot3 = new HBox(10);
        subRoot3.setAlignment(Pos.CENTER);
        subRoot3.getChildren().addAll(password, applyPassword, editPassword);

        root.getChildren().addAll(title, empty, subRoot1, subRoot2, subRoot3, empty2, back);
    }

    public String createAsterisksWithTheLengthOfPassword(String password) {
        String asterisks = "";

        for(int n = 0; n < password.length(); n++) {
            asterisks += "*";
        }

        return asterisks;
    }

    public Button createButtonWithGivenImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        Button button = createButton("");
        button.setGraphic(imageView);

        return button;
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
    public TextField createEditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setMinWidth(710);
        textField.setMinHeight(65);

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

    //a method to create a text field with the given text
    public TextField createUneditableTextField(String text) {
        //Text Field object is created with the desired traits
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setMinWidth(650);
        textField.setEditable(false);

        return textField;
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}