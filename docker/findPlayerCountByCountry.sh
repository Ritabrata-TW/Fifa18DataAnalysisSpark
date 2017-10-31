#!/usr/bin/env bash

container="players-by-country"

case $1 in
	"hot-reload")
		cd ..
		./gradlew build
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

