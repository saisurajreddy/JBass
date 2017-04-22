
public class DOfPatternCheck {
	public static boolean isAssymetricPattern(String pattern){
		boolean flag=true;
		for(int i=0;i<(pattern.length()%2==0?(pattern.length()/2)+1:(pattern.length()/2));i++){
			if(pattern.substring(0, i+1).equals(pattern.substring(pattern.length()-i-1, pattern.length()))){
				flag=false;
				break;
			}
		}
		return flag;
	}
	public static boolean isUnder255(String pattern){
		boolean flag=true;
		for(int i=0;i<pattern.length();i++){
			if(pattern.charAt(i)>255){
				flag=false;
				break;
			}
		}
		return flag;
	}
	public static boolean isUnder127(String pattern){
		boolean flag=true;
		for(int i=0;i<pattern.length();i++){
			if(pattern.charAt(i)>127){
				flag=false;
				break;
			}
		}
		return flag;
	}
}
