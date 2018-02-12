package com.sxd.beifa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * stock实体
 */
@Data(staticConstructor = "of")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@IdClass(TimeSerKey.class)
public class TimeSeriesData {

    /**
     * ID
     * 复合主键
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )
    @Column(length = 36)
    private String item_id;
    /**
     * 交易时间
     * 复合主键
     */
    @Id
    @Column(nullable = false)
    private Date trading_date;
    /**
     * 股票编码
     * 复合主键
     */
    @Id
    @Column(nullable = false,length = 20)
    private String stock_code;
    /**
     * 交易数据1
     */
    @Column(nullable = false)
    private Double item_value1  = 0D;
    /**
     * 交易数据2
     */
    @Column(nullable = false)
    private Double item_value2  = 0D;
    /**
     * 交易数据3
     */
    @Column(nullable = false)
    private Double item_value3  = 0D;



}
