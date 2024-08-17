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

public class CustomerProfile implements Navigable{
    //nodes used for this class
    VBox root;
    HBox subroot1;
    HBox subroot2;
    HBox subroot3;
    HBox subroot4;
    Button editUsername;
    Button applyPassword;
    Button applyEmail;
    Button applyUsername;
    Button applyAddress;
    Button editEmail;
    Button editPassword;
    Button editAddress;
    Button back;
    TextField username;
    TextField email;
    TextField password;
    TextField address;
    Label title;
    Label empty;
    //these boolean variables needs to be declared outside the constructor so that they can be used inside the inner classes
    boolean usernameAlreadyExists;
    boolean emailAlreadyExists;

    public CustomerProfile() {
        //since this class is to display information regarding the customer that entered this application, the User object should be turned into Customer object
        //to use the methods only available to the Customer class
        Customer currentCustomer = (Customer)DineFinderApplication.currentUser;

        root = new VBox(30);
        //positions the given nodes at the center of the root
        root.setAlignment(Pos.CENTER);
        //changes the background color of the root
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));

        //Textfield objects that displays the username, email, password and address of the current customer; only the length of the password can be seen
        username = TextFieldCreater.createUneditableTextField("Username: " + currentCustomer.getUsername(), true, 1450);
        email = TextFieldCreater.createUneditableTextField("Email: " + currentCustomer.getEmail(), true, 1450);
        password = TextFieldCreater.createUneditableTextField("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()), true, 1450);
        address = TextFieldCreater.createUneditableTextField("Address: " + currentCustomer.getAddress().getCityName() + "," + currentCustomer.getAddress().getDistrictName() + "," + currentCustomer.getAddress().getStreetName(), true, 1450);

        //a Button object to apply the changed username
        applyUsername = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
        //this button should only be visible after the button to edit the username is clicked
        applyUsername.setVisible(false);

        applyUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //a boolean variable to check whether the username is already taken or not so that appropriate action can be taken accordingly
                usernameAlreadyExists = false;

                //checks the list that contains the registered users and checks their username with the changed username
                for(User user : DineFinderApplication.listOfUsers.getUserList()) {
                    if(user.getUsername().equals(username.getText())) {
                        usernameAlreadyExists = true;  
                    }
                }

                if(usernameAlreadyExists) {
                    username.setText("Username is already taken");

                    //after letting the user know the the username is already taken, Timer class is used to clear the area for the username after a certain amount of 
                    //time has passed
                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            username.setText("");
                        }

                    }, 2000);
                }
                else {
                    //if a new username is not chosen, the previous username will be displayed after clicking the apply button for username
                    if(username.getText().equals("")) {
                        username.setText("Username: " + currentCustomer.getUsername());
                        applyUsername.setVisible(false);
                        editUsername.setVisible(true);
                        username.setEditable(false);
                    }
                    //if a new username is chosen, the new username will be displayed from now on
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

        //a Button object to apply the changed email
        applyEmail = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
        //this button should only be visible after the button to edit the email is clicked
        applyEmail.setVisible(false);

        applyEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //a boolean variable to check whether the email is already taken or not so that appropriate action can be taken accordingly
                emailAlreadyExists = false;

                //checks the list that contains the registered users and checks their email with the changed email
                for(User user : DineFinderApplication.listOfUsers.getUserList()) {
                    if(user.getEmail().equals(email.getText())) {
                        emailAlreadyExists = true;  
                    }
                }

                if(emailAlreadyExists) {
                    email.setText("Email is already taken");

                    //after letting the user know the the email is already taken, Timer class is used to clear the area for the email after a certain amount of 
                    //time has passed
                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                        @Override
                        public void run() {
                            email.setText("");
                        }

                    }, 2000);
                }
                else {
                    //if a new email is not chosen, the previous email will be displayed after clicking the apply button for email
                    if(email.getText().equals("")) {
                        email.setText("Email: " + currentCustomer.getEmail());
                        applyEmail.setVisible(false);
                        editEmail.setVisible(true);
                        email.setEditable(false);
                    }
                    //if a new email is chosen, the new email will be displayed from now on
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

        //a Button object to apply the changed password
        applyPassword = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
        //this button should only be visible after the button to edit the password is clicked
        applyPassword.setVisible(false);

        applyPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //if a new password is not chosen, the previous password will be displayed with asterisks after clicking the apply button for password
                if(password.getText().equals("")) {
                    password.setText("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()));
                    applyPassword.setVisible(false);
                    editPassword.setVisible(true);
                    password.setEditable(false);
                }
                //if a new password is chosen, the new password will be displayed from now on with asterisks
                else {
                    currentCustomer.setPassword(password.getText());
                    applyPassword.setVisible(false);
                    editPassword.setVisible(true);
                    password.setEditable(false);
                    password.setText("Password: " + createAsterisksWithTheLengthOfPassword(currentCustomer.getPassword()));
                }
            }
            
        });

        //a Button object to apply the changed address
        applyAddress = ButtonCreater.createButtonWithGivenImage(new Image("Images/Apply.png"), 35, 35);
        //this button should only be visible after the button to edit the address is clicked
        applyAddress.setVisible(false);

        applyAddress.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //if a new address is not chosen, the previous address will be displayed after clicking the apply button for address
                if(address.getText().equals("")) {
                    address.setText("Address: " + currentCustomer.getAddress());
                    applyAddress.setVisible(false);
                    editAddress.setVisible(true);
                    address.setEditable(false);
                }
                //if a new address is chosen, the new address will be displayed from now on
                else {
                    //a list to keep the entered city, district and street as String objects
                    ArrayList<String> list = new ArrayList<String>();
                    //a character iterator to get the city, district and street seperately which iterates through the entered address, it divides
                    //the city, district and street in this object according to comma that seperates them
                    CharacterIterator it = new StringCharacterIterator(address.getText());
                    String dividedAddress = "";

                    //a loop to iterate through every character in the given address
                    for(int n = 0; n < address.getText().length(); n++) {
                        //keeps adding the letters unless a comma is found or the end of the text for the address is reached
                        if(it.current() != ',' && it.getIndex() != it.getEndIndex() - 1) {
                            dividedAddress += it.current();
                            it.next();
                        }
                        //when the comma is found or the end of the text for the address reached, the found word is added to the list, then the process repeats
                        //until the end of the text for the address is reached
                        else {
                            if(it.getIndex() == it.getEndIndex() - 1) {
                                dividedAddress += it.current();
                            }

                            list.add(dividedAddress);
                            dividedAddress = "";
                            it.next();
                        }
                    }
                    
                    //makes sure that the user entered the address correctly with comma between them
                    if(list.size() == 3) {
                        currentCustomer.getAddress().setCityName(list.get(0));
                        currentCustomer.getAddress().setDistrictName(list.get(1));
                        currentCustomer.getAddress().setStreetName(list.get(2));    
                    }

                    applyAddress.setVisible(false);
                    editAddress.setVisible(true);
                    address.setEditable(false);
                    address.setText("Address: " + currentCustomer.getAddress());
                }
    
            }
            
        });

        //a Button object to edit the username
        editUsername = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        //once this button is clicked, the field for the username is emptied and an input for the username is expected
        editUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                username.setText("");
                username.setEditable(true);
                applyUsername.setVisible(true);
                editUsername.setVisible(false);
            }
            
        });

        //a Button object to edit the email
        editEmail = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        //once this button is clicked, the field for the email is emptied and an input for the email is expected
        editEmail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                email.setText("");
                email.setEditable(true);
                applyEmail.setVisible(true);
                editEmail.setVisible(false);
            }
            
        });

        //a Button object to edit the password
        editPassword = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        //once this button is clicked, the field for the password is emptied and an input for the password is expected
        editPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                password.setText("");
                password.setEditable(true);
                applyPassword.setVisible(true);
                editPassword.setVisible(false);
            }
            
        });

        //a Button object to edit the address
        editAddress = ButtonCreater.createButtonWithGivenImage(new Image("Images/Edit.png"), 35, 35);

        //once this button is clicked, an example input on how to enter the address is shown for a certain amount of time and then the field is emptied for an input
        //for the address
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

        //a Button object to let the user to navigate to the previous page which is the main screen
        back = ButtonCreater.createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        //navigates to the main screen once this button is clicked
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.navigate();
            }
            
        });

        //a Label object to set the title of this page
        title = new Label("Your Profile");
        //sets the style and size of the text for the title
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        //sets the color of the text for the title
        title.setTextFill(Color.WHITE);

        //an empty label object to leave empty space between certain nodes
        empty = new Label();

        //a subroot for the main root is created to add the field for the username and the buttons for this username next to each other
        subroot1 = new HBox(10);
        subroot1.setAlignment(Pos.CENTER);
        subroot1.getChildren().addAll(username, applyUsername, editUsername);

        //a subroot for the main root is created to add the field for the email and the buttons for this email next to each other
        subroot2 = new HBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(email, applyEmail, editEmail);

        //a subroot for the main root is created to add the field for the password and the buttons for this password next to each other
        subroot3 = new HBox(10);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(password, applyPassword, editPassword);

        //a subroot for the main root is created to add the field for the address and the buttons for this address next to each other
        subroot4 = new HBox(10);
        subroot4.setAlignment(Pos.CENTER);
        subroot4.getChildren().addAll(address, applyAddress, editAddress);

        //the necessary nodes for the main root is added
        root.getChildren().addAll(title, empty, subroot1, subroot2, subroot3, subroot4, back);
    }

    //a method to create asterisks that has the size of the password of the current user
    public String createAsterisksWithTheLengthOfPassword(String password) {
        String asterisks = "";

        for(int n = 0; n < password.length(); n++) {
            asterisks += "*";
        }

        return asterisks;
    }

    //a method to navigate to this page by changing the root of the main scene found in the Dine Finder Application class
    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}