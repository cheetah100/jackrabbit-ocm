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
package org.apache.jackrabbit.ocm.manager.objectconverter.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.InvocationHandler;

/**
 * Parent Class of the OCM Lazy Loaders
 * 
 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
 */
public abstract class AbstractLazyLoader implements InvocationHandler, Serializable {

	/**
	 * The logger
	 */
	private final static Logger log = LoggerFactory.getLogger(AbstractLazyLoader.class);

	/**
	 * The proxified instance
	 */
	private Object target = null;

	/**
	 * Indicate if the proxy has been loaded
	 */
	private boolean initialized = false;

	/**
	 * Return the proxified instance
	 * 
	 * @return the proxified instance
	 */
	protected Object getTarget() {
		if (!initialized) {
			target = fetchTarget();
			initialized = true;
			if (log.isDebugEnabled()) {
				log.debug("Target loaded");
			}
		}
		return target;
	}

	/**
	 * Fetch the proxified instance
	 * 
	 * @return the proxified instance
	 */
	protected abstract Object fetchTarget();

	/**
	 * Getter of property initialized
	 * 
	 * @return initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Invoke proxy methods : delegate to proxified instance except for OcmProxy
	 * methods that are intercepted (because not concretely implemented)
	 * 
	 * @see net.sf.cglib.proxy.InvocationHandler#invoke(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (!(proxy instanceof OcmProxy)) {
			throw new IllegalArgumentException("proxy should implement OcmProxy");
		}

		// proxified methods without concrete implementation
		if (args.length == 0) {
			if (method.getName().equals("isInitialized")) {
				return isInitialized();

			} else if (method.getName().equals("fetch")) {
				getTarget();
				return null;
			}
		}

		Object returnValue = null;

		if (Modifier.isPublic(method.getModifiers())) {
			if (!method.getDeclaringClass().isInstance(getTarget())) {
				throw new ClassCastException(getTarget().getClass().getName());
			}
			returnValue = method.invoke(getTarget(), args);
		} else {
			if (!method.isAccessible()) {
				method.setAccessible(true);
			}
			returnValue = method.invoke(getTarget(), args);
		}
		return returnValue == getTarget() ? proxy : returnValue;
	}
}
