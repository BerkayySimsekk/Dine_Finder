import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TextFieldCreater {
    //this method is to create an uneditable Text Field object with the desired minimum width
    public static TextField createUneditableTextField(String text, boolean setMinWidth, int minWidth) {
        TextField textField = new TextField(text);
        //changes style and size of the text
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        //changes the background of the text field
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), Insets.EMPTY)));
        //changes the color of the text
        textField.setStyle("-fx-text-inner-color: white");
        //aligns the text to the center of the allocated space found inside the text field
        textField.setAlignment(Pos.CENTER);
        //makes the text field uneditable
        textField.setEditable(false);

        //sets the minimum width of the text field if the boolean variable taken as a parameter is true
        if(setMinWidth) {
            textField.setMinWidth(minWidth);
        }

        return textField;
    }

    //this method is to create an editable Text Field object with desired minimum width, maximum width and minimum height; also, the text can be centered depending on the boolean
    //variable taken as a parameter
    public static TextField createEditableTextField(String text, boolean setMinWidth, boolean setMinHeight, boolean setMaxWidth, int minWidth, int minHeight, int maxWidth, boolean centered) {
        TextField textField = new TextField(text);
        //sets the style and size of the text
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        //sets the background of the text field
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), Insets.EMPTY)));
        //sets the color of the text
        textField.setStyle("-fx-text-inner-color: white");

        //positions the text to the center of text field
        if(centered) {
            textField.setAlignment(Pos.CENTER);
        }
        //positions the text to the left of the text field
        else {
            textField.setAlignment(Pos.CENTER_LEFT);
        }

        //the minimum width for the text field is set
        if(setMinWidth) {
            textField.setMinWidth(minWidth);
        }

        //the minimum height for the text field is set
        if(setMinHeight) {
            textField.setMinHeight(minHeight);
        }

        //the maximum width for the text field is set
        if(setMaxWidth) {
            textField.setMaxWidth(maxWidth);
        }

        //a default behaviour for the editable Text Field objects throughout the application which clears the field for entering the text if the original text found on that field is
        //unchanged
        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(textField.getText().equals(text)){
                    textField.setText("");   
                }
            } 
               
        });

        //a default behaviour for the editable Text Field objects throughout the application which sets the text of the field back to its original state if nothing is written inside
        //the field
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
}
