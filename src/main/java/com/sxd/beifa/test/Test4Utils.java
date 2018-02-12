package com.sxd.beifa.test;

import com.sxd.beifa.util.FileUtils;
import com.sxd.beifa.util.JavaCsvUtils;
import org.junit.jupiter.api.Test;

public class Test4Utils {

    @Test
    public void test1(){
        /**
         * 考虑到csv文件数据量过大，工具类提供了
         * 按多少行分割csv文件的方法
         */
//        FileUtils.splitDataToSaveFile(1,"E:/2017-09-19.csv","E:/分割/");

          JavaCsvUtils.readerCsvFile("E:/2017-09-19.csv");
    }

}
