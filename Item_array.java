
public class Item_array{
    private Item item_list[];
    public Item_array(){
        item_list = new Item[48];
        for(int i = 0;i<48;i++)
            item_list[i] = new Item(i);
    }
    public Item get_Item(int x){
        return item_list[x];
    }
}