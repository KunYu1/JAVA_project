
public class Bag{
    private Item bag_item[];
    private int bag_item_num[];
    private Item_array item_array;
    public Bag(){
        item_array = new Item_array();
        bag_item_num = new int[48];
        bag_item = new Item[48];
        for(int i =0;i<48;i++){
            bag_item[i] = item_array.get_Item(0);
            bag_item_num[i] = -1;
        }
    }
    public void add_Bag(int x){
        int find = 0;
        for(int i=0;i<48;i++){
            if(bag_item[i].get_item_num() == x){
                find = 1;
                bag_item_num[i]++;
                break;
            }
        }
        if(find!=1){
            for(int i=0;i<48;i++){
                if(bag_item[i].get_item_num() == -1){
                    bag_item[i].set_item(item_array.get_Item(x));
                }
            } 
        }
    }
    public void throw_Bag(int x){
        for(int i=0;i<48;i++){
            if(bag_item[i].get_item_num() == x){
                if(bag_item_num[i]>1)
                    bag_item_num[i]--;
                else{
                    bag_item_num[i]=-1;
                    bag_item[i].set_item(item_array.get_Item(0));
                }
                break;
            }
        }
    }
}