package com.whh.tools;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import java.io.*;

public class VerifiyTools001 {
    public static void main(String[] args) throws Exception {
//        downloadJPG(HttpClient.post("验证码url", "time=" + System.currentTimeMillis()), "1.jpg");
//        String code = getImgContent("1.jpg");
//        System.out.println("验证码 = " + code);

//        File imageFile = new File("YZM.jpg");
//        Tesseract instance = new Tesseract();
//        String result = instance.doOCR(imageFile);

        System.out.println(getImgContent("YZM.jpg"));
    }

    protected static void downloadJPG(HttpResponse httpResponse, String fileName) throws IOException {
        InputStream input = httpResponse.getEntity().getContent();
        OutputStream output = new FileOutputStream(new File(fileName));
        IOUtils.copy(input, output);
        if (output != null) {
            output.close();
        }
        output.flush();
    }

    protected static String getImgContent(String imgUrl) {
        String content = "";
        File imageFile = new File(imgUrl);
        //读取图片数字
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setLanguage("eng");//英文库识别数字比较准确
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        try {
            content = instance.doOCR(imageFile).replace("\n", "");
            System.out.println(content);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return content;
    }
}

