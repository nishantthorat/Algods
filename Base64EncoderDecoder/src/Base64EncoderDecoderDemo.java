

public class Base64EncoderDecoderDemo {

	private static String CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	public static void main(String[] args) {
		System.out.println(CODES.length());
		Base64EncoderDecoderDemo demo = new Base64EncoderDecoderDemo();
		System.out.println(demo.encode("pleasure.".getBytes()));
		System.out.println(demo.encode("sure.".getBytes()));
		System.out.println(new String(demo.decode("cGxlYXN1cmUu")));
	}

	private byte[] decode(String dest){
		if (null == dest || dest.isEmpty()){
			return null;
		}
		
		byte[] out = new byte[dest.length() * 3/4 - (dest.indexOf("=") > 0 ? dest.length() - dest.indexOf("=") : 0)];
		
		// get four characters at a time - 4 bytes at a time which are converted to 3 characters at a time
		char[] inChars = dest.toCharArray();
		int b[] = new int[4]; // 4 bytes
		int j = 0;
		for (int i = 0; i < dest.length(); i+=4){
			b[0] = CODES.indexOf(inChars[i]);
			b[1] = CODES.indexOf(inChars[i+1]);
			b[2] = CODES.indexOf(inChars[i+2]);
			b[3] = CODES.indexOf(inChars[i+3]);
			
			out[j++] = (byte) (b[0] << 2 | b[1] >> 4);
			
			 if (b[2] < 64) {
				 out[j++] = (byte) (b[1] << 4 | b[2] >> 2);
				 if (b[3] < 64){
					 out[j++] = (byte) (b[2] << 6 | b[3]);
				 }
			 }
		}
		return out;
	}
	
	private String encode(byte[] src){
		StringBuilder out = new StringBuilder();
		// take byte triplate and filter out six bits from left to right
		for (int i=0; i < src.length; i+=3){
			//
			// 1111 = F & 1100 = C - do ANDing
			int b = (src[i] & 0xFC) >> 2;
		    
			// get the code
		    out.append(CODES.charAt(b));
		    
		    // now get the next two bits in first byte
		    b = (src[i] & 0x03) << 4;
			
		    // check if we have next byte available or not
		    if (i+1 < src.length){
		    	// get the left 4 bits ( to form a 6 bits)
		    	b = b | (src[i+1] & 0xF0) >> 4;
		    	// get the code
		    	out.append(CODES.charAt(b));
		    	
		    	// get leftover 4 bits from this byte
		    	b = (src[i+1] & 0x0F) << 2; // shift it to left so that we may accommodate 2 bits from next byte
		    	
		    	// check if we have next byte available
		    	if (i+2 < src.length){
		    		// get the 2 bits - 1100 0000 - need to move it by 6 positions
		    		b = b | (src[i+2] & 0xC0) >> 6;
		    		out.append(CODES.charAt(b));
		    		
		    		// get next 6 bits - 0011 1111 - no need to move
		    		b = (src[i+2] & 0x3F);
		    		out.append(CODES.charAt(b));
		    	}else{
		    		out.append(CODES.charAt(b));
		    		out.append("=");
		    	}
		    }else{
		    	out.append("==");
		    }
		}
		return out.toString();
	}
	
	
}
