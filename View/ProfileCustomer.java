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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileCustomer extends Application implements Navigable{

    @FXML
    private ImageView profCusBackButtonImage;
    //private Image backButtonImage = new Image("backButton.png");

    @FXML
    private Button profCusBackButton;

    @FXML
    private Button profCusChangeRealName;

    @FXML
    private Button profCusChangeRealSurname;

    @FXML
    private Button profCusChangeAdress;

    @FXML
    private Button profCusChangeEmail;

    @FXML
    private Button profCusChangeName;

    @FXML
    private Button profCusChangePic;

    @FXML
    private Button profCusRevHistory;

    @FXML
    private Button profCusRevHisBackButton;

    @FXML
    public TextField profCusTFRealName;

    @FXML
    public TextField profCusTFRealSurname;
    
    @FXML
    public TextField profCusTFAdress;

    @FXML
    public TextField profCusTFEmail;

    @FXML
    public TextField profCusTFName;

    @FXML
    public TextField profCusTFRecComment;

    @FXML
    public TextField profCusReviewHistoryTF;

    public void start(Stage primaryStage) {
        //Set ImageView pictures
        //profCusPicture.setImage(profilePic);
        //profCusBackButton.setImage(backButtonImage);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProfileCustomer.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Customer Profile");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    //Change Buttons
    public void profCusChangeRealName(ActionEvent event){
        (MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1)).setCustName(profCusTFRealName.getText());
        profCusTFRealName.setText("Name has been updated.");
    }
    public void profCusChangeRealSurname(ActionEvent event){
        (MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1)).setCustSurname(profCusTFRealSurname.getText());
        profCusTFRealSurname.setText("Surname has been updated.");
    }
    public void profCusChangeName(ActionEvent event){
        (MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1)).setUsername(profCusTFName.getText());
        profCusTFName.setText("Username has been updated.");
    }
    public void profCusChangeAdress(ActionEvent event){
        String[] address = (profCusTFAdress.getText()).split(",");
        Address newAddress = new Address("", "", "");
        newAddress.setStreetName(address[0]);
        newAddress.setDistrictName(address[1]);
        newAddress.setCityName(address[2]);
        (MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1)).setAddress(newAddress);
        profCusTFAdress.setText("Address has been updated.");
    }
    public void profCusChangeEmail(ActionEvent event){
        (MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
            MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1)).setEmail(profCusTFEmail.getText());
        profCusTFEmail.setText("Email has been updated.");
    }

    //Back Button
    public void profCusBackButtonAction(ActionEvent event){
        MainScreen mainScreen = new MainScreen();
        mainScreen.navigate();
    }

    //Back Button
    public void profCusRevHisBackButtonAction(ActionEvent event){
        MainScreen mainScreen = new MainScreen();
        mainScreen.navigate();
    }

    //Review History Button
    public void profCusReviewHistoryAction(ActionEvent event){
        //profCusReviewHistoryTF.setText(MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
        //    MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1).getCommentsGivenByCustomer().get(0));
        //for (int index = 1; index < (MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1); index++) {
        //    profCusReviewHistoryTF.setText(profCusReviewHistoryTF + MealReelApplication.listOfUsers.getCustomersFromListOfUsers().get(
        //    MealReelApplication.listOfUsers.getCustomersFromListOfUsers().size() - 1).getCommentsGivenByCustomer().get(index));
        //}
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerReviewHistory.fxml"));

        try {
            MealReelApplication.profCusRevHistory = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.profCusRevHistory);

        
    }

    //Navigation
    public void navigate() {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfileCustomer.fxml"));

        try {
            MealReelApplication.profileCus = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.profileCus);
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}