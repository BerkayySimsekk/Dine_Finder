import java.util.ArrayList;

public class Menu implements SortableMenu {
    //instance data member
    private ArrayList<Item> menu;

    //constructor
    public Menu() {
        this.menu = new ArrayList<Item>();
    }

    //service methods
    public void addItemToMenu(Item item) {
        this.menu.add(item);
    }
    
    
    public void removeItemFromMenu(Item item) {
        this.menu.remove(item);
    }

    //getter
    public ArrayList<Item> getMenuAsArrayList() {
        return menu;
    }

    //service methods
    @Override
    public void sortItemsByAlphabeticalOrder() {
        Item temporary;

        //bubble sort algorithm that ends if there is nothing left to swap and sorts according to the alphabetical order of the name of the items
        for(int n = 0; n < menu.size() - 1; n++) {

            for(int j = 0; j < menu.size() - n - 1; j++) {
                
                if(menu.get(j + 1).getName().compareTo(menu.get(j).getName()) < 0) {
                    //swapping the found items
                    temporary = menu.get(j);
                    menu.set(j, menu.get(j + 1));
                    menu.set(j + 1, temporary);
        
                }
            }
        }
    }

    @Override
    public void sortItemsByPriceWithDiscount() {
        Item temporary;

        //bubble sort algorithm that ends if there is nothing left to swap and sorts according to the prices of the items
        for(int n = 0; n < menu.size() - 1; n++) {

            for(int j = 0; j < menu.size() - n - 1; j++) {
                if(menu.get(j + 1).getPriceWithDiscount() < menu.get(j).getPriceWithDiscount()) {
                    //swapping the found items
                    temporary = menu.get(j);
                    menu.set(j, menu.get(j + 1));
                    menu.set(j + 1, temporary);
        
                }
            }
        }
    }

    @Override
    public void sortItemsByType() {
        Item temporary;

        //bubble sort algorithm that ends if there is nothing left to swap and sorts according to the type of the items
        for(int n = 0; n < menu.size() - 1; n++) {

            for(int j = 0; j < menu.size() - n - 1; j++) {
                if(menu.get(j + 1).getType().compareTo(menu.get(j).getType()) < 0) {
                    //swapping the found items
                    temporary = menu.get(j);
                    menu.set(j, menu.get(j + 1));
                    menu.set(j + 1, temporary);
        
                }
            }
        }
    }

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
