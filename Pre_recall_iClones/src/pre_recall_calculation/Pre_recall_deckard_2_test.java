package pre_recall_calculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*class Store_change{
    String filename;
    int startline;
    int endline;
}*/

public class Pre_recall_deckard_2_test {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        try {

            // Load the MySQL JDBC driver
            String driverName = "com.mysql.cj.jdbc.Driver";

            Class.forName(driverName);

            // Create a connection to the database
            String serverName = "127.0.0.1";

            String schema = "freecol";

            String url = "jdbc:mysql://" + serverName + "/" + schema;

            String username = "root";

            String password = "";

            /**
             * ****************************************************************
             */
            File cleaned_nicad;
            BufferedReader br2 = null;
            Store_change[] stored_change=new Store_change[1000];
            String st2, qr_detected, qr_actual,qr_change_count, pr_filename,cur_filename,clone_results_table,qr_insert;
            String[] splited1;
            int b1,e1,ac_b1, ac_e1, pr_startline, pr_endline,cur_startline,cur_endline, chng_detect,chng_actual,used_clone,total_clone,counted,change_ptr;
            int k, duplicate=0, b2, e2, b, e, i, a;

            conn = DriverManager.getConnection(url, username, password);
            Statement stm1 = conn.createStatement();
            Statement stm2 = conn.createStatement();
            Statement stm3 = conn.createStatement();
            Statement stm4 = conn.createStatement();

