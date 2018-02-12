package com.sxd.beifa.util;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Vector;

/**
 * 解决方案 : javacsv操作csv文件
 *
 *
 * @author SXD
 * @date 2018/2/12
 */
public class JavaCsvUtils {

    /**
     * 应测试需求，故而保留了打印的语句
     * @param path
     */
    public static List<String>  readerCsvFile(String path){
        List<String> csvLists = new Vector<>();
        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = null;
        try {
            r = new CsvReader(path, ',', Charset.forName("GBK"));
            //读取表头
            r.readHeaders();
            //逐条读取记录，直至读完
            while (r.readRecord()) {
                //读取一条记录
                System.out.println(r.getRawRecord());
                csvLists.add(r.getRawRecord());
                int cellNum = r.getHeaderCount();
                //按列名读取这条记录的值
                for (int i = 0; i < cellNum; i++) {
                    System.out.println(r.get(r.getHeader(i)));
                }
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return csvLists;
    }

}
