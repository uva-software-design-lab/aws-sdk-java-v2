/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.core.internal.protocol.json;

import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.core.internal.protocol.AbstractMarshallerRegistry;
import software.amazon.awssdk.core.protocol.MarshallLocation;
import software.amazon.awssdk.core.protocol.MarshallingType;

/**
 * Marshaller registry for JSON based protocols.
 */
@SdkInternalApi
public final class JsonMarshallerRegistry extends AbstractMarshallerRegistry {

    private JsonMarshallerRegistry(Builder builder) {
        super(builder);
    }

    @SuppressWarnings("unchecked")
    public <T> JsonMarshaller<T> getMarshaller(MarshallLocation marshallLocation, T val) {
        return (JsonMarshaller<T>) getMarshaller(marshallLocation, toMarshallingType(val));
    }

    @SuppressWarnings("unchecked")
    public <T> JsonMarshaller<Object> getMarshaller(MarshallLocation marshallLocation,
                                                    MarshallingType<T> marshallingType,
                                                    Object val) {
        return (JsonMarshaller<Object>) getMarshaller(marshallLocation,
                                                      val == null ? MarshallingType.NULL : marshallingType);
    }

    /**
     * @return Builder instance to construct a {@link JsonMarshallerRegistry}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for a {@link JsonMarshallerRegistry}.
     */
    public static final class Builder extends AbstractMarshallerRegistry.Builder {

        private Builder() {
        }

        public <T> Builder payloadMarshaller(MarshallingType<T> marshallingType,
                                             JsonMarshaller<T> marshaller) {
            addMarshaller(MarshallLocation.PAYLOAD, marshallingType, marshaller);
            return this;
        }

        public <T> Builder headerMarshaller(MarshallingType<T> marshallingType,
                                            JsonMarshaller<T> marshaller) {
            addMarshaller(MarshallLocation.HEADER, marshallingType, marshaller);
            return this;
        }

        public <T> Builder queryParamMarshaller(MarshallingType<T> marshallingType,
                                                JsonMarshaller<T> marshaller) {
            addMarshaller(MarshallLocation.QUERY_PARAM, marshallingType, marshaller);
            return this;
        }

        public <T> Builder pathParamMarshaller(MarshallingType<T> marshallingType,
                                               JsonMarshaller<T> marshaller) {
            addMarshaller(MarshallLocation.PATH, marshallingType, marshaller);
            return this;
        }

        public <T> Builder greedyPathParamMarshaller(MarshallingType<T> marshallingType,
                                                     JsonMarshaller<T> marshaller) {
            addMarshaller(MarshallLocation.GREEDY_PATH, marshallingType, marshaller);
            return this;
        }

        /**
         * @return An immutable {@link JsonMarshallerRegistry} object.
         */
        public JsonMarshallerRegistry build() {
            return new JsonMarshallerRegistry(this);
        }
    }
}
