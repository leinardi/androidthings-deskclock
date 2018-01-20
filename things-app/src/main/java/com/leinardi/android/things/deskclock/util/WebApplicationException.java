/*
 * Copyright 2018 Roberto Leinardi.
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

package com.leinardi.android.things.deskclock.util;

/**
 * Runtime exception for applications.
 */
public class WebApplicationException extends RuntimeException {

    private Object mResponse;

    /**
     * Construct a new instance with a blank message
     */
    public WebApplicationException() {
        this(null, null, null);
    }

    /**
     * Construct a new instance using the supplied message
     *
     * @param message the detail message for this exception.
     */
    public WebApplicationException(String message) {
        this(message, null, null);
    }

    /**
     * Construct a new instance using the supplied response
     *
     * @param response the response that was returned to the server
     */
    public WebApplicationException(Object response) {
        this(null, null, response);
    }

    /**
     * Construct a new instance using the supplied response
     *
     * @param response the response that was returned to the server
     * @param message  the detail message for this exception.
     */
    public WebApplicationException(String message, Object response) {
        this(message, null, response);
    }

    /**
     * Construct a new instance with a blank message
     *
     * @param cause the underlying cause of the exception
     */
    public WebApplicationException(Throwable cause) {
        this(null, cause, null);
    }

    /**
     * Construct a new instance with a blank message
     *
     * @param cause   the underlying cause of the exception
     * @param message the detail message for this exception.
     */
    public WebApplicationException(String message, Throwable cause) {
        this(message, cause, null);
    }

    /**
     * Construct a new instance using the supplied response
     *
     * @param cause    the underlying cause of the exception
     * @param response the response that was returned to the server
     * @param message  the detail message for this exception.
     */
    public WebApplicationException(String message, Throwable cause, Object response) {
        super(message, cause);
        this.mResponse = response;
    }

    /**
     * Get the HTTP response.
     *
     * @return the HTTP response.
     */
    public Object getResponse() {
        return mResponse;
    }

}
