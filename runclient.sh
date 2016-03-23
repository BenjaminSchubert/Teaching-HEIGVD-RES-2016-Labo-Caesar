#!/bin/sh

java -cp target/CaesarProtocol-1.0-SNAPSHOT.jar ch.heigvd.res.client.TCPClient ${@}
