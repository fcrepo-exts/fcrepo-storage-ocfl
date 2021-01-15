/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fcrepo.storage.ocfl.exception;

import java.util.List;
import java.util.Objects;

/**
 * Indicates that the actual checksum of a file did not match the expectation
 *
 * @author pwinckles
 */
public class ChecksumMismatchException extends RuntimeException {

    private final List<String> problems;

    private String message;

    public ChecksumMismatchException(final List<String> problems) {
        this.problems = Objects.requireNonNull(problems, "problems cannot be null");
    }

    @Override
    public String getMessage() {
        if (message == null) {
            final var builder = new StringBuilder("The following checksums did not match: ");

            var index = 1;

            for (var problem : problems) {
                builder.append("\n  ").append(index++).append(". ");
                builder.append(problem);
            }

            message = builder.toString();
        }

        return message;
    }

    public List<String> getProblems() {
        return problems;
    }

}
