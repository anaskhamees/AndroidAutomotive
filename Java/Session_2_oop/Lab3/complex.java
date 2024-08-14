class Complex 
{
	private int real;
	private int img;
	public void setReal(int r)
	{
		real=r;
	}
	public void setImg(int i)
	{
		img=i;
	}
	public int getReal()
	{
		return real;
	}
	public int getImg()
	{
		return img;
	}
	public Complex()
	{
		real=0;
		img=0;
		System.out.println("Default Constructor");
	}
	public Complex(int n)
	{
		real=n;
		img=n;
		System.out.println("1-Parameterized Constructor");
	}
	
	public Complex(int r,int i)
	{
		real=r;
		img=i;
		System.out.println("2-Parameterized Constructor");
	}
	
	public static Complex AddComplex(Complex c1,Complex c2)
	{
		Complex comp=new Complex();
		comp.real=c1.real+c2.real;
		comp.img=c1.img+c2.img;
		return comp;
	}
	
	public static Complex SubComplex(Complex c1,Complex c2)
	{
		Complex comp=new Complex();
		comp.real=c1.real-c2.real;
		comp.img=c1.img-c2.img;
		return comp;
	}
	
	public void print()
	{
		if(img>0)
			System.out.println(real+ "+"+img+"i");
		else if(img<0)
			System.out.println(real+""+img+"i");
		else
			System.out.println(real);
	}
}

class Main
{
	static public void main(String[] args)
	{
		Complex c=new Complex(5,5);
		c.print();
		Complex c1=new Complex(1,1);
		Complex result=Complex.SubComplex(c1,c);
		result.print();
	}

}
