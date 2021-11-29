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

package javasoft.sqe.tests.jakarta.activation.MimeTypeParameterList;

import	java.beans.*;
import	java.io.*;
import	jakarta.activation.*;
import	java.net.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/**
 * Create an instance of MimeTypeParameterList, then use it to call get() method,
 * with a string argument, if it returns a value(non-null/null) associated with
 * passed in argument, then this test passes, otherwise it fails.
 */

public class get_Test extends MultiTest
{
public static void main(String argv[])
{
	get_Test lTest = new get_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getTest()
{
        try {
              MimeTypeParameterList mtpl =  new MimeTypeParameterList(";charset=us-ascii;isoset=iso-9000;foo=abc");

              if( mtpl == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object using constructor");
	      String value = mtpl.get("charset");	// API TEST

	      if( !value.equals("us-ascii") )
		  return Status.failed("get(String) failed to get expected "+value+" value");

              value = mtpl.get("foo");       // API TEST

              if( !value.equals("abc") )
                  return Status.failed("get(String) failed to get expected "+value+" value");

              value = mtpl.get("xyz");       // API TEST

              if( value != null )
                  return Status.failed("get(xyz) failed to get expected "+value+" value");

        } catch(Exception ex) {
                return Status.failed("get(string) threw " + ex.toString());
        }

	return Status.passed("get(String) test succeeded");
}

}
