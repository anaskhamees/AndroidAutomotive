class IP
{
	public static void main(String[] args)
	{
		String ip=args[0];
		int Endindex=0;
		int Startindex=0;
		if(args.length>1)
		{
			System.out.println(" Error: Enter only one parameter ");
		}
		else 
		{
			while(true)
			{
				 Endindex=ip.indexOf('.',Startindex);
				 if(Endindex==-1)
				 {	
				 		System.out.println(ip.substring(Startindex));
				 		System.out.println("################################");
				 		System.out.println(" The String is end ");
				 		break;
				 }
				 else
				 {
					 System.out.println(ip.substring(Startindex,Endindex));
				 	 Startindex=Endindex+1;
				 }
			}
		}
	} 


}
