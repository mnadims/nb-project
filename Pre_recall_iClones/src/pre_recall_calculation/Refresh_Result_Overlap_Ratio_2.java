/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pre_recall_calculation;
import java.io.BufferedReader;
import java.io.File;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 *
 * @author MD NADIM
 */
public class Refresh_Result_Overlap_Ratio_2 {
    public static void main(String[] args) throws Exception {

        Connection conn = null;
        try {

            // Load the MySQL JDBC driver
            String driverName = "com.mysql.cj.jdbc.Driver";

            Class.forName(driverName);

            // Create a connection to the database
            String serverName = "localhost";

            String schema = "freecol";

            String url = "jdbc:mysql://" + serverName + "/" + schema;

            String username = "root";

            String password = "";

            /**
             * ****************************************************************
             */
            File f_nicad;
            BufferedReader br2 = null;

            String query, in_ids="", filename, cur_clone_class, cur_filename="", nxt_filename="", del_qr="";
            String[] splited1, fileparts;
            int i, overlapped=0, total=0, b1=0, e1=0, b2=0, e2=0, cur_version=0, cur_class=0, nxt_class=0, nxt_version=0, cur_id=0, nxt_id=0, flg;
            float ratio=0;
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            /*for(i=1;i<775;i++){
                query="INSERT INTO `clone_count_versions` (`version`, `nicad_5`, `iclones_0_2`, `deckard_2_0`, `simcad_2_2`, `simian_2_5_10`) VALUES ('"+i+"', '0', '0', '0', '0', '0')";
                //System.out.println(query);
                st.executeUpdate(query);                
            }*/
            for(i=1000;i<=1000;i++){
                flg=0;
                query="SELECT * FROM `simian_result` WHERE `id` NOT IN (SELECT `id` FROM `simian_result_rr`) ORDER BY `version`, `CloneClass`, `file_name`, `startline`, `endline`";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);                
                if(rs.next()){
                    cur_id=rs.getInt("id");
                    cur_class=rs.getInt("CloneClass");
                    cur_filename=rs.getString("file_name");
                    b1=rs.getInt("startline");
                    e1=rs.getInt("endline");
                }

                while(rs.next()){ 
                    nxt_id=rs.getInt("id");
                    nxt_class=rs.getInt("CloneClass");
                    nxt_filename=rs.getString("file_name");
                    b2=rs.getInt("startline");
                    e2=rs.getInt("endline");
                    if(nxt_filename.equals(cur_filename) & nxt_class==cur_class && ((e2>=b1 && e2<=e1) || (b2>=b1 && b2<=e1) || (b2<b1 && e2>e1))){
                        overlapped=-1;
                        total=-1;
                        if((b2>=b1 && e2<=e1) || (b2<b1 && e2>e1)){
                            //System.out.println("Totally Absorbing!!!");
                        }
                        else if(e2>=b1 && e2<=e1){                           
                            overlapped=e2-b1+1;
                            //System.out.println("Overlapped: "+e2+" - "+b1+"+ 1 :"+overlapped);
                            total=e1-b2+1;
                            //System.out.println("Total: "+e1+" - "+b2+"+ 1 :"+total);                                                     
                            
                        }
                        else if(b2>=b1 && b2<=e1){                            
                            overlapped=e1-b2+1;
                            //System.out.println("Overlapped: "+e1+" - "+b2+"+ 1 :"+overlapped);
                            total=e2-b1+1;
                            //System.out.println("Total: "+e2+" - "+b1+"+ 1 :"+total);
                        }
                        else{
                            System.out.println("########### OMG NEW CASE ###########");
                        }
                        ratio=(float)overlapped/(float)total;
                        //System.out.println("*** "+"Overlapped: "+overlapped+" Total: "+ total);                        
                        //if(ratio>0.01 && overlapped>0 && total>0){
                        if(overlapped>0){
                            System.out.println("Ratio: "+ratio);
                            System.out.println("Current: "+ cur_filename+", "+b1+", "+e1);
                            System.out.println("Next: "+nxt_filename+", "+b2+", "+e2);
                            System.out.println("*** "+"Overlapped: "+overlapped+" Total: "+ total);

                            System.out.println("--------XXX--------");
                        }
                        
                        
//                        if(flg==0){
//                            in_ids+=cur_id+", "+nxt_id;
//                            flg=1;
//                        }
//                        else 
//                            in_ids+=", "+cur_id+", "+nxt_id;
                    } 
                    cur_id=nxt_id;
                    cur_class=nxt_class;
                    cur_filename=nxt_filename;
                    b1=b2;
                    e1=e2;

                }                
//                del_qr="DELETE FROM `simian_result_rr` WHERE `id` IN ("+in_ids+")";
//                System.out.println(del_qr);
//                st2.executeUpdate(del_qr);
//                in_ids="";
            }
            st.close();
            st2.close();

            System.out.println("Successfully Connected to the database!");

        } catch (ClassNotFoundException e) {

            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }

    }  
    
}
