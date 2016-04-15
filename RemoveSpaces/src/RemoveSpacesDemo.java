import java.util.Arrays;

public class RemoveSpacesDemo {

	public static void main(String[] args) {
		RemoveSpacesDemo demo = new RemoveSpacesDemo();
		String str  = " Hi, How are you? I still didn;t hear back from you.                  ok. not a problem.";
		System.out.println(demo.removeSpacesInPlace(str.toCharArray(), str.length()));
	}
	
	private char[] removeSpacesInPlace(char[] str, int length){
		// iterate over string, maintain a variable to keep a count of whitespaces
		// this variable should be reinitialized to zero whenever a non-whitespace character is found
		// in such case this value to be remembered and used to restore the non-white characters by that offset.
		if (length == 0 || str == null){
			return null;
		}
		
		
		int spacesCount = 0;
		for (int index = 0; index < length; index++){
			if (str[index] == ' ' ){
				
				
				spacesCount++; 
			}else{
				str[index-spacesCount] = str[index];
				//wasInNonWhitespace = true;
			}
		}
		str[length-spacesCount] = '\0';
		return Arrays.copyOf(str, length-spacesCount);
	}

}
