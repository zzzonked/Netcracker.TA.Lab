package ua.edu.sumdu.ta.yaroslavkuts.lab;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Enable to work with particular excel worksheet in xls format.
 *
 * @version 1.0 16 Dec 2016
 * @author Yaroslav Kuts
 */
class XLSXHandler extends ExcelHandler {
	
	final static Logger LOG = Logger.getLogger(XLSXHandler.class);
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public XLSXHandler() {}
	
	/**
	 * Create new entity that work with specified excel worksheet.
	 * @param file
	 * @param sheetName
	 * @throws NullPointerException if workbook does not contain such sheet
	 */
	public XLSXHandler(String file, String sheetName) throws NullPointerException {
		try {
			workbook = new XSSFWorkbook(new File(file));
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) throw new NullPointerException("There is not such sheet");
		} catch (IOException | InvalidFormatException e) {
			LOG.error(e.getMessage());
		}
	}
	
	public int getNumberOfFilledRows() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	/**
	 * Find cell in excel worksheet by specified coordinates and return it.
	 * @param rowIndex
	 * @param cellIndex
	 * @return searched cell
	 * @throws IndexOutOfBoundsException if pass minus values like coordinates
	 */
	public XSSFCell getCell(int rowIndex, int cellIndex) throws IndexOutOfBoundsException {
		XSSFCell cell = null;
		if (rowIndex < 0 || cellIndex < 0) {
			throw new IndexOutOfBoundsException("Row's or cell's index cannot be < 0");
		} else {
			try {
				cell = sheet.getRow(rowIndex).getCell(cellIndex);
			} catch (NullPointerException e) {
				return null;
			}
		}
		return cell;
	}
}