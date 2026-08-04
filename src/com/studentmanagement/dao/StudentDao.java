package com.studentmanagement.dao;
import com.studentmanagement.db.DBConnection;
import com.studentmanagement.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDao implements StudentDaoInterface {

    @Override
    public boolean insertStudent(Student s) {
        boolean flag=false;
        try{
            Connection con= DBConnection.createConnection();
            String query="insert into student_details(sname,clgname,city,percentage) value(?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,s.getName());
            pst.setString(2,s.getClgName());
            pst.setString(3,s.getCity());
            pst.setDouble(4,s.getPercentage());
            pst.executeUpdate();
            flag=true;

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int roll) {
        boolean flag=false;
        try{
            Connection con=DBConnection.createConnection();
            String query="delete from student_details where rollnum="+roll;
            PreparedStatement pst=con.prepareStatement(query);
            pst.executeUpdate();
            flag=true;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(int roll, String update, int ch, Student s) {
        int choice=ch;
        boolean flag=false;
        try{
            if(choice==1){
                Connection con=DBConnection.createConnection();
                String query="update student_details set sname=? where rollnum=?";
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1,update);
                ps.setInt(2,roll);
                ps.executeUpdate();
                flag=true;

            }
            else if(choice==2) {
            	Connection con = DBConnection.createConnection();
            	String query = "update student_details set clgname=? where rollnum=?";
            	PreparedStatement ps = con.prepareStatement(query);
            	ps.setString(1, update);
            	ps.setInt(2, roll);
            	ps.executeUpdate();
            	flag = true;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public void showAllStudent() {
        try{
            Connection con=DBConnection.createConnection();
            String query="select * from student_details";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("Roll Number: "+rs.getInt(1)+"\n" +
                        "Name: "+rs.getString(2)+"\n" +
                        "Clg Name: "+rs.getString(3)+"\n" +
                        "City: "+rs.getString(4)+"\n" +
                        "Percentage: "+rs.getDouble(5));
                System.out.println("----------------------------------");

            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public boolean showStudentById(int roll) {
        boolean flag=false;
        try{

            Connection con=DBConnection.createConnection();
            String query="select * from student_details where rollnum="+roll;
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("Roll Number: "+rs.getInt(1)+"\n" +
                        "Name: "+rs.getString(2)+"\n" +
                        "Clg Name: "+rs.getString(3)+"\n" +
                        "City: "+rs.getString(4)+"\n" +
                        "Percentage: "+rs.getDouble(5));
                //System.out.println("----------------------------------");
                flag=true;

            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return flag;
    }
    
    @Override
    public void filterByPercentage(double percentage) {

        try {
            Connection con = DBConnection.createConnection();

            String query = "SELECT * FROM student_details WHERE percentage >= ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, percentage);

            ResultSet rs = ps.executeQuery();

            System.out.println("===== Students with Percentage >= " + percentage + " =====");

            while(rs.next()) {
                System.out.println("------------------------------");
                System.out.println("Roll Number : " + rs.getInt(1));
                System.out.println("Name : " + rs.getString(2));
                System.out.println("Clg Name : " + rs.getString(3));
                System.out.println("City : " + rs.getString(4));
                System.out.println("Percentage : " + rs.getDouble(5));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void generateReport() {
      
      try {
        Connection con = DBConnection.createConnection();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "SELECT COUNT(*) AS totalStudents, " +
            "MAX(percentage) AS highestPercentage, " +
            "MIN(percentage) AS lowestPercentage, " +
            "AVG(percentage) AS averagePercentage " +
            "FROM student_details");

        if(rs.next()) {
            System.out.println("\n========== STUDENT REPORT ==========");
            System.out.println("Total Students      : " + rs.getInt("totalStudents"));
            System.out.println("Highest Percentage  : " + rs.getDouble("highestPercentage"));
            System.out.println("Lowest Percentage   : " + rs.getDouble("lowestPercentage"));
            System.out.println("Average Percentage  : " + rs.getDouble("averagePercentage"));
            System.out.println("====================================");
        }
     }   
     catch (Exception ex){
          ex.printStackTrace();
     }  
}  
}

