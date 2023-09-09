public class InClass<T> {
    T x;
    public InClass(T x){
        this.x = x;
    }
    public T getX(){
        return x;
    }
    public static void main(String[] args) {
        InClass<Boolean> inClass = new InClass<>(Boolean.valueOf(true));
        System.out.println(inClass.getX());
    }
}
