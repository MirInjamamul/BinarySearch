
package searchmap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class SearchMap {
    static int arrLength = 0;

    private static boolean sequentialSearch(int[] arr, int id) {
        for(int i = 0;i<arr.length;i++){
            if(arr[i]==id)
                return true;
        }
        return false;
    }

    // Student Model 
    class student{
        // attributes
        String student_name;
        int student_age;
        
        //default Constructor
        public student(String student_name , int student_age) {
            this.student_name = student_name;
            this.student_age = student_age;
        }       
    }
    
    Hashtable<Integer , List<student>> ht ; 
    
    //Initialize data structure here
    
    public SearchMap(){
        ht = new Hashtable<>();
    }
    
    // data insert function
    public int set(int arr[], int arrLength, int student_id , String student_name , int student_age, int capacity){
        
        if(arrLength >= capacity)
            return arrLength;
        
        int i;
        for(i = arrLength-1;(i>=0 && arr[i] > student_id); i--){
            arr[i+1] = arr[i];
        }
        
        arr[i+1] = student_id;
        
        student current = new student(student_name, student_age);
        List<student> list = ht.getOrDefault(student_id, new ArrayList<>());
        list.add(current);
        ht.put(student_id, list);
        
        return (arrLength+1);
    }
    
    // data search function
    public void get(int student_id){
        List<student> list = ht.get(student_id);
        
        System.out.println("Student Name : "+list.get(0).student_name);
        System.out.println("Student Age : "+list.get(0).student_age);
    }
    
    public int binarySearch(int arr[], int low, int high, int key){
        if(high < low)
            return -1;
        int mid = (low + high)/2;
        
        if(key == arr[mid])
            return mid;
        if(key > arr[mid])
            return binarySearch(arr, mid+1, high, key);
        return binarySearch(arr, low, (mid-1), key);
    }
    
    // data delete function
    public int delete(int arr[], int n, int student_id){
        ht.remove(student_id);
        
        //find position of element to be deleted
        int position = binarySearch(arr, 0, n-1, student_id);
        
        if(position == -1){
            System.err.println("Element not found");
            return n;
        }
        
        //Deleting Element
        int i;
        for(i=position;i<n-1;i++){
            arr[i] = arr[i+1];
        }
        
        return n-1;
    }
    
    public static void main(String[] args) {
        int arr[] = new int[1000];
        int capacity = 1000;
        // TODO code application logic here
        SearchMap sm = new SearchMap();
        Scanner input = new Scanner(System.in);
        int student_id;
        String student_name;
        int student_age;
        boolean execute = true;
        
        while(execute){
            System.out.println(" 1.Input \n 2.Binary Search \n 3.Delete \n 4. Sequential Search \n 5. Exit");
            System.out.print("Enter your choice : ");
            int choice = input.nextInt();
            
            switch(choice){
                case 1 :
                    System.out.print("Enter the Student id : ");
                    student_id = input.nextInt();
                    
                    System.out.print("Enter the Student name : ");
                    student_name = input.next();
                    
                    System.out.print("Enter the Student Age : ");
                    student_age = input.nextInt();
                    
                    arrLength = sm.set(arr, arrLength, student_id , student_name , student_age, capacity);
                    System.out.println("Data has been inserted ... ");
                    break;
                    
                case 2 :
                    System.out.print("Enter the Student id : ");
                    int searched_id = input.nextInt();
                    
                    int position = sm.binarySearch(arr, 0, arrLength-1, searched_id);
                    
                    if(position != -1){
                        sm.get(searched_id);
                    }else{
                        System.err.println("Data Not Found");
                    }
                    break;
                    
                case 3 :
                    System.out.print("Enter the Student id : ");
                    int deleted_id = input.nextInt();
                    
                    arrLength = sm.delete(arr, arrLength, deleted_id);
                    System.out.println("Data has been deleted ... ");
                    break;
                case 4 :
                    System.out.print("Enter the Student id : ");
                    int id = input.nextInt();
                    boolean isExist = sequentialSearch(arr,id);
                    
                    if(isExist){
                        System.out.println("Number Found.");
                    }else{
                        System.out.println("Number Not Found.");
                    }
                    break;
                case 5 :
                    execute = false;
                    System.out.println("Bye Bye");
                    break;
            }
        }
    }
}
