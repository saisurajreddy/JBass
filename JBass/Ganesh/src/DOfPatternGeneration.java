import java.util.ArrayList;

public class DOfPatternGeneration {
	public static void genPatternWithSize(int size, String temp, ArrayList<String> parr){
		if(size>0){
			genPatternWithSize(size-1, temp+"0", parr);
			genPatternWithSize(size-1, temp+"1", parr);
		}else
			parr.add(temp);
	}
	public static String[] genPattern(String bstr){
		String[] bsarr=new String[2];
		bsarr[0]=bstr+"0";
		bsarr[1]=bstr+"1";
		return bsarr;
	}
	//DOfPatternGeneration and DOfPatternCheck synchronizer
	public static void asymmetricPatternGeneration(String given, int originalLength, int countLength, ArrayList<String> result){
		if(originalLength>=countLength){
			if(DOfPatternCheck.isAssymetricPattern(given)){
				result.add(given);
			}else{
				String[] tempPattern=genPattern(given);
				asymmetricPatternGeneration(tempPattern[0], originalLength, countLength+1, result);
				asymmetricPatternGeneration(tempPattern[1], originalLength, countLength+1, result);
			}
		}
	}
	//DOfPatternGeneration and DOfPatternCount synchronizer
	public static void frequentOccurPatGeneration(String mainString,int index, int max, ArrayList<String> givenV, ArrayList<Integer> givenC){
		/*if(index<givenV.size()){
			if(givenV.get(index).length()>=max){
				givenV.remove(index);
				givenC.remove(index);
			}else{
				String[] temp=DOfPatternGeneration.genPattern(givenV.get(index));
				int count=DOfPatternCount.patternCount(mainString, temp[0]);
				if(count!=0){
					givenV.add(temp[0]);
					givenC.add(count);
				}
				count=DOfPatternCount.patternCount(mainString, temp[1]);
				if(count!=0){
					givenV.add(temp[1]);
					givenC.add(count);
				}
				frequentOccurPatGeneration(mainString, index+1, max, givenV, givenC);
			}
		}*/
		for(int i=index;i<givenV.size();i++){
			if(givenV.get(i).length()>=max){
				givenV.remove(i);
				givenC.remove(i);
			}else{
				String[] temp=DOfPatternGeneration.genPattern(givenV.get(i));
				int count=DOfPatternCount.patternCount(mainString, temp[0]);
				if(count!=0){
					givenV.add(temp[0]);
					givenC.add(count);
				}
				count=DOfPatternCount.patternCount(mainString, temp[1]);
				if(count!=0){
					givenV.add(temp[1]);
					givenC.add(count);
				}
			}
		}
	}
}
