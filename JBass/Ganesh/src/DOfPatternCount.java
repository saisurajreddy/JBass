import java.util.LinkedList;
import java.util.TreeMap;

public class DOfPatternCount {
	public static int patternCount(String mainString, String pattern){
		int index=mainString.length()-1, count=0;
		while(index>=0){
			int lastIndex=mainString.lastIndexOf(pattern, index);
			if(lastIndex<0)
				break;
			else{
				count++;
				index=lastIndex-1;
			}
		}
		return count;
	}
	public static int numberCount(LinkedList<Integer> ll, int n){
		int count=0;
		for(int i:ll)
			if(i==n)
				count++;
		return count;
	}
	
	public static void over255Count(LinkedList<Integer> ll, TreeMap<Integer,Integer> tm){
		for(int i:ll)
			if(i>255 && !tm.containsKey(i))
				tm.put(i, DOfPatternCount.numberCount(ll, i));
	}
}
