package com.template.easypayments.di

import com.template.easypayments.data.repositoryimpl.PaymentsRepositoryImpl
import com.template.easypayments.domain.repository.PaymentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentsRepositoryModule {
    @Binds
    abstract fun bindPaymentsRepositoryToPaymentsRepositoryImpl(Impl: PaymentsRepositoryImpl): PaymentsRepository
}