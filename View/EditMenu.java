import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class EditMenu implements Navigable {
    VBox root;
    VBox subroot1OfSubroot1;
    ScrollPane subroot1;
    HBox subroot2;
    HBox subroot3;
    Button create;
    Button delete;

    public EditMenu() {
        ArrayList<TextField> items = new ArrayList<TextField>();

        RestaurantOwner currentRestaurantOwner = (RestaurantOwner)DineFinderApplication.currentUser;

        subroot1OfSubroot1 = new VBox();
        subroot1OfSubroot1.setAlignment(Pos.CENTER);

        subroot1 = new ScrollPane(subroot1OfSubroot1);
        subroot1.setMaxHeight(330);
        subroot1.setVbarPolicy(ScrollBarPolicy.NEVER);

        subroot1OfSubroot1.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        subroot1.getViewportBounds().getWidth(), subroot1.viewportBoundsProperty()));

        TextField name = createEditableTextField("Enter a name for the item");

        TextField type = createEditableTextField("Enter a type for the item");

        TextField price = createEditableTextField("Enter a price for the item");

        create = createButton("Create");

        create.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Item item = new Item(name.getText(), type.getText(), Double.valueOf(price.getText()));
                currentRestaurantOwner.getMenu().addItemToMenu(item);

                TextField itemInfo = createUneditableTextField(item.toString());
                items.add(itemInfo);
                
                subroot1OfSubroot1.getChildren().add(itemInfo);

                name.setText("Created successfully");
                type.setText("Created successfully");
                price.setText("Created successfully");

                Timer myTimer = new Timer();
                myTimer.schedule(new TimerTask(){

                    @Override
                    public void run() {
                        name.setText("Enter a name for the item");
                        type.setText("Enter a type for the item");
                        price.setText("Enter a price for the item");
                    }

                }, 1000);
            }  
        });

        delete = createButton("Delete");

        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean isFound = false;

                Item item = new Item(name.getText(), type.getText(), Double.valueOf(price.getText()));
                
                for(int n = 0; n < items.size(); n++) {
                    if(item.toString().equals(items.get(n).getText())) {
                        isFound = true;

                        currentRestaurantOwner.getMenu().removeItemFromMenu(item);
                        subroot1OfSubroot1.getChildren().remove(items.get(n));
                        items.remove(n);
                        n--;
                    }
                }

                if(isFound) {
                    name.setText("Deleted successfully");
                    type.setText("Deleted successfully");
                    price.setText("Deleted successfully");

                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                    @Override
                    public void run() {
                        name.setText("Enter a name for the item");
                        type.setText("Enter a type for the item");
                        price.setText("Enter a price for the item");
                    }

                    }, 1000);
                }
                else {
                    name.setText("Item not found");
                    type.setText("Item not found");
                    price.setText("Item not found");

                    Timer myTimer = new Timer();
                    myTimer.schedule(new TimerTask(){

                    @Override
                    public void run() {
                        name.setText("Enter a name for the item");
                        type.setText("Enter a type for the item");
                        price.setText("Enter a price for the item");
                    }

                    }, 1000);
                }
            }
            
        });

        subroot2 = new HBox(10);
        subroot2.setAlignment(Pos.CENTER);
        subroot2.getChildren().addAll(name, type, price);

        subroot3 = new HBox(10);
        subroot3.setAlignment(Pos.CENTER);
        subroot3.getChildren().addAll(create, delete);

        Button back = createButtonWithGivenImage(new Image("Images/BackButton.png"), 35, 35);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RestaurantOwnerProfile restaurantOwnerProfile = new RestaurantOwnerProfile();
                restaurantOwnerProfile.navigate();
            }
            
        });

        

        TextField titleForItems = createUneditableTextField("Items");
        subroot1OfSubroot1.getChildren().add(titleForItems);

        for(int n = 0; n < currentRestaurantOwner.getMenu().getMenuAsArrayList().size(); n++) {
            TextField itemInfo = createUneditableTextField(currentRestaurantOwner.getMenu().getMenuAsArrayList().get(n).toString());
            items.add(itemInfo);

            subroot1OfSubroot1.getChildren().add(itemInfo);
        }

        Label title = new Label("Edit Menu");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        title.setTextFill(Color.WHITE);
        
        Label empty = new Label();
        Label empty2 = new Label();

        root = new VBox(30);
        root.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(0), new Insets(0))));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, empty, subroot1, empty2, subroot2, subroot3, back);
    }

    public TextField createUneditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setEditable(false);

        return textField;
    }

    public TextField createEditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        textField.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));
        textField.setStyle("-fx-text-inner-color: white");
        textField.setAlignment(Pos.CENTER);
        textField.setMinWidth(460);

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

    public Button createButtonWithGivenImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        Button button = createButton("");
        button.setGraphic(imageView);

        return button;
    }

    public Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 35));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.VIOLET, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(Color.HOTPINK, new CornerRadii(30), new Insets(0))));   
            }
            
        });

        return button;
    }

    @Override
    public void navigate() {
        DineFinderApplication.stage.getScene().setRoot(root);   
    }
}
