package gjxt.portal.api.common.utils;


public class ExcelUtilException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3391054483823149372L;

	public ExcelUtilException(String msg, Exception nestedEx) {
		super(msg, nestedEx);
	}
	
	public ExcelUtilException(String msg) {
		super(msg);
	}
	
}
