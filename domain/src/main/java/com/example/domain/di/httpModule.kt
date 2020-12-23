package com.example.domain.di

import com.example.domain.mapper.EmployeeMapper
import com.example.domain.usecase.GetEmployeeCountUseCase
import com.example.domain.usecase.GetTopPaidEmployeeUseCase
import com.example.domain.usecase.DispatchersUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        EmployeeMapper()
    }
    factory {
        GetEmployeeCountUseCase(
            employeeRepository = get(),
            employeeMapper = get(),
            dispatcher = get()
        )
    }
    factory {
        GetTopPaidEmployeeUseCase(
            employeeRepository = get(),
            employeeMapper = get(),
            dispatcher = get()
        )
    }

    single {
        DispatchersUseCase()
    }
}

