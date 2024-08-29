abstract class Shape 
{
	private double dim1;
	public void setterDim1(double n){
		dim1=n;
	}
	public double getterDim1()
	{
		return dim1;
	}
	abstract public double calcArea();
	
	public Shape()
	{
		dim1=0;
		System.out.println(" Shape Default Constructor");
	}
	public Shape(double n)
	{
		dim1=n;
		System.out.println(" Shape 1-Parameterized Constructor");
	}
}

class Triangle extends Shape
{
	private double dim2;
	public Triangle()
	{

		dim2=0;
		System.out.println(" Triangle Default Constructor ");
	}
	
	public Triangle(double n1,double n2)
	{
		super(n1);
		dim2=n2;
		System.out.println(" Triangle 1-Parameterized Constructor ");
	}
	
	public double calcArea()
	{
		return 0.5*getterDim1()*dim2;
	}
}

class Rectangle extends Shape
{
	private double dim2;
	public Rectangle()
	{
		super();
		dim2=0;
		System.out.println(" Rectangle Default Constructor ");
	}
	
	public Rectangle(double n1,double n2)
	{
		super(n1);
		dim2=n2;
		System.out.println(" Rectangle 1-Parameterized Constructor ");
	}
	
	public double calcArea()
	{
		return getterDim1()*dim2;
	}

}

class Circle extends Shape
{
	public Circle()
	{
		super();
		System.out.println(" Circle Default Constructor ");
	}
	
	public Circle(double n)
	{
		super(n);
		System.out.println(" Circle 1-Parameterized Constructor ");
	}
	
	public double calcArea()
	{
		return getterDim1()*getterDim1()*3.14;
	}

}

class Main
{

	public double sumArea(Shape s1,Shape s2,Shape s3)
	{
		return s1.calcArea()+s2.calcArea()+s3.calcArea();
	}
	public static void main(String[] args)
	{
		shape t=new Triangle(4,4);
		System.out.println(" Triangle Area: "+t.calcArea());
		System.out.println("###################################");
		Rectangle r=new Rectangle(4,4);
		System.out.println(" Rectangle Area: "+r.calcArea());
		System.out.println("###################################");
		Circle c=new Circle(5);
		System.out.println(" Circle Area: "+c.calcArea());
		System.out.println("###################################");
		Main m=new Main();
		System.out.println(" Sum Area: "+m.sumArea(t,r,c));
	}
}
