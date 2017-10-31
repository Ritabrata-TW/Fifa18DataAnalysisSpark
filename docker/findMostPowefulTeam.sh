#!/usr/bin/env bash

container="most-powerful-teams"

case $1 in
	"hot-reload")
		cd ..
		./gradlew jar
		cd docker
		docker-compose stop -t 0 $container
		docker-compose rm -f $container
		docker-compose up -d $container
	;;

	"restart")
		docker-compose stop
		docker-compose restart
	;;

	*)
		docker-compose up $container
	;;
esac

