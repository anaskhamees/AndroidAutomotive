import java.util.StringTokenizer;

class IP
{
	public static void main(String [] args)
	{
		String str=args[0];
		if(args.length>1)
		{
			System.out.println("ERROR, Enter Onlly one parameter");
		}
		else
		{
			StringTokenizer t =new StringTokenizer(str,".");
		while(t.hasMoreTokens())
			{
				System.out.println(t.nextToken());
			}
		}
		

	}

}
