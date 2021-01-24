package com.example.domain.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 */
interface BaseMapper<E, D> {
    fun toEntity(dto: E): D
    fun toDto(entity: D): E
}