/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.ocm.manager.jcrnodetype;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.jackrabbit.ocm.DigesterRepositoryTestBase;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.testmodel.File;
import org.apache.jackrabbit.ocm.testmodel.Folder;
import org.apache.jackrabbit.ocm.testmodel.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test inheritance with node type per concrete class (without  discreminator field)
 *
 * @author <a href="mailto:christophe.lombart@gmail.com">Christophe Lombart</a>
 */
public class DigesterJcrNodeTypeTest extends DigesterRepositoryTestBase {
	private final static Logger log = LoggerFactory.getLogger(DigesterJcrNodeTypeTest.class);


	public static Test suite() {
		// All methods starting with "test" will be executed in the test suite.
		return new TestSuite(DigesterJcrNodeTypeTest.class);
	}


	public void testRetrieveSingleton()
	{

		try
		{
			ObjectContentManager ocm = getObjectContentManager();

			//---------------------------------------------------------------------------------------------------------
			// Insert a  folder (class mapped to jcr:folder) with one file (class mapped to jcr:file)
			//---------------------------------------------------------------------------------------------------------			
            Resource resource = new Resource();
            resource.setData(new ByteArrayInputStream("this is the content".getBytes()));
            resource.setLastModified(Calendar.getInstance());
            resource.setMimeType("plain/text");
            File file = new File();
            file.setResource(resource);


            Folder folder = new Folder();
            folder.setPath("/folder1");
            folder.addChild(file);

            ocm.insert(folder);
			ocm.save();
			
			
			//---------------------------------------------------------------------------------------------------------
			// Retrieve a document object
			//---------------------------------------------------------------------------------------------------------						
			folder = (Folder) ocm.getObject( "/folder1");
			assertNotNull("folder is null", folder);
			log.info("Folder creation date : " + folder.getCreationDate());
			assertTrue("Invalid number of children", folder.getChildren().size() == 1);
			file = (File) folder.getChildren().iterator().next();
			assertNotNull("resource is null", file.getResource());	
			log.info("File resource calendar: " + file.getResource().getLastModified());	// The prop is autocreated
			
			
		}
		catch (Exception e)
		{
			log.error("testRetrieveSingleton failed", e);
			fail();
		}
			
	}	
}