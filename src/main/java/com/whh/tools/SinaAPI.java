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
    public static Ansi.Color read = Ansi.Color.RED;
    public static Ansi.Color green = Ansi.Color.GREEN;
    public static Ansi.Color blue = Ansi.Color.BLUE;
    public static Ansi.Color white = Ansi.Color.WHITE;

    public static float szfz = 8;
    public static float xdfz = 10f;
    public static int sleepTime = 2000;
    public static int balanceNum = 2;
    public static Ansi.Color currentColor = white;
    public static String[] pool = {"sh601118", "sz000807","sz002607"};

    public static void main(String arg[]) throws IOException, InterruptedException {
        SinaAPI api = new SinaAPI();
        api.calculateAndShow(pool);
    }


    public String calculateAndShow(String[] pool) throws IOException, InterruptedException {
        while (true) {
            for (int i = 0; i < pool.length; i++) {
                String strInfo = this.getRealTimeInformation(pool[i]);
                String[] splitInfo = StringUtils.split(strInfo, ",");
                if (splitInfo.length == 0) continue;
                String name = splitInfo[0].toString();
                Float todayStartPrice = Float.parseFloat(splitInfo[1]);
                Float yesterdayEndPrice = Float.parseFloat(splitInfo[2]);
                Float currentPrice = Float.parseFloat(splitInfo[3]);
                Float oldPercent = (currentPrice-yesterdayEndPrice)/yesterdayEndPrice * 100;
                Float newPercent = (currentPrice-todayStartPrice)/todayStartPrice * 100;
                System.out.print(ansi().eraseScreen().fg(currentColor).a((i+1)+"【").a(currentPrice).a("★").a(oldPercent).a("★").a(newPercent).a("】■■"));
            }
            Thread.sleep(sleepTime);
            System.out.println();
        }

    }


    public String getRealTimeInformation(String codeLists) throws IOException {
        HttpResponse response = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(APIURL + codeLists);
            response = httpclient.execute(httpget);
        } catch (Exception e) {

        }
        if (response != null) {
            try {
                return EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ((CloseableHttpResponse) response).close();
            }
        }
        return "";
    }


}
