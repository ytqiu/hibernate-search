/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.engine.service.beanresolver.spi;

import org.hibernate.search.engine.service.spi.Service;
import org.hibernate.search.engine.service.spi.Stoppable;


/**
 * Provides a way to resolve references to externally defined beans.
 * <p>
 * Used in some cases when beans have to be plugged in Hibernate Search
 * (for instance for field bridges and class bridges).
 *
 * @hsearch.experimental This type is under active development.
 *    You should be prepared for incompatible changes in future releases.
 * @since 5.8
 */
public interface BeanResolver extends Service, Stoppable {

	<T> T resolve(Class<?> reference, Class<T> expectedClass);

}
