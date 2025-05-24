package fourthLab

data class RouteStep(
    val fromStop: Int,
    val toStop: Int,
    val routeId: Int,
    val isTransfer: Boolean
)
