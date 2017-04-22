import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CharsetDetector {
	private static String[] charsetsToBeTested = {"Cp858","Cp437","Cp775","Cp850","Cp852","Cp855","Cp857","Cp862","Cp866","ISO8859_1","ISO8859_2","ISO8859_4","ISO8859_5","ISO8859_7","ISO8859_9","ISO8859_13","ISO8859_15","KOI8_R","KOI8_U","ASCII","UTF8","UTF-16","UTF-16BE","UTF-16LE","UTF_32","UTF_32BE","UTF_32LE","UTF_32BE_BOM","UTF_32LE_BOM","Cp1250","Cp1251","Cp1252","Cp1253","Cp1254","Cp1257","UnicodeBig","Cp737","Cp874","UnicodeLittle","Big5","Big5_HKSCS","EUC_JP","EUC_KR","GB18030","EUC_CN","GBK","Cp838","Cp1140","Cp1141","Cp1142","Cp1143","Cp1144","Cp1145","Cp1146","Cp1147","Cp1148",
			"Cp1149","Cp037","Cp1026","Cp1047","Cp273","Cp277"};
	
	public static Charset detectCharset(File f) {
		 
        Charset charset = null;
 
        for (String charsetName : charsetsToBeTested) {
            charset = detectCharset(f, Charset.forName(charsetName));
            if (charset != null) {
                break;
            }
        }
 
        return charset;
    }
 
    private static Charset detectCharset(File f, Charset charset) {
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(f));
 
            CharsetDecoder decoder = charset.newDecoder();
            decoder.reset();
 
            byte[] buffer = new byte[512];
            boolean identified = false;
            while ((input.read(buffer) != -1) && (!identified)) {
                identified = identify(buffer, decoder);
            }
 
            input.close();
 
            if (identified) {
                return charset;
            } else {
                return null;
            }
 
        } catch (Exception e) {
            return null;
        }
    }
 
    private static boolean identify(byte[] bytes, CharsetDecoder decoder) {
        try {
            decoder.decode(ByteBuffer.wrap(bytes));
        } catch (CharacterCodingException e) {
            return false;
        }
        return true;
    }
}
