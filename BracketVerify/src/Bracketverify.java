import java.util.*;
import java.util.Map.Entry;
public class Bracketverify {

	private static Scanner sc;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String input = sc.nextLine();
		Boolean ans=bracketverify(input);
		System.out.println(ans);
	}
	public static boolean bracketverify(String input){
		HashMap<Character, Character> matches= new HashMap<Character, Character>();
		matches.put('{', '}');
		matches.put('(', ')');
		matches.put('[', ']');
		
		Set<Character> spchar = new HashSet<Character>();
		Deque<Character> expected = new LinkedList<Character>();
		for( Entry<Character,Character> ee : matches.entrySet()){
			spchar.add(ee.getKey());
			spchar.add(ee.getValue());
		}
		if (input.length()==0) return true;
		for(int i=0; i<input.length(); i++){
			char next = input.charAt(i);
			Character expect = expected.peekLast();
			if(expect!=null && expect==next){
				expected.removeLast();
			}
			else if(matches.containsKey(next)){
				expected.addLast(matches.get(next));
			}
			else if (spchar.contains(next)){
				return false;
			}
		}
		if(!expected.isEmpty())	return false;
		return true;
	}
}
