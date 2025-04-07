/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import javax.swing.JOptionPane;

public class Tables {
    public static void main(String[] args){
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            JOptionPane.showMessageDialog(null, "Table créée avec succès");
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}