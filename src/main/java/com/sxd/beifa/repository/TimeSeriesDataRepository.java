package com.sxd.beifa.repository;

import com.sxd.beifa.model.TimeSerKey;
import com.sxd.beifa.model.TimeSeriesData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 持久化层
 * 本地数据库交互
 *
 * @author SXD
 * @date 2018/2/12
 */
public interface TimeSeriesDataRepository  extends JpaRepository<TimeSeriesData,TimeSerKey>{

}
