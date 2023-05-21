package com.isa.myflickr.di

import com.isa.myflickr.data.remote.FlickrService
import com.isa.myflickr.data.remote.RemoteDataSource
import com.isa.myflickr.data.repository.PhotoRepositoryImpl
import com.isa.myflickr.domain.repository.PhotoRepository
import com.isa.myflickr.domain.usecase.GetPhotosUseCase
import com.isa.myflickr.util.provideDispatcher
import org.koin.dsl.module

/**
 * Created by Isa Andi on 19/05/2023.
 */
private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { FlickrService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<PhotoRepository> { PhotoRepositoryImpl(get()) }
    factory { GetPhotosUseCase() }
}

private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules