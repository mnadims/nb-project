/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iClones_run;
import java.io.*;

/**
 *
 * @author MD NADIM
 */
public class CloneDetection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        for(int i=1;i<=1700;i++){

            String command="java -jar \"E:/Research_USASK/Clone_detection_tools_compare/Running_Differnet_Tools/iClones/iclones-0.2/iclones.jar\""

                    +" -minblock 30 -minclone 50 -language java "

                    +" -input \"E:/Research_USASK/Clone_detection_tools_compare/Programming_Data/carol/repository/version-"+i+"\""

                    +" -output \"E:/Programming/Netbeans_Projects/CloneDetection_iClones/iclones_carol_output/iclones_carol_out_v"+i+".txt\"";

            ProcessBuilder builder = new ProcessBuilder(

                "cmd.exe", "/c", command);

            builder.redirectErrorStream(true);

            Process p = builder.start();

            

            // DISPLAYING IN CONSOLE 

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line=r.readLine();

            while (line != null) {            

                //System.out.println(line);

                line=r.readLine();

            }

            System.out.println(i+": "+command);

        }
        /*String path="java -jar E:/Research_USASK/Clone_detection_tools_compare/Simian/bin/simian-2.5.10.jar"
                +" -threshold=5 E:/Research_USASK/Clone_detection_tools_compare/Simian/bin/ctags_Versions/version-2/*.c"
                +" E:/Research_USASK/Clone_detection_tools_compare/Simian/bin/ctags_Versions/version-2/*.h>"
                +" E:/Research_USASK/Clone_detection_tools_compare/Simian/bin/simian_ctags_output";
        File chk;
        for(int i=1;i<775;i++){
            chk=new File(path+i);
            if (chk.exists()) {
                // ...
            }
            else System.out.println("version-"+i);
        }*/
    }
    
}
