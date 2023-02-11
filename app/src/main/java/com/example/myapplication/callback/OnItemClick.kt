package com.example.myapplication.callback

interface OnItemClick<T> {
    fun onClickItem(item: T)
}