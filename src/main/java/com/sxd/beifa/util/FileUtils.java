package com.sxd.beifa.util;

import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 解决方案 : 解决超大csv文件读取问题
 *
 * 将csv文件按照指定行数分割再进行读取
 *
 * @author SXD
 * @date 2018/2/12
 */
public class FileUtils {
    /**
     * 获取csv文件头部
     * @param sourceFilePath
     * @return
     */
    private static String  header(String sourceFilePath){
        StringBuffer csvHeader = new StringBuffer();
        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = null;
        try {
            r = new CsvReader(sourceFilePath, ',', Charset.forName("GBK"));
            r.readHeaders();
            for (int i = 0; i <  r.getHeaderCount(); i++) {
                csvHeader.append(i == r.getHeaderCount()-1 ? r.getHeader(i) : r.getHeader(i)+",");
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        csvHeader.append("\r\n");
        return csvHeader.toString();
    }

    /**
     * 按行分割文件
     * @param rows 为多少行一个文件
     * @param sourceFilePath 为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     */
    public static void splitDataToSaveFile(int rows, String sourceFilePath,
                                    String targetDirectoryPath) {

        String csvHeaderStr = FileUtils.header(sourceFilePath);

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return;
            }
        } else {
            targetFile.mkdirs();
        }
        try {

            InputStreamReader in = new InputStreamReader(new FileInputStream(sourceFilePath),"GBK");
            BufferedReader br=new BufferedReader(in);

            BufferedWriter bw = null;
            String str = "";
            String tempData = br.readLine();
            int i = 1, s = 0;

            while (tempData != null) {
                str += tempData + "\r\n";
                if (i % rows == 0) {
                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                            targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".csv"), "GBK"),1024);

                    bw.write(str.replace(" ","").contains(csvHeaderStr) ? str : csvHeaderStr+str);
                    bw.close();

                    str = "";
                    s += 1;
                }
                i++;
                tempData = br.readLine();
            }
            if ((i - 1) % rows != 0) {

                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                        targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".csv"), "GBK"),1024);
                bw.write(csvHeaderStr+str);
                bw.close();
                br.close();

                s += 1;
            }
            in.close();

        } catch (Exception e) {
        }
    }
}
