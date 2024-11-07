package com.example.studybuddy.ui.Presentation.session

import com.example.studybuddy.Domain.Model.Session
import com.example.studybuddy.Domain.Model.Subject

data class SessionState(
    val subjects: List<Subject> = emptyList(),
    val sessions: List<Session> = emptyList(),
    val relatedToSubject: String? = null,
    val subjectId: Int? = null,
    val session: Session? = null
)