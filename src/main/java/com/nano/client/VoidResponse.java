package com.nano.client;

/**
 * This class is used for methods that return only
 * a success parameter. We still need to parse the
 * JSON response to determine that there is an error,
 * but this is used in methods with a void return type.
 */
class VoidResponse extends BaseResponse {

    private String success;
}
