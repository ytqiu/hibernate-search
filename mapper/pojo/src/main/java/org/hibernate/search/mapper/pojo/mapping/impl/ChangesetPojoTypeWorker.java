/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.mapper.pojo.mapping.impl;

import java.util.concurrent.CompletableFuture;

import org.hibernate.search.engine.backend.document.spi.DocumentState;
import org.hibernate.search.engine.backend.index.spi.ChangesetIndexWorker;
import org.hibernate.search.mapper.pojo.mapping.spi.PojoSessionContext;

/**
 * @author Yoann Rodiere
 */
class ChangesetPojoTypeWorker<D extends DocumentState, E> extends PojoTypeWorker<D, E, ChangesetIndexWorker<D>> {

	public ChangesetPojoTypeWorker(PojoTypeManager<?, E, D> typeManager, PojoSessionContext sessionContext,
			ChangesetIndexWorker<D> delegate) {
		super( typeManager, sessionContext, delegate );
	}

	public void prepare() {
		getDelegate().prepare();
	}

	public CompletableFuture<?> execute() {
		/*
		 * No need to call prepare() here: we don't do anything special ourselves when preparing,
		 * and delegates are supposed to handle execute() even without a prior call to prepare().
		 */
		return getDelegate().execute();
	}

}