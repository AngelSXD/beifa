package com.sxd.beifa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * hibernate 复合主键类
 * @author SXD
 * @date 2018/2/12
 */
@Data(staticConstructor = "of")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TimeSerKey implements Serializable {

    private static final long serialVersionUID = 3176972128965536016L;

    private String item_id;
    private Date trading_date;
    private String stock_code;


    /**
     * 必须重写
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeSerKey that = (TimeSerKey) o;
        return Objects.equals(item_id, that.item_id) &&
                Objects.equals(trading_date, that.trading_date) &&
                Objects.equals(stock_code, that.stock_code);
    }

    /**
     * 必须重写
     * @return
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (item_id == null ? 0 : item_id.hashCode());
        result = PRIME * result + (trading_date == null ? 0 : trading_date.hashCode());
        result = PRIME * result + (stock_code ==null ? 0 : stock_code.hashCode());
        return result;
    }
}
