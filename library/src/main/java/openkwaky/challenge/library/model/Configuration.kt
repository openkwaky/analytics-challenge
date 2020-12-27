package openkwaky.challenge.library.model

class Configuration private constructor(
    val url: String?,
    val delay: Int?
) {
    data class Builder(
        var url: String? = null,
        var delay: Int? = 0
    ) {
        fun withUrl(url: String) = apply { this.url = url }
        fun withDelay(delay: Int) = apply { this.delay = delay }
        fun build() = Configuration(url, delay)
    }
}
