package DU4.cz.cvut.fel.cz.storage;

import DU4.cz.cvut.fel.cz.shop.Item;

/**
 * Auxiliary class for item storage
 */
public class ItemStock {

    private Item refItem;
    private int count;
    
    public ItemStock(Item refItem) {
        this.refItem = refItem;
        count = 0;
    }
    
    @Override
    public String toString() {
        return "STOCK OF ITEM:  "+refItem.toString()+"    PIECES IN STORAGE: "+count;
    }
    
    public void increaseItemCount(int x) {
        if (count + x < 0)
            count = 0;
        else count += x;
    }
    
    public void decreaseItemCount(int x) {
        if (count - x < 0)
            count = 0;
        else count -= x;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public Item getItem() {
        return refItem;
    }
}