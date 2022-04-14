package reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMain {

	
	public static void main(String args[]) throws ClassNotFoundException {
		
		
		//Class Object
		Class c1 = Class.forName("java.lang.String");	 // get the class object by name (with package)
		
		Class c2 = Integer.class;
		
		Class c3 = Integer.TYPE; //Only wrapper Class
		
		Class c4 = PuppetClass.class;
		
		//class Methods
		
		System.out.println("METHODS"); 
		Method m[] = c4.getMethods();
		for(Method mElem : m) {
			System.out.println(mElem.toString()); //return method declaration
		}
		
		System.out.println("PARAMETERS"); 
		Class pTypes[] = m[0].getParameterTypes(); //Get method parameters types
		for(Class classElem :  pTypes) {
			System.out.println(classElem.toString()); 
		}
		
		System.out.println("EXCEPTION"); 
		Class eTypes[] = m[0].getExceptionTypes(); //Get method exception type
		for(Class classElem :  eTypes) {
			System.out.println(classElem.toString()); 
		}
		
		System.out.println("RETURN TYPE"); 
		Class retType = m[0].getReturnType(); //Get the return type
		System.out.println(retType); 

		Constructor constructors[] = c4.getDeclaredConstructors(); //get class constructors
		System.out.println("CONSTRUCTORS"); 
		for(Constructor cons : constructors) {
			System.out.println(cons.toString()); 

		}
		
		Field fieldlist[] = c4.getDeclaredFields(); //Get filed list
		System.out.println("FIELDS"); 
		for(Field field : fieldlist) {
			System.out.println(field.toString()); 
		}
		
		//FIELD OPERATION
		
		try {
			
			Field field2 = c4.getField("field2");//get a field with the passedname
			PuppetClass obj = new PuppetClass();
			
			System.out.println("before : " + obj.field2);
			field2.set(obj, "value");
			System.out.println("after : " + obj.field2);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		//ARRAY OPERATION
		
		System.out.println("ARRAY"); 

		try {
			
            Class cls = Class.forName("java.lang.String");
            
            Object arr = Array.newInstance(cls, 10); //create a string array of ten elements
            
            Array.set(arr, 5, "this is a test"); //Set the fith value to "this is a test"
            
            String s = (String)Array.get(arr, 5); //retrieve the fifth value
            
            System.out.println(s);
         }
         catch (Throwable e) {
        	 e.printStackTrace();        
        }
		
		//Is istance
		
		System.out.println("IS ISTANCE"); 
		boolean b1 = c1.isInstance(new Integer(1));
		System.out.println(b1); 
		boolean b2 = c2.isInstance(new Integer(1));
		System.out.println(b2); 
		
		//Constructor
		

	}
}
