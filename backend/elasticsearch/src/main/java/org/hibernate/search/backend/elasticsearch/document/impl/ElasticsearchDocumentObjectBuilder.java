/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.elasticsearch.document.impl;

import java.util.Objects;

import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.backend.elasticsearch.document.model.impl.ElasticsearchIndexSchemaObjectNode;
import org.hibernate.search.backend.elasticsearch.gson.impl.JsonAccessor;
import org.hibernate.search.engine.logging.impl.Log;
import org.hibernate.search.util.spi.LoggerFactory;

import com.google.gson.JsonObject;

/**
 * @author Yoann Rodiere
 */
public class ElasticsearchDocumentObjectBuilder implements DocumentElement {

	private static final Log log = LoggerFactory.make( Log.class );

	private final ElasticsearchIndexSchemaObjectNode schemaNode;
	private final JsonObject content;

	public ElasticsearchDocumentObjectBuilder() {
		this( ElasticsearchIndexSchemaObjectNode.root(), new JsonObject() );
	}

	ElasticsearchDocumentObjectBuilder(ElasticsearchIndexSchemaObjectNode schemaNode, JsonObject content) {
		this.schemaNode = schemaNode;
		this.content = content;
	}

	public <T> void add(ElasticsearchIndexSchemaObjectNode expectedParentNode, JsonAccessor<T> relativeAccessor, T value) {
		if ( !Objects.equals( expectedParentNode, schemaNode ) ) {
			throw log.invalidParentDocumentObjectState( expectedParentNode.getAbsolutePath(), schemaNode.getAbsolutePath() );
		}
		relativeAccessor.add( content, value );
	}

	public JsonObject build() {
		return content;
	}

}