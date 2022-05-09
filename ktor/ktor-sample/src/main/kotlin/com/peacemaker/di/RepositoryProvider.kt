package com.peacemaker.di

import com.peacemaker.repository.auths.AuthRepository
import com.peacemaker.repository.auths.AuthRepositoryImpl
import com.peacemaker.repository.user.UserRepository
import com.peacemaker.repository.user.UserRepositoryImpl
import com.peacemaker.service.auth.AuthServiceImpl
import com.peacemaker.service.user.UserServiceImpl

class RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
}