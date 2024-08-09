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
    HBox subroot1;
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
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        TextField username = createUneditableTextField("Username: " + currentCustomer.getUsername());

        TextField email = createUneditableTextField("Email: " + currentCustomer.getEmail());

        TextField password = createUneditableTextField("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()));

        applyUsername = createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyEmail = createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyPassword = createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        editUsername = createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                username.setText("");
                username.setEditable(true);
                applyUsername.setVisible(true);
                editUsername.setVisible(false);
            }
            
        });

        editEmail = createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                email.setText("");
                email.setEditable(true);
                applyEmail.setVisible(true);
                editEmail.setVisible(false);
            }
            
        });

        editPassword = createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                password.setText("");
                password.setEditable(true);
                applyPassword.setVisible(true);
                editPassword.setVisible(false);
            }
            
        });

        Button back = createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

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

        subroot1 = new HBox(10);
        subroot1.setAlignment(Pos.CENTER);
        subroot1.getChildren().addAll(username, applyUsername, editUsername);

        subRoot2 = new HBox(10);
        subRoot2.setAlignment(Pos.CENTER);
        subRoot2.getChildren().addAll(email, applyEmail, editEmail);

        subRoot3 = new HBox(10);
        subRoot3.setAlignment(Pos.CENTER);
        subRoot3.getChildren().addAll(password, applyPassword, editPassword);

        root.getChildren().addAll(title, empty, subroot1, subRoot2, subRoot3, back);
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

    public TextField createUneditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setMinWidth(1450);
        textField.setEditable(false);

        return textField;
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}