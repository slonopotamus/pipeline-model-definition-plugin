/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.pipeline.modeldefinition.validator;

import hudson.Extension;
import org.jenkinsci.plugins.pipeline.modeldefinition.ast.ModelASTOption;
import org.jenkinsci.plugins.workflow.flow.FlowExecution;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Validator contributor that rejects use of {@code parameters} or {@code triggers} in {@code options { ... }}
 */
@Extension
public class ParametersAndTriggersInOptions extends DeclarativeValidatorContributor {
    @Override
    @CheckForNull
    public String validateElement(@Nonnull ModelASTOption option, @CheckForNull FlowExecution execution) {
        if (option.getName() != null) {
            if (option.getName().equals("parameters")) {
                return Messages.ParametersAndTriggersInOptions_RejectParameters();
            } else if (option.getName().equals("triggers")) {
                return Messages.ParametersAndTriggersInOptions_RejectTriggers();
            }
        }

        return null;
    }
}