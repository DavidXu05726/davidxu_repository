package com.isoftstone.sample.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUtil {

	public static void main(String[] args) {
		//BigDecimal bd = new BigDecimal(3666.02144);
		log.info(CaculationUtil.subZeroAndDot("1000.00000"));
		
		log.info(CaculationUtil.getCommaNumberString(100223345.342));
		
		log.info(String.valueOf(DateUtil.getDaysSub("2018-06-07","2019-06-07","yyyy-MM-dd")));
		log.info(DateUtil.getAfterDayWeek("4"));
		log.info(DateUtil.getTimes("2013-06-08 17:21:12"));

	}

}
