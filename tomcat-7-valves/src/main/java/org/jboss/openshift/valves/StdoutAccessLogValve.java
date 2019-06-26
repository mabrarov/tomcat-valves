/**
 *  Copyright 2017 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package org.jboss.openshift.valves;

import org.apache.catalina.valves.AccessLogValve;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:rmartine@redhat.com">Ricardo Martinelli</a>
 */
public class StdoutAccessLogValve extends AccessLogValve {

    private static final String VALVE_INFO = StdoutAccessLogValve.class.getName();
    Logger log = Logger.getLogger(this.getClass().getName());

    public StdoutAccessLogValve() {
        // AccessLogValve#rotatable is true by default while we need false to avoid
        // invocation of AccessLogValve#restore method in AccessLogValve#startInternal
        // because AccessLogValve#restore method works with files
        rotatable = false;
    }

    @Override
    public void setRotatable(boolean rotatable) {
        // Prevent setting rotatable to true to avoid
        // invocation of AccessLogValve#restore method in AccessLogValve#startInternal
        // because AccessLogValve#restore method works with files
    }

    @Override
    public String getInfo() {
        return VALVE_INFO;
    }

    @Override
    public void log(String message) {
        log.info(message);
    }

    @Override
    protected synchronized void open() {
        // Nothing to do. Overridden to avoid creation of file
    }
}