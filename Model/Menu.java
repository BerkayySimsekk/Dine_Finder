import java.util.ArrayList;

public class Menu{
    //instance data member
    private ArrayList<Item> menu;

    //constructor to instantiate the Array List that is an instance data member of this class
    public Menu() {
        this.menu = new ArrayList<Item>();
    }

    //adds the given Item objects to the Array List that contains items
    public void addItemToMenu(Item item) {
        this.menu.add(item);
    }
    
    //removes the given Item object from the Array List that contains items if the item is found
    public void removeItemFromMenu(Item item) {
        //a loop to check whether the given Item object can be found inside the Array List that contains the items
        for(int n = 0; n < menu.size(); n++) {
            if(menu.get(n).getName().equals(item.getName()) && menu.get(n).getType().equals(item.getType()) && menu.get(n).getPrice() == item.getPrice()) {
                menu.remove(n);
                //after removing the item when it is found, the indexes of every element found in the Array List shifts by one, the "n" value is decreased by one 
                //to prevent the incident of skipping an element
                n--;
            }
        }   
    }

    //getter
    public ArrayList<Item> getMenuAsArrayList() {
        return menu;
    }

    //returns the items in the menu as a String object
    @Override
    public String toString() {
        String menuToPrint;
        
        menuToPrint = "\n";

        for(int n = 0; n < this.menu.size(); n++) {
            menuToPrint += this.menu.get(n) + "\n";
        }

        return menuToPrint;
    }
}
