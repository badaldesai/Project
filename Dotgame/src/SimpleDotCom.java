import java.util.ArrayList;
public class SimpleDotCom {
	
	private ArrayList<String> locationCells;
	int numOfHits = 0;
	
	public void setLocationCells(ArrayList<String> locs){
		locationCells = locs;
	}
	
	public String checkYourself(String userInput){
		String Result = "miss";
		int index = locationCells.indexOf(userInput);
		if(index >= 0){
			locationCells.remove(index);
			if(locationCells.isEmpty()){
				
				Result = "Kill";
			}else{
				Result = "hit";
			}
		}
		return Result;
		
	}

}
