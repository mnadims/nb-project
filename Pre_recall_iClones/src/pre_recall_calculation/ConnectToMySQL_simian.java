package pre_recall_calculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToMySQL_simian {

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
            File f_iclones;
            BufferedReader br2 = null;

            String st2, query, filename;
            String[] splited1;
            int i, cur_clone_class=0;

            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            //replace_str="E:\\Research_USASK\\Clone_detection_tools_compare\\Simian\\bin\\ctags_Versions\\version-2\\";
            for (i = 1000; i <= 2000; i++) {
                f_iclones = new File("F:\\01Programming_Data\\Detected_Clone_output\\simian_freecol_output\\simian_freecol_out_v" + i + ".txt");
                br2 = new BufferedReader(new FileReader(f_iclones));
                st2 = br2.readLine();
                if (f_iclones.exists()) {
                    while ((st2 = br2.readLine()) != null) {
                        splited1 = st2.split("\\s+");
                        if (splited1[1].equals("Between")) {
                            filename=splited1[7];
                            filename=filename.substring(52);
                            /*if(i<10)
                                filename=filename.substring(48);
                            else if(i<100)
                                filename=filename.substring(49);
                            else if(i<1000)
                                filename=filename.substring(50);
                            else if(i<10000)
                                filename=filename.substring(51);
                            */
                            filename=filename.replaceAll("\\\\", "/");
                            //System.out.println(filename);
                            query = "INSERT INTO `simian_result` (`version`, `CloneClass`, `file_name`, `startline`, `endline`)"
                                    + " VALUES ('" + i + "', '" + cur_clone_class + "', '" + filename + "', '" + splited1[3] + "', '" + splited1[5] + "');";
                            System.out.println(query);
                            st.executeUpdate(query);
                        } else {
                            cur_clone_class++;
                        }
                    }
                }
                else System.out.println("Not found iClones_detected_v" + i + ".txt");
            }

            // iterate through the java resultset
            /*query="INSERT INTO `change_info` (`version`, `file_name`, `change_type`, `startline`, `endline`) VALUES ('15', 'general.h', 'c', '3', '3')";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                int firstName = rs.getInt("version");
                String lastName = rs.getString("file_name");
                String dateCreated = rs.getString("change_type");
                int isAdmin = rs.getInt("startline");
                int numPoints = rs.getInt("endline");

                // print the results
                System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
            }*/
            st.close();

            System.out.println("Successfully Connected to the database!");

        } catch (ClassNotFoundException e) {

            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }

    }
    
    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try
        {
           Integer.parseInt(s);

           // s is a valid integer

           isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
           // s is not an integer
        }

        return isValidInteger;
     }
}
