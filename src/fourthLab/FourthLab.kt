package fourthLab

import java.util.PriorityQueue

class FourthLab(
    //val routes: List<List<Int>>,
    //val startStop: Int,
    //val endStop: Int
) {
    fun displayResult(){
        /*
        val (time, path) = findShortestPath()
        println("Минимальное время: ${if (time == -1) "нет пути" else time}")
        println("\nДетали маршрута:")

        path.forEach { step ->
            if (step.isTransfer) {
                println("[Пересадка] На остановке ${step.fromStop} " +
                        "пересядьте на маршрут ${step.routeId + 1}")
            } else {
                println("[Движение] От ${step.fromStop} до ${step.toStop} " +
                        "по маршруту ${step.routeId + 1}")
            }
        }

         */
    }

    fun findShortestPath(routes: List<List<Int>>, startStop: Int, endStop: Int): Pair<Int, List<RouteStep>> {
        if (startStop == endStop) return Pair(0, emptyList())

        val stopToRoutes = mutableMapOf<Int, MutableList<Int>>()
        val routeIndices = mutableListOf<Map<Int, Int>>()

        routes.forEachIndexed { routeId, stops ->
            val indexMap = mutableMapOf<Int, Int>()
            stops.forEachIndexed { idx, stop ->
                indexMap[stop] = idx
                stopToRoutes.getOrPut(stop) { mutableListOf() }.add(routeId)
            }
            routeIndices.add(indexMap)
        }

        if (!stopToRoutes.containsKey(startStop) || !stopToRoutes.containsKey(endStop)) {
            return Pair(-1, emptyList())
        }

        data class Vertex(val stop: Int, val route: Int)
        val dist = mutableMapOf<Vertex, Int>()
        val queue = PriorityQueue<Pair<Vertex, Int>>(compareBy { it.second })
        val predecessors = mutableMapOf<Vertex, Vertex?>()
        val steps = mutableMapOf<Vertex, RouteStep>()

        val start = Vertex(startStop, -1)
        dist[start] = 0
        queue.add(start to 0)
        predecessors[start] = null

        var minTime = Int.MAX_VALUE
        var targetVertex: Vertex? = null

        while (queue.isNotEmpty()) {
            val (current, time) = queue.poll()

            if (time > minTime) continue

            if (current.stop == endStop) {
                if (time < minTime) {
                    minTime = time
                    targetVertex = current
                }
                continue
            }

            when (current.route) {
                -1 -> {
                    stopToRoutes[current.stop]?.forEach { routeId ->
                        val newVertex = Vertex(current.stop, routeId)
                        val newTime = time + 3 // Пересадка
                        if (newTime < dist.getOrDefault(newVertex, Int.MAX_VALUE)) {
                            dist[newVertex] = newTime
                            predecessors[newVertex] = current
                            steps[newVertex] = RouteStep(current.stop, current.stop, routeId, true)
                            queue.add(newVertex to newTime)
                        }
                    }
                }
                else -> {
                    val route = routes[current.route]
                    val pos = routeIndices[current.route]?.get(current.stop) ?: continue

                    listOf(pos - 1, pos + 1).forEach { delta ->
                        val newPos = pos + delta
                        if (newPos in route.indices) {
                            val newStop = route[newPos]
                            val newVertex = Vertex(newStop, current.route)
                            val newTime = time + 1 // Движение
                            if (newTime < dist.getOrDefault(newVertex, Int.MAX_VALUE)) {
                                dist[newVertex] = newTime
                                predecessors[newVertex] = current
                                steps[newVertex] = RouteStep(current.stop, newStop, current.route, false)
                                queue.add(newVertex to newTime)
                            }
                        }
                    }

                    stopToRoutes[current.stop]
                        ?.filter { it != current.route }
                        ?.forEach { newRouteId ->
                            val newVertex = Vertex(current.stop, newRouteId)
                            val newTime = time + 3 // Пересадка
                            if (newTime < dist.getOrDefault(newVertex, Int.MAX_VALUE)) {
                                dist[newVertex] = newTime
                                predecessors[newVertex] = current
                                steps[newVertex] = RouteStep(current.stop, current.stop, newRouteId, true)
                                queue.add(newVertex to newTime)
                            }
                        }
                }
            }
        }

        return if (targetVertex == null) {
            Pair(-1, emptyList())
        } else {
            val path = mutableListOf<RouteStep>()
            var current: Vertex? = targetVertex

            while (current != null && predecessors[current] != null) {
                steps[current]?.let { path.add(0, it) }
                current = predecessors[current]
            }

            Pair(minTime, path)
        }
    }
}