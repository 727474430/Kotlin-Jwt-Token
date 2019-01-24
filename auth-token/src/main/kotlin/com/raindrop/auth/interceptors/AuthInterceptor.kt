package com.raindrop.auth.interceptors

import com.alibaba.fastjson.JSON
import com.raindrop.auth.model.ResultEntity
import com.raindrop.auth.model.buildResultEntity
import com.raindrop.auth.utils.JwtUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private val options = "OPTIONS"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method == options) return true

        var authorization = request.getHeader("Authorization")
        if (authorization != null) {
            var token = authorization.substring(7)
            if (JwtUtil.verifyToken(token)) {
                logger.info("Auth Success Token Effective...")
                return true
            }
        }
        logger.info("Auth Fail Token Invalid...")
        response.writer.write(JSON.toJSONString(buildUnauthorizedResultEntity()))
        return false
    }

    fun buildUnauthorizedResultEntity(): ResultEntity {
        return buildResultEntity {
            code = 403
            message = "Auth Fail"
        }
    }

}