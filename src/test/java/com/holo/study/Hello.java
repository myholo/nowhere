/**
 * 
 */
package com.holo.study;

/**
 * @author Holo
 *
 */
public class Hello {
	  Runnable r1 = () -> { System.out.println(this);};
	  Runnable r2 = () -> { System.out.println(toString()); };
	  Runnable r3 = new Runnable() {
		
		@Override
		public void run() {
			// this被覆盖
			System.out.println(this); 
			
		}
	};

	  public String toString() {  return "Hello, world"; }

	  public static void main(String... args) {
	    new Hello().r1.run();
	    new Hello().r2.run();
	    new Hello().r3.run();
	  }
	  
}