
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			decrypt("flgxswdliefy");
	}
	
	private  static String decrypt(String word){
		
		if (null == word || word.isEmpty()){
			return null;
		}
		
		byte[] wordBytes = word.getBytes();
		
		int prevAddedPadding = 1;
		for (int i=0; i< word.length(); i++){
			int chara = wordBytes[i] - prevAddedPadding;
			
			// a
			while (chara < 97){
				chara += 26;
			}
			
			prevAddedPadding = chara + prevAddedPadding;
			
			System.out.print(Character.toChars(chara));
		}
		
		return null;
	}

}
