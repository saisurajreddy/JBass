import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Bass {
	public Bass(int mal, int mprl, int mc){
		DOfAnalysis.MAX_ASYMMETRIC_PATTERN_LENGTH=mal;
		DOfAnalysis.MAX_LENGTH_OF_PATTERN_BEING_REPLACED=mprl;
		DOfAnalysis.MAX_CYCLES=mc;
	}
	public Bass(){}
	
	public void Compress(File input) throws Exception{
		String binary="";
		BufferedReader br=null;
		Charset set=CharsetDetector.detectCharset(input);
		try{
			InputStreamReader isr=new InputStreamReader(new FileInputStream(input), set);
			br=new BufferedReader(isr);			
			binary=DOfIO.mainctb8Reader(br);
			String str=DOfIO.mainReader(br);
			System.out.println("backer "+DOfPatternCheck.isUnder127(str));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		System.out.println("---   "+binary);
		Bass.first=binary;
		
		String resultbit=DOfAnalysis.EncodingManager(binary, "");
		
		String[] sa=resultbit.split("@");
		System.out.println("length of array  "+sa.length);
		System.out.println("initial    "+binary.length());
		System.out.println("after    "+sa[0].length());
		System.out.println("after    "+sa[1]);
		Bass.second=sa[0];
		
		System.out.println(sa[0]);
		DOfConversion d=new DOfConversion();
		String st=DOfConversion.adder8(sa[0], d);
		
		//System.out.println("**"+binary.length()/16);
		//System.out.println("**"+sa[0].length()/16);
		
		/*DOfConversion d=new DOfConversion();
		//System.out.println(sa[0].length()-(16*(sa[0].length()/16)));;
		
		String str=DOfConversion.btsBroker8(sa[0], d);
		System.out.println(str.length()+" "+str);
		
		str=new String(str.getBytes(), set);
		System.out.println(str.length()+" "+str);
		
		System.out.println(set);
		System.out.println(str.length());
		System.out.println(d.bTOsEND);
		//System.out.println(sa[0].substring(0, 64));
		//System.out.println(str.substring(0, 64));*/
		
		String name=input.getName()+"@"+sa[1]+"@"+d.bTOsEND+"@"+set+"@.copt";
		File output=new File(input.getParent()+"/"+name);
		BufferedWriter bw=null;
		try{
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(output), set);
			bw=new BufferedWriter(osw);
			for(int i=0;i<st.length()/8;i++){
				int n=Integer.parseInt(st.substring(8*i, 8*(i+1)), 2);
				if(n>=128 && n<=159)
					bw.write((char)DOfIO.BACK_CONVERSION_TABLE.get(n).intValue());
				else
					bw.write((char)n);
				//bw.write((char)n);
			}
			//bw.write(str);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				bw.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		System.out.println(Bass.first.length()+" "+Bass.second.length());
	}
	
	public void Decompress(File input) throws Exception{
		String binary="";
		BufferedReader br=null;
		try{
			InputStreamReader isr=new InputStreamReader(new FileInputStream(input),"IBM00858");
			br=new BufferedReader(isr);
			//binary=DOfIO.mainReader(br);
			binary=DOfIO.mainctb8Reader(br);
			String str=DOfIO.mainReader(br);
			System.out.println("backer2 "+DOfPatternCheck.isUnder127(str));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		System.out.println(binary.length());
		
		String[] parts=input.getName().split("@");
		
		//String mBinary=DOfConversion.stbBroker8(binary, Integer.parseInt(parts[2]));
		//System.out.println(mBinary.substring(0, 64));
		//System.out.println(binary.substring(0, 64));
		System.out.println(parts[0]);
		System.out.println(parts[1]);
		System.out.println(parts[2]);
		System.out.println(parts[3]);
		System.out.println(parts[4]);
		
		binary=binary.substring(0, binary.length()-Integer.parseInt(parts[2]));
		System.out.println("****1***"+binary.equals(Bass.second));
		System.out.println(binary.length()+" "+Bass.second.length());
		System.out.println("//"+Bass.second);
		System.out.println("//"+binary);
		
		String resultpat=DOfAnalysis.DecodingManager(binary, parts[1]);
		System.out.println("***2****"+resultpat.equals(Bass.first));
		System.out.println("//"+Bass.first);
		System.out.println("//"+resultpat);
		
		System.out.println(resultpat.substring(104, 160));
		
		File output=new File(input.getParent()+"/"+parts[0]);
		BufferedWriter bw=null;
		try{
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(output),parts[3]);
			bw=new BufferedWriter(osw);
			for(int i=0;i<resultpat.length()/8;i++){
				int n=Integer.parseInt(resultpat.substring(8*i, 8*(i+1)), 2);
				if(n>=128 && n<=159)
					bw.write((char)DOfIO.BACK_CONVERSION_TABLE.get(n).intValue());
				else
					bw.write((char)n);
			}
			//bw.write(DOfConversion.binaryToString8(resultpat, new DOfConversion()));
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				bw.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	public static String first="",second="";
	
	/*public static void main(String[] args) throws Exception{
		//DOfIO.intialize();
		
		//Compress(new File("india.gz"));
		//Decompress(new File("temp/india.7z@-15-12-16-11-15-11-3@3@IBM00858@.copt"));
		
		//start
		
		LinkedList<Integer> ll=new LinkedList<Integer>();
		BufferedReader br=null;
		File input=new File("india.7z");
		Charset set=CharsetDetector.detectCharset(input);
		System.out.println(set);
		
		try{
			InputStreamReader isr=new InputStreamReader(new FileInputStream(input),set);
			br=new BufferedReader(isr);
			DOfIO.charValueReader(br, ll);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		TreeMap<Integer,Integer> tm=new TreeMap<Integer,Integer>();
		DOfPatternCount.over255Count(ll, tm);
		System.out.println(tm);
		System.out.println(tm.size());
		
		TreeSet<Integer> ts=new TreeSet<Integer>();
		DOfAnalysis.replacers(ll, ts);
		System.out.println(ts);
		System.out.println(ts.size());
		
		//System.out.println("before "+ll);
		String end=DOfAnalysis.syncer(ll, tm, ts);
		System.out.println(end);
		
		TreeMap<Integer,Integer> rtm=DOfIO.assembleForward(end);
		System.out.println(rtm);
		//System.out.println("after "+ll);
		
		File output=new File("temp/ganesh.copt");
		BufferedWriter bw=null;
		try{
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(output), set);
			bw=new BufferedWriter(osw);
			for(int i:ll){
				if(DOfIO.CONVERSION_TABLE.containsKey(i))
					bw.write((char)DOfIO.CONVERSION_TABLE.get(i).intValue());
				else
					bw.write((char)i);
			}
			//bw.write(DOfConversion.compressedBinaryToString(ll, rtm));
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				bw.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		//end
		
		LinkedList<Integer> ll=new LinkedList<Integer>();
		BufferedReader br=null;
		File input=new File("india.7z");
		Charset set=CharsetDetector.detectCharset(input);
		System.out.println(set);
		
		try{
			InputStreamReader isr=new InputStreamReader(new FileInputStream(input),set);
			br=new BufferedReader(isr);
			DOfIO.charValueReader(br, ll);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		TreeMap<Integer,Integer> tm=new TreeMap<Integer,Integer>();
		
		for(int i:ll)
			tm.put(i, DOfPatternCount.numberCount(ll, i));
		
		System.out.println(tm);
		System.out.println(tm.size());
		
	}*/
}
