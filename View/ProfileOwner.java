import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProfileOwner extends Application implements Navigable{

    @FXML
    private ImageView profOwnBackButtonImage;

    @FXML
    private ImageView profOwnStar;

    @FXML
    private Button profOwnBackButton;

    @FXML
    private Button profOwnChangeAdress;

    @FXML
    private Button profOwnChangeDesc;

    @FXML
    private Button profOwnChangeEmail;

    @FXML
    private Button profOwnChangeMenu;

    @FXML
    private Button profOwnChangeName;

    @FXML
    private Button profOwnChangePhone;

    @FXML
    private Button profOwnChangePic;

    @FXML
    public TextField profOwnTFAdress;

    @FXML
    public TextField profOwnTFDesc;

    @FXML
    public TextField profOwnTFEmail;

    @FXML
    public TextField profOwnTFName;

    @FXML
    public TextField profOwnTFPhone;

    @FXML
    public TextField profOwnTFRating;

    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProfileOwner.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Owner Profile");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }
    
    //Change Buttons
    public void profOwnChangeName(ActionEvent event){
        (MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().size() - 1)).setUsername(profOwnTFName.getText());
        profOwnTFName.setText("Username has been updated.");
    }
    public void profOwnChangeAdress(ActionEvent event){
        String[] address = (profOwnTFAdress.getText()).split(",");
        Address newAddress = new Address("", "", "");
        newAddress.setStreetName(address[0]);
        newAddress.setDistrictName(address[1]);
        newAddress.setCityName(address[2]);
        (MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().size() - 1)).setAddress(newAddress);
        profOwnTFAdress.setText("Address has been updated.");
    }
    public void profOwnChangeEmail(ActionEvent event){
        (MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().size() - 1)).setEmail(profOwnTFEmail.getText());
        profOwnTFEmail.setText("Email has been updated.");
    }
    public void profOwnChangeDesc(ActionEvent event){
        (MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().size() - 1)).setDescription(profOwnTFDesc.getText());
            profOwnTFDesc.setText("Description has been updated.");
    }
    public void profOwnChangePhone(ActionEvent event){
        (MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().size() - 1)).setPhoneNumber(profOwnTFPhone.getText());
        profOwnTFPhone.setText("Phone number has been updated.");
    }

    //Back Button
    public void profOwnBackButtonAction(ActionEvent event){
        MainScreen mainScreen = new MainScreen();
        mainScreen.navigate();
    }

    //Navigation
    public void navigate() {
        

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfileOwner.fxml"));

        try {
            MealReelApplication.profileOwn = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.profileOwn);
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
