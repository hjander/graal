/*
 * Copyright (c) 2016, Oracle and/or its affiliates.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oracle.truffle.llvm.parser.factories;

import com.oracle.truffle.llvm.LLVMOptimizations;
import com.oracle.truffle.llvm.nodes.base.LLVMAddressNode;
import com.oracle.truffle.llvm.nodes.base.LLVMExpressionNode;
import com.oracle.truffle.llvm.nodes.base.LLVMFunctionNode;
import com.oracle.truffle.llvm.nodes.base.floating.LLVM80BitFloatNode;
import com.oracle.truffle.llvm.nodes.base.floating.LLVMDoubleNode;
import com.oracle.truffle.llvm.nodes.base.floating.LLVMFloatNode;
import com.oracle.truffle.llvm.nodes.base.integers.LLVMI16Node;
import com.oracle.truffle.llvm.nodes.base.integers.LLVMI1Node;
import com.oracle.truffle.llvm.nodes.base.integers.LLVMI32Node;
import com.oracle.truffle.llvm.nodes.base.integers.LLVMI64Node;
import com.oracle.truffle.llvm.nodes.base.integers.LLVMI8Node;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVM80BitFloatProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMAddressProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMDoubleProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMFloatProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMFunctionProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMI16ProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMI1ProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMI32ProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMI64ProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMProfilingSelectNodeFactory.LLVMI8ProfilingSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVM80BitFloatSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMAddressSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMDoubleSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMFloatSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMFunctionSelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMI16SelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMI1SelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMI32SelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMI64SelectNodeGen;
import com.oracle.truffle.llvm.nodes.others.LLVMSelectNodeFactory.LLVMI8SelectNodeGen;
import com.oracle.truffle.llvm.parser.LLVMBaseType;

public class LLVMSelectFactory {

    public static LLVMExpressionNode createSelect(LLVMBaseType llvmType, LLVMI1Node condition, LLVMExpressionNode trueValue, LLVMExpressionNode falseValue) {
        if (LLVMOptimizations.BRANCH_INJECTION_SELECT_INSTRUCTION) {
            switch (llvmType) {
                case I1:
                    return LLVMI1ProfilingSelectNodeGen.create(condition, (LLVMI1Node) trueValue, (LLVMI1Node) falseValue);
                case I8:
                    return LLVMI8ProfilingSelectNodeGen.create(condition, (LLVMI8Node) trueValue, (LLVMI8Node) falseValue);
                case I16:
                    return LLVMI16ProfilingSelectNodeGen.create(condition, (LLVMI16Node) trueValue, (LLVMI16Node) falseValue);
                case I32:
                    return LLVMI32ProfilingSelectNodeGen.create(condition, (LLVMI32Node) trueValue, (LLVMI32Node) falseValue);
                case I64:
                    return LLVMI64ProfilingSelectNodeGen.create(condition, (LLVMI64Node) trueValue, (LLVMI64Node) falseValue);
                case FLOAT:
                    return LLVMFloatProfilingSelectNodeGen.create(condition, (LLVMFloatNode) trueValue, (LLVMFloatNode) falseValue);
                case DOUBLE:
                    return LLVMDoubleProfilingSelectNodeGen.create(condition, (LLVMDoubleNode) trueValue, (LLVMDoubleNode) falseValue);
                case X86_FP80:
                    return LLVM80BitFloatProfilingSelectNodeGen.create(condition, (LLVM80BitFloatNode) trueValue, (LLVM80BitFloatNode) falseValue);
                case ADDRESS:
                    return LLVMAddressProfilingSelectNodeGen.create(condition, (LLVMAddressNode) trueValue, (LLVMAddressNode) falseValue);
                case FUNCTION_ADDRESS:
                    return LLVMFunctionProfilingSelectNodeGen.create(condition, (LLVMFunctionNode) trueValue, (LLVMFunctionNode) falseValue);
                default:
                    throw new AssertionError(llvmType);
            }
        } else {
            switch (llvmType) {
                case I1:
                    return LLVMI1SelectNodeGen.create(condition, (LLVMI1Node) trueValue, (LLVMI1Node) falseValue);
                case I8:
                    return LLVMI8SelectNodeGen.create(condition, (LLVMI8Node) trueValue, (LLVMI8Node) falseValue);
                case I16:
                    return LLVMI16SelectNodeGen.create(condition, (LLVMI16Node) trueValue, (LLVMI16Node) falseValue);
                case I32:
                    return LLVMI32SelectNodeGen.create(condition, (LLVMI32Node) trueValue, (LLVMI32Node) falseValue);
                case I64:
                    return LLVMI64SelectNodeGen.create(condition, (LLVMI64Node) trueValue, (LLVMI64Node) falseValue);
                case FLOAT:
                    return LLVMFloatSelectNodeGen.create(condition, (LLVMFloatNode) trueValue, (LLVMFloatNode) falseValue);
                case DOUBLE:
                    return LLVMDoubleSelectNodeGen.create(condition, (LLVMDoubleNode) trueValue, (LLVMDoubleNode) falseValue);
                case X86_FP80:
                    return LLVM80BitFloatSelectNodeGen.create(condition, (LLVM80BitFloatNode) trueValue, (LLVM80BitFloatNode) falseValue);
                case ADDRESS:
                    return LLVMAddressSelectNodeGen.create(condition, (LLVMAddressNode) trueValue, (LLVMAddressNode) falseValue);
                case FUNCTION_ADDRESS:
                    return LLVMFunctionSelectNodeGen.create(condition, (LLVMFunctionNode) trueValue, (LLVMFunctionNode) falseValue);
                default:
                    throw new AssertionError(llvmType);
            }
        }
    }

}
