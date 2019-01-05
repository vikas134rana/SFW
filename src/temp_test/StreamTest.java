package temp_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.Quotes;

public class StreamTest {

	public boolean isEven(Integer ele) {

		return ele%2==0;
	}

	public Integer getFirstEvenEle(List<Integer> eles) {

		if (eles == null)
			return null;

		return eles.stream().filter(i -> isEven(i)).findFirst().orElse(null);
	}

	public Integer getLastEvenEle(List<Integer> eles) {

		if (eles == null)
			return null;

		Collections.reverse(eles);
		Integer result = eles.stream().filter(i -> isEven(i)).findFirst().orElse(null);
		Collections.reverse(eles);
		
		return result;
	}

	public List<Integer> getAllEvenEle(List<Integer> eles) {

		if (eles == null)
			return new ArrayList<>();

		return eles.stream().filter(i -> isEven(i)).collect(Collectors.toList());
	}

	public Integer getEvenEleAt(List<Integer> eles, int index) {
		if (eles == null || index < 0)
			return null;

		return eles.stream().filter(i -> isEven(i)).skip(index).findFirst().orElse(null);
	}

	public static void main(String[] args) {
		
		
		System.out.println(Quotes.escape("foo'"));
		System.out.println(Quotes.escape("foo\""));
		System.out.println(Quotes.escape("foo\"'"));
		
		StreamTest obj = new StreamTest();
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
		System.out.println("First even : "+ obj.getFirstEvenEle(list));
		System.out.println("Last even : "+ obj.getLastEvenEle(list));
		System.out.println("All even : "+ obj.getAllEvenEle(list));
		System.out.println("At even : "+ obj.getEvenEleAt(list, 0));
	}
	
}
