package com.study;
public class Test { 
    Person person = new Person("Test"); 
    static{ 
        System.out.println("test static"); 
    } 
      
    public Test() { 
        System.out.println("test constructor"); 
    } 
      
    public static void main(String[] args) { 
        new MyClass();
        
        
        /* 
         * Ö´ÐÐ½á¹û
         * test static
         * myclass static
         * person static
         * person Test
         * test constructor
         * person MyClass
         * myclass constructor
         */
    } 
} 
  
class Person{ 
    static{ 
        System.out.println("person static"); 
    } 
    public Person(String str) { 
        System.out.println("person "+str); 
    } 
} 
  
  
class MyClass extends Test { 
    Person person = new Person("MyClass"); 
    static{ 
        System.out.println("myclass static"); 
    } 
      
    public MyClass() { 
        System.out.println("myclass constructor"); 
    } 
} 
