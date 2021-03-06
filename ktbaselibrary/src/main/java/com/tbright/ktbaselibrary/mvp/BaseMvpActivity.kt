package com.tbright.ktbaselibrary.mvp

import android.os.Bundle
import com.tbright.ktbaselibrary.base.BaseActivity
import com.tbright.ktbaselibrary.event.MessageEvent
import com.tbright.ktbaselibrary.global.GlobalConfig
import com.tbright.ktbaselibrary.utils.ReflectUtils

abstract class BaseMvpActivity<  P : IPresenter> : BaseActivity(),BaseView {

    open var mPresenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = ReflectUtils.getObject(this, 0)
        mPresenter?.onAttachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

    override fun <T> onMessageEvent(messageEvent: MessageEvent<T>) {
        super.onMessageEvent(messageEvent)
        GlobalConfig.showUIProxy?.parseResponseFailMessage(messageEvent)
    }

     override fun showLoading() {
         GlobalConfig.showUIProxy?.showLoading()
     }

     override fun hideLoading() {
         GlobalConfig.showUIProxy?.hideLoading()
     }

     override fun showError(errorMessage: String) {
         GlobalConfig.showUIProxy?.showError(errorMessage)
     }

      override fun finishRefresh() {

      }

      override fun finishLoadMore() {

      }

  }