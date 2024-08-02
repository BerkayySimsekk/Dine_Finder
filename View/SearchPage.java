import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchPage implements Navigable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    public static RestaurantFilter restaurantFilter = new RestaurantFilter();
    public static int index;
    public static int pageNumber = 0;
    public boolean sortByAlphabeticalOrder = false;
    public boolean sortByPopularity = false;
    public boolean sortByRating = false;
    public boolean sortByTotalDiscountOfItems = false;
    public boolean lower = false;
    public boolean upper = false;
    public double lowerBounds = 0;
    public double upperBounds = Double.MAX_VALUE;

    @FXML
    private Button applyFilter;
    @FXML
    private Button filterButton;
    @FXML
    private Button resetFilterButton;
    @FXML
    private Button myButton1;
    @FXML
    private Button myButton2;
    @FXML
    private Button myButton3;
    @FXML
    private Button myButton4;
    @FXML
    private Button search;

    @FXML
    private FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("RestaurantPage.fxml"));

    @FXML 
    private Text text1;
    @FXML 
    private Text text2;
    @FXML 
    private Text text3;
    @FXML 
    private Text text4;

    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;
    

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private TextField textField6;
    @FXML
    private TextField textField7;
    @FXML
    private TextField searchText;

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
    private ImageView rating6;
    @FXML
    private ImageView rating7;
    @FXML
    private ImageView rating8;
    @FXML
    private ImageView rating9;
    @FXML
    private ImageView rating10;
    @FXML
    private ImageView rating11;
    @FXML
    private ImageView rating12;
    @FXML
    private ImageView rating13;
    @FXML
    private ImageView rating14;
    @FXML
    private ImageView rating15;
    @FXML
    private ImageView rating16;
    @FXML
    private ImageView rating17;
    @FXML
    private ImageView rating18;
    @FXML
    private ImageView rating19;
    @FXML
    private ImageView rating20;

    @FXML
    private VBox filter;
    private boolean filterCheck = false;

    @FXML
    private Image filledStar = new Image("filledStar.png");
    private Image emptyStar = new Image("emptyStar.png");


    @FXML
    void initialize() 
    {     
        filter.setVisible(false);

        rating1.setImage(filledStar);
        rating6.setImage(filledStar);
        rating11.setImage(filledStar);
        rating12.setImage(filledStar);

        if(RestaurantPage.restaurantFilter.getFilteredRestaurants().size() > 0 && MainScreen.firstSearch == true)
        {
            restaurantFilter = RestaurantPage.restaurantFilter;
            restaurantFilter.includesGivenRestaurantName(MainScreen.searchString);
            MainScreen.firstSearch  = false;
        }
        else
        {
            restaurantFilter.resetFilter();
        }
        
        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
        
    public void openPage1(ActionEvent event)
    {
        index = 0;
        try 
        {
            MealReelApplication.restaurantPage = (Parent) fxmlLoader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.restaurantPage);
    }
    public void openPage2(ActionEvent event)
    {
        
        index = 1;
        try 
        {
            MealReelApplication.restaurantPage = (Parent) fxmlLoader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.restaurantPage);
    }
    public void openPage3(ActionEvent event)
    {
        index = 2;
        try 
        {
            MealReelApplication.restaurantPage = (Parent) fxmlLoader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.restaurantPage);
    }
    public void openPage4(ActionEvent event)
    {
        index = 3;
        
        try 
        {
            MealReelApplication.restaurantPage = (Parent) fxmlLoader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.restaurantPage);
       
    }

    public void page1(ActionEvent event)
    {
        pageNumber = 0;

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
    public void page2(ActionEvent event)
    {
        pageNumber = 1;

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
    public void page3(ActionEvent event)
    {
        pageNumber = 2;

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
    public void page4(ActionEvent event)
    {
        pageNumber = 3;

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
    public void page5(ActionEvent event)
    {
        pageNumber = 4;

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }

    public void resetFilter(ActionEvent event)
    {
        restaurantFilter.resetFilter();
        
        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));

        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
        radioButton3.setSelected(false);
        radioButton4.setSelected(false);

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }

    public void filter(ActionEvent event)
    {
        filterCheck = !filterCheck;
        filter.setVisible(filterCheck);
        filterButton.setDisable(!filterCheck);
    }

    public void apply(ActionEvent event)
    {
        if(textField1.getText().length() > 0)
        {
            restaurantFilter.includesGivenItem(textField1.getText());
            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());
    
            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
        }
        if(textField2.getText().length() > 0)
        {
            restaurantFilter.includesGivenItemType(textField2.getText());

            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
        }
        if(textField3.getText().length() > -1)
        {
            System.out.println(textField3.getText());
            System.out.println(textField3.getText().length());
            restaurantFilter.isFoundInGivenCity(textField3.getText());
            System.out.println(restaurantFilter.getFilteredRestaurants().size());

            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
        }
        if(textField4.getText().length() > 0)
        {
            restaurantFilter.includesGivenItem(textField4.getText());

            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());
    
            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber))); 
        }
        if(textField5.getText().length() > 0)
        {
            restaurantFilter.includesGivenItem(textField5.getText());

            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
        }
        if(textField6.getText().length() > 0)
        {
            lowerBounds = Double.parseDouble(textField1.getText());

            restaurantFilter.includesItemsBetweenSetPriceRange(lowerBounds, Double.parseDouble(textField1.getText()));

            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
        }
        if(textField7.getText().length() > 0)
        {
            upperBounds = Double.parseDouble(textField7.getText());

            restaurantFilter.includesItemsBetweenSetPriceRange(Double.parseDouble(textField7.getText()), upperBounds);

            text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
            text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
            text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
            text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

            setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
            setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
            setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
            setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
        }

    }

    public void search(ActionEvent event)
    {
        restaurantFilter.includesGivenRestaurantName(searchText.getText());

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));

    }

    public void radioButton1(ActionEvent event)
    {
        restaurantFilter.resetFilter();

        sortByAlphabeticalOrder = true;
        sortByPopularity = false;
        sortByRating = false;
        sortByTotalDiscountOfItems = false;

        radioButton1.setSelected(sortByAlphabeticalOrder);
        radioButton2.setSelected(sortByPopularity);
        radioButton3.setSelected(sortByRating);
        radioButton4.setSelected(sortByTotalDiscountOfItems);

        restaurantFilter.sortByAlphabeticalOrderOfRestaurantName();

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));

    }
    public void radioButton2(ActionEvent event)
    {
        restaurantFilter.resetFilter();

        sortByAlphabeticalOrder = false;
        sortByPopularity = true;
        sortByRating = false;
        sortByTotalDiscountOfItems = false;

        radioButton1.setSelected(sortByAlphabeticalOrder);
        radioButton2.setSelected(sortByPopularity);
        radioButton3.setSelected(sortByRating);
        radioButton4.setSelected(sortByTotalDiscountOfItems);

        restaurantFilter.sortByPopularity();

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
    public void radioButton3(ActionEvent event)
    {
        restaurantFilter.resetFilter();

        sortByAlphabeticalOrder = false;
        sortByPopularity = false;
        sortByRating = true;
        sortByTotalDiscountOfItems = false;

        radioButton1.setSelected(sortByAlphabeticalOrder);
        radioButton2.setSelected(sortByPopularity);
        radioButton3.setSelected(sortByRating);
        radioButton4.setSelected(sortByTotalDiscountOfItems);

        restaurantFilter.sortByRating();

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }
    public void radioButton4(ActionEvent event)
    {
        sortByAlphabeticalOrder = false;
        sortByPopularity = false;
        sortByRating = false;
        sortByTotalDiscountOfItems = true;

        radioButton1.setSelected(sortByAlphabeticalOrder);
        radioButton2.setSelected(sortByPopularity);
        radioButton3.setSelected(sortByRating);
        radioButton4.setSelected(sortByTotalDiscountOfItems);

        restaurantFilter.sortByTotalDiscountOfItems();

        text1.setText(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)).getRestaurantName());
        text2.setText(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)).getRestaurantName());
        text3.setText(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)).getRestaurantName());
        text4.setText(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)).getRestaurantName());

        setStars1(restaurantFilter.getFilteredRestaurants().get(0 + (4*pageNumber)));
        setStars2(restaurantFilter.getFilteredRestaurants().get(1 + (4*pageNumber)));
        setStars3(restaurantFilter.getFilteredRestaurants().get(2 + (4*pageNumber)));
        setStars4(restaurantFilter.getFilteredRestaurants().get(3 + (4*pageNumber)));
    }

    public void setStars1(RestaurantOwner restaurantOwner)
    {
        if(restaurantOwner.getRating() >= 2)
        {
            rating1.setImage(filledStar);
            rating2.setImage(filledStar);
            rating3.setImage(emptyStar);
            rating4.setImage(emptyStar);
            rating5.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 3)
        {
            rating1.setImage(filledStar);
            rating2.setImage(filledStar);
            rating3.setImage(filledStar);
            rating4.setImage(emptyStar);
            rating5.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 4)
        {
            rating1.setImage(filledStar);
            rating2.setImage(filledStar);
            rating3.setImage(filledStar);
            rating4.setImage(filledStar);
            rating5.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() > 5)
        {
            rating1.setImage(filledStar);
            rating2.setImage(filledStar);
            rating3.setImage(filledStar);
            rating4.setImage(filledStar);
            rating5.setImage(filledStar);
        }
    }

    public void setStars2(RestaurantOwner restaurantOwner)
    {
        if(restaurantOwner.getRating() >= 2)
        {
            rating6.setImage(filledStar);
            rating7.setImage(filledStar);
            rating8.setImage(emptyStar);
            rating9.setImage(emptyStar);
            rating10.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 3)
        {
            rating6.setImage(filledStar);
            rating7.setImage(filledStar);
            rating8.setImage(filledStar);
            rating9.setImage(emptyStar);
            rating10.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 4)
        {
            rating6.setImage(filledStar);
            rating7.setImage(filledStar);
            rating8.setImage(filledStar);
            rating9.setImage(filledStar);
            rating10.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() > 5)
        {
            rating6.setImage(filledStar);
            rating7.setImage(filledStar);
            rating8.setImage(filledStar);
            rating9.setImage(filledStar);
            rating10.setImage(filledStar);
        }
    }
    public void setStars3(RestaurantOwner restaurantOwner)
    {
        if(restaurantOwner.getRating() >= 2)
        {
            rating11.setImage(filledStar);
            rating12.setImage(filledStar);
            rating13.setImage(emptyStar);
            rating14.setImage(emptyStar);
            rating15.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 3)
        {
            rating11.setImage(filledStar);
            rating12.setImage(filledStar);
            rating13.setImage(filledStar);
            rating14.setImage(emptyStar);
            rating15.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 4)
        {
            rating11.setImage(filledStar);
            rating12.setImage(filledStar);
            rating13.setImage(filledStar);
            rating14.setImage(filledStar);
            rating15.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() > 5)
        {
            rating11.setImage(filledStar);
            rating12.setImage(filledStar);
            rating13.setImage(filledStar);
            rating14.setImage(filledStar);
            rating15.setImage(filledStar);
        }
    }
    public void setStars4(RestaurantOwner restaurantOwner)
    {
        if(restaurantOwner.getRating() >= 2)
        {
            rating16.setImage(filledStar);
            rating17.setImage(filledStar);
            rating18.setImage(emptyStar);
            rating19.setImage(emptyStar);
            rating20.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 3)
        {
            rating16.setImage(filledStar);
            rating17.setImage(filledStar);
            rating18.setImage(filledStar);
            rating19.setImage(emptyStar);
            rating20.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() >= 4)
        {
            rating16.setImage(filledStar);
            rating17.setImage(filledStar);
            rating18.setImage(filledStar);
            rating19.setImage(filledStar);
            rating20.setImage(emptyStar);
        }
        if(restaurantOwner.getRating() > 5)
        {
            rating16.setImage(filledStar);
            rating17.setImage(filledStar);
            rating18.setImage(filledStar);
            rating19.setImage(filledStar);
            rating20.setImage(filledStar);
        }
    }

    @Override
    public void navigate() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchPage.fxml"));
        
        try {
            MealReelApplication.searchPage = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MealReelApplication.stage.getScene().setRoot(MealReelApplication.searchPage);
    }
}
