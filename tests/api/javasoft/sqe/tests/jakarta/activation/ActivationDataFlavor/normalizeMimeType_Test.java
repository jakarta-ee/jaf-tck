/*
 * Copyright (c) 1996, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.activation.ActivationDataFlavor;

import java.io.*;
import jakarta.activation.*;
import com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/**
 * We create a TestActivationDataFlavor object then invoke
 * testNormalizeMimeType() method which in turn calls
 * normalizeMimeType() api, if it returns the expected
 * string value then this testcase passes, otherwsie it fails.
 */

public class normalizeMimeType_Test extends MultiTest {

    // main
    public static void main(String[] args)
    { 
	Test t = new normalizeMimeType_Test(); 
	Status s = t.run(args, new PrintWriter(System.err, true), new PrintWriter(System.out, true)); 
	s.exit(); 
    }

    // Tests for equals:
    public Status normalizeMimeType()
    {
	String mimeType = "text/plain";
	String expectedValue = mimeType;

	// actual type doesn't matter
	TestActivationDataFlavor adf = new TestActivationDataFlavor("text/plain","My DataFlavor");

	if(expectedValue.equals(adf.testNormalizeMimeType(mimeType)))
	    return Status.passed("normalizeMimeType() test succeeded");
	else
	    return Status.failed("normalizeMimeType() test failed: "
				+ adf.testNormalizeMimeType(mimeType) + " != " + expectedValue);
    }
}
