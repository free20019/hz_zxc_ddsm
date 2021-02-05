package com.twkf.helper;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
	 public static Workbook createWorkBook(List<Map<String, Object>> list,
			 String []keys,String columnNames[],String gzb) {
	        // 创建excel工作簿
	        Workbook wb = new HSSFWorkbook();
	        // 创建第一个sheet（页），并命名
	        Sheet sheet = wb.createSheet(gzb);
	        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
	        for(int i=0;i<keys.length;i++){
	            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
	        }

	        // 创建第一行
	        Row row = sheet.createRow((short) 0);

	        // 创建两种单元格格式
	        CellStyle cs = wb.createCellStyle();
	        CellStyle cs2 = wb.createCellStyle();

	        // 创建两种字体
	        Font f = wb.createFont();
	        Font f2 = wb.createFont();

	        // 创建第一种字体样式（用于列名）
	        f.setFontHeightInPoints((short) 10);
	        f.setColor(IndexedColors.BLACK.getIndex());
	        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

	        // 创建第二种字体样式（用于值）
	        f2.setFontHeightInPoints((short) 10);
	        f2.setColor(IndexedColors.BLACK.getIndex());

//	        Font f3=wb.createFont();
//	        f3.setFontHeightInPoints((short) 10);
//	        f3.setColor(IndexedColors.RED.getIndex());

	        // 设置第一种单元格的样式（用于列名）
	        cs.setFont(f);
	        cs.setBorderLeft(CellStyle.BORDER_THIN);
	        cs.setBorderRight(CellStyle.BORDER_THIN);
	        cs.setBorderTop(CellStyle.BORDER_THIN);
	        cs.setBorderBottom(CellStyle.BORDER_THIN);
	        cs.setAlignment(CellStyle.ALIGN_CENTER);
	        cs.setWrapText(true);

	        // 设置第二种单元格的样式（用于值）
	        cs2.setFont(f2);
	        cs2.setBorderLeft(CellStyle.BORDER_THIN);
	        cs2.setBorderRight(CellStyle.BORDER_THIN);
	        cs2.setBorderTop(CellStyle.BORDER_THIN);
	        cs2.setBorderBottom(CellStyle.BORDER_THIN);
	        cs2.setAlignment(CellStyle.ALIGN_CENTER);
	        //设置列名
	        for(int i=0;i<columnNames.length;i++){
	            Cell cell = row.createCell(i);
	            cell.setCellValue(columnNames[i]);
	            cell.setCellStyle(cs);
	        }
	        //设置每行每列的值
	        int rowss=1;
	        for (short i = 0; i < list.size(); i++) {
	            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
	            // 创建一行，在页sheet上
	            Row row1 = sheet.createRow((short) rowss+i);
	            // 在row行上创建一个方格
	            for(short j=0;j<keys.length;j++){
	                Cell cell = row1.createCell(j);
	                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
	                cell.setCellStyle(cs2);
	            }
	        }
	        return wb;
	    }

	public static Workbook createWorkBook2(List<Map<String, Object>> listsw, List<Map<String, Object>> listxw, String[] keys, String[] columnNames, String gzb, String time) throws IOException {
		//缓存的值
		//**列数
		int rowaccess = columnNames.length+2;
		//**天数
		int days=rowaccess-12;
		// 创建excel工作簿
		SXSSFWorkbook wb = new SXSSFWorkbook(rowaccess);
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet(gzb);
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for(int i=0;i<keys.length;i++){
			sheet.setColumnWidth((short) i, (short) (35.7 * 50));
		}
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
		cs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
		cs2.setWrapText(true);

		// 创建第1行
		Row row1 = sheet.createRow((short) 0);
		for(int i=1;i<rowaccess;i++){
			row1.createCell(i).setCellStyle(cs);
		}
		Cell cell1 = row1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, rowaccess-1));
		cell1.setCellValue("通悟科技");
		cell1.setCellStyle(cs);

		// 创建第2行
		Row row2 = sheet.createRow((short) 1);
		for(int i=1;i<rowaccess;i++){
			row2.createCell(i).setCellStyle(cs);
		}
		Cell cell2 = row2.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, rowaccess-1));
		cell2.setCellValue("职工考勤记录表");
		cell2.setCellStyle(cs);

		// 创建第3行
		Row row3 = sheet.createRow((short) 2);
		for(int i=1;i<rowaccess;i++){
			row3.createCell(i).setCellStyle(cs);
		}
		Cell cell3_0 = row3.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
		cell3_0.setCellValue("人员：全员");
		cell3_0.setCellStyle(cs);

		Cell cell3_1 = row3.createCell(8);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 8, rowaccess-1));
		time=time.replace("-","年")+"月";
		cell3_1.setCellValue(time);
		cell3_1.setCellStyle(cs);
		//设置列名
		Row row4 = sheet.createRow((short) 3);
