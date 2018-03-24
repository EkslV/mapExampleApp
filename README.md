# mapExampleApp
This is the android app with a single activity that displays a map.

## Features

The activity supports the following features:
- Add Marker button - this button adds a marker to the map in the
middle of the screen and prompts the user for a name for the marker,
then stores its coordinates and name in a Firebase Database.
- On marker click the name of the marker and its location is being displayed.
- Supports clustering when multiple markers are near each other.
- On cluster click the map is zoomed in until the cluster is no longer needed.

## Architectural components

- Clean architecture MVP using Moxy and Dagger2 libraries

## Used libraries

- Google maps API
- Firebase database SDK
- GeoFire for firebase
