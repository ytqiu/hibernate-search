/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.engine.search.dsl;


import java.util.Collection;

/**
 * @author Yoann Rodiere
 */
public interface SearchQueryContext<Q> {

	SearchQueryContext<Q> routing(String routingKey);

	SearchQueryContext<Q> routing(Collection<String> routingKeys);

	Q build();

}