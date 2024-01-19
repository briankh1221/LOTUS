import java.util.ArrayList;

public class ArrayListMethodTest {
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
//		add(value) : ArrayList의 맨 뒤에 value를 추가한다.
		list.add("둘리");
//		size() : ArrayList에 저장된 데이터의 개수를 얻어온다.
		System.out.println(list.size() + ": " + list);
		list.add("고길동");
		System.out.println(list.size() + ": " + list);
		list.add("도우너");
		System.out.println(list.size() + ": " + list);
		list.add("또치");
		System.out.println(list.size() + ": " + list);
		list.add("마이콜");
		System.out.println(list.size() + ": " + list);
		System.out.println("===========================");
		
//		add(index, value) : ArrayList의 index 번째 위치에 value를 삽입한다.
		list.add(1, "배추도사");
		System.out.println(list.size() + ": " + list);
		list.add(3, "무도사");
		System.out.println(list.size() + ": " + list);
		System.out.println("=======================================");
		
//		set(index, value) : ArrayList의 index 번째 위치의 데이터를 value로 수정한다.
		list.set(1, "하니");
		System.out.println(list.size() + ": " + list);
		list.set(3, "홍두깨");
		System.out.println(list.size() + ": " + list);
		System.out.println("=======================================");
		
//		get(index) : ArrayList의 index 번째 위치의 값을 얻어온다.
		System.out.println(list.get(5));
		System.out.println(list.get(1));
		System.out.println("=======================================");
		
//		일반 for
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("=======================================");
		
//		향상된 for
		for(String str : list) {
			System.out.println(str);
		}
		System.out.println("=======================================");
		
//		remove(index) : ArrayList의 index 번째 위치의 데이터를 제거한다.
		list.remove(1);
		System.out.println(list.size() + ": " + list);
		list.remove(2);
		System.out.println(list.size() + ": " + list);
		System.out.println("=======================================");
		
//		remove(object) : ArrayList의 인수로 지정된 데이터를 제거한다.
		list.remove("고길동");
		System.out.println(list.size() + ": " + list);
		list.add("도우너");
		System.out.println(list.size() + ": " + list);
		list.remove("도우너");
		System.out.println(list.size() + ": " + list);
		System.out.println("=======================================");
		
//		clear() : ArrayList에 저장된 모든 데이터를 제거한다.
		list.clear();
		System.out.println(list.size() + ": " + list);
		
	}

}

















