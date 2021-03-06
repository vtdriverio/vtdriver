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

package com.jd.jdbc.sqlparser.ast.statement;

import com.jd.jdbc.sqlparser.ast.SQLExpr;
import com.jd.jdbc.sqlparser.ast.SQLStatementImpl;
import com.jd.jdbc.sqlparser.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLDropUserStatement extends SQLStatementImpl implements SQLDropStatement {

    private List<SQLExpr> users = new ArrayList<SQLExpr>(2);

    public SQLDropUserStatement() {

    }

    public SQLDropUserStatement(String dbType) {
        super(dbType);
    }

    public List<SQLExpr> getUsers() {
        return users;
    }

    public void addUser(SQLExpr user) {
        if (user != null) {
            user.setParent(this);
        }
        this.users.add(user);
    }

    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, users);
        }
        visitor.endVisit(this);
    }

    @Override
    public List getChildren() {
        return users;
    }
}
