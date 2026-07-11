package com.hye.data.remote.dto.nearby

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer

//공공데이터 Object/Array 파싱 트랩 방어용 커스텀 시리얼라이저
object NearbySttnListSerializer : JsonTransformingSerializer<List<NearbySttnItemDto>>(
    tSerializer = ListSerializer(NearbySttnItemDto.serializer())
) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        // 만약 item이 객체 `{}` 형태로 날아왔을 경우
        return if (element !is JsonArray) {
            JsonArray(listOf(element))
        } else {
            element
        }
    }
}