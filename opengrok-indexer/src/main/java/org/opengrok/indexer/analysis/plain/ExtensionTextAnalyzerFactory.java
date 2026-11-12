/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2026, Oracle and/or its affiliates. All rights reserved.
 */
package org.opengrok.indexer.analysis.plain;

import org.opengrok.indexer.analysis.AbstractAnalyzer;
import org.opengrok.indexer.analysis.AnalyzerFactory;
import org.opengrok.indexer.analysis.FileAnalyzerFactory;

/**
 * Plain-text analyzer factory with a dedicated file type.
 */
public final class ExtensionTextAnalyzerFactory extends FileAnalyzerFactory {

    private final String typeName;

    public ExtensionTextAnalyzerFactory(String name, String typeName, String... suffixes) {
        super(null, null, suffixes, null, null, "text/plain", AbstractAnalyzer.Genre.PLAIN, name, true);
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    protected AbstractAnalyzer newAnalyzer() {
        return new ExtensionTextAnalyzer(this);
    }

    private static final class ExtensionTextAnalyzer extends PlainAnalyzer {

        private ExtensionTextAnalyzer(AnalyzerFactory factory) {
            super(factory);
        }

        @Override
        public String getFileTypeName() {
            return ((ExtensionTextAnalyzerFactory) getFactory()).getTypeName();
        }
    }
}