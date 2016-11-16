package com.kidbear.basic.util;


import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 写Excel文档
 * @author songhn
 *
 */
public interface ExceWriteInter {

	/**
	 * 创建Excel文档
	 * @param excelFilePath
	 */
    void createExcel(Workbook workBook, List<String[]> list)throws Exception;
}