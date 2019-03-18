/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.language.gremlin;

import org.apache.tinkerpop.machine.bytecode.Bytecode;
import org.apache.tinkerpop.machine.bytecode.Instruction;
import org.apache.tinkerpop.machine.bytecode.Symbols;
import org.apache.tinkerpop.machine.coefficient.Coefficient;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public final class TraversalUtil {

    private TraversalUtil() {
        // do nothing
    }

    public static <C> Bytecode<C> getBytecode(final Traversal<C, ?, ?> traversal) {
        return traversal.bytecode;
    }

    public static <C, S, E> void insertRepeatInstruction(final Bytecode<C> bytecode, final Coefficient<C> currentCoefficient, final char type, final Object argument) {
        final Instruction<C> lastInstruction = bytecode.lastInstruction();
        if (lastInstruction.op().equals(Symbols.REPEAT))
            lastInstruction.addArgs(type, argument);
        else
            bytecode.addInstruction(currentCoefficient, Symbols.REPEAT, type, argument);
    }
}