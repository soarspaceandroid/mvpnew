package com.mongootech.soarlibrary.http

import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.parse.AbstractParser
import java.io.IOException
import java.lang.reflect.Type


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 下午 5:37
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
@Parser(name = "Result", wrappers = [MutableList::class, BaseBean::class])
open class ResultParser<T> : AbstractParser<T> {
    /**
     * 此构造方法适用于任意Class对象，但更多用于带泛型的Class对象，如：List<Student>
     *
     * 用法:
     * Java: .asParser(new ResponseParser<List<Student>>(){})
     * Kotlin: .asParser(object : ResponseParser<List<Student>>() {})
     *
     * 注：此构造方法一定要用protected关键字修饰，否则调用此构造方法将拿不到泛型类型
     */
    protected constructor() : super()

    /**
     * 此构造方法仅适用于不带泛型的Class对象，如: Student.class
     *
     * 用法
     * Java: .asParser(new ResponseParser<>(Student.class))   或者  .asResponse(Student.class)
     * Kotlin: .asParser(ResponseParser(Student::class.java)) 或者  .asResponse(Student::class.java)
     */
    constructor(type: Type) : super(type)


    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val type: Type = ParameterizedTypeImpl[BaseBean::class.java, mType] //获取泛型类型
        val data: BaseBean<T> = convert(response, type)
        var t = data.data //获取data字段
        if (data.code != BaseNetCode.NET_SUCCESS || t == null) { //code不等于0，说明数据不正确，抛出异常
            throw FailException(ErrorInfo(data.code, data.msg), response)
        }
        return t
    }

}