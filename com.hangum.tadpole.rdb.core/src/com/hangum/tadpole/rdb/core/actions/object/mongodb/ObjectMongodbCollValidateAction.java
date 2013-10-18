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
package com.hangum.tadpole.rdb.core.actions.object.mongodb;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine;
import com.hangum.tadpold.commons.libs.core.define.PublicTadpoleDefine.DB_ACTION;
import com.hangum.tadpole.mongodb.core.dialogs.collection.CollValidateDialog;
import com.hangum.tadpole.rdb.core.actions.object.AbstractObjectAction;
import com.hangum.tadpole.sql.dao.mysql.TableDAO;
import com.hangum.tadpole.sql.dao.system.UserDBDAO;

/**
 * Collection validate
 * 
 * referencd : http://docs.mongodb.org/manual/reference/command/validate/
 * 
 * @author hangum
 *
 */
public class ObjectMongodbCollValidateAction extends AbstractObjectAction {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(ObjectMongodbCollValidateAction.class);

	public final static String ID = "com.hangum.db.browser.rap.core.actions.object.mongo.collection.validate";
	
	public ObjectMongodbCollValidateAction(IWorkbenchWindow window, PublicTadpoleDefine.DB_ACTION actionType, String title) {
		super(window, actionType);
		setId(ID + actionType.toString());
		setText(title);
	}

	@Override
	public void run(IStructuredSelection selection, UserDBDAO userDB, DB_ACTION actionType) {
		TableDAO collDAO = (TableDAO) selection.getFirstElement();

		CollValidateDialog dialog = new CollValidateDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), userDB, collDAO.getName());
		dialog.open();
	}
	
}