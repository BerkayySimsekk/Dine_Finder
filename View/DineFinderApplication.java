import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DineFinderApplication extends Application {

    public static Stage stage;
    public static ListOfUsers listOfUsers; //list of users which should be accesible for all pages
    public static User currentUser;

    @Override
    public void start(Stage stage) throws Exception {
        listOfUsers = new ListOfUsers();
        VBox defaultRoot = new VBox();
        Scene defaultScene = new Scene(defaultRoot);

        DineFinderApplication.stage = stage;
        stage.getIcons().add(new Image("Logo.png"));
        stage.setTitle("Dine Finder");
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setScene(defaultScene);
        
        Login login = new Login();
        login.navigate();

        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
