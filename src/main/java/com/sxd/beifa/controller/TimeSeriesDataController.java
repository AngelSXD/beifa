package com.sxd.beifa.controller;

import com.sxd.beifa.model.TimeSeriesData;
import com.sxd.beifa.repository.TimeSeriesDataRepository;
import com.sxd.beifa.util.DateUtils;
import com.sxd.beifa.util.JavaCsvUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping()
public class TimeSeriesDataController {

    @Resource
    private TimeSeriesDataRepository timeSeriesDataRepository;

    @ResponseBody
    @RequestMapping("index")
    @Transactional
    public String  test(String path){
        if(Objects.nonNull(path)){
            String fileName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
            System.out.println(fileName);
            List<String> csvLists = JavaCsvUtils.readerCsvFile(path);
            if (Objects.nonNull(csvLists) && Objects.nonNull(fileName) && csvLists.size()>0) {
                TimeSeriesData timeSeriesData = new TimeSeriesData();
                int sum = 0;
                for (int i = 0; i < csvLists.size() ; i++) {
                    timeSeriesData.setItem_id(UUID.randomUUID().toString());
                    timeSeriesData.setTrading_date(DateUtils.formatDate(fileName,"yyyy-MM-dd"));

                    String [] strArr = csvLists.get(i).split(",");
                    timeSeriesData.setStock_code(strArr[0]);
                    try{
                        timeSeriesData.setItem_value1(Double.parseDouble(strArr[1]));
                        timeSeriesData.setItem_value2(Double.parseDouble(strArr[2]));
                        timeSeriesData.setItem_value3(Double.parseDouble(strArr[3]));
                    }catch (Exception e){
                        System.out.println(e);
                    }

                    timeSeriesDataRepository.save(timeSeriesData);
                    sum++;
                }

                return sum == csvLists.size() ? "success" : "fail";
            }
        }
        return "路径无效！";
    }
}
