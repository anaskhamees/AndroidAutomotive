class IP 
{
	public static void main(String [] args)
	{
		if(args.length>1)
		{
			System.out.println(" Error: Enter only one parameter");
		}
		else
		{
			String [] ArrStr=args[0].split("\\.");
			for(int i=0;i<ArrStr.length;i++)
			{
				System.out.println(ArrStr[i]);
			}
		}
	
	}

}