            cleaned_nicad = new File("data_files/freecol_changes.txt");
            clone_results_table="deckard_2_0_result";
            br2 = new BufferedReader(new FileReader(cleaned_nicad));
            int pr_version=0, total_change=-1;
            while ((st2 = br2.readLine()) != null) {
                splited1 = st2.split("\\s+");
                b1=Integer.parseInt(splited1[3]);
                e1=Integer.parseInt(splited1[4]);
                if(pr_version!=Integer.parseInt(splited1[0])){
                    qr_change_count="SELECT COUNT(*) AS num_change FROM `change_info` WHERE `version`="+splited1[0];
                    ResultSet rs_change_count=stm1.executeQuery(qr_change_count);
                    if(rs_change_count.next())
                        total_change=rs_change_count.getInt("num_change");
                    else total_change=-1;
                    pr_version=Integer.parseInt(splited1[0]);
                }
                
                qr_detected="SELECT * FROM `"+clone_results_table+"` WHERE "
                        +"`version`="+splited1[0]+" AND `CloneClass` IN (SELECT `CloneClass` FROM `"+clone_results_table+"` WHERE "
                        +"`version`="+splited1[0]+" AND `file_name`='"+splited1[1]+"' AND ((`endline`>="+b1+" AND `endline`<="+e1+") "
                        +" OR (`startline`>="+b1+" AND `startline`<="+e1+") OR (`startline`<"+b1+" AND `endline`>"+e1+"))) AND "
                        +" `id` NOT IN (SELECT `id` FROM `"+clone_results_table+"` WHERE `version`="+splited1[0]+" AND `file_name`='"+splited1[1]+"' AND "
                        +" ((`endline`>="+b1+" AND `endline`<="+e1+") OR (`startline`>="+b1+" AND `startline`<="+e1+") OR "
                        +" (`startline`<"+b1+" AND `endline`>"+e1+"))) ORDER BY `file_name`, `startline`, `endline`";
                System.out.println(qr_detected);
                ResultSet rs_detected=stm1.executeQuery(qr_detected);
                pr_filename="";
                b2=0;
                e2=0;
                String[][] predicted=new String[50000][6];
                i=0;
                while (rs_detected.next()) {
                    cur_filename=rs_detected.getString("file_name");
                    b1=rs_detected.getInt("startline");
                    e1=rs_detected.getInt("endline");
                    if(pr_filename.equals(cur_filename) && ((e2>=b1 && e2<=e1) || (b2>=b1 && b2<=e1) || b2<b1 && e2>e1)){
                        if(e2>=b1 && e2<=e1){
                            b=b2;
                            e=e1;
                        }
                        else if(b2>=b1 && b2<=e1){
                            b=b1;
                            e=e2;
                        }
                        else{
                            b=b2;
                            e=e2;
                        }
                        
                        //System.out.println(cur_filename+", "+b+", "+e);
                        pr_filename=cur_filename;
                        b2=b;
                        e2=e;
                        predicted[i-1][0]=rs_detected.getString("id");
                        predicted[i-1][1]=rs_detected.getString("version");
                        predicted[i-1][2]=rs_detected.getString("CloneClass");
                        predicted[i-1][3]=cur_filename;
                        predicted[i-1][4]=Integer.toString(b);
                        predicted[i-1][5]=Integer.toString(e);
                    }
                    else{
                    
                        pr_filename=cur_filename;
                        b2=b1;
                        e2=e1;
                        predicted[i][0]=rs_detected.getString("id");
                        predicted[i][1]=rs_detected.getString("version");
                        predicted[i][2]=rs_detected.getString("CloneClass");
                        predicted[i][3]=cur_filename;
                        predicted[i][4]=Integer.toString(b1);
                        predicted[i][5]=Integer.toString(e1);
                        i++;
                    }
                    
                }
//                for(a=0;a<i;a++){
//                    System.out.println(predicted[a][3]+", "+predicted[a][4]+", "+predicted[a][5]);
//                }
                pr_filename="";
                pr_startline=0;
                pr_endline=0;
                total_clone=0;
                chng_detect=0;
                used_clone=0;
                change_ptr=0;
                for(a=0;a<i;a++){
                    //System.out.println(rs_detected.getString("file_name")+", "+rs_detected.getInt("startline")+", "+rs_detected.getInt("endline"));
                    cur_filename=predicted[a][3];
                    cur_startline=Integer.parseInt(predicted[a][4]);
                    cur_endline=Integer.parseInt(predicted[a][5]);
                    if(!(pr_filename.equals(cur_filename) && pr_startline==cur_startline && pr_endline==cur_endline)){
                        total_clone++;
                        qr_actual="SELECT * FROM `change_info` WHERE `version`="+splited1[0]+" AND `file_name`='"+cur_filename+"'";
                        //System.out.println(qr_actual);
                        ResultSet rs_actual=stm2.executeQuery(qr_actual);
                        counted=0;
                        while (rs_actual.next()) {                            
                           ac_b1=rs_actual.getInt("startline");
                           ac_e1=rs_actual.getInt("endline");
                           if((cur_endline>=ac_b1 &&  cur_endline<=ac_e1)|| (cur_startline>=ac_b1 &&  cur_startline<=ac_e1) || (cur_startline<ac_b1 && cur_endline>ac_e1)){
                               duplicate=0;                                   
                               if(change_ptr>0){
                                   for(k=0;k<change_ptr;k++){
                                       if(stored_change[k].filename.equals(cur_filename) && stored_change[k].startline==ac_b1 && stored_change[k].endline==ac_e1){
                                           duplicate=1;
                                           break;
                                       }
                                           
                                   }
                               }
                               if(duplicate==0){                                   
                                   stored_change[change_ptr]=new Store_change();
                                   stored_change[change_ptr].filename=cur_filename;
                                   stored_change[change_ptr].startline=ac_b1;
                                   stored_change[change_ptr].endline=ac_e1;
                                   change_ptr++;
                                   chng_detect++;  
                                   System.out.println(cur_filename+", "+ac_b1+", "+ac_e1);
                               }
                               if(counted==0){
                                   used_clone++;
                                   counted=1;
                               }
                           }
                        }
                        pr_filename=cur_filename;
                        pr_startline=cur_startline;
                        pr_endline=cur_endline;
                        
                    }                    
                }                
                qr_insert = "INSERT INTO `pre_recall_deckard_2_0_test` (`change_id`,`version`, `change_detect`, `change_total`, `clone_used`, `clone_pr_total`) "
                        + " VALUES ((SELECT `id` FROM `change_info` WHERE `version`='"+splited1[0]+"' AND `file_name`='"+splited1[1]+"' AND `startline`='"+splited1[3]+"' AND `endline`='"+splited1[4]+"'),"
                        +"'"+splited1[0]+"', '"+chng_detect+"', '"+(total_change-1)+"', '"+used_clone+"', '"+total_clone+"')";
                System.out.println(qr_insert);
                stm4.executeUpdate(qr_insert);
                System.out.println(splited1[0]+", "+splited1[1]+", "+splited1[3]+", "+splited1[4]+": "+"Detected Change: "+chng_detect+" Total Change: "+(total_change-1)+" Used Clone(s): "+used_clone+" Predicted Clone(s): "+total_clone);
                System.out.println("*****************");
            }
            
            stm1.close();  
            stm2.close();     
            stm3.close();  
            stm4.close();         

        } catch (ClassNotFoundException e) {

            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }

    }   
    
}
