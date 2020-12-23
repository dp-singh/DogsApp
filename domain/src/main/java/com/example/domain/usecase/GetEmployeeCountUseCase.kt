package com.example.domain.usecase

import com.example.data.respository.EmployeeRepository
import com.example.domain.base.UseCase
import com.example.domain.mapper.EmployeeMapper

class GetEmployeeCountUseCase(
    private val employeeRepository: EmployeeRepository,
    private val employeeMapper: EmployeeMapper,
    dispatcher: DispatchersUseCase
) : UseCase<Int>(dispatcher) {

    override suspend fun run(): Int {
        return employeeRepository.getEmployeeList()
            .map { employeeDTO -> employeeMapper.toEntity(employeeDTO) }
            .count()
    }
}