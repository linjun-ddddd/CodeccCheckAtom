package com.tencent.devops.docker.scm.pojo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ScmInfoJson(
    @JsonProperty("scm_info")
    val scmInfoList: List<ScmInfoItem>
)