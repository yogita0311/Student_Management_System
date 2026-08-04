package com.studentmanagement.main;

import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.dao.StudentDaoInterface;
import com.studentmanagement.model.Student;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);

	        StudentDaoInterface dao=new StudentDao();
	        
	        System.out.println("Welcome to Student Management application");
	        while(true){
	            System.out.println("\n1.Add Student" +
	                    "\n2.Show All Students" +
	                    "\n3.Search Student by Roll Number" +
	                    "\n4.Delete Student"+
	                    "\n5.Update Student" +
	                    "\n6.Filter Students by Percentage" +
	                    "\n7.Generate Report" +
	                    "\n8.Exit");
	            System.out.println("Enter your choice:");
	            int ch=sc.nextInt();
	            switch (ch){
	                case 1:
	                    System.out.println("Add Student");
	                    System.out.println("Enter student name:");
	                    String name=sc.next();
	                    System.out.println("Enter clg name:");
	                    String clgName=sc.next();
	                    System.out.println("Enter city:");
	                    String city=sc.next();
	                    System.out.println("Enter percentage:");
	                    double percentage=sc.nextDouble();
	                    Student st=new Student(name,clgName,city,percentage);
	                    boolean ans=dao.insertStudent(st);
	                    if(ans)
	                        System.out.println("Record inserted successfully!");
	                    else
	                        System.out.println("Something went wrong, please try again.");

	                    break;
	                case 2:
	                    System.out.println("Show All Students ");
	                    dao.showAllStudent();

	                    break;
	                case 3:
	                    System.out.println("Search Student by Roll Number");
	                    System.out.println("Enter roll number:");
	                    int roll=sc.nextInt();
	                  boolean f=  dao.showStudentById(roll);
	                  if(!f)
	                      System.out.println("Student with this roll number is not available in our system");

	                    break;
	                case 4:
	                    System.out.println("Delete Student");
	                    System.out.println("Enter roll number to delete:");
	                    int rollnum=sc.nextInt();
	                    boolean ff=dao.delete(rollnum);
	                    if(ff)
	                        System.out.println("Record deleted successfully!");
	                    else
	                        System.out.println("Something went wrong.");
	                    break;
	                case 5:
	                    System.out.println("Update Student");
	                    System.out.println("\n1.Update name\n2.Update clg name");
	                    System.out.println("Enter your choice:");
	                    int choice=sc.nextInt();
	                    if(choice==1){
	                        System.out.println("Enter roll number:");
	                        int rnum=sc.nextInt();
	                        System.out.println("Enter new name:");
	                        String sname=sc.next();
	                        Student std=new Student();
	                        std.setName(sname);
	                      boolean flag=  dao.update(rnum,sname,choice,std);
	                      if(flag)
	                          System.out.println("Name updated successfully!");
	                      else
	                          System.out.println("Something went wrong.");
	 }
	                    else if(choice==2){
	                        System.out.println("Enter roll number:");
	                        int rnum=sc.nextInt();
	                        System.out.println("Enter new clg name:");
	                        String newClgname=sc.next();
	                        Student std=new Student();
	                        std.setClgName(newClgname);
	                      boolean flag=  dao.update(rnum,newClgname,choice,std);
	                      if(flag)
	                          System.out.println("Clg name updated successfully!");
	                      else
	                          System.out.println("Something went wrong.");
	 }
	                    break;
	                case 6:
	                	System.out.println("Enter minimum percentage:");
	                	double per = sc.nextDouble();
	                	dao.filterByPercentage(per);
	                	break;    
	                case 7:
	                	dao.generateReport();
	                	break;
	                case 8:
	                    System.out.println("Thank You for using Student Management Application!");
	                    System.exit(0);
	                default:
	                    System.out.println("Please enter valid choice.");
	            }
	        }
	    }
}
