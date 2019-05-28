/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findintersect;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MD NADIM
 */
public class FindIntersectNiCAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        File ctag_changes, f_iclones;
        BufferedReader br1=null, br2=null;
        ctag_changes = new File("data_files/ctags_changes.txt");
        
        br1 = new BufferedReader(new FileReader(ctag_changes));

        String st1, st2,pr_notfound, filename,fileloc,pr_clone_class, cur_clone_class, out_clone_class;
        String[] splited1;
        String[] fileparts;
        int b1, e1, b2, e2, count_changes=0,count_versions=0, startline, endline; //beginning and end of two files
        PrintWriter writer = new PrintWriter("data_files/out_changes_NiCAD.txt", "UTF-8");
        PrintWriter wrt_not_found = new PrintWriter("data_files/NiCAD_not_found.txt", "UTF-8");
        PrintWriter wrt_nicad_count = new PrintWriter("data_files/NiCAD_change_count.txt", "UTF-8");
        PrintWriter wrt_rs_nicad = new PrintWriter("data_files/cleaned_rs_nicad.txt", "UTF-8");//To use for finding precession recall...
        pr_notfound="";   
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        while ((st1 = br1.readLine()) != null) {
            //System.out.println("# "+ st1);
            splited1 = st1.split("\\s+");
            //f_iclones = new File("data_files/nicad_block_clones/version-"+splited1[0]+"_blocks-clones-0.3.xml");
            f_iclones = new File("data_files/nicad_block_clones/version-"+splited1[0]+"_blocks-blind-clones-0.3.xml");
            if(f_iclones.exists()){
                count_versions=0;            
                br2 = new BufferedReader(new FileReader(f_iclones));
                st2 = br2.readLine();
                out_clone_class="";
                cur_clone_class="";
                pr_clone_class="";
                while ((st2 = br2.readLine()) != null) {
                    if(st2.length() > 6 && st2.substring(1,7).equals("source")){
                        Matcher m = p.matcher(st2);
                        m.find();
                        fileloc=m.group(1);
                        fileparts= fileloc.split("/");
                        filename=fileparts[8].replaceAll(".ifdefed", "");
                        m.find();
                        startline=Integer.parseInt(m.group(1));
                        m.find();
                        endline=Integer.parseInt(m.group(1));
                        //System.out.println(filename+", "+startline+", "+endline);   
                        if(splited1[1].equals(filename)){
                            b1=Integer.parseInt(splited1[3]);
                            e1=Integer.parseInt(splited1[4]);
                            b2=startline;
                            e2=endline;
                            if((e2>=b1 &&  e2<=e1)|| (b2>=b1 &&  b2<=e1) || (b2<b1 && e2>e1)){
                                if(!pr_clone_class.equals(cur_clone_class)){
                                    out_clone_class+=", "+cur_clone_class;
                                    pr_clone_class=cur_clone_class;
                                }
                                count_changes++;
                                count_versions++;          
                                System.out.println("++++++++++++++++++++++++");                                
                                System.out.println("cTags Change File("+splited1[0]+"): "+splited1[1]+" "+splited1[3]+" "+splited1[4]);
                                System.out.println("NiCAD File: "+filename+" "+startline+" "+endline);

                                // Write to file
                                writer.println("++++++++++++++++++++++++");
                                writer.println("cTags Change File("+splited1[0]+"): "+splited1[1]+" "+splited1[3]+" "+splited1[4]);
                                writer.println("NiCAD File: "+filename+" "+startline+" "+endline);

                            }                         

                        }

                        
                    }
                    else if(st2.length() > 5 && st2.substring(1,6).equals("class")){
                        Matcher m = p.matcher(st2);
                        m.find();
                        pr_clone_class=cur_clone_class;
                        cur_clone_class=m.group(1);
                    }
                }
                if(count_versions>0){
                    out_clone_class=out_clone_class.substring(2);
                    System.out.println(count_versions);   
                    wrt_nicad_count.println("Clone Overlap for Change version-"+splited1[0]+", "+splited1[1]+", "+splited1[2]+", "+splited1[3]+", "+splited1[4]+": "+count_versions+" (CloneClass: "+out_clone_class+")");
                    wrt_rs_nicad.println(splited1[0]+" "+splited1[1]+" "+splited1[3]+" "+splited1[4]);
                }
                                
                
            }
            else {
                if(!pr_notfound.equals(splited1[0])){
                    wrt_not_found.println("v"+splited1[0]);
                    pr_notfound=splited1[0];
                }
            }            
            //System.out.println(splited[0]+" "+splited[1]+" "+" "+splited[2]+" "+splited[3]+" "+splited[4]);
        }
        wrt_nicad_count.println("Total Change By NiCAD: "+count_changes);
        writer.close();
        wrt_not_found.close();
        wrt_nicad_count.close();
        wrt_rs_nicad.close();
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
