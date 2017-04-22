import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.TreeMap;

public class DOfConversion {
public int bTOsEND=0;
	
	public static String convertManagerStoB(File src){
		String binary="";
		BufferedReader br=null;
		try{
			br=new BufferedReader(new FileReader(src));
			String line;
			//boolean flag=false;
			while ((line = br.readLine()) != null) {
				System.out.println("********"+line);
				/*if(flag)
					line+='\n';
				flag=true;*/
				line+='\n';
				binary+=stringToBinary(line);
	            System.out.println(line);
	        }
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		System.out.println(binary);
		System.out.println("stob ended");
		return binary;
	}
	
	public static String stringToBinary(String text){
		String bString="";
	    String temp="";
	    for(int i=0;i<text.length();i++){
	        temp=Integer.toBinaryString(text.charAt(i));
	        for(int j=temp.length();j<16;j++){
	            temp="0"+temp;
	        }
	        System.out.print(text.charAt(i)+"-"+temp+" ");
	        bString+=temp;
	    }
	    //System.out.println(bString);
	    return bString;
	}
	
	public static String convertToBytes8(int num){
		String s=Integer.toBinaryString(num);
		for(int i=s.length();i<8;i++){
			s="0"+s;
		}
		return s;
	}
	
	public static String binaryToString8(String binary, DOfConversion dofc){
		String text="";
		int i=0;
		for(i=0;i+8<=binary.length();i+=8){
			char nextChar = (char)Integer.parseInt(binary.substring(i, i+8), 2);
			text+=nextChar;
			System.out.print(nextChar+" ");
		}
		if(i==binary.length())
			dofc.bTOsEND=0;
		else{
			dofc.bTOsEND=(i+8)-binary.length();
			String rem=binary.substring(i, binary.length());
			for(i=0;i<dofc.bTOsEND;i++)
				rem+="0";
			char nextChar = (char)Integer.parseInt(rem, 2);
			text+=nextChar;
		}
		return text;
	}
	
	public static String convertToBytes(int num){
		String s=Integer.toBinaryString(num);
		for(int i=s.length();i<16;i++){
			s="0"+s;
		}
		return s;
	}
	
	public static String binaryToString(String binary, DOfConversion dofc){
		String text="";
		int i=0;
		for(i=0;i+16<=binary.length();i+=16){
			char nextChar = (char)Integer.parseInt(binary.substring(i, i+16), 2);
			text+=nextChar;
			System.out.print(nextChar+" ");
		}
		if(i==binary.length())
			dofc.bTOsEND=0;
		else{
			dofc.bTOsEND=(i+16)-binary.length();
			String rem=binary.substring(i, binary.length());
			for(i=0;i<dofc.bTOsEND;i++)
				rem+="0";
			char nextChar = (char)Integer.parseInt(rem, 2);
			text+=nextChar;
		}
		return text;
	}
	
	public static String compressedBinaryToString(LinkedList<Integer> ll, TreeMap<Integer,Integer> rtm){
		String text="";
		for(int i:ll){
			char nextChar;
			if(rtm.containsKey(i))
				nextChar = (char)rtm.get(i).intValue();
			else
				nextChar = (char)i;
			text+=nextChar;
		}
		return text;
	}
	
	public static String btsBroker(String s, DOfConversion d){
		String sp="";
		int i=0;
		for(i=0;i<s.length()/16;i++){
			String temp=s.substring(16*i, 16*(i+1));
			sp+=(char)Integer.parseInt(temp, 2);
		}
		if((16*i)<s.length()){
			d.bTOsEND=16-(s.length()-(16*i));
			String temp=s.substring(16*i, s.length());
			for(int j=temp.length();j<16;j++)
				temp+="0";
			sp+=(char)Integer.parseInt(temp, 2);
		}
		return sp;
	}
	public static String stbBroker(String s, int n){
		String binary="";
		for(int i=0;i<s.length();i++)
			binary+=DOfConversion.convertToBytes((int)s.charAt(i));
		binary=binary.substring(0, binary.length()-n);
		return binary;
	}
	public static String btsBroker8(String s, DOfConversion d){
		String sp="";
		int i=0;
		for(i=0;i<s.length()/8;i++){
			String temp=s.substring(8*i, 8*(i+1));
			sp+=(char)Integer.parseInt(temp, 2);
		}
		if((8*i)<s.length()){
			d.bTOsEND=8-(s.length()-(8*i));
			String temp=s.substring(8*i, s.length());
			for(int j=temp.length();j<8;j++)
				temp+="0";
			sp+=(char)Integer.parseInt(temp, 2);
		}
		return sp;
	}
	public static String stbBroker8(String s, int n){
		String binary="";
		for(int i=0;i<s.length();i++)
			binary+=DOfConversion.convertToBytes8((int)s.charAt(i));
		binary=binary.substring(0, binary.length()-n);
		return binary;
	}
	public static String adder(String s, DOfConversion d){
		int n=16-(s.length()%16);
		d.bTOsEND=n;
		for(int i=0;i<n;i++)
			s+="0";
		return s;
	}
	public static String adder8(String s, DOfConversion d){
		int n=8-(s.length()%8);
		d.bTOsEND=n;
		for(int i=0;i<n;i++)
			s+="0";
		return s;
	}
}
