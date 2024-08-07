import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class RestaurantOwnerProfile implements Navigable{
    VBox root;
    HBox subRoot1;
    HBox subRoot2;
    HBox subRoot3;
    HBox subRoot4;
    HBox subRoot5;
    HBox subRoot6;
    boolean usernameAlreadyExists;
    boolean emailAlreadyExists;
    Button editUsername;
    Button applyPassword;
    Button applyEmail;
    Button applyUsername;
    Button editEmail;
    Button editPassword;
    Button applyRestaurantName;
    Button applyAddress;
    Button applyDescription;
    Button editRestaurantName;
    Button editAddress;
    Button editDescription;

    public RestaurantOwnerProfile() {
        RestaurantOwner currentRestaurantOwner = (RestaurantOwner)DineFinderApplication.currentUser;

        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        TextField username = createUneditableTextField("Username: " + currentRestaurantOwner.getUsername());

        TextField email = createUneditableTextField("Email: " + currentRestaurantOwner.getEmail());

        TextField password = createUneditableTextField("Password: " + createAsterisksWithTheLengthOfPassword(currentRestaurantOwner.getPassword()));

        TextField restaurantName = createUneditableTextField("Restaurant name: " + currentRestaurantOwner.getRestaurantName());

        TextField address = createUneditableTextField("Address: " + currentRestaurantOwner.getAddress().getCityName() + "," + currentRestaurantOwner.getAddress().getDistrictName() + "," + currentRestaurantOwner.getAddress().getStreetName());

        TextField description = createUneditableTextField("Description: " + currentRestaurantOwner.getDescription());

        applyRestaurantName = createButtonWithGivenImage(new Image("Apply.png"), 35, 35);
        applyRestaurantName.setVisible(false);

        applyRestaurantName.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                    
                if(restaurantName.getText().equals("")) {
                    restaurantName.setText("Restaurant name: " + currentRestaurantOwner.getRestaurantName());
                    applyRestaurantName.setVisible(false);
                    editRestaurantName.setVisible(true);
                    restaurantName.setEditable(false);
                }
                else {
                    currentRestaurantOwner.setRestaurantName(restaurantName.getText());
                    applyRestaurantName.setVisible(false);
                    editRestaurantName.setVisible(true);
                    restaurantName.setEditable(false);
                    restaurantName.setText("Restaurant name: " + currentRestaurantOwner.getRestaurantName());
                }
    
            }
            
        });

        applyAddress = createButtonWithGivenImage(new Image("Apply.png"), 35, 35);
        applyAddress.setVisible(false);

        applyAddress.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                    
                if(address.getText().equals("")) {
                    address.setText("Address: " + currentRestaurantOwner.getAddress());
                    applyAddress.setVisible(false);
                    editAddress.setVisible(true);
                    address.setEditable(false);
                }
                else {
                    ArrayList<String> list = new ArrayList<String>();
                    CharacterIterator it = new StringCharacterIterator(address.getText());
                    String dividedAddress = "";

                    for(int n = 0; n < address.getText().length(); n++) {
                        if(it.current() != ',' && it.getIndex() != it.getEndIndex() - 1) {
                            dividedAddress += it.current();
                            it.next();
                        }
                        else {
                            if(it.getIndex() == it.getEndIndex() - 1) {
                                dividedAddress += it.current();
                            }

                            list.add(dividedAddress);
                            dividedAddress = "";
                            it.next();
                        }
                    }
                    
                    if(list.size() == 3) {
                        currentRestaurantOwner.getAddress().setCityName(list.get(0));
                        currentRestaurantOwner.getAddress().setDistrictName(list.get(1));
                        currentRestaurantOwner.getAddress().setStreetName(list.get(2));    
                    }

                    applyAddress.setVisible(false);
                    editAddress.setVisible(true);
                    address.setEditable(false);
                    address.setText("Address: " + currentRestaurantOwner.getAddress());
                }
    
            }
            
        });

        applyDescription = createButtonWithGivenImage(new Image("Apply.png"), 35, 35);
        applyDescription.setVisible(false);

        applyDescription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                    
                if(description.getText().equals("")) {
                    description.setText("Description: " + currentRestaurantOwner.getDescription());
                    applyDescription.setVisible(false);
                    editDescription.setVisible(true);
                    description.setEditable(false);
                }
                else {
                    currentRestaurantOwner.setDescription(description.getText());
                    applyDescription.setVisible(false);
                    editDescription.setVisible(true);
                    description.setEditable(false);
                    description.setText("Description: " + currentRestaurantOwner.getDescription());
                }
    
            }
            
        });

        applyUsername = createButtonWithGivenImage(new Image("Apply.png"), 35, 35);
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
                        username.setText("Username: " + currentRestaurantOwner.getUsername());
                        applyUsername.setVisible(false);
                        editUsername.setVisible(true);
                        username.setEditable(false);
                    }
                    else {
                        currentRestaurantOwner.setUsername(username.getText());
                        applyUsername.setVisible(false);
                        editUsername.setVisible(true);
                        username.setEditable(false);
                        username.setText("Username: " + currentRestaurantOwner.getUsername());
                    }
    
                }
            }
            
        });

        applyEmail = createButtonWithGivenImage(new Image("Apply.png"), 35, 35);
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
                        email.setText("Email: " + currentRestaurantOwner.getEmail());
                        applyEmail.setVisible(false);
                        editEmail.setVisible(true);
                        email.setEditable(false);
                    }
                    else {
                        currentRestaurantOwner.setEmail(email.getText());
                        applyEmail.setVisible(false);
                        editEmail.setVisible(true);
                        email.setEditable(false);
                        email.setText("Email: " + currentRestaurantOwner.getEmail());
                    }
                }
            }
            
        });

        applyPassword = createButtonWithGivenImage(new Image("Apply.png"), 35, 35);
        applyPassword.setVisible(false);

        applyPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(password.getText().equals("")) {
                    password.setText("Password: " + createAsterisksWithTheLengthOfPassword(currentRestaurantOwner.getPassword()));
                    applyPassword.setVisible(false);
                    editPassword.setVisible(true);
                    password.setEditable(false);
                }
                else {
                    currentRestaurantOwner.setPassword(password.getText());
                    applyPassword.setVisible(false);
                    editPassword.setVisible(true);
                    password.setEditable(false);
                    password.setText("Password: " + createAsterisksWithTheLengthOfPassword(currentRestaurantOwner.getPassword()));
                }
            }
            
        });

        editRestaurantName = createButtonWithGivenImage(new Image("Edit.png"), 35, 35);

        editRestaurantName.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                restaurantName.setText("");
                restaurantName.setEditable(true);
                applyRestaurantName.setVisible(true);
                editRestaurantName.setVisible(false);
            }
            
        });

        editDescription = createButtonWithGivenImage(new Image("Edit.png"), 35, 35);

        editDescription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                description.setText("");
                description.setEditable(true);
                applyDescription.setVisible(true);
                editDescription.setVisible(false);
            }
            
        });

        editAddress = createButtonWithGivenImage(new Image("Edit.png"), 35, 35);

        editAddress.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                address.setText("city,district,street");

                Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            address.setText("");;
                        }

                    }, 2000);

                address.setEditable(true);
                applyAddress.setVisible(true);
                editAddress.setVisible(false);
            }
            
        });

        editUsername = createButtonWithGivenImage(new Image("Edit.png"), 35, 35);

        editUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                username.setText("");
                username.setEditable(true);
                applyUsername.setVisible(true);
                editUsername.setVisible(false);
            }
            
        });

        editEmail = createButtonWithGivenImage(new Image("Edit.png"), 35, 35);

        editEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                email.setText("");
                email.setEditable(true);
                applyEmail.setVisible(true);
                editEmail.setVisible(false);
            }
            
        });

        editPassword = createButtonWithGivenImage(new Image("Edit.png"), 35, 35);

        editPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                password.setText("");
                password.setEditable(true);
                applyPassword.setVisible(true);
                editPassword.setVisible(false);
            }
            
        });

        Button editMenu = createButton("Edit menu");

        editMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                EditMenu editMenu = new EditMenu();
                editMenu.navigate();
            }

        });

        Button back = createButtonWithGivenImage(new Image("BackButton.png"), 35, 35);

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

        subRoot1 = new HBox(10);
        subRoot1.setAlignment(Pos.CENTER);
        subRoot1.getChildren().addAll(username, applyUsername, editUsername);

        subRoot2 = new HBox(10);
        subRoot2.setAlignment(Pos.CENTER);
        subRoot2.getChildren().addAll(email, applyEmail, editEmail);

        subRoot3 = new HBox(10);
        subRoot3.setAlignment(Pos.CENTER);
        subRoot3.getChildren().addAll(password, applyPassword, editPassword);

        subRoot4 = new HBox(10);
        subRoot4.setAlignment(Pos.CENTER);
        subRoot4.getChildren().addAll(restaurantName, applyRestaurantName, editRestaurantName);

        subRoot5 = new HBox(10);
        subRoot5.setAlignment(Pos.CENTER);
        subRoot5.getChildren().addAll(address, applyAddress, editAddress);

        subRoot6 = new HBox(10);
        subRoot6.setAlignment(Pos.CENTER);
        subRoot6.getChildren().addAll(description, applyDescription, editDescription);

        root.getChildren().addAll(title, empty, subRoot1, subRoot2, subRoot3, subRoot4, subRoot5, subRoot6, editMenu, back);
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
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));

        //the color of the button's background is changed if the mouse enters the area that this button can be found at
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.VIOLET, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        //the color of the button's background is changed back to its previous color if the mouse exits the area that this button can be found at
        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        return button;
    }

    //a method to create a text field with the given text
    public TextField createUneditableTextField(String text) {
        //Text Field object is created with the desired traits
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
