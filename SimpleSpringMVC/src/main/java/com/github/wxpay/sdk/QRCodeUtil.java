package com.github.wxpay.sdk;

import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  

import net.glxn.qrgen.QRCode;  
import net.glxn.qrgen.image.ImageType;  

public class QRCodeUtil { 
  
        public static void genQRCode(String encodeStr, String QRLocation) {  
            ByteArrayOutputStream out = QRCode.from(encodeStr)  
                    .to(ImageType.PNG).stream();  
  
            try(FileOutputStream fout = new FileOutputStream(new File(QRLocation))) {  
                  
                fout.write(out.toByteArray());  
                fout.flush();  
                fout.close();  
  
            } catch (FileNotFoundException e) {  
                // Do Logging  
            	System.out.print("QR_Code.JPG not found");
            } catch (IOException e) {  
                // Do Logging  
            }  
        }  
  
 } 
