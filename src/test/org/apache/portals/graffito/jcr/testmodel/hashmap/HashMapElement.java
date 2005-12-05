/*
 * Copyright 2000-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.portals.graffito.jcr.testmodel.hashmap;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.portals.graffito.jcr.persistence.collectionconverter.ManageableCollection;

/** 
 *
 * @author <a href="mailto:christophe.lombart@gmail.com">Christophe Lombart</a>
 * 
 */
public class HashMapElement extends HashMap implements ManageableCollection
{

    /**
     * @see org.apache.portals.graffito.jcr.persistence.collectionconverter.ManageableCollection#addObject(java.lang.Object)
     */
    public void addObject(Object object)
    {
        if (object instanceof Element)
        {
            this.put(((Element)object).getId(), object);
        }
    }

    /**
     * @see org.apache.portals.graffito.jcr.persistence.collectionconverter.ManageableCollection#getIterator()
     */
    public Iterator getIterator()
    {
       return  this.values().iterator();
    }
    
	public int getSize()
	{
		
		return this.size();
	}
    

}