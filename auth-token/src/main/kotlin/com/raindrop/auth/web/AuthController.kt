package com.raindrop.auth.web

import com.raindrop.auth.model.ResultEntity
import com.raindrop.auth.model.User
import com.raindrop.auth.model.buildResultEntity
import com.raindrop.auth.service.IUserService
import com.raindrop.auth.utils.JwtUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var userService: IUserService

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @PostMapping("/login")
    fun login(username: String, password: String): ResultEntity {
        if (username == null) {
            logger.warn("Login Fail, UserName Is Null")
            throw IllegalArgumentException("登录失败，用户不存在")
        }
        if (password == null) {
            logger.warn("Login Fail, Password Is Null")
            throw IllegalArgumentException("登录失败，账号或密码错误")
        }

        if (userService.login(username, password)) {
            var token = JwtUtil.sign(username)
            return buildResultEntity { data = token }
        }

        return buildResultEntity {
            code = 400
            message = "登录失败"
        }
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/users")
    fun getUserList(): ResultEntity {
        var users = listOf(
                User("wl", "wl", "wl"),
                User("lv", "lv", "lv"),
                User("ee", "ee", "ee")
        )
        return buildResultEntity { data = users }
    }

}