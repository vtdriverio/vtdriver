/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jd.jdbc.sqlparser.visitor.functions;

import com.jd.jdbc.sqlparser.ast.SQLExpr;
import com.jd.jdbc.sqlparser.ast.expr.SQLMethodInvokeExpr;
import com.jd.jdbc.sqlparser.visitor.SQLEvalVisitor;

import static com.jd.jdbc.sqlparser.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Concat implements Function {

    public final static Concat instance = new Concat();

    public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
        StringBuilder buf = new StringBuilder();

        for (SQLExpr item : x.getParameters()) {
            item.accept(visitor);

            Object itemValue = item.getAttributes().get(EVAL_VALUE);
            if (itemValue == null) {
                return null;
            }
            buf.append(itemValue.toString());
        }

        return buf.toString();
    }
}
