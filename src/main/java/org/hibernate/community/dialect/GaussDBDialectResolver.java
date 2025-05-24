/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.community.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;

public class GaussDBDialectResolver implements DialectResolver {
	public enum GaussDBDatabase {
		GAUSSDB {
			@Override
			public Dialect createDialect(DialectResolutionInfo info) {
				return new GaussDBDialect( info );
			}

			@Override
			public boolean productNameMatches(String databaseName) {
				return "GaussDB".equals( databaseName );
			}

			@Override
			public String getDriverClassName(String jdbcUrl) {
				return "com.huawei.gaussdb.jdbc.Driver";
			}
		};

		/**
		 * Does this database match the given metadata?
		 */
		public boolean matchesResolutionInfo(DialectResolutionInfo info) {
			return productNameMatches( info.getDatabaseName() );
		}

		/**
		 * Does this database have the given product name?
		 */
		public abstract boolean productNameMatches(String productName);

		/**
		 * Create a {@link Dialect} for the given metadata.
		 */
		public abstract Dialect createDialect(DialectResolutionInfo info);

		/**
		 * Get the name of the JDBC driver class for this database,
		 * or null if we're not too sure what it should be.
		 */
		public String getDriverClassName(String jdbcUrl) {
			return null;
		}

		/**
		 * Get the JDBC URL prefix used by this database.
		 */
		public String getUrlPrefix() {
			return "jdbc:" + toString().toLowerCase() + ":";
		}

		/**
		 * Does the given JDBC URL connect to this database?
		 */
		public boolean matchesUrl(String jdbcUrl) {
			return jdbcUrl.toLowerCase().startsWith( getUrlPrefix() );
		}
	}

	@Override
	public Dialect resolveDialect(DialectResolutionInfo info) {
		for ( GaussDBDatabase database : GaussDBDatabase.values() ) {
			if ( database.matchesResolutionInfo( info ) ) {
				return database.createDialect( info );
			}
		}

		return null;
	}

}
