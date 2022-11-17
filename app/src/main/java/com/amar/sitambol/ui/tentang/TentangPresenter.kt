package com.amar.sitambol.ui.tentang

class TentangPresenter(val view: TentangContract.View) {
    init {
        view.initActivity()
        view.initListener()
    }
}