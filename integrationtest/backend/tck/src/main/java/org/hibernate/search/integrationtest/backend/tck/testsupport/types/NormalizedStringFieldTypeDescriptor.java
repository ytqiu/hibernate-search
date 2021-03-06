/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.integrationtest.backend.tck.testsupport.types;

import java.util.Optional;

import org.hibernate.search.engine.backend.types.dsl.IndexFieldTypeFactoryContext;
import org.hibernate.search.engine.backend.types.dsl.StandardIndexFieldTypeContext;
import org.hibernate.search.integrationtest.backend.tck.testsupport.configuration.DefaultAnalysisDefinitions;
import org.hibernate.search.integrationtest.backend.tck.testsupport.types.expectations.FieldProjectionExpectations;
import org.hibernate.search.integrationtest.backend.tck.testsupport.types.expectations.FieldSortExpectations;
import org.hibernate.search.integrationtest.backend.tck.testsupport.types.expectations.MatchPredicateExpectations;
import org.hibernate.search.integrationtest.backend.tck.testsupport.types.expectations.RangePredicateExpectations;

public class NormalizedStringFieldTypeDescriptor extends FieldTypeDescriptor<String> {

	NormalizedStringFieldTypeDescriptor() {
		super( String.class, "normalizedString" );
	}

	@Override
	public StandardIndexFieldTypeContext<?, String> configure(IndexFieldTypeFactoryContext fieldContext) {
		return fieldContext.asString().normalizer( DefaultAnalysisDefinitions.NORMALIZER_LOWERCASE.name );
	}

	@Override
	public Optional<MatchPredicateExpectations<String>> getMatchPredicateExpectations() {
		return Optional.of( new MatchPredicateExpectations<>(
				"irving", "Auster",
				"Irving"
		) );
	}

	@Override
	public Optional<RangePredicateExpectations<String>> getRangePredicateExpectations() {
		return Optional.of( new RangePredicateExpectations<>(
				"Aaron", "george", "Zach",
				"cecilia", "Roger"
		) );
	}

	@Override
	public Optional<FieldSortExpectations<String>> getFieldSortExpectations() {
		return Optional.of( new FieldSortExpectations<>(
				"Cecilia", "george", "Stefany",
				// TODO Fix HSEARCH-3387, then mix capitalization here
				"aaron", "daniel", "roger", "zach"
		) );
	}

	@Override
	public Optional<FieldProjectionExpectations<String>> getFieldProjectionExpectations() {
		return Optional.of( new FieldProjectionExpectations<>(
				"Aaron", "george", "Zach"
		) );
	}
}
