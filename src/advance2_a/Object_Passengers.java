/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advance2_a;

import java.sql.*;

import java.util.logging.Level;
import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Object_Passengers {
    int no;
    Date date;
    String TicketNo;
    String FromStation;
    String ToStation;
    String Status;

    public Object_Passengers(int no, Date date, String TicketNo, String FromStation, String ToStation, String Status) {
        this.no = no;
        this.date = date;
        this.TicketNo = TicketNo;
        this.FromStation = FromStation;
        this.ToStation = ToStation;
        this.Status = Status;
    }

    public Object_Passengers() {
    }
        
    public String getFromStation() {
        return FromStation;
    }

    public void setFromStation(String FromStation) {
        this.FromStation = FromStation;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getTicketNo() {
        return TicketNo;
    }

    public void setTicketNo(String TicketNo) {
        this.TicketNo = TicketNo;
    }

    public String getToStation() {
        return ToStation;
    }

    public void setToStation(String ToStation) {
        this.ToStation = ToStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    public int getIDPass(){
        int t = 0;
        try {
             SQLConnection con = new SQLConnection();
             Connection connect = con.Connect();
             Statement st = connect.createStatement();
             ResultSet rs = st.executeQuery("select count(*) from Passengers");
             while(rs.next()){
                t = rs.getInt(1);
             }
             if(t != 0){
                 t = 0;
                 ResultSet result = st.executeQuery("select * from Passengers");
                 while(result.next()){
                     if(result.getInt(1) > t){
                         t = result.getInt(1);
                     }
                 }
             }
        } catch (SQLException ex) {
            Logger.getLogger(Object_Passengers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t + 1;
    }
    
    public void updatePassengers(){
        try {
            SQLConnection con = new SQLConnection();
            Connection connect = con.Connect();
            String sql = "insert into Passengers (No, Date, TicketNo, FromStation, ToStation, Status) "
                    + "values (?, ?, ?, ? ,?, ?)";
            PreparedStatement prest = connect.prepareStatement(sql);
            prest.setInt(1, getNo());
            prest.setDate(2, getDate());
            prest.setString(3, getTicketNo());
            prest.setString(4, getFromStation());
            prest.setString(5, getToStation());
            prest.setString(6, getStatus());
            prest.executeUpdate();
            System.out.println("Update finish!");
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Object_Passengers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
