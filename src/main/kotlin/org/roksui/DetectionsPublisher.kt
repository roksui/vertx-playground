package org.roksui

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable

class DetectionsPublisher(observableList: ObservableList) {

    private var publisher: Flowable<String>

    init {
        val observable = observableList.getObservable()

        val connectableObservable = observable.share().publish()
        connectableObservable.connect()

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPublisher(): Flowable<String> = publisher
}