//            String[] a={"事假","病假","婚假","年休假","调休假","妊娠假","陪产假","探亲假","其他"};
		String[] a={"事假","病假","婚假","年休假","调休假"};
		List<String> leave = Arrays.asList(a);
		Row row5 = sheet.createRow((short) 4);
		for(int i=1;i<rowaccess;i++){
			row5.createCell(i).setCellStyle(cs);
		}
		int m=0;
		for(int i=0;i<columnNames.length;i++){
			if(leave.indexOf(columnNames[i])>-1){
				m=i+1;
			}
		}
		Cell cell4_0= row4.createCell(m-leave.size()+1);
		sheet.addMergedRegion(new CellRangeAddress(3, 3, m-leave.size()+1, m));
		//计算出从哪一列两行合并为一行
		int n=m-leave.size();
		cell4_0.setCellValue("缺勤天数");
		cell4_0.setCellStyle(cs);
		for(int i=0;i<columnNames.length+1;i++){
			if(i==0){
				Cell cell4 = row4.createCell(i);
				sheet.addMergedRegion(new CellRangeAddress(3, 4, i, i));
				cell4.setCellValue("序号");
				cell4.setCellStyle(cs);
			}else{
				if(leave.indexOf(columnNames[i-1])>-1){
					Cell cell5_1 = row5.createCell(i);
					sheet.addMergedRegion(new CellRangeAddress(4, 4, i, i));
					cell5_1.setCellValue(columnNames[i-1]);
					cell5_1.setCellStyle(cs);
				}else{
					Cell cell4 = row4.createCell(i);
					sheet.addMergedRegion(new CellRangeAddress(3, 4, i, i));
					cell4.setCellValue(columnNames[i-1]);
					cell4.setCellStyle(cs);
				}
			}
		}
		Cell cell4_last = row4.createCell(rowaccess-1);
		sheet.addMergedRegion(new CellRangeAddress(3, 4, rowaccess-1, rowaccess-1));
		cell4_last.setCellValue("备注");
		cell4_last.setCellStyle(cs);
		int c=5;
		for (int i = 0; i < listsw.size(); i++) {
			Row rowlist = sheet.createRow((short) c+i*2);
			//two第1列
			Cell celllist1 = rowlist.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, 0, 0));
			celllist1.setCellValue(i+1);
			celllist1.setCellStyle(cs2);
			//two第2列
			Cell celllist2 = rowlist.createCell(1);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, 1, 1));
			celllist2.setCellValue(String.valueOf(listsw.get(i).get("USERNAME")));
			celllist2.setCellStyle(cs2);
			//two第3列
			Cell celllist3 = rowlist.createCell(n);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n, n));
			celllist3.setCellValue(String.valueOf(listxw.get(i).get("CQ")));
			celllist3.setCellStyle(cs2);
			//two第4列
			Cell celllist4 = rowlist.createCell(n+1);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+1, n+1));
			celllist4.setCellValue(String.valueOf(listxw.get(i).get("SJ")));
			celllist4.setCellStyle(cs2);
			//two第5列
			Cell celllist5 = rowlist.createCell(n+2);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+2, n+2));
			celllist5.setCellValue(String.valueOf(listxw.get(i).get("BJ")));
			celllist5.setCellStyle(cs2);
			//two第6列
			Cell celllist6 = rowlist.createCell(n+3);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+3, n+3));
			celllist6.setCellValue(String.valueOf(listxw.get(i).get("TXJ")));
			celllist6.setCellStyle(cs2);
			//two第7列
			Cell celllist7 = rowlist.createCell(n+4);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+4, n+4));
			celllist7.setCellValue(String.valueOf(listxw.get(i).get("NJ")));
			celllist7.setCellStyle(cs2);
			//two第8列
			Cell celllist8 = rowlist.createCell(n+5);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+5, n+5));
			celllist8.setCellValue(String.valueOf(listxw.get(i).get("HJ")));
			celllist8.setCellStyle(cs2);
			//two第9列
			Cell celllist9 = rowlist.createCell(n+6);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+6, n+6));
			celllist9.setCellValue(String.valueOf(listxw.get(i).get("JJR")));
			celllist9.setCellStyle(cs2);
			//two第10列(新加)
			Cell celllist10 = rowlist.createCell(n+7);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+7, n+7));
			celllist10.setCellValue(String.valueOf(listxw.get(i).get("JB")));
			celllist10.setCellStyle(cs2);
			//two第11列(备注)
			Cell celllist101 = rowlist.createCell(n+8);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2+1, n+8, n+8));
			celllist101.setCellValue("");
			celllist101.setCellStyle(cs2);
			//上午下午
			Row rowlistxw = sheet.createRow((short) c+i*2+1);
			for(int k=0;k<rowaccess;k++){
				rowlistxw.createCell(k).setCellStyle(cs2);
			}
			Cell celllist11 = rowlist.createCell(2);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2, 2, 2));
			celllist11.setCellValue(String.valueOf(listsw.get(i).get("TYPE")));
			celllist11.setCellStyle(cs2);

			Cell celllist12 = rowlistxw.createCell(2);
			sheet.addMergedRegion(new CellRangeAddress(c+i*2+1, c+i*2+1, 2, 2));
			celllist12.setCellValue(String.valueOf(listxw.get(i).get("TYPE")));
			celllist12.setCellStyle(cs2);
			for(int j=1;j<=days;j++){
				Cell celllistmonthsw = rowlist.createCell(j+2);
				sheet.addMergedRegion(new CellRangeAddress(c+i*2, c+i*2, j+2, j+2));
				celllistmonthsw.setCellValue(String.valueOf(listsw.get(i).get("KQTB"+j)));
				celllistmonthsw.setCellStyle(cs2);

				Cell celllistmonthxw = rowlistxw.createCell(j+2);
				sheet.addMergedRegion(new CellRangeAddress(c+i*2+1, c+i*2+1, j+2, j+2));
				celllistmonthxw.setCellValue(String.valueOf(listxw.get(i).get("KQTB"+j)));
				celllistmonthxw.setCellStyle(cs2);
			}
		}
		//备注
		Row rowremarks = sheet.createRow((short) c+listsw.size()*2);
		for(int i=1;i<rowaccess;i++){
			rowremarks.createCell(i).setCellStyle(cs2);
		}
		Cell cellremarks1 = rowremarks.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(c+listsw.size()*2, c+listsw.size()*2, 0, 1));
		cellremarks1.setCellValue("备注");
		cellremarks1.setCellStyle(cs2);
		Cell cellremarks2 = rowremarks.createCell(2);
		sheet.addMergedRegion(new CellRangeAddress(c+listsw.size()*2, c+listsw.size()*2, 2, rowaccess-1));
		cellremarks2.setCellValue("出勤:/ 事假:⚪ 病假:▲ 婚假:婚 年假:年 调休假:L 迟到/早退:* 加班:● 旷工:× 节假日:节 其他:什");
		cellremarks2.setCellStyle(cs2);
		return wb;
	}
	public static Workbook createWorkBook3(List<Map<String, Object>> list,
										  String []keys,String columnNames[],String gzb) {
		// 创建excel工作簿
		Workbook wb = new HSSFWorkbook();
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet(gzb);
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for(int i=0;i<keys.length;i++){
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

		// 创建第一行
		Row row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setWrapText(true);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第3种单元格的样式（用于值
		Font f3 = wb.createFont();
		f3.setFontHeightInPoints((short) 10);
		f3.setColor(IndexedColors.RED.getIndex());
		CellStyle cs3 = wb.createCellStyle();
		cs3.setFont(f3);
		cs3.setBorderLeft(CellStyle.BORDER_THIN);
		cs3.setBorderRight(CellStyle.BORDER_THIN);
		cs3.setBorderTop(CellStyle.BORDER_THIN);
		cs3.setBorderBottom(CellStyle.BORDER_THIN);
		cs3.setAlignment(CellStyle.ALIGN_CENTER);
		//设置列名
		for(int i=0;i<columnNames.length;i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		//设置每行每列的值
		int rowss=1;
		for (short i = 0; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			Row row1 = sheet.createRow((short) rowss+i);
			// 在row行上创建一个方格
			for(short j=0;j<keys.length;j++){
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
				if(gzb.indexOf("月签到表")>-1){
					if(keys[j].indexOf("SW")>-1&&"不正常".equals(String.valueOf(list.get(i).get("QD"+keys[j].replace("SW",""))))){
						cell.setCellStyle(cs3);
					}
					if(keys[j].indexOf("XW")>-1&&"不正常".equals(String.valueOf(list.get(i).get("QT"+keys[j].replace("XW",""))))){
						cell.setCellStyle(cs3);
					}
				}
			}
		}
		return wb;
	}
}
