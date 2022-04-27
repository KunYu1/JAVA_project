public class Person{
    private Vec look_vector;
    public Person(){
        look_vector = new Vec(0, 1, 0);
    }
    public Vec get_version(){
        return look_vector;
    }
}