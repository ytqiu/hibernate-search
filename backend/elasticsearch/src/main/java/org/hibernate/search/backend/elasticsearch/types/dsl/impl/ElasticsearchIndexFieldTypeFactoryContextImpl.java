/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.elasticsearch.types.dsl.impl;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.search.backend.elasticsearch.logging.impl.Log;
import org.hibernate.search.backend.elasticsearch.types.dsl.ElasticsearchIndexFieldTypeFactoryContext;
import org.hibernate.search.backend.elasticsearch.types.dsl.ElasticsearchJsonStringIndexFieldTypeContext;
import org.hibernate.search.engine.backend.types.dsl.StandardIndexFieldTypeContext;
import org.hibernate.search.engine.backend.types.dsl.StringIndexFieldTypeContext;
import org.hibernate.search.engine.spatial.GeoPoint;
import org.hibernate.search.util.common.reporting.EventContext;
import org.hibernate.search.util.common.logging.impl.LoggerFactory;

import com.google.gson.Gson;


/**
 * @author Yoann Rodiere
 */
public class ElasticsearchIndexFieldTypeFactoryContextImpl
		implements ElasticsearchIndexFieldTypeFactoryContext, ElasticsearchIndexFieldTypeBuildContext {

	private static final Log log = LoggerFactory.make( Log.class, MethodHandles.lookup() );

	private final EventContext eventContext;
	private final Gson userFacingGson;

	public ElasticsearchIndexFieldTypeFactoryContextImpl(EventContext eventContext, Gson userFacingGson) {
		this.eventContext = eventContext;
		this.userFacingGson = userFacingGson;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <F> StandardIndexFieldTypeContext<?, F> as(Class<F> inputType) {
		if ( String.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asString();
		}
		else if ( Integer.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asInteger();
		}
		else if ( Long.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asLong();
		}
		else if ( Boolean.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asBoolean();
		}
		else if ( LocalDate.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asLocalDate();
		}
		else if ( Instant.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asInstant();
		}
		else if ( GeoPoint.class.equals( inputType ) ) {
			return (StandardIndexFieldTypeContext<?, F>) asGeoPoint();
		}
		else {
			// TODO implement other types
			throw log.cannotGuessFieldType( inputType, getEventContext() );
		}
	}

	@Override
	public StringIndexFieldTypeContext<?> asString() {
		return new ElasticsearchStringIndexFieldTypeContext( this );
	}

	@Override
	public StandardIndexFieldTypeContext<?, Integer> asInteger() {
		return new ElasticsearchIntegerIndexFieldTypeContext( this );
	}

	@Override
	public StandardIndexFieldTypeContext<?, Long> asLong() {
		return new ElasticsearchLongIndexFieldTypeContext( this );
	}

	@Override
	public StandardIndexFieldTypeContext<?, Boolean> asBoolean() {
		return new ElasticsearchBooleanIndexFieldTypeContext( this );
	}

	@Override
	public StandardIndexFieldTypeContext<?, LocalDate> asLocalDate() {
		return new ElasticsearchLocalDateIndexFieldTypeContext( this );
	}

	@Override
	public StandardIndexFieldTypeContext<?, Instant> asInstant() {
		return new ElasticsearchInstantIndexFieldTypeContext( this );
	}

	@Override
	public StandardIndexFieldTypeContext<?, GeoPoint> asGeoPoint() {
		return new ElasticsearchGeoPointIndexFieldTypeContext( this );
	}

	@Override
	public ElasticsearchJsonStringIndexFieldTypeContext<?> asJsonString(String mappingJsonString) {
		return new ElasticsearchJsonStringIndexFieldTypeContextImpl( this, mappingJsonString );
	}

	@Override
	public EventContext getEventContext() {
		return eventContext;
	}

	@Override
	public Gson getUserFacingGson() {
		return userFacingGson;
	}
}
