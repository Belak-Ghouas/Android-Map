package com.mzd.myapp.data.base.mocks

import android.content.Context
import android.content.res.Resources.NotFoundException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mzd.myapp.data.base.live.BaseResponse
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class MockInterceptor internal constructor(
    private val context: Context,
    private val mockServiceConfig: MockServiceConfig?
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var fileName: String?
        if (mockServiceConfig == null || mockServiceConfig.isLive()) {
            return chain.proceed(chain.request())
        }

        // If the default suffix is set, we go through the rules, and if not found, the base file
        if (MockCallGlobalConfig.instance?.suffix == MockCallGlobalConfig.DEFAULT_SUFFIX) {
            fileName = try {
                getFileToOpen(mockServiceConfig.rootFileName, chain.request().url.toString())
            } catch (e: NotFoundException) {
                mockServiceConfig.rootFileName
            }
            // Use default file if it could not find match in the getFileToOpen function
            if (fileName == null) {
                fileName = mockServiceConfig.defaultRootFileName
            }
        } else {
            // Else we use the file name + custom suffix
            fileName = mockServiceConfig.rootFileName + MockCallGlobalConfig.instance?.suffix
        }

        return try {
            val stream = context.resources.openRawResource(
                context.resources.getIdentifier(
                    fileName,
                    "raw",
                    context.packageName
                )
            )
            val json = parseStream(stream)
            val gson = Gson()
            val jsonResp = gson.fromJson(json, BaseResponse::class.java)
            val builder = Response.Builder()
                .request(chain.request())
                .code(jsonResp.header!!.code)
                .protocol(Protocol.HTTP_2)
                .message(jsonResp.header?.message!!)
            if (jsonResp.body != null) {
                val bodyString = gson.toJson(jsonResp.body)
                builder.body(ResponseBody.create(MEDIA_JSON, bodyString))
            } else {
                builder.body(ResponseBody.create(MEDIA_JSON, ""))
            }
            builder.build()
        } catch (e: Exception) { // Nothing intercepted here.
            chain.proceed(chain.request())
        }
    }

    @Throws(IOException::class)
    private fun getFileToOpen(rootFakeFileName: String, url: String): String? {
        val stream = context.resources.openRawResource(
            context.resources.getIdentifier(
                "rules",
                "raw",
                context.packageName
            )
        )
        val json = parseStream(stream)
        val gson = Gson()
        val type = object : TypeToken<Map<String?, List<FileRule?>?>?>() {}.type
        val map =
            gson.fromJson<Map<String, List<FileRule>>>(
                json,
                type
            )
        for (requestName in map.keys) {
            if (rootFakeFileName == requestName) {
                val fileRuleList = map[requestName]
                if (fileRuleList != null) {
                    for (fileRule in fileRuleList) {
                        val keyList = fileRule.keyList
                        val isCorrectKey = isCorrectKey(keyList, url)
                        if (isCorrectKey) {
                            return rootFakeFileName + fileRule.file
                        }
                    }
                }
            }
        }
        return null
    }

    private fun isCorrectKey(
        keyList: List<String>?,
        url: String
    ): Boolean {
        for (key in keyList.orEmpty()) {
            if (url.contains(key)) {
                return true
            }
        }
        return false
    }

    @Throws(IOException::class)
    private fun parseStream(stream: InputStream): String {
        val builder = StringBuilder()
        val `in` = BufferedReader(
            InputStreamReader(
                stream,
                StandardCharsets.UTF_8
            )
        )
        var line: String?
        while (`in`.readLine().also { line = it } != null) {
            builder.append(line)
        }
        `in`.close()
        return builder.toString()
    }

    companion object {
        private val MEDIA_JSON = "application/json".toMediaTypeOrNull()
    }

}