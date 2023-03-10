import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime=getCurrentTime();

        int value = openingTime.compareTo(closingTime);
        //Check if open after mid-night ...
        if (value > 0){
            if ((LocalTime.parse("00:00:00").isAfter(currentTime) && closingTime.isBefore(currentTime))||(openingTime.isAfter(currentTime) && LocalTime.parse("00:00:00").isBefore(currentTime)))
                return true;
            else
                return false;
        }
        else {//
            if (openingTime.isBefore(currentTime) && closingTime.isAfter(currentTime))
                return true;
            else
                return false;
        }


    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int totalPrice(List<String> selectedItem){
        int total=0;
        for (String item:selectedItem){
            for (Item menuItem:menu){
                if(menuItem.getName().equals(item))
                    total+=menuItem.getPrice();
            }
        }
        return total;
    }
}
