import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DineFinderApplication extends Application {
    //this static object is used to navigate between pages by changing the root of this Scene object
    public static Stage stage;
    //this static object is used to get the registered users every time the program runs
    public static ListOfUsers listOfUsers;
    //this static object is used to keep track of the currently logged in user
    public static User currentUser;

    @Override
    public void start(Stage stage) {
        listOfUsers = new ListOfUsers();

        //default root and scene are instantiated that are only used to start the program, after the program starts the root of the scene is changed immediately with
        //the root created in the Login class
        VBox root = new VBox();
        Scene scene = new Scene(root, 1650, 900);

        DineFinderApplication.stage = stage;
        //sets the logo of the stage to be displayed at various locations
        stage.getIcons().add(new Image("Images/Logo.png"));
        //sets the title of the stage
        stage.setTitle("Dine Finder");
        //the stage cannot be resized due to concerns regarding the unwanted results emerging from changing the size of the stage
        stage.setResizable(false);
        //the default scene is set whose root is to be changed immediately
        stage.setScene(scene);
        stage.show();

        //the root of the scene is changed with the root created in the Login class
        Login login = new Login();
        login.navigate();
    }
    
    public static void main(String[] args) {
        //starts the application
        launch(args);
    }
}
