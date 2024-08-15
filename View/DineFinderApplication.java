import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DineFinderApplication extends Application {
    public static Stage stage;
    public static ListOfUsers listOfUsers;
    public static User currentUser;

    @Override
    public void start(Stage stage) {
        listOfUsers = new ListOfUsers();

        VBox defaultRoot = new VBox();

        Scene defaultScene = new Scene(defaultRoot);

        DineFinderApplication.stage = stage;
        stage.getIcons().add(new Image("Images/Logo.png"));
        stage.setTitle("Dine Finder");
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setScene(defaultScene);
        
        stage.show();

        Login login = new Login();
        login.navigate();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
