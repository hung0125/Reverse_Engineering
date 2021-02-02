import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;

public class Uploader {
    // Uploading some files to http server
    /*
    What to do in this class: 
        get result list from 'find' command 
        
    */
    public static void main(String[] args) {
        //get result path list's file path
        File f = new File(args[0]);
        try
        {
            Scanner sc = new Scanner(f);
            ArrayList<String> targets = new ArrayList<String>();
            while(sc.hasNextLine())
            {
                targets.add(sc.nextLine());
            }
            Collections.shuffle(targets);
            //set limitation of files upload, here I set 3
            int fcount = 0;
            for(int i = 0; i < targets.size(); i++)
            {   
                File temp = new File(targets.get(i).toString());
                Long fsize = temp.length() / 1024 / 1024;
                if(fsize <= 3)
                {
                            uploadFile(temp);
                            System.out.println("ok: " + targets.get(i).toString()); 
                }
                fcount++;
                if(fcount == 3)break;
            }

        }catch(Exception e)
        {System.out.println(e.toString());}
           
    }

    static void uploadFile(File file) {

        if(!file.exists()) return;
        try
        {
            FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");
        
            HttpPost post = new HttpPost("http://localhosts/file.php");

			//set binary entity
            post.setEntity(reqEntity);
            reqEntity.setContentType("binary/octet-stream");

            //set timestamp of the file as the component of filename
            post.setHeader("fname", file.getName());

            //fire
            HttpClient httpclient = HttpClientBuilder.create().build();
            httpclient.execute(post);
            
        }catch(Exception e)
        {System.out.println(e.toString());}
    }

}
