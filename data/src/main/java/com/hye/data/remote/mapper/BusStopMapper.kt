package com.hye.data.remote.mapper

import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import com.hye.domain.model.route.BusStop

fun BusSttnItemDto.toDomainModel(): BusStop {
    return BusStop(
        nodeId = this.stationId,
        name = this.stationNm,
        direction = "", //Todo : 방향 정보 가공 필요
        latitude = this.gpsLati,
        longitude = this.gpsLong
    )
}