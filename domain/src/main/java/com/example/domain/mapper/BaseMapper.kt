package com.example.domain.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <T> the remote model input type
 * @param <V> the model return type
 */
interface BaseMapper<E, D> {
    fun toEntity(type: E): D
    fun toDto(type: D): E
}