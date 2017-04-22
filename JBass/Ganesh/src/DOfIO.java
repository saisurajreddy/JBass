import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.TreeMap;

public class DOfIO {
	public static TreeMap<Integer, Integer> CONVERSION_TABLE=new TreeMap<Integer, Integer>();
	public static TreeMap<Integer, Integer> BACK_CONVERSION_TABLE=new TreeMap<Integer, Integer>();
	
	public static void intialize(){
		DOfIO.CONVERSION_TABLE.put(402, 128);
		DOfIO.CONVERSION_TABLE.put(8215, 129);
		DOfIO.CONVERSION_TABLE.put(8364, 130);
		DOfIO.CONVERSION_TABLE.put(9472, 131);
		
		DOfIO.CONVERSION_TABLE.put(9474, 132);
		DOfIO.CONVERSION_TABLE.put(9484, 133);
		DOfIO.CONVERSION_TABLE.put(9488, 134);
		DOfIO.CONVERSION_TABLE.put(9492, 135);
		
		DOfIO.CONVERSION_TABLE.put(9496, 136);
		DOfIO.CONVERSION_TABLE.put(9500, 137);
		DOfIO.CONVERSION_TABLE.put(9508, 138);
		DOfIO.CONVERSION_TABLE.put(9516, 139);
		
		DOfIO.CONVERSION_TABLE.put(9524, 140);
		DOfIO.CONVERSION_TABLE.put(9532, 141);
		DOfIO.CONVERSION_TABLE.put(9552, 142);
		DOfIO.CONVERSION_TABLE.put(9553, 143);
		
		DOfIO.CONVERSION_TABLE.put(9556, 144);
		DOfIO.CONVERSION_TABLE.put(9559, 145);
		DOfIO.CONVERSION_TABLE.put(9562, 146);
		DOfIO.CONVERSION_TABLE.put(9565, 147);
		
		DOfIO.CONVERSION_TABLE.put(9568, 148);
		DOfIO.CONVERSION_TABLE.put(9571, 149);
		DOfIO.CONVERSION_TABLE.put(9574, 150);
		DOfIO.CONVERSION_TABLE.put(9577, 151);
		
		DOfIO.CONVERSION_TABLE.put(9580, 152);
		DOfIO.CONVERSION_TABLE.put(9600, 153);
		DOfIO.CONVERSION_TABLE.put(9604, 154);
		DOfIO.CONVERSION_TABLE.put(9608, 155);
		
		DOfIO.CONVERSION_TABLE.put(9617, 156);
		DOfIO.CONVERSION_TABLE.put(9618, 157);
		DOfIO.CONVERSION_TABLE.put(9619, 158);
		DOfIO.CONVERSION_TABLE.put(9632, 159);
		
		//back
		DOfIO.BACK_CONVERSION_TABLE.put(128, 402);
		DOfIO.BACK_CONVERSION_TABLE.put(129, 8215 );
		DOfIO.BACK_CONVERSION_TABLE.put(130, 8364);
		DOfIO.BACK_CONVERSION_TABLE.put(131, 9472);
		
		DOfIO.BACK_CONVERSION_TABLE.put(132, 9474);
		DOfIO.BACK_CONVERSION_TABLE.put(133, 9484);
		DOfIO.BACK_CONVERSION_TABLE.put(134, 9488);
		DOfIO.BACK_CONVERSION_TABLE.put(135, 9492);
		
		DOfIO.BACK_CONVERSION_TABLE.put(136, 9496);
		DOfIO.BACK_CONVERSION_TABLE.put(137, 9500);
		DOfIO.BACK_CONVERSION_TABLE.put(138, 9508);
		DOfIO.BACK_CONVERSION_TABLE.put(139, 9516);
		
		DOfIO.BACK_CONVERSION_TABLE.put(140, 9524);
		DOfIO.BACK_CONVERSION_TABLE.put(141, 9532);
		DOfIO.BACK_CONVERSION_TABLE.put(142, 9552);
		DOfIO.BACK_CONVERSION_TABLE.put(143, 9553);
		
		DOfIO.BACK_CONVERSION_TABLE.put(144, 9556);
		DOfIO.BACK_CONVERSION_TABLE.put(145, 9559);
		DOfIO.BACK_CONVERSION_TABLE.put(146, 9562);
		DOfIO.BACK_CONVERSION_TABLE.put(147, 9565);
		
		DOfIO.BACK_CONVERSION_TABLE.put(148, 9568);
		DOfIO.BACK_CONVERSION_TABLE.put(149, 9571);
		DOfIO.BACK_CONVERSION_TABLE.put(150, 9574);
		DOfIO.BACK_CONVERSION_TABLE.put(151, 9577);
		
		DOfIO.BACK_CONVERSION_TABLE.put(152, 9580);
		DOfIO.BACK_CONVERSION_TABLE.put(153, 9600);
		DOfIO.BACK_CONVERSION_TABLE.put(154, 9604);
		DOfIO.BACK_CONVERSION_TABLE.put(155, 9608);
		
		DOfIO.BACK_CONVERSION_TABLE.put(156, 9617);
		DOfIO.BACK_CONVERSION_TABLE.put(157, 9618);
		DOfIO.BACK_CONVERSION_TABLE.put(158, 9619);
		DOfIO.BACK_CONVERSION_TABLE.put(159, 9632);
	}
	
	public static String mainctbReader(BufferedReader br) throws Exception{
		String binary="";
		int value;
		
		while((value=br.read())!=-1){
			binary+=DOfConversion.convertToBytes(value);
		}
		
		return binary;
	}
	public static String mainctb8Reader(BufferedReader br) throws Exception{
		String binary="";
		int value;
		
		while((value=br.read())!=-1){
			if(value>255){
				binary+=DOfConversion.convertToBytes8(DOfIO.CONVERSION_TABLE.get(value));
			}else{
				binary+=DOfConversion.convertToBytes8(value);
			}
			//if(value>127)
				//System.out.print("()()()()"+DOfConversion.convertToBytes8(value)+" "+value+" "+(char)value);
			//System.out.println("");
		}
		System.out.println("");
		
		return binary;
	}
	public static String mainReader(BufferedReader br) throws Exception{
		String sp="";
		int value;
		
		while((value=br.read())!=-1){
			sp+=(char)value;
		}
		
		return sp;
	}
	
	public static void charValueReader(BufferedReader br, LinkedList<Integer> ll) throws Exception{
		int value;
		while((value=br.read())!=-1)
			ll.add(value);
	}
	
	public static TreeMap<Integer,Integer> assembleForward(String end){
		String[] sa=end.split("-");
		TreeMap<Integer,Integer> rtm=null;
		if((sa.length%2)!=0){
			rtm=new TreeMap<Integer,Integer>();
			System.out.println(sa.length);
			for(int i=sa.length-1;i>0;i=i-2){
				//System.out.println(sa[i-1]+" "+sa[i]);
				rtm.put(Integer.parseInt(sa[i-1]), Integer.parseInt(sa[i]));
			}
		}
		return rtm;
	} 
}
