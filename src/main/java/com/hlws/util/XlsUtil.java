package com.hlws.util;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class XlsUtil {
	
	private static Map<Integer, ByteArrayInputStream> cached_data = new ConcurrentHashMap<>(); 
	
	public static CellStyle getBoldStyle(Workbook wb) {
		CellStyle boldStyle = wb.createCellStyle();
		/* Create HSSFFont object from the workbook */
        Font boldFont = wb.createFont();
        /* set the weight of the font */
        boldFont.setBold(true);
        /* attach the font to the style created earlier */
        boldStyle.setFont(boldFont);
        /* At this stage, we have a bold style created which we can attach to a cell */
        return boldStyle;
	}
	
	public static CellStyle getColoredStyle(Workbook wb, short colorIndex) {
		CellStyle coloredStyle = wb.createCellStyle();
		/* Create HSSFFont object from the workbook */
        Font font = wb.createFont();
        font.setColor(colorIndex);
        coloredStyle.setFont(font);
        return coloredStyle;
	}
	
	public static ByteArrayInputStream getFromCache(Integer cacheKey) {
		return cached_data.get(cacheKey);
	}
	
	public static Integer addToCache(ByteArrayInputStream is) {
		Random random = new Random();
		
		Integer cacheKey = 10000000 + random.nextInt(90000000);
		cached_data.put(cacheKey, is);
		return cacheKey;
	}

}
