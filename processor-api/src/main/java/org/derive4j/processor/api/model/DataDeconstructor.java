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
package org.derive4j.processor.api.model;

import org.derive4j.Data;
import org.derive4j.Derive;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;

import static org.derive4j.Visibility.Smart;
import static org.derive4j.processor.api.model.DataDeconstructors.*;

@Data(@Derive(withVisibility = Smart))
public abstract class DataDeconstructor {

  DataDeconstructor() {
  }

  public static DataDeconstructor deconstructor(VariableElement visitorParam, DeclaredType visitorType, ExecutableElement visitorMethod) {
    return DataDeconstructors.deconstructor(visitorParam, visitorType, visitorMethod);
  }

  public ExecutableElement visitorMethod() {
    return getVisitorMethod(this);
  }

  public VariableElement visitorParam() {
    return getVisitorParam(this);
  }

  public DeclaredType visitorType() {
    return getVisitorType(this);
  }

  public abstract <R> R match(Case<R> deconstructor);

  public interface Case<R> {
    R deconstructor(VariableElement visitorParam, DeclaredType visitorType, ExecutableElement visitorMethod);
  }

}
