/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findintersect;

import java.io.*;

/**
 *
 * @author MD NADIM
 */
public class FindIntersectIClones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        File ctag_changes, f_iclones;
        BufferedReader br1=null, br2=null;
        ctag_changes = new File("data_files/ctags_changes.txt");
        
        br1 = new BufferedReader(new FileReader(ctag_changes));

        String st1, st2,pr_notfound,pr_clone_class, cur_clone_class, out_clone_class;
        String[] splited1, splited2;
        int b1, e1, b2, e2, count_changes=0,count_versions=0; //beginning and end of two files
        PrintWriter writer = new PrintWriter("data_files/out_changes_iclones.txt", "UTF-8");
        PrintWriter wrt_not_found = new PrintWriter("data_files/Versions_not_found.txt", "UTF-8");
        PrintWriter wrt_iClone_count = new PrintWriter("data_files/iClone_change_count.txt", "UTF-8");
        PrintWriter wrt_rs_iclones = new PrintWriter("data_files/cleaned_rs_iclones.txt", "UTF-8");//To use for finding precession recall...
        pr_notfound="";   
        while ((st1 = br1.readLine()) != null) {
            //System.out.println("# "+ st1);
            count_versions=0;
            splited1 = st1.split("\\s+");
            f_iclones = new File("data_files/iClones_result/iClones_detected_v"+splited1[0]+".txt");
            if(f_iclones.exists()){
                br2 = new BufferedReader(new FileReader(f_iclones));
                st2 = br2.readLine();
                out_clone_class="";
                cur_clone_class="";
                pr_clone_class="";
                while ((st2 = br2.readLine()) != null) {
                    splited2 = st2.split("\\s+");
                    if(isInteger(splited2[1])) {
                        if(splited1[1].equals(splited2[2])){
                            b1=Integer.parseInt(splited1[3]);
                            e1=Integer.parseInt(splited1[4]);
                            b2=Integer.parseInt(splited2[3]);
                            e2=Integer.parseInt(splited2[4]);
                            if((e2>=b1 &&  e2<=e1)|| (b2>=b1 &&  b2<=e1) || (b2<b1 && e2>e1)){
                                if(!pr_clone_class.equals(cur_clone_class)){
                                    out_clone_class+=", "+cur_clone_class;
                                    pr_clone_class=cur_clone_class;
                                }
                                count_changes++;
                                count_versions++;          
                                System.out.println("++++++++++++++++++++++++");                                
                                System.out.println("cTags Change File("+splited1[0]+"): "+splited1[1]+" "+splited1[3]+" "+splited1[4]);
                                System.out.println("iClone File: "+splited2[2]+" "+splited2[3]+" "+splited2[4]);
                                
                                // Write to file
                                writer.println("++++++++++++++++++++++++");
                                writer.println("cTags Change File("+splited1[0]+"): "+splited1[1]+" "+splited1[3]+" "+splited1[4]);
                                writer.println("iClone File: "+splited2[2]+" "+splited2[3]+" "+splited2[4]);
                               
                            }                         
                            
                        }
                        
                    }
                    else { 
                        pr_clone_class=cur_clone_class;
                        cur_clone_class=splited2[2];                        
                    }
                }
                if(count_versions>0){
                    out_clone_class=out_clone_class.substring(2);
                    System.out.println(count_versions);   
                    wrt_iClone_count.println("Clone Overlap for Change version-"+splited1[0]+", "+splited1[1]+", "+splited1[2]+", "+splited1[3]+", "+splited1[4]+": "+count_versions+" (CloneClass: "+out_clone_class+")");
                    wrt_rs_iclones.println(splited1[0]+" "+splited1[1]+" "+splited1[3]+" "+splited1[4]);
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
        wrt_iClone_count.println("Total Change By iClone: "+count_changes);
        writer.close();
        wrt_not_found.close();
        wrt_iClone_count.close();
        wrt_rs_iclones.close();
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
