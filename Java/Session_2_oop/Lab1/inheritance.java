 class Parent {

	private int num1;
	private int num2;
	
	public void setterNum1(int n1)
	{
		num1=n1;
	}
	
	public void setterNum2(int n2)
	{
		num2=n2;
	}
	public int getterNum1()
	{
		return num1;
	}

	public int getterNum2()
	{
		return num2;
	}
	
	public Parent()
	{
		num1=0;
		num2=0;
		System.out.println("Parent Default Constructor");
	}
	public Parent(int n)
	{
		num1=n;
		num2=n;
		System.out.println("Parent 1-Parameterized Constructor");
	}
	
	public Parent(int n1,int n2)
	{
		num1=n1;
		num2=n1;
		System.out.println("Parent 2-Parameterized Constructor");
	}
	public int sum()
	{
		return num1+num2;
	} 
}

 class Child extends Parent{
	private int num3;
	
	public void setterNum3(int n)
	{
		num3=n;
	}	
	
	public int getNum3()
	{
		return num3;
	}
	public int sum()
	{
		return num3+super.sum();
	}
	
	Child()
	{
		num3=0;
		System.out.println("Child Default Constructor");
	}

	Child(int n1,int n2)
	{
		super(n1);
		num3=n2;
		System.out.println("Child 1-parameterized Constructor");
	}
	
	Child(int n1,int n2,int n3)
	{
		super(n1,n2);
		num3=n3;
		System.out.println("Child 2-parameterized Constructor");
	}
}

 class Main{
	public static void main(String[] args)
	{
		Child c=new Child(1,2,3);
		System.out.println("Sum of Numbers: "+c.sum());
	
	}
	
}
