package com.raindrop.auth.service

interface IUserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    fun login(username: String, password: String): Boolean

}
