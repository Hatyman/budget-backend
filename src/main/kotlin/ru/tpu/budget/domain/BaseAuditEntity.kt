package ru.tpu.budget.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAuditEntity<T: Serializable> : BaseEntity<T>() {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    lateinit var created: LocalDateTime

    @LastModifiedDate
    @Column(nullable = false)
    lateinit var modified: LocalDateTime
}