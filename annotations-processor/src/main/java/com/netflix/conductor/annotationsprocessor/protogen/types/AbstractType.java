/*
 * Copyright 2022 Conductor Authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.annotationsprocessor.protogen.types;

import java.lang.reflect.Type;
import java.util.Set;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public abstract class AbstractType {
    Type javaType;
    TypeName javaProtoType;

    AbstractType(Type javaType, TypeName javaProtoType) {
        this.javaType = javaType;
        this.javaProtoType = javaProtoType;
    }

    // Getters for javaType and javaProtoType
    public Type getJavaType() {
        return javaType;
    }

    public TypeName getJavaProtoType() {
        return javaProtoType;
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getProtoType();

    public abstract TypeName getRawJavaType();

    public abstract void mapToProto(String field, MethodSpec.Builder method);

    public abstract void mapFromProto(String field, MethodSpec.Builder method);

    public abstract void getDependencies(Set<String> deps);

    public abstract void generateAbstractMethods(Set<MethodSpec> specs);

    // Method to convert field names to camelCase
    protected String javaMethodName(String m, String field) {
        String fieldName = field.substring(0, 1).toUpperCase() + field.substring(1);
        return m + fieldName;
    }

    // Static inner class for converting field names to Protobuf case
    private static class ProtoCase {
        static String convert(String s) {
            // Implementation for converting snake_case to CamelCase
            // ...
        }
    }

    // Method to convert field names to Protobuf case
    protected String protoMethodName(String m, String field) {
        return m + ProtoCase.convert(field);
    }
}

