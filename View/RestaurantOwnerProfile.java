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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class RestaurantOwnerProfile implements Navigable{
    VBox root;
    HBox subroot1;
    HBox subroot2;
    HBox subroot3;
    HBox subroot4;
    HBox subroot5;
    HBox subroot6;
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
    Button editMenu;
    Button back;
    TextField username;
    TextField email;
    TextField password;
    TextField restaurantName;
    TextField address;
    TextField description;
    Label title;
    Label empty;

    public RestaurantOwnerProfile() {
        RestaurantOwner currentRestaurantOwner = (RestaurantOwner)DineFinderApplication.currentUser;

        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        username = TextFieldCreater.createUneditableTextField("Username: " + currentRestaurantOwner.getUsername(), true, 1450);

        email = TextFieldCreater.createUneditableTextField("Email: " + currentRestaurantOwner.getEmail(), true, 1450);

        password = TextFieldCreater.createUneditableTextField("Password: " + createAsterisksWithTheLengthOfPassword(currentRestaurantOwner.getPassword()), true, 1450);

        restaurantName = TextFieldCreater.createUneditableTextField("Restaurant name: " + currentRestaurantOwner.getRestaurantName(), true, 1450);

        address = TextFieldCreater.createUneditableTextField("Address: " + currentRestaurantOwner.getAddress().getCityName() + "," + currentRestaurantOwner.getAddress().getDistrictName() + "," + currentRestaurantOwner.getAddress().getStreetName(), true, 1450);

        description = TextFieldCreater.createUneditableTextField("Description: " + currentRestaurantOwner.getDescription(), true, 1450);

        applyRestaurantName = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyAddress = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyDescription = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyUsername = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyEmail = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        applyPassword = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
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

        editRestaurantName = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editRestaurantName.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                restaurantName.setText("");
                restaurantName.setEditable(true);
                applyRestaurantName.setVisible(true);
                editRestaurantName.setVisible(false);
            }
            
        });

        editDescription = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editDescription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                description.setText("");
                description.setEditable(true);
                applyDescription.setVisible(true);
                editDescription.setVisible(false);
            }
            
        });

        editAddress = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editAddress.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                address.setText("city,district,street");

                Timer myTimer = new Timer();
                myTimer.schedule(new TimerTask(){

                    @Override
                    public void run() {
                        address.setText("");
                    }

                }, 2000);

                address.setEditable(true);
                applyAddress.setVisible(true);
                editAddress.setVisible(false);
            }
            
        });

        editUsername = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                username.setText("");
                username.setEditable(true);
                applyUsername.setVisible(true);
                editUsername.setVisible(false);
            }
            
        });

        editEmail = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                email.setText("");
                email.setEditable(true);
                applyEmail.setVisible(true);
                editEmail.setVisible(false);
            }
            
        });

        editPassword = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        editPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                password.setText("");
                password.setEditable(true);
                applyPassword.setVisible(true);
                editPassword.setVisible(false);
            }
            
        });

        editMenu = ButtonCreater.createButton("Edit menu");

        editMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                EditMenu editMenu = new EditMenu();
                editMenu.navigate();
            }

        });

        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.navigate();
            }
            
        });

        title = new Label("Your Profile");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);

        empty = new Label();

        subroot1 = new HBox(10);
        subroot1.setAlignment(Pos.CENTER);
        subroot1.getChildren().addAll(username, applyUsername, editUsername);

        subroot2 = new HBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(email, applyEmail, editEmail);

        subroot3 = new HBox(10);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(password, applyPassword, editPassword);

        subroot4 = new HBox(10);
        subroot4.setAlignment(Pos.CENTER);
        subroot4.getChildren().addAll(restaurantName, applyRestaurantName, editRestaurantName);

        subroot5 = new HBox(10);
        subroot5.setAlignment(Pos.CENTER);
        subroot5.getChildren().addAll(address, applyAddress, editAddress);

        subroot6 = new HBox(10);
        subroot6.setAlignment(Pos.CENTER);
        subroot6.getChildren().addAll(description, applyDescription, editDescription);

        root.getChildren().addAll(title, empty, subroot1, subroot2, subroot3, subroot4, subroot5, subroot6, editMenu, back);
    }

    public String createAsterisksWithTheLengthOfPassword(String password) {
        String asterisks = "";

        for(int n = 0; n < password.length(); n++) {
            asterisks += "*";
        }

        return asterisks;
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
