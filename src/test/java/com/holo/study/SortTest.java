/**
 * 
 */
package com.holo.study;

/**
 * @author Holo
 * 简单的复习一下排序算法
 */
public class SortTest {
	public static void sort(int a[], int low, int high){    
	    int i=low;    
	    int j=high;    
	    int key=a[low];  
	    String bString="";

	    if (low < high){    
	        while(i<j){ // 此处的while循环结束，则完成了元素key的位置调整    J6 I2 J5 I3
	            while(i<j&&key<=a[j]){    // 0 6 2 5 3
	                j--;    
	            }    
	            a[i]=a[j];    
	            while(i<j&&key>=a[i]){    
	                i++;    
	            }    
	            a[j]=a[i];    
	            a[i]=key;  //此处不可遗漏  
	        }     
	        sort(a,low,i-1);    
	        sort(a,i+1,high);    
	    }    
	    for(int c=0;c<a.length;c++){
	    	System.err.println(bString+=a[c]);
	    }
	}
	public static void main(String[] args) {
		int a[]={3,5,7,8,9,22,11,2,1};
		sort(a, 0, 8);
	}
}
