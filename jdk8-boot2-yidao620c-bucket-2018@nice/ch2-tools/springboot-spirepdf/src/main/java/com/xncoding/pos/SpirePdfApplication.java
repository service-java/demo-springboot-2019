package com.xncoding.pos;

import com.spire.doc.Document;
import com.spire.doc.ToPdfParameterList;
import com.spire.pdf.security.PdfEncryptionKeySize;
import com.spire.pdf.security.PdfPermissionsFlags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpirePdfApplication {

    public static void main(String[] args) {


        SpringApplication.run(SpirePdfApplication.class, args);
    }


}
