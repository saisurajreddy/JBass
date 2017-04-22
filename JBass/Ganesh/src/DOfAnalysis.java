import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DOfAnalysis {
	public static int MAX_ASYMMETRIC_PATTERN_LENGTH=16, MAX_LENGTH_OF_PATTERN_BEING_REPLACED=16, MAX_CYCLES=5;
	public static String patternBeingReplaced="", replacedPattern="", FORMAT_STRING="\n", ENCODING_STRING="cp1252";
	public static int noc=0;
	
	public static String EncodingManager(String binary, String fileNamesEnd){
		noc=0;
		boolean flag=false;
		for(noc=0;noc<MAX_CYCLES;noc++){
			System.out.println("&&"+noc);
			patternBeingReplaced="";
			replacedPattern="";
			if(!AnalyzeToEncode(binary)){
				flag=true;
				break;
			}
			fileNamesEnd="-"+patternBeingReplaced.length()+"-"+replacedPattern.length()+fileNamesEnd;
			binary=binary.replace(patternBeingReplaced, replacedPattern);
			binary=replacedPattern+patternBeingReplaced+binary;
			System.out.println("initial "+replacedPattern+"    "+patternBeingReplaced);
		}
		/*if(flag){
			noc--;
			System.out.println("[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]");
		}*/
		fileNamesEnd+="-"+noc;
		binary+="@"+fileNamesEnd;
		return binary;
	}
	public static boolean AnalyzeToEncode(String binary){
		int startSize=3;
		boolean flag=true;
		ArrayList<String> generation=new ArrayList<String>();
		ArrayList<Integer> generationResult=new ArrayList<Integer>();
		ArrayList<String> asymmetricPatterns=new ArrayList<String>();
		while(flag && startSize<16){
			DOfPatternGeneration.genPatternWithSize(startSize, "", generation);
			for(String pattern:generation){
				int var=DOfPatternCount.patternCount(binary, pattern);
				if(var==0)
					flag=false;
				generationResult.add(var);
			}
			if(flag){
				generation.clear();
				generationResult.clear();
				startSize++;
			}
		}
		if(startSize>=MAX_ASYMMETRIC_PATTERN_LENGTH){
			return false;
		}
		for(int i=0;i<generation.size();i++){
			if(generationResult.get(i)==0){
				if(DOfPatternCheck.isAssymetricPattern(generation.get(i))){
					System.out.println("success");
					asymmetricPatterns.add(generation.get(i));
				}else{
					System.out.println("nope");
					DOfPatternGeneration.asymmetricPatternGeneration(generation.get(i), generation.get(i).length(), 0, asymmetricPatterns);
				}
			}
		}
		int minAsymmetricPattern=minLengthFinder(asymmetricPatterns);
		if(asymmetricPatterns.get(minAsymmetricPattern).length()>=MAX_ASYMMETRIC_PATTERN_LENGTH){
			return false;
		}
		System.out.println(asymmetricPatterns.get(minAsymmetricPattern));
		generation.clear();
		generationResult.clear();
		DOfPatternGeneration.genPatternWithSize(asymmetricPatterns.get(minAsymmetricPattern).length()+1, "", generation);
		for(int i=0;i<generation.size();i++){
			generationResult.add(DOfPatternCount.patternCount(binary, generation.get(i)));
		}
		deleteWhenZero(generation, generationResult);
		DOfPatternGeneration.frequentOccurPatGeneration(binary, 0, MAX_LENGTH_OF_PATTERN_BEING_REPLACED, generation, generationResult);
		if(generation.size()==0)
			return false;
		int best=0, bestIndex=-1;
		for(int i=0;i<generation.size();i++){
			int diff=(generationResult.get(i)*generation.get(i).length())-((generationResult.get(i)*asymmetricPatterns.get(minAsymmetricPattern).length())+asymmetricPatterns.get(minAsymmetricPattern).length()+generation.get(i).length());
			if(diff>best){
				best=diff;
				bestIndex=i;
			}
		}
		if(best==0 || bestIndex==-1)
			return false;
		patternBeingReplaced=generation.get(bestIndex);
		replacedPattern=asymmetricPatterns.get(minAsymmetricPattern);
		
		System.out.println(best+"    "+bestIndex);
		System.out.println(generation.get(bestIndex)+"     "+generationResult.get(bestIndex));
		
		generation.clear();
		generationResult.clear();
		asymmetricPatterns.clear();
		
		/*for(int i=0;i<generation.size();i++){
			System.out.println(generation.get(i)+"        "+generationResult.get(i));
		}*/
		return true;
	}
	
	public static void deleteWhenZero(ArrayList<String> givenV ,ArrayList<Integer> givenC){
		ArrayList<Integer> temp=new ArrayList<Integer>();
		for(int i=0;i<givenC.size();i++)
			if(givenC.get(i)==0)
				temp.add(i);
		for(int i=0;i<temp.size();i++){
			givenV.remove(temp.get(i)-i);
			givenC.remove(temp.get(i)-i);
		}
	}
	
	public static int minLengthFinder(ArrayList<String> given){
		int min=given.get(0).length(), minIndex=0;
		for(int i=1;i<given.size();i++){
			if(min>given.get(i).length()){
				min=given.get(i).length();
				minIndex=i;
			}
		}
		return minIndex;
	}
	
	public static String DecodingManager(String binary, String fileNamesEnd){
		String[] larr=fileNamesEnd.split("-");
		int cycles=Integer.parseInt(larr[larr.length-1]);
		int top=1;
		System.out.println("length"+larr.length+"cycles:"+cycles);
		/*for(String s:larr){
			System.out.println(s);
		}*/
		for(int i=cycles;i>0;i--){
			String target=binary.substring(0, Integer.parseInt(larr[top+1]));
			String replace=binary.substring(Integer.parseInt(larr[top+1]), Integer.parseInt(larr[top])+Integer.parseInt(larr[top+1]));
			System.out.println(target+"    "+replace);
			binary=binary.substring(Integer.parseInt(larr[top])+Integer.parseInt(larr[top+1]));
			binary=binary.replace(target, replace);
			System.out.println(top+"    "+(top+1));
			top+=2;
		}
		return binary;
	}
	
	public static void replacers(LinkedList<Integer> ll, TreeSet<Integer> ts){
		int[] v=new int[256];
		for(int i:ll)
			if(i<256 && v[i]==0)
				v[i]=1;
		for(int i=0;i<256;i++){
			if(v[i]!=1){
				if(i>=128 && i<=159)
					ts.add(i+128);
				else
					ts.add(i);
			}
		}
	}
	
	public static int maxCountHolder(TreeMap<Integer,Integer> tm){
		Set<Integer> s=tm.keySet();
		int max=0, value=0;
		for(int i:s){
			if(tm.get(i)>max){
				max=tm.get(i);
				value=i;
			}
		}
		tm.remove(value);
		return value;
	}
	
	public static String syncer(LinkedList<Integer> ll, TreeMap<Integer,Integer> tm, TreeSet<Integer> ts){
		String end="";
		if(tm.size()>ts.size()){
			
		}else{
			for(int i:ts){
				Set<Integer> s=tm.keySet();
				int max=0, value=0;
				boolean flag=false;
				for(int j:s){
					flag=true;
					if(tm.get(j)>max){
						max=tm.get(j);
						value=j;
					}
				}
				if(flag){
					tm.remove(value);
					end="-"+value+"-"+i+end;
				}else
					return end;
			}
		}
		return null;
	}
}
