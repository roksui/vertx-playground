package org.roksui

import io.reactivex.rxjava3.subjects.PublishSubject

class ObservableList {
    val projects: MutableList<String> = mutableListOf()
    private val onAdd: PublishSubject<String> = PublishSubject.create()

    fun add(project: String) {
        projects.add(project)
        onAdd.onNext(project)
    }
    fun getObservable() = onAdd
}