import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ButtonCreater {
    //a method that creates a button with given image; also, the height and width of the image can be determined by this method as well
    public static Button createButtonWithGivenImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image);
        //changes the height of the image
        imageView.setFitHeight(height);
        //changes the width of the image
        imageView.setFitWidth(width);

        Button button = createButton("");
        //sets the image to be used for the Button object
        button.setGraphic(imageView);

        return button;
    }

    //this method is used only in the Search Page class, it creates a button that has the purpose to direct to Restaurant Page class if the button is clicked
    public static Button createButtonForRestaurants(String text) {
        Button button = new Button(text);
        //changes the font of the text inside the Button object 
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        //changes the color of the text inside the Button object
        button.setTextFill(Color.WHITE);
        //changes the background of the Button object
        button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        //changes the width of the Button object; since its default property is to expand as the text size increases, the minimum width of the button should be set
        //instead of the maximum width
        button.setMinWidth(1650);

        //this method is used to change the background color of the button when the mouse enters the allocated field for the Button object
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //changes the background of the Button object
                button.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            
        });

        //this method is used to change the background color of the button when the mouse exits the allocated field for the Button object
        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //changes the background of the Button object
                button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));   
            }
            
        });

        return button;
    }

    //this is a general method to create Button objects that have only the traits that are default for this application
    public static Button createButton(String text) {
        Button button = new Button(text);
        //changes the font of the text inside the Button object 
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        //changes the color of the text inside the Button object
        button.setTextFill(Color.WHITE);
        //changes the background of the Button object
        button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));

        //this method is used to change the background color of the button when the mouse enters the allocated field for the Button object
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //changes the background of the Button object
                button.setBackground(new Background(new BackgroundFill(Color.VIOLET, new CornerRadii(30), new Insets(0))));
            }
            
        });

        //this method is used to change the background color of the button when the mouse exits the allocated field for the Button object
        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //changes the background of the Button object
                button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        return button;
    }
}
