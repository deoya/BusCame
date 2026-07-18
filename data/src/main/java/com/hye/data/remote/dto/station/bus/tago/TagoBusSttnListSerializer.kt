package com.hye.data.remote.dto.station.bus.tago

import com.hye.data.remote.dto.station.bus.common.BusSttnItemDto
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

//공공데이터 Object/Array 파싱 트랩 방어용 커스텀 시리얼라이저
object TagoBusSttnListSerializer : JsonTransformingSerializer<List<BusSttnItemDto>>(
    tSerializer = ListSerializer(BusSttnItemDto.serializer())
) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        // 케이스 1: 결과가 0개일 때 ("" 로 내려옴) -> 빈 배열 반환
        if (element is JsonPrimitive && element.isString) {
            return JsonArray(emptyList())
        }

        // 객체 형태일 때 (케이스 2 & 3)
        if (element is JsonObject) {
            val itemNode = element["item"] ?: return JsonArray(emptyList())

            return when (itemNode) {
                is JsonArray -> itemNode // 케이스 3: 2개 이상일 때 (정상적인 배열)
                is JsonObject -> JsonArray(listOf(itemNode)) // 케이스 2: 딱 1개일 때 (객체를 배열로 강제 감싸기!)
                else -> JsonArray(emptyList())
            }
        }

        return JsonArray(emptyList())

    }
}