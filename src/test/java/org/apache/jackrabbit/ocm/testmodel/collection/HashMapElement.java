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
package org.apache.jackrabbit.ocm.testmodel.collection;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.jackrabbit.ocm.manager.collectionconverter.ManageableMap;

/**
 * No very useful class.
 * This is just there to test custom ManageableMap implementation
 *
 * @author <a href="mailto:christophe.lombart@gmail.com">Christophe Lombart</a>
 *
 */
public class HashMapElement implements ManageableMap
{

	private HashMap<String , Element> map = new HashMap<String, Element>();
    /**
     * @see org.apache.jackrabbit.ocm.manager.collectionconverter.ManageableCollection#addObject(java.lang.Object)
     */
    public void addObject(Object object)
    {
        if (object instanceof Element)
        {
        	map.put(((Element)object).getId(), (Element)object);
        }
    }

    public void addObject(Object key, Object object)
    {
        if (object instanceof Element)
        {
        	map.put((String) key, (Element)object);
        }
    }

    /**
     * @see org.apache.jackrabbit.ocm.manager.collectionconverter.ManageableCollection#getIterator()
     */
    public Iterator getIterator()
    {
       return  map.values().iterator();
    }

	public int getSize()
	{

		return map.size();
	}

	public Object getObjects()
	{
		return map;
	}

}
