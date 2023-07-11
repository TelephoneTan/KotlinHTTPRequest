import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test
import pub.telephone.javahttprequest.network.http.HTTPMethod
import pub.telephone.javahttprequest.network.http.HTTPResult

class Test {
    @Test
    fun test() {
        GlobalScope.request()
            .clone()
            .SetMethod(HTTPMethod.GET)
            .SetURL("https://tencent.com")
            .String()
            .next {
                println("腾讯：")
                println(value.Result)
            }.then<String, Unit> {
                return@then request()
                    .clone()
                    .SetMethod(HTTPMethod.GET)
                    .SetURL("https://baidu.com")
                    .String()
                    .then<String, HTTPResult<String>> {
                        value.Result
                    }
            }.next {
                println("百度：")
                println(value)
                println("百度长度：")
                println(value.length)
            }.error {
                reason.printStackTrace()
            }.Await()
    }
}