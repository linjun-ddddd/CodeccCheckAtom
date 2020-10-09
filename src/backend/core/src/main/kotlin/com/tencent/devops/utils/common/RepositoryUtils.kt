/*
 * Tencent is pleased to support the open source community by making BK-CI 蓝鲸持续集成平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CI 蓝鲸持续集成平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.devops.utils.common

import com.tencent.devops.api.RepositoryApi
import com.tencent.devops.pojo.exception.CodeccUserConfigException
import com.tencent.devops.pojo.repo.Repository
import com.tencent.devops.pojo.repo.RepositoryConfig
import org.slf4j.LoggerFactory

object RepositoryUtils {

    private val repoApi = RepositoryApi()

    fun getRepository(repositoryConfig: RepositoryConfig): Repository {
        try {
            logger.info("Start to get the repo($repositoryConfig)")
            val result = repoApi.get(repositoryConfig)
            if (result.isNotOk() || result.data == null) {
                throw CodeccUserConfigException("Fail to get the repo($repositoryConfig) because of ${result.message}")
            }
            logger.info("Get the repo(${result.data})")
            return result.data!!
        } catch (ignored: Exception) {
            throw CodeccUserConfigException("Fail to get the repo($repositoryConfig), ${ignored.message}")
        }
    }

    private val logger = LoggerFactory.getLogger(RepositoryUtils::class.java)
}
