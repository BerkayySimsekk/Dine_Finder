import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class EditMenu implements Navigable {
    BorderPane root;
    ScrollPane subroot1;

    public EditMenu() {

        TextArea menuInfo = new TextArea("123412341\n23\n421\n341234");
        menuInfo.setEditable(false);
        menuInfo.setStyle("-fx-control-inner-background: deeppink;");
        menuInfo.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));

        root = new BorderPane(menuInfo, null, null, null, null);
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);   
    }
}
