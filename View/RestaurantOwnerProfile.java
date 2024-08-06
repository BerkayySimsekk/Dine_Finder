import javafx.scene.layout.BorderPane;

public class RestaurantOwnerProfile implements Navigable {
    BorderPane root;

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);
    }
}
