
public class Item{
    private int enum_item;
    private String picture_address;
    public Item(int enum_item){
        this.enum_item = enum_item;
        this.picture_address = enum_item+".png";
    }
    public int get_item_num(){
        return enum_item;
    }
    public void set_item(Item input){
        this.enum_item = input.enum_item;
        this.picture_address = input.picture_address;
    }
}