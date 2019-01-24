package com.raindrop.auth.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import java.util.*

/**
 * Jwt 工具类
 *
 * @author Raindrop
 */
open class JwtUtil {

    companion object {

        /* 秘钥 */
        private val secret = "raindrop-666"
        /* 过期时间 15 分钟 */
        private val expireTime = 15 * 60 * 1000

        /**
         * 生成Token
         *
         * @param username 用户名
         * @return token令牌
         */
        fun sign(username: String): String {
            var createDate = Date()
            var expireDate = Date(System.currentTimeMillis() + expireTime)

            var claims = mapOf("username" to username)
            return JWT.create()
                    .withHeader(claims)
                    .withSubject(username)
                    .withIssuedAt(createDate)
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC512(secret))
        }

        /**
         * 解析Token
         *
         * @param token 令牌
         * @return claim
         */
        fun parse(token: String): MutableMap<String, Claim>? {
            return JWT.decode(token).claims
        }

        /**
         * 检验Token
         *
         * @param token 令牌
         * @return 有效true 无效false
         */
        fun verifyToken(token: String): Boolean {
            return try {
                JWT.require(Algorithm.HMAC512(secret)).build().verify(token)
                true
            } catch (e: Exception) {
                false
            }
        }

        /**
         * 获取用户名
         *
         * @param token 令牌
         * @return 用户名
         */
        fun getUserName(token: String) = JWT.decode(token).getClaim("username").asString()

    }

}
