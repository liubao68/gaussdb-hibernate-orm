/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.community.dialect;

import java.util.Objects;

import org.hibernate.boot.registry.selector.spi.DialectSelector;
import org.hibernate.dialect.Dialect;

public class GaussDBDialectSelector implements DialectSelector {
	@Override
	public Class<? extends Dialect> resolve(String name) {
		Objects.requireNonNull( name );
		if ( name.isEmpty() ) {
			return null;
		}
		switch ( name ) {
			case "GaussDB":
				return GaussDBDialect.class;
		}
		return null;
	}
}
