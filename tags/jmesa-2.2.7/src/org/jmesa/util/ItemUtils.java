/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmesa.util;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * General utilities to process the Collecton of Beans or the Collection of
 * Maps. Most methods wrap or add value to the commons Beanutils.
 * 
 * @since 2.1
 * @author Jeff Johnston
 */
public class ItemUtils {
    private static Logger logger = LoggerFactory.getLogger(ItemUtils.class);

    private ItemUtils() {
        // hide constructor
    }

    /**
     * Get the value from the Bean or Map by property.
     * 
     * @param item The Bean or Map.
     * @param property The Bean attribute or Map key.
     * @return The value from the Bean or Map.
     */
    public static Object getItemValue(Object item, String property) {
        Object itemValue = null;

        try {
            if (item instanceof Map) {
                itemValue = ((Map<?,?>) item).get(property);
            } else {
                itemValue = PropertyUtils.getProperty(item, property);
            }
        } catch (Exception e) {
            logger.debug("item class " + item.getClass().getName() + " doesn't have property " + property);
        }

        return itemValue;
    }
}