/*
 * Copyright (C) 2015 The Android Open Source Project
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
package com.google.currysrc.api.transform;

import com.google.currysrc.api.match.SourceMatcher;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

/**
 * A rule for transforming the AST. Consists of a transformer, a matcher to determine which
 * compilations units
 * the transformer will be applied to, and a flag that indicates whether the transformer is
 * <em>required</em>
 * to make a modification.
 */
public final class AstTransformRule {

  private final AstTransformer transformer;

  private final SourceMatcher matcher;

  private final boolean mustModify;

  public AstTransformRule(AstTransformer transformer, SourceMatcher matcher, boolean mustModify) {
    this.transformer = transformer;
    this.matcher = matcher;
    this.mustModify = mustModify;
  }

  public boolean matches(CompilationUnit cu) {
    return matcher.matches(cu);
  }

  public void transform(CompilationUnit cu, ASTRewrite rewrite) {
    transformer.transform(cu, rewrite);
  }

  public boolean mustModify() {
    return mustModify;
  }

  @Override
  public String toString() {
    return "AstTransformRule{" +
        "transformer=" + transformer +
        ", matcher=" + matcher +
        ", mustModify=" + mustModify +
        '}';
  }
}