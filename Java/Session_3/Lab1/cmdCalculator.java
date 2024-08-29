class Calculator
{

	public static void main(String[] args)
	{
		if(args.length>3)
		{
			System.out.println(" Error:: You Entered More than 2 Numbers !!");
		}
		double num1=Double.parseDouble(args[0]);
		double num2=Double.parseDouble(args[2]);
		String operator=args[1];
		
		else
		{
			switch(operator)
			{
				case "+":
					System.out.println(" Num Add : "+(num1+num2));
					break;
				case "-":
					System.out.println(" Num Sub : "+(num1-num2));
					break;
				case "x":
					System.out.println(" Num Multi : "+(num1*num2));
					break;
				case "/":
					if(num2!=0)
					{
						System.out.println(" Num div : "+(num1/num2));
					}
					else
					{
						System.out.println("Error:: Division on Zeroooo !!");
					}
					break;
				default: 
					System.out.println("Error :: Non-Provided Operator !!");
					break;
			}	
		}
			
		
	
	}
	
}

