/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iClones_run;

import java.io.*;
import static java.lang.System.out;

/**
 *
 * @author MD NADIM
 */
public class CloneDetection_conqat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String text;
        BufferedWriter bufferedWriter = null;
        File myFile = null;
        for (int i = 1; i <= 774; i++) {
            System.out.println("######################################");
            System.out.println("Working for CTags Version-"+i);
            System.out.println("######################################");
            text = "#!org.conqat.engine.code_clones.languages.multi.MultiLanguageCloneAnalysis\n"
                    + "clone.minlength=5\n"
                    + "input.project=ctags\n"
                    + "include.pattern=**/*.c\n"
                    + "input.dir=F:/01Programming_Data/ctags/repository/version-" + i + "/\n"
                    + "output.dir=E:/conqat_out/conqat_ctags_output/version-" + i + "\n"
                    + "#";

            try {

                myFile = new File("E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception ex) {

                }
            }

            
            String command="E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\conqat.bat -f E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr";

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
             
            //System.out.println(i+": "+command);
        }
        
        
        for (int i = 3; i <= 2115; i++) {
            System.out.println("######################################");
            System.out.println("Working for BrlCAD Version-"+i);
            System.out.println("######################################");
            text = "#!org.conqat.engine.code_clones.languages.multi.MultiLanguageCloneAnalysis\n"
                    + "clone.minlength=5\n"
                    + "input.project=brlcad\n"
                    + "include.pattern=**/*.c\n"
                    + "input.dir=F:/01Programming_Data/brlcad/repository/version-" + i + "/\n"
                    + "output.dir=E:/conqat_out/conqat_brlcad_output/version-" + i + "\n"
                    + "#";

            try {

                myFile = new File("E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception ex) {

                }
            }

            
            String command="E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\conqat.bat -f E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr";

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
             
            //System.out.println(i+": "+command);
        }
        
        
        for (int i = 1; i <= 1700; i++) {
            System.out.println("######################################");
            System.out.println("Working for Carol Version-"+i);
            System.out.println("######################################");
            text = "#!org.conqat.engine.code_clones.languages.java.JavaGappedCloneAnalysis\n" +
                    "clone.minlength=5\n" +
                    "input.project=carol\n" +
                    "input.dir=F:/01Programming_Data/carol/repository/version-" + i + "/\n" +
                    "output.dir=E:/conqat_out/conqat_carol_output/version-" + i + "\n" +
                    "errors.max=3\n" +
                    "gap.ratio=0.3\n" +
                    "#";

            try {

                myFile = new File("E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception ex) {

                }
            }

            
            String command="E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\conqat.bat -f E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr";

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
             
            //System.out.println(i+": "+command);
        }
        
        
        for (int i = 1000; i <= 2000; i++) {
            System.out.println("######################################");
            System.out.println("Working for Freecol Version-"+i);
            System.out.println("######################################");
            text = "#!org.conqat.engine.code_clones.languages.java.JavaGappedCloneAnalysis\n" +
                    "clone.minlength=5\n" +
                    "input.project=freecol\n" +
                    "input.dir=G:/Programming_Data/freecol/repository/version-" + i + "/\n" +
                    "output.dir=E:/conqat_out/conqat_freecol_output/version-" + i + "\n" +
                    "errors.max=3\n" +
                    "gap.ratio=0.3\n" +
                    "#";

            try {

                myFile = new File("E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception ex) {

                }
            }

            
            String command="E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\conqat.bat -f E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr";

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
             
            //System.out.println(i+": "+command);
        }
        
        
        for (int i = 6; i <= 1545; i++) {
            System.out.println("######################################");
            System.out.println("Working for Jabref Version-"+i);
            System.out.println("######################################");
            text = "#!org.conqat.engine.code_clones.languages.java.JavaGappedCloneAnalysis\n" +
                    "clone.minlength=5\n" +
                    "input.project=jabref\n" +
                    "input.dir=G:/Programming_Data/jabref/repository/version-" + i + "/\n" +
                    "output.dir=E:/conqat_out/conqat_jabref_output/version-" + i + "\n" +
                    "errors.max=3\n" +
                    "gap.ratio=0.3\n" +
                    "#";

            try {

                myFile = new File("E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception ex) {

                }
            }

            
            String command="E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\conqat.bat -f E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr";

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
             
            //System.out.println(i+": "+command);
        }
        
        
        for (int i = 3787; i <= 4001; i++) {
            System.out.println("######################################");
            System.out.println("Working for JEdit Version-"+i);
            System.out.println("######################################");
            text = "#!org.conqat.engine.code_clones.languages.java.JavaGappedCloneAnalysis\n" +
                    "clone.minlength=5\n" +
                    "input.project=jedit\n" +
                    "input.dir=G:/Programming_Data/jedit/repository/version-" + i + "/\n" +
                    "output.dir=E:/conqat_out/conqat_jedit_output/version-" + i + "\n" +
                    "errors.max=3\n" +
                    "gap.ratio=0.3\n" +
                    "#";

            try {

                myFile = new File("E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr");

                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Exception ex) {

                }
            }

            
            String command="E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\conqat.bat -f E:\\Research_USASK\\Clone_detection_tools_compare\\Running_Differnet_Tools\\ConQAT\\conqat-2015.2\\conqat\\bin\\config_files\\config.cqr";

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
             
            //System.out.println(i+": "+command);
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
