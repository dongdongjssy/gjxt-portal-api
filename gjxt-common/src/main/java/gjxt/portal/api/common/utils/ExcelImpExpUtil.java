package gjxt.portal.api.common.utils;

import gjxt.portal.api.common.base.R;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelImpExpUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelImpExpUtil.class);    
    
    public static int IDX_DATA_START = 2; //row 0 is header, row 1 is description and data start at row 2.
    
    public static <T> List<T> parseExcelFile(InputStream input, Class<T> domainClass) throws ExcelUtilException{
    	List<T> all = new ArrayList<>();
        try {
			Workbook workbook = WorkbookFactory.create(input);
			int cnt = workbook.getNumberOfSheets();
			Sheet sheet = workbook.getSheetAt(0);
			List<T> objList = parseObjectList(sheet, domainClass);
			if(!objList.isEmpty())
				all.addAll(objList);
//			for(int i =0; i < cnt; i++) {
//			}
		} catch (Exception e) {
			String err = "无法解析输入文件为excel表格文件, "+ e.getMessage(); 
			log.error(err);
			throw new ExcelUtilException(err);
		}
        
        if(all.isEmpty()) {
        	String err = "excel表格文件数据格式错误或无有效数据"; 
			log.error(err);
			throw new ExcelUtilException(err);
        }

		return all;
    }

    
	private static <T> List<T> parseObjectList(Sheet sheet, Class<T> domainClass) throws ExcelUtilException {		
		String sheetName = sheet.getSheetName();
		List<String> fieldNames;
		try {
			fieldNames = parseHeaderFieldNames(sheet.getRow(1));
		} catch (ExcelUtilException e) {
			String err = "表单头部(第1行)解析失败, "+e.getMessage()+". 跳过处理表单, "+sheetName;
			log.error(err);
			throw new ExcelUtilException(err);
		}
			
		int cnt = sheet.getPhysicalNumberOfRows();
		int idx = IDX_DATA_START;
		List<T> rowObj = new ArrayList<>();
		R result = new R();
		for (idx = IDX_DATA_START; idx < cnt; idx++) {
			try {
				rowObj.add(parseObject(sheet.getRow(idx), domainClass, fieldNames));
			} catch (ExcelUtilException e) {
				String err = "无法转换行到域对象，行号: " + (idx+1);
				result.put(Integer.toString(idx+1), err);
			}
		}
		
		return rowObj;		
	}
	
	
	private static <T> T parseObject(Row r, Class<T> objClazz, List<String> fieldNames) throws ExcelUtilException {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignore unknown field
		
		Map<String, String> nameValue= new HashMap<>();
		int cnt = fieldNames.size();
		for(int i = 0; i < cnt; i++) {
			Cell c = r.getCell(i);
			if(c != null) {
	            if (c.getCellTypeEnum() == CellType.NUMERIC) {
	            	if (DateUtil.isCellDateFormatted(c)) {
	   	             DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	            		String value = DateTimeFormatter.ISO_DATE_TIME.format(c.getDateCellValue().toInstant());	            		
	            		nameValue.put(fieldNames.get(i), formater.format(c.getDateCellValue()));
	            	}
	            	else {
	            		DataFormatter df = new DataFormatter();
	            		nameValue.put(fieldNames.get(i), df.formatCellValue(c));
	            	}	     			
	            }
	            else
	    			nameValue.put(fieldNames.get(i), c.getStringCellValue());
			}
		}
		
		try {
			String json = mapper.writeValueAsString(nameValue);			
			return mapper.readValue(json, objClazz);
		} catch (IOException e) {
			String err ="无法转换行数据到域对象, "+objClazz.getName() +"."+e.getMessage(); 
			log.error(err);
			throw new ExcelUtilException(err, e);			
		}

	} 
	
	
	private static List<String> parseHeaderFieldNames(Row r) throws ExcelUtilException {
		List<String> fieldNames = new ArrayList<>();
		int first = r.getFirstCellNum(), last = r.getLastCellNum();	
		for(int i = first; i < last; i++) {
			Cell c = r.getCell(i);
			if(c != null) {
				if(c.getCellTypeEnum() != CellType.STRING) {
					String err = "表单头部的字段名称不可以是非文本数据（例如数字，日期等）;";
					log.error(err);				
					throw new ExcelUtilException(err);
				}
				else
					fieldNames.add(c.getStringCellValue().trim());
			}
			else {
				String err = "表单头部列不能为空";
				log.error(err);
				throw new ExcelUtilException(err);			
			}
		}
		
		if(fieldNames.isEmpty()) {
			String err = "表单头部(第一行)为空或格式错误.  ";
			log.error(err);
			throw new ExcelUtilException(err);
		}
	
		return fieldNames;
	}
    
	/* START: Interface to call service to insert and return detailed result using json */	
    public static int MSG_CODE_SUCCESS = 0;
    public static int MSG_CODE_ERR_PARSE_EXCEL = 2;
    public static int MSG_CODE_ERR_PARTIAL_FAILURE = 3;
    public static int MSG_CODE_ERR_INCORRECT_SHEET_NAME = 3;
    public static int MSG_CODE_ERR_INCORRECT_SHEET_HEADER = 4;
    public static int MSG_CODE_ERR_SERVICE_METHOD = 6;
    
	public static R importFile(InputStream input, Object svcObj, Class<?> domainClass)  {
		R result = new R();
		boolean partialFailure = false;
		
		Method insert = null;
		try {
			insert = getMethod(svcObj, domainClass);
		} catch (ExcelUtilException e) {
			return R.error(MSG_CODE_ERR_SERVICE_METHOD, e.getMessage());
		}
		
        try {
			Workbook workbook = WorkbookFactory.create(input);
			int cnt = workbook.getNumberOfSheets();
			for(int i =0; i < cnt; i++) { 
				Sheet sheet = workbook.getSheetAt(i);				
				R detail = importSheet(sheet, svcObj, insert, domainClass);
				result.put(sheet.getSheetName(), detail);
				if(!detail.get("code").toString().equals("0") ) {
					partialFailure = true;
				}
			}
		} catch (Exception e) {
			String err = "无法解析输入文件为ｅｘｃｅｌ表格文件, "+ e.getMessage(); 
			log.error(err);
			return R.error(MSG_CODE_ERR_PARSE_EXCEL, err);
		} 
        
        if(partialFailure) {
			result.put("msg", "部分或全部数据导入失败");
			result.put("code", MSG_CODE_ERR_PARTIAL_FAILURE);	
        	
        }
        else {
        	result.put("msg", "成功导入全部数据");
			result.put("code", MSG_CODE_SUCCESS);
        }
        
        return result;
	}	
	
	
	private static R importSheet(Sheet sheet, Object svcObj, Method insert, Class<?> objClazz) {
		log.info("Processing work sheet : "+sheet.getSheetName()+". total physical row number: "+sheet.getPhysicalNumberOfRows());		
		
		String sheetName = sheet.getSheetName();
		String domainName = parseDomainNameFromSheetName(sheet.getSheetName());
		
		
		if(objClazz == null || svcObj == null) {
			log.error("Domain name "+domainName+" or service is null. Skip this work sheet: "+sheetName);
			return R.error(MSG_CODE_ERR_INCORRECT_SHEET_NAME, "域对象:"+domainName+" 不存在或未注册.  跳过处理表单: "+sheetName);
		}
		
		List<String> fieldNames;
		try {
			fieldNames = parseHeaderFieldNames(sheet.getRow(0));
			if(fieldNames.isEmpty()) {
				String err = "表单头部（第一行）为空或格式错误.  跳过处理表单 "+sheetName;
				return R.error(MSG_CODE_ERR_INCORRECT_SHEET_HEADER, err);
			}
		} catch (ExcelUtilException e) {
			String err = "表单头部（第一行）解析失败, "+e.getMessage()+". 跳过处理表单: "+sheetName;
			return R.error(MSG_CODE_ERR_INCORRECT_SHEET_HEADER, err);
		}
		
			
		int cnt = sheet.getPhysicalNumberOfRows();
		int idx = IDX_DATA_START;
		Map<Integer, Object> rowObj = new LinkedHashMap<>();
		R result = new R();		
		for (idx = IDX_DATA_START; idx < cnt; idx++) {
			try {
				rowObj.put(idx, parseObject(sheet.getRow(idx), objClazz, fieldNames));
			} catch (ExcelUtilException e) {
				String err = "无法转换行到域对象，行号: " + (idx+1);
				result.put(Integer.toString(idx+1), err);
			}
		}			
		
		for (Map.Entry<Integer, Object> entry : rowObj.entrySet()) {
			try {
				insert(svcObj, insert, entry.getValue());
			} catch (ExcelUtilException e) {
				idx = entry.getKey();
				String err = "数据库插入失败，行号:" + (idx+1);
				result.put(Integer.toString(idx+1), err);
			}
		}
		
		if(result.isEmpty()) {
			result = R.ok("成功导入表单, "+sheet.getSheetName());
		}
		else {
			result.put("msg", "表单部分或全部数据导入失败, "+sheet.getSheetName());
			result.put("code", MSG_CODE_ERR_PARTIAL_FAILURE);			
		}		
		return result;
	}
	
	private static void insert(Object svcObj, Method insert, Object obj) throws ExcelUtilException {
		
		try {
			insert.invoke(svcObj, obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			String err = "数据库插入失败,  "+e.getMessage();
			log.error(err);
			throw new ExcelUtilException(err, e);
		}
	}	

	
	//sheet name rule must be as: DomainClassName(Any description), the quote and content inside quote are optional
	private static String parseDomainNameFromSheetName(String sheetName) {
		if(sheetName.indexOf("(") > 0) { 
			sheetName = sheetName.substring(0, sheetName.indexOf("("));
		}
		return sheetName.toUpperCase();
	}

	
	public static Method getMethod(Object service, Class<?> objClazz) throws ExcelUtilException {
		
		String objClazzName = objClazz.getSimpleName();		
		Class<?> svcClazz = service.getClass();	
		
		try {
			Method method = svcClazz.getMethod("insert"+objClazzName, objClazz); //mybatis insert方法的命名规则必须是：　insert[DomainClassName](DomainClassType)
			return method;
		} catch (NoSuchMethodException e) {
			log.info("Cannot find method: "+"insert"+objClazzName+" on class "+svcClazz.getName()+". Will check save method.");			
		} catch (SecurityException e) {
			log.info("Cannot access method: "+"insert"+objClazzName+" on class "+svcClazz.getName()+". Will check save method.");			
		}
		
	
		try {
			Method method = svcClazz.getMethod("save", Object.class); //spring CRUD repo 的单条记录的insert方法是：　save(Object)
			return method;
		} catch (NoSuchMethodException e) {
			log.error("Cannot find method: " + "insert" + objClazzName + " on class " + svcClazz.getName());
			throw new ExcelUtilException(
					"Cannot find/access method: " + "insert" + objClazzName + " or save on class " + svcClazz.getName());
		} catch (SecurityException e) {
			log.error("Cannot access method: " + "save on class " + svcClazz.getName());
			throw new ExcelUtilException(
					"Cannot access/find method: " + "insert" + objClazzName + " or save on class " + svcClazz.getName());
		}
			
	}
	/* END: Interface to call service to insert and return detailed result using json */

}
