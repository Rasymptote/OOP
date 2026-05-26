network {
    host "localhost"
    port 8080
    websocket {
        path "/ws"
        appPrefix "/app"
        topicPrefix "/topic"
        routes {
            state "state"
            join "join"
            direction "direction"
        }
    }
}

game {
    field {
        width 10
        height 10
    }
    tickDuration 300
    playerBufferSize 5
    foodReplenishCount 3
    randomSeed 12345
}
