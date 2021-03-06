/*
 * Copyright (c) 2015, Jean-Baptiste Giraudeau <jb@giraudeau.info>
 *
 * This file is part of "Derive4J - Processor API".
 *
 * "Derive4J - Processor API" is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * "Derive4J - Processor API" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Derive4J - Processor API".  If not, see <http://www.gnu.org/licenses/>.
 */
package org.derive4j.processor.api;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import org.derive4j.processor.api.model.AlgebraicDataType;
import org.derive4j.processor.api.model.TypeRestriction;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public interface DeriveUtils {

  Types types();

  Elements elements();

  DeriveResult<AlgebraicDataType> parseAlgebraicDataType(TypeElement typeElement);

  TypeName resolveToTypeName(TypeMirror typeMirror, Function<TypeVariable, Optional<TypeName>> typeArgs);

  Function<TypeVariable, Optional<TypeMirror>> typeArgs(DeclaredType dt);

  Function<TypeVariable, Optional<TypeMirror>> typeRestrictions(List<TypeRestriction> typeRestrictions);

  TypeMirror resolve(TypeMirror typeMirror, Function<TypeVariable, Optional<TypeMirror>> typeArgs);

  MethodSpec.Builder overrideMethodBuilder(final ExecutableElement abstractMethod, Function<TypeVariable, Optional<TypeMirror>> typeArgs);

  default MethodSpec.Builder overrideMethodBuilder(final ExecutableElement abstractMethod) {
    return overrideMethodBuilder(abstractMethod, tv -> Optional.empty());
  }

  Stream<TypeVariable> typeVariablesIn(TypeMirror typeMirror);
}
