package com.whh.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.fusesource.jansi.Ansi;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.showMessageDialog;
import static org.fusesource.jansi.Ansi.ansi;

public class SinaAPI {
    public static String APIURL = "http://hq.sinajs.cn/list=";
    public static Ansi.Color r = Ansi.Color.RED;
    public static Ansi.Color g = Ansi.Color.GREEN;
    public static float szfz=8;
    public static float xdfz=10f;
    public static int sleepTime=2000;

    public static void main(String arg[]) throws IOException, InterruptedException {
        String[] pool = {"sh601118","sz000807"};
        SinaAPI api = new SinaAPI();
        api.calculateAndShow(pool);
    }


    public String getRealTimeInformation(String codeLists) throws IOException {
        HttpResponse response = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(APIURL + codeLists);
            response = httpclient.execute(httpget);
        }catch (Exception e){

        }
        if(response!=null) {
            try {
                return EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                ((CloseableHttpResponse) response).close();
            }
        }
        return "";
    }

    public String calculateAndShow(String []pool) throws IOException, InterruptedException {
        while (true) {
            for (int i = 0; i < pool.length; i++) {
                String strInfo = this.getRealTimeInformation(pool[i]);
                String[] splitInfo = StringUtils.split(strInfo, ",");
                if(splitInfo.length==0) continue;
                final String name  = splitInfo[0].toString();
                Float kpj = Float.parseFloat(splitInfo[1]);
                Float ztspj = Float.parseFloat(splitInfo[2]);
                Float dqj = Float.parseFloat(splitInfo[3]);
                final  float abs = Math.abs((dqj - ztspj) / ztspj * 100);
                if (abs > 5) {
                    if (dqj < ztspj) {
                        System.out.print(ansi().eraseScreen().fg(g).reset().a("|").fg(g).a(dqj).reset().a("|").fg(g).a((dqj - ztspj) / ztspj * 100).a("%"));
                        if(abs>xdfz){
                            JOptionPane.showMessageDialog(null,name+"_"+abs);
                        }
                    } else {
                        System.out.print(ansi().eraseScreen().fg(r).reset().a("|").fg(r).a(dqj).reset().a("|").fg(r).a((dqj - ztspj) / ztspj * 100).a("%"));
                        if(abs>szfz){
                            showMessageDialog(null,name+"_"+abs);
                        }
                    }

                } else {
                    System.out.print(ansi().eraseScreen().reset().a("|").a(dqj).a("|").a((dqj - ztspj) / ztspj * 100).a("%"));
                }
            }
            Thread.sleep(sleepTime);
            System.out.println();
        }

    }

    public String calculateAndShowOneTime(String []pool) throws IOException, InterruptedException {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < pool.length; i++) {
                String strInfo = this.getRealTimeInformation(pool[i]);
                String[] splitInfo = StringUtils.split(strInfo, ",");
                final String namest  = splitInfo[0].toString();
                final String name = namest.substring(21, namest.length());
                Float kpj = Float.parseFloat(splitInfo[1]);
                Float dqj = Float.parseFloat(splitInfo[3]);
                final  float abs = Math.abs((dqj - kpj) / kpj * 100);
                SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
                final Date date = new Date();
                final String fdate = df.format(date);
                buf.append(name+"_"+dqj+"_"+(dqj - kpj) / kpj * 100+"_"+fdate);
                buf.append("\r\n");
            }
            return  buf.toString();
    }

}
