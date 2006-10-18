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
package org.apache.portals.graffito.jcr.mapper.model;

/**
 * PropertyDefDescriptor is used by the node type management tools based on
 * class descriptors to manage property definitions
 *
 * @author <a href="mailto:fmeschbe[at]apache[dot]com">Felix Meschberger</a>
 */
public interface PropertyDefDescriptor {

    /**
     * @return Returns the name of the property.
     */
    String getJcrName();

    /**
     * @return Returns the property type name.
     */
    String getJcrType();

    /**
     * @return Whether the property is auto created.
     */
    boolean isJcrAutoCreated();

    /**
     * @return Whether the property is mandatory.
     */
    boolean isJcrMandatory();

    /**
     * @return What to do on parent version creation.
     */
    String getJcrOnParentVersion();

    /**
     * @return Whether the property is protected.
     */
    boolean isJcrProtected();

    /**
     * @return Whether the property is multi-valued.
     */
    boolean isJcrMultiple();
}