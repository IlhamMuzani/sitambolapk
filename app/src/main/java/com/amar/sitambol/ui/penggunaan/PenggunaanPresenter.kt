package com.amar.sitambol.ui.penggunaan

class PenggunaanPresenter(val view: PenggunaanContract.View) {
    init {
        view.initActivity()
        view.initListener()
    }
}