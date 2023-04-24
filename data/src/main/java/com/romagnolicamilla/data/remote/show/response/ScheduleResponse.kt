package com.romagnolicamilla.data.remote.show.response

import com.romagnolicamilla.data.base.BaseResponse
import com.romagnolicamilla.domain.show.entity.Schedule

data class ScheduleResponse(val time: String?, val days: List<String>?) : BaseResponse<Schedule> {
    override fun toDomain() = Schedule(time, days)
}
