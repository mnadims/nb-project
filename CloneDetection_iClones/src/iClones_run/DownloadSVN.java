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
public class DownloadSVN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        for(int i=72001;i<=72001;i++){

            String command="svn export -r "+i+" https://svn.code.sf.net/p/brlcad/code/brlcad/trunk/src E:\\test\\SVN_Data\\brlcad\\Revisions\\rev-"+i+"\\src";

            ProcessBuilder builder = new ProcessBuilder(

                "cmd.exe", "/c", command);

            builder.redirectErrorStream(true);

            Process p = builder.start();

            

            // DISPLAYING IN CONSOLE 

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line=r.readLine();

            while (line != null) {            

                System.out.println(line);

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
