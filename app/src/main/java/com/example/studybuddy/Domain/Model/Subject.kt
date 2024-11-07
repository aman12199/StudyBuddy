package com.example.studybuddy.Domain.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.studybuddy.ui.Presentation.gradient1
import com.example.studybuddy.ui.Presentation.gradient2
import com.example.studybuddy.ui.Presentation.gradient3
import com.example.studybuddy.ui.Presentation.gradient4
import com.example.studybuddy.ui.Presentation.gradient5

@Entity
data class Subject(
    val name: String,
    val goalHours: Float,
    val colors: List<Int>,
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int? = null
) {
    companion object {
        val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}
