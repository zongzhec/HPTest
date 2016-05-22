package foo.zongzhe.hpsearch.test;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
	public static void main(String[] args) {
		File file = new File("C:/hptest/output/result_output/result.xls");
		double a[] = new double[3];
		a[0] = 32.1;
		a[1] = 13.1;
		a[2] = 1.3;
		CreateWorkbook(file, a);
	}

	public static void CreateWorkbook(File file, double[] a) {
		try {
			if (!file.exists()) { // 判断文件是否已存在，如果没有存在则创建新文件
				jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File("C:/hptest/output/result_output/result.xls"));
				jxl.write.WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
				int i = 0;
				ws.setColumnView(0, 20); // 设置列宽
				jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.0000000000000000"); // 定义数值格式
				WritableCellFormat wcfN = new WritableCellFormat(nf);
				String str2 = "第" + 1 + "次试验";
				Label label = new Label(0, 0, str2);
				ws.addCell(label);
				while (i < a.length) {
					jxl.write.Number num = new jxl.write.Number(0, i + 1, a[i], wcfN);
					ws.addCell(num);
					i++;
				}
				// 写入Exel工作表
				wwb.write();
				// 关闭Excel工作薄对象
				wwb.close();
			} else {
				Workbook rwb = Workbook.getWorkbook(file);
				File tempfile = new File("C:/hptest/output/result_output/result.xls");
				System.out.println("file exists.");
				WritableWorkbook wwb = Workbook.createWorkbook(tempfile, rwb);
				WritableSheet ws = wwb.getSheet(0);
				int num = rwb.getSheet(0).getRows();
				int num1 = num + 1;
				ws.setColumnView(num, 20); // 设置列宽
				String str2 = "第" + num1 + "次试验"; // 添加列名
				Label label = new Label(0, num, str2);
				ws.addCell(label);
				int i = 0;
				jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.000000000000000"); // 定义数值格式
				WritableCellFormat wcfN = new WritableCellFormat(nf);
				while (i < a.length) {
					jxl.write.Number number = new jxl.write.Number(num, i + 1, a[i], wcfN);
					ws.addCell(number);
					i++;
				}
				wwb.write();
				wwb.close();
				rwb.close();
				String filename = file.getPath();
				System.out.println("filename:" + filename);
				// file.delete();
				tempfile.renameTo(file);
				System.out.println("tempfile:" + tempfile.getPath());
				System.out.println(tempfile.exists());
				System.out.println(file.exists());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
