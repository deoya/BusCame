package com.hye.data.remote.dto.nearby

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

/*
* 만약 서버가 준 데이터가 문자열(String) 형태라면? (예: "")
*  빈 객체 `{}` 인 것처럼 속여서 리턴해주는 커스텀 시리얼라이저
* */
object NearbySttnItemsStringOrObjectSerializer :
    JsonTransformingSerializer<NearbySttnItems>(NearbySttnItems.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        if (element is JsonPrimitive && element.isString) {
            return JsonObject(emptyMap())
        }
        return element
    }
}