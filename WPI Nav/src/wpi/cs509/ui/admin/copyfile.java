package wpi.cs509.ui.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class copyfile {

	public static void copyFile(File targetFile, File file) {  
        try {  
            InputStream is = new FileInputStream(file);  
            FileOutputStream fos = new FileOutputStream(targetFile);  
            byte[] buffer = new byte[1024];  
            while (is.read(buffer) != -1) {  
                fos.write(buffer);  
            }  
            is.close();  
            fos.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }   
	
	public static void deleteFile(File delfile){
		delfile.delete();
	}
}
