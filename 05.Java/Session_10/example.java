interface MyValue
{
	double getValue();
}


class Main()
{
	public static void main(String [] args)
	{
		MyValue ref;
		ref=new MyValue()
		{
			public double getValue()
			{
				return 0.1;
			
			}
		
		};
	double result=ref.getValue();
	
	}

}
