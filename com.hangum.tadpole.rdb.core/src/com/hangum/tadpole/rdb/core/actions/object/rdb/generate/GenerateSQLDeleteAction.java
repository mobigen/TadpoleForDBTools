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
package com.hangum.tadpole.rdb.core.actions.object.rdb.generate;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;

import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine;
import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine.DB_ACTION;
import com.hangum.tadpole.commons.exception.dialog.ExceptionDetailsErrorDialog;
import com.hangum.tadpole.engine.query.dao.mysql.TableColumnDAO;
import com.hangum.tadpole.engine.query.dao.mysql.TableDAO;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.hangum.tadpole.rdb.core.Activator;
import com.hangum.tadpole.rdb.core.Messages;
import com.hangum.tadpole.rdb.core.util.FindEditorAndWriteQueryUtil;
import com.hangum.tadpole.rdb.core.viewers.object.sub.rdb.TadpoleObjectQuery;

/**
 * generate sql statement     
 * 
 * @author hangum
 *
 */
public class GenerateSQLDeleteAction extends GenerateSQLSelectAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GenerateSQLDeleteAction.class);
	public final static String ID = "com.hangum.db.browser.rap.core.actions.object.GenerateSQLDeleteAction"; //$NON-NLS-1$
	
	public GenerateSQLDeleteAction(IWorkbenchWindow window, PublicTadpoleDefine.DB_ACTION actionType, String title) {
		super(window, actionType, title);
	}
	
	@Override
	public void run(IStructuredSelection selection, UserDBDAO userDB, DB_ACTION actionType) {
		StringBuffer sbSQL = new StringBuffer();
		try {
			TableDAO tableDAO = (TableDAO)selection.getFirstElement();
			
			List<TableColumnDAO> showTableColumns = TadpoleObjectQuery.makeShowTableColumns(userDB, tableDAO);
			sbSQL.append("DELETE FROM " + tableDAO.getSysName() + " "); //$NON-NLS-1$ //$NON-NLS-2$
			sbSQL.append(PublicTadpoleDefine.LINE_SEPARATOR + "WHERE " + PublicTadpoleDefine.LINE_SEPARATOR); //$NON-NLS-1$
			int cnt = 0;
			for (int i=0; i<showTableColumns.size(); i++) {
				TableColumnDAO dao = showTableColumns.get(i);
				if(PublicTadpoleDefine.isKEY(dao.getKey())) {
					if(cnt == 0) sbSQL.append("\t" + dao.getSysName() + " = ? " + PublicTadpoleDefine.LINE_SEPARATOR); //$NON-NLS-1$ //$NON-NLS-2$
					else sbSQL.append("\tAND " + dao.getSysName() + " = ?"); //$NON-NLS-1$ //$NON-NLS-2$
					cnt++;
				}				
			}
			sbSQL.append(PublicTadpoleDefine.SQL_DELIMITER); //$NON-NLS-1$
			
			FindEditorAndWriteQueryUtil.run(userDB, sbSQL.toString(), PublicTadpoleDefine.DB_ACTION.TABLES);
		} catch(Exception e) {
			logger.error(Messages.GenerateSQLDeleteAction_10, e);
			
			Status errStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e); //$NON-NLS-1$
			ExceptionDetailsErrorDialog.openError(null, "Error", Messages.GenerateSQLDeleteAction_0, errStatus); //$NON-NLS-1$
		}
	}

}
