package id.ark.rizzr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform