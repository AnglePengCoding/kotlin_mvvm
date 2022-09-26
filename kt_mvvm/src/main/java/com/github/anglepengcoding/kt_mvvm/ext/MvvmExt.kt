package com.github.anglepengcoding.kt_mvvm.base

import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import com.github.anglepengcoding.kt_mvvm.R
import com.github.anglepengcoding.kt_mvvm.ext.paresVmException
import com.github.anglepengcoding.kt_mvvm.ext.paresVmResult
import com.github.anglepengcoding.kt_mvvm.ext.showToast
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */


/**
 * 获取vm clazz
 */
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

/**
 * 跟下面的方法类似,只是调用形式上有所区别
 * 这种vmResult要提前定义好
 * 下面vmResult: VmResult<T>.() -> Unit可以直接写在参数里面
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserver(owner: LifecycleOwner, vmResult: VmResult<T>) {
    observe(owner) {
        when (it) {
            is VmState.Loading -> {
                vmResult.onAppLoading()
            }
            is VmState.Success -> {
                vmResult.onAppSuccess(it.data)
                vmResult.onAppComplete()
            }
            is VmState.Error -> {
                vmResult.onAppError(it.error)
                vmResult.onAppComplete()
            }
        }
    }
}

/**
 * 重写所有回调方法
 * onAppLoading
 * onAppSuccess
 * onAppError
 * onAppComplete
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserver(owner: LifecycleOwner, vmResult: VmResult<T>.() -> Unit) {
    val result = VmResult<T>();result.vmResult();observe(owner) {
        when (it) {
            is VmState.Loading -> {
                result.onAppLoading()
            }
            is VmState.Success -> {
                result.onAppSuccess(it.data);result.onAppComplete()
            }
            is VmState.Error -> {
                result.onAppError(it.error);result.onAppComplete()
            }
        }
    }
}

/**
 * 带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(activity) {
        when (it) {
            is VmState.Loading -> {
                activity.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                activity.dismissLoadingDialog()
            }
            is VmState.Error -> {
                if (null != tips && tips) {
                    when (it.error.errorMsg) {
                        "无网络连接" -> activity.unknownHostDialog()
                        "网络超时" -> activity.timeOutDialog()
                        "数据错误,json错误" -> activity.jsonSyntaxDialog()
                        "网络错误" -> activity.socketDialog()
                        "未知错误" -> activity.elseNetDialog()
                    }
                }
            }
        }
    }
}


/**
 * 带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(activity) {
        when (it) {
            is VmState.Loading -> {
                activity.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                activity.dismissLoadingDialog()
                onComplete()
            }
            is VmState.Error -> {
                if (null != tips && tips) {
                    when (it.error.errorMsg) {
                        "无网络连接" -> activity.unknownHostDialog()
                        "网络超时" -> activity.timeOutDialog()
                        "数据错误,json错误" -> activity.jsonSyntaxDialog()
                        "网络错误" -> activity.socketDialog()
                        "未知错误" -> activity.elseNetDialog()
                    }
                }
                onComplete()
            }
        }
    }
}

/**
 * 默认不带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(activity) {
        when (it) {
            is VmState.Loading -> {
            }
            is VmState.Success -> {
                onSuccess(it.data)
            }
            is VmState.Error -> {
                if (null != tips && tips) {
                    when (it.error.errorMsg) {
                        "无网络连接" -> activity.unknownHostDialog()
                        "网络超时" -> activity.timeOutDialog()
                        "数据错误,json错误" -> activity.jsonSyntaxDialog()
                        "网络错误" -> activity.socketDialog()
                        "未知错误" -> activity.elseNetDialog()
                    }
                }
            }
        }
    }
}

/**
 * 默认不带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(activity) {
        when (it) {
            is VmState.Loading -> {
            }
            is VmState.Success -> {
                onSuccess(it.data)
                onComplete()
            }
            is VmState.Error -> {
                if (null != tips && tips) {
                    when (it.error.errorMsg) {
                        "无网络连接" -> activity.unknownHostDialog()
                        "网络超时" -> activity.timeOutDialog()
                        "数据错误,json错误" -> activity.jsonSyntaxDialog()
                        "网络错误" -> activity.socketDialog()
                        "未知错误" -> activity.elseNetDialog()
                    }
                }

                onComplete()
            }
        }
    }
}

/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(activity) {
        when (it) {
            is VmState.Loading -> {
//                if (activity.getBaseViewStatus() != EBaseViewStatus.SUCCESS) activity.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
//                activity.showSuccessLayout()
                onComplete()
            }
            is VmState.Error -> {
                if (null != tips && tips) {
                    when (it.error.errorMsg) {
                        "无网络连接" -> activity.unknownHostDialog()
                        "网络超时" -> activity.timeOutDialog()
                        "数据错误,json错误" -> activity.jsonSyntaxDialog()
                        "网络错误" -> activity.socketDialog()
                        "未知错误" -> activity.elseNetDialog()
                    }
                }
                onComplete()
            }
        }
    }
}


/**
 * 带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading -> {
                fragment.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                fragment.dismissLoadingDialog()
            }
            is VmState.Error -> {
                if (null != tips && tips) {
//                    when (it.error.errorMsg) {
//                        "无网络连接" -> fragment.unknownHostDialog()
//                        "网络超时" -> fragment.timeOutDialog()
//                        "数据错误,json错误" -> fragment.jsonSyntaxDialog()
//                        "网络错误" -> fragment.socketDialog()
//                        "未知错误" -> fragment.elseNetDialog()
//                    }
                }
            }
        }
    }
}

/**
 * 带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading -> {
                fragment.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                fragment.dismissLoadingDialog()
                onComplete()
            }
            is VmState.Error -> {
//                when (it.error.errorMsg) {
//                    "无网络连接" -> activity.unknownHostDialog()
//                    "网络超时" -> activity.timeOutDialog()
//                    "数据错误,json错误" -> activity.jsonSyntaxDialog()
//                    "网络错误" -> activity.socketDialog()
//                    "未知错误" -> activity.elseNetDialog()
//                }
                onComplete()
            }
        }
    }
}


/**
 * 不带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading -> {
            }
            is VmState.Success -> {
                onSuccess(it.data)
            }
            is VmState.Error -> {
//                when (it.error.errorMsg) {
//                    "无网络连接" -> activity.unknownHostDialog()
//                    "网络超时" -> activity.timeOutDialog()
//                    "数据错误,json错误" -> activity.jsonSyntaxDialog()
//                    "网络错误" -> activity.socketDialog()
//                    "未知错误" -> activity.elseNetDialog()
//                }
            }
        }
    }
}

/**
 * 不带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading -> {
            }
            is VmState.Success -> {
                onSuccess(it.data)
                onComplete()
            }
            is VmState.Error -> {
//                when (it.error.errorMsg) {
//                    "无网络连接" -> activity.unknownHostDialog()
//                    "网络超时" -> activity.timeOutDialog()
//                    "数据错误,json错误" -> activity.jsonSyntaxDialog()
//                    "网络错误" -> activity.socketDialog()
//                    "未知错误" -> activity.elseNetDialog()
//                }
                onComplete()
            }
        }
    }
}


/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading -> {
//                if (fragment.getBaseViewStatus() != EBaseViewStatus.SUCCESS) fragment.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
//                fragment.showSuccessLayout()
            }
            is VmState.Error -> {
//                when (it.error.errorMsg) {
//                    "无网络连接" -> activity.unknownHostDialog()
//                    "网络超时" -> activity.timeOutDialog()
//                    "数据错误,json错误" -> activity.jsonSyntaxDialog()
//                    "网络错误" -> activity.socketDialog()
//                    "未知错误" -> activity.elseNetDialog()
//                }
            }
        }
    }
}


/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading -> {
//                if (fragment.getBaseViewStatus() != EBaseViewStatus.SUCCESS) fragment.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
//                fragment.showSuccessLayout()
                onComplete()
            }
            is VmState.Error -> {
//                when (it.error.errorMsg) {
//                    "无网络连接" -> activity.unknownHostDialog()
//                    "网络超时" -> activity.timeOutDialog()
//                    "数据错误,json错误" -> activity.jsonSyntaxDialog()
//                    "网络错误" -> activity.socketDialog()
//                    "未知错误" -> activity.elseNetDialog()
//                }
                onComplete()
            }
        }
    }
}

/**
 * BaseViewModel开启协程扩展
 */
fun <T> BaseViewModel.launchVmRequest(
    request: suspend () -> BaseData<T>,
    viewState: VmLiveData<T>
) {
    viewModelScope.launch {
        runCatching {
            viewState.value = VmState.Loading
            request()
        }.onSuccess {
            viewState.paresVmResult(it)
        }.onFailure {
            viewState.paresVmException(it)
        }
    }
}


/**
 * 网络错误
 */

fun Throwable?.parseErrorString(): String {
    return when (this) {
        is SocketException -> BaseApp.instance.getString(R.string.SocketException)
        is ConnectException -> BaseApp.instance.getString(R.string.ConnectException)
        is UnknownHostException -> BaseApp.instance.getString(R.string.UnknownHostException)
        is JsonSyntaxException -> BaseApp.instance.getString(R.string.JsonSyntaxException)
        is SocketTimeoutException -> BaseApp.instance.getString(R.string.SocketTimeoutException)
        is TimeoutException -> BaseApp.instance.getString(R.string.SocketTimeoutException)
        else -> BaseApp.instance.getString(R.string.ElseNetException)
    }
}
