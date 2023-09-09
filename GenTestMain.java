import java.util.ArrayList;
import java.util.Arrays;
class GenTest<T>{
	T v1;
	//int k = 100;
	GenTest(T o){
		v1 = o;
	}
	void getType(){
		System.out.println("Generic class is instantiated with " + v1.getClass().getName());
	}
	T getVal(){
		return v1;
	}

public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
    ArrayList<E> newList = new ArrayList<E>();
    for (E element : list) {
        if (!newList.contains(element)) {
            newList.add(element);
        }
    }
    return newList;
}	
}
class GenTestMain{
	public static void main(String[] args){
		GenTest<Integer> i = new GenTest<Integer>(2001);
		GenTest<Double> d = new GenTest<Double>(2.0);
		GenTest<String> s = new GenTest<String>("Ashesi");
		i.getType();
		d.getType();
		s.getType();
		
		int i1 = i.getVal();
		double d1 = d.getVal();
		String s1 = s.getVal();
		
		System.out.printf("The values are: %d, %f, %s ", i1, d1, s1);

		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,10,10,10,10,10,10,10));
		System.out.println("\nThe list without duplicates: " + GenTest.removeDuplicates(testList));

	}
}
		
		
		

	
	