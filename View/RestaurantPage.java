import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RestaurantPage implements Navigable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private VBox CommentBox;

    @FXML
    public static RestaurantFilter restaurantFilter = SearchPage.restaurantFilter;
    private boolean commentCheck = false;
    private int pageNumber = SearchPage.pageNumber;
    private int index = (4 * pageNumber) + SearchPage.index;
    private RestaurantOwner restaurantOwner = restaurantFilter.getFilteredRestaurants().get(index);

    @FXML
    private TextArea addressText;
    @FXML
    private TextArea descText;
    @FXML
    private TextArea commentsText;
    @FXML
    private TextArea menuText;
    @FXML
    private TextArea userComment;

    @FXML
    private Button starBut1;
    @FXML
    private Button starBut2;
    @FXML
    private Button starBut3;
    @FXML
    private Button starBut4;
    @FXML
    private Button starBut5;


    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    @FXML
   
    private ImageView rating1;
    @FXML
    private ImageView rating2;
    @FXML
    private ImageView rating3;
    @FXML
    private ImageView rating4;
    @FXML
    private ImageView rating5;
    @FXML
    private Image filledStar = new Image("filledStar.png");
    private Image emptyStar = new Image("emptyStar.png");


    @FXML
    void initialize() {
        CommentBox.setVisible(false);
        rating1.setImage(filledStar);
        rating2.setImage(emptyStar);
        rating3.setImage(emptyStar);
        rating4.setImage(emptyStar);
        rating5.setImage(emptyStar);
    
        menuText.appendText(MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(index).getMenu().toString());

        for (int i = 0; i < restaurantOwner.getCommentsOfRestaurant().size(); i++) {
            commentsText.appendText(MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(index).getCommentsOfRestaurant().get(i) + "\n");
        }

        addressText.appendText("City: " + restaurantOwner.getAddress().getCityName() + "\n");
        addressText.appendText("Street: " + restaurantOwner.getAddress().getStreetName() + "\n");
        addressText.appendText("District Name: " + restaurantOwner.getAddress().getDistrictName() + "\n");

        descText.appendText(restaurantOwner.getDescription());

        if(restaurantOwner.getRating() >= 2)
        {
            rating2.setImage(filledStar);
        }
        if(restaurantOwner.getRating() >= 3)
        {
            rating3.setImage(filledStar);
        }
        if(restaurantOwner.getRating() >= 4)
        {
            rating4.setImage(filledStar);
        }
        if(restaurantOwner.getRating() > 5)
        {
            rating5.setImage(filledStar);
        }
        
    }

    public void back(ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchPage.fxml"));
            MealReelApplication.searchPage = (Parent) fxmlLoader.load();
            MealReelApplication.stage.getScene().setRoot(MealReelApplication.searchPage);
        } catch (Exception e) {
            e.printStackTrace();
        }       
        
    }
    public void fiveStars(ActionEvent event)
    {
        star1.setImage(filledStar);
        star2.setImage(filledStar);
        star3.setImage(filledStar);
        star4.setImage(filledStar);
        star5.setImage(filledStar);
        starBut1.setDisable(true);
        starBut2.setDisable(true);
        starBut3.setDisable(true);
        starBut4.setDisable(true);
        starBut5.setDisable(true);
        restaurantOwner.getGivenRatings().add(5);
        
    }
    public void fourStars(ActionEvent event)
    {
        star1.setImage(emptyStar);
        star2.setImage(filledStar);
        star3.setImage(filledStar);
        star4.setImage(filledStar);
        star5.setImage(filledStar);
        starBut1.setDisable(true);
        starBut2.setDisable(true);
        starBut3.setDisable(true);
        starBut4.setDisable(true);
        starBut5.setDisable(true);
        restaurantOwner.getGivenRatings().add(4);
        
    }
    public void threeStars(ActionEvent event)
    {
        star1.setImage(emptyStar);
        star2.setImage(emptyStar);
        star3.setImage(filledStar);
        star4.setImage(filledStar);
        star5.setImage(filledStar);
        starBut1.setDisable(true);
        starBut2.setDisable(true);
        starBut3.setDisable(true);
        starBut4.setDisable(true);
        starBut5.setDisable(true);
        restaurantOwner.getGivenRatings().add(3);
        
    }
    public void twoStars(ActionEvent event)
    {
        star1.setImage(emptyStar);
        star2.setImage(emptyStar);
        star3.setImage(emptyStar);
        star4.setImage(filledStar);
        star5.setImage(filledStar);
        starBut1.setDisable(true);
        starBut2.setDisable(true);
        starBut3.setDisable(true);
        starBut4.setDisable(true);
        starBut5.setDisable(true);
        restaurantOwner.getGivenRatings().add(2);
    }
    public void oneStar(ActionEvent event)
    {
        star1.setImage(emptyStar);
        star2.setImage(emptyStar);
        star3.setImage(emptyStar);
        star4.setImage(emptyStar);
        star5.setImage(filledStar);
        starBut1.setDisable(true);
        starBut2.setDisable(true);
        starBut3.setDisable(true);
        starBut4.setDisable(true);
        starBut5.setDisable(true);
        restaurantOwner.getGivenRatings().add(1);
        
    }
    public void addComment(ActionEvent event)
    {
        commentCheck = true;
        CommentBox.setVisible(commentCheck);
        
    }
    public void exitCommentSec(ActionEvent event)
    {
        commentCheck = false;
        CommentBox.setVisible(commentCheck);
        
    }
    public void add(ActionEvent event)
    {
        MealReelApplication.listOfUsers.getRestaurantOwnersFromListOfUsers().get(index).addCommentsToRestaurant(userComment.getText());
        userComment.setText("");   
        commentsText.appendText(restaurantOwner.getCommentsOfRestaurant().get(restaurantOwner.getCommentsOfRestaurant().size() - 1));
        
        
    }

    @Override
    public void navigate() {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("RestaurantPage.fxml"));

        try {
            MealReelApplication.restaurantPage = (Parent) fxmlLoader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.restaurantPage);
    }
    

}
