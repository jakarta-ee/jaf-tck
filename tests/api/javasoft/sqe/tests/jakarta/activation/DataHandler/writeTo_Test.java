/*
 * Copyright (c) 1997, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javasoft.sqe.tests.jakarta.activation.DataHandler;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/**
 * writeTo() api writes out to OutputStream for a given DataHandler object
 * created with DataSource. If this operation is successfull then this
 * testcase passes, it fails if it throws an exception.
 */

public class writeTo_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null; 	  // DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  // a Dathandler created from a DataSource;

private Object obj = null;     	  // Object for DataHandler(Object, String)
private DataHandler objdh = null; // a DataHandler created from an Object/mimeType
private DataSource objds = null;  // DataSource returned from getDataSource for the Object case

public static void main(String argv[])
{
	writeTo_Test lTest = new writeTo_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status writeToTest()
{	
	obj = new Object(); // just a random object
	objdh = new DataHandler(obj, "foo/goo");

	if ( objdh == null )
	     return Status.failed("Failed to create first DataHandler object");

	OutputStream bos1 = new ByteArrayOutputStream();

        if ( bos1 == null )
             return Status.failed("Failed to create first ByteArray output stream object");

	IOException refex = null;

	try {

		objdh.writeTo(bos1);		// API TEST

		return Status.failed("DataHandler(Object,String).writeTo didn't throw");
	} catch(IOException ioex) {

	//	ioex.printStackTrace();
		if (!(ioex instanceof UnsupportedDataTypeException)) 
			return Status.failed("Failed: writeTo threw " + ioex.toString() +
						" not " + refex.toString());
	}

        // setup the DataHandler
        ds = new FileDataSource(kFileName);
        dsdh = new DataHandler(ds);

        if ( dsdh == null )
             return Status.failed("Failed to create second DataHandler object");

	// we have a dh and ds with content, so test writeTo as well.
	ByteArrayOutputStream bos2 = new ByteArrayOutputStream();

        if ( bos2 == null )
             return Status.failed("Failed to create second ByteArray output stream object");

	try {
		dsdh.writeTo(bos2);		// API TEST
	} catch (IOException ex) {
		ex.printStackTrace();
		return Status.failed("DataHandler(DataSource).writeTo() threw " + ex.toString());
	}
	byte bytes[]  = {0,1,2,3,4,5,6,7,8,9};
	DataSource no_in_ds = new TestDataSource(bytes);
	DataHandler no_in_dh = new DataHandler(no_in_ds);

        if ( no_in_dh == null )
             return Status.failed("Failed to create third DataHandler object");

	OutputStream bos3 = new ByteArrayOutputStream();

        if ( bos3 == null )
             return Status.failed("Failed to create third ByteArray output stream object");

	try {
		no_in_dh.writeTo(bos3);		// API TEST
	} catch(IOException ex) {
	    return Status.failed("Failed: writeTo threw " + ex.toString() + " not "
                              + refex.toString());
	} finally {
		try {
			bos3.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Status.failed("Failed to close output stream");
		}
	}
	return Status.passed("writeTo() test succeeded");
}

}
