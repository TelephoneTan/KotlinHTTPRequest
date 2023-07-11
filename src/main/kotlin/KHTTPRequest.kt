import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import pub.telephone.javahttprequest.network.http.HTTPMethod
import pub.telephone.javahttprequest.network.http.HTTPRequest
import pub.telephone.javapromise.async.promise.PromiseCancelledBroadcast
import pub.telephone.javapromise.async.promise.PromiseSemaphore

private fun PromiseCancelledBroadcast?.request(
    method: HTTPMethod? = null,
    url: String? = null,
    requestSemaphore: PromiseSemaphore? = null,
) = HTTPRequest(this, method, url, requestSemaphore)

fun CoroutineScope.request(
    method: HTTPMethod? = null,
    url: String? = null,
    requestSemaphore: PromiseSemaphore? = null,
) = coroutineContext[Job].ToBroadcast().request(
    method = method,
    url = url,
    requestSemaphore = requestSemaphore,
)

fun KPromiseScope.request(
    method: HTTPMethod? = null,
    url: String? = null,
    requestSemaphore: PromiseSemaphore? = null,
) = CancelledBroadcast.request(
    method = method,
    url = url,
    requestSemaphore = requestSemaphore,
)