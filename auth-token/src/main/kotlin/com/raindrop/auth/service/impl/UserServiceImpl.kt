package com.raindrop.auth.service.impl

import com.raindrop.auth.service.IUserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : IUserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    override fun login(username: String, password: String): Boolean = true

}