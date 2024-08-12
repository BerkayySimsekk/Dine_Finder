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
    public static TextField createUneditableTextField(String text, boolean setMinWidth, int minWidth) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), Insets.EMPTY)));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setEditable(false);

        if(setMinWidth) {
            textField.setMinWidth(minWidth);
        }

        return textField;
    }

    public static TextField createEditableTextField(String text, boolean setMinWidth, boolean setMinHeight, boolean setMaxWidth, int minWidth, int minHeight, int maxWidth, boolean centered) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), Insets.EMPTY)));
        textField.setStyle("-fx-text-inner-color: white");

        if(centered) {
            textField.setAlignment(Pos.CENTER);
        }
        else {
            textField.setAlignment(Pos.CENTER_LEFT);
        }

        if(setMinWidth) {
            textField.setMinWidth(minWidth);
        }

        if(setMinHeight) {
            textField.setMinHeight(minHeight);
        }

        if(setMaxWidth) {
            textField.setMaxWidth(maxWidth);
        }

        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(textField.getText().equals(text)){
                    textField.setText("");   
                }
            } 
               
        });

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
