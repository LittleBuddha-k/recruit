package com.littlebuddha.recruit.common.utils.excel;

import com.littlebuddha.recruit.common.utils.reflect.Reflections;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 支持对xml，xmls读写操作
 *
 * @author ck
 * @date 2020/12/16 15:06
 */
public class ExcelImport {

	private Workbook wb;//指定excel文件
	private Sheet sheet;//excel文件中的sheet页
	private Row row;//sheet页对应的行号

	/**
	 * 标题行号
	 */
	private int headerNum;

	public ExcelImport(MultipartFile multipartFile, int headerNum, int sheetIndex) throws IOException, InvalidFormatException {
		this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), headerNum, sheetIndex);
	}

	public ExcelImport(String fileName, InputStream is, int headerNum, int sheetIndex)
			throws InvalidFormatException, IOException {
		if (StringUtils.isBlank(fileName)) {
			throw new RuntimeException("导入文档为空!");
		} else if (fileName.toLowerCase().endsWith("xls")) {
			this.wb = new HSSFWorkbook(is);
		} else if (fileName.toLowerCase().endsWith("xlsx")) {
			this.wb = new XSSFWorkbook(is);
		} else {
			throw new RuntimeException("文档格式不正确!");
		}
		if (this.wb.getNumberOfSheets() < sheetIndex) {
			throw new RuntimeException("文档中没有工作表!");
		}
		this.sheet = this.wb.getSheetAt(sheetIndex);
		this.headerNum = headerNum;
	}

	/**
	 * 获取数据行号
	 *
	 * @return
	 */
	public int getDataRowNum() {
		return headerNum + 1;
	}

	/**
	 * 获取最后一个数据行号
	 *
	 * @return
	 */
	public int getLastDataRowNum() {
		return this.sheet.getLastRowNum() + headerNum;
	}

	/**
	 * 获取行对象
	 *
	 * @param rownum
	 * @return
	 */
	public Row getRow(int rownum) {
		return this.sheet.getRow(rownum);
	}

	/**
	 * 获取excel数据
	 *
	 * @param cls
	 * @param groups
	 * @param <E>
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <E> List<E> getDataList(Class<E> cls, int... groups) throws IllegalAccessException, InstantiationException {
		List<Object[]> annotationList = new ArrayList<>();
		// Get annotation field
		Field[] fs = cls.getDeclaredFields();
		for (Field f : fs) {
			ExcelField ef = f.getAnnotation(ExcelField.class);
			if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
				if (groups != null && groups.length > 0) {
					boolean inGroup = false;
					for (int g : groups) {
						if (inGroup) {
							break;
						}
						for (int efg : ef.groups()) {
							if (g == efg) {
								inGroup = true;
								annotationList.add(new Object[]{ef, f});
								break;
							}
						}
					}
				} else {
					annotationList.add(new Object[]{ef, f});
				}
			}
		}
		// Get annotation method
		Method[] ms = cls.getDeclaredMethods();
		for (Method m : ms) {
			ExcelField ef = m.getAnnotation(ExcelField.class);
			if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
				if (groups != null && groups.length > 0) {
					boolean inGroup = false;
					for (int g : groups) {
						if (inGroup) {
							break;
						}
						for (int efg : ef.groups()) {
							if (g == efg) {
								inGroup = true;
								annotationList.add(new Object[]{ef, m});
								break;
							}
						}
					}
				} else {
					annotationList.add(new Object[]{ef, m});
				}
			}
		}
		// Field sorting
		Collections.sort(annotationList, new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				return new Integer(((ExcelField) o1[0]).sort()).compareTo(
						new Integer(((ExcelField) o2[0]).sort()));
			}

			;
		});
		//log.debug("Import column count:"+annotationList.size());
		// Get excel data
		List<E> dataList = new ArrayList<>();
		for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++) {
			E e = (E) cls.newInstance();
			int column = 0;
			Row row = this.getRow(i);
			StringBuilder sb = new StringBuilder();
			for (Object[] os : annotationList) {
				Object val = this.getCellValue(row, column++);
				if (val != null) {
					ExcelField ef = (ExcelField) os[0];
					// If is dict type, get dict value
					//if (StringUtils.isNotBlank(ef.dictType())){
					//    val = DictUtils.getDictValue(val.toString(), ef.dictType(), "");
					//    log.debug("Dictionary type value: ["+i+","+colunm+"] " + val);
					//}
					// Get param type and type cast
					Class<?> valType = Class.class;
					if (os[1] instanceof Field) {
						valType = ((Field) os[1]).getType();
					} else if (os[1] instanceof Method) {
						Method method = ((Method) os[1]);
						if ("get".equals(method.getName().substring(0, 3))) {
							valType = method.getReturnType();
						} else if ("set".equals(method.getName().substring(0, 3))) {
							valType = ((Method) os[1]).getParameterTypes()[0];
						}
					}
					//log.debug("Import value type: ["+i+","+column+"] " + valType);
					try {
						//如果导入的java对象，需要在这里自己进行变换。
						if (valType == String.class) {
							String s = String.valueOf(val.toString());
							if (StringUtils.endsWith(s, ".0")) {
								val = StringUtils.substringBefore(s, ".0");
							} else {
								val = String.valueOf(val.toString());
							}
						} else if (valType == Integer.class) {
							val = Double.valueOf(val.toString()).intValue();
						} else if (valType == Long.class) {
							val = Double.valueOf(val.toString()).longValue();
						} else if (valType == Double.class) {
							val = Double.valueOf(val.toString());
						} else if (valType == Float.class) {
							val = Float.valueOf(val.toString());
						} else if (valType == Date.class) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							val = sdf.parse(val.toString());
						} else {
							if (ef.fieldType() != Class.class) {
								val = ef.fieldType().getMethod("getValue", String.class).invoke(null, val.toString());
							} else {
								val = Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
										"fieldtype." + valType.getSimpleName() + "Type")).getMethod("getValue", String.class).invoke(null, val.toString());
							}
						}
					} catch (Exception ex) {
						val = null;
					}
					// set entity value
					if (os[1] instanceof Field) {
						Reflections.invokeSetter(e, ((Field) os[1]).getName(), val);
					} else if (os[1] instanceof Method) {
						String mthodName = ((Method) os[1]).getName();
						if ("get".equals(mthodName.substring(0, 3))) {
							mthodName = "set" + StringUtils.substringAfter(mthodName, "get");
						}
						Reflections.invokeMethod(e, mthodName, new Class[]{valType}, new Object[]{val});
					}
				}
				sb.append(val + ", ");
			}
			dataList.add(e);
		}
		return dataList;
	}

	/**
	 * 获取单元格值
	 *
	 * @param row    获取的行
	 * @param column 获取单元格列号
	 * @return 单元格值
	 */
	public Object getCellValue(Row row, int column) {
		Object val = "";
		try {
			Cell cell = row.getCell(column);
			if (cell != null) {
				if (cell.getCellType() == CellType.NUMERIC) {
					// val = cell.getNumericCellValue();
					// 当excel 中的数据为数值或日期是需要特殊处理
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						double d = cell.getNumericCellValue();
						Date date = HSSFDateUtil.getJavaDate(d);
						SimpleDateFormat dformat = new SimpleDateFormat(
								"yyyy-MM-dd");
						val = dformat.format(date);
					} else {
						NumberFormat nf = NumberFormat.getInstance();
						nf.setGroupingUsed(false);// true时的格式：1,234,567,890
						val = nf.format(cell.getNumericCellValue());// 数值类型的数据为double，所以需要转换一下
					}
				} else if (cell.getCellType() == CellType.STRING) {
					val = cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.FORMULA) {
					val = cell.getCellFormula();
				} else if (cell.getCellType() == CellType.BOOLEAN) {
					val = cell.getBooleanCellValue();
				} else if (cell.getCellType() == CellType.ERROR) {
					val = cell.getErrorCellValue();
				}
			}
		} catch (Exception e) {
			return val;
		}
		return val;
	}
}
