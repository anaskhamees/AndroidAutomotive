# OOP



#

#

#

#

```cpp
class DIO

{
    DIO(){
        x=3;
        y=0;
    }
	int x;
	int y;
    DIO_SetPinValue();

}DIO_AVR;

### 
```



### Constructor

#### 1. Default Constructor

- Compiler generate it implicitly 

#### 2. Default Constructor (explicit)

- programmer implememt the default constructor

#### 3. parametrize constructor

```cpp
DIO(int var1,int var2)
{
    x=var1;
    y=var2;
}

DIO AVR(3,4);
```

- usecase in embedded (pass a HW registers to a class)

  ```
  struct DIO* register;
  DIO(struct DIO* reg)
  {
  	register=reg
  }
  ```

  

- compiler will not generate default constructor , if there is parametrized constructor [ERROR]



### Destructor

```
~class();
```

- prevent memory leakage 

- call in 

  - end of scope }

  - delete keyword (delete the instance)

    - ```c++
      class *DIO=new DIO;
      delete DIO;
      ```

      

### Member Initialization

```
class DIO {
int x;
int y;
DIO:x(0),y(0){}
}
```

```cpp
DIO(int x,int y):x(x),y(y);
```



### Delegate Constructor

```cpp
class DIO {
    int x,y;
    DIO():x(0),y(0){}
    DIO(int x, int y):x(x),y(y){}
    /*Delegate*/
    DIO(int x,int y):DIO(){}

}
```

**ERROR**

```c++
class DIO {
    int x,y;
    DIO():DIO(0,4){}
    DIO(int x,int y):DIO(){}
}
```

- Who get initialized first  (based on the order of initialization)  task

  ```
  int x;
  int y;
  class DIO(int x,int y):x(x),y(y){}
  ```

  #### Copy Constructor
