/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.mapper.orm.search.spi;

import org.hibernate.search.engine.search.dsl.predicate.SearchPredicateFactoryContext;
import org.hibernate.search.engine.search.dsl.projection.SearchProjectionFactoryContext;
import org.hibernate.search.engine.search.dsl.sort.SearchSortContainerContext;
import org.hibernate.search.mapper.orm.hibernate.FullTextQueryResultDefinitionContext;
import org.hibernate.search.mapper.pojo.search.PojoReference;

public interface HibernateOrmSearchTarget<T> {

	FullTextQueryResultDefinitionContext<T> jpaQuery();

	SearchPredicateFactoryContext predicate();

	SearchSortContainerContext sort();

	SearchProjectionFactoryContext<PojoReference, T> projection();

}
