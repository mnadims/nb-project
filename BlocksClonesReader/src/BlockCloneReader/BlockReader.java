/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockCloneReader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MD NADIM
 */
class CloneData {

    int clone_class;
    String filename1 = null;
    int startline1;
    int endline1;

}

public class BlockReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        // We need to provide file path as the parameter: 
        // double backquote is to avoid compiler interpret words 
        // like \test as \t (ie. as a escape sequence) 
        File file = new File("Version_XML_files/version-93_blocks-clones-0.3.xml");
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        CloneData[] obj = new CloneData[10000];
        int i,j;

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        i = 0;
        int current_class = 0;
        while ((st = br.readLine()) != null) {
            if (st.substring(1, 6).equals("class")) {
                while (st.length() > 6 && !st.substring(0, 6).equals("</class")) {                    
                    if(st.substring(1,7).equals("source")){
                        obj[i] = new CloneData (); 
                        obj[i].clone_class=current_class;
                        Matcher m = p.matcher(st);
                        m.find();
                        obj[i].filename1=m.group(1);
                        m.find();
                        obj[i].startline1=Integer.parseInt(m.group(1));
                        m.find();
                        obj[i].endline1=Integer.parseInt(m.group(1));                        
                        i++;
                        
                    }
                    st = br.readLine();
                }
                current_class++;
            }
        }
        
        for(j=0;j<i;j++){
            System.out.println(obj[j].clone_class+" "+obj[j].filename1+" "+obj[j].startline1+" "+ obj[j].endline1);
            
        }
    }

}
