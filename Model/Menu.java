import java.util.ArrayList;

public class Menu{
    private ArrayList<Item> menu;

    public Menu() {
        this.menu = new ArrayList<Item>();
    }

    public void addItemToMenu(Item item) {
        this.menu.add(item);
    }
    
    
    public void removeItemFromMenu(Item item) {
        this.menu.remove(item);
    }

    public ArrayList<Item> getMenuAsArrayList() {
        return menu;
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
