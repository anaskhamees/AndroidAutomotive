class Starts {
	public static void main (String[] args){
		String str1 = new String("*");
		String str2 = new String("          *");
		String string_1=str1;
		String string_2=str2;
		
	for (int i =0;i<5;i++)
	{
		System.out.println(string_1 + string_2);
		string_1+=str1;
		string_2=string_2.substring(2);
		string_2+=" *";
	}		
	}	
}
