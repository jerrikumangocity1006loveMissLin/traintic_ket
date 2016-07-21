package org.mangocube.corenut.commons.exception;

import java.util.List;

public class ErrorRecordUtil {

	public static List<Object> getErrorParameters(ErrorRecord record) {
		List<Object> list = record.getErrorParameters();
		return list;
	}
}
