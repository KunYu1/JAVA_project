public class Hand{
    private Item hand_item[];
    private int hand_item_num[];
    private Item_array item_array;
    public Hand(){
        item_array = new Item_array();
        hand_item_num = new int[48];
        hand_item = new Item[48];
        for(int i =0;i<48;i++){
            hand_item[i] = item_array.get_Item(0);
            hand_item_num[i] = -1;
        }
    }
    public void add_hand(int x){
        int find = 0;
        for(int i=0;i<48;i++){
            if(hand_item[i].get_item_num() == x){
                find = 1;
                hand_item_num[i]++;
                break;
            }
        }
        if(find!=1){
            for(int i=0;i<48;i++){
                if(hand_item[i].get_item_num() == -1){
                    hand_item[i].set_item(item_array.get_Item(x));
                }
            } 
        }
    }
    public void throw_hand(int x){
        for(int i=0;i<48;i++){
            if(hand_item[i].get_item_num() == x){
                if(hand_item_num[i]>1)
                    hand_item_num[i]--;
                else{
                    hand_item_num[i]=-1;
                    hand_item[i].set_item(item_array.get_Item(0));
                }
                break;
            }
        }
    }
}