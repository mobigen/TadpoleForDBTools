/*******************************************************************************
 * Copyright (c) 2013 hangum.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.hangum.tadpole.engine.sql.util.resultset;

import java.sql.ResultSet;
import java.util.Map;

import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine;
import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine.QUERY_EXECUTE_STATUS;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * 쿼리 실행결과 dto
 * 
 * @author hangum
 *
 */
public class QueryExecuteResultDTO extends ResultSetUtilDTO {
	private PublicTadpoleDefine.QUERY_EXECUTE_STATUS execute_status = QUERY_EXECUTE_STATUS.SUCCESS;
	private String strExceptionMsg = "";
	
	public QueryExecuteResultDTO() {
	}

	public QueryExecuteResultDTO(
			PublicTadpoleDefine.SQL_STATEMENTS_TYPE statementType, 
			UserDBDAO userDB, 
			Map<Integer, String> columnName, 
			Map<Integer, String> columnTableName, 
			Map<Integer, Integer> columnType, 
			TadpoleResultSet dataList
	) {
		super(statementType, userDB, columnName, columnTableName, columnType, dataList);
	}

	public QueryExecuteResultDTO(PublicTadpoleDefine.SQL_STATEMENTS_TYPE statementType, UserDBDAO userDB, boolean isShowRownum, ResultSet rs, int queryResultCount) throws Exception {
		super(statementType, userDB, isShowRownum, rs, queryResultCount);
	}

	/**
	 * @return the execute_status
	 */
	public PublicTadpoleDefine.QUERY_EXECUTE_STATUS getExecute_status() {
		return execute_status;
	}

	/**
	 * @param execute_status the execute_status to set
	 */
	public void setExecute_status(
			PublicTadpoleDefine.QUERY_EXECUTE_STATUS execute_status) {
		this.execute_status = execute_status;
	}

	/**
	 * @return the strExceptionMsg
	 */
	public String getStrExceptionMsg() {
		return strExceptionMsg;
	}

	/**
	 * @param strExceptionMsg the strExceptionMsg to set
	 */
	public void setStrExceptionMsg(String strExceptionMsg) {
		this.strExceptionMsg = strExceptionMsg;
	}
	
	

}
