package foo.zongzhe.hpresearch.action;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelAction {

	File WORK_DIR = new File("E:\\HPResearch");

	/**
	 * ����һ��Excel�ļ�
	 * 
	 * @param fileName
	 *            Ҫ���ɵ�Excel�ļ���
	 */
	public void writeExcel(String fileName) {

		DirectoryAction da = new DirectoryAction();
		if (!da.whetherDirectoryExists(WORK_DIR)) {
			da.createDir(WORK_DIR);
		}

		WritableWorkbook wwb = null;
		try {
			// ����Ҫʹ��Workbook��Ĺ�����������һ����д��Ĺ�����(Workbook)����
			wwb = Workbook.createWorkbook(new File(WORK_DIR +"\\"+ fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// ����һ����д��Ĺ�����
			// Workbook��createSheet������������������һ���ǹ���������ƣ��ڶ����ǹ������ڹ������е�λ��
			WritableSheet ws = wwb.createSheet("sheet1", 0);

			// ���濪ʼ��ӵ�Ԫ��
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					// ������Ҫע����ǣ���Excel�У���һ��������ʾ�У��ڶ�����ʾ��
					Label labelC = new Label(j, i, "���ǵ�" + (i + 1) + "�У���"
							+ (j + 1) + "��");
					try {
						// �����ɵĵ�Ԫ����ӵ���������
						ws.addCell(labelC);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}

				}
			}

			try {
				// ���ڴ���д���ļ���
				wwb.write();
				// �ر���Դ���ͷ��ڴ�
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}

}
